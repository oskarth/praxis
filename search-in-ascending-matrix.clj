(ns matrix)

;; 1155 - start much earlier, first first thing!
;; ~20m for naive

;; first do naive row by row

(defn search-naive [matrix x]
  (let [m (count matrix)
        n (count (get matrix 0))
        poss (for [r (range m) c (range n)] [r c])
        hits (filter #(= (get-in matrix %) x) poss)]
    (if (seq hits) (first hits) nil)))

;; Let's exploit the ordering
;; If we check 1-1 and x is > then we know not to check other
;; So 1-1, then 2-2, and then we know it's one of those four.
;; Four being in 2 row or 2 col.
;; So find midpoint then check other.
(defn search [matrix x]
  (let [m (count matrix)
        n (count (get matrix 0))
        poss (for [r (range m) c (range n)] [r c])
        hits (filter #(= (get-in matrix %) x) poss)]
    (if (seq hits) (first hits) nil)))


(comment
  ;; Nice trick to get around scoping!
  ;; Now we can just evaluate let and we are calling the fn.
  (def matrix [[1 5 7 9] [4 6 10 15] [8 11 12 19] [14 16 18 21]])
  (def x 11)
  
  (search-naive matrix 11) ;; => [2 1]
  (search-naive matrix 13) ;; => nil

)
