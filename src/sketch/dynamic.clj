(ns sketch.dynamic
  (:require [clojure.pprint :as pretty]
            [quil.core :as quil]))

(def variance 8)

(def low 100)
(def high 800)

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
  "A random integer in the range N +/- X."
  [n x]
  (+ n
     (- (rand-int (inc (* 2 x)))
        x)))

(defn- draw-shade-line
  []
  (quil/stroke 180 9 63 (rand)) ; base1 (grey)
  (quil/curve (quil/random low high)
              (quil/random low high)
              (quil/random low high)
              (quil/random low high)
              (quil/random low high)
              (quil/random low high)
              (quil/random low high)
              (quil/random low high))
  )

(defn- draw-shading
  [n]
  (dotimes [_ n]
    (draw-shade-line)))

(defn- draw-quad
  []
  (quil/stroke 45 100 71 (rand)) ; yellow
  (quil/line (rand-int-around low variance)
             (rand-int-around low variance)
             (rand-int-around high variance)
             (rand-int-around low variance))
  (quil/line (rand-int-around high variance)
             (rand-int-around low variance)
             (rand-int-around high variance)
             (rand-int-around high variance))
  (quil/line (rand-int-around high variance)
             (rand-int-around high variance)
             (rand-int-around low variance)
             (rand-int-around high variance))
  (quil/line (rand-int-around low variance)
             (rand-int-around high variance)
             (rand-int-around low variance)
             (rand-int-around low variance)))

(defn- draw-quads
  [n]
  (dotimes [_ n]
    (draw-quad)))

(defn- draw-point
  []
  (quil/point (rand-int (quil/width))
              (rand-int (quil/height))))

(defn- draw-points
  [n]
  (quil/stroke 44 10 99)
  (dotimes [_ n]
    (draw-point)))

(defn draw
  []
  (quil/no-loop)
  (quil/background 44 10 99)
  (quil/no-fill)
  (draw-quads 7)
  (draw-shading 1001)
  (draw-points 750000)
  (save-frame-to-disk))

(defn initialise
  []
  (quil/smooth)
  (quil/color-mode :hsb 360 100 100 1.0))
