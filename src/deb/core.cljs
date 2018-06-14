(ns deb.core
  (:require [debux.cs.core :as d :refer-macros [dbg dbgn clog clogn clog clogn break]]))

(defn update-phase [phases {:keys [draggableId source destination]}]
 (let [dest-index   (:index destination)
       source-index (:index source)
       phase-text   draggableId]
   (mapv (clogn (fn [{:keys [text index]
                      :as   phase}]
                  (if (= phase-text text)
                    (assoc phase :index dest-index)
                    (if (= index dest-index)
                      (assoc phase :index source-index)
                      phase))))
         phases)))

(let [phases [{:index 0 :text "Paint walls"}
              {:index 1 :text "Get sink"}
              {:index 2 :text "Fix heater"}]
      event {:draggableId "Fix heater"
             :type        "DEFAULT"
             :source      {:droppableId "fudge" :index 2}
             :destination {:droppableId "fudge" :index 1}
             :reason      "DROP"}]
  (update-phase phases event))
