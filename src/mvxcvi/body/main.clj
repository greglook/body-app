(ns mvxcvi.body.main
  "Main entry-point for launching the application service. The system of
  components is configured using environment variables."
  (:gen-class)
  (:require
    [clojure.tools.logging :as log]
    [com.stuartsierra.component :as component]
    [environ.core :refer [env]]))


(def system nil)


(defn init!
  "Initialize the system for standalone operation."
  []
  (log/info "Initializing main system from environment variables...")
  (alter-var-root #'system
    (constantly
      (let [port (Integer/parseInt (env :port "8080"))
            server-url (env :server-url (str "http://localhost:" port))]
        (component/system-map
          #_ :web
          #_
          (server/->jetty-server
            :server "0.0.0.0"
            :port port
            :min-threads 2
            :max-threads 5
            :max-queued 25
            :session-key (env :session-key))))))
  :init)


(defn start!
  "Runs the initialized system."
  []
  (when system
    (log/info "Starting main system...")
    (alter-var-root #'system component/start))
  :start)


(defn stop!
  "Halts the running system."
  []
  (when system
    (log/info "Stopping main system...")
    (alter-var-root #'system component/stop))
  :stop)


(defn -main []
  (init!)
  (.addShutdownHook
    (Runtime/getRuntime)
    (Thread. ^Runnable stop! "main system shutdown hook"))
  (start!)
  (log/info "Main system started, entering active mode..."))
