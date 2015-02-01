## Agents

   *Agent* Like an atom such that it will represent some state of an identity at some point in time, but can be asynchronous. For example you can keep
sending work to an agent and carry on with your program.

Unlike the way Atoms synchornously accept changes to their identities through the compare-and-set mechanism, agents allow you to asychronously send off work and block each other until their work is complete.

### Using an Agent

Interacting with an agent is very similar to using atoms. If we wanted to represent what we know about Sansa Stark as an agent:

   (def sansa (agent {:name "Sansa Stark" :house "Stark" :married? false}))

And changing her martial status:

   (send sansa (comp #(assoc % :married? true)))

Lets rewrite our current implementation to use agents.

_TIP: skip ahead to an implementation of using delays with `git checkout agents`_
