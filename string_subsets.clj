(defn string-subset?
  "Is a a subset of string b?"
  [a b]
  (not (some #((complement (set b)) %) (set a))))

;; In logical terms:
;; A string a is a subset of a string if, for all unique characters in a
;; there does not exist some character that is in a that is not in b.

(string-subset? "foo" "bar")
(string-subset? "foo" "f")
(string-subset? "foo" "foobar")

;; Alternative in Clojure
;; (every? #(<= (count (filter #{%} s1)) (count (filter #{%} s2))) s1))

;; OOPS, I didn't read question properly. Turns out counts matter. Back to drawing board.
