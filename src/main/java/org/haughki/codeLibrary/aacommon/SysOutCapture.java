package org.haughki.codeLibrary.aacommon;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SysOutCapture implements AutoCloseable {
    private ByteArrayOutputStream outputStream;
    private PrintStream sysOutStream;

    public SysOutCapture() {
        outputStream = new ByteArrayOutputStream();  // Create a stream to hold the output
        PrintStream printStream = new PrintStream(outputStream);
        sysOutStream = System.out;  // Save the sysOutStream System.out so we can reset back to it when finished
        System.setOut(printStream);  // Tell Java to use your special stream
    }

    public String value() {
        System.out.flush();
        return outputStream.toString();
    }
    
    @Override
    public void close() throws Exception {
        resetSysOut();
    }
    
    private void resetSysOut() {
        System.out.flush();
        System.setOut(sysOutStream);
    }
}