(ns mvxcvi.body.web.middleware
  (:require
    [clojure.string :as str]
    [clojure.tools.logging :as log]
    [ring.util.response :as r]))


(defn wrap-cache-control
  "Ring middleware to add a cache-control header if the response matches
  certain content types."
  [handler cacheable-type? & {:keys [max-age] :or {max-age 300}}]
  (fn [req]
    (let [resp (handler req)]
      (if (cacheable-type? (get-in resp [:headers "Content-Type"]))
        (r/header resp "Cache-Control" (format "public,max-age=%d" max-age))
        resp))))


(defn wrap-request-logger
  "Ring middleware to log information about service requests."
  [handler logger-ns]
  (fn [{:keys [uri remote-addr request-method] :as request}]
    (let [start (System/currentTimeMillis)
          method (str/upper-case (name request-method))]
      (log/log logger-ns :debug nil
               (format "%s %s %s" remote-addr method uri))
      (let [{:keys [status headers] :as response} (handler request)
            elapsed (- (System/currentTimeMillis) start)
            msg (format "%s %s %s -> %s (%d ms)"
                        remote-addr method uri status elapsed)]
        (log/log logger-ns
                 (if (<= 400 status 599) :warn :info)
                 nil msg)
        response))))


(defn wrap-x-forwarded-for
  "Ring middleware to fix the remote address if the request passed through
  proxies on the way to the service.

  This function replaces the request's :remote-addr with the LAST entry in the
  forwarded header. This gives the address of the host which called the service
  endpoint, which may not be the same as the original client."
  [handler]
  (fn [request]
    (if-let [xff (get-in request [:headers "x-forwarded-for"])]
      (let [addrs (str/split xff #"\s*,\s*")]
        (when (> (count addrs) 1)
          (log/trace "Multiple request forwards from"
                     (str/join " -> " addrs)
                     "->" (:remote-addr request)))
        (handler (assoc request :remote-addr (first addrs))))
      (handler request))))