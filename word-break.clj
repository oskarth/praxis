;; 643
;; almost 1h

;; "loopycatso" => loopy cat so

;; fencepost problem?

(def en-dict #{"apple" "pie" "loop" "loopy" "cat" "so"})

(defn first-words
  "Returns list of words in dictionary starting with the first character of s."
  [s dict]
  (remove nil?
    (map (fn [n] (get dict (apply str (take n s))))
         (range 1 (inc (count s))))))

(defn break-word [s dict]
  (let [nchars (map count (first-words s en-dict))]
    (map (fn [n] (first-words (apply str (drop n s)) en-dict))
         nchars)))

;; How to check for more than two words?
;; We call first-words on rest of string

;; always check str version
;; "apple" in en-dict?
;; (get en-dict "apple") is truthy

;; for each combination: "a" "ap" ... "apple"..."applepie" check if it is dict
;; if it is, like "apple" we check same for "pie".

;; Problem: "loopycat" both loop and loopy mgiht be in dict, but cat is and ycat
;; isn't

;;(first-words "applepie" en-dict) ;; => '("apple")
;;(first-words "loopycat" en-dict) ;; => '("loop", "loopy")


;;; Fresh start

;; Fencepost problem from combinatorics?

;; "loopycatso" has 10 chars
;; a word has at least one character

;; nth fencepost?
(def en-dict #{"apple" "pie" "loop" "loopy" "cat" "so"})

;; Bah, confused
;; Idea is to get the fenceposts, but need both sides for it.
;; And better logic. But 1h is up.

#_(defn fence-posts
  ([s dict] (fence-posts s dict 1 '()))
  ([s dict cursor posts]
    (let [left (- (count s) cursor)
          substr (apply str (take cursor (drop left s)))]
      (if (= left 0) :done
        (get dict substr) (fence-posts s dict cursor (conj posts cursor))))))
     
    
