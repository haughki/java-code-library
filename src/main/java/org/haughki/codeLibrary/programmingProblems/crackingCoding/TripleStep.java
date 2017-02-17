package org.haughki.codeLibrary.programmingProblems.crackingCoding;

/*
p.134
 */
public class TripleStep {
    public static void main(String[] args) {
        int num = 6;
        int[] count = new int[num + 1];
        count[0] = 1; // landed on the bottom step
        System.out.println(stepsDown(num, count));
        
        count = new int[num + 1];
        count[num] = 1;  // landed on the top step
        System.out.println(stepsUp(0, num, count));
    }
    
    // top down.  i.e., start at the top step, work down
    private static int stepsDown(int i, int[] count) {
        if (i < 0)
            return 0;
        
        if (count[i] == 0)
            count[i] = stepsDown(i - 1, count) + stepsDown(i - 2, count) + stepsDown(i - 3, count);
        return count[i];
    }

    // bottom up -- this seemed more intuitive to me, originally
    private static int stepsUp(int i, int target, int[] count) {
        if (i > target)
            return 0;

        if (count[i] == 0)
            count[i] = stepsUp(i + 1, target, count) + stepsUp(i + 2, target, count) + stepsUp(i + 3, target, count);
        return count[i];
    }

}
