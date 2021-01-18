package com.rd.stack;

public class StackMatchParens {
    public static void main(String[] args) {
        System.out.println(String.format(
                "Has matching parens %s? : %s", "(abcd)", hasMatchingParens("(abcd)")));
        System.out.println(String.format(
                "Has matching parens %s? : %s", "{{{{}}", hasMatchingParens("{{{{}}")));
        System.out.println(String.format(
                "Has matching parens %s? : %s", "{{{{}}})", hasMatchingParens("{{{{}}})")));
        System.out.println(String.format(
                "Has matching parens %s? : %s", "{{{}}}()", hasMatchingParens("{{{}}}()")));
        System.out.println(String.format(
                "Has matching parens %s? : %s", "{{{}}]()", hasMatchingParens("{{{}}]()")));
        System.out.println(String.format(
                "Has matching parens %s? : %s", "{{}}([]){}{}{}{}{[[[[]]]]}",
                hasMatchingParens("{{}}([]){}{}{}{}{[[[[]]]]}")));
    }

    public static boolean hasMatchingParens(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            if (!isParanthesis(ch))
                continue;
            Character peek = stack.peek();
            if (peek != null && isMatching(peek, ch))
                stack.pop();
            else
                stack.push(ch);
        }
        System.out.println(stack);
        return stack.isEmpty();
    }

    private static boolean isParanthesis(Character ch) {
        return ch.equals('{') || ch.equals('}') ||
                ch.equals('[') || ch.equals(']') ||
                ch.equals('(') || ch.equals(')');
    }

    private static boolean isMatching(Character top, Character curr) {
        return (top.equals('{') && curr.equals('}'))
                || (top.equals('[') && curr.equals(']'))
                || (top.equals('(') && curr.equals(')'));

    }
}
