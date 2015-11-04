(ns rot13)

(defn rot13 [word]
  (let [lowercase-alphabet (map char (range 97 123))
        uppercase-alphabet (map char (range 65 91))
        [a b] (partition 13 lowercase-alphabet)
        [c d] (partition 13 uppercase-alphabet)
        table (zipmap (concat a b c d) (concat b a d c))
        cs (map #(get table % %) word)]
    (apply str cs)))

(rot13 "Cebtenzzvat Cenkvf vf sha!")
(rot13 (rot13 "Cebtenzzvat Cenkvf vf sha!"))
