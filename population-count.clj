; Population Count
; http://programmingpraxis.com/2011/01/28/population-count/
; 
; The population count of a bitstring is the number of set bits (1-bits) in the
; string. For instance, the population count of the number 23, which is
; represented in binary as 10111_2, is 4. The population count is used in
; cryptography and error-correcting codes, among other topics in computer science;
; some people use it as an interview question. The population count is also known
; as Hamming weight.
; 
; Your task is to write a function that determines the population count of a
; number representing a bitstring; you should concern yourself with the efficiency
; of your solution. When you are finished, you are welcome to read or run a
; suggested solution, or to post your own solution or discuss the exercise in the
; comments below.

; Not a good solution, works up to 1e8
(defn population-count [num]
  (loop [s (Long/toBinaryString num) n 0]
    (cond (= (first s) \1) (recur (rest s) (inc n))
          (= (first s) \0) (recur (rest s) n)
          :else n)))
