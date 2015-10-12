; Three Quadratic Sorts
; http://programmingpraxis.com/2009/10/27/three-quadratic-sorts/
; 
; We are going to explore classical sorting algorithms in the next several
; exercises. The rules of the game: We will be sorting arrays of integers with
; elements stored in locations zero through n−1, where n is the number of elements
; in the array. We will always sort into ascending order, and will use <, never ≤,
; to compare array elements. All sorting functions will be called with two
; parameters, the name of the array and its length.
; 
; Today, we will look at three simple sorting algorithms. Bubble sort works by
; repeatedly stepping through the array to be sorted, comparing each pair of
; adjacent elements and interchanging them if they are in the wrong order, until
; the array is sorted. Selection sort works by repeatedly passing through the
; array, at each pass finding the minimum element of the array, interchanging it
; with the first element of the array, then repeating on the sub-array that
; excludes the first element of the array. Insertion sort works the same way that
; card players generally sort their hands; starting from an empty hand, they pick
; up a card, insert it into the correct position, then repeat with each new card
; until no cards remain.
; 
; Your task is to write functions that sort an array using bubble sort, selection
; sort, and insertion sort; you should also write a test program that can be used
; for any of the sorting algorithms. When you are finished, you are welcome to
; read or run a suggested solution, or to post your own solution or discuss the
; exercise in the comments below.

;; Instead of using java arrays, we can use Clojure transients
(def v (transient [2 3 5]))

(defn my-swap! [tv i j]
  (let [temp (get tv i)]
    (assoc! tv i (get tv j))
    (assoc! tv j temp)))
    
(defn bubble-sort [tv n]
  {:pre [(= (type tv) clojure.lang.PersistentVector$TransientVector)]}
  (loop [i 1 modded false]
    (when (or (< i n) modded)                  ; done when at end and no mods made
      (cond (= i n) (recur 1 false)            ; at end, do another pass
            (< (get tv i) (get tv (dec i)))    ; comparison condition
              (do (my-swap! tv i (dec i))
                  (recur (inc i) true))
            :else (recur (inc i) modded))))    ; keep going
  (persistent! tv))                            ; perist vector before returning

(defn gen-vector [items max]
  (vec (repeatedly items #(rand-int max))))

(defn test-random-vec []
  (let [pre (gen-vector 100 100)
        sorted (vec (sort pre))
        bubbled (bubble-sort (transient pre) 100)]
    (= bubbled sorted)))

(test-random-vec)

;; Better solution (for selection sort)

; Writing with transients first goes against the rule of transients - only use
; them after you have correct algorithm

(import 'java.util.ArrayList)

(defn arr-swap! [#^ArrayList arr i j]
  (let [t (.get arr i)]
    (doto arr
      (.set i (.get arr j))
      (.set j t))))

(defn sel-sort! [arr]
  (let [n (.size arr)]
    (letfn [(move-min! [start-i]
              (loop [i start-i]
                (when (< i n)
                  (when (< (compare (.get arr i) (.get arr start-i)) 0)
                    (arr-swap! arr start-i i))
                  (recur (inc i)))))]
      (doseq [start-i (range (dec n))]
        (move-min! start-i))
      arr)))
