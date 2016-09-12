(ns breakfast-frontend.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 :loading?
 (fn [db]
   (:loading? db)))

(re-frame/reg-sub
 :rota
 (fn [db]
   (:rota db)))

(re-frame/reg-sub
 :dish
 (fn [db]
   (:dish db)))

(re-frame/reg-sub
 :past-dishes
 (fn [db]
   (:past-dishes db)))
