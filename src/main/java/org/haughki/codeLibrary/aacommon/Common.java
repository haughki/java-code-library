package org.haughki.codeLibrary.aacommon;

import java.io.File;
import java.nio.file.Path;

public class Common {
    public static final Path SOME_TEST_DATA;

    static {
        ClassLoader classLoader = new Common().getClass().getClassLoader();
        SOME_TEST_DATA = new File(classLoader.getResource("someTestData.data").getFile()).toPath();
    }
}
