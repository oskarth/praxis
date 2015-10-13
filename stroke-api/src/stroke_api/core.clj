(ns stroke-api.core
  (:require [org.httpkit.server :as server]))

;;; Server.

(defn app [req]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "hello"})

(defonce server (atom nil))

(defn stop-server []
  (when-not (nil? @server)
    (@server :timeout 100)
    (reset! server nil)))

(defn -main [& args]
  (reset! server (server/run-server #'app {:port 8080})))

;;; REPL prelude.

(comment
  (require '[stroke-api.core :refer :all])
  (require '[clojure.test :refer [run-tests]])
  (require '[clojure.tools.namespace.repl :refer [refresh]])
  (defn rt [] (refresh) (run-tests 'stroke-api.core-test))
)
