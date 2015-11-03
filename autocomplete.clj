(ns autocomplete)

(def raw-dict (slurp "/usr/share/dict/words"))

(def words (clojure.string/split (apply str raw-dict) #"\n"))

(def sorted-words (into [] (sort words)))

(def small-dict
  ["abacus"
   "foobar"
   "fool"
   "hardly"])

(defn min' [x y] (if (nil? x) y (min x y)))

(defn subs? [s r]
  (= s (apply str (take (count s) r))))

(defn find-first-substring
  "Binary searches over ss until it finds the first substring."
  [s ss]
  (loop [lo 0
         hi (dec (count ss))
         res nil]
    (if (< hi lo) res
        (let [mid (quot (+ lo hi) 2)
              cmp (compare s (nth ss mid))
              sub (subs? s (nth ss mid))
              newres (if sub (nil-or-min res mid) res)]
          (cond
            (neg? cmp) (recur lo (dec mid) newres)
            (pos? cmp) (recur (inc mid) hi newres)
            :else newres)))))

(find-first-substring "foo" small-dict)
(find-first-substring "fool" small-dict)
(find-first-substring "foolhard" small-dict)

(find-first-substring "abil" sorted-words)

(nth sorted-words 25395)

(defn get-substrings [s ss n]
  (if-let [i (find-first-substring s ss)]
    (filter
     #(subs? s %)
     (map #(nth ss (+ i %)) (range n)))))

;; Nice!
;; Up to top N autocomplete
(get-substrings "abi" sorted-words 10)
(get-substrings "abil" sorted-words 10)
(get-substrings "foohardyhar" sorted-words 10)
