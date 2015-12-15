(ns csv)

;; 2015

(def csv-file (slurp "sample.csv"))

;; Strategy for embedded newlines:
;; Go over all chars, keeping track of matched quotations.
;; If we are matched, go ahead and keep newline.
;; If not matched, replace that newline with something else.
;; Then split on newline, and deal with custom-newline separately.

(defn process-newlines
  ([cs] (process-newlines cs 0 true 1 false))
  ([cs n matched tmp start]
   (cond
     (= n (count cs))
     (clojure.pprint/pprint
      (clojure.string/split cs #"\n"))

     (= (get cs n) \,)
     (process-newlines cs (inc n) true tmp true)

     (and (= (get cs n) \") start)
     (process-newlines cs (inc n) false tmp false)

     (= (get cs n) \")
     (process-newlines cs (inc n) true tmp false)

     (= (get cs n) \newline)
     ;; XXX: n count off now?
     ;; OOPS: only when matched
     (let [new-cs (apply str (concat (take n cs)
                                     "NEWLINE"
                                     (drop (inc n) cs)))]

       ;; XXX: something with n count and new-cs / cs I think.
       (if matched
         (process-newlines new-cs (inc n) matched (inc tmp) false)
         (process-newlines cs (inc n) matched (inc tmp) false)))
       ;;(do (prn (str tmp ": " matched " newline"))

     :else
     (process-newlines cs (inc n) matched tmp false))))

;; 2104
;; 2117, until 2130

;; I don't understand this case wrt ", so I'm going to ignore it for now.
;; NOTE: Deleted it in sample.csv for now.

;; 9,123 456,123"456, 123 456 ,strange numbers

;;#_(prn "\n\nFOUND NEWLINE STARTING AT" n (get cs n))

;; Woah, there's a lot of things here.

;; So am I correct in assuming that a field starting with " must be end-quoted, but this is not the case for inline "?

;; A field starts with , - if we have a " after we are in a quote.

(process-newlines csv-file)

(count (clojure.string/split csv-file #"\n"))

(clojure.pprint/pprint
 (clojure.string/split csv-file #"\n"))
