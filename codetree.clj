;; Measures the code complexity in terms of the AST.
;; A better metric than lines of code.

;; Surely there's a better way using reducers and walking?
(defn codetree [form]
  (let [n (atom 0)]
    (clojure.walk/postwalk
     (fn [_] (swap! n inc))
     form)))

;; Either quote the expression to call it directly
(codetree
 '(defn fib [n]
    (condp = n
      0 0
      1 1
      (+ (fib (dec n)) (fib (- n 2)))))) ;; => 26

;; Or read-string from a source file
(codetree (read-string (slurp "fib.clj"))) ;; => 26
