## Promises

Promises share many of the mechanics of delays and futures. A a promise may be dereferenced and dereferencing a promise will block until it has a value to provide, and a promise will only ever have one value.
However, promises are not created with any code or function that will eventually define its value

> **Promise:** A barren container where at some later point in time,may be fulfilled by having a value delivered to it.

### Using a Promise

Creating a promise looks like:

~~~clojure
    (def my-promise (promise))
~~~

We can deliver on our promises:

~~~clojure
    (deliver my-promise (fn [] println "My promise!"))
~~~

A common use for promises is to deliver some unit of work after some longer running processes finish at a later point in time.  One thing that you may have noticed about our code examples is that when we load our code into the repl, it shows our ending balance before all of our work has finished. Lets rewrite to return a promise each time we withdraw, and after each thread returns its promise we can safely print out our true ending balance. To get started fix our program in`lib/promises.clj` with `git checkout promises`.

> _TIP: skip ahead to an implementation of using delays with `git checkout promises-solved`_
