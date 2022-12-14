(ns flexiana-task-ui.api
  (:require [ajax.core :refer [GET]]
            [flexiana-task-ui.state :as state]))

(def server-uri "http://localhost:4000/")

(defn handler 
  [response]
  (.log js/console response)
  (reset! state/*result response))

(defn error-handler
  [{:keys [status status-text]}]
  (.log js/console (str "No patients found: " status " " status-text)))

(defn scramble
  []
  (GET server-uri
    {:params {:s1 (:rearranged-string @state/*strings)
              :s2 (:match-string @state/*strings)}
     :handler handler
     :error-handler error-handler}))