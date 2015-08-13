(ns workflow.controller
 (:use
       compojure.core
       workflow.services.workflowService
       workflow.util.utility)
 (:require
           [compojure.handler     :as handler]
           [ring.middleware.json :as middleware]
           [compojure.route       :as route]))

;;(defn json-response [data & [status]]
;;  {:status (or status 200)
;;   :headers {"Content-Type" "application/json"}
;;   :body (json/generate-string data)})

(defroutes app-routes
           (GET "/" [] (workflow)) ;;(json-response {"server" "ready"}))
           (GET "/workflow" [] (workflow))
           (route/not-found "Not Found"))

(def app
  ( -> (handler/site app-routes)
    (middleware/wrap-json-body {:keywords? true})
    middleware/wrap-json-response))
