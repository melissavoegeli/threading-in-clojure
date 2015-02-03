## Writing your first threaded program

### Our Feature, the Bank Account

While we're learning about writing multiple threads in Clojure, we'll be focusing on a single feature that we'll implement in several different ways. For our first example we'll be going out of our way to use Java APIs, which means this is not a typical Clojure approach to this problem.

Here is a quick explanation of our feature:

    Bank accounts have a running balance that (ideally) should never drop below $0. Normal things that you can do with a bank account are:
      1. check the balance at any point in time
      2. withdraw money
      3. deposit money

These are the features we will be focussing on for our bank account.  We're going to get started with some Java API code that defines an account interface and account class.  Don't worry too much about what is going on in the implementation.

~~~clojure
    1 (defprotocol IAccount
    2   (balance [this])
    3   (withdraw [this val])
    4   (deposit [this val]))
    5 (deftype Account [^:volatile-mutable checking]
    6   IAccount
    7   (balance [this] (println (str "Balance: $" (. this checking))))
    8   (withdraw [this val] ( if (<= val checking)
    9                             (set! checking (- checking val))
    10                            (print "Insufficient funds!\n")))
    11  (deposit [this val] (set! checking (+ checking val))))
    12 (def your-names-account (Account. 5))
~~~

Above we have created an account class that keeps track of our checking account balance.  You can query the balance as well as make deposits and withdrawals. Keep in mind this is a very Java approach to this problem.

To give this code a try, start your Clojure repl and load up our `getting-started.clj` file that resides in the `lib` directory.

`lein repl; (load-file "/path/to/lib/getting-started.clj")`

If we run:

`(balance a)`

We get $5.

`(deposit 10)`

We get $15.

`(withdraw 10)`

We get $5 again.

Now that we have an idea of what we're going to be creating, lets move on to [Futures and Delays](Futures_and_Delays.md).
