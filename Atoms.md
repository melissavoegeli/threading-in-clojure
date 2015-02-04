## Atoms

> **Atom:** A datatype that is indivisible and unchanging. A process generates a new atom and we choose to associate the identity with the new atom, which keeps us from mutating the data.

### State and Identity without Clojure

Let's reference our list of threading rules from a few chapters ago. Specifically rule #3.

> `If you must share data across threads, don't share mutable data.`

Here is where we get to talk about Clojure's bread and butter: immutable state. The problem that we have encountered with futures is the fact that we have multiple threads accessing some data that can be in an unknown state. Clojure takes care of this with a reference type known as the atom.

In Clojure, there is a difference between a data's state and identity. In most other languages there is hardly a difference between these two properties. For example lets pretend we implemented our checking account in Ruby. In our withdrawal function we would have some code that looks like this:

~~~ruby
    # thread 1
    checking_account = Account.new
    checking_account.deposit(100)
    checking_account.holder('Sansa')
    amount = 105
    ...
    if (!checking_account.is_zero?)
      checking_account.withdraw(amount)
~~~

There is nothing inherently bad about this implementation. We have established a reference to an `Account` which is meant to represent our checking account.  The holder of the checking account is `Sansa`. Overtime our account could have existed in many different states. It could have had $0, $100, or $1000.  Our checking account state may change from one point in time to another, but it never modifies what it previously was.  But one troubling fact is that we can conflate the identity of our checking account to suddenly be someone else's.

~~~ruby
    checking_account.holder('Arya')
~~~

### State and Identity the Clojure Way

An alternative solution to this is to ensure that we can never allow our checking account to fall into an inconsistent state in between threads. Clojure allows you to separate the identity of what we know as our checking account to be separate from the identity that represents it.  Atoms are identities that implement synchronous, uncoordinated, atomic compare-and-set modification.

Let's elaborate a bit more on what `compare-and-set modification` means.  When we're modifying our checking account balance by withdrawing $1, our atom should look to ensure our current balance is an appropriate amount to withdraw from. Next we withdraw the amount and get ready to move all of our unchanged information associated with our checking account identity to a new state that represents our atom, including our new changes.  Next we compare our inclination of what our atom thought was the "old state" just before we update. If that has changed in another thread we throw out all of our work and start over again with the correct state.

### Using an Atom

If we wanted to represent what we know about Sansa Stark as an atom:

~~~clojure
   (def checking-account (atom {:balance 100 :holder "my-name"}))
~~~

And changing her martial status:

~~~clojure
   (swap! checking-account (comp #(assoc % :holder "your-name")))
~~~

***

#### Programming Exercise

Let's complete our example `lib/atoms.clj`. We want to show two things about our new knowledge of atoms. Show an example that demonstrates an atom's synchronousness properties. For example, lets print a message each time we call `withdraw` to show that it will block until completion. Next lets demonstrate the `compare-and-set` property. If we have multiple threads attempting to withdraw amounts at the same time, that is sure to cause some collisions.

> _TIP: skip ahead to an implementation of using delays with `git checkout atoms-solved`_

Continue on to [Agents](Agents.md).
