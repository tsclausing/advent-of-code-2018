(ns advent-of-code-2018.day-01
  "https://adventofcode.com/2018/day/1"
  (:require [clojure.string :as string]))

(defn read-input [input]
  (map bigint (string/split-lines input)))

;-- PUZZLE SOLUTIONS --;

(defn frequency
  "Puzzle 1: What is the resulting frequency after changes have
   been applied?"
  [changes]
  (apply + changes))

(defn first-repeat-frequency
  "Puzzle 2: Repeating changes, what is the first frequency your
   device reaches twice?"
  [changes]
  (reduce (fn [a v] (if-not (a v)
                      (conj a v)
                      ; Found a repeat!
                      (reduced v)))
          ; Seen
          #{0}
          ; Infinite next frequency value
          (reductions + (cycle changes))))
