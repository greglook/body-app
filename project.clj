(defproject mvxcvi/body-app "0.1.0-SNAPSHOT"
  :description "A health tracking and planning webapp."
  :url "https://github.com/greglook/health-tracker"
  :license {:name "Public Domain"
            :url "http://unlicense.org/"}

  :source-paths ["src/clj"]

  :plugins
  [[lein-cljsbuild "1.0.5"]]

  :dependencies
  [[org.clojure/clojure "1.7.0-beta1"]
   [org.clojure/clojurescript "0.0-3211"]
   [prismatic/schema "0.4.2"]]

  :cljsbuild
  {:builds [{:source-paths ["src/cljs"]
             :compiler {:output-to "resources/public/js/main.js"
                        :optimizations :whitespace
                        :pretty-print true}}]})
