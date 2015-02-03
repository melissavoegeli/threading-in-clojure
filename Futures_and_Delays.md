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

#### Programming Exercise

Lets create 10 different threads that all try to withdraw $1 from our account at the same time.  To get started fill out the blanks in `lib/futures.clj`. What happened when you ran the program multiple times?

> _TIP: skip ahead to an implementation of this answer with `git checkout futures-solved`_

##### Explanation

Our program didn't behave consistently.

When _thread #1_ is checking if the value to be withdrawn is still less than the current checking balance on `line 8`, it proceeds to `line 9` to deduct the amount. Keep in mind that the time it takes for _thread #1_ to get from `line 8` to `line 9,` _thread #2_ could have successfully withdrawn the last dollar already!

This is why it is bad to share our state in several threads, and what was aforementioned as a **race condition**. Running your program multiple times will result in inconsistent results each time it is executed.

In most other languages the way to fix this issue is to block access to our checking account when it is in use by another thread. When _thread #1_ is checking if the balance is valid in order to continue making the withdrawal, we can lock the account down until we're finished. When the account is locked, no other thread will read or write to it. The bad thing is that it will slow down PERFORMANCE!

> **Blocking:** Waiting for an operation to finish before continuing with your work. A common way to fix race conditions.

You can lock shared in data Clojure.

~~~clojure
    (locking shared-data (some-work-performed-on-shared-data))
~~~

Rewrite your solution to use this mechanism. Did it work?

> _TIP: skip ahead to an implementation of locking data with `git checkout futures-locking-solved`_

### Delays

Delays are a multithreading construct that are similar to futures. There is only one small difference.

> **Delay:** A construct that suspends some body of code, evaluating it in another thread only upon demand.

An example of a delay is:
~~~clojure
    (def d (delay (print "Wait for it...")
                  :done))
~~~

As mentioned before, that code is currently suspended (or delayed) until we want to call it. To evaluate the delay, you dereference it just as you would a future.

~~~clojure
    @d ; => "Wait for it...", :done
~~~

When you dereference a delay multiple times it will execute its work more than once. This comes in handy when multiple threads have access to the same delay. Once _thread #1_ dereferences the delay, _thread #2_ will only get the return value and not execute the same body of work again.

What happens when we replace future with delay in or current solution? Rewrite our solution to create a list of delayed work that will be executed by several threads by fixing the example in `lib/delays.clj`.

> _TIP: skip ahead to an implementation of using delays with `git checkout delays-solved`_

### Discussion

Discuss when we would want to use a future and when it would make more sense to use a delay. Can you give any real world application examples? Afterwards continue on to learn about [Atoms](Atoms.md).
