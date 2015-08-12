(ns workflow.controller
 (:use
       compojure.core
       workflow.services.workflowService
       workflow.util.utility)
 (:require
           [compojure.handler     :as handler]
           [ring.middleware.json :as middleware]
           [compojure.route       :as route]))


(defroutes app-routes
           (GET "/" [] (workflow))
           (GET "/workflow" [] (workflow))
           (route/not-found "Not Found"))

(def app
  ( -> (handler/site app-routes)
    (middleware/wrap-json-body {:keywords? true})
    middleware/wrap-json-response))
