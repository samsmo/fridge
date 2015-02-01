(ns fridge.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [cljs.core.async :refer [put! chan <!]]))

(enable-console-print!)

(def app-state
  (atom
    {:contacts
     [{:first "Stephanie" :last "Mitchell" :middle "Yun-Ju" :email "smitchell@peemail.com"}
      {:first "Bob" :last "Shoebox" :email "bobshoeb0x@yahoo.com"}
      {:first "Muriel" :last "Nowhere" :middle-initial "p" :email "nil" }
      {:first "Crizz" :last "Demp" :email "crzySkater@yahoo.com"}
      {:first "Mack" :last "Howeel" :email "mhowell@hugeinc.com"}]}))

(defn middle-name [{:keys [middle middle-initial]}]
  (cond
    middle (str " " middle)
    middle-initial (str " " middle-initial ".")))

(defn display-name [{:keys [first last] :as contact}]
  (str last ", " first (middle-name contact)))

(defn contact-view [contact owner]
  (reify
    om/IRenderState
    (render [this]
      (dom/li nil
         (dom/span nil (display-name contact))
         (dom/button #js {:onClick (fn[e] (put! delete contact))} "Delete")))))

(defn contacts-view [app owner]
  (reify
    om/IInitState
    (init-state [_]
      {:delete (chan)})
    om/IWillMount
    (will-mount [_]
      (let [delete (om/get-state owner :delete)]
        (go (loop []
              (let [contact (<! delete)]
                (om/transact! app :contacts
                    (fn [xs (vec  (remove #(= contact %) xs))]))
                (recur))))))
    om/IRenderState
    (render-state [this {:keys [delete]}]
      (dom/div nil
          (dom/h2 nil "Contact List")
          (apply dom/ul nil
            (om/build-all contact-view (:contacts app)
                          {:init-state {:delete delete}}))))))

(om/root contacts-view app-state
         {:target (. js/document (getElementById "contacts"))})
