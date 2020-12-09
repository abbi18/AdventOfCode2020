package com.abnndn.day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Second {
    public void calculateAnswer() {
        List<BigDecimal> input = new ArrayList<>();
        try {
            File myObj = new File("/Users/abhmitta/Desktop/AdventOfCode2020/src/com/abnndn/day9/input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                input.add(BigDecimal.valueOf(Long.parseLong(data)));
            }

            List<BigDecimal> presum = new ArrayList<>();

            presum.add(input.get(0));
            for (int i=1;i<input.size();i++) {
                presum.add(input.get(i).add(presum.get(presum.size()-1)));
            }

            int start=-1;
            int end=-1;

            // Answer for first
            BigDecimal found = BigDecimal.valueOf(177777905);

            boolean foundRange = false;
            for (int i=0;i<presum.size();i++) {
                if (foundRange) {
                    break;
                }
                if (input.get(i).equals(found)) {
                    start = i; end = i;
                    break;
                }
                for (int j=0;j<i;j++) {
                    System.out.println(i + "::" + j);
                    if (presum.get(i).subtract(presum.get(j)).equals(found)) {
                        start = j+1;  end = i;
                        foundRange = true;
                        break;
                    }
                }

            }
            System.out.println("range: " + start + "::" + end);
            long biggest = input.get(start).longValue();
            long smallest = input.get(start).longValue();
            for (int i=start;i<=end;i++) {
                biggest = Math.max(biggest, input.get(i).longValue());
                smallest = Math.min(smallest, input.get(i).longValue());
            }
            System.out.println(biggest+smallest);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
