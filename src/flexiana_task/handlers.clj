(ns flexiana-task.handlers
  (:require [ninja.unifier.response :as r]
            [taoensso.timbre :as log]
            [flexiana-task.api :as api]))

(defn scramble-handler
  [request]
  (log/info {:msg "Request recieved"
             :params (:params request)})
  (let [res-data (api/scramble request)]
    (if (r/error? res-data)
      (r/as-http (assoc res-data :error "Query params must be two not empty strings") 
                 {:headers {"content-type" "application/edn"}}) 
      (r/as-http res-data {:headers {"content-type" "application/edn"}}))))


(defn not-found
  [request]
  (r/as-http (r/as-not-found {:error "Page not found"})))