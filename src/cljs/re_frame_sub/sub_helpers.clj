(ns re-frame-sub.sub-helpers
  (:require [clojure.core])
  )

(defn- get-simple-get-sub [nm kw]
  `(re-frame/register-sub ~nm (~'fn [~'db [~'_ ~'_]] (~'let [~'val (~'reagent.ratom/reaction (~'get (~'deref ~'db) ~kw))] (~'reagent.ratom/reaction (~'deref ~'val)))))
  )

(defn- get-get-in-sub[nm kws]
  `(re-frame/register-sub ~nm (~'fn [~'db [~'_ ~'_]] (~'let [~'val (~'reagent.ratom/reaction (~'get-in (~'deref ~'db) ~kws))] (~'reagent.ratom/reaction (~'deref ~'val)))))
  )

(defn- create-sub-for-item [item]
  (cond
      (not (or (sequential? item) (keyword? item))) (throw (ex-info "all items must be keywords or sequences" {}))
      (keyword? item) (get-simple-get-sub item item)
      (= 1 (count item)) (get-simple-get-sub (first item) (first item))
      (= 2 (count item)) (get-simple-get-sub (first item) (second item))
      :else (get-get-in-sub (first item) (rest item))
    ))

(defmacro register-simple-subs
"Registers a number of simple subscriptions.
  Syntax: [ keys ].
  Syntax for a key:
    :keyword or [:keyword] - a single get call, subscription named :keyword.
    [:name :keyword-a :keyword-b ... ]  - a get-in call to all keywords, subscription named :name"
  [items]
  `(do ~@(map create-sub-for-item items) )
  )
