(ns card-game-war.game)

;; feel free to use these cards or use your own data structure
(def suits [:spade :club :diamond :heart])
(def ranks [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace])
(def cards
  (for [suit suits
        rank ranks]
    [suit rank]))

(defn suitrank [card]
  (+ (* (count suits) (.indexOf ranks (second card)))
     (.indexOf suits (first card))))

(defn play-round [player1-card player2-card]
    (if (> (suitrank player1-card) (suitrank player2-card)) 0 1))

(defn result-round [player1-card player2-card]
  (let [pot [player1-card player2-card]]
    (if (= 0 (play-round player1-card player2-card))
      [pot []]
      [[] pot])))

(defn play-game [player1-cards player2-cards]
  (cond
    (= 0 (count player1-cards)) 1
    (= 0 (count player2-cards)) 0
    :else (let [result (result-round (first player1-cards) (first player2-cards))]
            (recur (into (rest player1-cards) (first result))
                   (into (rest player2-cards) (second result))))))
