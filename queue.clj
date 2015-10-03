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
; TODO: How to get docstring for dequeue?
(defprotocol Queue
  "Queue abstraction."
  (enqueue [queue x])
  (dequeue [queue] "Returns a vector pair of first item and new queue.")
  (isempty? [queue]))

(extend-type clojure.lang.IPersistentVector
  Queue
  (enqueue [queue x]
    (conj queue x))
  (dequeue [queue]
    [(first queue) (vec (rest queue))])
  (isempty? [queue]
    (empty? queue)))

; TODO: Stateful queue.



#_(deftest queue
  (let [q (new-queue)]
    (is (isempty? q))
    (is (= (dequeue (enqueue q 1)) [1 []]))
    (is (= (dequeue (enqueue q 1)) [1 []]))
    (is (= (dequeue (enqueue (enqueue q 1) 2))) [2 []])))

(deftest queue-protcol
  (is (= (dequeue (enqueue (enqueue [3] 1) 2))
         [3 [1 2]])))
