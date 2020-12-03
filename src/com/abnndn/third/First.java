package com.abnndn.third;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class First {

    public static void calculateAnswer() {
        List<String> input =  new ArrayList<>();
        try {
            File myObj = new File("/Users/abhmitta/Desktop/AdventOfCode2020/src/com/abnndn/third/input.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                input.add(data);
            }

            int ans = 0;
            int len = input.get(0).length();
            int index=0;
            for (String s: input) {
                    index = index%len;

                    if(s.charAt(index) == '#') {
                        ans++;
                    }

                    index = index+3;
            }
            System.out.println(ans);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
