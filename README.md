## Overview Threading in Clojure

### Abstract

Multithreaded programming is a way for us to write applications with higher performance by providing simultaneous execution of programs.  Some examples include handling multiple connection requests from a web server, collecting and digesting big data, and handling simultaneous transactions on a checking account.  Another big example is any user interface you interact with.  While you're waiting for a movie to download, you can switch to a different application until your download has completed.

Clojure was designed to have efficient and safe threading practices out of the box, and has many good constructs for easily managing multiple threads and not have them clobber each other to cause race conditions.  Clojure accomplishes this by not having threads modify data, but instead create an identity to represent data's state at any given point in time.

This will get your feet wet with solving a simple concurrency problem using many of Clojure's multithreading constructs.

### Goals

* Learn why multithreaded programming is important as well as the difference between parallelism and concurrency.
* How Clojure solves common multithreading problems like race conditions.
* Several design patterns for solving a simple problem using multiple threads.
  * Futures
  * Atoms
  * Agents
  * Refs

### Requirements

* java 1.7+
* lein 2.5+
* git 2.1+

## Multithreaded Programming in Clojure

Machines are very powerful, but they are not becoming as powerful as quickly as they used to. CPU clock speeds have barely increased in recent years and there are more dual core and quad core computers. This means in order to make a program nearly twice as fast, you have to parallelize work on the separate cores.

#### Concurrency versus Parallelism

It is possible to run a concurrent program with multiple threads, yet not get any sort of performance boost.  Keep in mind that concurrency is a property of the program itself.  You can write a concurrent program that can perform many computational tasks at once, but whether or not your program can parallelize execution is a property of the system executing your program.

> **Concurrency:** Managing more than one task at the same time. Creating several threads that take turns executing on a single core is an example of a concurrent program.

> **Parallelism:** Executing two tasks at the same time. An example of a parallelized program is creating two different threads that execute on two separate cores at the same time.


#### When Should You Write a Multithreaded Program?

Learning how to write multithreaded programs is tricky. In the JRuby community there are four rules you need to follow before writing them:

    In general, the safest path to concurrency in JRuby is the same as on any other platform:
      1. Don't do it.
      2. If you must do it, don't share data across threads.
      3. If you must share data across threads, don't share mutable data.
      4. If you must share mutable data across threads, synchronize access to that data.

These rules are not exclusive to only the JRuby community.  They may be less explicit in the communities around Java, Python, and C#; but they are just as widely practiced.

Rule #1 exists because the JRuby doesn't make it easy to write concurrent programs.  Without the right tools and language constructs, it is hard to write safe and efficient multithreaded programs in JRuby. #2 suggests that if you absolutely _must_ write a multithreaded program, be sure to not share any data across threads.

Rules #3 and #4 talk about something called mutable data. Mutable data means the ability to mutate, or change, a piece of data. Mutable data is dangerous when it is accessed by multiple threads. If you have more than one thread reading or changing data, then the data can potentially be in an inconsistent state that causes something called _RACE CONDITIONS_.

> **Race Condition:** A race condition occurs when two or more threads can access shared data and they try to change it at the  same time. Because the thread scheduling algorithm can swap between threads at any time, you don't know the order in which the threads will attempt to access the shared data.

When your program has race conditions, it is almost _impossible_ to reproduce the bug in order to fix it.  is generally scares programmers away from writing multithreaded programs.

One important thing about Clojure is that it inherently allows us to protects us from this  problem right out of the box. We'll explore this idea a bit more later. For now lets continue on to learning about the **[Bank Account Feature](Bank_Account_Feature.md)** we'll be implementing to learn about multithreaded programming in Clojure.
