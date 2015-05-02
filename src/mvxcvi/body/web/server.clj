(ns mvxcvi.body.web.server
  "This namespace provides a Jetty component for serving the app."
  (:require
    [clojure.tools.logging :as log]
    [com.stuartsierra.component :as component]
    [ring.adapter.jetty :as jetty])
  (:import
    org.eclipse.jetty.server.Server))


(defrecord JettyServer
  [handler-constructor controller options ^Server server]

  component/Lifecycle

  (start
    [this]
    (if server
      (do
        (if-not (.isStarted server)
          (do
            (log/info "Restarting JettyServer...")
            (.start server))
          (log/info "JettyServer is already started"))
        this)
      (let [handler (handler-constructor controller)
            options (assoc options :join? false)]
        (log/info (str "Starting JettyServer on port " (:port options) "..."))
        (assoc this :server (jetty/run-jetty handler options)))))


  (stop
    [this]
    (log/info "Stopping JettyServer...")
    (when (and server (not (.isStopped server)))
      (.stop server))
    this))


(defn jetty-server
  "Constructs a new web server component with the given options. The first
  argument should be a constructor for a Ring handler function."
  [constructor & {:as options}]
  (map->JettyServer
    {:handler-constructor constructor
     :options options}))
