(ns loan)

;; 1305

;; 3 year car loan

;; 10k *1.07 three times, but we

(defn table-row [period start end]
  {:period period
   :start-balance start
   :end-balance end})

(defn amort [initial-balance interest term-months monthly-pay]
  (loop [period 0
         time term-months
         balance initial-balance
         table []]
    (let [new-balance (* (+ 1 interest) (- balance (* 12 monthly-pay)))]
      (if (< time 12) table
          (recur (inc period)
                 (- time 12)
                 new-balance
                 (conj table (table-row period balance new-balance)))))))

;; eg with 10k, you pay X, then your new principal is 10k-x
;; something liek this? don't really grok formt desired. DP to findit?

;; Output format: N PRIN INT BALANCE, all the way to zero
;; Actually I don't understand the output, principal payment? Why go up? Doesn't make sense. Look at again when better internet access.

(map #(clojure.pprint/pprint (amort 10000 0.07 (* 3 12) %))
     [100 200 300])
