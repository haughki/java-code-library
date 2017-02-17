package org.haughki.codeLibrary.programmingProblems;

import org.junit.Test;


import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class WordChopperTest {
    @Test
    public void testFindNextAndUpdate() throws Exception {
        WordChopper.indexes = new ArrayList<>(Arrays.asList(4));
        WordChopper.findNextAndUpdate(0);
        assertEquals(2, WordChopper.indexes.size());
        assertEquals(0, (int)WordChopper.indexes.get(0));
        assertEquals(1, (int)WordChopper.indexes.get(1));

        WordChopper.indexes = new ArrayList<>(Arrays.asList(3, 4));
        WordChopper.findNextAndUpdate(1);
        assertEquals(3, WordChopper.indexes.size());
        assertEquals(0, (int)WordChopper.indexes.get(0));
        assertEquals(1, (int)WordChopper.indexes.get(1));
        assertEquals(2, (int)WordChopper.indexes.get(2));
        
        WordChopper.indexes = new ArrayList<>(Arrays.asList(2, 4));
        WordChopper.findNextAndUpdate(1);
        assertEquals(2, WordChopper.indexes.size());
        assertEquals(3, (int)WordChopper.indexes.get(0));
        assertEquals(4, (int)WordChopper.indexes.get(1));

        WordChopper.indexes = new ArrayList<>(Arrays.asList(0, 1, 4));
        WordChopper.findNextAndUpdate(2);
        assertEquals(3, WordChopper.indexes.size());
        assertEquals(0, (int)WordChopper.indexes.get(0));
        assertEquals(2, (int)WordChopper.indexes.get(1));
        assertEquals(3, (int)WordChopper.indexes.get(2));

        WordChopper.indexes = new ArrayList<>(Arrays.asList(0, 3, 4));
        WordChopper.findNextAndUpdate(2);
        assertEquals(3, WordChopper.indexes.size());
        assertEquals(1, (int)WordChopper.indexes.get(0));
        assertEquals(2, (int)WordChopper.indexes.get(1));
        assertEquals(3, (int)WordChopper.indexes.get(2));

        WordChopper.indexes = new ArrayList<>(Arrays.asList(2, 3, 4));
        WordChopper.findNextAndUpdate(2);
        assertEquals(4, WordChopper.indexes.size());
        assertEquals(0, (int)WordChopper.indexes.get(0));
        assertEquals(1, (int)WordChopper.indexes.get(1));
        assertEquals(2, (int)WordChopper.indexes.get(2));
        assertEquals(3, (int)WordChopper.indexes.get(3));

    }
}
    