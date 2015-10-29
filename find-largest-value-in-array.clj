(ns max)

;; 2m

(def v [1 51 2 10 5 2])

;; Alt 1
(reduce max v)

;; Alt 2
(defn my-max [gt-fn x y]
  (if (gt-fn x y) x y))

(reduce #(my-max > %1 %2) v)

;; or inline

(reduce (fn [x y] (if (> x y) x y))
        v)
