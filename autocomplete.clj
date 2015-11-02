(ns autocomplete)

;; 1. Read in dictionary
;; 2. Sort it (it's already sorted)
;; 3. Write autocomplete function that
;;    - takes a substring
;;    - does binary search on substring and finds closest start match
;;    - return up to 10 next words, conditional on being substrings

;; 1915
(def raw-dict (slurp "/usr/share/dict/words"))

(def words (clojure.string/split (apply str raw-dict) #"\n"))

(def sorted-words (sort words))

(def small-dict
  ["abacus"
   "foobar"
   "fool"
   "hardly"])

(count sorted-words)

(take 10 sorted-words)

;; TODO: check substring too
(defn binary-search [x xs]
  (loop [lo 0 hi (dec (count xs))]
    (if (< hi lo) nil
        (let [mid (quot (+ lo hi) 2)]
          (cond (neg? (compare x (nth xs mid))) (recur lo (dec mid))
                (pos? (compare x (nth xs mid))) (recur (inc mid) hi)
                :else            mid)))))

(binary-search "Ababua" sorted-words)

;; This should return index 1, cause that's prefix substring match
;; foo is a substring of foobar, then +1 and continue to look
(binary-search "foo" small-dict)
