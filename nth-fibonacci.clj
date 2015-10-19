;; 1200-1202, 1210/1209
;; fibonacci: 0 1 1 2 3 5 8

;; naive version
(defn fib1 [n]
  (if (<= n 1) n
    (+ (fib1 (- n 1)) (fib1 (- n 2)))))

;; linear time version
;; start from bottom up
;; integeroverflow before stackoverflow :) at fib2 1000

;; fib0 fib1 we have
;; decrement n to zero, for each iteration
;; a is prevprev and b is prev
(defn fib2
  ([n] (fib2 n 0 1))
  ([n a b]
    (if (= n 0) a
      (fib2 (dec n) b (+ a b)))))

;; no error handling for < 0
;; fib 0
;; fib 1
;; fib 2
