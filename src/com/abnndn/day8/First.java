package com.abnndn.day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class First {

    boolean inLoop = false;
    List<Entity> input;
    int ans = 0;

    void getAns(Integer index) {
        if (inLoop) {
            return;
        }
        Entity entity = input.get(index);
        if (entity.getVisited()) {
            inLoop = true;
            return;
        }
        entity.setVisited(true);
        if (entity.command.equals("nop")) {
            index++;
        } else if (entity.command.equals("acc")) {
            ans += entity.getValue();
            index++;
        } else if (entity.command.equals("jmp")) {
            index += entity.getValue();
        }
        getAns(index);
    }

    public void calculateAnswer() {
        input = new ArrayList<>();
        try {
            File myObj = new File("/Users/abhmitta/Desktop/AdventOfCode2020/src/com/abnndn/day8/input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                List<String> values = Arrays.asList(data.split(" ", 2));

                int value = Integer.parseInt(values.get(1).substring(1));
                if (values.get(1).charAt(0) == '-') {
                    value = -1 * value;
                }
                input.add(Entity.builder()
                        .command(values.get(0))
                        .value(value)
                        .visited(false)
                        .build());
            }

            getAns(0);
            System.out.println(ans);

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
