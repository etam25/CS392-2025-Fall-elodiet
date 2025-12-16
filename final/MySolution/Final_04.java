/*
// HX: 50 points for Final_04
// HX: This one tests your RBST implementation done in Quiz02_06.
// In Final_02, pg2701_word$count$listize1() is implemented
// to list words in pg2701.txt according their frequencies.
// In Final_04, you are asked to implement the same functionality
// with a different approach.
*/
import MyLibrary.FnList.*;
import MyLibrary.FnTuple.*;
import MyLibrary.LnStrm.*;

public class Final_04 {
    
    static FnList<FnTupl2<FnList<Character>, Integer>> pg2701_word$count$listize4() {
        // Step 1: Call pg2701_word$strmize() to get a stream of words
        LnStrm<FnList<Character>> wordStrm = Final_01.pg2701_word$strmize();
        
        // Step 2: Use RBST (Quiz02_06) to count word occurrences
        // Assuming Quiz02_06 has been modified to be generic: Quiz02_06<K, V>
        Quiz02_06<String, Integer> wordCountMap = new Quiz02_06<>();
        
        // Process each word in the stream
        LnStcn<FnList<Character>> cons = wordStrm.eval0();
        while (cons.consq()) {
            FnList<Character> word = cons.hd();
            String wordStr = wordToString(word);
            
            // Get current count or 0 if not found
            Integer count = wordCountMap.get(wordStr);
            if (count == null) {
                count = 0;
            }
            
            // Increment and insert/update
            wordCountMap.insert(wordStr, count + 1);
            
            cons = cons.tl().eval0();
        }
        
        // Step 3: Turn RBST-based map into a list WNS of word-count pairs
        final FnList<FnTupl2<String, Integer>>[] tempList = new FnList[]{FnListSUtil.nil()};
        
        // Use forEach to iterate through RBST (assumes forEach method exists)
        wordCountMap.forEach((wordStr, count) -> {
            FnTupl2<String, Integer> pair = new FnTupl2<>(wordStr, count);
            tempList[0] = FnListSUtil.cons(pair, tempList[0]);
        });
        
        // Convert from String keys to FnList<Character> keys
        FnList<FnTupl2<FnList<Character>, Integer>> WNS = FnListSUtil.nil();
        FnList<FnTupl2<String, Integer>> tempPairs = tempList[0];
        
        while (!tempPairs.nilq()) {
            FnTupl2<String, Integer> pair = tempPairs.hd();
            String wordStr = pair.sub0;
            Integer count = pair.sub1;
            
            FnList<Character> word = stringToWord(wordStr);
            FnTupl2<FnList<Character>, Integer> newPair = 
                new FnTupl2<>(word, count);
            WNS = FnListSUtil.cons(newPair, WNS);
            
            tempPairs = tempPairs.tl();
        }
        
        // Step 4: Use mergeSort to sort WNS
        FnList<FnTupl2<FnList<Character>, Integer>> sortedWNS = 
            FnListSUtil.mergeSort(WNS, (p1, p2) -> comparePairs(p1, p2));
        
        // Step 5: Return sorted WNS
        return sortedWNS;
    }
    
    // Helper to convert FnList<Character> to String
    private static String wordToString(FnList<Character> word) {
        StringBuilder sb = new StringBuilder();
        word.foritm(ch -> sb.append(ch));
        return sb.toString();
    }
    
    // Helper to convert String to FnList<Character>
    private static FnList<Character> stringToWord(String str) {
        FnList<Character> result = FnListSUtil.nil();
        for (int i = str.length() - 1; i >= 0; i--) {
            result = FnListSUtil.cons(str.charAt(i), result);
        }
        return result;
    }
    
    // Compare two words lexicographically
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
    
    public static void main(String[] args) {
        // HX-2025-12-16: minimal testing
        System.out.println("Computing word counts using RBST...");
        System.out.println();
        
        FnList<FnTupl2<FnList<Character>, Integer>> wordCounts = 
            pg2701_word$count$listize4();
        
        System.out.println("First 100 word-count pairs:");
        System.out.println("============================");
        
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