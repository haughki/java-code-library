package org.haughki.codeLibrary.programmingProblems.hackerRank;

import java.util.Scanner;

public class RoadsAndLibraries {
    public static void main(String[] args) {
        
    }
}

class RoadsAndLibs {
    public void calcCost() {
        String input = "2 3 3 2 1 1 2 3 1 2 3 6 6 2 5 1 3 3 4 2 4 1 2 2 3 5 6";
        Scanner in = new Scanner(input);

        //Scanner in = new Scanner(System.in);

        int q = in.nextInt();
        for(int i = 0; i < q; i++){
            int cities = in.nextInt();
            int roads = in.nextInt();
            int libCost = in.nextInt();
            int roadCost = in.nextInt();
            for(int j = 0; j < roads; j++){
                int city_1 = in.nextInt();
                int city_2 = in.nextInt();
            }
        }

        
    }
}