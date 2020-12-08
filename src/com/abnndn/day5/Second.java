package com.abnndn.day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Second {

    public static void calculateAnswer() {
        try {
            File myObj = new File("/Users/abhmitta/Desktop/AdventOfCode2020/src/com/abnndn/day5/input.txt");
            Scanner myReader = new Scanner(myObj);
            int ans = Integer.MIN_VALUE;
            List<Integer> input = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

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

                input.add(minRow*8 + minCol);

            }
            Collections.sort(input);

            for (int i=0;i<input.size()-1;i++) {
                if (input.get(i+1) -input.get(i) == 2) {
                    System.out.println(input.get(i) + 1);
                    break;
                }
            }
            myReader.close();
        } catch (
                FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
