(ns mvxcvi.body.web.app
  "This namespace defines a Ring handler binding URL paths to data lookup and
  controller actions."
  (:require
    [clojure.tools.logging :as log]
    (compojure.api
      [core :refer [ANY* GET* POST* context*]]
      [middleware :refer [api-middleware]]
      [routes :as routes]
      [swagger :as swagger])
    [compojure.core :as compojure]
    [hiccup.core :as hiccup]
    [mvxcvi.body.web.middleware :refer :all]
    [mvxcvi.body.web.views.common :refer [index-page]]
    (ring.middleware
      [content-type :refer [wrap-content-type]]
      [format :refer [wrap-restful-format]]
      [keyword-params :refer [wrap-keyword-params]]
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


(defn wrap-middleware
  "Wraps the application handler in common stateless middleware."
  [handler session-key]
  (-> handler
      (wrap-session {:store (cookie/cookie-store {:key session-key})})
      (api-middleware)
      (wrap-request-logger 'mvxcvi.body.server)
      (wrap-resource "public")
      (wrap-content-type)
      (wrap-cache-control #{"text/css" "text/javascript"})
      (wrap-not-modified)
      (wrap-x-forwarded-for)))


(defn api-handler
  "Constructs a new Ring handler implementing the application."
  [controller]
  (compojure/routes
    (compojure/GET "/" []
      (render (index-page)))
    (compojure/context "/api" []
      (routes/api-root
        (swagger/swagger-ui)
        (swagger/swagger-docs)

        (context* "/baz" []
          :tags ["foo"]

          (GET* "/" []
            {:foo :bar}))))))
