(ns rgb)

;; Format an RGB value (three 1-byte numbers) as a 6-digit hexadecimal string.

;; 2238-2244
;; Best would be if we could have macros in pre, but this works too.

(defn rgb->hexadecimal [x y z]
  (if (and (<= (max x y z) 255) (>= (min x y z) 0))
    (format "%h%h%h" x y z)
    (throw (Exception. "Invalid RGB number."))))


(rgb->hexadecimal 255 255 255)
(rgb->hexadecimal 255 255 256)

;; Old implementation, for reference (!)
(defn rgb->hex [x y z]
  {:pre (< (max x y z) 256)}
  (apply str (map str (for [n [x y z]] (format "%h" n)))))

