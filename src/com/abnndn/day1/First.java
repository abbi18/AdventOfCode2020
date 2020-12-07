package com.abnndn.day1;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

public class First {

    public static void calculateAnswer() {
        List<Integer> input = new ArrayList<>();
        try {
            File myObj = new File("/Users/abhmitta/Desktop/AdventOfCode2020/src/com/abnndn/first/input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                input.add(Integer.valueOf(data));
            }
            myReader.close();

            Collections.sort(input);

            for(int i: input) {
                System.out.print(i + " ,");
            }
            System.out.println();

            int first = 0;
            int last = input.size()-1;

            while(first < last) {
                int sum = input.get(first) + input.get(last);
                System.out.println("in while " + first + " " + last + " " + sum);
                if (input.get(first) + input.get(last) == 2020) {
                    System.out.println(input.get(first));
                    System.out.println(input.get(last));
                    System.out.println(input.get(first) * input.get(last));
                    return;
                } else if (input.get(first) + input.get(last) > 2020) {
                    last--;
                } else {
                    first++;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
