import Library.LnStrm.*;
import Library.FnTuple.*;
import java.util.function.ToIntBiFunction;

public class Assign06_02 {
    
    public static
    LnStrm<Integer>
    ramanujanNumbers() {
        LnStrm<FnTupl2<Integer, Integer>> pairs = cubeSumOrderedIntegerPairs();
        return findDuplicateSums(pairs);
    }
    
    private static LnStrm<Integer> findDuplicateSums(LnStrm<FnTupl2<Integer, Integer>> pairs) {
        return new LnStrm<Integer>(
            () -> {
                LnStcn<FnTupl2<Integer, Integer>> stcn = pairs.eval0();
                if (stcn.nilq()) {
                    return new LnStcn<Integer>();
                }
                FnTupl2<Integer, Integer> first = stcn.head;
                int firstSum = cubeSum(first);
                return scanForDuplicates(firstSum, first, stcn.tail);
            }
        );
    }
    
    private static LnStcn<Integer> scanForDuplicates(
            int targetSum, 
            FnTupl2<Integer, Integer> firstPair, 
            LnStrm<FnTupl2<Integer, Integer>> remaining) {
        
        LnStcn<FnTupl2<Integer, Integer>> stcn = remaining.eval0();
        
        if (stcn.nilq()) {
            return new LnStcn<Integer>();
        }
        
        FnTupl2<Integer, Integer> current = stcn.head;
        int currentSum = cubeSum(current);
        
        if (currentSum == targetSum) {
            return new LnStcn<Integer>(targetSum, findDuplicateSums(stcn.tail));
        } else if (currentSum > targetSum) {
            return scanForDuplicates(currentSum, current, stcn.tail);
        } else {
            return scanForDuplicates(targetSum, firstPair, stcn.tail);
        }
    }
    
    public static
    LnStrm<FnTupl2<Integer,Integer>>
    cubeSumOrderedIntegerPairs() {
        LnStrm<Integer> intStream = intStreamFrom(1);
        LnStrm<LnStrm<FnTupl2<Integer, Integer>>> streamOfStreams = 
            LnStrmSUtil.map0(intStream, (Integer x) -> pairsStartingWith(x));
        
        ToIntBiFunction<FnTupl2<Integer, Integer>, FnTupl2<Integer, Integer>> cmpr = 
            (p1, p2) -> {
                int sum1 = cubeSum(p1);
                int sum2 = cubeSum(p2);
                return Integer.compare(sum1, sum2);
            };
        
        return Assign06_01.mergeLnStrm(streamOfStreams, cmpr);
    }
    
    private static LnStrm<FnTupl2<Integer, Integer>> pairsStartingWith(int x) { 
        LnStrm<Integer> intStream = intStreamFrom(x);
        return LnStrmSUtil.map0(intStream, (Integer y) -> new FnTupl2<Integer, Integer>(x, y));
    }
    
    private static LnStrm<Integer> intStreamFrom(int n) {
        return new LnStrm<Integer>(
            () -> new LnStcn<Integer>(Integer.valueOf(n), intStreamFrom(n + 1))
        );
    }
    
    private static int cubeSum(FnTupl2<Integer, Integer> pair) {
        Integer x = pair.T0;
        Integer y = pair.T1;
        return x * x * x + y * y * y;
    }
    
    public static void main(String[] args) {
        System.out.println("Testing cubeSumOrderedIntegerPairs:");
        System.out.println("First 20 pairs ordered by cube sum:");
        
        LnStrm<FnTupl2<Integer, Integer>> pairs = cubeSumOrderedIntegerPairs();
        int count = 0;
        LnStcn<FnTupl2<Integer, Integer>> stcn = pairs.eval0();
        
        while (stcn.consq() && count < 20) {
            FnTupl2<Integer, Integer> pair = stcn.head;
            Integer x = pair.get0();
            Integer y = pair.get1();
            int sum = cubeSum(pair);
            System.out.printf("(%d, %d): %d^3 + %d^3 = %d%n", x, y, x, y, sum);
            stcn = stcn.tail.eval0();
            count++;
        }
        
        System.out.println("\nTesting ramanujanNumbers:");
        System.out.println("First 10 Ramanujan numbers:");
        
        LnStrm<Integer> ramanujan = ramanujanNumbers();
        count = 0;
        LnStcn<Integer> ramStcn = ramanujan.eval0();
        
        while (ramStcn.consq() && count < 10) {
            Integer num = ramStcn.head;
            System.out.printf("%d. %d%n", count + 1, num);
            findRepresentations(num);
            ramStcn = ramStcn.tail.eval0();
            count++;
        }
    } 
    
    private static void findRepresentations(int n) {
        int foundCount = 0;
        for (int x = 1; x * x * x < n && foundCount < 2; x++) {
            for (int y = x; y * y * y <= n - x * x * x && foundCount < 2; y++) {
                if (x * x * x + y * y * y == n) {
                    System.out.printf("   %d^3 + %d^3 = %d%n", x, y, n);
                    foundCount++;
                }
            }
        }
    }
    
}