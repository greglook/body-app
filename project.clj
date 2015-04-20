(defproject mvxcvi/health-tracker "0.1.0-SNAPSHOT"
  :description "A health tracking and planning webapp."
  :url "https://github.com/greglook/health-tracker"
  :license {:name "Public Domain"
            :url "http://unlicense.org/"}

  :dependencies
  [[ch.qos.logback/logback-classic "1.1.2"]
   [clj-time "0.9.0"]
   [compojure "1.3.1"]
   [com.stuartsierra/component "0.2.2"]
   [environ "1.0.0"]
   [hiccup "1.0.5"]
   [org.clojure/clojure "1.6.0"]
   [org.clojure/tools.logging "0.3.1"]
   [org.slf4j/jul-to-slf4j "1.7.6"]
   [ring/ring-core "1.3.2"]
   [ring/ring-jetty-adapter "1.3.2"]
   [ring-middleware-format "0.4.0"]]

  :profiles {:repl [{:source-paths ["dev"]
                     :dependencies [[org.clojure/tools.namespace "0.2.8"]]}
                    :local]})
