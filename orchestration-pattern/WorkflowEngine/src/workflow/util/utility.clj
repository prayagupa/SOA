;;
;; http://onclojure.com/2009/03/05/a-monad-tutorial-for-clojure-programmers-part-1/
;;

(ns workflow.util.utility
  (:import [java.util Locale Date]
           [org.joda.time DateTimeZone]
           [org.joda.time.format DateTimeFormat DateTimeFormatter]
           [org.joda.time.tz FixedDateTimeZone]))

(defn displaySourceStream
  "I don't do a whole lot."
  [sourceName]
  (println "Hello," sourceName))

(defn hackMonad []
(let [a  1
      b  (inc a)]
  (* a b)))

;;http://stackoverflow.com/q/10801744/432903
(def array [4 5 6])

(defn nohup-config [service-name] 
  {:service-name service-name
   :run-file {
    :content (str "#!/bin/sh\nexec " service-name)
  }})

;; http://stackoverflow.com/questions/6685916/how-to-iterate-over-map-keys-and-values
(def dbMap {:classname "com.mysql.jdbc.Driver" 
            :subprotocol "mysql" 
            :subname "//10.0.7.40:3306/neleaks" 
            :username "root" 
            :password "mysql55"})
(defn dbMapX []
  (let []))

(defn printDbMap []
  (doseq [keyVal dbMap] (prn keyVal)))


;; http://blog.jayfields.com/2010/07/clojure-destructuring.html
(def book {:name "SICP" 
           :details {:pages 657 
                     :isbn-10 "0262011530"
                    }})

(defn printBook []
(let [{name :name 
       {:keys [pages isbn-10]} :details} book]
     (println "name:" name)
     (println "pages:" pages)
     (println "isbn-10:" isbn-10)))

(defn equality []
 (if (= "staging" (str "stag" "ing_"))
       (let [bucketname "staging-cdn"]
       (println bucketname))
  ) (cond (= "production" "production")
          (let [bucketname "cdn"]
            (println bucketname))))

;(defn equality-iflet []
;   (if-let [(= "staging" (str "stag" "ing"))]
;       ("staging-cdn")
;       ("cdn")))

;;(defn equality-letif []
;;   (let [bucketname
;     (if (= "staging" "staging")
;       ("staging-cdn"))
;     (cond (= "production" "production_")
;       ("cdn"))]))

(def buckets {:production "cdn" 
              :staging "staging-cdn"})

(defn aws-time []
  (let [aws-date-value (java.util.Date.)]
  (println aws-date-value)))

(defn awsdate []
    (let [GMT              (new FixedDateTimeZone "GMT" "GMT" 0 0)
          rfc822           (. DateTimeFormat forPattern "EEE, dd MMM yyyy HH:mm:ss z")
          locale           (. Locale US)
          rfcL             (. rfc822 withLocale locale)
          rfcT             (. rfcL withZone GMT)
          date             (Date.)
          datetime         (. date getTime)
          finalDate        (. rfcT print datetime)]
    (str finalDate)))

(defn get-bucket [env]
  (let [
       bucketname (buckets (keyword env))]
       (println bucketname)))

(defn util []
  ;; (displaySourceStream "leaks-engine")
  ;; (printBook)
  ;;(get-bucket "staging")
  ;; (hackMonad)
  (doseq [keyval (nohup-config "nimbus")] (prn keyval))
  (let [date (awsdate)]
    (println date)))
