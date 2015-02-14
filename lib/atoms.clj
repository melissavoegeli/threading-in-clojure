; 1. Create an atom the represents the checking balance as a map

; 2. Show that you know how to access the atom's value by dereferncing it

; 3. Create a balance function that takes an account as an argument
;    Print out the balance in a format similar to: Balance $5

(defn balance [account] (str "Balance: $" (:checking @my-account)))
(defn withdraw [account val]
    (let [known-balance (balance account)]
      (Thread/sleep 1000)
      (let [new-account
              ; 4. Create a conditional
              ;     A. When the balance is greater than the value being withdrawn, subtract the amount
              ;     B. When the balance is less than the value being withdraw print "Insufficient funds"
              ;   For each case be sure to return an account
           ]
           (println (str "Known " known-balance ", Current Balance: $" (:checking new-account)))
           new-account)))
; 5. Create a loop that will attempt to withdraw $1 from the account 10 times using `swap!`
;    A. Show that the loop synchronously attempts to change the state of the atom
;    B. Using multiple threads, demonstrate the many collisions of an atom's compare-and-set mechanism

; 6. Load this code in your REPL in order to run it.
;    When it has finished running check the balance
