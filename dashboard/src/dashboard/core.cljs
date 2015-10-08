(ns ^:figwheel-always dashboard.core
 (:require [goog.net.XhrIo :as xhr]
           [reagent.core :as r :refer [atom]]))

;; A game of commit

; Annoyances: source sometimes doesn't work when doc does
; ctrl-c cancels repl, shouldn't! ctrl-d does that.
; print should toggle ish

; How to print from inside lein repl process:
; (figwheel.client/enable-repl-print!)
;(figwheel.client/figwheel-repl-print (.getResponseText xhr))
;
https://github.com/bhauman/lein-figwheel/blob/master/support/src/figwheel/client.cljs

; TODO: try this https://gist.github.com/mneise/e39484340ff4a2789da6
; ok that works! prob with enable-conosle-print etc
;req            [figwheel.client :as fc]))
; (fc/enable-repl-print!)
; just println now.

(enable-console-print!)

; Use send() instance method instead instead

;;; AJAX Layer

(defn gh-receiver [event]
  (let [xhr (.-target event)]                     ; equivalent to event.target
    ;(figwheel.client/figwheel-repl-print (.getResponseText xhr))
    (println (.getResponseText xhr))
    #_(println "response" (.getResponseText xhr)))) ; getResponseJson exists

(defn GET [url content cb-fn]
  (xhr/send url cb-fn "GET" content))

(defn get-koan! []
  (GET "https://api.github.com/zen" "" gh-receiver))

; What is the interface we want? want to be able to get and post to urls
; with Clojure data structures. Then get JSON back and translate that.


(defonce gh-koan (r/atom 0))

(defonce app-state (atom {:text "Hello Oskar!"}))

; Not a good solution. Instead, when we click button we put it on a queue or
; whatever. Event loop thingy like you had with your om thing. Use that!
(defn counting-component []
  [:div
    "The atom " [:code "gh-koan"] " has value: "
    @gh-koan ". "
    [:input {:type "button" :value "Click me to get it!"
             :on-click #(swap! gh-koan inc)}]])

(defn timer-component []
  (let [seconds-elapsed (r/atom 0)]
    (fn []
      (js/setTimeout #(swap! seconds-elapsed inc) 1000)
      [:div
        "Seconds Elapsed: " @seconds-elapsed])))

(defn simple-component [name]
  [:div
    [:p "I am a component called " name "!"]])

(defn main-component []
  [:div
    [:p "I include simple-component."]
    [simple-component "world"]
    [counting-component]
    [timer-component]])

(defn hello-world []
  [:h1 (:text @app-state)])

(r/render-component [main-component]
  (. js/document (getElementById "app")))

(defn on-js-reload []
  ;(swap! app-state update-in [:__figwheel_counter] inc)
)
