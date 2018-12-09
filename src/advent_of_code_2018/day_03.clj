(ns advent-of-code-2018.day-03
  "https://adventofcode.com/2018/day/3"
  (:require [clojure.string :as string]
            [clojure.set :as set]))

(defn read-input [input]
  (map (comp (partial zipmap [:claim :id :x :y :w :h])
             #(cons (first %) (map bigint (rest %)))
             (partial re-matches #"#(\d+)\s@\s(\d+),(\d+):\s(\d+)x(\d+)"))
       (string/split-lines input)))

;-- CLAIMS --;

(defn claim-points
  "A vector of vectors `[x y]` for each point in `claim`."
  [claim]
  (let [cx (claim :x) cy (claim :y) cw (claim :w) ch (claim :h)]
    (for [x (range cx (+ cx cw)) y (range cy (+ cy ch))]
      [x y])))

(defn claims-overlap?
  "Based on https://developer.mozilla.org/en-US/docs/Games/Techniques/2D_collision_detection"
  [claim-a claim-b]
  (let [ax (claim-a :x) ay (claim-a :y) aw (claim-a :w) ah (claim-a :h)
        bx (claim-b :x) by (claim-b :y) bw (claim-b :w) bh (claim-b :h)]
    (and (< ax (+ bx bw))
         (< bx (+ ax aw))
         (< ay (+ by bh))
         (< by (+ ay ah)))))

;-- PUZZLE SOLUTIONS --;

(defn overlapping-claim-points
  "Puzzle 1: How many points in the claims overlap?"
  [claims]
  ; Count points in claims that occur more than one time
  (count (filter (fn [[point freq]] (> freq 1))
                 (frequencies (mapcat identity (map claim-points claims))))))

(defn non-overlapping-claim
  "Puzzle 2: What is the ID of the only claim that doesn't overlap?"
  [claims]
  (first (drop-while #(not= 1 (count (filter (partial claims-overlap? %) claims)))
                     claims)))

(defn non-overlapping-claims
  "Puzzle 2: Variation ... what if there is more than one claim that doesn't overlap?"
  [claims]
  (reduce (fn [res claim]
            ; Non-overlapping claims only overlap themselves
            (if (= 1 (count (filter (partial claims-overlap? claim) claims)))
              (conj res claim)
              res))
          []
          claims))
