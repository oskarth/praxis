#!/usr/bin/env boot
; RPN Calculator
; http://programmingpraxis.com/2009/02/19/rpn-calculator/
; Implement an RPN calculator that takes an expression like 19 2.14 + 4.5 2 4.3
; / - * which is usually expressed as (19 + 2.14) * (4.5 - 2 / 4.3) and responds
; with 85.2974. The program should read expressions from standard input and print
; the top of the stack to standard output when a newline is encountered. The
; program should retain the state of the operand stack between expressions.

(defn str->expr
  "Parses string into an expression. Warning: uses read-string, which is unsafe."
  [s]
  (vec (map read-string (clojure.string/split s #" "))))

(defn apply-op-to-ops
  "Apply operator to top two operands on stack."
  [op stack]
  (let [operands [(peek (pop stack)) (peek stack)]]
    (apply (resolve op) operands)))

(defn eval-op
  "Evaluate op and push result to stack, which is returned."
  [op stack]
  (conj (pop (pop stack))
        (apply-op-to-ops op stack)))

(defn eval-rpn
  "Takes a RPN expression and evaluates it. Return top of stack."
  [exp & [stack]]
  (let [[curr rem] [(first exp) (rest exp)]
        stack (or stack [])]
    ; (println "eval-rpn" stack)
    (cond (number? curr) (eval-rpn rem (conj stack curr))
          (symbol? curr) (eval-rpn rem (eval-op curr stack))
          :else (first stack))))

(defn run-test [name test spec & [compare-fn]]
  (let [compare (or compare-fn =)]
    (if (compare test spec)
      (println (str "PASS: " name))
      (println (str "FAIL: " name " " test " != " spec)))))

(defn run-tests []
  (println "[Running tests...]\n")
  (run-test "str->expr" (str->expr "2 3 +")  '[2 3 +])
  (run-test "eval-rpn" (eval-rpn '[2 3 +]) 5)
  (run-test "eval-rpn 2" (eval-rpn '[2 3 + 1 +]) 6)
  (run-test "eval-rpn 3"
    (eval-rpn '[19 2.14 + 4.5 2 4.3 / - *])
              85
              #(< (Math/abs (- %1 %2)) 1))
  (println "\n[Done.]"))

(defn rpn-loop []
  (println (eval-rpn (str->expr (read-line))))
  (rpn-loop))

; (rpn-loop)

; (run-tests)

; Better solution (from the comments)
(defn rpn [stack value]
  (let [[s1 s2] stack
        v (eval (read-string value))]
    (cond
      (number? v) (conj stack v)
      (fn? v) (conj (rest (rest stack)) (v s2 s1))
      :else stack)))

(defn calc [stack line]
  (let [coll (clojure.string/split line #" ")
        newstack (reduce rpn stack coll)]
    (println (first newstack))
    newstack))

(defn rpn-repl []
  (loop [stack '()]
    (recur (calc stack (read-line)))))

(rpn-repl)
