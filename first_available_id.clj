;; Original solution, codetree 29
(defn first-available-id [xs]
  (let [taken (set xs)
        [curr _] (filter #((complement taken) %) (drop 1 (range)))]
    curr))

;; How much more beautiful isn't this?
;; codetree is 14 now
;; Can add assertion: {:pre [(set? taken)]}
(defn first-available-id [taken]
  (first (drop-while taken (iterate inc 1))))

;; Also pretty elegant
;; codetree: 6+11=17
(defn first-available-id [taken]
  (->> (range 1 Integer/MAX_VALUE)
       (filter (complement taken))
       first))

;; test
(= '(1 2 3) (map first-available-id [#{} #{5 3 1} #{5 4 1 2}]))

