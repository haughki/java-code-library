package org.haughki.codeLibrary.programmingProblems;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    public static void main(String[] args) {
        System.out.println(convert("DCXXI"));
        System.out.println(convert("MCMXCVI"));
        
    }
    
    private static int convert(String s) {
        int currVal, prevVal = 0;
        int result = 0;
        
        for (int i = 0; i < s.length(); i++) {
            //currVal = ROMAN.get(s.charAt(i));
            switch (s.charAt(i)) {
                case 'I': currVal = 1; break;
                case 'V': currVal = 5; break;
                case 'X': currVal = 10; break;
                case 'L': currVal = 50; break;
                case 'C': currVal = 100; break;
                case 'D': currVal = 500; break;
                case 'M': currVal = 1000; break;
                default: throw new IllegalStateException();
            }
            
            if (prevVal < currVal)
                result -= prevVal * 2;
            
            result += currVal;
            prevVal = currVal;
        }
        return result;
    }
}
