(defproject mvxcvi/body-app "0.1.0-SNAPSHOT"
  :description "A health tracking and planning webapp."
  :url "https://github.com/greglook/health-tracker"
  :license {:name "Public Domain"
            :url "http://unlicense.org/"}

  :source-paths ["src/clj"]

  :plugins
  [[lein-cljsbuild "1.0.6"]
   [lein-figwheel "0.3.7"]]

  :dependencies
  [[figwheel "0.3.7"]
   [org.clojure/clojure "1.7.0"]
   [org.clojure/clojurescript "0.0-3308"]
   [prismatic/schema "0.4.3"]
   [reagent "0.5.0"]]

  :figwheel
  {:repl false}

  :cljsbuild
  {:builds {:client {:source-paths ["src/cljs"]
                     :compiler
                     {:output-dir "target/app"
                      :output-to "target/app.js"}}}}

  :profiles
  {:dev {:cljsbuild
         {:builds {:client {:source-paths ["dev"]
                            :compiler
                            {:main mvxcvi.body-app.dev
                             :optimizations :none
                             :source-map true
                             :source-map-timestamp true
                             :pretty-print true}}}}}

   :prod {:cljsbuild
          {:builds {:client {:compiler
                             {:optimizations :advanced
                              :elide-asserts true
                              :pretty-print false}}}}}})
