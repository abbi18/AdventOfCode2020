package com.abnndn.day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class First {

    char[] rightDir = {'E', 'S', 'W', 'N'};
    char[] leftDir = {'E', 'N', 'W', 'S'};

    char getNextDir(char currDir, int degree, char side) {
        char[] useArr;
        if (side == 'L') {
            useArr = leftDir;
        } else {
            useArr = rightDir;
        }

        int currIndex = -1;
        for (int i=0;i<useArr.length;i++) {
            if (useArr[i] == currDir) {
                currIndex = i;  break;
            }
        }

        int nextIndex = (currIndex+(degree/90))%4;
        return useArr[nextIndex];
    }

    int changeVertical(char instruc, int curr) {
        if (instruc == 'N') {
            return curr;
        } else if (instruc == 'S') {
            return -curr;
        }
        return 0;
    }

    int changeHorizontal(char instruc, int curr) {
        if (instruc == 'E') {
            return curr;
        } else if (instruc == 'W') {
            return -curr;
        }
        return 0;
    }

    public void calculateAnswer() {
        List<Entity> input = new ArrayList<>();
        try {
            File myObj = new File("/Users/abhmitta/Desktop/AdventOfCode2020/src/com/abnndn/day12/input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                input.add(Entity.builder()
                        .instruc(data.charAt(0))
                        .value(Integer.parseInt(data.substring(1)))
                        .build());
            }
            int north = 0; int east = 0;

            char currDir = 'E';
            int currIndex=0;
            for (Entity data: input) {
                north += changeVertical(data.getInstruc(), data.getValue());
                east += changeHorizontal(data.getInstruc(), data.getValue());
                if (data.getInstruc() == 'L' || data.getInstruc() == 'R') {
                    currDir = getNextDir(currDir, data.getValue(), data.getInstruc());
                } else if (data.getInstruc() == 'F') {
                    north += changeVertical(currDir, data.getValue());
                    east += changeHorizontal(currDir, data.getValue());
                }
                System.out.println(currIndex + " :: " + east + " , " + north + " ; " + currDir);
                currIndex++;
            }

            System.out.println(Math.abs(north) + Math.abs(east));
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
