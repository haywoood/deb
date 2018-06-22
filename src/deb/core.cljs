(ns deb.core
  (:require-macros [cljs.core.async.macros :refer [go-loop]])
  (:require [debux.cs.core :as d :refer-macros [dbg dbgn clog clogn clog clogn break]]
            [artemis.core :as a]
            [artemis.document :as dd]
            [artemis.stores.mapgraph.core :as mgs]
            [artemis.network-steps.http :as http]))

(def client (a/create-client :store         (mgs/create-store :id-attrs #{:File/id})
                             :network-chain (http/create-network-step "https://api.graph.cool/simple/v1/cj3xmuos7p3ns0104k9clzkep")))

(def file-fragment
  (dd/parse-document
   "fragment fileFrag on File {
     __typename
     name
     url
     id
   }"))

(def query
 (dd/parse-document
  "query ok {
    allFiles {
      ...fileFrag
    }
  }"))

(let [q-chan (a/query! client (clog (dd/compose query file-fragment)) {} :fetch-policy :local-then-remote)]
  (go-loop []
    (when-let [result (<! q-chan)]
      (clog (a/store client)))))

(a/store client)

(comment
  (in-ns 'shadow.user)
  (def mapgraph-store (mgs/create-store :id-attrs #{:File/id}))
  (def network-chain (http/create-network-step "https://api.graph.cool/simple/v1/cj3xmuos7p3ns0104k9clzkep"))
  (shadow/nrepl-select :app))
