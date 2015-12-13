(ns csv
  (require [clojure.test :refer [deftest testing is]]))

;; 1330

;; does looking up solution help vs not?

;; ASCII
;; a line-termination-sequence is a CR character, or a line-feed character, or both characters in either order.

;; how do I see CRs?

;; Ok, been 1h.

;; Cap at 90m? Or 1h?

;; ok clock in, 1h

;; XXX: doesn't deal well with embedded newlines
(defn file->lines [filename]
  (let [file (slurp filename)]
    (clojure.string/split file #"\n")))

(def sample (file->lines "sample.csv"))

;; get rid of quoting
;; various rules

(defn remove-quoting [s] (clojure.string/replace s "\"" ""))
(defn comma-separate [ss] (clojure.string/split ss #","))
(defn sep-join [ss sep] (clojure.string/join sep ss))
;;(remove-quoting (second sample))

(defn parse-csv [coll sep]
  (let [xss (map comma-separate coll)]
    (map #(sep-join % sep) xss)))

(clojure.pprint/pprint
 (parse-csv sample "|"))

;; newlines go
(defn save-off [filename coll]
  (spit filename (clojure.string/join "\n" (parse-csv coll "|"))))

(save-off "test.out" (file->lines "sample.csv"))

;; lets write some tests here.
;; oops, sin't what we are testing just an artifcat?
;; 20m on this.

(deftest csv
  (testing "unquoed character strings, and more"
    (let [raw-csv (slurp "sample.csv")
          parsed (save-off "test.out" (file->lines "sample.csv"))
          expected (slurp "expected.out")
          output (slurp "test.out")]
      (clojure.pprint/pprint (str "EXPECTED\n" expected))
      (clojure.pprint/pprint (str "OUTPUT\n" output))
      (is (= (first expected) (first output)))
      (is (= (nth expected 15) (nth output 15)))
      (is (= (nth expected 14) (nth output 14)))
      )))
