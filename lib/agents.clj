; Create the initial agent
____

(defn balance [] (:checking @a))

(defn withdraw [account val]
    (let [known-balance (balance)]
      (Thread/sleep 1000)
      (let [new-account (if (<= val (:checking account) )
                            (assoc account :checking (- (:checking account) val))
                            (do
                              (println "Insufficient funds!")
                              account))]
           (println (str "Known Balance: " known-balance ", Current Balance: " (balance new-account)))
           new-account)))
(doseq [i (range 10)]
 (do
    ____ ; Show that we asynchronously send of work to our agent
    (println "Tried to withdraw $1")))

(balance)
