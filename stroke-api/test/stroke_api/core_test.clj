(ns stroke-api.core-test
  (:require [clojure.test :refer :all]
            [stroke-api.core :refer :all]
            [ring.mock.request :as mock]
            [cheshire.core :as json]))

;; Note: add a header with something like
;; (app (header (mock/request :get "/") "Authorization" "Basic #f(foo:bar)"))) 

(deftest webserver-test
  (is (= (:status (app (mock/request :get "/"))) 401))

  (is (= (:status (app (mock/request :get "/")))
         200))
  )
