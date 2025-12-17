# Final Exam Solutions - CS392X1, Fall 2025

This README briefly describes the implementations for Final assignments 1-5.

## Final_01 (20 points) - Streamization of Words

**Implementation:**
- Implemented `pg2701_word$strmize()` that converts a character stream into a word stream
- Words are defined as sequences of letters (a-z, A-Z) and apostrophes, separated by non-letter characters
- All words are converted to lowercase for consistency
- Uses a recursive helper function `word_stream_helper()` that processes characters and builds words incrementally
- Returns a lazy stream (`LnStrm`) of words as `FnList<Character>`

**Key Features:**
- Handles word boundaries correctly (punctuation, spaces, etc.)
- Case-insensitive word processing
- Lazy evaluation using streams

## Final_02 (50 points) - Counting Words: Version 1

**Implementation:**
- Implemented `pg2701_word$count$listize2()` using quicksort and mergesort
- **Step 1:** Gets word stream from `Final_01.pg2701_word$strmize()`
- **Step 2:** Converts stream to array `FnList<Character>[]`
- **Step 3:** Sorts array using quicksort from `Assign06_03` (3-way partition with median-of-three pivot)
- **Step 4:** Counts consecutive identical words in sorted array to create word-count pairs
- **Step 5:** Sorts pairs using mergesort from `Assign05_01` with custom comparator:
  - Primary: count descending (higher counts first)
  - Secondary: word ascending (lexicographic order)
- **Step 6:** Returns sorted list of word-count pairs

**Key Features:**
- Includes complete quicksort and mergesort implementations (copied from assignments)
- Efficient counting using sorted array
- Custom comparator for frequency-based sorting

## Final_03 (50 points) - Counting Words: Version 2

**Implementation:**
- Implemented `pg2701_word$count$listize3()` using hash map (open addressing)
- **Step 1:** Gets word stream from `Final_01.pg2701_word$strmize()`
- **Step 2:** Uses `Assign08_02` hash map to count word occurrences
  - Strategy: Insert value `1` for each word occurrence
  - Hash map automatically prepends to list for existing keys
  - Avoids expensive remove/re-insert operations
- **Step 3:** Converts hash map to list using `strmize()`, counting list length for each word
- **Step 4:** Sorts pairs using mergesort from `Assign05_01` with same comparator as Final_02
- **Step 5:** Returns sorted list of word-count pairs

**Key Features:**
- Efficient insertion-only approach (no remove/re-insert)
- Uses hash map's built-in list prepending for counting
- Includes complete mergesort implementation

## Final_04 (50 points) - Counting Words: Version 3

**Implementation:**
- Implemented `pg2701_word$count$listize4()` using RBST (Randomized Binary Search Tree)
- **Step 1:** Gets word stream from `Final_01.pg2701_word$strmize()`
- **Step 2:** Uses RBST-based map (adapted from `Quiz02_06`) to count word occurrences
  - Generic RBST implementation with insert and search operations
  - Maintains word-count pairs in tree structure
- **Step 3:** Converts RBST to list by in-order traversal
- **Step 4:** Sorts pairs using mergesort from `Assign05_01` with same comparator
- **Step 5:** Returns sorted list of word-count pairs

**Key Features:**
- RBST implementation with randomized insertion for balanced trees
- In-order traversal for converting tree to list
- Generic map structure adapted from quiz implementation

## Final_05 (50 points) - N-way Merge and Mergesort

**Implementation:**
- Implemented `LnList_n$way$merge()` for merging multiple sorted linear lists
  - Uses a simple priority queue (min-heap) to find minimum element across all lists
  - Reuses existing list nodes (no new node creation)
  - Merges lists in sorted order according to comparator
- Implemented `LnList_mergeSort$5way()` for 5-way mergesort
  - Splits input list evenly into 5 sublists
  - Recursively sorts each sublist
  - Merges sorted sublists using `LnList_n$way$merge()`
  - Ensures stable sorting (preserves relative order of equal elements)

**Key Features:**
- Priority queue implementation for efficient minimum finding
- 5-way merge sort with stable sorting guarantee
- No new list node creation (reuses existing nodes)
- Test code includes parity-sort of 1,000,000 elements

## Summary

All implementations successfully process the `pg2701.txt` file (Moby Dick) and produce word frequency counts sorted by:
1. Frequency (descending)
2. Word (ascending, for ties)

Each version uses a different data structure approach:
- **Final_02:** Array + Quicksort + Mergesort
- **Final_03:** Hash Map (open addressing)
- **Final_04:** RBST (Randomized Binary Search Tree)

Final_05 demonstrates advanced sorting with n-way merge capabilities.

