;; 1325 ~1h, looked things up

;; First, how do we represent a binary tree?
;; [a] or [a nil c] or [a b nil] (last one not necessary but there for clarity)
(def vectree [8 [3 [1] [6 [4] [7]]] [10 nil [14 [13] nil]]])

;; So pretty, if only I wrote it myself.
(defn vec-to-tree [t]
  (if (vector? t)
      (let [[val left right] t]
        {:val val
         :left (vec-to-tree left)
         :right (vec-to-tree right)})
      t))

;; Different types of walking, visit left right etc.
;; print each time
(defn walk [node order]
  (when node
    (doseq [o order]
      (if (= o :visit)
        (print (format "%2d" (:val node)))
        (walk (o node) order)))))
  
(defn foo [vt]
  (walk (vec-to-tree vt) [:visit :left :right]))

