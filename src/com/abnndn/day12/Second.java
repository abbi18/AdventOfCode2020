package com.abnndn.day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Second {

    void rotate90(Entity eWay, Entity nWay, Entity data) {
        int tmp = eWay.getValue();
        eWay.setValue(Math.abs(nWay.getValue()));
        nWay.setValue(Math.abs(tmp));

        if (eWay.getInstruc() == 'E' && nWay.getInstruc() == 'N') {
            if (data.getInstruc() == 'R') {
                nWay.setInstruc('S');
            } else if (data.getInstruc() == 'L') {
                eWay.setInstruc('W');
            }
        } else if (eWay.getInstruc() == 'E' && nWay.getInstruc() == 'S') {
            if (data.getInstruc() == 'R') {
                eWay.setInstruc('W');
            } else if (data.getInstruc() == 'L') {
                nWay.setInstruc('N');
            }
        } else if (eWay.getInstruc() == 'W' && nWay.getInstruc() == 'S') {
            if (data.getInstruc() == 'R') {
                nWay.setInstruc('N');
            } else if (data.getInstruc() == 'L') {
                eWay.setInstruc('E');
            }
        } else if (eWay.getInstruc() == 'W' && nWay.getInstruc() == 'N') {
            if (data.getInstruc() == 'R') {
                eWay.setInstruc('E');
            } else if (data.getInstruc() == 'L') {
                nWay.setInstruc('S');
            }
        }

        if (eWay.getInstruc() == 'W') {
            eWay.setValue(-1 * eWay.getValue());
        }
        if (nWay.getInstruc() == 'S') {
            nWay.setValue(-1 * nWay.getValue());
        }
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

            Entity eWay = Entity.builder()
                    .instruc('E')
                    .value(10)
                    .build();
            Entity nWay = Entity.builder()
                    .instruc('N')
                    .value(1)
                    .build();

            for (Entity data: input) {

                if (data.getInstruc() == 'N') {
                    nWay.setValue(nWay.getValue() + data.getValue());
                } else if (data.getInstruc() == 'S') {
                    nWay.setValue(nWay.getValue() - data.getValue());
                } else if (data.getInstruc() == 'E') {
                    eWay.setValue(eWay.getValue() + data.getValue());
                } else if (data.getInstruc() == 'W') {
                    eWay.setValue(eWay.getValue() - data.getValue());
                }

                if (eWay.getValue() < 0) { eWay.setInstruc('W'); }
                if (eWay.getValue() > 0) { eWay.setInstruc('E'); }

                if (nWay.getValue() < 0) { nWay.setInstruc('S'); }
                if (nWay.getValue() > 0) { nWay.setInstruc('N'); }

                if (data.getInstruc() == 'F') {
                    east += data.getValue()*eWay.getValue();
                    north += data.getValue()*nWay.getValue();
                } else if (data.getInstruc() == 'L' || data.getInstruc() == 'R'){
                    if (data.getValue()%180 != 0) {
                        for(int i=90;i<=data.getValue();i+=90) {
                            rotate90(eWay, nWay, data);
                        }
                    } else if (data.getValue() == 180) {
                        eWay.setValue(eWay.getValue()*-1);
                        eWay.setInstruc(eWay.getInstruc() == 'E' ? 'W' : 'E');
                        nWay.setValue(nWay.getValue()*-1);
                        nWay.setInstruc(nWay.getInstruc() == 'N' ? 'S' : 'N');
                    }
                }

                System.out.println(data.getValue() + " -- " + data.getInstruc());
                System.out.println("waypoint :: " + eWay + ","  + nWay);
                System.out.println("values :: " + east + ","  + north);
                System.out.println();

                if (eWay.getInstruc() == 'E' && eWay.getValue()<0) {
                    System.out.println("abnndn1");
                    return;
                }
                if (nWay.getInstruc() == 'N' && nWay.getValue()<0) {
                    System.out.println("abnndn2");
                    return;
                }
                if (eWay.getInstruc() == 'W' && eWay.getValue()>0) {
                    System.out.println("abnndn3");
                    return;
                }
                if (eWay.getInstruc() == 'S' && eWay.getValue()>0) {
                    System.out.println("abnndn4");
                    return;
                }
            }

            System.out.println(Math.abs(north) + Math.abs(east));
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
