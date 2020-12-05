package com.abnndn.fourth;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class First {
    public static void calculateAnswer() {

        try {
            File myObj = new File("/Users/abhmitta/Desktop/AdventOfCode2020/src/com/abnndn/fourth/input.txt");
            Scanner myReader = new Scanner(myObj);
            int start=1;
            int ans=0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                List<String> onePass = new ArrayList<>();

                while (!data.equals("")) {
                    onePass.add(data);
                    if(myReader.hasNextLine()) {
                        data = myReader.nextLine();
                    } else {
                         break;
                    }
                }

                for(String value: onePass) {
                    System.out.print(value + " ");
                }
                System.out.println();

                List<String> oneField = new ArrayList<>();
                for(String input: onePass) {
                    oneField.addAll(Arrays.asList(input.split(" ")));
                }

                if (oneField.size() == 8) {
                    ans++;
                } else if (oneField.size() == 7) {
                    int notFound=0;
                    for(String field: oneField) {
                        if(field.contains("cid")) {
                            notFound = 1;
                        }
                    }
                    if (notFound == 0) {
                        ans++;
                    }
                }
            }
            System.out.println(ans);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
