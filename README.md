# New start

Different strategy. Focus on learning and only add for retaining once learned.
We don't learn by SRS, only retain what is learned. Take it slow, don't know
multiple yet. Bleed in, one a day ish.

Hypothesis: will remain around 5m solve time. More strict/clear? Cause sometimes
forget. If fail then that counts at Anki, so same failure rate as fast cards?
IDK.

Also doing it in Scheme for copywork effect.

is. What's the bigger lesson of parsing?

I think we want to keep Anki shorts. Anything else is too time-wasting.

Lets turn that into cards!

Actually, the test is whether you can do it in ~5m later, and now we want to
retain with cards.

- Only add to Anki SRS (slow deck) when I can solve in ~5m from scratch
- 48h tolerance in terms of understanding / becoming fluent with a single problem
- Think of it as adding to your repertoire

What do we want to record here? When added relevant things to Anki, perhaps.

1. 160129: RPN Calculator in Scheme (http://programmingpraxis.com/2009/02/19/rpn-calculator/)
2. 160130: SQL Aggregate function in SQL (https://pgexercises.com/questions/aggregates/fachours1a.html)
3. 160131: Word count in C (K&R p. 21)

# OLD: Practice journal

Inspiration:

http://programmingpraxis.com/contents/about/ and
http://senseis.xmp.net/?BenjaminTeuber%2FGuideToBecomeStrong

> Computer programming, like any creative activity, demands constant study and
practice. Vladimir Horowitz practiced the études of Chopin every night, after he
performed a concert, to maintain his skills for the next performance.

## Problem index

Two types of problems: those that are max ~20-30 lines of code and should take
~10-20 minutes to solve, and ~1h problems (or max two), like TTT-api etc, and
are maybe 100-200 LOC. In other words, we have short and long problems.

Atomic practice. metric and do again and again and again. metric, ~10-20m, no
look up documentation. then rehearse every now and then - a sequence.

Thought: maybe 10-20 minutes is too optimistic for some of these. That is more
of in a rehearsed state. They are designed to be solve in around 1h. Maybe just
measure and see how you feel about a given problem?

Hemibellian scale:

1. 5m  (2-10m)
2. 20m (10-30m)
3. 1h  (30-90m)
4. 3h  (90m-4.5h)

Fluent if you don't have look anything up, and it's implemented fluently, as if
it is obvious. I try to err on the side of saying no in this table. Manifold
means that you should implement it in more than two ways, such as
imperative/functional, iterative/recursive, naive/optimized, etc.

| ID  | Name                               | Spec?   | Fluent/TTS | # | Visited |
|-----|------------------------------------|---------|------------|---|---------|
|   1 | RPN                                | Yes     | No, ~1h    | 2 | Nov 14  |
|   2 | Reverse string, functional         | Yes     | No, ~5m    | 2 | Oct 18  |
|   3 | Reverse string, imperative         | Yes     | No, ~5m    | 2 | Nov 09  |
|   4 | Nth fibonacci (naive and linear)   | Yes     | Yes, ~5m   | 2 | Oct 19  |
|   5 | Print multiplication table         | Yes     | No, ~5m    | 3 | Nov 11  |
|   6 | Sum ints from text file            | Yes     | Yes, ~5m   | 2 | Oct 24  |
|   7 | Print odd numbers between 1 and 99 | Yes     | Yes, ~5m   | 2 | Oct 28  |
|   8 | Find the largest value in an array | Yes     | Yes, ~5m   | 2 | Oct 29  |
|   9 | Convert RGB to hexadecimal         | Yes     | Yes, ~5m   | 2 | Oct 30  |
|  10 | Sieve of Eratosthenes.             | No      | No, ~1h    | 1 | Sep 30  |
|  11 | TTT-API (long problem).            | No      | No, ~3h    | 1 | Sep 30  |
|  12 | Bingo.                             | No      | No, ~3h    | 1 | Oct  1  |
|  13 | Binary search                      | Yes     | Yes, ~5m   | 3 | Nov 16  |
|  14 | Queue.                             | No      | No         | 1 | Oct  3  |
|  15 | Store credit.                      | No      | Maybe      | 1 | Oct  4  |
|  16 | Reverse words (naive).             | No      | Maybe      | 1 | Oct  4  |
|  17 | T9 to latin.                       | No      | No         | 1 | Oct  5  |
|  18 | Population count.                  | No      | No         | 1 | Oct  6  |
|  19 | Bubble sort.                       | No      | No         | 1 | Oct 11  |
|  20 | Insertion sort.                    | No      | No         | 1 | Oct 11  |
|  21 | Selection sort.                    | No      | No         | 1 | Oct 11  |
|  22 | REST API.                          | No      | No         | 1 | Oct 13  |
|  23 | Lowest common ancestor             | Yes     | No         | 1 | Oct 15  |
|  24 | Binary Search Tree                 | Yes     | No         | 1 | Oct 17  |
|  25 | Min stack                          | Yes     | No, ~20m   | 1 | Oct 18  |
|  26 | String subset                      | Yes     | No, ~20m   | 1 | Oct 19  |
|  27 | Word break                         | Yes     | No, -      | 1 | Oct 20  |
|  28 | First unique character             | Yes     | No, ~1h    | 1 | Oct 21  |
|  29 | Binary tree traversal              | Yes     | No, -      | 1 | Oct 22  |
|  30 | Median of five                     | Yes     | No, -      | 1 | Oct 23  |
|  31 | Anagram                            | Yes     | No, -      | 1 | Oct 25  |
|  32 | Spell checking                     | Yes     | N/A, ?     | 1 | Oct 26  |
|  33 | String search brute force          | Yes     | No, ~20m   | 1 | Oct 27  |
|  34 | Search in ascending matrix         | Yes     | No, -      | 1 | Oct 31  |
|  35 | Autocomplete                       | Yes     | No, ~3h    | 1 | Nov 03  |
|  36 | rot13                              | Yes     | No, -      | 1 | Nov 04  |
|  37 | First available id                 | Yes     | No, ~20m   | 1 | Nov 05  |
|  38 | String subset                      | Yes     | No, ~5m    | 1 | Nov 06  |
|  39 | Sudoku                             | Yes     | No, -      | 2 | Nov 27  |
|  40 | Word search                        | Yes     | No, ~3h    | 1 | Nov 10  |
|  41 | Head and tail                      | Yes     | Yes, ~5m   | 1 | Nov 13  |
|  42 | Loan amortization                  | Yes     | No,        | 1 | Nov 17  |
|  43 | Flavius Josephus                   | Yes     | No, ~1h    | 1 | Nov 25  |


## Upcoming

- REST API in Clojure with BA
- Dashboard in Clojurescript

Break down small problems, like hosting web-server etc? Just to make it more
mini. Routes or w/e. Don't know.

## Experiment: Parsing Practice

*December 13, 2015. CSV (http://programmingpraxis.com/2009/03/17/comma-separated-values/). 1h effort but noe. Got base case in ~20m, but lots of parsing/string manipulation undone. Realized my tests were a bit of a waste (~20m) because they test an artifact - doing visual diff now instead. Also realized we can think of it as a set of rules, or string manipulations, we do for fields etc.*

*December 15, 2015. CSV again. Spent 1h. Started by dealing with embedded newlines. Got confused by quotation rules but realized it only quotes as a quote if it's in the beginning of a field. Almost got it, but there are a lot of edge cases: a meta lesson I learned is that what I really want is to make a parser state machine.*

*December 20, 2015. CSV again. Spent 1 hour and decided I spent enough time
going for myself, looked up solution and spent a total of 3h. I got that it's a
finite state machine I want, and I managed to get quite close in practice after
some Aha moments. Missed a part that radically simplified state transitions:
mutually recursive functions, which could be provided by letfn in Clojure (how
does this work and how is it different from forward declaration wrt stack space
consumed etc?).*

*December 26, 2015. Beautiful code, Rob Pike's regex parser. Spent 1h
translating the code and think I got it right, or mostly right. Not sure how to
test this with confidence - a lot of paths (generative testing? compare with
builtin regex implementation? what is ideal? good Quicktest candidate, I
think).*

*December 27, 2015. Beautiful code, 1h after looking at soln. They translated
strings into seqs first, since that makes the code more natural in Lisp. Think
that's the right call. Started using PP:s Scheme implementation.*

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

*October 18, 2015. Reverse-string in Clojure and C. ~5m and right, but didn't get
reduce version. Haven't internalized consequence of conjs happening at head of
lists. Also did imperative version in C, but failed - flailed around with
pointers. Very straightforward solution.*

*October 18, 2015. Min Stack in Clojure. ~20m solved, but not happy with
non-datatyped it was. After looking around some I found what feels like The
Right Way to do it using protocol IMinStack and defrecord MinStack, so I'm
happy about that. Very terse. Interface, implementation and constructor.*

*October 19, 2015. String subset in Clojure. ~20m solved. Used loop-recur and
O(nlogn), whereas O(n) is possible. OK but should get O(n) soln too (maybe in
<1h though?)*

*October 19, 2015. Nth fibonacci in Clojure, both naive and linear. Both took
~5m (10m total), so better than expected! Yes, mastered. One problem or keep as
two? Same problem. Integeroverflow before Stackoverflow*

*October 20, 2015. Word break in Clojure. Spent ~1h but failed. Have to look
more closely at solutions but not now. Interesting problem.*

*October 21, 2015. First unique character. ~1h, not super clean and looked up
some ideas.*

*October 22, 2015. Binary tree traversal. Fun! ~1h, but didn't get the
 make-tree bit. Figure out what is meant by this. Get quicker at walk
 fn logic.*

*October 23, 2015. Median of five. ~1h but didn't manage to with
 mutation (and logic). Quite tricky solution, depends on check
 invariants (partial sortings) and then mutating variables a b c d e
 with ifs. Possible it could be pattern matched nicely.*

*October 24, 2015. Multiplication table again. ~30m-1h, way too
 long. Lessons: which collections do I want? Bottom-up, bottom-up,
 bottom-up. Don't mess with headers until later. Addendum: bigger
 point is not using print! Didn't realize it was just adding, since I
 normally eval. Also dotimes better than doseq here.*

*October 24, 2015. File sum from file. ~5m, so counting it as
 mastered. Interesting to do this in other languages too for basic IO?
 Interestingly I did it differently this time: apply vs reduce + and
 line-seq reader vs split on newline.*

*October 25, 2015. Anagrams in Clojure, ~1h. Tried to write permutate but
 failed. Looked up Scheme implementation and need to study more to
 understand. Their solution used clever trick: sign each word in dict
 by sorting it and storing it in a map.*

*October 26, 2015. Spell checking trie in Clojure. ~20m. Feel pretty good with
 my solution, but don't know how to evaluate it. To know if my solution is
 reasonable, we would have to check that it's more space-efficient than a normal
 hash map, and also that it's faster because of fewer lookups. This is a lot of
 extra work, and also not a natural problem for Clojure - the important part
 here is the concept one I think.*

*October 27, 2015. String search brute force in Clojure. ~20-30 min. A
 lot of incidential stuff with editor etc. Seems reasonably
 straightforward, logic-wise though.*

*October 28, 2015. Print odd numbers between 1 and 99 in Clojure,
 ~1m. Very tired and little time so I did this admittely too easy (but
 no such thing?) task. Mastered yes. More interesting in different
 languages though, given that it's mastered.*

*October 29, 2015. Find max in array, Clojure. Same as yesterday, ~2m,
 mastered, very easy.*

*October 30, 2015. RGB to hex in Clojure. Same as yesterday, ~5m. Much
 simpler than last one, had to look up throw though but that's a
 general error thing, so consider mastered.*

*October 31, 2015. Search in ascending matrix in Clojure. Naive version ~20m,
hint at strategy for O(m+n) algo but didn't succeed in implementing in 30m-1h.*

*November 1, 2015. Minor work on string search in Clojure, searching for
multiple in dictionary and displaying words.*

*November 2, 2015. Autocomplete in Clojure. ~1.5h, binary search / substring.
 Still a work in progress.*

*Novemeber 3, 2015. Autocomplete in Clojure, continued. ~1h, figured out a nice
 find first substring using binary search algo. Still WIP, next is to get N
 following substring linearly, should be easy. Update: added top N substring.*

*November 4, 2015. Rot13 in Clojure. Failed in ~1h. Too late/tired, think I
 should have sketched out more on paper. Also didn't manage to get the intrinsic
 rotation from problem. Oh well, live and learn. UPDATE: 20m later I find a
 beautiful solution after just going after the lookup-table idea.*

*November 5, 2015. First available ID in Clojure. ~20-30m, part of interview.
 Went ok but took a bit too long.*

*November 6, 2015. String subsets in Clojure. ~5-10m, but I solved the wrong
 problem - didn't read problem carefully enough, count matters (so not really
 subset). Do again.*

*November 7, 2015. Start on sudoku solver in Clojure, 30m-1h. Mostly read
Norvig, not enough coding.*

*November 8, 2015. Took day off.*

*November 9, 2015. String reverse in C. Solved in 9m but didn't use string
 stdlib. OK but do again in a month or so for fluency.*

*November 10, 2015. Word search in Clojure. Started yesterday, took ~5h in two
tries. Fun! Not mastered, and maybe not optimal strategy, but pretty satisfied
with it.*

*November 11, 2015. Short print multiplication table. 5-10m, forgot about print
 fn again, so will do again in some time.*

*November 12, 2015. Wanted to TTT or RPN, but too tired to make a real effort.*

*November 13, 2015. Very simple head and tail in Clojure, ~1m. No real
 constraints. Perhaps good for new languages?*

*November 14, 2015. RPN in Clojure. ~1h, didn't do full state-keep. Felt fairly simple, but still unsure about best strategy for parsing.*

*November 15, 2015. Travel day.*

*November 16, 2015. Binary search in Clojure. ~5m (9m), felt straightforward and fluent. Good!*

*November 17, 2015. Loan amortization in Clojure. ~20m but not really
 understanding what is asked. Limited internet access - will revisit soon so I can investigate.*

*November 18-23, 2015. Travel / long break; very sleep deprived.*

*November 24, 2015. Flavius Josephus in Clojure. ~1h rough start, not done, still sleep deprived.*

*November 25, 2015. Josephus again, got something working ish ~1h, but not best solution. Re-visit soon.*

*November 26-27, 2015. Sudoku solver. ~1h copywork-style using Joy Of Clojure. Requires more studying.*


## Unix V7

Praxis here? Using Anki and slow deck.

Do one praxis thing and put in slow deck.
