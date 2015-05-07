(ns mvxcvi.body.main
  (:require
    [clojure.browser.repl :as repl]))

(repl/connect "http://localhost:3004/repl")

(.write js/document "<p>Hello, world!</p>")
