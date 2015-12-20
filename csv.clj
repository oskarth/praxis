(ns csv
  (:require [clojure.test :refer [deftest is run-tests]]))

(def sample-csv (slurp "sample.csv"))

(defn trace [x] (do (println x) x))

(defn read-csv-record [characters]
  (letfn
      [(read-char! []
         (let [c (first @characters)
               _ (swap! characters rest)]
           c))
       (add-field [field fields]
         (conj fields field))
       (start [field fields]
         (let [c (read-char!)]
           (cond
             (nil? c) fields
             (= c \return) (carriage-return field fields)
             (= c \newline) (line-feed field fields)
             (= c \") (quoted-field field fields)
             (= c \,) (not-field [] (add-field field fields))
             :else (unquoted-field (conj field c) fields))))
       (not-field [field fields]
         (let [c (read-char!)]
           (cond
             (nil? c) (conj fields "")
             (= c \return) (carriage-return [] (add-field field fields))
             (= c \newline) (line-feed [] (add-field field fields))
             (= c \") (quoted-field field fields)
             (= c \,) (not-field [] (add-field field fields))
             :else (unquoted-field (conj field c) fields))))
       (quoted-field [field fields]
         (let [c (read-char!)]
           (cond
             (nil? c) (add-field field fields)
             (= c \") (may-be-doubled-quotes field fields)
             :else (quoted-field (conj field c) fields))))
       (may-be-doubled-quotes [field fields]
         (let [c (read-char!)]
           (cond
             (nil? c) (add-field field fields)
             (= c \return) (carriage-return [] (add-field field fields))
             (= c \newline) (line-feed [] (add-field field fields))
             (= c \") (quoted-field (conj field \") fields)
             (= c \,) (not-field [] (add-field field fields))
             :else (unquoted-field (conj field c) fields))))
       (unquoted-field [field fields]
         (let [c (read-char!)]
           (cond
             (nil? c) (add-field field fields)
             (= c \return) (carriage-return [] (add-field field fields))
             (= c \newline) (line-feed [] (add-field field fields))
             (= c \,) (not-field [] (add-field field fields))
             :else (unquoted-field (conj field c) fields))))
       (carriage-return [field fields]
         (let [c (first @characters)]
           (cond
             (nil? c) fields
             (= c \newline) (do (read-char!) fields)
             :else fields)))
       (line-feed [field fields]
         (let [c (first @characters)]
           (cond (nil? c) fields
                 (= c \return) (do (read-char!) fields)
                 :else fields)))]
    (start [] [])))

(defn stringify-csv [parsed-csv]
  (clojure.string/join \| (map #(apply str %) parsed-csv)))

(defn with-pseudo-input [input f]
  (let [stream (atom input)]
    (loop [lines []]
      (if (seq @stream)
        (recur (conj lines (f stream)))
        lines))))

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
