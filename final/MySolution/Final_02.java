/*
// HX: 50 points for Final_02
// HX: This one tests your quicksort and mergesort
// In Final_01, pg2701_word$strmize() is implemented
// that lists all the words in pg2701.txt. Here, you
// are asked to generate FnList of pairs; each pair consists
// of a word (FnList<Character>) and a count (Integer) such that
// the count is the number of occurrences of the word in pg2701.txt.
// Note that a lower case letter is considered the same as its
// corresponding upper case. For instance, "Whale" and "whale"
// are considered the same word.
*/
import MyLibrary.FnList.*;
import MyLibrary.FnTuple.*;
import MyLibrary.FnA1sz.*;
import MyLibrary.LnStrm.*;

public class Final_02 {
    
    static FnList<FnTupl2<FnList<Character>, Integer>> pg2701_word$count$listize2() {
        // Step 1: Call pg2701_word$strmize() to get a stream of words
        LnStrm<FnList<Character>> wordStrm = Final_01.pg2701_word$strmize();
        
        // Step 2: Turn this stream into an array A1 of words (FnList<Character>[])
        FnList<FnList<Character>> wordList = LnStrmSUtil.toFnList0(wordStrm);
        FnA1sz<FnList<Character>> A1 = wordList.toArray();
        
        // Step 3: Call the quicksort (arrayQuickSort) to sort A1
        FnA1sz<FnList<Character>> sortedA1 = A1.U0.quickSort(A1, 
            (w1, w2) -> compareWords(w1, w2));
        
        // Step 4: Use sorted A1 to generate a list L2 of word-count pairs
        FnList<FnTupl2<FnList<Character>, Integer>> L2 = generate_word_counts(sortedA1);
        
        // Step 5: Use the mergesort to sort L2 using the order:
        // (w1, n1) <= (w2, n2) if n1 > n2 or n1 = n2 and w1 <= w2
        FnList<FnTupl2<FnList<Character>, Integer>> sortedL2 = 
            FnListSUtil.mergeSort(L2, (p1, p2) -> comparePairs(p1, p2));
        
        // Step 6: The sorted L2 is the return value
        return sortedL2;
    }
    
    // Compare two words
    private static int compareWords(FnList<Character> w1, FnList<Character> w2) {
        while (!w1.nilq() && !w2.nilq()) {
            char c1 = w1.hd();
            char c2 = w2.hd();
            if (c1 < c2) return -1;
            if (c1 > c2) return 1;
            w1 = w1.tl();
            w2 = w2.tl();
        }
        // If one word is a prefix of the other
        if (w1.nilq() && w2.nilq()) return 0;
        if (w1.nilq()) return -1; // w1 is shorter
        return 1; // w2 is shorter
    }
    
    // Generate word-count pairs from sorted array
    private static FnList<FnTupl2<FnList<Character>, Integer>> 
    generate_word_counts(FnA1sz<FnList<Character>> sortedWords) {
        int n = sortedWords.length();
        if (n == 0) return FnListSUtil.nil();
        
        FnList<FnTupl2<FnList<Character>, Integer>> result = FnListSUtil.nil();
        
        FnList<Character> currentWord = sortedWords.getAt(0);
        int count = 1;
        
        for (int i = 1; i < n; i++) {
            FnList<Character> word = sortedWords.getAt(i);
            
            if (wordsEqual(currentWord, word)) {
                // Same word, increment count
                count++;
            } else {
                // Different word, save current word-count pair
                result = FnListSUtil.cons(
                    new FnTupl2<FnList<Character>, Integer>(currentWord, count),
                    result
                );
                currentWord = word;
                count = 1;
            }
        }
        
        result = FnListSUtil.cons(
            new FnTupl2<FnList<Character>, Integer>(currentWord, count),
            result
        );
        
        return FnListSUtil.reverse(result);
    }
    
    // Check if two words are equal
    private static boolean wordsEqual(FnList<Character> w1, FnList<Character> w2) {
        return compareWords(w1, w2) == 0;
    }
    
    // Compare pairs: (w1, n1) <= (w2, n2) if n1 > n2 or n1 = n2 and w1 <= w2
    private static int comparePairs(
        FnTupl2<FnList<Character>, Integer> p1,
        FnTupl2<FnList<Character>, Integer> p2) {
        
        int n1 = p1.sub1;
        int n2 = p2.sub1;
        
        // Higher count comes first (n1 > n2 means p1 < p2 in our order)
        if (n1 > n2) return -1;
        if (n1 < n2) return 1;
        
        // If counts are equal, sort lexicographically by word
        return compareWords(p1.sub0, p2.sub0);
    }
    
    // Helper to convert FnList<Character> to String
    private static String wordToString(FnList<Character> word) {
        StringBuilder sb = new StringBuilder();
        word.foritm(ch -> sb.append(ch));
        return sb.toString();
    }
    
    public static void main(String[] args) {
        // HX-2025-12-16: minimal testing
        System.out.println("Computing word counts from pg2701.txt...");
        System.out.println();
        
        FnList<FnTupl2<FnList<Character>, Integer>> wordCounts = 
            pg2701_word$count$listize2();
        
        System.out.println("First 100 word-count pairs:");
        
        int i = 0;
        FnList<FnTupl2<FnList<Character>, Integer>> xs = wordCounts;
        
        while (i < 100 && !xs.nilq()) {
            FnTupl2<FnList<Character>, Integer> pair = xs.hd();
            String word = wordToString(pair.sub0);
            int count = pair.sub1;
            
            System.out.println(word + " : " + count);
            
            xs = xs.tl();
            i++;
        }
        
        System.out.println();
        System.out.println("Total unique words: " + wordCounts.length());
        
        return; /*void*/
    }
}