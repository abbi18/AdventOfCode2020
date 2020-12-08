package com.abnndn.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Second {

    public static void calculateAnswer() {
        List<Entity> input = new ArrayList<>();

        try {
            File myObj = new File("/Users/abhmitta/Desktop/AdventOfCode2020/src/com/abnndn/day2/input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] partition = data.split(" ", 3);

                String[] minMax = partition[0].split("-", 2);

                Entity entity = Entity.builder()
                        .min(Integer.parseInt(minMax[0]) - 1)
                        .max(Integer.parseInt(minMax[1]) - 1)
                        .letter(partition[1].charAt(0))
                        .word(partition[2])
                        .build();

                input.add(entity);
            }

            int ans=0;
            int index=0;
            for (Entity entity: input) {
                String word = entity.word;

                if (entity.min>word.length()) {
                    continue;
                }

                if(entity.max<word.length()) {
                    if (word.charAt(entity.min) == entity.letter && word.charAt(entity.max) == entity.letter) {

                    } else if (word.charAt(entity.min) == entity.letter || word.charAt(entity.max) == entity.letter) {
                        ans++;
                    }
                } else {
                    if (word.charAt(entity.min) == entity.letter) {
                        ans++;
                    }
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
