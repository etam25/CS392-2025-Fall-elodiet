# MyDequeList Integration - README

## Changes Made to FnGtreeSUtil.java

### Overview
Modified `FnGtreeSUtil.java` to use `MyDequeList` (from Assign04_02) instead of `MyQueueList` and `MyStackList` for tree traversal operations.

### Key Modifications

#### 1. Imports
- **Removed**: `import Library.MyStack.*;` and `import Library.MyQueue.*;`
- **Added**: Uses `MyDequeList` from the provided implementation

#### 2. BFirstSearch and BFirstEnumerate (Breadth-First)
Uses `MyDequeList` as a **queue** (FIFO):
- `deque.renque(item)` - enqueue at rear
- `deque.fdeque()` - dequeue from front
- Children added at rear to maintain BFS order

#### 3. DFirstSearch and DFirstEnumerate (Depth-First)
Uses `MyDequeList` as a **stack** (LIFO):
- `deque.fenque(item)` - push at front
- `deque.fdeque()` - pop from front
- Children added at front using `rforitm()` to maintain DFS order

### Changes to MyDequeList Implementation

**NO CHANGES REQUIRED** to the original `MyDequeList` implementation. The provided implementation already supports all necessary operations:
- `renque(T item)` - rear enqueue
- `fenque(T item)` - front enqueue
- `fdeque()` - front dequeue
- `isEmpty()` - check if empty

The implementation is complete and functional for this use case.

### Testing

The modified code maintains the same traversal behavior:
- **BFS**: Visits nodes level by level (parent before children, siblings left to right)
- **DFS**: Visits nodes depth-first (explores each branch fully before backtracking)

## Summary

The deque data structure elegantly replaces both stack and queue implementations, demonstrating the power of a well-designed double-ended queue abstraction.