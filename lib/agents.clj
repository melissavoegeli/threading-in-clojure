; create an agent to represent your checking account
____

; create a balance function to check the current balance
____

(defn withdraw [account val]
    (let [known-balance (balance account)]
      (Thread/sleep 1000)
      (let [new-account
            ; rewrite withdraw to work the same was as check-and-withdraw from our previous exercise
           ]
           (println (str "Known " known-balance ", Current Balance: $" (:checking new-account)))
           new-account)))

; create a loop to send work to the agent that with withdraw a known amount
; be sure to include a print statement much like the previous example to demonstrate its asynchronouse property
____

; check the balance at the end
____
