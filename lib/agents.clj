(defn alert-invalid-withdrawl
      "Alert the user that the withdrawl cannot take place"
      [] (println "Insufficient funds!"))

(defn withdraw?
      "Check if balance is valid and withdraw the amount"
      [account-hash amount-to-withdraw] (if (<= amount-to-withdraw (balance account-hash) )
                                          (!IMPLEMENT-WITHDRAWL! account-hash amount-to-withdraw)
                                          (do
                                            (alert-invalid-withdrawl)
                                            account-hash)))

(defn print-balances
      "Print the value known before the swap! happens if it is different from the known global balance"
      [known-balance account-changes]
          (if-not (= known-balance (balance @my-account-atom))
          (println (str "Known Balance Beginning of Swap: $" known-balance ", Actual Global Balance: $" (balance @my-account-atom)))))

(defn sleep-withdraw-print
      "Sleep 1 second then make a withdrawl and print out the balance known before and after"
      [account-hash amount-to-withdraw]
        (let [known-balance (balance account-hash)]
          (Thread/sleep 1000)
          (let [account-changes (withdraw? account-hash amount-to-withdraw)]
               (print-balances known-balance account-changes)
               account-changes)))

(defn balance "Print out the balance of the account" [account-hash] (:checking account-hash))

(defn loop-over
     "Given an account, withdraw money however many times is specified"
     [account num-of-times amount-to-withdraw]
       (doseq [i (range num-of-times)]
         (do
            ;(!IMPLEMENT-SEND!)
            ;(!IMPLEMENT-SEND-SHOWING-COLLISIONS!)
            (println "Tried to withdraw $1"))))

; 1) Create the agent that references a map whose checking balance is currently $5

; 2) Subtract the amount from the checking balance.  Implement the missing piece of the function.
(defn !IMPLEMENT-WITHDRAWL!
      "Subtract from checking balance"
      [account-hash amount-to-withdraw] ___________________________________________________________________________)

; 3) Demonstrate the synchronousness of withdrawing using atoms
(defn !IMPLEMENT-SEND! "Calls sleep-withdraw-print on an account atom using swap" [] ______________________________________________)

; 4) Demonstrate the collisions that can occur from an atom's "compare-and-set" mechanism using multiple simulataneous withdrawls
(defn !IMPLEMENT-SEND-SHOWING-COLLISIONS! "Does the same thing as !IMPLEMENT-WITHDRAWL! except in multiple threads" [] _______________________________________________________)

; 5) Print the known balance before, then use loop-over to withdraw money multiple times
