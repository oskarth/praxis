(ns stroke-api.core
  (:require [org.httpkit.server :as server]
            [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [cheshire.core :as json]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]))

;;; Server and routes.

(defn json-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string data)})

(defroutes app-routes
  (GET "/" [] (json-response "hi"))
  (route/not-found (json-response "Page not found." 404)))

(defonce server (atom nil))

(defn stop-server []
  (when-not (nil? @server)
    (@server :timeout 100)
    (reset! server nil)))

(def app
  (wrap-defaults app-routes api-defaults))

(defn -main [& args]
  (reset! server (server/run-server #'app {:port 8080})))

;;; REPL prelude.

(comment
  (require '[stroke-api.core :refer :all])
  (require '[clojure.test :refer [run-tests]])
  (require '[clojure.tools.namespace.repl :refer [refresh]])
  (defn rt [] (refresh) (run-tests 'stroke-api.core-test))
  (rt)
)
