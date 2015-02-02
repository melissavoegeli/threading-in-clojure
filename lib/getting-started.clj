(defprotocol IAccount
   (balance [this])
   (withdraw [this val])
   (deposit [this val]))
(deftype Account [^:volatile-mutable checking]
   IAccount
   (balance [this] (. this checking))
   (withdraw [this val] ( if (<= val checking)
                             (set! checking (- checking val))
                             (print "Insufficient funds!\n")))
   (deposit [this val] (set! checking (+ checking val))))
(def melissa (Account. 5))

(balance melissa)) ; $5

(withdraw melissa 1))
(balance melissa) ; $6

(deposit melissa 100))
(balance melissa) ; $106
