(def a (atom {:checking 5}))
(defn balance [] (:checking @a))

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
(defn withdraw [val]
  (let [promi (promise)]
    (future
          (swap! a check-and-withdraw val)
          (deliver promi a))
    promi))

(let [promises (map
                 (fn [x]
                    (println "Tried to withdraw $1")
                    (withdraw 1)
                    ) (range 10))]
    (doseq [p promises]
     @p)
     
    (println (str "Ending Balance: " (balance))))
