package com.abnndn.day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Second {

    public static boolean matcher(String oneField) {
        List<String> values = Arrays.asList(oneField.split(":", 2));
        String value = values.get(1);

        try {
            if (oneField.contains("byr")) {
                System.out.print("byr ");
                if (value.length() != 4) {
                    return false;
                }
                if (Integer.parseInt(value) < 1920 || Integer.parseInt(value) > 2002) {
                    return false;
                }
            } else if (oneField.contains("iyr")) {
                System.out.print("iyr ");
                if (value.length() != 4) {
                    return false;
                }
                if (Integer.parseInt(value) < 2010 || Integer.parseInt(value) > 2020) {
                    return false;
                }
            } else if (oneField.contains("eyr")) {
                System.out.print("eyr ");
                if (value.length() != 4) {
                    return false;
                }
                if (Integer.parseInt(value) < 2020 || Integer.parseInt(value) > 2030) {
                    return false;
                }
            } else if (oneField.contains("hgt")) {
                System.out.print("hgt ");
                if (value.contains("cm")) {
                    String amount = Arrays.stream(value.split("c", 2)).findFirst().get();
                    if (Integer.parseInt(amount) < 150 || Integer.parseInt(amount) > 193) {
                        return false;
                    }
                } else if (value.contains("in")) {
                    String amount = Arrays.stream(value.split("i", 2)).findFirst().get();
                    if (Integer.parseInt(amount) < 59 || Integer.parseInt(amount) > 76) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else if (oneField.contains("hcl")) {
                System.out.print("hcl ");
                List<Character> valid = new ArrayList<>();
                valid.add('0');
                valid.add('1');
                valid.add('2');
                valid.add('3');
                valid.add('4');
                valid.add('5');
                valid.add('6');
                valid.add('7');
                valid.add('8');
                valid.add('9');
                valid.add('a');
                valid.add('b');
                valid.add('c');
                valid.add('d');
                valid.add('e');
                valid.add('f');
                if (value.charAt(0) != '#' || value.length() != 7) {
                    return false;
                }
                for (int i = 1; i < value.length(); i++) {
                    if (!valid.contains(value.charAt(i))) {
                        return false;
                    }
                }
            } else if (oneField.contains("ecl")) {
                System.out.print("ecl ");
                List<String> valid = new ArrayList<>();
                valid.add("amb");
                valid.add("blu");
                valid.add("brn");
                valid.add("gry");
                valid.add("grn");
                valid.add("hzl");
                valid.add("oth");

                if (!valid.contains(value)) {
                    return false;
                }

            } else if (oneField.contains("pid")) {
                System.out.print("pid ");
                if (value.length() != 9) {
                    return false;
                }
                int number = Integer.parseInt(value);

            }
        } catch (Exception e) {
            return false;
        }
         return true;
    }

    public static void calculateAnswer() {

        try {
            File myObj = new File("/Users/abhmitta/Desktop/AdventOfCode2020/src/com/abnndn/day4/input.txt");
            Scanner myReader = new Scanner(myObj);
            int ans=0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
//                System.out.println("-"+data+"-");
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

                if (oneField.size() < 7 || oneField.size() > 8) {
                    continue;
                }
                if (oneField.size() == 7) {
                    int notFound=0;
                    for(String field: oneField) {
                        if (field.contains("cid")) {
                            notFound = 1;
                            break;
                        }
                    }
                    if (notFound == 1) {
                        continue;
                    }
                }

                boolean dontmatch=false;
                for (String field: oneField) {
                    if (!matcher(field)) {
                        dontmatch=true;
                    }
                }
                if(!dontmatch) {
                    ans++;
                }
                System.out.println();
                System.out.println("ans: " + ans );
            }
            System.out.println(ans);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
