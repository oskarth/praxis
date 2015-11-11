(ns multiplication)

;;; 1225, 5-10 min but :hmhm

(dotimes [m 12]
  (println
   (apply str
          (map #(format "%4d" %)
               (map #(* (inc m) %) (range 1 (inc 12)))))))


;; Still, I didn't use the print fn! Should just be. Not satisfied until I get that. Good that you obviously forgot though.

(dotimes [m 12]
  (dotimes [n 12]
    (print (format "%3d " (* (inc m) (inc n)))))
  (println))
