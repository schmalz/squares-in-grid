(ns sketch.dynamic
  (:require [clojure.pprint :as pretty]
            [quil.core :as quil]))

(def variance 8)

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
     (- (rand-int (inc (* 2 variance)))
        variance)))

(defn- draw-quad
  []
  (quil/stroke 180 9 63 (rand))
  (quil/line (rand-int-around 100)
             (rand-int-around 100)
             (rand-int-around 800)
             (rand-int-around 100))
  (quil/line (rand-int-around 800)
             (rand-int-around 100)
             (rand-int-around 800)
             (rand-int-around 800))
  (quil/line (rand-int-around 800)
             (rand-int-around 800)
             (rand-int-around 100)
             (rand-int-around 800))
  (quil/line (rand-int-around 100)
             (rand-int-around 800)
             (rand-int-around 100)
             (rand-int-around 100)))

(defn- draw-point
  []
  (quil/point (rand-int (quil/width))
              (rand-int (quil/height))))

(defn draw
  []
  (quil/no-loop)
  (quil/background 44 10 99)
  (quil/no-fill)
  (dotimes [_ 5]
    (draw-quad))
  (quil/stroke 44 10 99)
  (dotimes [_ 750000]
    (draw-point))
  (save-frame-to-disk))

(defn initialise
  []
  (quil/smooth)
  (quil/color-mode :hsb 360 100 100 1.0))
