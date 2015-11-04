(ns rot13)

;; 2120-2220

;; Driving me nuts! How can this be so difficult?

;; (mod 15 13) => 2
;; so +2! that's it. How can that be difficult?

(defn lowercase? [code] (<= 97 code 122))
(defn uppercase? [code] (<= 65 code 90))

;; code is int
(defn get-rot13-code [code]
  (cond (lowercase? code) (+ 97 (+ (- code 97) 13))
        (uppercase? code) (+ 65 (+ (- code 65) 13))
        :else             code))

(defn get-rot13-char [c]
  (char (get-rot13-code (int c))))

;; ok wtf am I doing. pencil and paper here, and don't be tired like this.
;; the rotation is intrinsic, why doesn't it show up in your code?
;; could maybe do this lazily? IDK.
(map get-rot13-char "The")

(int \T)
(+ 97 (+ (- (int \f) 97) 13))

(range 26)

(defn rot13-ascii [c]
  (let [code (int c)
        uppercase? (<= 65 code 90)
        lower (int (if uppercase? (- code 32) code))
        plus? (< (- lower 97) 13)]
    (if plus?
      (char (+ code 13))
      (char (- code 13)))))

(defn rot13 [word]
  (apply str (map rot13-ascii word)))

(rot13 "The butler did it!")
(int \T) ;; 84
(mod (+ 84 13) 65)

(rot13 "The") ;; Gak, should be Gur
(int \space)


;; mod?
(- 97 65)
(mod 102 13)
;; 97 to 122
;; 65 to 90
(int \Z)
(- 122 13)

;; 97 to 122
(- 122 97)
;; 65
(int \z)
(char (+ (int \s) 2))
