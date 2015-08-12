(ns workflow.controller
 (:use
       compojure.core
       workflow.services.workflowService
       workflow.util.utility)
 (:require [clojure.data.json :as json]
           [compojure.handler     :as handler]
           [compojure.route       :as route]))


(defroutes app-routes
           (GET "/" [] (workflow))
           (GET "/workflow" [] (workflow)))

(def app
             (handler/site app-routes))
