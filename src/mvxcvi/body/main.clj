(ns mvxcvi.body.main
  "Main entry-point for launching the application service. The system of
  components is configured using environment variables.

  #### Environment Variables

  `BIND_INTERFACE`
  Network interface to bind the HTTP server to. Defaults to `\"localhost\"`.

  `PORT`
  TCP port the HTTP server will bind to. Defaults to `8080`.

  `SERVER_URL`
  Base server URL for links."
  (:gen-class)
  (:require
    [clojure.tools.logging :as log]
    [com.stuartsierra.component :as component]
    [environ.core :refer [env]]
    (mvxcvi.body.web
      [app :as app]
      [server :as server])))


(def system nil)


(defn init!
  "Initialize the system for standalone operation."
  []
  (log/info "Initializing main system from environment variables...")
  (let [interface (env :bind-interface "localhost")
        port (Integer/parseInt (env :port "8080"))
        server-url (env :server-url (str "http://localhost:" port))]
    (alter-var-root #'system
      (constantly
        (component/system-map
          :server
          (server/jetty-server
            (comp app/wrap-middleware app/route-handler)
            :host interface
            :port port
            :min-threads 2
            :max-threads 5
            :max-queued 25)))))
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
