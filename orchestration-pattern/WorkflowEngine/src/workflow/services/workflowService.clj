;;
;; workflow Service
;;
(ns workflow.services.workflowService
  (:require [flatland.ordered.map          :as collection]
            [clj-http.client               :as client]
            [workflow.conf.config          :as conf]
            [clojure.tools.logging         :as log]))

(def params {:name "prayagupd" :amount 120000})

(defn getRequest [endpoint]
  (client/get endpoint {:accept :json}))

(defn postRequest [endpoint params]
  (client/post endpoint {:form-params params}))

(defn workflow []
  (println "number of components in workflow : " (count (conf/development)))
  (doseq [key (keys (conf/development))]
    (println " " key)
    (println "====================== workflow step "  key " "  (get (conf/development) key) " =========== ")
     (println (postRequest (get (conf/development) key) params))
     (println ""))

   (println "PAYMENT FLOW COMPLETED")
   {:body {:status "COMPLETED"}}
  )



