(ns stroke-api.core-test
  (:require [clojure.test :refer :all]
            [stroke-api.core :refer :all]
            [ring.mock.request :as mock]))

;; Bah. Reload tests ffs.

(deftest webserver-test
  (is (= (app (mock/request :get "/"))
         {:status 200
          :headers {"Content-Type" "text/plain"}
          :body "hello"}))
          )
