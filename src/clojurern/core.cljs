(ns clojurern.core
  (:require [steroid.rn.core :as rn]
            [steroid.views :as views]
            [re-frame.core :as re-frame]
            [steroid.rn.navigation.core :as rnn]
            [steroid.rn.navigation.stack :as stack]
            [steroid.rn.navigation.bottom-tabs :as bottom-tabs]
            [clojurern.views :as screens]
            [steroid.rn.navigation.safe-area :as safe-area]
            [steroid.rn.components.touchable :as touchable]
            clojurern.events
            clojurern.subs))

(defn main-screens []
  [bottom-tabs/bottom-tab
   [{:name      :home
     :component screens/home-screen}
    {:name      :basic
     :component screens/basic-screen}
    {:name      :ui
     :component screens/ui-screen}
    {:name      :list
     :component screens/list-screen}
    {:name      :storage
     :component screens/storage-screen}]])

;; (views/defview root-comp []
;;   (views/letsubs [counter [:counter]]
;;      [rn/view {:style {:align-items :center :justify-content :center :flex 1}}
;;       [rn/text (str "Counter: " counter)]
;;       [touchable/touchable-opacity {:on-press #(re-frame/dispatch [:update-counter])}
;;        [rn/view {:style {:background-color :gray :padding 5}}
;;         [rn/text "Update counter"]]]]))

(defn root-stack []
  [safe-area/safe-area-provider
   [(rnn/create-navigation-container-reload
     {:on-ready #(re-frame/dispatch [:init-app-db])}
     [stack/stack {:mode :modal :header-mode :none}
      [{:name      :main
        :component main-screens}
       {:name      :modal
        :component screens/modal-screen}]])]])

(defn init []
  ;; (re-frame/dispatch [:init-app-db])
  (rn/register-reload-comp "clojurern" root-stack))
