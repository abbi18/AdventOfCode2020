package com.abnndn.day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class First {
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

            int one=0;
            int three=0;

            for (int i=0;i<input.size();i++) {
                System.out.print(input.get(i) + " ");
            }
            System.out.println();
            for (int i=1;i<input.size();i++) {
                if (input.get(i)-input.get(i-1) == 1) {
                    one++;
                }
                if (input.get(i)-input.get(i-1) == 3) {
                    three++;
                }
            }

            System.out.println(one + " : " + three);
            System.out.println(three*one);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
