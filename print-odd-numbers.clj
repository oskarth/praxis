(ns odd)

;; print odd 1..99

(defn print-odds []
  (doseq [n (filter odd? (range 100))]
    (println n)))

;; could also do  (doseq [n (range 1 100) :when (odd? n)], equivalent
