(ns re-frame-sub.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [re-frame-sub.handlers]
              [re-frame-sub.subs]
              [re-frame-sub.views :as views]
              [re-frame-sub.config :as config]))

(when config/debug?
  (println "dev mode"))

(defn mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init [] 
  (re-frame/dispatch-sync [:initialize-db])
  (mount-root))
