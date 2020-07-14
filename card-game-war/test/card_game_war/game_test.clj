(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all]))

(defn stack-size [[player1-cards player2-cards]]
  [(count player1-cards) (count player2-cards)])

;; fill in  tests for your game
(deftest test-play-round
  (testing "the highest rank wins the cards in the round"
    (is (= 1 (play-round [:club 5] [:spade :jack]))))
  (testing "queens are higher rank than jacks"
    (is (= 1 (play-round [:heart :jack] [:diamond :queen]))))
  (testing "kings are higher rank than queens"
    (is (= 0 (play-round [:diamond :king] [:heart :queen]))))
  (testing "aces are higher rank than kings"
    (is (= 0 (play-round [:spade :ace] [:heart :king]))))
  (testing "if the ranks are equal, clubs beat spades"
    (is (= 1 (play-round [:spade 7] [:club 7]))))
  (testing "if the ranks are equal, diamonds beat clubs"
    (is (= 0 (play-round [:diamond 9] [:club 9]))))
  (testing "if the ranks are equal, hearts beat diamonds"
    (is (= 0 (play-round [:heart 4] [:diamond 4])))))

(deftest test-play-game
  (testing "the player loses when they run out of cards"
    (is (= 0 (play-game [[:spade 2]] []))))
  (testing "the player with the best cards wins"
    (is (= 0 (play-game [[:spade :ace] [:diamond :king] [:heart 7] [:diamond 9]]
                        [[:heart :jack] [:club :queen] [:club 2] [:heart 10]]))))
  (testing "small but mighty"
    (is (= 0 (play-game [[:spade :ace]]
                        [[:heart :jack] [:club :10] [:diamond :queen] [:spade :king]])))))

