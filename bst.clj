
;; http://programmingpraxis.com/2010/03/05/binary-search-tree/2/

(defn tree [k v l r] {:key k :val v :left l :right r})

(defn lookup [t k]
  (cond (nil? t) false
        (< k (:key t)) (lookup (:left t) k)
        (> k (:key t)) (lookup (:right t) k)
        :else (:val t)))

(defn insert [t k v]
  (cond
    (nil? t) (tree k v nil nil)
    (< k (:key t)) (tree (:key t) (:val t) (insert (:left t) k v) (:right t))
    (> k (:key t)) (tree (:key t) (:val t) (:left t) (insert (:right t) k v))
    :else (tree k v (:left t) (:right t))))

;; A bit tricky, hence rotation I guess?
(defn delete [t k]
)

(defn enlist [t]
  (when (:key t)
    (concat (enlist (:left t)) [(:val t)] (enlist (:right t)))))
