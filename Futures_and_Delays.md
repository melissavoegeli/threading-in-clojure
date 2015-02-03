## Futures and Delays

### Futures

> **Futures:** Allows you to create a new thread and give it some work to perform.

This is an example of how to create a future that evaluates a body of code in another thread.

~~~clojure
    (def f (future (Thread/sleep 3000)
                   (println "Running after 3 seconds...")
                   :done!)))
~~~

Our global variable `f` will execute a print statement and return the value `:done!`

In order to obtain the value of our future you must _dereference_ it. Here is an example:

~~~clojure
    @f ; => :done!
~~~

We can return `:done!` from our future as many times as we want, but keep in mind that the body of code will only be executed once. Dereferencing a future will block if the code it is evaluating has not completed yet.

***

#### Exercise 5-10m

Lets create 10 different threads that all try to withdraw $1 from our account at the same time.  To get started fill out the blanks in `lib/futures.clj`. What happened when you ran the program multiple times?

> _TIP: skip ahead to an implementation of this answer with `git checkout futures-solved`_

#### Explanation

When thread #1 is checking if the value to be withdrawn is still less than the current balance in the checking account on line 8, it proceeds to line 9 to deduct the amount. However, in the time it takes for thread #1 to get from line 8 to line 9, thread #2 could have successfully withdrawn the last dollar!

This is why it is bad to share our state in 10 different threads, and what is referred to as a race condition. Running your program multiple times will result in inconsistent results each time it is executed.

In most other languages the way to fix this issue is to lock down our checking account balance when it is being used by a thread. When thread 1 is checking if the balance is valid in order to continue making the withdrawal, we can lock the account down until we're finished. When the account is locked, no other thread can read or write to it. The bad thing is that it will slow down PERFORMANCE!

> **Blocking:** Waiting for an operation to finish before continuing with your work.

You can lock shared data by `(locking shared-data (some-work))`. Which brings us to rule #4.

> _TIP: skip ahead to an implementation of locking data with `git checkout futures-locking-solved`_

### Delays

Delays are a multithreading construct that are similar to futures.

> **Delay:** A construct that suspends some body of code, evaluating it in another thread only upon demand.

An example of a delay is:
~~~clojure
    (def d (delay (print "Wait for it...")
                  :done))
~~~

As mentioned before, that code is currently suspended (or delayed) until we want to call it. To evaluate the delay, you dereference it.

~~~clojure
    @d ; => "Wait for it...", :done
~~~

Another thing to note is if you dereference our delay once more it will not print out "Wait for it..." again. This comes in handy when multiple threads have access to the same delay. Once thread #1 dereferences the delay, thread #2 will only get the return value and not execute the same body of work again.

What happens when we replace future with delay in or current solution? It doesn't make sense to delay withdrawing money from an account, unless we're waiting for someone to approve it. Fix our example in `lib/delays.clj`.

> _TIP: skip ahead to an implementation of using delays with `git checkout delays-solved`_

### Differences

Discuss when we would use a delay versus a future. Afterwards continue on to learn about [Atoms](Atoms.md).
