(ns advent-of-code-2018.day-04
  "https://adventofcode.com/2018/day/4"
  (:require [clojure.string :as string]
            [clojure.core.match :refer [match]]
            [clojure.core.match.regex]))

(defn read-input [input]
  (map (comp #(update % :m bigint)
             (partial zipmap [:entry :y :m :d :h :m :event])
             (partial re-matches #"\[(\d+)\-(\d+)\-(\d+)\s(\d+):(\d+)\]\s\w+\s(#?\w+).*"))
       (sort (string/split-lines input))))

;-- HANDLERS --;

(defn tx
  "Match a pair of events to a dispatch keyword to `handle`."
  [{a :event} {b :event}]
  (match [a b]
         [_ #"#\w+"] :*-guard
         ["asleep" "up"] :asleep-awake
         [_ _] nil))

(defmulti handle (fn [[a b] state] (tx a b)))
; A transition to the initial state records the new `:guard-on-duty`.
(defmethod handle :*-guard
  [[_ b] state]
  (let [guard (b :event)
        state (assoc state :guard-on-duty guard)
        state (update state :guards #(conj (or % #{}) guard))
        ] state))
; A transition from asleep to awake records metrics about the guard's sleep.
(defmethod handle :asleep-awake
  [[a b] state]
  (let [guard (state :guard-on-duty)
        minutes-slept (range (a :m) (b :m))
        state (update-in state [guard :minutes-asleep]
                         (fn [v] (concat (or v []) minutes-slept)))
        state (update-in state [guard :total-minutes-asleep]
                         (fn [v v2] (+ (or v 0) v2)) (count minutes-slept))
        state (assoc-in state [guard :minutes-asleep-frequency]
                        (frequencies (get-in state [guard :minutes-asleep])))
        state (assoc-in state [guard :minutes-asleep-most-frequency]
                        (val (apply max-key val (get-in state [guard :minutes-asleep-frequency]))))
        state (assoc-in state [guard :minutes-asleep-most-frequently]
                        (for [[k v] (get-in state [guard :minutes-asleep-frequency])
                              :when (= v (get-in state [guard :minutes-asleep-most-frequency]))]
                          k))
        ] state))
; Any other transition does not affect the projection and is discarded.
(defmethod handle :default
  [[_ _] state]
  state)

;-- PROJECTION --;

(defn guard-sleep-projection
  "Return a projection of sleep metrics from the `sleep-log`."
  [sleep-log]
  ; Project the `state` by successively `handle`-ing each entry in the `sleep-log`.
  (first (reduce (fn [[state prev] curr]
                   [(handle [prev curr] state) curr])
                 [{} nil]
                 sleep-log)))

;-- PUZZLE SOLUTIONS --;

(defn sleepiest-guard
  "Problem 1: Find the guard that has the most minutes asleep.
   What minute does that guard spend asleep the most?"
  [sleep-log]
  (let [proj (guard-sleep-projection sleep-log)
        guards (select-keys proj (proj :guards))]
    (apply max-key #(:total-minutes-asleep (val %))
           guards)))

(defn sleepiest-guard-2
  "Problem 2: Of all guards, which guard is most frequently
   asleep on the same minute?"
  [sleep-log]
  (let [proj (guard-sleep-projection sleep-log)
        guards (select-keys proj (proj :guards))]
    (apply max-key #(:minutes-asleep-most-frequency (val %))
           guards)))
