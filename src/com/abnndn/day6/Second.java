package com.abnndn.day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Second {

    public static void calculateAnswer() {
        List<String> input = new ArrayList<>();
        List<Integer> people = new ArrayList<>();
        try {
            File myObj = new File("/Users/abhmitta/Desktop/AdventOfCode2020/src/com/abnndn/sixth/input.txt");
            Scanner myReader = new Scanner(myObj);
            int ans = 0;
            while (myReader.hasNextLine()) {
                StringBuilder curr = new StringBuilder();
                String data = myReader.nextLine();
                int num = 0;
                while (!data.equals("")) {
                    curr.append(data);
                    num++;
                    if (myReader.hasNextLine()) {
                        data = myReader.nextLine();
                    } else {
                        break;
                    }
                }
                input.add(curr.toString());
                people.add(num);
            }

            for (int i=0;i<input.size();i++) {
                String s = input.get(i);
                int[] mapper = new int[256];
                for (int j=0;j<s.length();j++) {
                    mapper[s.charAt(j)]++;
                }
                int curr = 0;
                for (int j='a';j<='z';j++) {
                    if (mapper[j] == people.get(i)) {
                        curr++;
                    }
                }
                System.out.println("curr: " + curr);
                ans += curr;
            }
            System.out.println(ans);
            myReader.close();
        } catch(FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
