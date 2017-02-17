package org.haughki.codeLibrary.programmingProblems;

/*
This problem is _very_ tricky.  It's not "easy".  My attempts, below, aren't really even close, though the idea
of breaking the buckets into groups (divide and conquer) is nice.
https://leetcode.com/problems/poor-pigs/
https://discuss.leetcode.com/topic/67482/solution-with-detailed-explanation
 */
public class PoorPigs {
    public static void main(String[] args) {
        DamnPigs d = new DamnPigs();
        System.out.println(d.poorPigs(1000, 15, 60));
        System.out.println(d.poorPigs(1000, 12, 60));
        System.out.println(d.poorPigs(3, 1, 1));
    }
}

class DamnPigs {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        if (buckets == 1)
            return 0;
        if (buckets == 2)
            return 1;
        
        int chances = minutesToTest / minutesToDie;
        int startingPigs = 2;
        while (true) {
            int bucketsLeft = buckets;
            int pigsLeft = startingPigs;
            for (int i = 0; i < chances; i++) {
                bucketsLeft = bucketsLeft / pigsLeft;
                if (bucketsLeft <= 1)
                    return startingPigs;
                bucketsLeft += bucketsLeft % pigsLeft;
                pigsLeft--;
                if (pigsLeft < 2)
                    break;
            }
            startingPigs++;
        }
    }

    public int poorPigs1(int buckets, int minutesToDie, int minutesToTest) {
        int chances = minutesToTest / minutesToDie;
        int startingPigs = 2;
        while (true) {
            int bucketsLeft = buckets;
            int pigsLeft = startingPigs;
            for (int i = 0; i < chances; i++) {
                bucketsLeft = bucketsLeft / pigsLeft;
                if (bucketsLeft <= 1)
                    return startingPigs;
                bucketsLeft += bucketsLeft % pigsLeft;
                pigsLeft--;
                if (pigsLeft < 2)
                    break;
            }
            startingPigs++;
        }
    }

}
