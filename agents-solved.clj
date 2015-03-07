(def my-account (agent {:checking 5}))

(defn balance "Print out the balance of the account" [account-hash] (:checking account-hash))

(defn withdrawal
      "Subtract from checking balance"
      [account-hash amount-to-withdraw] (assoc account-hash :checking (- (:checking account-hash) amount-to-withdraw)))

(defn alert-invalid-withdrawl
      "Alert the user that the withdrawl cannot take place"
      [] (println "Insufficient funds!"))

(defn withdraw?
      "Check if balance is valid and withdraw the amount"
      [account-hash amount-to-withdraw] (if (<= amount-to-withdraw (balance account-hash) )
                                          (withdrawal account-hash amount-to-withdraw)
                                          (do
                                            (alert-invalid-withdrawl)
                                            account-hash)))

(defn print-balances
      "Print the value known before the swap! happens if it is different from the known global balance"
      [known-balance account-changes]
          (if-not (= known-balance (balance my-account))
          (println (str "Known Balance Beginning of Swap: $" known-balance ", Actual Global Balance: $" (balance @my-account)))))

(defn sleep-withdraw-print
      "Sleep 1 second then make a withdrawl and print out the balance known before and after"
      [account-hash amount-to-withdraw]
        (let [known-balance (balance account-hash)]
          (Thread/sleep 1000)
          (let [account-changes (withdraw? account-hash amount-to-withdraw)]
               (print-balances known-balance account-changes)
               account-changes)))

(defn send-values "Calls sleep-withdraw-print on an account atom using swap" [] (send my-account sleep-withdraw-print 1))

(defn loop-over
     "Given an account, withdraw money however many times is specified"
     [account num-of-times amount-to-withdraw]
       (doseq [i (range num-of-times)]
         (do
            (send-values)
            (println "Tried to withdraw $1"))))


(loop-over my-account 10 1)
