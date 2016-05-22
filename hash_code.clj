;; Deals with overflows and all types AFAIK
;; Reasonably fast and presumably uniform?
;; How can we make it better / simpler?

(defn hash-code [x]
  (loop [n 1 cs (-> x str seq)]
    (if-let [c (first cs)]
      (recur (unchecked-add (unchecked-multiply 31 n) (unchecked-int c))
             (rest cs))
      n)))

(hash-code "foobar blitzball")

;; test, should be true
(every? number? (map hash-code [\a 1 55 "abc" :foo [1 2 3] {:foo :bar} "The quick brown fox jumps over the lazy dog"]))
