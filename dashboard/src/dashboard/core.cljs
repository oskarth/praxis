(ns ^:figwheel-always dashboard.core
 (:require [goog.net.XhrIo :as xhr]
           [figwheel.client :as fc]
           [reagent.core :as r :refer [atom]]))

; TODO: Login with Github
; TODO: Get gist or something to show up
; TODO: Get iPhone 6 etc bg-color to fill screen

;(enable-console-print!) ; print to terminal
(fc/enable-repl-print!) ; print to repl

;; AJAX Layer
;; What is the interface we want? want to be able to get and post to urls
;; with Clojure data structures. Then get JSON back and translate that.

(defn gh-receiver [event]
  (let [xhr (.-target event)]
    (println (.getResponseText xhr))))

(defn GET [url content cb-fn]
  (xhr/send url cb-fn "GET" content))

(defn get-koan! []
  (GET "https://api.github.com/zen" "" gh-receiver))

;; Application state and components

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

(defn global-nav []
  [:nav.clearfix
    [:div.col
      [:a.btn.py2 {:href "/"} "Home"]]
    [:div.col-right
      [:a.btn.py2 {:href "/"} "About"]]])

(defn main-component []
  [:header
    (global-nav)
    [:div.center.px2.py4
      [:h2.h2-responsive.caps.mt4.mb0.regular "gitcomm"]
      [:p.h3 "A game of commitment"]
      [:a.h3.btn.btn-primary.mb4.black.bg-yellow {:href "#"} "Play with Github"]]])

(defn hello-world []
  [:h1 (:text @app-state)])

(r/render-component [main-component]
  (. js/document (getElementById "app")))
