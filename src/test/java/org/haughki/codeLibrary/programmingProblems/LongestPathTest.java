package org.haughki.codeLibrary.programmingProblems;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LongestPathTest {
    private LongestPath l;
    @Before
    public void setUp() throws Exception {
        this.l = new LongestPath();
    }

    @Test
    public void a() throws Exception {
        Assert.assertEquals(0, l.findLongest("a"));
    }

    @Test
    public void custom() throws Exception {
        Assert.assertEquals(34, l.findLongest("root1\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\t\t\tsubsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext\nroot2\n\tsubdir21\n\t\tsubsubdir21\n\t\tfile21.ext"));
    }
    
    @Test
    public void simple() throws Exception {
        Assert.assertEquals(12, l.findLongest("dir\n\tfile.txt"));
    }

    @Test
    public void basic() throws Exception {
        Assert.assertEquals(20, l.findLongest("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
    }

    @Test
    public void moreComplicated() throws Exception {
        Assert.assertEquals(32, l.findLongest("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));
    }

    @Test
    public void spaces1() throws Exception {
        Assert.assertEquals(12, l.findLongest("dir\n    file.txt"));
    }

    @Test
    public void spaces2() throws Exception {
        Assert.assertEquals(16, l.findLongest("dir\n        file.txt"));
    }

    @Test
    public void twoRoots() throws Exception {
        Assert.assertEquals(9, l.findLongest("a\n\tb.txt\na2\n\tb2.txt"));
    }

    @Test
    public void extreme() throws Exception {
        Assert.assertEquals(133, l.findLongest("skd\n\talskjv\n\t\tlskjf\n\t\t\tklsj.slkj\n\t\tsdlfkj.sdlkjf\n\t\tslkdjf.sdfkj\n\tsldkjf\n\t\tlskdjf\n\t\t\tslkdjf.sldkjf\n\t\t\tslkjf\n\t\t\tsfdklj\n\t\t\tlskjdflk.sdkflj\n\t\t\tsdlkjfl\n\t\t\t\tlskdjf\n\t\t\t\t\tlskdjf.sdlkfj\n\t\t\t\t\tlsdkjf\n\t\t\t\t\t\tsldkfjl.sdlfkj\n\t\t\t\tsldfjlkjd\n\t\t\tsdlfjlk\n\t\t\tlsdkjf\n\t\tlsdkjfl\n\tskdjfl\n\t\tsladkfjlj\n\t\tlskjdflkjsdlfjsldjfljslkjlkjslkjslfjlskjgldfjlkfdjbljdbkjdlkjkasljfklasjdfkljaklwejrkljewkljfslkjflksjfvsafjlgjfljgklsdf.a"));
    }
    
    @Test
    public void spaces3() throws Exception {
        Assert.assertEquals(20, l.findLongest("dir\n\t        file.txt\n\tfile2.txt"));
    }
}