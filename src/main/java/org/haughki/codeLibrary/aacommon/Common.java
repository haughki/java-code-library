package org.haughki.codeLibrary.aacommon;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;

public class Common {
    public static final Path SOME_TEST_DATA;

    static {
        ClassLoader classLoader = Common.class.getClassLoader();
        SOME_TEST_DATA = new File(classLoader.getResource("someTestData.data").getFile()).toPath();
    }

    public static int[] createArray(int size) {
        return createArray(size, 100);
    }

    public static int[] createArray(int size, int eachRange) {
        int[] array = new int[size];
        int item;
        for(int i=0;i<size;i++){
            item = (int)(Math.random()*eachRange);
            array[i] = item;
        }
        return array;
    }

    public static void printArray(int[] array) {
        Arrays.stream(array).forEach(value -> System.out.print(value + " "));
    }
}
