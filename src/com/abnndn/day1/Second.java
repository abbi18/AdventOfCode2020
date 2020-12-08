package com.abnndn.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Second {
    public static void calculateAnswer() {
        List<Integer> input = new ArrayList<>();
        try {
            File myObj = new File("/Users/abhmitta/Desktop/AdventOfCode2020/src/com/abnndn/day1/input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                input.add(Integer.valueOf(data));
            }
            myReader.close();

            Collections.sort(input);

            for(int first=0;first < input.size()-2; first++) {
                int sum = input.get(first);
                int second = first+1;
                int last = input.size()-1;

                while(second < last) {
                    System.out.println("in while " + first + " " + second + " " + last);
                    if (sum + input.get(second) + input.get(last) == 2020) {
                        System.out.println(input.get(first));
                        System.out.println(input.get(second));
                        System.out.println(input.get(last));
                        System.out.println(input.get(second) * input.get(first) * input.get(last));
                        return;
                    } else if (sum + input.get(second) + input.get(last) > 2020) {
                        last--;
                    } else {
                        second++;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
