(defproject mvxcvi/body-app "0.1.0-SNAPSHOT"
  :description "A health tracking and planning webapp."
  :url "https://github.com/greglook/health-tracker"
  :license {:name "Public Domain"
            :url "http://unlicense.org/"}

  :plugins
  [[lein-environ "1.0.0"]]

  :dependencies
  [[ch.qos.logback/logback-classic "1.1.3"]
   [clj-time "0.9.0"]
   [compojure "1.3.3"]
   [com.cemerick/friend "0.2.1" :exclusions [org.clojure/core.cache]]
   [com.stuartsierra/component "0.2.3"]
   [environ "1.0.0"]
   [hiccup "1.0.5"]
   [metosin/ring-http-response "0.6.1"]
   [org.clojure/clojure "1.7.0-beta1"]
   [org.clojure/tools.logging "0.3.1"]
   [prismatic/schema "0.4.2"]
   [ring/ring-core "1.3.2"]
   [ring/ring-jetty-adapter "1.3.2"]
   [ring-middleware-format "0.5.0"]]

  :profiles {:repl {:source-paths ["dev"]
                    :dependencies [[org.clojure/tools.namespace "0.2.10"]]
                    :jvm-opts ["-DBODY_LOG_APPENDER=repl"]}

             :test {:jvm-opts ["-DBODY_LOG_APPENDER=nop"]}})
