(defn first-available-id [xs]
  (let [taken (set xs)
        [curr _] (filter #((complement taken) %) (drop 1 (range)))]
    curr))

(first-available-id [5 3 1])
(first-available-id [5 4 1 2])
(first-available-id [])
