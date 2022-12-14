(ns flexiana-task-ui.state
  (:require [reagent.core :as r]))


(def *strings (r/atom {:rearranged-string ""
                       :match-string ""}))

(def *result (r/atom nil))