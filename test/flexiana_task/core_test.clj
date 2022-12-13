(ns flexiana-task.core-test
  (:require [flexiana-task.routes :as sut]
            [flexiana-task.core :as core]
            [clojure.test :refer :all]))


(deftest api-logic-test

  (testing "GET request with two correct strings in params.
            Expecting answer: true."
    (let [res (sut/app {:uri "/"
                        :request-method :get
                        :params {:s1 "cedewaraaossoqqyt"
                                 :s2 "codewars"}})
          exp {:headers {"content-type" "application/edn"}
               :status 200
               :body {:data true}}]
      (is (= res exp))))

  (testing "GET request with two correct strings in params.
            Expecting answer: false."
    (let [res (sut/app {:uri "/"
                        :request-method :get
                        :params {:s1 "katas"
                                 :s2 "steak"}})
          exp {:headers {"content-type" "application/edn"}
               :status 200
               :body {:data false}}]
      (is (= res exp)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest incorrect-params-test

  (testing "GET request with one empty string."
    (let [res (sut/app {:uri "/"
                        :request-method :get
                        :params {:s1 "katas"
                                 :s2 ""}})
          exp {:headers {"content-type" "application/edn"}
               :status 400
               :body {:data nil
                      :error "Query params must be two not empty strings"}}]
      (is (= res exp))))

  (testing "GET request with one parameter"
    (let [res (sut/app {:uri "/"
                        :request-method :get
                        :params {:s1 "katas"}})
          exp {:headers {"content-type" "application/edn"}
               :status 400
               :body {:data nil
                      :error "Query params must be two not empty strings"}}]
      (is (= res exp)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest incorrect-request-test

  (testing "POST request test"
    (let [res (sut/app {:uri "/"
                        :request-method :post
                        :params {:s1 "katas"
                                 :s2 "steak"}})
          exp {:headers {}
               :status 404
               :body {:error "Page not found"}}]
      (is (= res exp))))

  (testing "Incorrect route request test"
    (let [res (sut/app {:uri "/incorrect-route"
                        :request-method :get
                        :params {:s1 "katas"
                                 :s2 "steak"}})
          exp {:headers {}
               :status 404
               :body {:error "Page not found"}}]
      (is (= res exp)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest core-app-test
  (testing "Middleware add :params and :query-params to request from :query-string"
    (let [request {:uri "/"
                   :request-method :get
                   :query-string "s1=katas&s2=steak"}
          wrap-echo (core/my-middleware identity)
          res (wrap-echo request)]
      (are [result exp] (= exp result)
        (:params res) {:s1 "katas", :s2 "steak"}
        (:query-params res) {"s1" "katas", "s2" "steak"}))))