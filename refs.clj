(def a (ref {:checking 5}))
(def b (ref {:checking 500}))
(defn balance [acc] (:checking @acc))
(defn check-and-withdraw [account val]
    (let [known-balance (balance account)]
        (Thread/sleep 1000)
        (let [worked (if (<= val (:checking @account) )
                              (do (alter account update-in [:checking] - val) true)
                              (do
                                (println "Insufficient funds!")
                                false))]
             (println (str "Withdrawal Known Balance: " known-balance ", Current Balance: " (balance account)))
             worked)))
(defn show-and-deposit [account val]
   (let [known-balance (balance account)]
       (Thread/sleep 1000)
       (let [new-account (do (alter account update-in [:checking] + val) account)]
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

(future (dosync (transfer a b 1)))
