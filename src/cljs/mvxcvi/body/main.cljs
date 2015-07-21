(ns mvxcvi.body.main
  (:require
    [reagent.core :as reagent :refer [atom]]))


(defn ^:export run
  []
  (reagent/render
    [:p "Hello, Reagent!"]
    (js/document.getElementById "app")))
