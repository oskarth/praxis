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
