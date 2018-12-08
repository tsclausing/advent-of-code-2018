(ns advent-of-code-2018.day-02
  "https://adventofcode.com/2018/day/2"
  (:require [clojure.string :as string]))

(defn read-input [input]
  (string/split-lines input))

(defn checksum
  "Puzzle 1: Count the items that contain exactly two of any letter
   and separately count those with exactly three of any letter.
   Multiply the two counts together to get a rudimentary checksum."
  [colls]
  (let [repeated #(set (vals (frequencies %)))
        {twos 2 threes 3
         :or  {twos 0 threes 0}} (->> colls
                                      (map (comp sequence repeated))
                                      flatten
                                      frequencies)]
    (* twos threes)))

(defn off-by-same-one
  "Puzzle 2: Find which strings differ by exactly one character at
   the same position. Returns the common letters that the identified
   strings share."
  [strings]
  (reduce (fn [a idx]
            (into a (filter
                      ; Only [string frequency] where frequency > 1
                      (fn [[k v]] (> v 1))
                      ; of `strings` where char at `idx` is removed
                      (frequencies (map
                                     #(str (subs % 0 idx) (subs % (inc idx)))
                                     strings)))))
          ; Returned map of [string frequency] in which keys are solutions
          {}
          ; For the range of indexes in the first string with the
          ; assumption that all `strings` are the same length
          (range (count (first strings)))))
