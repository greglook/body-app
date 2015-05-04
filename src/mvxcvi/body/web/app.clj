(ns mvxcvi.body.web.app
  "This namespace defines a Ring handler binding URL paths to data lookup and
  controller actions."
  (:require
    [clojure.tools.logging :as log]
    (compojure
      [core :as compojure :refer [context GET PUT POST]]
      [route :as route])
    [hiccup.core :as hiccup]
    [mvxcvi.body.web.middleware :refer :all]
    [mvxcvi.body.web.views.common :refer [index-page]]
    (ring.middleware
      [content-type :refer [wrap-content-type]]
      [format :refer [wrap-restful-format]]
      [http-response :refer [wrap-http-response]]
      [keyword-params :refer [wrap-keyword-params]]
      [nested-params :refer [wrap-nested-params]]
      [not-modified :refer [wrap-not-modified]]
      [params :refer [wrap-params]]
      [resource :refer [wrap-resource]]
      [session :refer [wrap-session]])
    [ring.middleware.session.cookie :as cookie]
    [ring.util.http-response :as response]))


(defn render
  "Renders a Hiccup template data structure to HTML and returns a response with
  the correct content type."
  [template]
  (-> template
      (hiccup/html)
      (response/ok)
      (response/content-type "text/html")
      (response/charset "utf-8")))


; Don't forget routes can be separated out into other functions


(defn route-handler
  "Constructs a new Ring handler implementing the application."
  [controller]
  (compojure/routes
    (GET "/" []
      (render (index-page)))

    (route/not-found "Not Found")))


(defn wrap-middleware
  "Wraps the application handler in common stateless middleware."
  [handler]
  (-> handler
      (wrap-session {:store (cookie/cookie-store)})
      (wrap-http-response)
      (wrap-restful-format)
      (wrap-keyword-params)
      (wrap-nested-params)
      (wrap-params)
      (wrap-request-logger 'mvxcvi.body.server)
      (wrap-resource "public")
      (wrap-content-type)
      (wrap-cache-control #{"text/css" "text/javascript"})
      (wrap-not-modified)
      (wrap-x-forwarded-for)))
