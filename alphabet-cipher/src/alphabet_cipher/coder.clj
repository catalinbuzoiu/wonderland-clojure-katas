(ns alphabet-cipher.coder)


(defn char->pos [ch]
  (- (int ch)
     (int \a)))

(defn pos->char [i]
  (char (+ (mod i 26)
           (int \a))))

(defn encode-letter [col row]
  (pos->char (+ (char->pos col)
                (char->pos row))))

(defn decode-letter [col row]
  (pos->char (- (char->pos col)
                (char->pos row))))

;============================

(defn repeats [str len]
  (loop [i len]
    (if (< i (count str))
      (if (= (nth str i) (nth str (mod i len)))
        (recur (inc i))
        false)
      true)))

(defn shortest-pattern [str & [len]]
  (let [len (or len 1)]
    (if (repeats str len)
      (subs str 0 len)
      (recur str [(inc len)]))))

;============================

(defn encode [keyword message]
  (apply str (map encode-letter message (cycle keyword))))

(defn decode [keyword message]
  (apply str (map decode-letter message (cycle keyword))))

(defn decipher [cipher message]
  (shortest-pattern (apply str (map decode-letter cipher message))))