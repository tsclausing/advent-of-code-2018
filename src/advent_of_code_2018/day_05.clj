(ns advent-of-code-2018.day-05
  "https://adventofcode.com/2018/day/5"
  (:require [clojure.string :as string]
            [clojure.math.numeric-tower :as math]))

(defn read-input [input]
  (string/trim input))

;-- PUZZLE SOLUTIONS --;

(defn react-polymer
  "Puzzle 1: How many units remain after fully reacting the polymer
   you scanned?"
  [polymer]
  (let [polymer-ns (map int (seq polymer))
        reacted-ns (reduce (fn [reaction curr]
                             (let [prev (or (peek reaction) 0)
                                   diff (math/abs (- prev curr))]
                               (if (= diff 32)
                                 (pop reaction)
                                 (conj reaction curr))))
                           []
                           polymer-ns)]
    (apply str (map char reacted-ns))))

(defn shortest-polymer
  "Puzzle 2: What is the length of the shortest polymer you can
   produce by removing all units of exactly one type and fully
   reacting the result?"
  [polymer]
  (let [polymer-seqs (map (fn [char-int]
                            (filter (fn [char] (not (#{char-int (+ char-int 32)} (int char))))
                                    (seq polymer)))
                          (range (int \A) (inc (int \Z))))]
    (apply str (apply min-key count (map react-polymer polymer-seqs)))))
