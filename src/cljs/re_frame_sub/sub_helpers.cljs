(ns re-frame-sub.sub-helpers
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]))

(defn- register-simple-get-sub [nm kw]
  (re-frame/register-sub nm (fn [db [_ _]] (let [val (reagent.ratom/reaction (get @db kw))] (reagent.ratom/reaction @val)))))

(defn- register-get-in-sub[nm kws]
  (re-frame/register-sub nm (fn [db [_ _]] (let [val (reagent.ratom/reaction (get-in @db kws))] (reagent.ratom/reaction @val)))))

(defn- create-sub-for-item [item]
  (cond
      (not (or (sequential? item) (keyword? item))) (throw (ex-info "all items must be keywords or sequences" {}))
      (keyword? item) (register-simple-get-sub item item)
      (= 1 (count item)) (register-simple-get-sub (first item) (first item))
      (= 2 (count item)) (register-simple-get-sub (first item) (second item))
      :else (register-get-in-sub (first item) (rest item))
    ))

(defn register-simple-subs
"Registers a number of simple subscriptions.
  Syntax: [ keys ].
  Syntax for a key:
    :keyword or [:keyword] - a single get call, subscription named :keyword.
    [:name :keyword-a :keyword-b ... ]  - a get-in call to all keywords, subscription named :name"
  [items]
  (doseq [item items]
    (create-sub-for-item item)) )
