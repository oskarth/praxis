(ns wordsearch)

(defn- positions [board]
  (for [r (range (count board))
        c (range (count (get board 0)))]
    [r c]))

(defn- gen-char-map [board]
  (apply (partial merge-with concat)
         (for [pos (positions board)]
           {(get-in board pos) [pos]})))

(def board (atom ["FYYHNRD" "RLJCINU" "AAWAAHR" "NTKLPNE" "CILFSAP" "EOGOTPN" "HPOLAND"]))
(def char-map (atom (gen-char-map @board)))
(def words (atom ["ITALY" "HOLLAND" "POLAND" "SPAIN" "FRANCE" "JAPAN" "TOGO" "PERU"]))

(defn pos->char [pos]
  (get-in @board pos))

(defn char->pos [c]
  (@char-map c))

(def route-fn-map
  {:left       (fn [[r c]] [r (dec c)])
   :right      (fn [[r c]] [r (inc c)])
   :up         (fn [[r c]] [(dec r) c])
   :down       (fn [[r c]] [(inc r) c])
   :up-left    (fn [[r c]] [(dec r) (dec c)])
   :up-right   (fn [[r c]] [(dec r) (inc c)])
   :down-left  (fn [[r c]] [(inc r) (dec c)])
   :down-right (fn [[r c]] [(inc r) (inc c)])})

(defn- gen-initial-paths [pos]
  (for [route (set (keys route-fn-map))]
    (let [new-pos ((get route-fn-map route) pos)]
      {:curr (pos->char new-pos)
       :start pos
       :end new-pos
       :route route})))

(defn- initial-candidates [word]
  (let [fc (first word)]
    (apply concat (map #(gen-initial-paths %) (char->pos fc)))))

(defn- step-forward [path]
  (let [route (:route path)
        curr-pos (:end path)
        new-pos ((get route-fn-map route) curr-pos)]
    {:curr (pos->char new-pos)
     :start (:start path)
     :end new-pos
     :route route}))

(defn- step-forward-all [paths]
  (map step-forward paths))

(defn- format-word-result [word paths]
  (when (seq paths)
    (let [path (first paths)]
      {:word word
       :start (:start path)
       :end (:end path)
       :route (:route path)})))

(defn find-word
  ([word] (find-word (rest word) (initial-candidates word) word))
  ([subword candidates word]
   (let [[fc & more] subword
         matching-candidates (filter #(= (:curr %) fc) candidates)]
     (println subword matching-candidates word)
     (if (seq more)
       (find-word more (step-forward-all matching-candidates) word)
       (format-word-result word matching-candidates)))))

;; This works now, nice!
(map find-word @words)
