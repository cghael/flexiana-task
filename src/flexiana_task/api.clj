(ns flexiana-task.api
  (:require [taoensso.timbre :as log]
            [ninja.unifier.response :as r]))

(defn scramble?
  "Returns true if a portion of s1 characters can be rearranged to match s2, otherwise returns false"
  [s1 s2]
  (let [s1-fr (frequencies s1)
        s2-fr (frequencies s2)]
    (every? #(and (s1-fr %)
                  (>= (s1-fr %) (s2-fr %))) (keys s2-fr))))

(defn scramble
  [{{:keys [s1 s2]} :params :as request}]
  (if (and (seq s1) (seq s2))
    (let [res-data {:data (scramble? s1 s2)}]
      (log/info "Scramble success.")
      (r/as-success res-data))
    (do
      (log/error "Error scramble data with params" (:params request))
      (r/as-incorrect {:data nil}))))
