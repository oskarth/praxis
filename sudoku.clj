(ns sudoku)

;; Started at 1340-1420 ish

;; Translating Norvig's Sudoku solver (http://norvig.com/sudoku.html)
;; into Clojure.

(defn cross
  "Cross product of elements in c1 and elements in c2."
  [c1 c2]
  (for [a c1 b c2] (str a b)))

;; XXX: Can we use a better collection type for this?
;; Maybe we should conj
(def digits #{"1" "2" "3" "4" "5" "6" "7" "8" "9"})
(def rows #{"A" "B" "C" "D" "E" "F" "G" "H" "I"})
(def cols digits)
(def squares (cross rows cols))

(def unitlist) ;; concat of all

(for [c cols] (cross rows c))
(for [r rows] (cross r cols))

;; for diagonals
(for [r rows] (cross r cols))


;; OR I could do TTT.
