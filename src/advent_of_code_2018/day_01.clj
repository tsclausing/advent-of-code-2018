(ns advent-of-code-2018.day-01
  "https://adventofcode.com/2018/day/1"
  (:require [clojure.string :as string]))

(defn read-input [input]
  (map bigint (string/split-lines input)))

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
                      ; found a repeat!
                      (reduced v)))
          ; seen
          #{0}
          ; infinite next value
          (reductions + (cycle changes))))
