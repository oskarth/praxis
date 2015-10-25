(ns anagrams)

;; 1240-1310, 30m trying to figure out perm.
;; Time to google. Ok got scheme version working but I don't fully
;; understand it, and now almost 1h.

(def dict #{"post" "stop" "tops" "hello"})


;; base case: (permutations "a") => a
(defn permutations [coll]
  (if (seq (rest coll))
    (concat
     (concat (permutations (list (first coll)))
             (rest coll))
     (concat (permutations (list (rest coll)))
                    (first coll)))
    (list (first coll))))

(permutations '(1))
(permutations '(1 2))


(defn anagram [word dict]
  (get dict word))

(comment
  (anagram "post" dict) ;; => '("post", "stop", "tops")

)

;;;; This is from rosetta code a ala scheme

(defn insert
  "Insert element at position n in coll."
  [coll pos elem]
  (if (= pos 0)
    (conj coll elem)
    (conj (insert (rest coll) (dec pos) elem)
          (first coll))))

;; I don't understand this algorithm yet.
(defn permute
  "Return a list of permutions of the elements in a collection."
  [coll]
  (if (empty? coll)
    '(())
    (apply concat
           (map (fn [p]
                  (map (fn [n]
                         (insert p n (first coll)))
                       (range (inc (count p)))))
                (permute (rest coll))))))

(def c '(1)) ;;coll
;;(permute '(1)) ;; => 1
(apply concat
       (map (fn [p]
              (map (fn [n]
                     (insert p n (first c)))
                   (range (inc (count p)))))
            '(())
            ))



;; Looking at solution:
;; OH I get it, clever other solution. Rea each word in dict, sort it,
;; and "sign" it with its "sorted hash" in a hash table. Then easy look
;; up. Clever! Use usr dict words too. (/usr/share/dict/words on mac)

;; Easy to check largest anagram class then too.
