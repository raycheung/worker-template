(ns {{name}}.error-tracking
  (:require [{{name}}.config :refer [env]]
            [circleci.rollcage.core :as rollcage]
            [mount.core :as mount]))

(mount/defstate rollbar-client
  :start (rollcage/client (env :rollbar-access-token)
                          {:framework "mount"
                           :environment (or (env :rollbar-environment)
                                            (if (env :prod) "production")
                                            "development")}))

(defn report [e]
  (rollcage/error rollbar-client e))

(defn set-error-handler []
  (rollcage/setup-uncaught-exception-handler rollbar-client))
