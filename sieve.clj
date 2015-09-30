; Sieve of Eratosthenes
; http://programmingpraxis.com/2009/02/19/sieve-of-eratosthenes/
; Write a function that takes a single argument n and returns a list of prime
; numbers less than or equal to n using the optimized sieving algorithm described
; above. Apply the function to the argument 15485863 and count the number of
; primes returned.

; Doesn't work for n> ~1e5 (need 1e7) due to stack overflow.
; Why? As far as I understand it, this should not take up stack space
#_(defn prime-sieve [n]
  (loop [coll (vec (range 2 (+ n 1))), acc []]
    (if (seq coll)
      (recur (remove #(= (rem % (first coll)) 0) coll)
            (conj acc (first coll)))
      acc)))

; Better solution - cgrande's version without optimizations
; http://clj-me.cgrand.net/2009/07/30/everybody-loves-the-sieve-of-eratosthenes/
; Need to study this more.
(defn primes2 [max]
  (let [enqueue (fn [sieve n factor]
                  (let [m (+ n factor)]
                    (if (sieve m)
                      (recur sieve m factor)
                      (assoc sieve m factor))))
        next-sieve (fn [sieve candidate]
                     (if-let [factor (sieve candidate)]
                       (-> sieve
                         (dissoc candidate)
                         (enqueue candidate factor))
                       (enqueue sieve candidate candidate)))]
    (vals (reduce next-sieve {} (range 2 (+ max 1))))))


; Another solution from comments (but it uses additional optimizations)

; we work only with odd numbers till N
(defn odds [n] (vec (range 3 (inc n) 2)))

; it's easy to calculate index of a value in our vector of odds
(defn odds-idx [v] (/ (- v 3) 2))

; just sieve the vector from start=(index of v²)
(defn sieve [max-idx nums v]
  (let [start (odds-idx (* v v))]
    (reduce #(assoc %1 %2 nil) nums (range start max-idx v))))

; get primes equal or less than N
(defn primes [n]
  (let [max-i (Math/floor (odds-idx n))]
    (loop [nums (odds n) i 0]
      (if-let [v (nums i)]
        (if (> (* v v) n)
          (cons 2 (remove nil? nums))
          (recur (sieve max-i nums v) (inc i)))
        (recur nums (inc i))))))

; (time (count (primes 15485863)))
