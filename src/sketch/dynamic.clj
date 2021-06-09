(ns sketch.dynamic
  (:require [clojure.pprint :as pretty]
            [quil.core :as quil]))

(defn save-frame-to-disk
  ([]
   (quil/save-frame (pretty/cl-format nil
                                      "frames/~d-~2,'0d-~2,'0d-~2,'0d-~2,'0d-~2,'0d-####.jpeg"
                                      (quil/year)
                                      (quil/month)
                                      (quil/day)
                                      (quil/hour)
                                      (quil/minute)
                                      (quil/seconds))))
  ([state _]
   (save-frame-to-disk)
   state))

(defn- rand-int-around
  [n]
  (+ n
     (- (rand-int 5)
        2)))

(defn draw
  []
  (quil/no-loop)
  (quil/background 44 10 99)
  (quil/no-fill)
  (quil/stroke 180 9 63 0.5)
  (dotimes [_ 5]
    (quil/quad (rand-int-around 100)
               (rand-int-around 100)
               (rand-int-around 800)
               (rand-int-around 100)
               (rand-int-around 800)
               (rand-int-around 800)
               (rand-int-around 100)
               (rand-int-around 800)))
  (quil/stroke 44 10 99)
  (dotimes [_ 750000]
    (quil/point (rand-int 900)
                (rand-int 900)))
  (save-frame-to-disk))

(defn initialise
  []
  (quil/smooth)
  (quil/color-mode :hsb 360 100 100 1.0))
