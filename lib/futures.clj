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

; 1) Fix this function so it can withdraw some amount repeatedly from your account
(defn create-future [account amount-to-withdraw] ______________________________________________)

(defn loop-over
      "Given an account, withdraw money however many times is specified"
      [account num-of-times amount-to-withdraw]
        (doseq [i (range num-of-times)]
          (create-future account amount-to-withdraw)))

; 2) Create 10 threads to withdraw money by calling the function loop-over

; 3) Print out the balance

; 4) Load this file multiple times in your repl to determine results

(balance your-name)
