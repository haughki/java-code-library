package org.haughki.codeLibrary.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordChopper {
    static final String THE_WORD = "columbia";
    private static final int[] baseline;
    static List<Integer> indexes = new ArrayList<>(Arrays.asList(0));
    
    private static boolean canIncrement() {
        if (indexes.size() < baseline.length - 1)
            return true;
        
//        for (int i = baseline.length; i > -1 ; i--) {
//            if (indexes.size() != baseline.length)
//                return true;
//        }
        return false;
    }
    
    private static boolean canIncrementLast() {
        return lastValue() < baseline.length - 1;
    }
    
    private static Integer lastValue() {
        return indexes.get(lastIndex());
    }

    private static Integer lastIndex() {
        return indexes.size() - 1;
    }

    private static void incrementLast() {
        Integer incremented = lastValue();
        incremented++;
        indexes.set(lastIndex(), incremented);
    }
    
    static void findNextAndUpdate(final Integer currentPosition) {
        if (currentPosition > 0){
            final Integer nextPosition = currentPosition - 1;
            Integer nextValue = indexes.get(nextPosition);
            if (nextValue >= indexes.get(currentPosition) - 1)
                findNextAndUpdate(nextPosition);  // recurse
            else {
                reset(indexes.size(), nextPosition, nextValue + 1);
            }
        } else {
            // the current position, which we know is fully incremented, is the zero index.
            // Increase the size of the index is array, and reset all the values.
            reset(indexes.size() + 1, 0, 0);
        }
    }

    private static void reset(final int size, final int startingIndex, final int startingValue) {
        // starting at the starting index with starting value, set each subsequent index to 
        // starting value + 1, additive
        int newValue = startingValue;
        for (int i = startingIndex; i < size; i++) {
            if (i >= indexes.size())  // we've hit or passed the end of the list
                indexes.add(newValue);
            else
                indexes.set(i, newValue);
            newValue++;
        }
    }
    
    private static void generateNewWord(){
        StringBuilder theWord = new StringBuilder(THE_WORD);
        for (Integer index : indexes) {
            theWord.setCharAt(index, ' ');
        }
        System.out.println(theWord);
    }

    static {
        baseline = new int[THE_WORD.length()];
        for (int i = 0; i < THE_WORD.length(); i++) {
            baseline[i] = i;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(THE_WORD);
        generateNewWord();
        while (canIncrement()) {
            while (canIncrementLast()) {
                incrementLast();
                generateNewWord();
            }
            findNextAndUpdate(lastIndex());
            generateNewWord();
        }
    }
}
