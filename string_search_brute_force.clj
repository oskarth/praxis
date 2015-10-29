(ns string-brute)

(defn search [pattern string]
  (loop [pi 0 si 0]
    (cond (= (count pattern) pi) si   ;; return index in string
          (nil? (get string si)) false ;; no match
          (= (get pattern pi) (get string si)) (recur (inc pi) (inc si))
          :else (recur 0 (inc si)))))


(comment

  (def pattern "foo")
  (def string "hello foo bar there")

  (= (search "foo" "hello foo bar") 9)
  (= (search "foo" "hello fo") false)
  )
