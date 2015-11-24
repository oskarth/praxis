(ns )

;; 940

(defn josephus [n m]
  (loop [people (range n) acc []]
    (let [match? #(zero? (mod % m))]
      (if (seq (rest people))
        ;; for loop?
        ;; something something remove/filter, too tired.
        ;; (for [i (range (count people)) :when (match? i)])
        (recur (remove match? people) (filter match? people))
        acc))))

(def match? #(zero? (mod % 3)))
(def people (range 41))
(def people (remove match? people))
people
;;(for [i (range 10) :when (match? i)] i)

(for [i (range 10)]
  i)

;; for each thingy we need to calc pops

(filter match? people)

;; list of 41 people, numbered from 0 to n-1 in order of execution
;; taking every 3rd one
(josephus 41 3)

;; say we take every 3rd person and put in list.
;; thn we call josephus again with that

;; we are filtering modulo
;; assuming we have 10 people and take every 3rd
(range 10)

;; not on value, on key
(def pred-our #(zero? (mod % 3)))
;; but: assumes on val, which is a bit wrong

(filter #(zero? (mod % 3)) (range 10))

;; keep going as long as there are more than 1 right

;; still really tired.

;; do this once, then we get new part?
;;(take 1 (drop 2 (range 10)))

;; then take and drop?

(def coll (cycle (range 3)))

(mod 10 3)
