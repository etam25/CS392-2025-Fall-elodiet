/*
// HX: 50 points for Final_05
// HX: This one tests your priority queue implementation
*/

import Library.LnList.*;
import java.util.function.ToIntBiFunction;

public class Final_05 {

    public static<T> LnList<T>
	LnList_n$way$merge(LnList<T> xss[], ToIntBiFunction<T,T> cmp) {
	// HX: Given an array of (linear) lists (LnList), each of which is
	// ordered according to cmp, please implement a function to merge them
	// into one ordered (linear) list. Please note that you cannot create
	// new list nodes; you can only use exist nodes to form the returned
	// linear list. You are asked to use MyPQueueArray.java implemented in
	// Assigment#9 for finding the minimum of a collection of arguments.
    }

    public static<T>
	FnList<T>
	LnList_mergeSort$5way(LnList<T> xs, ToIntBiFunction<T,T> cmp) {
	// HX: Please use LnList_n$way$merge to implement 5-way mergesort
	// on a linear list. That is, split each list evenly into 5 sublists;
	// recursely sort the 5 sublist and then use LnList_n$way$merge to merge
	// them into one sorted list.
	// Please make sure that your implementation of LnList_mergeSort$5way
	// does stable sorting!
    }

    public static void main(String[] args) {
	// Please write some testing code that applies
	// mergeSort to parity-sort the list [0,1,2,...,999998,999999]
	// of 1000000 elements.
    }


}


