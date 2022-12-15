(ns flexiana-task.routes
  (:require [compojure.core :refer :all]
            [compojure.route :as compojure-route]
            [flexiana-task.handlers :as h]
            [ring.util.response :refer [resource-response content-type]]))

(defroutes app
  (GET "/" [] (content-type (resource-response "index.html" {:root "public"}) "text/html"))
  (GET "/api" request h/scramble-handler)
  (compojure-route/not-found h/not-found))