(ns flexiana-task-ui.core
  (:require [reagent.core :as r]
            [flexiana-task-ui.component.header :refer [header]]
            [flexiana-task-ui.component.footer :refer [footer]]
            [flexiana-task-ui.component.content :refer [content]]))


(defn app
  []
  [:div.container
   [header]
   [content]
   [footer]])


(defn ^:export main
  "Run application startup logic."
  []
  (r/render
   [app]
   (.getElementById js/document "app")))


(comment
  (shadow.cljs.devtools.api/nrepl-select :app)
  )