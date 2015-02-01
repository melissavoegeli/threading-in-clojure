## Futures and Delays

### Futures

The first way we're going to use concurrency is with futures.

  *Futures* Allows you to create a new thread and give it some work to perform.

This is an example of how to give a future some work to perform.

    (future (println "Running...")
            :done!))

   _TIP: skip ahead to an implementation of this answer with `git checkout futures`_

Lets create 10 different threads that all try to withdraw $1 from our account at the same time. What will happen?

When thread 1 is checking if the value to be withdrawn is still less than the current balance in the checking account on line 8, it proceeds to line 9 to deduct the amount. However, in the time it takes for thread 1 to get from line 8 to line 9, thread 2 could have successfully withdrawn the last dollar!

This is why it is bad to share our state in 10 different threads, and what is referred to as a race condition. Running your program multiple times has will result in inconsistent results each time it is executed.

In most other languages the way to fix this issue is to lock down our checking account balance when it is being used by a thread. When thread 1 is checking  if the balance is valid in order to continue making the withdrawal, we can lock the account down until we're finished. When the account is locked, no other thread can read or write to it. The bad thing is that it will slow down PERFORMANCE!

   *Blocking* Waiting for an operation to finish before continuing with your work.

You can lock shared data by `(locking shared-data (some-work))`. Which brings us to rule #4.

   _TIP: skip ahead to an implementation of locking data with `git checkout futures-locking`_

### Delays

   *Delay* A construct that suspends some body of code, evaluating it in another thread only upon demand.

An example of a delay is:

    (def d (delay (print "Wait for it...")
                  :done))

As mentioned before, that code is currently suspended (or delayed) until we want to call it. To evaluate the delay, you dereference it.

    @d ; => "Wait for it...", :done

What happens when we replace future with delay in or current solution? It doesn't make sense to delay withdrawing money from an account, unless we're waiting for someone to approve it. Implement a solution that creates a vector of delays and multiple threads to dereference them to approve them. What happens?

_TIP: skip ahead to an implementation of using delays with `git checkout delays`_

### Differences

Discuss when we would use a delay versus a future.
