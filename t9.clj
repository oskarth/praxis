; T9 spelling
; http://programmingpraxis.com/2011/02/15/google-code-jam-qualification-round-africa-2010/
; 
; T9 Spelling: The Latin alphabet contains 26 characters and telephones only have
; ten digits on the keypad. We would like to make it easier to write a message to
; your friend using a sequence of keypresses to indicate the desired characters.
; The letters are mapped onto the digits as 2=ABC, 3=DEF, 4=GHI, 5=JKL, 6=MNO,
; 7=PQRS, 8=TUV, 9=WXYZ. To insert the character B for instance, the program would
; press 22. In order to insert two characters in sequence from the same key, the
; user must pause before pressing the key a second time. The space character
; should be printed to indicate a pause. For example “2 2” indicates AA whereas
; “22” indicates B. Each message will consist of only lowercase characters a-z and
; space characters. Pressing zero emits a space. For instance, the message “hi” is
; encoded as “44 444”, “yes” is encoded as “999337777”, “foo  bar” (note two
; spaces) is encoded as “333666 6660022 2777”, and “hello world” is encoded as
; “4433555 555666096667775553”.

(def t9-alphabet
  {\0 [\space]
   \2 [\a \b \c]
   \3 [\d \e \f]
   \4 [\g \h \i]
   \5 [\j \k \l]
   \6 [\m \n \o]
   \7 [\p \q \r \s]
   \8 [\t \u \v]
   \9 [\w \x \y \z]})

(defn t9->latin [x]
  (letfn [(lookup [char n] (get (get t9-alphabet char) n))]
    (loop [xs (flatten (map list x)) wip nil wipn 0 res []]
      (let [curr (first xs) new-xs (rest xs)]
        (cond (empty? xs) (apply str (conj res (lookup wip wipn)))
              (and (= curr wip) (not= curr \0)) (recur new-xs wip (inc wipn) res)
              :else (recur new-xs curr 0 (conj res (lookup wip wipn))))))))
(and
  (= (t9->latin "") "")
  (= (t9->latin " ") "")
  (= (t9->latin "22") "b")
  (= (t9->latin "2 2") "aa")
  (= (t9->latin "44 444") "hi")
  (= (t9->latin "999337777") "yes")
  (= (t9->latin "333666 6660022 2777") "foo  bar")
  (= (t9->latin "4433555 555666096667775553") "hello world"))
