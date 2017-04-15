package org.haughki.codeLibrary.programmingProblems;

import java.util.*;

/*
3/24
https://leetcode.com/problems/valid-parentheses/#/description

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */
public class ValidParentheses {
    public static void main(String[] args) {
        Validator v = new Validator();
        System.out.println(v.isValid(""));
        System.out.println(v.isValid("()"));
        System.out.println(v.isValid("()[]{}"));
        System.out.println(v.isValid("([])"));
        System.out.println(v.isValid("{()[{}]}"));
        System.out.println(v.isValid("([{}[((){()})]])"));
        
        
        System.out.println();
        System.out.println(v.isValid(")"));
        System.out.println(v.isValid("(]"));
        System.out.println(v.isValid("(("));
        System.out.println(v.isValid("}()}"));
        System.out.println(v.isValid("([)]"));
        System.out.println(v.isValid("([]}"));
        System.out.println(v.isValid("([]))"));
        System.out.println(v.isValid("([{}[((){()}))])"));
        
    }
}

class Validator {

    // Second attempt: Really just playing around here, trying to eek out a little more speed than below.
    public boolean isValid(String s) {
        Map<Character, Character> brackets = new HashMap<>(3);
        brackets.put('(', ')');
        brackets.put('[', ']');
        brackets.put('{', '}');

        char[] stack = new char[s.length()];

        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (brackets.containsKey(curr))
                stack[index++] = curr;
            else {
                index--;
                if (index < 0)
                    return false;
                char popped = stack[index];
                if (!brackets.get(popped).equals(curr))
                    return false;
            }
        }

        return index == 0;
    }

    // first attempt:  using Deque, works fine, still O(n), but not quite as fast as above.
    public boolean isValid0(String s) {
        Map<Character, Character> brackets = new HashMap<>(3);
        brackets.put('(', ')');
        brackets.put('[', ']');
        brackets.put('{', '}');

        Deque<Character> stack = new ArrayDeque<>(s.length()/2);

        for (int i = 0; i < s.length(); i++) {
            Character curr = s.charAt(i);
            if (brackets.containsKey(curr))
                stack.push(curr);
            else {
                Character popped = stack.pollFirst();
                if (popped == null)
                    return false;
                if (!brackets.get(popped).equals(curr))
                    return false;
            }
        }
        
        return stack.isEmpty();
    }
}