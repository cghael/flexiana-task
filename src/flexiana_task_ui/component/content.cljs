(ns flexiana-task-ui.component.content
  (:require [flexiana-task-ui.state :as state]
            [flexiana-task-ui.api :as api]
            [flexiana-task-ui.component.result :refer [result]]))


(defn content
  []
  [:div.content
   [:div.scramble_body
    [:div.scramble_description
     "Please, enter two strings and scramble it!"]
    [:div.form
     [:div.row
      [:div.col-25
       [:label "Rearranged string:"]]
      [:div.col-75
       [:input.form__input {:type "text"
                            :value (:rearranged-string @state/*strings)
                            :on-change #(swap! state/*strings assoc :rearranged-string (.. % -target -value))}]]]]
    [:div.form
     [:div.row
      [:div.col-25
       [:label "Match string:"]]
      [:div.col-75
       [:input.form__input {:type "text"
                            :value (:match-string @state/*strings)
                            :on-change #(swap! state/*strings assoc :match-string (.. % -target -value))}]]]]
    [:button.scramble-btn
     (when (and (seq (:rearranged-string @state/*strings)) 
                (seq (:match-string @state/*strings)))
      {:class "active"
       :on-click #(do
                   (api/scramble)
                   (reset! state/*strings {:rearranged-string ""
                                           :match-string ""}))})
     "Scramble!"]
    [result]]])