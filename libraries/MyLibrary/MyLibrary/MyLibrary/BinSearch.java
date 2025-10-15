package MyLibrary;
import java.util.Arrays;

/* 
 * used to find the position of a target value 
 * within a sorted array
 *  - repeatedly divides search interval in half
 */

 public class BinSearch<T extends Comparable<T>> {
    public BinSearch() { }

    public int search(T[] array, T key) {
        int low = 0; 
        int high = array.length - 1;

       while (low <= high) {
         final int mid = (low + high) / 2;
         final int cmp = key.compareTo(array[mid]);

        if (cmp == 0) {
            return mid; // found
        } else if (cmp < 0) {
            low = mid + 1; // go right 
        } else {
            high = mid - 1; // go left 
        }
        
       } 
       return -1; // not found 

    }
    
 }

 /* 
  * want to: 
      - index the array 
            - set values to the highest arr value
            and lowest
      - given those bounds, find the midpoint 
      of the array 
      - use compareTo method 
  */
  