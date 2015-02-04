(defprotocol IAccount
  (balance [this])
  (withdraw [this val])
  (deposit [this val]))
(deftype Account [^:volatile-mutable checking]
  IAccount
  (balance [this] (println (str "Balance: $" (. this checking))))
  (withdraw [this val] ( if (<= val checking)
                            (set! checking (- checking val))
                            (println "Insufficient funds!\n")))
  (deposit [this val] (set! checking (+ checking val))))
(def your-name (Account. 5))

; create a list of 10 delays to perform withdrawals

; execute the list of delays in 10 different threads

; print the balance when you're done
