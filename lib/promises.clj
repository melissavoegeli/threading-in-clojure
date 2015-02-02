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
  (let [p (promise)]
    ; Create a thread that will swap the account like in previous examples
    ; Also deliver and return the promise at the end
    ____)

(let [promises (map
                 (fn [x]
                    (println "Tried to withdraw $1")
                    (withdraw 1)
                    ) (range 10))]
    ; Loop over all promises and dereference. This should block until you're able to deference all promises.
    ____)

    (println (str "Ending Balance: " (balance))))
