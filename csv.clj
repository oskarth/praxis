(ns csv
  (:require [clojure.test :refer [deftest is run-tests]]))

(def sample-csv (slurp "sample.csv"))

(defn trace [x] (do (println x) x))

;; Make a state diagram!

;; STATES
;; start
;; not-field
;; quoted-field
;; may-be-doubled quotes
;; unquoted-field
;; carriage-return
;; line-feed

;; State diagram, ish
;; | C    | CURR                 | NEXT
;;-------------------------------------------------------
;; | eof  | start                | =>
;; | cr   | start                | carriage-return
;; | nl   | start                | line-feed
;; | "    | start                | quoted-field
;; | ,    | start                | not-field
;; | else | start                | unquoted-field
;; | eof  | not-field            | =>
;; | cr   | not-field            | carriage-return
;; | nl   | not-field            | line-feed
;; | "    | not-field            | quoted-field
;; | ,    | not-field            | not-field
;; | else | not-field            | unquoted-field
;; | eof  | quoted-field         | =>
;; | "    | quoted-field         | may-be-double-quotes
;; | else | quoted-field         | quoted-field
;; | eof  | may-be-double-quotes | =>
;; | cr   | may-be-double-quotes | carriage-return
;; | nl   | may-be-double-quotes | line-feed
;; | "    | may-be-double-quotes | quoted-field
;; | ,    | may-be-double-quotes | not-field
;; | else | may-be-double-quotes | unquoted-field
;; | eof  | unquoted-field       | =>
;; | cr   | unquoted-field       | carriage-return
;; | nl   | unquoted-field       | line-feed
;; | ,    | unquoted-field       | not-field
;; | else | unquoted-field       | unquoted-field
;; | eof  | carriage-return      | =>
;; | nl   | carriage-return      | =>
;; | else | carriage-return      | =>
;; | eof  | line-feed            | =>
;; | cr   | line-feed            | =>
;; | else | line-feed            | =>

;; Awfully many default states

;; all arguments take x and xs
;; b default it seems to be [] conj xs x

;; :return cr
;; :newline newline
;; :quote :quoted

(defn read-csv-record [peek-char read-char!]
  (letfn
      [(start [x xs]
         (let [c (read-char!)]
           (condp = c
             nil      (conj xs x)
             \return  (carriage-return [] (conj xs x))
             \newline (line-feed [] (conj xs x))
             \"       (quoted-field [] xs)
             \,       (start [] (conj xs x))
             (unquoted-field (conj x c) xs))))
       (quoted-field [x xs]
         (let [c (read-char!)]
           (condp = c
             nil (conj xs x)
             \"  (may-be-doubled-quotes x xs)
             (quoted-field (conj x c) xs))))
       (may-be-doubled-quotes [x xs]
         (let [c (read-char!)]
           (condp = c
             nil      (conj xs x)
             \return  (carriage-return [] (conj xs x))
             \newline (line-feed [] (conj xs x))
             \"       (quoted-field (conj x \") xs)
             \,       (start [] (conj xs x))
             (unquoted-field (conj x c) xs))))
       (unquoted-field [x xs]
         (let [c (read-char!)]
           (condp = c
             nil      (conj xs x)
             \return  (carriage-return [] (conj xs x))
             \newline (line-feed [] (conj xs x))
             \,       (start [] (conj xs x))
             (unquoted-field (conj x c) xs))))
       (carriage-return [x xs]
         (let [c (peek-char)]
           (condp = c
             nil       xs
             \newline (do (read-char!) xs)
             xs)))
       (line-feed [x xs]
         (let [c (peek-char)]
           (condp = c
             nil     xs
             \return (do (read-char!) xs)
             xs)))]
    (start [] [])))

(defn stringify-csv [parsed-csv]
  (clojure.string/join \| (map #(apply str %) parsed-csv)))

(defn take-first! [atm]
  (let [[x _] [(first @atm) (swap! atm rest)]]
    x))

(defn with-pseudo-input [input f]
  (let [stream (atom input)]
    (loop [acc []]
      (if (seq @stream)
        (recur (conj acc (f #(first @stream) #(take-first! stream))))
        acc))))

(defn machine [csv]
  (clojure.string/join
   \newline
   (map stringify-csv
        (with-pseudo-input csv read-csv-record))))

;; `diff expected.out csv-output` should return nil.
(spit "csv-output" (machine sample-csv))

(deftest machine-tests
  (is (= (machine "1,abc,def ghi,jkl,unquoted character strings\n")
         "1|abc|def ghi|jkl|unquoted character strings"))

  (is (= (machine "2,\"abc\",\"def ghi\",\"jkl\",quoted character strings\n")
         "2|abc|def ghi|jkl|quoted character strings"))

  (is (= (machine "3,123,456,789,numbers\n")
         "3|123|456|789|numbers"))

  (is (= (machine "4, abc,def , ghi ,strings with whitespace\n")
         "4| abc|def | ghi |strings with whitespace"))

  (is (= (machine "5, \"abc\",\"def\" , \"ghi\" ,quoted strings with whitespace\n")
         "5| \"abc\"|def | \"ghi\" |quoted strings with whitespace"))

  (is (= (machine "6, 123,456 , 789 ,numbers with whitespace\n")
         "6| 123|456 | 789 |numbers with whitespace"))

  (is (= (machine "7,TAB123,456TAB,TAB789TAB,numbers with tabs for whitespace\n")
         "7|TAB123|456TAB|TAB789TAB|numbers with tabs for whitespace"))

  (is (= (machine "8, -123, +456, 1E3,more numbers with whitespace\n")
         "8| -123| +456| 1E3|more numbers with whitespace"))

  (is (= (machine "9,123 456,123\"456, 123 456 ,strange numbers\n")
         "9|123 456|123\"456| 123 456 |strange numbers"))

  (is (= (machine "10,abc\",de\"f,g\"hi,embedded quotes\n")
         "10|abc\"|de\"f|g\"hi|embedded quotes"))


  (is (= (machine "11,\"abc\"\"\",\"de\"\"f\",\"g\"\"hi\",quoted embedded quotes\n")
         "11|abc\"|de\"f|g\"hi|quoted embedded quotes"))

  (is (= (machine "12,\"\",\"\" \"\",\"\"x\"\",doubled quotes\n")
         "12|| \"\"|x\"\"|doubled quotes"))

  (is (= (machine "13,\"abc\"def,abc\"def\",\"abc\" \"def\",strange quotes\n")
         "13|abcdef|abc\"def\"|abc \"def\"|strange quotes"))

  (is (= (machine "14,,\"\", ,empty fields\n")
         "14||| |empty fields"))

  (is (= (machine "15,abc,\"def\n ghi\",jkl,embedded newline\n")
         "15|abc|def\n ghi|jkl|embedded newline"))

  (is (= (machine "16,abc,\"def\",789,multiple types of fields\n")
         "16|abc|def|789|multiple types of fields")))
