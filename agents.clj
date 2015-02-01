(def a (agent {:checking 5}))
(defn withdraw [account val]
    (let [known-balance (balance)]
      (Thread/sleep 1000)
      (let [new-account (if (<= val (:checking account) )
                            (assoc account :checking (- (:checking account) val))
                            (do
                              (println "Insufficient funds!")
                              account))]
           (println (str "Known Balance: " known-balance ", Current Balance: " (:checking new-account)))
           new-account)))

(defn balance [] (:checking @a))
(doseq [i (range 10)]
 (do
    (send a withdraw 1)
    (println "Tried to withdraw $1")))

(balance)
