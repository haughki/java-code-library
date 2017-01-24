package org.haughki.codeLibrary.programmingProblems;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BinWatchSolutionTest {
    @Test
    public void readBinaryWatch_8() throws Exception {
        BinWatchSolution bws = new BinWatchSolution();
        List<String> valid = bws.readBinaryWatch(8);
        List<String> expected = Arrays.asList("11:59", "11:55", "11:47", "11:31", "7:59", "7:55", "7:47", "7:31");
        Assert.assertEquals(expected.size(), valid.size());
        for (int i = 0; i < expected.size(); i++) {
            Assert.assertEquals(expected.get(i), valid.get(i));
        }
    }

    @Test
    public void readBinaryWatch_4() throws Exception {
        BinWatchSolution bws = new BinWatchSolution();
        List<String> valid = bws.readBinaryWatch(4);
        List<String> expected = Arrays.asList("11:32", "11:16", "11:08", "11:04", "11:02", "11:01", "10:48", "10:40", "10:36", "10:34", "10:33", "10:24", "10:20", "10:18", "10:17", "10:12", "10:10", "10:09", "10:06", "10:05", "10:03", "9:48", "9:40", "9:36", "9:34", "9:33", "9:24", "9:20", "9:18", "9:17", "9:12", "9:10", "9:09", "9:06", "9:05", "9:03", "8:56", "8:52", "8:50", "8:49", "8:44", "8:42", "8:41", "8:38", "8:37", "8:35", "8:28", "8:26", "8:25", "8:22", "8:21", "8:19", "8:14", "8:13", "8:11", "8:07", "7:32", "7:16", "7:08", "7:04", "7:02", "7:01", "6:48", "6:40", "6:36", "6:34", "6:33", "6:24", "6:20", "6:18", "6:17", "6:12", "6:10", "6:09", "6:06", "6:05", "6:03", "5:48", "5:40", "5:36", "5:34", "5:33", "5:24", "5:20", "5:18", "5:17", "5:12", "5:10", "5:09", "5:06", "5:05", "5:03", "4:56", "4:52", "4:50", "4:49", "4:44", "4:42", "4:41", "4:38", "4:37", "4:35", "4:28", "4:26", "4:25", "4:22", "4:21", "4:19", "4:14", "4:13", "4:11", "4:07", "3:48", "3:40", "3:36", "3:34", "3:33", "3:24", "3:20", "3:18", "3:17", "3:12", "3:10", "3:09", "3:06", "3:05", "3:03", "2:56", "2:52", "2:50", "2:49", "2:44", "2:42", "2:41", "2:38", "2:37", "2:35", "2:28", "2:26", "2:25", "2:22", "2:21", "2:19", "2:14", "2:13", "2:11", "2:07", "1:56", "1:52", "1:50", "1:49", "1:44", "1:42", "1:41", "1:38", "1:37", "1:35", "1:28", "1:26", "1:25", "1:22", "1:21", "1:19", "1:14", "1:13", "1:11", "1:07", "0:58", "0:57", "0:54", "0:53", "0:51", "0:46", "0:45", "0:43", "0:39", "0:30", "0:29", "0:27", "0:23", "0:15");
        Assert.assertEquals(expected.size(), valid.size());
        for (int i = 0; i < expected.size(); i++) {
            Assert.assertEquals(expected.get(i), valid.get(i));
        }
    }

}