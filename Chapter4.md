## Atoms

   *Atom* A datatype that is indivisible and unchanging. A process generates a new atom and we choose to associate the identity with the new atom.  Which keeps us from mutating the data.

(Talk about difference between state and identity)

(Give time to write an atom implementation, after 10 minutes go through it together live)

   > git checkout atoms

Here is our first step toward changing what we have to begin using atoms.  We still have a problem. While we are using swap to safely change the identity of the checking account balance between threads, we still check the validity of the operation outside of our threadsafe function.

(Explain how Atoms work under the cover, especially with swap!)

   > git checkout atoms-fixed
