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

;; Q: if I eval (map #(* (inc row) %) (range 13)) can I temp bind row?
;; Wrap in let, or smt
