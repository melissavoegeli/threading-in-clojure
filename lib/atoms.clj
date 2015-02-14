; create an agent to represent your checking account
(def my-account (atom {:checking 0}))

; create a balance function when given the account, checks the current balance
(defn balance [acc] (:checking acc))

(defn withdraw [account val]
    (let [known-balance (balance account)]
      (Thread/sleep 1000)
      (let [new-account
            (if (<= val known-balance)
            (do (println "Insufficient funds!")
                account)
            (do (println "Insufficient funds!")
                account))]
           (println (str "Known " known-balance ", Current Balance: $" (:checking new-account)))
           new-account)))
(doseq [i (range 10)]
 (future (withdraw my-account 1)))
; create a loop to send work to the agent that with withdraw a known amount
; VERSION 1: Implement to show sycnhronousness by including a print statement at the end
; VERSION 2: Afterwards implement to print how an atom's compare-and-set mechanism can cause many collisions
