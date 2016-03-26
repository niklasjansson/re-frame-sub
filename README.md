# re-frame-sub

An example implementation of helpers for re-frame subscriptions for when you just want to pass on simple lookup to db.

## Usage
```
(re-frame-sub.sub-helpers/register-simple-subs
  [:simple-keyword              ;both name of reaction and lookup position in db
   [:name-of-reaction-2 :keyword-to-lookup]
   [:name-of-reaction-3 :nested :list :of :keywords]])
```