(ns mvxcvi.body.web.views.common
  (:require
    [cemerick.friend :as friend]
    [hiccup.core :as hiccup]))


;; ## Page Layout Components

(defn- head
  "Generates an html <head> section."
  [title & extra]
  [:head
   [:title (str "Body" (when title (str \space title)))]
   [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
   [:link {:rel "stylesheet" :href "/css/bootstrap.min.css" :media "screen"}]
   [:link {:rel "stylesheet" :href "/css/bootstrap-theme.min.css"}]
   [:link {:rel "stylesheet" :href "/css/octicons.css"}]
   [:link {:rel "stylesheet" :href "/css/site.css"}]
   [:link {:rel "shortcut icon" :href "/favicon.ico"}]
   extra])


(defn- navbar
  "Navigation top bar."
  []
  [:div.navbar.navbar-inverse.navbar-fixed-top {:role "navigation"}
   [:div.container
    [:div.nav.navbar-nav.navbar-right
     [:ul.nav.navbar-nav
      (if-let [auth (friend/current-authentication)]
        [:li [:a#logout {:href "/logout", :title "Logout"} (:identity auth)]]
        [:li [:a#login  {:href "/login", :title "Login"} "Login"]])]]
    [:div.navbar-header
     [:a.navbar-brand {:href "/"} "Body"]]
    [:div.navbar-collapse.collapse
     [:ul.nav.navbar-nav
      [:li [:a {:href "/"} "Build History"]]
      [:li [:a {:href ("trigger")} "New Build"]]]]]])


(defn- layout
  "Generates an html page based on the standard template."
  [head-content & body-content]
  [:html
   head-content
   [:body {:role "document"}
    (navbar)
    [:div.container {:role "main"} body-content]
    [:script {:src "/js/jquery.min.js"}]
    [:script {:src "/js/bootstrap.min.js"}]
    [:script {:src "/js/site.js"}]]])



;; ## Page Views

(defn index-page
  "Generate a hiccup document for the index page."
  [queues recent-builds]
  (layout
    (head "Status")
    [:div.page-header
     [:h1 "Body"]]))
