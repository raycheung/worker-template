(defproject {{name}} "SNAPSHOT"
  :dependencies [[ch.qos.logback/logback-classic "1.2.3"]
                 [cheshire "5.8.1"]
                 [circleci/rollcage "1.0.194"]
                 [com.cognitect/transit-clj "0.8.313"]
                 [com.microsoft.sqlserver/mssql-jdbc "7.2.2.jre11"]
                 [com.novemberain/langohr "5.1.0"]
                 [conman "0.8.3"]
                 [cprop "0.1.13"]
                 [mount "0.1.16"]
                 [nrepl "0.6.0"]
                 [org.clojure/clojure "1.10.1"]
                 [org.clojure/core.async "0.4.500"]
                 [org.clojure/spec.alpha "0.2.176"]
                 [org.clojure/test.check "0.10.0-alpha3"]
                 [org.clojure/tools.logging "0.4.1"]]
  ; for compatibility with JDK 11
  ; https://www.deps.co/blog/how-to-upgrade-clojure-projects-to-use-java-11/#java-util-collection-toarray
  :managed-dependencies [[org.clojure/core.rrb-vector "0.0.14"]]

  :main ^:skip-aot {{name}}.core
  :target-path "target/%s"

  :plugins [[lein-kibit "0.1.5"]]

  :profiles
  {:uberjar {:omit-source true
             :aot :all
             :uberjar-name "{{name}}.jar"
             :source-paths ["env/prod/clj"]
             :resource-paths ["env/prod/resources"]}

   :dev           [:project/dev]
   :test          [:project/dev :project/test]

   :project/dev  {:dependencies [[expound "0.7.2"]
                                 [pjstadig/humane-test-output "0.9.0"]
                                 [prone "1.6.3"]]
                  :plugins      [[com.jakemccrary/lein-test-refresh "0.23.0"]]
                  :source-paths ["env/dev/clj"]
                  :resource-paths ["env/dev/resources"]
                  :repl-options {:init-ns user}
                  :injections [(require 'pjstadig.humane-test-output)
                               (pjstadig.humane-test-output/activate!)]}
   :project/test {:resource-paths ["env/test/resources"]}})
