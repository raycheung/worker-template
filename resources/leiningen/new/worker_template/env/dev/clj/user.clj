(ns user
  (:require [{{name}}.config :refer [env]]
            [clojure.spec.alpha :as s]
            [expound.alpha :as expound]
            [mount.core :as mount]
            [{{name}}.core :refer [start-app]]))

(alter-var-root #'s/*explain-out* (constantly expound/printer))

(defn start []
  (mount/start-without #'{{name}}.core/repl-server))

(defn stop []
  (mount/stop-except #'{{name}}.core/repl-server))

(defn restart []
  (stop)
  (start))
