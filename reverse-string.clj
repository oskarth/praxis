;; Took ~5m (9m), didn't get the reduce version.

(defn reverse-string [s]
  (apply str (my-reverse s)))

(defn my-reverse
  ([coll] (my-reverse coll '()))
  ([coll acc]
    (if (empty? coll)
        acc
        (my-reverse (rest coll) (conj acc (first coll))))))

;; Better solution.
;; This works because conj happens at head for lists.
;; For "foo", conj is applied to '() and  \f, then to '(\f) \o etc, which
;; effectively reverses the collection of characters since we use a list type.
(defn reverse' [coll]
  (reduce conj () coll))
