package com.abnndn.sixth;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class First {

    public static void calculateAnswer() {
        List<String> input = new ArrayList<>();
        try {
            File myObj = new File("/Users/abhmitta/Desktop/AdventOfCode2020/src/com/abnndn/sixth/input.txt");
            Scanner myReader = new Scanner(myObj);
            int ans = 0;
            while (myReader.hasNextLine()) {
                StringBuilder curr = new StringBuilder();
                String data = myReader.nextLine();
                while (!data.equals("")) {
                    curr.append(data);
                    if (myReader.hasNextLine()) {
                        data = myReader.nextLine();
                    } else {
                        break;
                    }
                }
                input.add(curr.toString());
            }

            for (String s: input) {
                int[] mapper = new int[256];
                for (int i=0;i<s.length();i++) {
                    mapper[s.charAt(i)]++;
                }
                int curr = 0;
                for (int i='a';i<='z';i++) {
                    if (mapper[i]>0) {
                        curr++;
                    }
                }
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
