package org.haughki.codeLibrary.algorithm;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


class InputCars {
    List<Integer> list1;
    List<Integer> list2;
    String expectedOrder;
    InputCars(List<Integer> _list1, List<Integer> _list2, String _expectedOrder) {
        list1 = _list1;
        list2 = _list2;
        expectedOrder = _expectedOrder;
    }
}

public class CarMergeTest {
    private static final List<InputCars> testLists = new ArrayList<>();
    
    @Before
    public void setUp() throws Exception {
        List<Integer> a = Arrays.asList(1,1,2,3,4);
        List<Integer> b = Arrays.asList(2,2,3,4,5);
        testLists.add(new InputCars(a, b, "1122233445"));
        a = Arrays.asList(1,2,3,4,5);
        b = Arrays.asList(5,6,7,8,9);
        testLists.add(new InputCars(a, b, "1234556789"));
        a = Arrays.asList(1,6,6,8,9);
        b = Arrays.asList(4,5,5,6,10);
        testLists.add(new InputCars(a, b, "14556668910"));
    }
    
    @Test
    public void mergeCars() throws Exception {
        CarMerge merger = new CarMerge();
        ArrayList<CarMerge> m = new ArrayList<>();
        for (InputCars toMerge : testLists) {
            String result = merger.mergeCars(toMerge.list1, toMerge.list2);
            Assert.assertEquals(toMerge.expectedOrder, result);
            result = merger.mergeCars(toMerge.list2, toMerge.list1);
            Assert.assertEquals(toMerge.expectedOrder, result);
        }
    }

    @Test
    public void mergeCars2() {
    }
}