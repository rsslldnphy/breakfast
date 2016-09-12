(ns breakfast-frontend.handlers
    (:require [re-frame.core :as re-frame]
              [breakfast-frontend.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))
