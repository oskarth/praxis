(ns ints)

(defn file-sum [name]
  (reduce + (map #(Integer. %)
                 (clojure.string/split (slurp name) #"\n"))))

;; This time I did:
;; (clojure.string/split (slurp name) #"\n")

;; Last time I did:
;; (line-seq (clojure.java.io/reader name))

;; (file-sum "/Users/oskarth/praxis/foo")
