(ns flexiana-task-ui.component.result
  (:require [flexiana-task-ui.state :as state]))

(defn result
  []
  [:div.result-window__underlay (when @state/*result {:class "active"})
   [:div.result-window
    [:div.result-window__body
     (if (nil? (:data @state/*result))
       (:error @state/*result)
       (str "Your result is " (:data @state/*result)))]
    [:div.result-window__footer
     [:button.window-btn
      {:on-click #(reset! state/*result nil)}
      "Try again"]]]])