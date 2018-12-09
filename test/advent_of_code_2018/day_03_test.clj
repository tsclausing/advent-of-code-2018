(ns advent-of-code-2018.day-03-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2018.day-03 :refer :all]))

(deftest example-points
  (testing "With example claim."
    (is (= [[0 0] [0 1] [1 0] [1 1]]
           (claim-points {:x 0 :y 0 :w 2 :h 2})))
    (is (= [[3 2] [3 3] [3 4] [3 5] [4 2] [4 3] [4 4] [4 5] [5 2] [5 3] [5 4] [5 5] [6 2] [6 3] [6 4] [6 5] [7 2] [7 3] [7 4] [7 5]]
           (claim-points {:x 3 :y 2 :w 5 :h 4})))))

(deftest example-overlapping
  (testing "With example claims."
    (is (= 4 (overlapping-claim-points (read-input "#1 @ 1,3: 4x4\n#2 @ 3,1: 4x4\n#3 @ 5,5: 2x2"))))))

(deftest example-non-overlapping
  (testing "With example claims."
    (is (= "#3 @ 5,5: 2x2"
           (:claim (non-overlapping-claim (read-input "#1 @ 1,3: 4x4\n#2 @ 3,1: 4x4\n#3 @ 5,5: 2x2")))))))

;-- PUZZLE SOLUTIONS --;

(def full-input (slurp "test/fixtures/day_03_test.txt"))

(deftest full-overlapping
  (testing "With full claims."
    (is (= 116140 (overlapping-claim-points (read-input full-input))))))

(deftest full-non-overlapping
  (testing "With full claims."
    (is (= "#574 @ 397,847: 21x24"
           ; oops, my first implementation was wrong and cared about input order
           (:claim (non-overlapping-claim (read-input full-input)))
           (:claim (non-overlapping-claim (shuffle (read-input full-input))))))))

(deftest full-non-overlapping-multiple-one
  (testing "With full claims no additional."
    (is (= ["#574 @ 397,847: 21x24"]
           (map :claim (non-overlapping-claims (read-input full-input)))))))

(deftest full-non-overlapping-multiple-many
  (testing "With full claims and additional."
    (is (= ["#0 @ 10000,10000: 2x2" "#574 @ 397,847: 21x24"]
           (map :claim (non-overlapping-claims (read-input (str "#0 @ 10000,10000: 2x2\n" full-input))))))))