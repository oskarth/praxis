
;; Don't need all this

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
  
(defn foo [vt] (walk (vec-to-tree vt) [:visit :left :right]))

;;---------------------------------------------------------------------------

(def vectree [8 [3 [1] [6 [4] [7]]] [10 nil [14 [13] nil]]])

;; It appears to be a BST, not a binary tree, which makes the question easier.

;; Key insight: If we are at a node that's greater than both n and m, we go left.
;; Vice versa for less than and right. Only if it's in between have we found it.
;; With this we might not need scaffolding above.

;; Assuming n and m are nodes in tree and m<n.
;; m leq node leq n
(defn lct [node m n]
  (let [[val left right] node]
    (cond (<= m val n) val
          (<= val m)   (lct right m n)
          :else        (lct left m n))))

(and
 (= (lct vectree 4 7) 6)
 (= (lct vectree 4 10) 8)
 (= (lct vectree 1 4) 3)
 (= (lct vectree 1 3) 3)
 (= (lct vectree 3 6) 3))
