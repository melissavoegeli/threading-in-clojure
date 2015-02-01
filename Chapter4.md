## Atoms

   *Atom* A datatype that is indivisible and unchanging. A process generates a new atom and we choose to associate the identity with the new atom.  Which keeps us from mutating the data.

### State and Identity without Clojure

Here is where we get to talk about Clojure's bread and butter: immutable state. The problem that we have encountered with futures is the fact that we have multiple threads accessing some data that can be in an unknown state. Clojure takes care of this by separating the concerns of data's identity and data's current state.

Imagine that we have a ledger with the title "Sansa Stark" on it. This ledger is filled with all of Sansa's characteristics: "red hair", "house Stark", "not married." We'll say her identity is the title "Sansa Stark" and her state is what is currently written into her ledger.

Next we make some plans and write some code to marry her off to house Lannister. So in thread #1 we have:

    if (Sansa.is_married?)
      Sansa.marry(Tyrion)

Our thread #1 successfully executes `if (Sansa.is_married?)`, but just before we marry her off our execution switches to thread #2:

    if (Sansa.is_married?)
      Sansa.marry(Joffrey)

At this point in time our ledger of Sansa Stark still says that Sansa's state represents that she is yet not married. So we go ahead and successfully marry her off to Joffrey. In order to do so we execute the above code and erase "not married" from our ledger and write in "married to Joffrey."

When we switch back to thread #1, we're still under the impression that her current state is not married. So we execute `Sansa.marry(Tyrion)` and erase "married to Joffrey" and fill in "married to Tyrion."

In this example there really doesn't seem to be much of a difference between Sansa's identity and her current state. This causes trouble for us.

### State and Identity the Clojure Way

An alternative solution to this is to ensure that we can never allow Sansa to fall into an inconsistent state in between threads. Clojure allows you to separate the identity of what we know Sansa Stark to be from whatever her current state happens to be.

Imagine a long line of buckets, and the one at the very end has a name plate on it that reads "Sansa Stark." The bucket is filled with the same characteristics as we saw on the ledger: "red hair", "house Stark", "not married." The name plate represents Sansa Stark's identity while the bucket it is attached to represents her current state of being.

Our new approach to this problem is to say when we reach into our Sansa Stark bucket to check her characteristic of being unmarried, we remover it. Next we make a new bucket and dump all of her old attributes into it, and instead of writing over her characteristic "not married" we throw it away and "married to Tyrion." Now because this is all one complete transaction, when our program switches over to thread #2 there is nothing for it to do yet.  The identity of Sansa Stark isn't attached to the old state or the new state yet. Thread #2 have to wait until we're done in thread #1 to see what Sansa Stark's current state is.

This is an example of how atoms are used. We move over all the unchanged information associated with the identity of a piece of data, and add the new stuff in and release it back into our program.

### Using an Atom

If we wanted to represent what we know about Sansa Stark as an atom:

    (def sansa (atom {:name "Sansa Stark" :house "Stark" :married? false}))

And changing her martial status:

    (swap! sansa (comp #(assoc % :married? true)))

One additional important thing about atoms is they use a mechanism called compare-and-set when modifying their data. This means that when a thread is about to successfully complete the action of setting an atom to its new state, it compares the old value to ensure nothing in any additional thread has already changed it. If something has already changed it, then you can expect all of the work to be thrown out, and started again with the new state of the atom.

Lets rewrite our current implementation to use atoms.

_TIP: skip ahead to an implementation of using delays with `git checkout atoms`_
