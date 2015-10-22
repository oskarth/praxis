(defn vec->tree
  "Given a vector representation of a tree, produce a tree."
  [t]
  (if (vector? t)
    (let [[val left right] t]
      {:value val
       :left (vec->tree left)
       :right (vec->tree right)})
    t))

(defn walk
  "Walks a tree in some order and performs f on each node."
  [t f order]
  (when t
    (doseq [o order]
      (if (= o :visit)
        (f (:value t))
        (walk (o t) f order)))))

(defn pre-order [t f] (walk t f [:visit :left :right]))
(defn in-order [t f] (walk t f [:left :visit :right]))
(defn post-order [t f] (walk t f [:left :right :visit]))

;; Hm, how make the recursive call?
;; In 8 3 1 6, I don't understand how we can know if 6 is left node of 1
;; Or right node of 8.

;; If it is a binary search tree we could conj and it would be
;; equivalent. is that what they mean? But then 6 couldn't be right of 8...

(defn make-tree
  "Given a sequence representing tree in some order, reconstruct tree."
  [coll]
  (if (seq coll)
    {:value (first coll)
     :left (make-tree (rest coll))
     :right (make-tree (rest coll)) ;; how to fix order? 
     }))

;;(prn  (make-tree @a))
;; Given that it's a pre-order, [:visit :left :right] =>
;; recursive soln, we spawn calls right, calls to xconj




(comment

  ;; ~1h now

  ;; Vector tree
  (def vt [1 2 3])
  (def vt' [1 [2 3 4] 5])
  (def vt'' [8 [3 [1] [6 [4] [7]]] [10 nil [14 [13]]]])

  (clojure.pprint/pprint (vec->tree vt''))

  (def a (atom [])) ;; temp atom to make a list of nodes from walking a tree
  (pre-order (vec->tree vt'') #(swap! a conj %))
  @a
  
  (in-order (vec->tree vt'') prn)
  (post-order (vec->tree vt'') prn)

  )


