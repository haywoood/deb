(ns deb.core-test
  (:require [debux.cs.core :as d :refer-macros [dbg dbgn clog clogn clog clogn break]]
            [cljs.test :refer-macros [deftest is testing run-tests]]))

(deftest deb-test
  (testing "does dbg work"
    (is (= 27 (+ 1 1 (dbg (* 5 5)))))))

(comment
  (enable-console-print!)
  (run-tests))
