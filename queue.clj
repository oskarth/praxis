; Queue
; http://programmingpraxis.com/2013/11/01/queues/
; One of the basic data structures in computer science is the queue, in which
; items are inserted into the data structure and retrieved in the order in which
; they were inserted; the standard operations on a queue are enqueue, dequeue, and
; isEmpty.

(require '[clojure.test :refer [deftest is run-tests]])

; "Functional" queue.
;----------------------------------------

; Need this to ensure conj inserts in the right place
; TODO: More generic solution, define protocol and then implement for () and []?
#_(defn new-queue [] [])

#_(defn enqueue [queue x]
  (conj queue x))

; XXX: Interface for this in a functional queue?
#_(defn dequeue [queue]
  [(first queue) (vec (rest queue))])

; Tautological...
#_(defn isempty? [queue]
  (empty? queue))


; Protocols solution.
;----------------------------------------
; TODO: How to get docstring for dequeue?
#_(defprotocol Queue
  "Queue abstraction."
  (enqueue [queue x])
  (dequeue [queue] "Returns a vector pair of first item and new queue.")
  (isempty? [queue]))

#_(extend-type clojure.lang.IPersistentVector
  Queue
  (enqueue [queue x]
    (conj queue x))
  (dequeue [queue]
    [(first queue) (vec (rest queue))])
  (isempty? [queue]
    (empty? queue)))

; Stateful queue.
;----------------------------------------
#_(defn new-queue []
  (atom []))

#_(defn isempty? [queue]
  (empty? @queue))

#_(defn enqueue [queue x]
  (swap! queue #(conj % x)))

#_(defn dequeue [queue]
  {:pre [(not (isempty? queue))]}
  (let [x (first @queue)]
    (swap! queue #(vec (rest %)))
    x))

; Tests
;----------------------------------------
#_(deftest queue
  (let [q (new-queue)]
    (is (isempty? q))
    (is (= (dequeue (enqueue q 1)) [1 []]))
    (is (= (dequeue (enqueue q 1)) [1 []]))
    (is (= (dequeue (enqueue (enqueue q 1) 2))) [2 []])))

#_(deftest queue-protcol
  (is (= (dequeue (enqueue (enqueue [3] 1) 2))
         [3 [1 2]])))

#_(deftest queue-stateful
  (let [q (new-queue)]
    (do (enqueue q 1)
        (enqueue q 2)
        (is (= (dequeue q) 1))
        (is (= (dequeue q) 2))
        (is (thrown? AssertionError (dequeue q))))))

(deftest queue-clj
  (let [q clojure.lang.PersistentQueue/EMPTY]
    (is (empty? q) true)
    (is (= (type (conj q 1)) clojure.lang.PersistentQueue))
    (is (= (peek (conj (conj q 1) 2)) 1))
    (is (= (peek (pop (conj (conj (conj q 1) 2) 3))) 2))
    (is (= (-> q (conj 1) (conj 2) (conj 3) pop peek) 2))))

(run-tests)
