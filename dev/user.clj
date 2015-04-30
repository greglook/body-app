(ns user
  (:require
    (clj-time
      [coerce :as coerce-time]
      [core :as time]
      [format :as format-time])
    [clojure.java.io :as io]
    [clojure.repl :refer :all]
    [clojure.stacktrace :refer [print-cause-trace]]
    [clojure.string :as str]
    [clojure.tools.namespace.repl :refer [refresh]]
    [com.stuartsierra.component :as component]
    [datomic.api :as d]
    [mvxcvi.body.main :as main :refer [system init! start! stop!]]
    [environ.core :refer [env]]))


(defn go!
  "Initializes and starts the system."
  []
  (init!)
  (start!))


(defn reload!
  "Reloads all changed namespaces to update code, then re-launches the system."
  []
  (stop!)
  (refresh :after 'user/go!))


(defn init-database
  "Creates and initializes a database for the given URI. If the database did
  not exist previously, transaction data will be read and executed from the
  given resources."
  [uri & sources]
  (if (d/create-database uri)
    (let [conn (d/connect uri)]
      (doseq [source sources]
        (let [tx (read-string (slurp source))]
          ; transact returns a future, which we deref to make sure any failures
          ; are thrown as exceptions.
          (force (d/transact conn tx))))
      :created)
    :existed))
