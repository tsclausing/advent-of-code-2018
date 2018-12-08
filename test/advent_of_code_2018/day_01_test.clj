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
(def full-input "+3\n+15\n-1\n-18\n+3\n-10\n+3\n+10\n-8\n-20\n+13\n+11\n-21\n-10\n-16\n-2\n-6\n+19\n-8\n+12\n-13\n+4\n-9\n-20\n+12\n+15\n-17\n-18\n+11\n-17\n-9\n-12\n-9\n+3\n+9\n-6\n+18\n+16\n+9\n-11\n-1\n+8\n-20\n+17\n-3\n-16\n-7\n+10\n+6\n+3\n+15\n+3\n-2\n+9\n-8\n-6\n+11\n+5\n+24\n+10\n+4\n-8\n+2\n+13\n-16\n+13\n-16\n+11\n+7\n-9\n-13\n-12\n+17\n-4\n-5\n-1\n+16\n+22\n+8\n+12\n+9\n+2\n-3\n-5\n+12\n+4\n+4\n+8\n+18\n+9\n-14\n-11\n-5\n-11\n+15\n-18\n-7\n-12\n+1\n+5\n-18\n+14\n-13\n-10\n+17\n+8\n+11\n-12\n+20\n-16\n+22\n+5\n+1\n-9\n+16\n+13\n+6\n+5\n+7\n-4\n-4\n-7\n+16\n+4\n-1\n-15\n-11\n+19\n+9\n+17\n-15\n+21\n-7\n+4\n-16\n-3\n-9\n+4\n+11\n+12\n+11\n+7\n+12\n+14\n-6\n-1\n-18\n-3\n-13\n+4\n+10\n-15\n-10\n-8\n+15\n+15\n+19\n+16\n-3\n-14\n+13\n+16\n+1\n+10\n+9\n+7\n-11\n-19\n+2\n-3\n+10\n-2\n+13\n-19\n-7\n+3\n+18\n+1\n+9\n+2\n+12\n-13\n-9\n-7\n-15\n-18\n-6\n-1\n-8\n-12\n+19\n-1\n-14\n-6\n-16\n-4\n+1\n+4\n-22\n-19\n-2\n+4\n+18\n-8\n-19\n-16\n+6\n+16\n-7\n+4\n+8\n-20\n-11\n-11\n+20\n+15\n+19\n+30\n+19\n+19\n-11\n-18\n+4\n+1\n+21\n-9\n-9\n+12\n+18\n-1\n-6\n+12\n+16\n+12\n+3\n+1\n-9\n-15\n-15\n-1\n-1\n-18\n-32\n-11\n-17\n-20\n+4\n+14\n-1\n+16\n+20\n+53\n+19\n+16\n+14\n+14\n+6\n-8\n+14\n-17\n-5\n+3\n+14\n-3\n-15\n-10\n-19\n+17\n+3\n+17\n+5\n+14\n-7\n+5\n+14\n+18\n-2\n-19\n+8\n+10\n+15\n-6\n+13\n-11\n+19\n+10\n-15\n-15\n+2\n-4\n-3\n+11\n+2\n+18\n+3\n+17\n+9\n-16\n-8\n+6\n+15\n-11\n-3\n+20\n-1\n+12\n+1\n+8\n+11\n-8\n+6\n+7\n+17\n-12\n+13\n-8\n+16\n+15\n-8\n-12\n+7\n-12\n-18\n+5\n+5\n+7\n-8\n+6\n-4\n-15\n+17\n-15\n-10\n-1\n+17\n+18\n-21\n-19\n-9\n+19\n+16\n+18\n-5\n-15\n+19\n-11\n+10\n+9\n-1\n+4\n+4\n+9\n-5\n+20\n+4\n-18\n+17\n+12\n-18\n+14\n-21\n-15\n-5\n-18\n-17\n-19\n+11\n+12\n-20\n-19\n+12\n-9\n+8\n-1\n-22\n+4\n+15\n+14\n+9\n-10\n-26\n-22\n-10\n+7\n+9\n-1\n-16\n-21\n-18\n+8\n+6\n+18\n+1\n+5\n+11\n+18\n+6\n+20\n-7\n-10\n-5\n+33\n-21\n-8\n-13\n-5\n+7\n+1\n-7\n-39\n-14\n-12\n+16\n-14\n-15\n+4\n+2\n+12\n-11\n+16\n+8\n+15\n-1\n+21\n-5\n+13\n-18\n-7\n+2\n-15\n+19\n+8\n+26\n-24\n+37\n+59\n+21\n-19\n+18\n+6\n-11\n+15\n-2\n+5\n-2\n+20\n+8\n+2\n-7\n+18\n-8\n-1\n-8\n+3\n+17\n+12\n+18\n+2\n-8\n+3\n+40\n+3\n+21\n+5\n-1\n+15\n+2\n+16\n-50\n+15\n+5\n+21\n-31\n-17\n-15\n-10\n-9\n-2\n-18\n-14\n+7\n-14\n+6\n-20\n-8\n+13\n-27\n+8\n+2\n-16\n+3\n-7\n-6\n+23\n+31\n+52\n-6\n-1\n+2\n-54\n-42\n-6\n+18\n+2\n+1\n-29\n+36\n-42\n+7\n-146\n-2\n-14\n-13\n+19\n-8\n+19\n-15\n-18\n-4\n-18\n-1\n+2\n-3\n+11\n-16\n-23\n-9\n-30\n-31\n+58\n+18\n-19\n+45\n+11\n+14\n-16\n+19\n-10\n+17\n+12\n-2\n-8\n+19\n-50\n+8\n-114\n-40\n-62\n-153\n-60\n+12\n-23\n-40\n-87\n+377\n-79652\n-12\n+1\n-9\n-5\n-12\n-14\n+9\n-3\n-2\n+8\n-15\n-18\n+19\n-6\n+4\n-14\n-2\n-18\n-19\n-8\n+2\n+11\n-19\n-11\n+5\n-3\n+4\n+1\n-3\n+9\n+7\n+15\n-9\n-2\n-6\n+3\n+2\n+10\n+9\n+3\n-5\n-4\n+12\n+15\n-6\n-11\n-9\n-15\n+9\n-19\n-15\n+4\n+7\n+7\n-16\n+15\n-4\n-19\n-8\n-11\n-18\n-14\n-18\n-16\n+5\n+5\n+12\n-9\n-16\n+2\n-6\n-2\n+20\n-13\n-9\n-16\n+4\n-1\n+2\n-3\n-17\n-2\n-11\n+1\n+6\n+17\n-4\n-15\n-3\n-19\n+6\n-16\n-18\n-6\n+2\n+12\n-9\n+6\n+7\n-6\n+7\n-9\n-18\n-3\n+10\n-17\n+11\n+16\n+16\n+6\n+13\n-17\n+2\n+3\n+17\n-10\n+8\n-1\n-9\n-15\n+11\n+2\n-14\n+7\n-12\n-19\n-3\n-7\n-7\n-15\n-17\n+5\n+3\n+14\n+6\n-5\n+17\n-9\n-5\n+1\n-8\n-13\n+7\n+17\n-12\n-14\n-18\n+10\n+16\n-3\n-19\n+7\n+14\n+14\n+11\n-1\n+18\n-16\n-4\n+10\n-14\n+9\n-17\n-1\n+4\n-14\n-16\n+1\n-14\n-7\n+1\n+7\n-11\n-4\n-14\n+19\n-11\n+8\n+14\n+8\n-15\n-18\n+2\n-17\n-19\n+13\n+3\n+6\n+8\n-10\n+7\n-3\n-9\n+8\n+12\n-4\n+15\n+7\n+10\n+11\n-10\n-20\n-16\n+11\n-1\n-21\n-13\n-19\n-15\n-15\n-7\n+10\n+19\n-17\n-6\n-10\n-13\n+12\n-18\n+16\n+10\n-13\n+12\n-1\n+12\n+12\n+10\n+16\n-18\n-5\n-10\n-13\n-7\n+19\n+14\n+18\n-2\n-4\n-13\n+14\n+18\n-7\n+11\n-13\n-21\n-12\n+14\n+16\n+7\n-19\n+16\n+7\n+2\n-5\n+1\n+19\n+16\n+14\n+9\n+12\n+14\n-8\n-28\n+13\n+11\n+13\n-17\n-21\n+15\n-37\n-11\n+19\n+1\n+8\n+12\n-1\n-27\n-21\n-19\n-21\n+15\n-24\n-7\n-11\n-17\n-15\n-11\n-11\n+8\n-9\n-9\n-4\n-6\n+15\n-10\n-3\n+19\n+9\n+8\n+6\n+11\n-4\n-14\n-1\n+3\n-15\n+17\n+18\n-10\n-22\n-15\n+14\n+6\n-14\n-11\n+4\n-18\n+5\n-16\n-9\n-13\n+14\n+3\n+9\n+17\n-9\n+22\n-12\n-8\n-5\n-1\n-6\n-6\n-19\n+17\n+9\n+28\n+20\n+14\n+11\n+6\n+7\n-2\n+17\n+17\n-12\n+7\n+21\n-2\n-4\n+10\n+2\n+12\n-19\n-22\n-8\n-20\n+25\n-17\n-15\n-8\n-1\n+13\n-15\n+16\n+15\n+19\n-128\n-4\n-16\n-4\n+13\n-10\n+20\n-16\n-13\n+15\n+9\n+1\n-11\n-16\n+13\n+1\n+15\n+1\n-31\n+11\n-53\n-11\n-9\n-16\n-11\n+7\n+6\n+13\n+6\n-12\n-3\n+16\n+2\n-5\n+7\n-16\n+17\n-13\n-9\n+15\n-3\n+13\n+8\n+5\n-8\n-1\n-5\n-21\n+25\n+23\n+12\n+1\n-2\n+7\n-25\n-51\n-5\n+4\n+14\n-8\n+4\n+11\n-5\n-21\n-14\n+9\n+8\n-13\n+4\n+80915")

(deftest full-frequency
  (testing "Full input."
    (is (= 556 (frequency (read-input full-input))))))

(deftest full-repeat-frequency
  (testing "Full repeat input."
    (is (= 448 (first-repeat-frequency (read-input full-input))))))