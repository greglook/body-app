(ns user
  (:require
    [clojure.java.io :as io]
    [clojure.repl :refer :all]
    [clojure.string :as str]
    [compojure.core :as compojure]
    [compojure.route :as route]
    [ring.adapter.jetty :as jetty]
    [ring.util.response :as response]
    (ring.middleware
      [content-type :refer [wrap-content-type]]
      [not-modified :refer [wrap-not-modified]]
      [resource :refer [wrap-resource]])))


(def server nil)


(def server-opts
  {:host "localhost"
   :port 3003
   :join? false})


(def static-handler
  (-> (compojure/routes
        (compojure/GET "/" []
          (response/redirect "/app.html"))
        (route/not-found "Not Found"))
      (wrap-resource "public")
      (wrap-content-type)
      (wrap-not-modified)))


(defn start!
  "Initializes and starts the system."
  []
  (if server
    (if-not (.isStarted server)
      (do
        (println "Restarting server...")
        (.start server))
      (println "Server is already started"))
    (do
      (println "Starting server on port " (:port server-opts))
      (alter-var-root #'server
        (constantly
          (jetty/run-jetty static-handler server-opts)))))
  :start)


(defn stop!
  "Stops the server."
  []
  (when (and server (not (.isStopped server)))
    (println "Stopping server...")
    (.stop server))
  :stop)


(defn reload!
  []
  (stop!)
  (require 'user :reload))
