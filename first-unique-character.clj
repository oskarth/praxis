;; ~1h

(defn freq-map
  "Takes a string and returns a frequency map of each character."
  [s]
  (loop [chars s freq {}]
    (if (seq chars)
      (let [count (or (get freq (first chars)) 0)]
        (recur (rest chars) (assoc freq (first chars) (inc count))))
      freq)))

(defn first-non-repeating
  "Takes a string and returns first non repeating character."
  [s]
  (let [candidates (filter #(= (val %) 1) (freq-map s))
        match? (fn [c] (some #(= % c) (keys candidates)))]
    (first (filter match? s))))

;; Better solution (that I got freq idea from without reading rest)
;; Less complex. Also, where is the complexity metric code tree?
(defn freq [chars]
  (loop [chars chars freq {}]
    (let [current (first chars)]
      (if (empty? chars) freq
        (recur (rest chars) (assoc freq current (inc (or (freq current) 0)))))))) 

(defn first-unique-char [chars]
  (let [freqs (freq chars)]
    (first (filter #(= (freqs %) 1) chars))))
