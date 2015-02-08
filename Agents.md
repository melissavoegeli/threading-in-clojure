## Agents

> **Agent:** Like an atom such that it will represent some state of an identity at some point in time, but can be asynchronous. For example you can keep sending work to an agent and carry on with your program.

Unlike the way Atoms synchronously accept changes to their identities through the compare-and-set mechanism, agents allow you to asynchronously send off work and that will block each other until their work is complete.

### Using an Agent

Interacting with an agent is very similar to using atoms. If we wanted to represent a checking account as an agent:

~~~clojure
   (def checking-account (agent {:balance 100 :holder "my-name"}))
~~~

And changing her martial status:

~~~clojure
   (send checking-account (comp #(assoc % :holder "your-name")))
~~~

***

#### Programming Exercise

We can make slight modifications to our previous example in `lib/agents.clj`.

> _TIP: skip ahead to an implementation of using delays with `git checkout agents-solved`_

### Discussion

Can you give any real world examples about when it would be more appropriate to use an atom? An agent? Continue on to [Refs](Refs.md).
