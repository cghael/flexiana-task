(ns flexiana-task.api
  (:require [taoensso.timbre :as log]
            [ninja.unifier.response :as r]))

(defn scramble?
  "Returns true if a portion of s1 characters can be rearranged to match s2, otherwise returns false"
  [s1 s2]
  (when (and (seq s1) (seq s2))
    (let [s1-fr (frequencies s1)
          s2-fr (frequencies s2)]
      (every? #(and (s1-fr %)
                    (>= (s1-fr %) (s2-fr %))) (keys s2-fr)))))

(defn scramble
  [request]
  (let [req-params (:params request)
        res-data {:data (scramble? (:s1 req-params) (:s2 req-params))}]
    (if (nil? (:data res-data))
      (do
        (log/error "Error scramble data with params" (:params request))
        (r/as-incorrect res-data))
      (do
        (log/info "Scramble success.")
        (r/as-success res-data)))))