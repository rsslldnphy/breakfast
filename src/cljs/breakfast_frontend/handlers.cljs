(ns breakfast-frontend.handlers
    (:require [re-frame.core :as re-frame]
              [breakfast-frontend.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-db
  :fetch-rota
  (fn [db _]
    ;; make ajax request
    (js/setTimeout #(re-frame/dispatch [:fetch-rota-success]) 1000)
    (assoc db :loading? true)))

(re-frame/reg-event-db
  :fetch-rota-success
  (fn [db response]
      (let [response [{:host "Russell"
                       :date "Mon Sep 12 2016 20:30:21 GMT+0100 (BST)"}
                      {:host "Anders"
                       :date "Mon Sep 19 2016 20:30:21 GMT+0100 (BST)"}
                      {:host "Mario"
                       :date "Mon Sep 26 2016 20:30:21 GMT+0100 (BST)"}]]
        (assoc db :loading? false :rota response))))

(re-frame/reg-event-db
  :choose-dish
  (fn [db [_ dish]]
      (-> db
          (assoc :dish dish)
          (update :past-dishes (fnil conj ()) dish))))
