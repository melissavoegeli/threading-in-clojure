; Create the initial atom
____

; Fill out the logic that associates a new value for our checking account balance
(defn check-and-withdraw [account val]
    (let [known-balance (balance)]
      (Thread/sleep 1000)
      (let [new-account (if (<= val (:checking account) )
                            ____
                            (do
                              (println "Insufficient funds!")
                              account))]
           (println (str "Known Balance: " known-balance ", Current Balance: " (:checking new-account)))
           new-account)))

; Write a way to swap a given atom
(defn withdraw [val] ____)

(defn balance [] (:checking @a))
(doseq [i (range 10)]
 (do
    ____ ; Implement to show sycnhronous say of attempting to withdraw money from account
    ____ ; VERSION 2 Afterwards implement again to print how an atom's compare-and-set mechanism can cause collisions
    (println "Tried to withdraw $1")))

(balance)
