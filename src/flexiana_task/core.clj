(ns flexiana-task.core
  (:require [flexiana-task.routes :as routes]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.cors :refer [wrap-cors]]
            [muuntaja.middleware :as middleware]
            [taoensso.timbre :as log]))


(defn my-middleware
  [app]
  (-> app
      (middleware/wrap-format)
      (wrap-defaults (assoc site-defaults [:params :keywordize] true))
      (wrap-cors :access-control-allow-origin [#"http://localhost:3000"] 
                 :access-control-allow-methods [:get])))


(defn server
  [port host]
  (jetty/run-jetty (my-middleware routes/app) {:host  host 
                                               :port  port 
                                               :join? false}))


(defn -main
  "Start point"
  [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "4000"))
        host (or (System/getenv "HOST") "127.0.0.1")]
    (server port host)
    (log/info "Running server at" host "port" port)))
