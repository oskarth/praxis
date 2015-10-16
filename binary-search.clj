;; If we start at the middle, target <= middle or target > middle.
;; We maintain lo and hi and compute a middle between them. If it's less than,
;; we iterate on lo..mid, else mid+1..hi. What is our base case?
;; Invariant to maintain: lo < hi, if it's broken we return.
;;
;; Key: make a smaller subset each recursive call.
;;
;; Minimal example:
;; [2 3 4] => 0..3, 0..1 or 2..3. (a) mid is 0 or (b) 2
;; (a) target < 0, try to check 0..0. target > 0, check 1..1.
;; So base case if difference between lo and hi is 0, and that is not the
;; target.

;; Mistake I made: confused index with value. What is a good variable name
;; praxis to make sure this doesn't happen?

;; The function body is also too long for my taste.

#_(defn bsearch [target xs]
 (letfn [(find-mid [lo hi] (quot (+ lo hi) 2))]
  (loop [lo 0 hi (dec (count xs))]
   (if (< hi lo)
       -1
       (let [mi (find-mid lo hi) mid (nth xs mi)]
        (cond (= mid target) mi
              (< target mid) (recur lo (dec mi))
              :else          (recur (inc mi) hi)))))))

;; Invariant restated:
;; If x is present, it must be between lo and hi. Each recur call is on a strict
;; subset, so when (< hi lo) we return -1

;; Refactored version.
;; - variables: target -> x, mi -> mid
;; - if then on same line
;; - inline mid index assignment and remove needless mid element
;; - make left/right branching more explicit and symmetrical
(defn bsearch [x xs]
 (loop [lo 0 hi (dec (count xs))]
  (if (< hi lo) -1
    (let [mid (quot (+ lo hi) 2)]
     (cond (< x (get xs mid)) (recur lo (dec mid))
           (> x (get xs mid)) (recur (inc mid) hi)
           :else mid)))))

(and
  (= (bsearch 32, [13 19 24 29 32 37 43]) 4)
  (= (bsearch 32, [13 19 24 29 33 37 43]) -1))
