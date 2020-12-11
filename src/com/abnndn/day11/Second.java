package com.abnndn.day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Second {

    List<Entity> range = new ArrayList<>();

    void fillPresum() {
        range.add(Entity.builder().row(-1).col(-1).startFromTop(true).startFromLeft(true).build());
        range.add(Entity.builder().row(-1).col(0).startFromTop(true).startFromLeft(true).build());
        range.add(Entity.builder().row(-1).col(+1).startFromTop(true).startFromLeft(false).build());
        range.add(Entity.builder().row(0).col(-1).startFromTop(true).startFromLeft(true).build());
        range.add(Entity.builder().row(0).col(+1).startFromTop(true).startFromLeft(false).build());
        range.add(Entity.builder().row(+1).col(-1).startFromTop(false).startFromLeft(true).build());
        range.add(Entity.builder().row(+1).col(0).startFromTop(false).startFromLeft(true).build());
        range.add(Entity.builder().row(+1).col(+1).startFromTop(false).startFromLeft(false).build());
    }

    void setupAdjacent(List<List<Character>> state) {

        for (int i=0;i<state.size();i++) {
            for (int j=0;j<state.get(i).size();j++) {
                System.out.print(state.get(i).get(j));
            }
            System.out.println();
        }
        System.out.println();

        for (int count=0;count<8;count++) {
            List<List<Boolean>> presum = new ArrayList<>();
            for (int i=0;i<state.size();i++) {
                List<Boolean> currCal = new ArrayList<>();
                for (int j = 0; j < state.get(i).size(); j++) {
                    currCal.add(false);
                }
                presum.add(currCal);
            }
            if (range.get(count).isStartFromTop()) {
                for (int i = 0; i < state.size(); i++) {
                    if (range.get(count).isStartFromLeft()) {
                        for (int j = 0; j < state.get(i).size(); j++) {
                            if (state.get(i).get(j) == 'L') {
                                presum.get(i).set(j, false);
                            } else if (state.get(i).get(j) == '#') {
                                presum.get(i).set(j, true);
                            } else {
                                if (isValid(i + range.get(count).getRow(), j + range.get(count).getCol(), state)) {
                                    presum.get(i).set(j, presum.get(i + range.get(count).getRow()).get(j + range.get(count).getCol()));
                                }
                            }
                        }
                    } else {
                        for (int j = state.get(i).size() - 1; j >= 0; j--) {
                            if (state.get(i).get(j) == 'L') {
                                presum.get(i).set(j, false);
                            } else if (state.get(i).get(j) == '#') {
                                presum.get(i).set(j, true);
                            } else {
                                if (isValid(i + range.get(count).getRow(), j + range.get(count).getCol(), state)) {
                                    presum.get(i).set(j, presum.get(i + range.get(count).getRow()).get(j + range.get(count).getCol()));
                                }
                            }
                        }
                    }
                }
            } else {
                for (int i = state.size()-1; i>=0; i--) {
                    if (range.get(count).isStartFromLeft()) {
                        for (int j = 0; j < state.get(i).size(); j++) {
                            if (state.get(i).get(j) == 'L') {
                                presum.get(i).set(j, false);
                            } else if (state.get(i).get(j) == '#') {
                                presum.get(i).set(j, true);
                            } else {
                                if (isValid(i + range.get(count).getRow(), j + range.get(count).getCol(), state)) {
                                    presum.get(i).set(j, presum.get(i + range.get(count).getRow()).get(j + range.get(count).getCol()));
                                }
                            }
                        }
                    } else {
                        for (int j = state.get(i).size() - 1; j >= 0; j--) {
                            if (state.get(i).get(j) == 'L') {
                                presum.get(i).set(j, false);
                            } else if (state.get(i).get(j) == '#') {
                                presum.get(i).set(j, true);
                            } else {
                                if (isValid(i + range.get(count).getRow(), j + range.get(count).getCol(), state)) {
                                    presum.get(i).set(j, presum.get(i + range.get(count).getRow()).get(j + range.get(count).getCol()));
                                }
                            }
                        }
                    }
                }
            }
            range.get(count).setPrecal(presum);
        }
    }

    boolean isValid(int i, int j, List<List<Character>> input) {
        return !(i<0 || i>=input.size() || j<0 || j>=input.get(0).size());
    }

    int if5AdjacentFilled(int row, int col, List<List<Character>> input) {
        int count=0;
        for (int i=0;i<8;i++) {
            if (isValid(row+range.get(i).getRow(), col+range.get(i).getCol(), input)) {
                if (range.get(i).precal.get(row+range.get(i).getRow()).get(col+range.get(i).getCol())) {
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
                int besidesFilled = if5AdjacentFilled(i, j, prev);
                if (prev.get(i).get(j) == 'L' && besidesFilled == 0) {
                    curr.get(i).set(j, '#');
                    change++;
                } else if (prev.get(i).get(j) == '#' && besidesFilled >= 5) {
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

        fillPresum();

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
            setupAdjacent(input1);
            int change = countChange(input1, input2);

            int dofirst = 1;
            while(change > 0) {
                System.out.println("change :: " + change);
                if (dofirst == 0) {
                    setupAdjacent(input1);
                    change = countChange(input1, input2);
                } else {
                    setupAdjacent(input2);
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