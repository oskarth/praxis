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
