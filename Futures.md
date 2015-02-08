## Futures and Delays

### Futures

> **Futures:** Creates a new thread and give it some work to perform.

#### "How do I start a new thread and run some code in it?"

In a language such as Java, you may expect an answer to be along the times of
    1. Create a new Runnable object with some work
    2. Create a Thread object
    3. Submit the Runnable object to the Thread
    4. Start executing

~~~java
    Runnable runnable = ...;
    Thread t = new Thread(runnable);
    t.start();
~~~

We can start off a little simpler than this in Clojure.  A Future is an abstraction that allows you to hide the mechanics of creating and manipulating threads.  

When you use a Future, you have some procedure that you want executed in another thread.  This is an example of how to create a future that evaluates a body of code in another thread.

~~~clojure
    1 (def f (future (Thread/sleep 3000)
    2               (println "3 second delay complete, pretend we're hitting a database")
    3               :result-of-the-work!))
    4    ; => "3 second delay complete, pretend we're hitting a database"
    5  f ; => #<core$future_call$reify__6320@140fedd8: :result-of-the-work!>
    6 @f ; => :result-of-the-work!
    7 @f ; => :result-of-the-work!
~~~

Our global variable `f` will execute a print statement and cache the value `:result-of-the-work!`

`f` is a variable that is storing a reference to our Future. If we peek at the return value of `f` we can see just that.

In order to obtain the return value of the work our future performed, we must _dereference_ it.  `Line 6`  shows that in order to dereference our future we must put an `@` in front on our variable.  

If we choose to dereference our future more than once, it will return `:result-of-the-work!` each time.  The work our future performs will only be executed once because the return value is cached.  If you have a particularly long running future then dereferencing it will block if the code it is evaluating has not completed yet.


***

#### Programming Exercise

Lets create 10 different threads that all try to withdraw $1 from our account at the same time.  To get started, add to the program by following the directions provided by the comments `lib/futures.clj`.

> _TIP: skip ahead to an implementation of this answer with `git checkout futures-solved`_

##### Discussion

Discuss the results you got after running your program multiple times.  What do you think is causing the results that you got?  If you noticed a problem with your program, how would you do to solve it?

##### Explanation

The program may have not run consistently for you.  Let's discuss what happened.

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

Continue on to learn about [Atoms](Atoms.md).
