(ns flexiana-task-ui.component.footer)


(defn footer
  []
  [:footer
   [:img.footer-logo {:src "img/footer-logo.png" 
                      :alt "Scramble logo"}]
   [:div.footer__text "The best scramble service ever. Let's scramble it!"]])