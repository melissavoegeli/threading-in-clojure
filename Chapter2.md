## Writing your first threaded program

### Futures and Delays

Learning how to write multithreaded programs is tricky. In fact, it is Run DMC Tricky. In the JRuby community there are four rules you need to follow before writing them:

    In general, the safest path to concurrency in JRuby is the same as on any other platform:
    1.Don't do it.
    2.If you must do it, don't share data across threads.
    3.If you must share data across threads, don't share mutable data.
    4.If you must share mutable data across threads, synchronize access to that data.

3 and 4 talk about something called mutable data. Quite frankly, mutable data is just as it sounds. It's data that you can change. Mutable data is dangerous when it is used between multiple threads because if you have more than one thread reading and changing data, it can potentially put it in an inconsistent state that causes something called _RACE CONDITIONS_.

    *Race Condition* A race condition occurs when two or more threads can access shared data and they try to change it at the same time. Because the thread scheduling algorithm can swap between threads at any time, you don't know the order in which the threads will attempt to access the shared data.

Clojure inherently allows us to protect ourselves from this right off the bat. See? Clojure loves you! We'll see more proof for this later.
