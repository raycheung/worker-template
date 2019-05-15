(ns leiningen.new.worker-template
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "worker-template"))

(defn worker-template [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' worker-template project.")
    (->files data
             ["project.clj" (render "project.clj" data)]
             [".gitignore" (render ".gitignore" data)]
             ["README.md" (render "README.md" data)]
             ["Procfile" (render "Procfile" data)]
             ["Dockerfile" (render "Dockerfile" data)]
             ["Capstanfile" (render "Capstanfile" data)]
             ["src/{{sanitized}}/core.clj" (render "src/core.clj" data)]
             ["src/{{sanitized}}/config.clj" (render "src/config.clj" data)]
             ["src/{{sanitized}}/nrepl.clj" (render "src/nrepl.clj" data)]
             ["src/{{sanitized}}/error_tracking.clj" (render "src/error_tracking.clj" data)]
             ["resources/config.edn" (render "resources/config.edn" data)]
             ["resources/logback-common.xml" (render "resources/logback-common.xml" data)]
             ["env/dev/clj/user.clj" (render "env/dev/clj/user.clj" data)]
             ["env/dev/resources/env.edn" (render "env/dev/resources/env.edn" data)]
             ["env/dev/resources/logback.xml" (render "env/dev/resources/logback.xml" data)]
             ["env/prod/resources/env.edn" (render "env/prod/resources/env.edn" data)]
             ["env/prod/resources/logback.xml" (render "env/prod/resources/logback.xml" data)]
             ["env/test/resources/env.edn" (render "env/test/resources/env.edn" data)]
             ["env/test/resources/logback.xml" (render "env/test/resources/logback.xml" data)]
             ["test/{{sanitized}}/core_test.clj" (render "test/core_test.clj" data)])))
