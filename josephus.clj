(ns josephus)

;; ~1h

;; XXX: Might've messed something up
(defn josephus [n m]
  (loop [people (range n) exec []]
    (if (seq (rest people))
      (let [pos (range (count people))
            mth? #(zero? (mod % m))
            map-nth (fn [coll] (map #(nth people %) coll))]
        (recur (map-nth (remove mth? pos))
                   (concat exec (map-nth (filter mth? pos)))))
      (concat exec people))))

;; oh, mine is incorrect. I get 0 3, should be 2 5!
(josephus 41 3)
(last (josephus 41 3)) ;; 40th pos


;; Solutions
;; Can think of it as front and back queue, a la this

#_(defn josephus [n m]
  (loop [idx m front (range n) back [] dead []]
    (cond
      (and (empty? front) (empty? back)) dead
      (empty? front) (recur idx back [] dead)
      (= idx 1) (recur m (next front) back (conj dead (first front)))
      :else (recur (dec idx) (next front) (conj back (first front)) dead))))

(josephus 41 3)
