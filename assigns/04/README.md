# Assignment 4

It is due Tuesday, the 30th of September, 2025

## Assignment 4-1 (20 points)

In Assign03_03, you are asked to implement a two list
based queue. Please extend your implementation of Assign03_03
with code implementing the following four higher-order methods:
foritm, iforitm, rforall, irforall

## Assignment 4-2 (50 points)

This is a comprehensive assignment. You are asked to implement
MyDequeList, a list-based implementation of deque. Note that a
deque is a double ended queue, which is a combination of stack
and queue. In other words, a deque is both a stack and a queue.
You have two sets of enqueue and dequeue operations:

```
rpeek: for read peek // peek was called 'top'
fpeek: for front peek // peek was called 'top'
renque and rdeque: for rear enqueue and dequeue
fenque and fdeque: for front enqueue and dequeue
```

The underlying list for your implementation of MyDequeuList
should be doubly-linked: each node has a 'next' field as well
as a 'prev' field; node.next returns the following node (if it
exists) and node.prev returns the preceding node (if it exists).

Please base your code structure on Library/MyQueue. Yes, you
need to implement the four higher-order methods (foritm, iforitm,
rforitm, and irforitm). Don't forget the System$out$print method.

Please put all of your MyDeque implementation inside the (already
created) MySolution/MyDeque directory.