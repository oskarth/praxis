; Steve Yegge’s Phone-Screen Coding Exercises
; http://programmingpraxis.com/2009/06/30/steve-yegges-phone-screen-coding-exercises/
; 1. Write a function to reverse a string.
; 2. Write a function to compute the Nth fibonacci number.
; 3. Print out the grade-school multiplication table up to 12 x 12.
; 4. Write a function that sums up integers from a text file, one per line.
; 5. Write a function to print the odd numbers from 1 to 99.
; 6. Find the largest int value in an int array.
; 7. Format an RGB value (three 1-byte numbers) as a 6-digit hexadecimal string.

; 1. Reverse string
; Could use reduce I think.
(defn reverse-string [s]
  (loop [rem s rev ""]
    (if (empty? rem)
        rev
        (recur (rest rem) (str (first rem) rev)))))

; Better solution
(defn my-reverse [coll]
  (reduce conj () coll))

(defn reverse-string-sol [s]
  (apply str (my-reverse s)))

; 2. Fibonacci numbers
; Naive version. Aaround fib 35 it takes a lot of time.
; What's the smallest solution that is better? loop-recur? memo? DP?
(defn fib1 [n]
  (cond (= n 0) 0
        (= n 1) 1
        :else (+ (fib1 (- n 1))
                 (fib1 (- n 2)))))

; Can calculate fib 40, but still too slow. Go more aggressive but later.
(def fib-memo (atom {0 0, 1 1}))
(defn fib2 [n]
  (if (contains? @fib-memo n)
      (do
        (@fib-memo n))
      (let [res (+ (fib2 (- n 1)) (fib2 (- n 2)))]
        (swap! fib-memo #(assoc % n res))
        res)))

; Better solution, linear time
(defn fib
  ([n]
    (fib n 1 0))
  ([n f1 f2]
    (if (zero? n) f2
          (fib (- n 1) (+ f1 f2) f1))))

; 3. Print out the grade-school multiplication table up to 12 x 12.
; Lots of code duplication - better fn / coll manipulation?
(defn header-str [n]
  (str (format "%3s" "*")
       " |"
       (apply str (map #(str " " (format "%3d" %))
                       (range 1 (+ n 1))))))

(defn row-str [row n]
  (str (format "%3d" row)
        " |"
        (apply str (map #(str " " (format "%3d" %))
                        (for [col (range 1 (+ n 1))] (* row col))))))

(defn print-mul [n]
  (println (header-str n))
  (println (apply str (repeat (+ (* 4 (+ n 1)) 1) "-")))
  (doseq [row (range 1 (+ n 1))]
    (println (row-str row n))))

; Better solution
(defn times-table [n]
  (dotimes [x n]
    (dotimes [y n]
      (print (format "%3d " (* (inc x) (inc y)))))
    (println)))

; 4. Write a function that sums up integers from a text file, one per line.
; How to format this type of code better?
(defn file-sum [name]
  (apply +
         (map #(Integer. %)
              (line-seq (clojure.java.io/reader name)))))

; 5. Write a function to print the odd numbers from 1 to 99.
(defn print-odd []
  (doseq [n (range 1 100) :when (odd? n)]
    (println n)))

; 6. Find the largest int value in an int array.
; (Assuming we can't use built-in max)
(defn max-int [v]
  (reduce (fn [x y] (if (> x y) x y))
          v))

; pre can't take values of a macro - how can we have multiple pre conditions?
;(and (pos? (min (x y z))) (< (max x y z) 256))

; 7. Format an RGB value (three 1-byte numbers) as a 6-digit hexadecimal string.
(defn rgb->hex [x y z]
  {:pre (< (max x y z) 256)}
  (apply str (map str (for [n [x y z]] (format "%h" n)))))
