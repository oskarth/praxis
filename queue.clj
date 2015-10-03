; Queue
; http://programmingpraxis.com/2013/11/01/queues/
; One of the basic data structures in computer science is the queue, in which
; items are inserted into the data structure and retrieved in the order in which
; they were inserted; the standard operations on a queue are enqueue, dequeue, and
; isEmpty.

(require '[clojure.test :refer [deftest is run-tests]])

; "Functional" queue.

; Need this to ensure conj inserts in the right place
; TODO: More generic solution, define protocol and then implement for () and []?
(defn new-queue [] [])

(defn enqueue [queue x]
  (conj queue x))

; XXX: Interface for this in a functional queue?
(defn dequeue [queue]
  [(first queue) (vec (rest queue))])

; Tautological...
(defn isempty? [queue]
  (empty? queue))


; TODO: Stateful queue.
; TODO: Protocoly.



(deftest queue
  (let [q (new-queue)]
    (is (isempty? q))
    (is (= (dequeue (enqueue q 1)) [1 []]))
    (is (= (dequeue (enqueue q 1)) [1 []]))
    (is (= (dequeue (enqueue (enqueue q 1) 2))) [2 []])))
