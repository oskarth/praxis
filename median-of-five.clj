(ns median-of-five)
;; 1355

;; Given a list of items
(def l (into [] (repeatedly 5 #(rand-int 100))))
l
;; What is the definition of median?
;; If we sort it, it's the value that is in the middle
;; Oh right, it's just of five values, so it's the one in the middle
;; (5 10 15 20 31) 15 is the median here
;; What is faster than sorting? :o
;; Sorting is like this

(nth (sort l) 2) ;; 29 for this one
;; How many comparisons is this? I have no idea. :/
;; How would I find out? depends on sort logic, if re-implement and log?
;; or other way to find out? profiling? good Q

;; we can count how many smaller right

(defn median-of-five [[a b c d e]]
  (if (> a b)
    ;; swap a b but continue etc
)
  )

;; this is a bit tricky with mutation so I'm just gonna grok logic for now

;; if > a b, we swap them so we know a <b
;; same with d and e
;; we now have a < b       d < e
;; now two cases:
;; 1. if a < d we check if b>c and swap then
;; we also check d < b and return b if b < e
;; bah

;; I tihnk we could do a nice pattern matching thing with core logic/mathc here
;; or maybe just in C? dk.

#_(let [a 10 b 5]
  (do (set! a b)))

(median-of-five v)

(def v [5 0 10 20 40]) ;; 10 here
(nth v 0)
(nth v 1)
(nth v 2)
(nth v 3)
(nth v 4)

;; why do we sort? so we gcan et middle element.
;; we just need to find the lowest 3






(prn "hi")

(defn foo [] (prn "hi"))

(foo)
