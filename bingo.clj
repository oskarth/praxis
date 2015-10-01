; Bingo
; http://programmingpraxis.com/2009/02/19/bingo/
; Bingo is a children’s game of chance, sometimes played by adults for fun or
; money. Each player has a card of numbers arranged in a five-by-five grid with
; five randomly-chosen numbers from 1 to 15 in the first column, from 16 to 30 in
; the second column, 31 to 45 in the third column, 46 to 60 in the fourth column,
; and 61 to 75 in the fifth column; the central space is “free” and is considered
; to be occupied. Then a caller randomly calls numbers from 1 to 75 without
; replacement, each player marking the corresponding number, if it is present on
; their card, as occupied. The first player to have five occupied numbers in a row
; horizontally, in a column vertically, or along either of the two major diagonals
; is the winner.
; 
; What is the average number of calls required before a single card achieves
; bingo? In a large game with five hundred cards in play, what is the average
; number of calls required before any card achieves bingo?
;
; Let's just simulate it.

; Bingo layer

(defn- new-row [row]
  (vec (for [col (range 5)]
            (+ 1 (* row 15) (rand-int 15)))))

(defn new-board
  "Creates a new bingo board."
  []
  (assoc-in (vec (for [row (range 5)]
                      (new-row row)))
            [2 2]
            true))

(defn- maybe-play-bingo
  [board]
  (let [rand (+ 1 (rand-int 75))]
    (for [r (range 5) c (range 5)
          :when (= (get-in board [r c]) rand)]
         (assoc-in board [r c] true))))

(defn play-bingo
  "Plays a round of bingo and returns board, updated or not."
  [board]
  (let [maybe (maybe-play-bingo board)]
    (if (seq maybe)
        (first maybe)
        board)))

(defn candidate-colls [board]
  (let [rows (for [row (range 5)]
                  (get board row))
        cols (vec (for [col (range 5)]
               (vec (for [row (range 5)]
                         (get-in board [row col])))))
        diag1 [(vec (for [pos [[0 0] [1 1] [2 2] [3 3] [4 4]]]
                         (get-in board pos)))]
        diag2 [(vec (for [pos [[4 0] [3 1] [2 2] [1 3] [0 4]]]
                         (get-in board pos)))]]
    (concat rows cols diag1 diag2)))


(defn bingo?
  "Checks if board has a bingo position."
  [board]
  (seq (filter #(every? true? %) (candidate-colls board))))

; Game layer

(defn single-until-bingo
  "Plays a single game until it gets bingo. Returns number of calls it took."
  ([]
    (single-until-bingo (new-board) 0))
  ([board n]
    (let [new-board (play-bingo board)]
      (if (bingo? new-board)
          n
          (single-until-bingo new-board (inc n))))))

;; TODO: Very inefficient, should run in parallel.
(defn large-until-bingo
  "Plays n games until bingo."
  [n]
  (apply min (repeatedly n single-until-bingo)))

; 69 average (n=10k)
(defn average-single [n]
  (Math/floor (/ (reduce + (repeatedly n single-until-bingo)) n)))

; 12 average (n=10)
(defn average-large [n]
  (Math/floor (/ (reduce + (repeatedly n #(large-until-bingo 500))) n)))
