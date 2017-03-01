package org.haughki.codeLibrary.bitsAndBytes;


import java.util.Arrays;

/*
My play impl of a bit vector, just to understand it better.
 */
public class BitVector {
    private final int length;
    private final int[] vector;
    private final static int BUCKET_SIZE = 32;
    
    public BitVector(int length) {
        this.length = length;
        int numBuckets = this.length / BUCKET_SIZE;
        if (this.length % BUCKET_SIZE > 0)
            numBuckets++;
        vector = new int[numBuckets];
    }
    
    public void set(int key, boolean value) {
        int bucket = key / BUCKET_SIZE;
        if (bucket >= vector.length)
            throw new IllegalArgumentException("Bad key: " + key);
        int index = key % BUCKET_SIZE;
        int mask = 1 << index;
        if (value)
            vector[bucket] |= mask;
        else
            vector[bucket] &= ~mask;
    }
    
    public boolean get(int key) {
        int bucket = key / BUCKET_SIZE;
        if (bucket >= vector.length)
            throw new IllegalArgumentException("Bad key: " + key);
        int index = key % BUCKET_SIZE;
        int mask = 1 << index;
        return (vector[bucket] & mask) == mask; 
    }
    
    // ** TESTING ONLY
    public int[] getVector() {
        return Arrays.copyOf(vector, vector.length);
    }
}
