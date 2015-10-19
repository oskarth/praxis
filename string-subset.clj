;; 1021 - 1048, ~27m

(defn string-subset?
  "Returns true if s2 is a subset of s1, where count matters. Assumes s1 is
  longer or as long as s2."
  [s1 s2]
    (loop [cs1 (sort s1) cs2 (sort s2)]
      (cond (empty? cs2) true
            (empty? cs1) false
            (= (first cs1) (first cs2)) (string-subset? (rest cs1) (rest cs2))
            :else                       (string-subset? (rest cs1) cs2))))


;; Better solution?
;; It's clear, but uses loop-recur instead of HOFs, and O(nlogn) whereas O(n) is
;; possible. Also better variable names such as main and sub.

;; From Praxis:
;; We next look at two O(n) solutions. Both work by forming a list of the counts
;; of each character in the main string, then subtracting for each character in the
;; substring; if a count ever becomes negative the test fails. One solution uses a
;; hash table to store the counts, the other uses an array indexed by the ordinal
;; of the character.

;; From Praxis comments:
;; Initial impression is that it's hard to read.
;; For each character in main string, we count the number of such chars in both
;; strings. If sub-count leq main for all chars in sub, all good. This is O(nm)
;; soln I believe.
(defn subset-of [s1 s2]
  (every? #(<= (count (filter #{%} s1)) (count (filter #{%} s2))) s1))

(and
 (string-subset? "a" "a")
 (string-subset? "aa" "a")
 (string-subset? "a" "")
 (string-subset? "abcd" "dcb")
 (not (string-subset? "abcd" "dcb"))
 )
