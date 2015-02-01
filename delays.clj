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

(def vec [(delay (withdraw melissa 1)), (delay (withdraw melissa 1))])
(doseq [i (range 10)]
  (future (for [x vec] @x)))
