(defproject workflow-engine "0.1.0-SNAPSHOT"
  :description "workflow"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories {"conjars" "http://conjars.org/repo"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [clj-json "0.2.0"]
                 [ring-json-params "0.1.0"]
                 [congomongo "0.4.3"]
                 [clojurewerkz/elastisch "1.4.0"]
                 [ring/ring-json "0.3.1"]
                 [compojure "1.1.8"]
                 [enlive  "1.1.1"]
                 [net.cgrand/moustache "1.1.0"]
                 [org.clojure/tools.logging "0.2.6"]
                 [org.clojure/data.json "0.2.4"]
                 [org.clojure/algo.monads "0.1.5"]
                 ;;;
                 [org.apache.httpcomponents/httpcore "4.3.2"]
                 [org.apache.httpcomponents/httpclient "4.3.3"]
                 [org.apache.httpcomponents/httpmime "4.3.3"]
                 [clojurewerkz/neocons "2.0.1"]
                 [clj-time "0.8.0"]
                 [me.raynes/conch "0.8.0"]
                 [org.flatland/ordered "1.5.3"]
                 [clj-yaml "0.4.0"]]

  :plugins [
            [lein-ring "0.8.10"]
            [lein-sub "0.3.0"]]
  :ring {:handler workflow.controller/app
         ;; hot-reload http://stackoverflow.com/a/14472281/432903
         ;;:auto-reload? true
         ;;:auto-refresh? true
         }
 )
