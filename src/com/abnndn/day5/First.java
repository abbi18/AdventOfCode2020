package com.abnndn.day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class First {

    public static void calculateAnswer() {
        try {
            File myObj = new File("/Users/abhmitta/Desktop/AdventOfCode2020/src/com/abnndn/fifth/input.txt");
            Scanner myReader = new Scanner(myObj);
            int ans = Integer.MIN_VALUE;
            List<String> input = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                input.add(data);

                int minRow = 0; int maxRow = 127;

                for (int i=0;i<7;i++) {
                    if (data.charAt(i) == 'F') {
                        maxRow = (minRow+maxRow)/2;
                    } else {
                        minRow = (minRow+maxRow)/2 + 1;
                    }
                }

                if (minRow != maxRow) {
                    System.out.println("this is wrong in rows");
                    throw new RuntimeException();
                }

                int minCol = 0; int maxCol = 7;
                for (int i=7;i<10;i++) {
                    if (data.charAt(i) == 'L') {
                        maxCol = (minCol+maxCol)/2;
                    } else {
                        minCol = (minCol+maxCol)/2 + 1;
                    }
                }

                if (minCol != maxCol) {
                    System.out.println("this is wrong in columns");
                    throw new RuntimeException();
                }
                System.out.println(minRow + " " + minCol);
                System.out.println(minRow*8 + minCol);
                ans = Math.max(ans, minRow*8 + minCol);
            }
            System.out.println();
            System.out.println(ans);
            myReader.close();
        } catch (
                FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
