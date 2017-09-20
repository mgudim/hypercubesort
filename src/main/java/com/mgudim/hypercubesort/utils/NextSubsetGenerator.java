package com.mgudim.hypercubesort.utils;

import java.util.LinkedList;

public class NextSubsetGenerator {
    protected int setSize = -1;
    protected LinkedList<Integer> intSubsets = new LinkedList<Integer>();

    public NextSubsetGenerator() {
    }

    public void setSetSize(int setSize) {
        this.setSize = setSize;
        return;
    }

    protected String[] getSubsets(int setSize, int subsetSize) {
        if (subsetSize == 0) {
            String empty = "";
            for (int i = 0; i < setSize; i++) {
                empty += "0";
            }
            return new String[]{empty};
        }
        if (setSize == 0) {
            return new String[]{};
        }

        String subsets = "";
        int startIndx = 0;
        while (startIndx + subsetSize - 1 < setSize) {
            String prefix = "";
            for (int i = 0; i < startIndx; i++) {
                prefix += "0";
            }
            prefix += "1";

            String[] smallerSubsets = this.getSubsets(
                setSize - startIndx - 1,
                subsetSize - 1
            );
            if (smallerSubsets.length == 0) {
                subsets = "1";
            }
            for (String smallerSubset : smallerSubsets) {
                subsets += (prefix + smallerSubset + ",");
            }
            startIndx++;
        }

        return subsets.split(",");
    }

    protected int fromBinary(String binaryString) {
        int num = 0;
        int highestExp = binaryString.length() - 1;
        for (int i = 0; i < highestExp + 1;  i++) {
            if (Character.toString(binaryString.charAt(i)).equals("1")) {
                num += (int) Math.pow(2, highestExp - i);
            }
        }

        return num;
    }

    public void prepare() {
        for (int i = 0; i < this.setSize + 1; i++) {
            String[] strSubsets = this.getSubsets(this.setSize, i);
            for (String strSubset : strSubsets) {
                intSubsets.add(this.fromBinary(strSubset));
            }
        }
    }

    public int getNext() {
        return this.intSubsets.remove().intValue();
    }
}
