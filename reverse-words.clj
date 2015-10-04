; Reverse words
; http://programmingpraxis.com/2011/02/15/google-code-jam-qualification-round-africa-2010/
;
; Reverse Words: Given a list of space separated words, reverse the order of the
; words. Each input string contains L letters and W words. An input string will
; only consist of letters and space characters. There will be exactly one space
; character between each pair of consecutive words. For instance, the reverse of
; “this is a test” is “test a is this”, the reverse of “foobar” is “foobar”, and
; the reverse of “all your base” is “base your all”.

; with join and split it's ridiculously easy, but why wouldn't you use them?
(defn reverse-words [s]
  (clojure.string/join " "
    (reverse (clojure.string/split s #" "))))

(and
  (= (reverse-words "") "")
  (= (reverse-words "foobar") "foobar")
  (= (reverse-words "this is a test") "test a is this")
  (= (reverse-words "all your base") "base your all"))
