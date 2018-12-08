(ns advent-of-code-2018.day-02-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2018.day-02 :refer :all]))

(deftest simple-checksum
  (testing "Simple checksum input."
    (is (= 12 (checksum (read-input "abcdef\nbababc\nabbcde\nabcccd\naabcdd\nabcdee\nababab"))))))

(deftest simple-off-by-same-one
  (testing "Simple off by same one input."
    (is (= {"fgij" 2} (off-by-same-one (read-input "abcde\nfghij\nklmno\npqrst\nfguij\naxcye\nwvxyz"))))))


;-- PUZZLE SOLUTIONS --;
(def full-input (slurp "test/fixtures/day_02_test.txt"))

(deftest full-checksum
  (testing "Full input."
    (is (= 7470 (checksum (read-input full-input))))))

(deftest full-off-by-same-one
  (testing "Full off by same one input."
    (is (= {"kqzxdenujwcstybmgvyiofrrd" 2} (off-by-same-one (read-input full-input))))))