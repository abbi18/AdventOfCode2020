package com.abnndn.day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Second {

    List<Entity> input;
    int invoked = 0;

    Integer getAns(Integer index) {
        invoked++;
        Entity entity = input.get(index);
        if (entity.getVisited()) {
            return Integer.MIN_VALUE;
        }
        Integer value = 0;
        if (entity.command.equals("acc")) {
            value = entity.getValue();
        }

        if (index == input.size() - 1) {
            return value;
        }

        entity.setVisited(true);
        if (!entity.command.equals("jmp")) {
            index++;
        } else {
            index += entity.getValue();
        }

        Integer downStream = getAns(index);
        if (downStream == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return  downStream + value;
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

            Integer ans = Math.max(Integer.MIN_VALUE, getAns(0));

            for (int i=0;i<input.size();i++) {
                for (Entity entity: input) {
                    entity.setVisited(false);
                }
                String previousCommand = "acc";
                if (input.get(i).getCommand().equals("jmp")) {
                    previousCommand = "jmp";
                    input.get(i).setCommand("nop");
                } else if (input.get(i).getCommand().equals("nop")) {
                    previousCommand = "nop";
                    input.get(i).setCommand("jmp");
                }

                ans = Math.max(ans, getAns(0));

                input.get(i).setCommand(previousCommand);
            }

            System.out.println("Recur function Invoked: " + invoked);
            System.out.println(ans);

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
