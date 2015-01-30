## Writing your first threaded program

### Futures and Delays

#### Our Feature, the Bank Account

Lets walk through our list of why the community generally advises you to not to use threading as your first choice for solving a problem. In particular, lets see why you shouldn't share mutable data across threads.

As we alluded to before, we're going to use the example of a bank account. We'll be going out of our way to use Java APIs, which means this is not a typical Clojure approach to this problem. But now you'll know how to make Java classes in Clojure so you're welcome.

Bank accounts have a running balance that (ideally) should never drop below $0. Normal things that you can do with a bank account are 1) check the balance at a certain point in time, 2) withdraw money from it, and 3) make deposits. These are the features we will be focussing on.


#### Futures

The first way we're going to use concurrency is with futures.

    *Futures* allow you to shove off work into another thread.
