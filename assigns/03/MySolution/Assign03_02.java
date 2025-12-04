import Library.MyStack.*;

public class Assign03_02 {
    
    static boolean balancedq(String text) {
        int capacity = text.length() > 0 ? text.length() : 1;
        MyStack<Character> stack = new MyStackArray<Character>(capacity);
        
        try {
            for (int i = 0; i < text.length(); i++) {
                char ch = text.charAt(i);
                
                if (ch == '(' || ch == '[' || ch == '{') {
                    // Use push$exn for proper error handling
                    stack.push$exn(ch);
                } else if (ch == ')' || ch == ']' || ch == '}') {
                    // Check if stack is empty before popping
                    if (stack.isEmpty()) {
                        return false;
                    }
                    
                    // Use pop$exn for proper error handling
                    char top = stack.pop$exn();
                    
                    // Check if brackets match
                    if ((ch == ')' && top != '(') || 
                        (ch == ']' && top != '[') ||
                        (ch == '}' && top != '{')) {
                        return false;
                    }
                }
            }
            
            // String is balanced if stack is empty at the end
            return stack.isEmpty();
            
        } catch (MyStackFullExn e) {
            // This shouldn't happen since we initialize with text.length() capacity
            System.err.println("Error: Stack overflow - " + e.getMessage());
            return false;
        } catch (MyStackEmptyExn e) {
            // This shouldn't happen due to isEmpty() checks, but handle it anyway
            System.err.println("Error: Stack underflow - " + e.getMessage());
            return false;
        }
    }
    
    public static void main(String[] argv) {
        System.out.println("Testing balanced parentheses checker:");
        
        // Test cases that should return true (balanced)
        String[] balancedTests = {
            "()",
            "[]",
            "{}",
            "([])",
            "{()}",
            "[{}]",
            "([{}])",
            "{[()]}",
            "((()))",
            "[[[]]]",
            "{{{}}}", 
            "()[]{}",
            "(()[]{})",
            "{[()]}",
            "((())[{}])",
            "" // empty string should be balanced
        };
        
        System.out.println("Balanced test cases (should return true):");
        for (String test : balancedTests) {
            boolean result = balancedq(test);
            System.out.printf("'%s' --> %s %s%n", 
                test.isEmpty() ? "(empty)" : test,
                result,
                result ? "✓ correct" : "✗ error");
        }
        
        System.out.println();
        
        // Test cases that should return false (unbalanced)
        String[] unbalancedTests = {
            "(",
            ")",
            "[",
            "]", 
            "{",
            "}",
            "([)]",
            "([}])",
            "(()",
            "())",
            "[[]",
            "[]]",
            "{{}",
            "{}}",
            "([{})",
            "([{}]",
            ")(",
            "][",
            "}{",
            "()(",
            "())",
            "{[}]",
            "([)]"
        };
        
        System.out.println("Unbalanced test cases (should return false):");
        for (String test : unbalancedTests) {
            boolean result = balancedq(test);
            System.out.printf("'%s' --> %s %s%n",
                test,
                result,
                !result ? "✓ correct" : "✗ error");
        }
        
        System.out.println();
        System.out.println("Testing completed!");
        
        // Additional edge case testing
        System.out.println("\nEdge case testing:");
        System.out.printf("Single opening: '(' -> %s%n", balancedq("("));
        System.out.printf("Single closing: ')' -> %s%n", balancedq(")"));
        System.out.printf("Nested deeply: '(((())))' -> %s%n", balancedq("(((())))"));
        System.out.printf("Mixed valid: '()[]{()}' -> %s%n", balancedq("()[]{()}"));
        System.out.printf("Mixed invalid: '([)]' -> %s%n", balancedq("([)]"));
    }
}