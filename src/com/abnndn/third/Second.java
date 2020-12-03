package com.abnndn.third;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Second {

    public static void calculateAnswer() {
        List<String> input =  new ArrayList<>();
        try {
            File myObj = new File("/Users/abhmitta/Desktop/AdventOfCode2020/src/com/abnndn/third/input.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                input.add(data);
            }

            int len = input.get(0).length();

            List<Integer> right = new ArrayList();
            List<Integer> down = new ArrayList();

            right.add(1); down.add(1);
            right.add(3); down.add(1);
            right.add(5); down.add(1);
            right.add(7); down.add(1);
            right.add(1); down.add(2);

            BigInteger ans = BigInteger.ONE;
            for (int cas=0; cas<right.size(); cas++) {
                int index=0;
                int curr = 0;
                System.out.println(cas + " " + right.get(cas) + " " + down.get(cas));
                for (int i=0;i<input.size();i += down.get(cas)) {
                    index = index%len;
                    if(input.get(i).charAt(index) == '#') {
                        curr++;
                    }

                    index = index + right.get(cas);
                }
                ans  = ans.multiply(BigInteger.valueOf(curr));
                System.out.println(cas + " " + curr + " " + ans);
            }

            System.out.println(ans);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
