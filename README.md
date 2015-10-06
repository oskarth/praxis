# Practice journal

*September 28, 2015. RPN Calculator in Clojure. Took too long, several attempts
and on the order of three hours. Code is too verbose and error-prone. Required
googling multiple times, but never looked up solution. [UPDATE: Better solution
notes - loop recur, fewer fns with clear purpose, local destructuring, fn?
predicate, use lists.]*

*September 29, 2015. Yegge's phone screen questions in Clojure. Took too long,
~2h, and too verbose. Required a bit of googling. OK error handling. [UPDATE:
Better solution - use reduce, multi-arity, dotimes; fib bottom-up linear.]*

*September 30, 2015. Sieve of Eratosthenes in Clojure. OK but getting stack
overflow for n > ~1e5 (need ~1e7), despite using loop-recur. After googling and
looking up answers it seems as if one problem might be with me using multiply
rather than addition, but don't know why that would impact the stack. ~1h time.*

*September 30, 2015. TTT API in Clojure. Failed this task in a job interview.
The main issue was that I had no tests, which lead to error-prone code. I was
also rusty with Clojure, which meant I didn't finish in time and made a lot of
stupid errors. This time testing and robustness went a lot better and I
finished, ish (no AI), but it took me closer to ~3h. A lot of this was spent on
setup tasks that I'll get better at with practice, though. Main thing missing
here is execution speed, and possibly an AI.*

*October 1, 2015. Bingo time in Clojure. Took ~2-3h, which is, again, too much.
Need to get better at manipulating collection, defining and testing interfaces.
OK code, but far from optimal for problem. Discrepency in one answer; unclear
why.*

*October 2, 2015. Binary search in Clojure. Only had an hour late at night -
keep doing in the morning for better concentration. Not very pretty and most
likely buggy, more testing needed. [UPDATE: Better solution - test edges case,
use lo/hi index with invariant thattarget num between those,; each recur inc lo
or dec hi, so always progress => base case (< hi lo)]*

*October 3, 2015. Queue in Clojure. Implemented it in three ways: "functional",
protocol extend vector, and stateful (atom) in ~1h. Nice to use protocols, but
not sure about standalone utility - basics already builtin. Another common way
is to have two lists, but doesn't make sense with Clojure's data structures I
think. Only thing to consider is that lists conj at head and vectors at tail.
[UPDATE: Use clojure.lang.PersistentQueue/EMPTY with peek/conj/pop. No literal
syntax or constructor fn.]*

*October 4, 2015. Store credit and reverse words in Clojure. Total ~<1h, both
pretty easy. Reverse word almost cheating with join/split - do this while
writing them yourself, or alternatively do the harder version of this problem.
Started on T9.*

*October 5, 2015. T9 to latin in Clojure. ~2h, quite tricky until I realized I
could just flatten input and treat as characters. Main data structure and a 7
lines, so pretty happy with that.*

*October 6, 2015. Population count in Clojure. ~10m, but not a good solution.
Feels a bit like specialized knowledge, but pretty cool. Would like to know how
to solve it elegantly in Clojure. And how do you measure the performance of
this? How do crypto libs do?*
