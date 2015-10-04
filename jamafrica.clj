; Google Code Jam Qualification Round Africa 2010
; http://programmingpraxis.com/2011/02/15/google-code-jam-qualification-round-africa-2010/
;
; Store Credit: You receive a credit C at a local store and would like to buy
; two items. You first walk through the store and create a list L of all
; available items. From this list you would like to buy two items that add up to
; the entire value of the credit. The solution you provide will consist of the
; two integers indicating the positions of the items in your list (smaller
; number first). For instance, with C=100 and L={5,75,25} the solution is 2,3;
; with C=200 and L={150,24,79,50,88,345,3} the solution is 1,4; and with C=8 and
; L={2,1,9,4,4,56,90,3} the solution is 4,5.

; What if it doesn't add up? Assume it does.
; Since we are buying two items we can just keep track of the complement.

(defn get-two-items
  "Returns the index of two items in lst that add up to credit."
  [credit lst]
  (loop [seen {} n 0]
    (if (= (count lst) n) false
      (let [curr (get lst n) complement (- credit curr)]
        (if (contains? seen complement)
              (vec (sort [(inc n) (inc (get seen complement))]))
            (recur (assoc seen curr n) (inc n)))))))

(and
  (= (get-two-items 100 [5 75 25]) [2 3])
  (= (get-two-items 200 [150,24,79,50,88,345,3]) [1 4])
  (= (get-two-items 8 [2,1,9,4,4,56,90,3]) [4 5]))
