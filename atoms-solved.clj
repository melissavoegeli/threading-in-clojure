(def my-account (atom {:checking 5}))

(defn balance [account] (str "Balance: $" (:checking @my-account)))
(defn check-and-withdraw [account val]
    (let [known-balance (balance account)]
      (Thread/sleep 1000)
      (let [new-account (if (<= val (:checking account) )
                            (assoc account :checking (- (:checking account) val))
                            (do
                              (println "Insufficient funds!")
                              account))]
           (println (str "Known " known-balance ", Current Balance: $" (:checking new-account)))
           new-account)))
(defn withdraw [account val] (swap! my-account check-and-withdraw val))
(doseq [i (range 10)]
 (do
    (future (withdraw my-account 1)) ; this shows up the synchronousness of compare-and-set
    (println "Tried to withdraw $1")))

(balance my-account)
