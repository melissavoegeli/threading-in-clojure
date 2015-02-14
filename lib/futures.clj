(defprotocol IAccount
  (balance [this])
  (withdraw [this val])
  (deposit [this val]))
(deftype Account [^:volatile-mutable checking]
  IAccount
  (balance [this] (. this checking))
  (withdraw [this val] ( if (<= val checking)
                            (set! checking (- checking val))
                            (println "Insufficient funds!\n")))
  (deposit [this val] (set! checking (+ checking val))))
(def your-name (Account. 5))


; create 10 futures to perform withdrawals

; Load this file in your repl multiple times to see if your results are consistent

; Print the balance at the end of your 10 threads executing
