(ns stroke-api.core-test
  (:require [clojure.test :refer :all]
            [stroke-api.core :refer :all]
            [ring.mock.request :as mock]
            [cheshire.core :as json]))

(deftest webserver-test
  (is (= (app (mock/request :get "/"))
         {:status 200
          :headers {"Content-Type" "application/json"}
          :body (json/generate-string "hi")}))
  )
