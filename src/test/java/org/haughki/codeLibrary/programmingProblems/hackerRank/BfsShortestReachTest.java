package org.haughki.codeLibrary.programmingProblems.hackerRank;

import org.haughki.codeLibrary.aacommon.SysOutCapture;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BfsShortestReachTest {
    private SysOutCapture sysOutCapture;
    
    @Before
    public void setUp() {
        sysOutCapture = new SysOutCapture();
    }
    
    @After
    public void tearDown() throws Exception {
        sysOutCapture.close();
    }

    @Test
    public void big_query1() throws Exception {
        String[] args = new String[2];
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL inputUrl = classLoader.getResource("BfsShortestReach_BigTestInput_query1.data");
        if (inputUrl != null)
            args[0] = inputUrl.getFile();
        else
            throw new IllegalStateException("Test input data file is missing?");
        BfsShortestReach.main(args);

                URL outputUrl = classLoader.getResource("BfsShortestReach_BigTestOutput_query1.data");
        String expected;
        if (outputUrl != null)
            expected = new String(Files.readAllBytes(Paths.get(outputUrl.toURI())));
        else
            throw new IllegalStateException("Test output data file is missing?");

        Assert.assertEquals(expected, sysOutCapture.value());
    }

    @Test
    public void big() throws Exception {
        String[] args = new String[2];
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL inputUrl = classLoader.getResource("BfsShortestReach_BigTestInput.data");
        if (inputUrl != null)
            args[0] = inputUrl.getFile();
        else
            throw new IllegalStateException("Test input data file is missing?");
        BfsShortestReach.main(args);

                URL outputUrl = classLoader.getResource("BfsShortestReach_BigTestOutput.data");
        String expected;
        if (outputUrl != null)
            expected = new String(Files.readAllBytes(Paths.get(outputUrl.toURI())));
        else
            throw new IllegalStateException("Test output data file is missing?");

        Assert.assertEquals(expected, sysOutCapture.value());
    }

    @Test
    public void simpleBig() throws Exception {
        String[] args = new String[1];
        args[0] = "1 " +
                "7 7 " +
                "1 3 " +
                "1 4 " +
                "3 7 " +
                "4 6 " +
                "7 5 " +
                "5 2 " +
                "6 2 " +
                "1 ";

        BfsShortestReach.main(args);

        
        Assert.assertEquals("18 6 6 18 12 12", sysOutCapture.value());
    }
    @Test
    public void oneNode() throws Exception {
        String[] args = new String[1];
        args[0] = "1 " +
                "1 0 " +
                "1 ";

        BfsShortestReach.main(args);

                // Note: don't print anything if searching from start node to start node.
        Assert.assertEquals("", sysOutCapture.value());
    }

    @Test
    public void twoDisconnectedNodes() throws Exception {
        String[] args = new String[1];
        args[0] = "1 " +
                "2 0 " +
                "1 ";

        BfsShortestReach.main(args);

                // Note: don't print anything if searching from start node to start node.
        Assert.assertEquals("-1", sysOutCapture.value());
    }

    @Test
    public void duplicateEdge() throws Exception {
        String[] args = new String[1];
        args[0] = "1 " +
                "2 2 " +
                "1 2 " +
                "1 2 " +
                "1 ";

        BfsShortestReach.main(args);

                
        Assert.assertEquals("6", sysOutCapture.value());
    }


    @Test
    public void fourConnected() throws Exception {
        String[] args = new String[1];
        args[0] = "1 " +
                "4 3 " +
                "1 2 " +
                "2 3 " +
                "3 4 " +
                "1 ";

        BfsShortestReach.main(args);

                // Note: don't print anything if searching from start node to start node.
        Assert.assertEquals("6 12 18", sysOutCapture.value());
    }

    // This is the "run code" test case for the problem.  Distance from one node to another, and a disconnected node.
    @Test
    public void first() throws Exception {
        String[] args = new String[1];
        args[0] = "2 " +
                "4 2 " +
                "1 2 " +
                "1 3 " +
                "1 " +
                "3 1 " +
                "2 3 " +
                "2 ";

        BfsShortestReach.main(args);

                Assert.assertEquals("6 6 -1\r\n" +
                            "-1 6", sysOutCapture.value());
    }
    
    // Two paths to 5, one shorter.  Also, the second query from the first case, just to make sure that still works.
    @Test
    public void twoPaths() throws Exception {
        String[] args = new String[1];
        args[0] = "2 " +
                "7 5 " +
                "1 2 " +
                "1 3 " +
                "2 4 " +
                "3 5 " +
                "4 5 " +
                "1 " +
                "3 1 " +
                "2 3 " +
                "2 ";
        BfsShortestReach.main(args);

                Assert.assertEquals("6 6 12 12 -1 -1\r\n" +
                            "-1 6", sysOutCapture.value());
    }
    
}