## Futures and Delays

### Futures

> **Futures:** Allows you to create a new thread and give it some work to perform.

##### "How can I create a thread and run some code in it?"

You may be familiar with a very long and tedious way of creating a thread to execute some work.  Here is an example of the Java way:

~~~java
   Runnable runnable = ...;
   Thread t = new Thread(runnable);
   t.start();
~~~

Our first step is to create a `Runnable` object that holds our code that will be executed.  Next, we create a `Thread` object, give it our `Runnable` object, and start executing it.

We can very easily create a thread and give it some code to execute in the form of an abstraction called a Future.  This is an example of how to create a future that will evaluate a body of code in another thread.

~~~clojure
    (def f (future (Thread/sleep 3000)
                   (println "Making some database query")
                   :result-of-database-query!)))
    f  ; => #<core$future_call$reify__6320@6402790e: :result-of-database-query!>
    @f ; => :result-of-database-query!
    @f ; => :result-of-database-query!
~~~

Our global variable `f` will sleep for three seconds and print `Making some database query`.  When it is done it will cache `:result-of-database-query!`.

If we peek at what our future `f` returns, we'll see that it does not show our expected return value `:result-of-database-query!`.  Instead `f` holds a reference to our future and shows us something along the lines of `#<core$future_call$reify__6320@6402790e: :result-of-database-query!>`.  If we want to access our cached return value, we must _dereference_ `f` with the `@` sign. Here is an example:

~~~clojure
    @f ; => :done!
~~~

We can return `:result-of-database-query!` from our future as many times as we want, but keep in mind that the body of code will only be executed once. Dereferencing a future will block if the code it is evaluating has not completed yet.

***

#### Programming Exercise

Create 10 different threads that all try to withdraw $1 from our account at the same time.  To get open up the file `lib/futures.clj` and follow the directions provided by the comments.


##### Discussion

What happened when you ran the program multiple times?  Did you expect the result that you got?  If there was something wrong with your program, can you describe how it happened?  How would you fix your problem if you encountered one?

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

Rewrite your solution to use this mechanism. What happened?

> _TIP: skip ahead to an implementation of locking data with `git checkout futures-locking-solved`_

Continue on to learn about [Atoms](Atoms.md).
