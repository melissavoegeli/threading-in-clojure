## Writing your first threaded program

### Futures and Delays

#### Our Feature, the Bank Account

Lets walk through our list of why the community generally advises you to not to use threading as your first choice for solving a problem. In particular, lets see why you shouldn't share mutable data across threads.

As we alluded to before, we're going to use the example of a bank account. We'll be going out of our way to use Java APIs, which means this is not a typical Clojure approach to this problem. But now you'll know how to make Java classes in Clojure so you're welcome.

Bank accounts have a running balance that (ideally) should never drop below $0. Normal things that you can do with a bank account are 1) check the balance at a certain point in time, 2) withdraw money from it, and 3) make deposits. These are the features we will be focussing on.

#### Futures

The first way we're going to use concurrency is with futures.

  *Futures* allow you to shove off work into another thread.

    1 (defprotocol IAccount
    2   (balance [this])
    3   (withdraw [this val])
    4   (deposit [this val]))
    5 (deftype Account [^:volatile-mutable checking]
    6   IAccount
    7   (balance [this] (. this checking))
    8   (withdraw [this val] ( if (<= val checking)
    9                             (set! checking (- checking val))
    10                            (print "Insufficient funds!\n")))
    11  (deposit [this val] (set! checking (+ checking val))))
    12 (def a (Account. 5))

    > git checkout futures

Above we have created an account data type that keeps track of our checking account balance.
You can query the balance, and make deposits and withdrawals. Keep in mind this is a very Java approach to this problem.

_TIP: You can use Account to base your future iterations of solving this problem off of. Most of the logic will not change, just message passing with different threading constructs._

If we run:

    > (balance a)

We get 5, or essentially $5.

    > (deposit 10)

We get $15.

    > (withdraw 10)

We get $5 again.

Lets create 10 different threads that all try to withdraw $1 from our account at the same time. What will happen?

    (doseq [i (range 10)]
      (future ((withdraw a 1))))
    (balance a)
