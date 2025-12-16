/*
// HX: 50 points for Final_03
// HX: This one tests your hash map implementation
// In Final_02, pg2701_word$count$listize2() is implemented
// to list words in pg2701.txt according their frequencies.
// In Final_03, you are asked to implement the same functionality
// with a different approach.
*/
import MyLibrary.FnList.*;
import MyLibrary.FnTuple.*;
import MyLibrary.LnStrm.*;

public class Final_03 {
    
    static FnList<FnTupl2<FnList<Character>, Integer>> pg2701_word$count$listize3() {
        LnStrm<FnList<Character>> wordStrm = Final_01.pg2701_word$strmize();
        
        Assign08_02<Integer> wordCountMap = new Assign08_02<>(10000);
        
        LnStcn<FnList<Character>> cons = wordStrm.eval0();
        while (cons.consq()) {
            FnList<Character> word = cons.hd();
            String wordStr = wordToString(word);
            
            FnList<Integer> counts = wordCountMap.search$opt(wordStr);
            if (counts == null) {
                wordCountMap.insert$exn(wordStr, 1);
            } else {
                int currentCount = counts.hd();
                wordCountMap.remove$exn(wordStr);
                wordCountMap.insert$exn(wordStr, currentCount + 1);
            }
            
            cons = cons.tl().eval0();
        }
        
        FnList<FnTupl2<FnList<Character>, Integer>> WNS = FnListSUtil.nil();
        
        wordCountMap.foritm((key, count) -> {
            FnList<Character> word = stringToWord(key);
            FnTupl2<FnList<Character>, Integer> pair = 
                new FnTupl2<>(word, count);
        });
        
        FnList<FnTupl2<String, Integer>> tempPairs = FnListSUtil.nil();
        wordCountMap.foritm((key, count) -> {
        });
        
        LnStrm<FnTupl2<String, FnList<Integer>>> mapStrm = wordCountMap.strmize();
        LnStcn<FnTupl2<String, FnList<Integer>>> mapCons = mapStrm.eval0();
        
        while (mapCons.consq()) {
            FnTupl2<String, FnList<Integer>> entry = mapCons.hd();
            String wordStr = entry.sub0;
            FnList<Integer> counts = entry.sub1;
            int count = counts.hd(); 
            
            FnList<Character> word = stringToWord(wordStr);
            FnTupl2<FnList<Character>, Integer> pair = 
                new FnTupl2<>(word, count);
            WNS = FnListSUtil.cons(pair, WNS);
            
            mapCons = mapCons.tl().eval0();
        }
        
        FnList<FnTupl2<FnList<Character>, Integer>> sortedWNS = 
            FnListSUtil.mergeSort(WNS, (p1, p2) -> comparePairs(p1, p2));
        
        return sortedWNS;
    }
    
    private static String wordToString(FnList<Character> word) {
        StringBuilder sb = new StringBuilder();
        word.foritm(ch -> sb.append(ch));
        return sb.toString();
    }
    
    private static FnList<Character> stringToWord(String str) {
        FnList<Character> result = FnListSUtil.nil();
        for (int i = str.length() - 1; i >= 0; i--) {
            result = FnListSUtil.cons(str.charAt(i), result);
        }
        return result;
    }
    
    private static int compareWords(FnList<Character> w1, FnList<Character> w2) {
        while (!w1.nilq() && !w2.nilq()) {
            char c1 = w1.hd();
            char c2 = w2.hd();
            if (c1 < c2) return -1;
            if (c1 > c2) return 1;
            w1 = w1.tl();
            w2 = w2.tl();
        }
        if (w1.nilq() && w2.nilq()) return 0;
        if (w1.nilq()) return -1; 
        return 1;
    }
    
    private static int comparePairs(
        FnTupl2<FnList<Character>, Integer> p1,
        FnTupl2<FnList<Character>, Integer> p2) {
        
        int n1 = p1.sub1;
        int n2 = p2.sub1;
        
        if (n1 > n2) return -1;
        if (n1 < n2) return 1;
        
        return compareWords(p1.sub0, p2.sub0);
    }
    
    public static void main(String[] args) {
        System.out.println("Computing word counts using hash map...");
        System.out.println();
        
        FnList<FnTupl2<FnList<Character>, Integer>> wordCounts = 
            pg2701_word$count$listize3();
        
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
        
        return;
    }
}