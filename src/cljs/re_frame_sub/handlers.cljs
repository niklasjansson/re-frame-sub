(ns re-frame-sub.handlers
    (:require [re-frame.core :as re-frame]
              [re-frame-sub.db :as db]))

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))
