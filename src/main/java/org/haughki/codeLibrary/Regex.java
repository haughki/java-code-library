package org.haughki.codeLibrary;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList
                ("http://my.damn.donkey.org/some/special/index.html",
                "https://my.damn.donkey.org/some/special/index.html",
                "http://my.damn.donkey.org",
                "https://my.damn.donkey.org",
                "http://my.damn.donkey.org/some/special/index.html?bug=nutz&fog=hat"
        );

        strings.forEach(s -> {
            Pattern p = Pattern.compile("https?://([^/]+)/?.*");
            Matcher m = p.matcher(s);
            System.out.println(m.find());
            System.out.println(m.group());
            System.out.println(m.group(1));
            System.out.println();
        });


    }
    
}
