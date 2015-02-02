; Create two accounts with refs
____
____

(defn balance [acc] (:checking @acc))
(defn check-and-withdraw [account val]
    (let [known-balance (balance account)]
        (Thread/sleep 1000)
        (let [worked (if (<= val (:checking @account) )
                              ; Alter the ref and return true at the end
                              ____
                              (do
                                (println "Insufficient funds!")
                                false))]
             (println (str "Withdrawal Known Balance: " known-balance ", Current Balance: " (balance account)))
             worked)))
(defn show-and-deposit [account val]
   (let [known-balance (balance account)]
       (Thread/sleep 1000)
       ; Alter the account and return the account at the end
       (let [new-account ____]
            (println (str "Deposit Known Balance: " known-balance ", Current Balance: " (balance new-account)))
            new-account)))

(defn withdraw [acc val]
  (check-and-withdraw acc val))

(defn deposit [acc val]
  (show-and-deposit acc val))

(defn transfer [from to val]
        (Thread/sleep 1000)
        (let [withdrawal-successful? (withdraw from val)]
          (if withdrawal-successful?
             (do
               (deposit to val)
               (println (str "Transfer Successful. From Balance: " (balance from) ", To Balance: " (balance to))))
             (println (str "Transfer Unsuccessful. From Balance: " (balance from) ", To Balance: " (balance to))))))

; Create a thread that contains a transaction to transfer $1 from account a to account b
____
