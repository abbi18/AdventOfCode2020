package com.abnndn.second;

import com.abnndn.second.entities.Entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class First {

    public static void calculateAnswer() {
        List<Entity> input = new ArrayList<>();

        try {
            File myObj = new File("/Users/abhmitta/Desktop/AdventOfCode2020/src/com/abnndn/second/input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] partition = data.split(" ", 3);

                String[] minMax = partition[0].split("-", 2);

                Entity entity = Entity.builder()
                        .min(Integer.parseInt(minMax[0]))
                        .max(Integer.parseInt(minMax[1]))
                        .letter(partition[1].charAt(0))
                        .word(partition[2])
                        .build();

                input.add(entity);
            }

            int ans=0;
            int index=0;
            for (Entity entity: input) {
                String word = entity.word;
                int[] intArray = new int[256];
                for (int i = 0; i < word.length(); i++) {
                    intArray[(int) word.charAt(i)]++;
                }
                if (intArray[(int) entity.letter] >= entity.min && intArray[(int) entity.letter] <= entity.max) {
                    System.out.println(intArray[(int) entity.letter] );
                    ans++;
                }
                System.out.println(" " + index + ": " + ans);
                index++;
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
