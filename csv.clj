(ns csv
  (:require [clojure.test :refer [deftest is run-tests]]))

;; 60+60+40m+120 = 4.5h

;; 4.5h spent on this. ok this is getting to be too much time commitment. I didn't manage to
;; get it done in H4, so I have no reason to believe it wouldn't take H5, which
;; is too long for me right now.
;; => solution looky.

;; Hemibell
;; H1 ~5m
;; H2 ~20m
;; H3 ~1h
;; H4 ~3h

;; From old
;; Why did it take me so long ~30m to make small test cases? Shoudl be first thing! Instead waste time on printing less or only in certain cases. Failure mode. Right thing: define small cases and test them!

;; todo: quick macro to wrap/dewrap with trace
(def sample-csv (slurp "sample.csv"))

(clojure.pprint/pprint sample-csv)

(defn trace [x] (do (println x) x))

(defn base-case-reached? [s n] (= (count s) n))
(defn new-field? [c] (= c \,))
(defn new-line? [c] (= c \newline))
(defn quote? [c] (= c \"))
(defn double-quote? [c prev-quote?] (and prev-quote? (quote? c)))
(defn begin-quote? [c field]      (and (not (seq field)) (quote? c)))
(defn end-quote? [c in-quote?]    (and in-quote? (quote? c)))

;; double-quote characters represented as two successive double-quotes.
;; yet we have the weird numbers too. Oh well.

(defn machine [s]
  (loop [n           0
         in-quote?   false
         prev-quote? false
         field       []
         line        []
         done        []]
    (let [c (get s n)]
      (cond (base-case-reached? s n)
            (clojure.string/join \newline done)

            (new-field? c)
            (recur (inc n)
                   false
                   false
                   []
                   (conj line (apply str field))
                   done)

            (new-line? c)
            (let [new-line (conj line (apply str field))]
              (recur (inc n)
                     false
                     false
                     []
                     []
                     (conj done (clojure.string/join \| new-line))))

            (begin-quote? c field)
            (recur (inc n) true false field line done)

            ;; This logic is wrong, I think. Generally first and last, not first and any quote.
            ;; What are semantics? See case 5, trailing whitespaces. It is only a quote if we start with
            ;; quote, but it can end at any given point? Do we have to do two pass to get rid of the ""s?
            ;; I don't understand.
            (end-quote? c in-quote?)
            (recur (inc n) false false field line done)

            ;; XXX: something wrong with in-quote logic, also case ordering matters a lot here.
            (double-quote? c prev-quote?)
            (recur (inc n) in-quote? true field line done)

            (quote? c)
            (recur (inc n) in-quote? true (conj field c) line done)

             :else
            (recur (inc n)
                   in-quote?
                   false
                   (conj field c)
                   line
                   done)))))

(spit "csv-output" (machine sample-csv))

;; What is the right thing to do here? Pause and no look or what.
;; Fundamental thing here is that I don't really grok semantics.
;; Lets read spec.

;; Can't we just read this line by line from csv file?
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
         "16|abc|def|789|multiple types of fields"))

  )


(run-tests)

;; not getting last one. why not?
;; what if we have two lines?

(machine (apply str (take 96 sample-csv)))

;; jklgs\n2, where is gs from? would expect it to say jkl, then new field!

;; new field only when , so we want to add it also when we are hitting a newline right

;; ok, now we just got the uqoted thing which is odd, it shouldn't take the ns!

;; hemibellian
;; ~5m, 20m, 1h, 3h [1.5 to 5h]
