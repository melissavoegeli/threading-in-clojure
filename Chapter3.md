## Futures and Delays

### Futures

We just ran something similar to:

    (doseq [i (range 10)]
      (future ((withdraw melissas-account 1))))
    (balance melissas-account)

   _TIP: skip ahead to this step with `git checkout futures`_

When thread 1 is checking if the value to be withdrawn is still less than the current balance in the checking account on line 8, it proceeds to line 9 to deduct the amount. However, in the time it takes for thread 1 to get from line 8 to line 9, thread 2 could have successfully withdrawn the last dollar!

This is why it is bad to share our state in 10 different threads, and what is referred to as a race condition. Running your program multiple times has will result in inconsistent results each time it is executed.

In most other languages the way to fix this issue is to lock down our checking account balance when it is being used by a thread. When thread 1 is checking  if the balance is valid in order to continue making the withdrawal, we can lock the account down until we're finished. When the account is locked, no other thread can read or write to it. The bad thing is that it will slow down PERFORMANCE!

   *Blocking* Waiting for an operation to finish before continuing with your work.

Which brings us to rule #4.

    (doseq [i (range 10)]
      (future (locking melissas-account ((withdraw melissas-account 1)))))
    (balance melissas-account)

### Delays

   *Delay* A construct that suspends some body of code, evaluating it in another thread only upon demand.

What happens when we replace future with delay?

(Add example of adding delays to a list and executing them later)


### Differences

Discuss when we would use a delay versus a future.
