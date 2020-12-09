package com.abnndn.day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class First {
    public void calculateAnswer() {
        List<BigDecimal> input = new ArrayList<>();
        try {
            File myObj = new File("/Users/abhmitta/Desktop/AdventOfCode2020/src/com/abnndn/day9/input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                input.add(BigDecimal.valueOf(Long.parseLong(data)));
            }

            List<BigDecimal> sorted = new ArrayList<>();
            for (int i=0; i<25;i++) {
                int j=0;
                for (;j<sorted.size();j++) {
                    if (sorted.get(j).longValue() >= input.get(i).longValue()) {
                        break;
                    }
                }
                System.out.println(input.get(i) + " :: " + j);
                sorted.add(j, input.get(i));
            }

            for (int i=25;i<input.size();i++) {
                for (int j=0;j<sorted.size();j++) {
                    System.out.print(sorted.get(j) + " ");
                }
                System.out.println();

                int start=0;
                int end=sorted.size()-1;
                Boolean found = false;
                while(start < end) {
                    if (sorted.get(start).add(sorted.get(end)).equals(input.get(i))) {
                        System.out.println(input.get(i) + " = " + sorted.get(start) + " + " + sorted.get(end));
                        found = true;
                        break;
                    }
                    if (sorted.get(start).add(sorted.get(end)).longValue() > input.get(i).longValue()) {
                        end--;
                    } else {
                        start++;
                    }
                }
                if (!found) {
                    System.out.println("Found it: " + input.get(i));
                    return;
                }

                for (int j=0;j<sorted.size();j++) {
                    if (sorted.get(j).equals(input.get(i - 25))) {
                        sorted.remove(j);
                        break;
                    }
                }

                int j=0;
                for (;j<sorted.size();j++) {
                    if (sorted.get(j).longValue() >= input.get(i).longValue()) {
                        break;
                    }
                }
                sorted.add(j, input.get(i));
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
