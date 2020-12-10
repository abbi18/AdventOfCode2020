package com.abnndn.day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Second {
    public void calculateAnswer() {
        List<Integer> input = new ArrayList<>();
        try {
            File myObj = new File("/Users/abhmitta/Desktop/AdventOfCode2020/src/com/abnndn/day10/input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                input.add(Integer.valueOf(data));
            }
            myReader.close();

            Collections.sort(input);

            input.add(0,0);
            input.add(input.get(input.size()-1)+3);

            Map<Integer, Boolean> mapper = new HashMap<>();
            Map<Integer, Long> total = new HashMap<>();

            for (int i=0;i<input.size();i++) {
                mapper.put(input.get(i), true);
                System.out.print(input.get(i) + " ");
            }
            System.out.println();

            total.put(0, (long) 1);
            System.out.println(0 + " :: " + total.get(0));
            for (int i=1;i<input.size();i++) {
                long ways = 0;
                if (mapper.containsKey(input.get(i)-1)) {
                    ways += total.get(input.get(i)-1);
                }
                if (mapper.containsKey(input.get(i)-2)) {
                    ways += total.get(input.get(i)-2);
                }
                if (mapper.containsKey(input.get(i)-3)) {
                    ways += total.get(input.get(i)-3);
                }
                System.out.println(input.get(i) + " :: "+ ways);
                total.put(input.get(i), ways);
            }

            System.out.println(total.get(input.get(input.size()-1)));

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
