(ns advent-of-code-2018.day-01-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2018.day-01 :refer :all]))

(deftest example-frequency
  (testing "with example input."
    (is (= 3 (frequency (read-input "+1\n+1\n+1"))))
    (is (= 0 (frequency (read-input "+1\n+1\n-2"))))
    (is (= -6 (frequency (read-input "-1\n-2\n-3"))))))

(deftest example-repeat-frequency
  (testing "with example repeat input."
    (is (= 0 (first-repeat-frequency (read-input  "+1\n-1"))))
    (is (= 10 (first-repeat-frequency (read-input "+3\n+3\n+4\n-2\n-4"))))
    (is (= 5 (first-repeat-frequency (read-input  "-6\n+3\n+8\n+5\n-6"))))
    (is (= 14 (first-repeat-frequency (read-input "+7\n+7\n-2\n-7\n-4"))))))


;-- PUZZLE SOLUTIONS --;
(def full-input (slurp "test/fixtures/day_01_test.txt"))

(deftest full-frequency
  (testing "Full input."
    (is (= 556 (frequency (read-input full-input))))))

(deftest full-repeat-frequency
  (testing "Full repeat input."
    (is (= 448 (first-repeat-frequency (read-input full-input))))))
