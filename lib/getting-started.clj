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

; Change the name of the account to something special
(def your-name (Account. 5))

; Be sure to update your account name everywhere it is used
(println (str "Balance: $" (balance your-name))) ; Balance: $5

(withdraw your-name 1)
(println (str "Balance: $" (balance your-name))) ; Balance: $4

(deposit your-name 100)
(println (str "Balance: $" (balance your-name))) ; Balance: $104

; Load this file in your repl

; See what happens if you try to withdraw more money than your balance currently has
