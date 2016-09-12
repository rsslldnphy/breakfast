(ns breakfast-frontend.views
  (:require [re-frame.core :as re-frame]))

(defn loading-page
  []
  [:div.container
   [:div.col-md-4.col-md-offset-4
    [:div [:h1 "loading!!!"]]]])

(defn main-panel []
  (let [loading?    (re-frame/subscribe [:loading?])
        rota        (re-frame/subscribe [:rota])
        dish        (re-frame/subscribe [:dish])
        past-dishes (re-frame/subscribe [:past-dishes])]
    (fn []
        (if @loading?
          [loading-page]
          [:div.container
           [:div.col-md-4
            [:hr]
            [:h4 "What have we had recently?"]
            [:ul {:style {:list-style "none" :padding-left "0"}}
                (for [[i dish] (map-indexed vector @past-dishes)]
                     ^{:key [i dish]}
                     [:li {:style {:margin "15px 0"}} [:strong dish]])]]
           [:div.col-md-4
            (let [[{:keys [host date]} & remaining] @rota]
              [:div
               [:h1 "Next up"]
               [:h2 [:em host]]
               (when-let [dish @dish]
                 [:p "is bringing " dish])
               [:p date]
               [:hr]
               [:ul {:style {:list-style "none" :padding-left "0"}}
                (for [{:keys [host date]} remaining]
                     ^{:key date}
                     [:li {:style {:margin "15px 0"}} [:strong host] " - " date])]])]
           [:div.col-md-4
            [:hr]
            [:h4 "What are you bringing?"]
            [:ul {:style {:list-style "none" :padding "0"}}
             (for [dish ["Breakfast Burrito" "Cappuccino and Croissants" "Breakfast Pizza" "Vodka"]]
                  [:li {:style {:margin "15px 0"}}
                   ^{:key dish}
                   [:button.btn.btn-primary {:on-click #(re-frame/dispatch [:choose-dish dish])} dish]])]]]))))
