; Create the initial atom with some starting balance
____

; Create a function that takes an account and prints out Balance: $100 (or whatever the current balance is)
(defn balance [account] ____)

(defn check-and-withdraw [account val]
    (let [known-balance (balance account)]
      (Thread/sleep 1000)
      (let [new-account
              ; ensure our balance is a sufficient amount
                  ; if true, subtract the amount
                  ; if false, print "Insufficient funds!"
               ; return out account in both cases, whether it newly modified one or the same account
           ]
           (println (str "Known " known-balance ", Current Balance: $" (:checking new-account)))
           new-account)))

; Create a function withdraw that will swap a provided atom atom, use the function check-and-withdraw to help
(defn withdraw [account val] ____)

(doseq [i (range 10)]
 (do
    ____ ; Implement to show sycnhronous say of attempting to withdraw money from account (Hint: a print statement at the end can help)
    ____ ; VERSION 2 Afterwards implement to print how an atom's compare-and-set mechanism can cause collisions
))

; Print the remaining balance at the end
