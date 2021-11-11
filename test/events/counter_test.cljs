(ns events.counter-test
  (:require [cljs.test :refer (deftest is)]
            [clojurern.events :as events]))

(deftest events-counter-test
  (is (= (events/update-counter {:db {:counter 0}})
         {:db {:counter 1}})))
