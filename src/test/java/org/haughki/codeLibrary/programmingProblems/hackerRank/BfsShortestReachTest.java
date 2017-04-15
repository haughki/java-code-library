package org.haughki.codeLibrary.programmingProblems.hackerRank;

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
    private ByteArrayOutputStream outputStream;
    private PrintStream sysOutStream;
    
    @Before
    public void setUp() {
        // Create a stream to hold the output
        outputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outputStream);
        // IMPORTANT: Save the sysOutStream System.out! -- need to reset to this stream in teardown
        sysOutStream = System.out;
        System.setOut(ps);   // Tell Java to use your special stream
    }
    
    @After
    public void tearDown() {
        resetSysOut();
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

        resetSysOut();
        URL outputUrl = classLoader.getResource("BfsShortestReach_BigTestOutput_query1.data");
        String expected;
        if (outputUrl != null)
            expected = new String(Files.readAllBytes(Paths.get(outputUrl.toURI())));
        else
            throw new IllegalStateException("Test output data file is missing?");

        Assert.assertEquals(expected, outputStream.toString());
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

        resetSysOut();
        URL outputUrl = classLoader.getResource("BfsShortestReach_BigTestOutput.data");
        String expected;
        if (outputUrl != null)
            expected = new String(Files.readAllBytes(Paths.get(outputUrl.toURI())));
        else
            throw new IllegalStateException("Test output data file is missing?");

        Assert.assertEquals(expected, outputStream.toString());
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

        resetSysOut();

        Assert.assertEquals("18 6 6 18 12 12", outputStream.toString());
    }
    @Test
    public void oneNode() throws Exception {
        String[] args = new String[1];
        args[0] = "1 " +
                "1 0 " +
                "1 ";

        BfsShortestReach.main(args);

        resetSysOut();
        // Note: don't print anything if searching from start node to start node.
        Assert.assertEquals("", outputStream.toString());
    }

    @Test
    public void twoDisconnectedNodes() throws Exception {
        String[] args = new String[1];
        args[0] = "1 " +
                "2 0 " +
                "1 ";

        BfsShortestReach.main(args);

        resetSysOut();
        // Note: don't print anything if searching from start node to start node.
        Assert.assertEquals("-1", outputStream.toString());
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

        resetSysOut();
        
        Assert.assertEquals("6", outputStream.toString());
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

        resetSysOut();
        // Note: don't print anything if searching from start node to start node.
        Assert.assertEquals("6 12 18", outputStream.toString());
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

        resetSysOut();
        Assert.assertEquals("6 6 -1\r\n" +
                            "-1 6", outputStream.toString());
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

        resetSysOut();
        Assert.assertEquals("6 6 12 12 -1 -1\r\n" +
                            "-1 6", outputStream.toString());
    }
    
    private void resetSysOut() {
        // Put things back
        System.out.flush();
        System.setOut(sysOutStream);
    }

}