package com.example.windows7.myapplication.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MathProblem {

    ArrayList<Integer> firstInt = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
    ArrayList<Integer> secondInt = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));

    public MathProblem() {

    }

    public int getFirstInt() {
        Collections.shuffle(firstInt);
        return firstInt.get(0);
    }

    public void setFirstInt(ArrayList<Integer> firstInt) {
        this.firstInt = firstInt;
    }

    public int getSecondInt() {
        Collections.shuffle(secondInt);
        return secondInt.get(0);
    }

    public void setSecondInt(ArrayList<Integer> secondInt) {
        this.secondInt = secondInt;
    }
}
