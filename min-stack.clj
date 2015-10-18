;; This solves the problem of getting O(1) access, but it doesn't define a new
;; data type.

#_(defn minstack [] {:vals [] :mins []})

#_(defn ms-push [val ms]
  {:vals (conj (:vals ms) val)
   :mins (conj (:mins ms) (if (ms-min ms)
                              (min val (ms-min ms))
                              val))})

#_(defn ms-pop [ms]
  {:vals (pop (:vals ms))
   :mins (pop (:mins ms))})

#_(defn ms-peek [ms] (peek (:vals ms)))

#_(defn ms-min [ms] (peek (:mins ms)))

;; Testing

#_(def ms (minstack))
#_(def ms (ms-push 1 ms))
#_(def ms (ms-push 10 ms))
#_(def ms (ms-push 5 ms))
#_(def ms (ms-push 2 ms))
#_(prn (ms-peek ms))
#_(def ms (ms-pop ms))
#_(ms-peek ms)

;; Better solution from kawas at PP, version 1.
;; What I like about this is that it uses destructuring to make structure more
;; evident, requiring less orchestration. It also doesn't use peek/pop builtins.

#_(defn empty' [] (vector '() '()))

#_(defn min' [[_ mins]] (first mins))
#_(defn peek' [[stack _]] (first stack))

#_(defn push' [[stack mins] x]
  (vector (conj stack x)
          (if (and (seq mins) (> x (first mins)) mins)
            (conj mins x))))

#_(defn pop' [[stack mins]]
  (vector (rest stack)
          (if (not= (first stack) (first mins)) mins
            (rest mins))))

;; Version 2.
;; This is nice because protocol makes the problem more evident.

#_(defprotocol IMinStack
  (min' [this])
  (peek' [this])
  (push' [this e])
  (pop' [this]))

#_(defrecord MinStack [_stack _mins]
  IMinStack
    (min' [this] (first _mins))
    (peek' [this] (first _stack))

    (push' [this e]
      (MinStack.
        (conj _stack e)
        (if (and (seq _mins) (> e (first _mins))) _mins
            (conj _mins e))))
    
    (pop' [this]
      (MinStack.
        (rest _stack)
        (if (not= (first _stack) (first _mins)) _mins
            (rest _mins)))))

#_(defn empty' [] (MinStack. '() '()))

;; This is the cleanest solution, imo
;; We could also replace first fn calls with peek to make it
;; collection-independent. The only thing I don't like is that we can't say
;; "when pop is called on this type, do this". Interface, implementation and
;; constructor.

(defprotocol IMinStack
  (min'  [this])
  (peek' [this])
  (push' [this e])
  (pop'  [this]))

(defrecord MinStack [stack mins]
  IMinStack
    (min'  [this]   (first mins))
    (peek' [this]   (first stack))
    (push' [this e] (MinStack. (conj stack e) (conj mins e)))
    (pop'  [this]   (MinStack. (rest stack) (rest mins))))

(defn minstack [] (->MinStack '() '()))

;; Testing
(minstack)
(min' (minstack))
(peek' (minstack))
(pop' (minstack))
(push' (minstack) 5)
(push' (push' (minstack) 5) 3)
(peek' (push' (push' (minstack) 5) 3))
(push' (push' (minstack) 5) 7)
(peek' (push' (push' (minstack) 5) 7))
(pop' (push' (push' (minstack) 5) 3))
(pop' (push' (push' (minstack) 5) 7))
(peek' (pop' (push' (push' (minstack) 5) 7)))
