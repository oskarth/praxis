#!/usr/bin/env boot

(ns rpn)
;; rpn calculator

;; 1730
;; 18 break small
;; 1830 ok, ~50m but not 100% done

(def op-map {"+" + "-" - "*" * "/" /})

;; XXX: using read-string unsafe
(defn parse-string [s]
  (if-let [op (get op-map s)]
    op
    (read-string s)))

(defn apply-operator [op stack]
  (let [[a b & more] stack]
    (conj more (op b a))))

(defn rpn-eval [exprs stack]
   (if (seq exprs)
     (let [[curr & more] exprs]
       (if (number? curr) (rpn-eval more (conj stack curr))
           (rpn-eval more (apply-operator curr stack))))
     (peek stack)))

(defn rpn [s]
  (let [lines (clojure.string/split s #" ")
        expr (map parse-string lines)]
    (rpn-eval expr '())))

;; (rpn "3 2 -")
;; (rpn "5 1 2 + 4 * + 3 -")

(defn get-input [prompt]
  (println prompt)
  (read-line))

;;retain state?
(defn loop-until-quit []
  (let [input (get-input "")]
    (if (= input "quit") "Quitting"
        (do (println (rpn input))
            (loop-until-quit)))))
