(ns spell)

;; ~20m

(def word-trie
  {\c {\a {\r {\* true
               \t {\* true}}
             \t {\* true}}}
   \d {\o {\g {\* true}}}})

(defn valid-word? [word trie]
  (get (get-in word-trie (seq word)) \*))

(valid-word? "car" word-trie)
(valid-word? "qatar" word-trie)

;; Their solution:
;; Used a-lists, with characters stored in sorted order.
;; A trie is then a pair of a value and an a-list of successors.
;; They also have a make-dict function and read from unix standard
;; word list.
