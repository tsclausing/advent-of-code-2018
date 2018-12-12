(ns advent-of-code-2018.day-04-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2018.day-04 :refer :all]))

;-- PUZZLE SOLUTIONS --;

(def full-input (slurp "test/fixtures/day_04_test.txt"))

(deftest full-guard-sleep
  (testing "total-minutes-asleep"
    (let [guard-entry (sleepiest-guard (read-input full-input))
          guard-id (key guard-entry)
          [minute] ((val guard-entry) :minutes-asleep-most-frequently)]
      (is (= "#2411" guard-id))
      (is (= 42 minute))
      ; solution
      (is (= 101262 (* 2411 42))))))

(deftest full-guard-sleep-2
  (testing "minute-asleep-most-frequency"
    (let [guard-entry (sleepiest-guard-2 (read-input full-input))
          guard-id (key guard-entry)
          [minute] ((val guard-entry) :minutes-asleep-most-frequently)]
      (is (= "#2999" guard-id))
      (is (= 24 minute))
      ; solution
      (is (= 71976 (* 2999 24))))))
