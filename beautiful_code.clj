(ns beautifulcode)

;; ~1h, think I have it?

;; Alternative is to operate on lists and such, possibly better.
;; Also two tests: one is first sol and other is total time until master, which includes snafu solve thingy. Keep accurate records!

(declare match-here)
(declare match-star)
(declare match')
(declare match-here')
(declare match-star')

(defn trex [re text]
  (match' (seq re) (seq text)))

(defn match' [regex text]
  (let [[r & rs] regex
        [t & ts] text]
     (cond (empty? regex) true
           (empty? text)  false
           (= r \^)       (match-here' rs text)
           :else          (or (match-here' regex text) (match' regex ts)))))

;; here atm, destructuring is cool and all but whats up with experiment
;; did I solve it first hour? IDK.

;; TTM - time to master per problem, where master is defined as 20m after a
;; week. Maybe.

;; So 4.5h + 1.5h for last problem. 1h now, then ...howm much?
;; 40 min more so far with soln
(defn match-here' [regex text]
  (let [[r & rs] regex
        [t & ts] text]
    (cond (empty? regex)                        true
          (and (rest rs) (= r \*))              (match-star r (rest rs) text)
          (and (= r \$) (empty? rs))            (empty? text)
          (and (rest ts) (or (= r \.) (= r t))) (match-here rs ts)
          )))

;; shouldn't that be (seq (rest rs))?

(get "hello" 1)
(first (rest "hello"))

(defn match-star' [])

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
