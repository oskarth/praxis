;; In reality: use sorted-set/sorted-map

;; A BST:  [20 [10 [5] []] [30]])

;; Alternative representation?
;; {:val 20 :left {:val 10 :left {:val 5}} :right {:val 30}}

;; Return something else? depends on how being used
#_(defn lookup
  "Returns subtree if x is tree, otherwise it returns false."
  [x tree]
  (let [[val left right] tree]
    (if (nil? val) false
      (cond (< x val) (lookup x left)
            (> x val) (lookup x right)
            :else tree))))

;; What is this?
;; (defn enlist [x tree])


;; 6.2 JOC Map
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Very nice.
(defn xconj [t v]
  (cond
    (nil? t)       {:val v, :L nil, :R nil}
    (< v (:val t)) {:val (:val t) :L (xconj (:L t) v) :R (:R t)}
    :else          {:val (:val t) :L (:L t) :R (xconj (:R t) v)}))

(defn xseq [t]
  (when t
    (concat (xseq (:L t)) [(:val t)] (xseq (:R t)))))

(def tree1 (xconj nil 5))
(def tree1 (xconj tree1 3))
(def tree1 (xconj tree1 2))
(def tree2 (xconj tree1 7))
(xseq tree2)
(identical? (:L tree1) (:L tree2))


;; 9.3 JOC Records
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Creates a java class with constructor that takes arguments as values
;; explicit import to other ns with :import joy.udp.TreeNode
(defrecord TreeNode [val l r])

;;(TreeNode. 5 nil nil)

;; Explicit type.
;; Simple and specific idiom for documenting expected fields the
;; object. Also less mem and quicker etc.

(defn xconj [t v]
  (cond
    (nil? t)       (TreeNode. v nil nil)
    (< v (:val t)) (TreeNode. (:val t) (xconj (:l t) v) (:r t))
    :else          (TreeNode. (:val t) (:l t) (xconj (:r t) v))))

(defn xseq [t]
  (when t
    (concat (xseq (:l t)) [(:val t)] (xseq (:r t)))))

(def sample-tree (reduce xconj nil [3 5 2 4 6]))

;; Praxis solution but with Clojure bend
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defrecord BinaryTree [k v l r])

;; How do I extend empty? to work as I want on BTs?

(defprotocol SortedSet
  (empty? [t]))

(extend-type BinaryTree
  SortedSet
  (empty? [t] true))

;;(defprotocol BinaryTree)

(defn tree [k v l r] (vector k v l r))
(defn key [t]  (get t 0))
(defn val [t]  (get t 1))
(defn lkid [t]  (get t 2))
(defn rkid [t]  (get t 3))
;(defn empty?)
;;(defn leaf? [t])

(defn lookup [lt? t k]
  (cond (nil? t)

