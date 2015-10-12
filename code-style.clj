; Two spaces to indent bodies of forms that have body params (& body).

(when something
  (something-else))

(with-out-str
  (println "Hello, ")
  (println "World"))


; Vertically align function/macro arguments spanning multiple lines
(filter even?
        (range 1 10))

; Single indentation for function/macro args when no args on the same line.

(filter
 even?
 (range 1 10))

(or
 ala
 bala
 portokala)

; Use empty lines between top-level forms.

(def x ...)

(defn foo ...)

; Unless it's a grouping of related defs.

(def min-rows 10)
(def max-rows 20)


; Do not place blank lines in the middle of a fn or macro def. Exception for
pairwise constructs in let/cond.

; Prefer :require :as over :require :refer

; Avoid fns longer than 10 LOC.

; Avoid param lists with more than 3-4 pos parameters.

; Idiomatic variable names:

 Follow clojure.core's example for idiomatic names like pred and coll. [link]

 in functions:
 f, g, h - function input
 n - integer input usually a size
 index, i - integer index
 x, y - numbers
 xs - sequence
 m - map
 s - string input
 re - regular expression
 coll - a collection
 pred - a predicate closure
 & more - variadic input
 xf - xform, a transducer
 in macros:
 expr - an expression
 body - a macro body
 binding - a macro binding vector


; Writing heading comments with a t least four semicolons.
; Write top level comments with three semicolons
; Write comments on a particular fragment of code before that fragment, and
aligned with two semicolons.
; Write margin comments with one semicolon
; ALways have at least one line between semicolo n and text following
; Comments longer than a word begin with Capital letter and use punctuation.

; Comment nnotations example

(defn some-fun
  []
  ;; FIXME: This as crashed occasionally since v1.2.3. It may
  ;;        be related to the BarBazUtil upgrade.

; TODO to note missing features or functuonality added later date
; FIXME broken code needs fixing
; OPTIMIZE slow/inefficient
; HACK Code smell
; REVIEW to confirm working as intentional etc
