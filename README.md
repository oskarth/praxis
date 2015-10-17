# Practice journal

## Problem index

Two types of problems: those that are max ~20-30 lines of code and should take
~10-20 minutes to solve, and ~1h problems (or max two), like TTT-api etc, and are
maybe 100-200 LOC. In other words, we have short and long problems.

atomic practice. metric and do again and again and again. metric, ~10-20m, no look
up documentation. then rehearse every now and then - a sequence.

Fluent if you don't have look anything up, and it's implemented fluently, as if
it is obvious. I try to err on the side of saying no in this table. Manifold
means that you should implement it in more than two ways, such as
imperative/functional, iterative/recursive, naive/optimized, etc.

| ID | Name                                | Specced? | Fluent   | # | Visited |
|----|-------------------------------------|----------|----------|---|---------|
|  1 | RPN calculator                      | Yes      | No, ~3h  | 1 | Sep 28  |
|  2 | Reverse string (manifold)           | Yes      | No       | 1 | Sep 29  |
|  3 | Nth Fibonacci (manifold)            | Yes      | No       | 1 | Sep 29  |
|  4 | Print mul table up to 12x12.        | No       | No       | 1 | Sep 29  |
|  5 | Sum up ints from a text file.       | No       | No       | 1 | Sep 29  |
|  6 | Print odd numbers between 1 and 99. | No       | No       | 1 | Sep 29  |
|  7 | Find the largest value in an array. | No       | No       | 1 | Sep 29  |
|  8 | Convert RGB to hexadecimal string.  | No       | No       | 1 | Sep 29  |
|  9 | Sieve of Eratosthenes.              | No       | No, ~1h  | 1 | Sep 30  |
| 10 | TTT-API (long problem).             | No       | No, ~3h  | 1 | Sep 30  |
| 11 | Bingo.                              | No       | No, ~3h  | 1 | Oct  1  |
| 12 | Binary search                       | Yes      | No       | 2 | Oct 16  |
| 13 | Queue.                              | No       | No       | 1 | Oct  3  |
| 14 | Store credit.                       | No       | Maybe    | 1 | Oct  4  |
| 15 | Reverse words (naive).              | No       | Maybe    | 1 | Oct  4  |
| 16 | T9 to latin.                        | No       | No       | 1 | Oct  5  |
| 17 | Population count.                   | No       | No       | 1 | Oct  6  |
| 18 | Bubble sort.                        | No       | No       | 1 | Oct 11  |
| 19 | Insertion sort.                     | No       | No       | 1 | Oct 11  |
| 20 | Selection sort.                     | No       | No       | 1 | Oct 11  |
| 21 | REST API.                           | No       | No       | 1 | Oct 13  |
| 22 | Lowest common ancestor              | Yes      | No       | 1 | Oct 15  |

## Upcoming

- REST API in Clojure with BA
- Dashboard in Clojurescript

## Log

*September 28, 2015. RPN Calculator in Clojure. Took too long, several attempts
w
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

*October 7, 2015. Quadratic sorts in Clojure. Started with bubble sort, took
~1h. Tricky part is doing it in-place. Started by looking into using Java
arrays, but seems more idiomatic to use transients. Curious to see how others do
this in numerically intensive environments.*

*October 8 to October 11, 2015. Initially intended to make a dashboard in
Clojurescript, but found myself making something else that doesn't belong in
this repo. ~10h spent on it; a lot of flailing around with Github, CORS,
Firebase etc. I moved it to Gitcomm repo.*

*October 12, 2015. Quadratic sorts in C. Decided in-place algorithms make a lot
more sense to do in a language like C, rather than mucking around with type
aliases and transients in Clojure. Bubble sort in ~30m. Speaks to non-fluency in
C for such a simple problem - good diagnostics! ~10m for selection sort, so
that's pretty good. ~10m for insertion sort too, but had to look up algo pseudo
to grok.*

*October 13, 2015. Stroke REST API in Clojure. ~3h, not a lot of progress. Got
basic server, routes and json response up, basic auth work in progress (figuring
out how to test/simulate it). A lot of mocking around with tools. Will do more
on this soon. Want to get to a decent state in 1-3h. Will specify it more soon.*

*October 14, 2015. Lowest common ancestor in Clojure. Just ~1h this morning. Had
problems with expressing tree traversal in a sane way, so eventually looked it
up. Didn't tackle real problem yet.*

*October 15, 2015. Lowest common ancestor in Clojure. ~20m. Wrong track
yesterday. It's a BST, and with key insight we only need a small fn to check
invariant m < val < n. Generalized tree traversal is a different problem.*

*October 16, 2015. Binary search in Clojure again. ~30m and correct solution,
but code logic could be a bit more explicit. Also get at invariant quicker,
should be a 5m problem.*

*October 17, 2015. Binary Search Tree in Clojure. Spent a few hours looking
for right way to abstract this using protocols and such, but no luck yet.
Later I decided to go back to just simplest thing that worked, a map and
constructor. Still need to fix the deletion and rotations though.*
