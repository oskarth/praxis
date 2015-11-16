(ns binarysearch)

;; 9m, ~5m

(defn bsearch [target lst]
  (loop [lo 0 hi (dec (count lst))]
    (println "lo hi" lo hi)
    (if (< hi lo) -1
        (let [mid (quot (+ lo hi) 2)]
        (cond (> (get lst mid) target) (recur lo (dec mid))
              (< (get lst mid) target) (recur (inc mid) hi)
              :else mid)))))

(and
  (= (bsearch 32, [13 19 24 29 32 37 43]) 4)
  (= (bsearch 32, [13 19 24 29 33 37 43]) -1))
