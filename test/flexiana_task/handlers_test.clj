(ns flexiana-task.handlers-test
  (:require [flexiana-task.api :as api]
            [flexiana-task.handlers :as sut] 
            [clojure.test :refer :all]
            [ninja.unifier.response :as r]))

#_(deftest scramble-handler-test
  (let [request {:uri "/" :request-method :get}]
    
    (testing "Handle success api response"
      (with-redefs [api/scramble (constantly (r/as-success {:data true}))] 
        (let [exp {:headers {"content-type" "application/edn"}, :status 200, :body {:data true}}
              res (sut/scramble-handler request)]
          (is (= exp res)))))
    
    (testing "Handle error api response"
      (with-redefs [api/scramble (constantly (r/as-incorrect {:data nil}))]
        (let [exp {:headers {"content-type" "application/edn"}, :status 400, :body {:data nil, :error "Query params must be not empty strings"}}
              res (sut/scramble-handler request)]
          (is (= exp res)))))))