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
(def your-name (Account. 5))




(pmap (fn [x] (withdraw your-name 1)) (range 10))

(balance your-name)
