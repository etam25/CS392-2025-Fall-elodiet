class MyStack {
    private char[] stack;
    private int top;
    private int capacity;

    public MyStack(int size) {
        capacity = size;
        stack = new char[capacity];
        top = -1;
    }

    public void push(char item) {
        if (top < capacity - 1) {
            stack[++top] = item;
        }
    }

    public char pop() {
        if (top >= 0) {
            return stack[top--];
        }
        return '\0'; // Return null character if stack is empty
    }
    public boolean isEmpty() {
        return top == -1;
    }

    public char peel() {
        if (top >= 0) {
            return stack[top];
        }
        return '\0';
    }
}

public class Assign03_02 {
    static boolean balancedq(String text) {
        MyStack stack = new MyStack(text.length());

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();

                if ((ch == ')' && top != '(') || 
                    (ch == ']' && top != '[') ||
                    (ch == '}' && top != '{')) {
                        return false;
                    }
            }   
        }
        return stack.isEmpty();
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
            result ? "correct" : "error");
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

        System.out.println("unbalanced test cases (should return false):");
        for (String test : unbalancedTests) {
            boolean result = balancedq(test);
            System.out.printf("'%s' --> %s %s%n",
            test,
            result,
            !result ? "correct" : "error");
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