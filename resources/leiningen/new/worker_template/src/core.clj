(ns {{name}}.core
  (:require [{{name}}.nrepl :as nrepl]
            [{{name}}.config :refer [env]]
            [{{name}}.error-tracking :as err]
            [clojure.tools.logging :as log]
            [mount.core :as mount])
  (:gen-class))

(mount/defstate ^{:on-reload :noop} repl-server
  :start
  (when (env :nrepl-port)
    (nrepl/start {:bind (env :nrepl-bind)
                  :port (env :nrepl-port)}))
  :stop
  (when repl-server
    (nrepl/stop repl-server)))

(defn stop-app []
  (doseq [component (:stopped (mount/stop))]
    (log/info component "stopped"))
  (shutdown-agents))

(defn start-app [args]
  (doseq [component (:started (mount/start))]
    (log/info component "started"))
  (err/set-error-handler)
  (.addShutdownHook (Runtime/getRuntime) (Thread. stop-app)))

(defn -main [& args]
  (start-app args))
