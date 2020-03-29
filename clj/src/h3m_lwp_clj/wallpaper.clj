(ns h3m-lwp-clj.wallpaper
  (:import
   [com.badlogic.gdx Gdx]
   [com.badlogic.gdx.utils.viewport Viewport])
  (:require
   [h3m-parser.core :as h3m-parser]
   [h3m-lwp-clj.terrain :as terrain]
   [h3m-lwp-clj.objects :as objects]
   [h3m-lwp-clj.orth-camera :as orth-camera]
   [h3m-lwp-clj.input-processor :as input-processor]))


(defn create-renderer
  [settings-atom ^Viewport viewport]
  (let [{position-update-interval :position-update-interval} @settings-atom
        h3m-map (h3m-parser/parse-h3m (.read (.internal Gdx/files "maps/invasion.h3m")))
        camera-controller (input-processor/create (.getCamera viewport) (:size h3m-map))
        terrain-renderer (terrain/create-renderer h3m-map)
        objects-renderer (objects/create-renderer h3m-map)]
    (orth-camera/set-camera-updation-timer (.getCamera viewport) (:size h3m-map) position-update-interval)
    (.setToOrtho (.getCamera viewport) true)
    (fn []
      (terrain-renderer (.getCamera viewport))
      (objects-renderer (.getCamera viewport))
      camera-controller)))
