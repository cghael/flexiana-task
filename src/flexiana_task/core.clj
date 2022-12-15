(ns flexiana-task.core
  (:require [flexiana-task.config :refer [config]]
            [flexiana-task.routes :as routes]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.cors :refer [wrap-cors]]
            [mount.core :as mount :refer [defstate]]
            [muuntaja.middleware :as middleware]
            [taoensso.timbre :as log]
            [ring.middleware.resource :refer [wrap-resource]])
  (:gen-class))


(defn my-middleware
  [app]
  (-> app
      (wrap-resource "public")
      (middleware/wrap-format)
      (wrap-defaults (assoc site-defaults [:params :keywordize] true))
      (wrap-cors :access-control-allow-origin [#"http://localhost:3000"]
                 :access-control-allow-methods [:get])))


(defstate server
  :start
  (let [server-config (:server config)]
    (log/info "Running server with config" server-config)
    (jetty/run-jetty (my-middleware routes/app) server-config))
  
  :stop
  (do (log/info "Stop server.")
      (.stop server)))


(defn stop!
  []
  (let [status (mount/stop #'server)]
    status))


(defn start!
  []
  (let [status (mount/start #'config #'server)
        _ (log/info status)]
    status))


(defn -main
  "Start point"
  [& args]
  (start!))
