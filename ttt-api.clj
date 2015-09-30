#!/usr/bin/env boot
; Tic-tac-toe API
; From an interview problem I had. Timed task, 1.5h
;
; 1. Your server will be provided the current board in a GET request, using the
; 'board' parameter in the query string.
; 2. If the board string doesn't represent a valid tic-tac-toe board, or it’s not
; plausibly o’s turn, your server should return an HTTP response code 400 (Bad
; Request)
; 3. Your server always plays as o.
; 4. Either player can go first.
; 5. If the board is a valid tic-tac-toe board and it is plausibly o's turn, your
; 6. server should return a string representation of the same board with one ‘o’
; added.
; 7. If possible, your tic-tac-toe api should play optimally (i.e. never lose
; when it is possible to force a tie, or tie when it is possible to win)
;
; The board is encoded as a string of nine characters
; where each character is either 'o', 'x', or a space. The nine characters are the
; tic-tac-toe board read left to right, top to bottom -- for example: "xo o   x ",
;
; If I run `curl YOUR_URL?board=+xxo++o++`
; I should get `oxxo  o  ` (that’s o-x-x-o-space-space-o-space-space)
;
; Things I did bad last time:
; 1. No tests, lead to error-prone code
; 2. Did not finish all (eg diagonal checking)
; 3. Long time since using Clojure lead to stupid errors

; Dependencies and setup
;-------------------------------------------------------------------------------
(set-env! :dependencies '[[http-kit "2.1.19"]
                          [compojure "1.4.0"]
                          [ring/ring-mock "0.3.0"]])
(require '[org.httpkit.server :refer [run-server]])
(require '[clojure.test :refer [deftest is run-tests]])
(require '[ring.mock.request :as mock])
(require '[ring.middleware.params :refer [wrap-params]])

; Tic Tac Toe layer
;-------------------------------------------------------------------------------
; FIXME: Don't like all this board passing.
;
; Board
; 0 1 2
; 3 4 5
; 6 7 8

(defn contains-invalid-char? [board]
  (> (count (clojure.string/replace board #"[ox ]" "")) 0))

(defn count-chars [char string]
  {:pre [(char? char)]}
  (count (filter #(= % char) string)))

(defn o-turn?
  "It's o's turn if the difference in turns is either zero or one."
  [board]
  (let [o (count-chars \o board)
        x (count-chars \x board)]
    (= (- x o) (or 0 1))))

(defn valid-board? [board]
  (cond (not= (count board) 9) false
        (contains-invalid-char? board) false
        :else true))

(defn all-same? [coll]
  (let [s (apply str coll)]
    (or (= s "ooo") (= s "xxx"))))

(defn get-triplet [board triplet]
  (map #(get board %) triplet))

(defn won? [board]
  (some all-same?
     [(get-triplet board [0 1 2])
      (get-triplet board [3 4 5])
      (get-triplet board [6 7 8])
      (get-triplet board [0 3 6])
      (get-triplet board [1 4 7])
      (get-triplet board [2 5 8])
      (get-triplet board [0 4 8])
      (get-triplet board [2 4 6])]))

(defn tied? [board] (zero? (count-chars \space board)))
(defn finished? [board] (or (won? board) (tied? board)))
  
(defn playable-board? [board]
  (and (valid-board? board)
       (not (finished? board))
       (o-turn? board)))

; TODO: Basic AI
(defn play-move
  "Plays o at random free space."
  [board]
  (clojure.string/replace-first board #" " "o"))

; Web server and handler
;-------------------------------------------------------------------------------

(defn ok-resp [& [body]]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body body})

(defn bad-request-resp []
  {:status 400})
    
(defn ttt-handler [board]
  (if (playable-board? board)
      (ok-resp (play-move board))
      (bad-request-resp)))

(defn handler [req]
  (if-let [board (get (:query-params req) "board")]
    (ttt-handler board)
    (ok-resp)))

(def app (wrap-params handler))

(defonce server (atom nil))

(defn stop-server []
  (when-not (nil? @server)
    (@server :timeout 100)
    (reset! server nil)))

(defn -main [& args]
  (reset! server (run-server #'app {:port 8080})))

; Tests
;-------------------------------------------------------------------------------

(deftest webserver
  (is (= (:status (app (mock/request :get "/"))) 200))
  (is (= (:body (app (mock/request :get "/?board=+++++++++"))) "o        "))
  (is (= (:status (app (mock/request :get "/?board=foo"))) 400)))

(deftest tictactoe
  (is (= (playable-board? "        ") false))
  (is (= (playable-board? "         ") true))
  (is (= (playable-board? "q        ") false))
  (is (= (playable-board? "oo xx    ") true))
  (is (= (playable-board? "xx       ") false))
  (is (= (playable-board? "oooxxx   ") false))
  (is (= (won? "oooxxx   ") true))
  (is (= (won? " ooxxx   ") true))
  (is (= (won? "o  o  o  ") true))
  (is (= (won? "  x  x  x") true))
  (is (= (won? "ox  o   o") true))
  (is (= (tied? "xoxoxoxo") true))
  (is (= (play-move "xox    xo")  "xoxo   xo"))
)

;(run-tests)
; TODO: boot task for server start? rather than REPL and calling (-main)
