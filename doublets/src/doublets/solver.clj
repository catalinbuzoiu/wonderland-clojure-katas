(ns doublets.solver
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))

(def words (-> "words.edn"
               (io/resource)
               (slurp)
               (read-string)))

(defn n-words [n]
  (filter #(= (count %) n) words))

(defn adj? [w1 w2]
  (= 1 (reduce + (map #(if (= %1 %2) 0 1) w1 w2))))


(defn concat-if-not-empty [coll rest]
  (if (empty? rest)
    []
    (concat coll rest)))

(defn take-out [w words]
  (filter #(not= w %) words))

(defn first-not-empty [coll]
  (first (drop-while empty? coll)))

(defn iter-doublets [word1 word2 words]
  (if (= word1 word2)
    [[word2]]
    (for [w words :when (adj? word1 w)]
      (->>
        (take-out w words)
        (iter-doublets w word2)
        (first-not-empty)
        (concat-if-not-empty [word1])))))

(defn doublets [word1 word2]
  (let [n (count word1)]
    (if (= n (count word2))
      (first (iter-doublets word1 word2 (n-words n)))
      [])))
