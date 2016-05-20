;; Deals with overflows and all types AFAIK
;; Reasonably fast and presumably uniform?
;; How can we make it better / simpler?

(defn hash-code [x]
  (if (char? x) (long x)
      (loop [hash 1 work (str x)]
        (if-let [[curr & todo] (seq work)]
          (recur (unchecked-add (unchecked-multiply 31 hash)
                                (unchecked-long (hash-code curr)))
                 todo)
          hash))))

;; test, should be true
(every? number? (map hash-code [\a 1 55 "abc" :foo [1 2 3] {:foo :bar} "The quick brown fox jumps over the lazy dog"]))
