package com.abnndn.day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class First {

    int[] first = {-1,-1,-1,0,0,+1,+1,+1};
    int[] second = {-1,0,+1,-1,+1,-1,0,+1};

    boolean isValid(int i, int j, List<List<Character>> input) {
        return !(i<0 || i>=input.size() || j<0 || j>=input.get(0).size());
    }

    int if4AdjacentFilled(int row, int col, List<List<Character>> input) {
        int count=0;
        for (int i=0;i<8;i++) {
            if (isValid(row+first[i], col+second[i], input)) {
                if (input.get(row+first[i]).get(col+second[i]) == '#') {
                    count++;
                }
            }
        }
        return count;
    }

    int countChange(List<List<Character>> prev, List<List<Character>> curr) {
        int change = 0;
        for (int i=0;i<prev.size();i++) {
            for(int j=0;j<prev.get(i).size();j++) {
                curr.get(i).set(j, prev.get(i).get(j));
                int besidesFilled = if4AdjacentFilled(i, j, prev);
                if (prev.get(i).get(j) == 'L' && besidesFilled == 0) {
                    curr.get(i).set(j, '#');
                    change++;
                } else if (prev.get(i).get(j) == '#' && besidesFilled >= 4) {
                    curr.get(i).set(j, 'L');
                    change++;
                }
            }
        }
        return change;
    }

    int countAns(List<List<Character>> arr) {
        int ans=0;
        for (int i=0;i<arr.size();i++) {
            for (int j=0;j<arr.get(i).size();j++) {
                if (arr.get(i).get(j) == '#') {
                    ans++;
                }
            }
        }
        return ans;
    }

    public void calculateAnswer() {
        List<List<Character>> input1 = new ArrayList<>();
        List<List<Character>> input2 = new ArrayList<>();
        try {
            File myObj = new File("/Users/abhmitta/Desktop/AdventOfCode2020/src/com/abnndn/day11/input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                List<Character> string1 = new ArrayList<>();
                List<Character> string2 = new ArrayList<>();
                for (int i=0;i<data.length();i++) {
                    string1.add(data.charAt(i));
                    string2.add(data.charAt(i));
                }
                input1.add(string1);
                input2.add(string2);
            }
            myReader.close();

            for (int i=0;i<input1.size();i++) {
                for (int j=0;j<input1.get(i).size();j++) {
                        System.out.print(input1.get(i).get(j));
                }
                System.out.println();
            }
            System.out.println();

            int change = countChange(input1, input2);

            int dofirst = 1;

            while(change > 0) {
                if (dofirst == 0) {
                    change = countChange(input1, input2);
                } else {
                    change = countChange(input2, input1);
                }
                dofirst = (dofirst+1)%2;
            }

            System.out.println();
            int ans = dofirst == 0 ? countAns(input1) : countAns(input2);

            System.out.println(ans);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}


//        #.#L.L#.##
//        #LLL#LL.L#
//        L.#.L..#..
//        #L##.##.L#
//        #.#L.LL.LL
//        #.#L#L#.##
//        ..L.L.....
//        #L#L##L#L#
//        #.LLLLLL.L
//        #.#L#L#.##