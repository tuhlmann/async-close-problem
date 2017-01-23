(ns async-close-problem.core
  (:require [clojure.core.async :as a]))

(defonce channels (atom {}))

;; Problem occurs no matter if using go-loop or while true
(defn start-chan2 []

  (let [channel (a/chan (a/sliding-buffer 5000))]

    (println "Channel started")
    (a/go
      (while true
        (when-let  [document (a/<! channel)]
          (println "New document to render: " (pr-str document)))))

    (swap! channels assoc :render-channel channel)))


(defn start-chan []

  (let [channel (a/chan)]

    (println "Channel started")
    (a/go-loop []
               (when-let  [document (a/<! channel)]
                 (println "New document to render: " (pr-str document)))
               (recur))

    (swap! channels assoc :render-channel channel)))


(defn stop-chan []
  (println "Closing Channel")
  (when-let [channel (:render-channel @channels)]
    (a/close! channel))
  (reset! channels {}))

(defn start-and-stop []
  (start-chan)
  (Thread/sleep 1000)
  (stop-chan))

