package org.haughki.codeLibrary;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Time {

    public static void main(String[] args) {
        // Convert a string in 12 hr time to 24 hr time.  Note that the default format seems to be 24hr, BUT, oddly,
        // if the secs are 00, it drops them, so while 07:05:45PM -> 19:05:45, 12:00:00 -> 12:00.  Hence the need for
        // formatting in the print statement.
        String time12 = "07:05:45PM";
        //String time12 = "12:00:00AM";
        LocalTime l = LocalTime.parse(time12, DateTimeFormatter.ofPattern("hh:mm:ssa"));
        System.out.println(l.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
}
