(def a (atom {:checking 5}))
(defn check-and-withdraw [account val]
    (let [known-balance (balance)]
      (Thread/sleep 1000)
      (let [new-account (if (<= val (:checking account) )
                            (assoc account :checking (- (:checking account) val))
                            (do
                              (println "Insufficient funds!")
                              account))]
           (println (str "Known Balance: " known-balance ", Current Balance: " (:checking new-account)))
           new-account)))
(defn withdraw [val] (swap! a check-and-withdraw val))

(defn balance [] (:checking @a))
(doseq [i (range 10)]
 (do
    (future (withdraw 1)) ; this shows up the synchronousness of compare-and-set
    (println "Tried to withdraw $1")))

(balance)
