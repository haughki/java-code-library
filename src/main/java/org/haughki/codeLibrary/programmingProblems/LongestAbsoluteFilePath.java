package org.haughki.codeLibrary.programmingProblems;

import java.util.HashMap;
import java.util.Map;

/*
Suppose we abstract our file system by a string in the following manner:

The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:

dir
    subdir1
    subdir2
        file.ext
The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.

The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:

dir
    subdir1
        file1.ext
        subsubdir1
    subdir2
        subsubdir2
            file2.ext
The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty 
second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file 
file2.ext.

We are interested in finding the longest (number of characters) absolute path to a file within our file system. For 
example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length 
is 32 (not including the double quotes).

Given a string representing the file system in the above format, return the length of the longest absolute path to file 
in the abstracted file system. If there is no file in the system, return 0.

Note:
The name of a file contains at least a . and an extension.
The name of a directory or sub-directory will not contain a ..
Time complexity required: O(n) where n is the size of the input string.
The *only* special chars are \n and \t.  Every other char (even a space) is part of the dir or file name.

Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.
 */
public class LongestAbsoluteFilePath {
    public static void main(String[] args) {
        /* SEE UNIT TESTS */
        //133
        String s = "skd\n\talskjv\n\t\tlskjf\n\t\t\tklsj.slkj\n\t\tsdlfkj.sdlkjf\n\t\tslkdjf.sdfkj\n\tsldkjf\n\t\tlskdjf\n\t\t\tslkdjf.sldkjf\n\t\t\tslkjf\n\t\t\tsfdklj\n\t\t\tlskjdflk.sdkflj\n\t\t\tsdlkjfl\n\t\t\t\tlskdjf\n\t\t\t\t\tlskdjf.sdlkfj\n\t\t\t\t\tlsdkjf\n\t\t\t\t\t\tsldkfjl.sdlfkj\n\t\t\t\tsldfjlkjd\n\t\t\tsdlfjlk\n\t\t\tlsdkjf\n\t\tlsdkjfl\n\tskdjfl\n\t\tsladkfjlj\n\t\tlskjdflkjsdlfjsldjfljslkjlkjslkjslfjlskjgldfjlkfdjbljdbkjdlkjkasljfklasjdfkljaklwejrkljewkljfslkjflksjfvsafjlgjfljgklsdf.a";
        LongestPath l = new LongestPath();
        System.out.println(l.findLongest(s));
    }
}

class LongestPath {

    // Top-voted leetcode submission from sky-xu: https://discuss.leetcode.com/topic/55247/9-lines-4ms-java-solution
    public int findLongest2(String input) {
        String[] paths = input.split("\n");
        int[] stack = new int[paths.length + 1];
        int maxLen = 0;
        for(String s:paths){
            int lev = s.lastIndexOf("\t") + 1;
            stack[lev + 1] = stack[lev] + s.length() - lev + 1; // +1 for the trailing slash
            int curLen = stack[lev + 1];
            if(s.contains(".")) 
                maxLen = Math.max(maxLen, curLen - 1);  // -1 to remove the trailing slash (that was never there)
        }
        return maxLen;
    }

    // Modified original attempt based on top voted from sky-xu: 
    // https://discuss.leetcode.com/topic/55247/9-lines-4ms-java-solution
    // I like this because it doesn't use split.  Could use an array vs hashmap, but how to init?  Would ArrayList be
    // any better (really)?
    public int findLongest(String input) {
        int greatest = 0;
        Map<Integer, Integer> depthToTotal = new HashMap<>();
        depthToTotal.put(-1, 0); // sets the "no depth" to 0. I.e., "before" the root.
        int i = 0;
        int count = 1;  // initialize here and to 1 to handle the missing '\n' at the start of the string.  I.e.,
                        // the root dir doesn't start with '\n', but everything else does.  Thus.
        while(i < input.length()){
            if (++i >= input.length())
                return greatest;
            
            int depth = 0;
            char curr = input.charAt(i);
            while (curr == '\t') {
                depth++;
                i++;
                curr = input.charAt(i);
            }
            
            boolean isFile = false;
            while (curr != '\n') {
                i++;
                count++;
                if (curr == '.')
                    isFile = true;
                if (i < input.length())
                    curr = input.charAt(i);
                else 
                    break;
            }
            
            depthToTotal.put(depth, depthToTotal.get(depth - 1) + count + 1);  // add the possible trailing /
            int currentTotal = depthToTotal.get(depth);
            if (isFile)
                greatest = Math.max(greatest, currentTotal - 1); // remove the trailing / that was never there
            
            count = 0;  // All so that we can initialize this to 1 before the start of the first loop. ugh :(
        }

        return greatest;
    }

    public int findLongest1(String input) {
        int greatest = 0, total = 0, depth = 0;
        boolean lastWasFile = false;
        Map<Integer, Integer> depthToTotal = new HashMap<>();
        depthToTotal.put(-1, 0);
        int i = 0;
        while(i < input.length()){
            char curr = input.charAt(i);
            if (curr == '.') {
                total++; // count the '.'
                while (curr != '\n') {
                    i++;
                    total++;
                    if (i < input.length())
                        curr = input.charAt(i);
                    else
                        break;
                }
                total--;
                greatest = Math.max(greatest, total);
                lastWasFile = true;
            } else if (curr == '\n') {
                total++; // count the slash
                if (!lastWasFile)
                    depthToTotal.put(depth, total);
                else
                    lastWasFile = false;
                
                int newDepth = 0;
                curr = input.charAt(++i);
                while (curr == '\t') {
                    newDepth++;
                    i++;
                    curr = input.charAt(i);
                }

                if (newDepth <= depth)
                    total = depthToTotal.get(newDepth - 1);
                depth = newDepth;
            } else {
                total++;
                i++;
            }
        }
        
        return greatest;
    }


/*
    public int findLongest(String s, int i, int total, int depth, int greatest, Map<Integer, Integer> depthToTotal) {
        if (i >= s.length())
            return greatest;
        
        char curr = s.charAt(i);
        while(curr != '.' && curr != '\n') {
            total++;
            i++;
            curr = s.charAt(i);
        }
        total++; // for '\n' or '.'

        if (curr == '.') {
            i += 4; // move to next '\n' 
            greatest = Math.max(greatest, total + 3); // 3 == 'ext'
            if (i >= s.length())
                return greatest;
        } else 
            depthToTotal.put(depth, total);
        
        int newDepth = 0;
        curr = '\n';  // if this was '.', it's '\n' now
        while (curr == '\n' || curr == '\t') {
            newDepth++; 
            i++;
            curr = s.charAt(i);
        }
        newDepth--; // correct for \n "offset"
        
        if (newDepth <= depth)
            total = depthToTotal.get(newDepth - 1);
        
        return findLongest(s, i, total, newDepth, greatest, depthToTotal);
    }
*/
}