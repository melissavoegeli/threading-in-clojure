### Writing your first threaded program

## Futures and Delays

Learning how to write multithreaded programs is hard. In the JRuby community there are four rules you need to follow before writing them:

  "In general, the safest path to concurrency in JRuby is the same as on any other platform:
    1.Don't do it.
    2.If you must do it, don't share data across threads.
    3.If you must share data across threads, don't share mutable data.
    4.If you must share mutable data across threads, synchronize access to that data."

3 and 4 talk about something called mutable data.  Mutable data is dangerous between threads because if you have more than one thread reading and changing data, it can potentially put it in an inconsistent state that causes something called RACE CONDITIONS.

Race conditions occur when you run your multithreaded program and you consistently get difference results.  Clojure inherently allows us to protect ourselves from this right off the bat.  We'll see more proof for this later.
