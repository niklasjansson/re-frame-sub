(ns re-frame-sub.subs
    (:require-macros [reagent.ratom :refer [reaction]]
                     [re-frame-sub.sub-helpers :as h])
    (:require [re-frame.core :as re-frame ]))

(h/register-simple-subs [[:name :name]])
