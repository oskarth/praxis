(ns multiplication)

;; 1310 45 ~1h :/

(defn print-mul [n]
  (doseq [row (range 1 (inc n))]
    (let [col (map #(* row %) (range 1 (inc n)))]
      (println (apply str (map #(format "%4d" %) col))))))

(defn print-mul-headers [n]
  (println "     |"
           (apply str (map #(format "%4d" %) (range 1 (inc n)))))
  (println (apply str (repeat (- (* 4 (+ n 2)) 1) "-")))
  (doseq [row (range 1 (inc n))]
    (let [col (map #(* row %) (range 1 (inc n)))]
      (println (format "%4d" row) "|"
               (apply str (map #(format "%4d" %) col))))))


;; Better solution. dotimes vs doseq
;; doseq would work too, but you didn't use print?
;; prn is also newline. Bah. dotimes better though cause impliclity doing
;; range calc. A reason you haven't noticed print is because when you
;; evaluate it it flushes (?) to newline!
;; See this:
;; (dotimes [x 4] (print 2))
(defn times-table [n]
  (dotimes [x n]
    (dotimes [y n]
      (print (format "%3d " (* (inc x) (inc y)))))
    (println)))

(comment
  (let [n 4]
    (doseq [x (range n)]
      (doseq [y (range n)]
        (print (format "%3d " (* (inc x) (inc y)))))
      (println)))
)
