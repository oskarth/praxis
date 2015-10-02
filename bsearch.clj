; Binary Search
; http://programmingpraxis.com/2009/03/23/binary-search/
; Your first task is to write a function that takes a target number and an array
; of numbers in non-decreasing order and returns either the position of the number
; in the array, or -1 to indicate the target number is not in the array. For
; instance, bsearch(32, [13 19 24 29 32 37 43]) should return 4, since 32 is the
; fourth element of the array (counting from zero).

;; Not very pretty, and probably buggy.
(defn bsearch [target v]
  (letfn [(midpoint [l r] (int (/ (- r l) 2)))]
    (loop [l -1 r 8 m (midpoint l r)]
      (cond (= target (get v m))       m
            (= target (get v (inc m))) (inc m)
            (< (- r l) 2)               -1
            (< target (get v m))       (recur l (midpoint l r) (midpoint l r))
            :else                      (recur (midpoint l r) r (midpoint l r))))))
