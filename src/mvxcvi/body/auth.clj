(ns mvxcvi.body.auth
  "This namespace defines a protocol for authentication providers used to
  verify user credentials.")


(defprotocol AuthenticationProvider
  "Protocol which provides authentication for username/password credentials."

  (authenticate
    [provider credentials]
    "Checks the provided credentials map and returns a map of identification
    data if the credentials are valid."))


(defrecord AuthenticationMap
  [users]

  AuthenticationProvider

  (authenticate
    [this {:keys [username password]}]
    (when-let [user-record (get users username)]
      (when (= password (:password user-record))
        (-> user-record
            (assoc :identity username)
            (dissoc :password))))))
