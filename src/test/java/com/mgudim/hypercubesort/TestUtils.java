package com.mgudim.hypercubesort;

import java.util.Random;

public class TestUtils {
    public boolean isSorted(Integer[] arr) {
        int size = arr.length;
        if (size < 2) {
            return true;
        }

        boolean sorted = true;
        int currIndx = 1;

        while(sorted && (currIndx < size)) {
            if (arr[currIndx] < arr[currIndx - 1]) {
                sorted = false;
            }
            currIndx++;
        }

        return sorted;
    }

    public Integer[] arrayOfConsecutive(int size) {
        Integer[] res = new Integer[size];
        for (int i = 0; i < size; i++) {
            res[i] = new Integer(i);
        }

        return res;
    }

    public Integer[] getRandomArray(int size, int bound) {
        Random rand = new Random();
        Integer[] res = new Integer[size];
        for (int i = 0; i < size; i++) {
            res[i] = new Integer(rand.nextInt(bound));
        }

        return res;
    }

    //not all permutations have equal probability, but ok.
    public void randomlyPermuteArray(Integer[] arr, int numPermutations) {
        int size = arr.length;
        Random rand = new Random();
        Integer temp;
        for (int i = 0; i < numPermutations; i++) {
            int indx1 = rand.nextInt(size);
            int indx2 = rand.nextInt(size);
            //swap
            temp = arr[indx1];
            arr[indx1] = arr[indx2];
            arr[indx2] = temp;
        }

        return;
    }
}
