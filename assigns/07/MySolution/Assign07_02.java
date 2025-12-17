import Library.LnStrm.*;
import java.util.ArrayList;
import java.util.List;

class UnsupportedOpr
    extends RuntimeException {
    String opr;
    public UnsupportedOpr(String opr) {
	this.opr = opr;
    }
}

abstract class Term {
    public String tag = "Term";
    public abstract double eval();
    public abstract String toString();
}

class TermInt extends Term {
    public int val;
    public TermInt(int val) {
	this.tag = "TermInt"; 
    this.val = val;
    }
    public double eval() { 
        return val; 
    }
    public String toString() {
        return String.valueOf(val);
    }
}

class TermOpr extends Term {
    public String opr;
    public Term arg1, arg2;
    public TermOpr(String opr0, Term arg1, Term arg2) {
	this.tag = "TermOpr";
	this.opr = opr0; 
    this.arg1 = arg1; 
    this.arg2 = arg2;
    }
    public double eval() {
	switch (opr) {
	  case "+":
	      return arg1.eval() + arg2.eval();
	  case "-":
	      return arg1.eval() - arg2.eval();
	  case "*":
	      return arg1.eval() * arg2.eval();
	  case "/":
        double divisor = arg2.eval();
        if (Math.abs(divisor) < 1e-10) {
            return Double.NaN;
        }
	      return arg1.eval() / arg2.eval();
	}
	throw new UnsupportedOpr(opr);
    }

    public String toString() {
        return "(" + arg1.toString() + " " + opr + " " + arg2.toString() + ")";
    }
}

public class Assign07_02 {

    private static final double epsilon = 1e-9;
    private static final double target = 24.0;
    private static final String[] operations = {
        "+", "-", "*", "/"
    };

    private static boolean isTarget(double value) {
        return Math.abs(value - target) < epsilon && 
            !Double.isNaN(value) &&
            !Double.isInfinite(value);
    }

    private static List<Term> combineTerms(List<Term> terms, int i, int j, Term newTerm) {
        List<Term> result = new ArrayList<>();
        for (int k = 0; k < terms.size(); k++) {
            if (k != i && k != j) {
                result.add(terms.get(k));
            }
        }
        result.add(newTerm);
        return result;
    }

    private LnStrm<Term> concat_streams(LnStrm<Term> s1, LnStrm<Term> s2) {
        return new LnStrm<>(() -> {
            LnStcn<Term> c1 = s1.eval0();
            if (c1.consq()) {
                return new LnStcn<Term>(c1.head, concat_streams(c1.tail, s2));
            } else {
                return s2.eval0();
            }
        });
    }

    public LnStrm<Term> GameOf24_bfs_solve
	(int n1, int n2, int n3, int n4) {
        List<Term> initialTerms = new ArrayList<>();
        initialTerms.add(new TermInt(n1));
        initialTerms.add(new TermInt(n2));
        initialTerms.add(new TermInt(n3));
        initialTerms.add(new TermInt(n4));

        List<List<Term>> queue = new ArrayList<>();
        queue.add(initialTerms);

        return bfs_helper(queue);
    }

    private LnStrm<Term> dfs_helper(List<Term> terms) {
        // base: one term remaining
        if (terms.size() == 1) {
            Term term = terms.get(0);
            if (isTarget(term.eval())) {
                return new LnStrm<>(() -> 
                    new LnStcn<>(term, new LnStrm<>(() -> new LnStcn<>()))
                );
            } else {
                return new LnStrm<>(() -> new LnStcn<>());
            }
        }

        return dfs_enumerate_pairs(terms, 0, 1);
    }

    private LnStrm<Term> dfs_enumerate_pairs(List<Term> terms, int i , int j) {
        int size = terms.size();

        // base case: exhausted all pairs
        if (i >= size - 1) {
            return new LnStrm<>(() -> new LnStcn<>());
        }

        if (j >= size) {
            return dfs_enumerate_pairs(terms, i + 1, i + 2);
        }

        Term t1 = terms.get(i);
        Term t2 = terms.get(j);

        return concat_streams( 
            dfs_try_all_operations(terms, i, j, t1, t2, 0),
            dfs_enumerate_pairs(terms, i, j + 1)
        );
    }

    private LnStrm<Term> dfs_try_all_operations(List<Term> terms, int i , int j, Term t1, Term t2, int opIdx) {
        if (opIdx >= operations.length) {
            return new LnStrm<>(() -> new LnStcn<>());
        }

        String op = operations[opIdx];

        // Try t1 op t2
        LnStrm<Term> stream1 = dfs_apply_operation(terms, i, j, t1, t2, op);
        
        // For non-commutative operations, also try t2 op t1
        LnStrm<Term> stream2 = new LnStrm<>(() -> new LnStcn<>());
        if (op.equals("-") || op.equals("/")) {
            stream2 = dfs_apply_operation(terms, i, j, t2, t1, op);
        }

        return concat_streams(stream1, concat_streams(stream2, dfs_try_all_operations(terms, i, j, t1, t2, opIdx + 1)));
    }

    private LnStrm<Term> dfs_apply_operation(List<Term> terms, int i, int j, Term first, Term second, String op) {
        Term newTerm = new TermOpr(op, first, second);
        double result = newTerm.eval();

        if (Double.isNaN(result) || Double.isInfinite(result)) {
            return new LnStrm<>(() -> new LnStcn<>());
        }

        List<Term> newTerms = combineTerms(terms, i, j, newTerm);
        return dfs_helper(newTerms);
    }

    public LnStrm<Term> GameOf24_dfs_solve
	(int n1, int n2, int n3, int n4) {
        List<Term> initialTerms = new ArrayList<>();
        initialTerms.add(new TermInt(n1));
        initialTerms.add(new TermInt(n2));
        initialTerms.add(new TermInt(n3));
        initialTerms.add(new TermInt(n4));

        return dfs_helper(initialTerms);
    }

    private LnStrm<Term> bfs_helper(List<List<Term>> currentLevel) {
        if (currentLevel.isEmpty()) {
            return new LnStrm<>(() -> new LnStcn<>());
        }

        List<Term> solutions = new ArrayList<>();
        List<List<Term>> nextLevel = new ArrayList<>();

        for (List<Term> state : currentLevel) {
            if (state.size() == 1) {
                Term term = state.get(0);
                if (isTarget(term.eval())) {
                    solutions.add(term);
                }
            } else {
                bfs_generate_successors(state, nextLevel);
            }
        }

        return concat_streams( 
            list_to_stream(solutions), 
            bfs_helper(nextLevel)
        );
    }

    private void bfs_generate_successors(List<Term> terms, List<List<Term>> nextLevel) {
        int size = terms.size();

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                Term t1 = terms.get(i);
                Term t2 = terms.get(j);

                for (String op : operations) {
                    Term newTerm1 = new TermOpr(op, t1, t2);
                    if (!Double.isNaN(newTerm1.eval()) && !Double.isInfinite((newTerm1.eval()))) {
                        nextLevel.add(combineTerms(terms, i, j, newTerm1));
                    }
                    if (op.equals("-") || op.equals("/")) {
                        Term newTerm2 = new TermOpr(op, t2, t1);
                        if (!Double.isNaN(newTerm2.eval()) && !Double.isInfinite(newTerm2.eval())) {
                            nextLevel.add(combineTerms(terms, i, j, newTerm2));
                        }
                    }
                }

            }
        }
    }

    private LnStrm<Term> list_to_stream(List<Term> list) {
        return list_to_stream_helper(list, 0);
    }

    private LnStrm<Term> list_to_stream_helper(List<Term> list, int idx) {
        return new LnStrm<>(() -> {
            if (idx >= list.size()) {
                return new LnStcn<>();
            } else {
                return new LnStcn<>(list.get(idx), list_to_stream_helper(list, idx + 1));
            }
        });
    }

    public static void main(String[] args) {Assign07_02 solver = new Assign07_02();
        
        System.out.println("=== Testing DFS Solver ===");
        testSolver(solver, "DFS", 3, 3, 8, 8);
        testSolver(solver, "DFS", 1, 5, 5, 5);
        testSolver(solver, "DFS", 4, 6, 6, 8);
        
        System.out.println("\n=== Testing BFS Solver ===");
        testSolver(solver, "BFS", 3, 3, 8, 8);
        testSolver(solver, "BFS", 1, 5, 5, 5);
        testSolver(solver, "BFS", 4, 6, 6, 8);
    }
    
    private static void testSolver(Assign07_02 solver, String method, 
                                    int n1, int n2, int n3, int n4) {
        System.out.println("\n" + method + " solving for: " + n1 + ", " + n2 + ", " + n3 + ", " + n4);
        
        LnStrm<Term> solutions = method.equals("DFS") 
            ? solver.GameOf24_dfs_solve(n1, n2, n3, n4)
            : solver.GameOf24_bfs_solve(n1, n2, n3, n4);
        
        final int[] count = {0};
        final int MAX_DISPLAY = 5;
        
        solutions.foritm0(term -> {
            count[0]++;
            if (count[0] <= MAX_DISPLAY) {
                System.out.println("  Solution " + count[0] + ": " + term + " = " + term.eval());
            }
        });
        
        if (count[0] > MAX_DISPLAY) {
            System.out.println("  ... and " + (count[0] - MAX_DISPLAY) + " more solutions");
        }
        System.out.println("Total solutions found: " + count[0]);
    }
} // end of [public class Assign07_02{...}]
