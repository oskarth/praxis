(ns string-brute)

(defn search [pattern string]
  (loop [pi 0 si 0 res '()]
    (cond (= (count pattern) pi) (recur (inc pi) (inc si) (conj res si))
          (nil? (get string si)) res
          (= (get pattern pi) (get string si)) (recur (inc pi) (inc si) res)
          :else (recur 0 (inc si) res))))



(comment
  ;; doing some keyboard experiments, so not a lot of code but something.

  ;; Notes:
  ;; Take dictionary, sort words then autocomplete fn. Actually that's a separate problem! Good.
  ;; Show next 10 autocompletes.
  ;; Starting to like spacemacs.

  (def pattern "foo")
  (def string "hello foo bar there")

  (def dict (slurp "/usr/share/dict/words"))
  (def mindict (apply str (take 1000 dict)))

  (search "aar" mindict)
  (nth mindict 37)

  (find-string 34 "")


  ;; what if last word? newline there too ithink
  (defn find-string [i s]
    (let [char (nth mindict i)]
      (if (= char \newline) s
          (find-string (inc i) (str s char)))))




  (= (search "foo" "hello foo bar") 9)
  (= (search "foo" "hello fo") false))
