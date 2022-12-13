(ns flexiana-task.routes
  (:require [compojure.core :refer :all]
            [compojure.route :as compojure-route]
            [flexiana-task.handlers :as h]))

(defroutes app
  (GET "/" request h/scramble-handler)
  (compojure-route/not-found h/not-found))