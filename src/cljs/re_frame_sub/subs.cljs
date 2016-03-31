(ns re-frame-sub.subs
    (:require-macros [reagent.ratom :refer [reaction]]
                     )
    (:require [re-frame.core :as re-frame ]
              [re-frame-sub.sub-helpers :as h]))

(h/register-simple-subs [[:name :nm]])
