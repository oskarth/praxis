(ns beautifulcode)

;; 50m done.

;; ~1h, think I have it?

;; Port Robk Pike matcher

;; Alternative is to operate on lists and such, possibly better.

(declare match-here)
(declare match-star)

(defn match
  "Search for re anywhere in text."
  [re text]
  (if (= (subs re 0 1) "^")
    (match-here (subs re 1) text)
    (loop [text text]
        (cond (empty? text) false
              (match-here re text) true
              :else (recur (subs text 1))))))

(defn match-here
  "Search for re at beginning of text."
  [re text]
  (cond (empty? re) true

        (= (subs re 1) "*") ;; then take first?
        (match-star (subs re 0 1) (subs re 2) text)

        (and (= (subs re 0 1) "$") (empty? (subs re 1)))
        (empty? text)

        (or (and (not (empty? text)) (= (subs re 0 1) "."))
            (= (subs re 0 1) (subs text 0 1)))
        (match-here (subs re 1) (subs text 1))

        :else false))

(defn match-star
  "Search for c*re at beginning of text."
  [c re text]
  (cond (empty? text) false ;; or they don't match, missing condition?
        (match-here re text) true
        :else (match-star c re (subs text 1))))

;; Testing - think we could do a lot more, but how systematically?

(and
 (match "a" "abc")
 (match "bc" "abc")
 (not (match "ac" "abc"))
 (match "a.c" "abc")
 (match "a*" "daa")
 (match "a*" "da")
 (match "^a" "abc")
 (not (match "a$" "abc"))
 (match "a$" "abca")
 )

(subs "abc" 1)
