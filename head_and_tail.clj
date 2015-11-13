(ns filey)


;; 1510, 1 minute but not ideal
;; naive solution: read file and take first and last

(defn first-last-lines [file]
  (let [lines (clojure.string/split (slurp file) #"\n")]
    [(first lines) (last lines)]))

(first-last-lines "foobar")

;; could also use read-line I guess
;; not a great problem because it's not motivated by constraints. Above approach works for most files.

;; random access,
;; could use somthing like this but seems complicated:
;; https://github.com/clojure-cookbook/clojure-cookbook/blob/master/04_local-io/4-11_random-access-files.asciidoc
