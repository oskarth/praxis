(defn fib [n]
  (condp = n
    0 0
    1 1
    (+ (fib (dec n)) (fib (- n 2)))))
