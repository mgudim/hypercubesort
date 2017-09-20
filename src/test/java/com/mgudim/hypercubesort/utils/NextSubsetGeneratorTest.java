package com.mgudim.hypercubesort.utils;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;


public class NextSubsetGeneratorTest {
    @Test()
    public void testGetSubsets() {
        NextSubsetGenerator generator = new NextSubsetGenerator();
        String[] res;

        res = generator.getSubsets(1, 1);
        assertTrue(
            Arrays.deepEquals(
                res,
                new String[]{
                    "1"
                }
            )
        );

        res = generator.getSubsets(2, 1);
        assertTrue(
            Arrays.deepEquals(
                res,
                new String[]{
                    "10",
                    "01"
                }
            )
        );

        res = generator.getSubsets(5, 1);
        assertTrue(
            Arrays.deepEquals(
                res,
                new String[]{
                    "10000",
                    "01000",
                    "00100",
                    "00010",
                    "00001",
                }
            )
        );

        res = generator.getSubsets(5, 5);
        assertTrue(
            Arrays.deepEquals(
                res,
                new String[]{
                    "11111"
                }
            )
        );


        res = generator.getSubsets(5, 2);
        assertTrue(
            Arrays.deepEquals(
                res,
                new String[]{
                    "11000",
                    "10100",
                    "10010",
                    "10001",
                    "01100",
                    "01010",
                    "01001",
                    "00110",
                    "00101",
                    "00011"
                }
            )
        );

        return;
    }

    @Test()
    public void testFromBinary() {
        NextSubsetGenerator generator = new NextSubsetGenerator();
        int res;

        res = generator.fromBinary("0000");
        assertTrue(res == 0);

        res = generator.fromBinary("0011");
        assertTrue(res == 3);

        res = generator.fromBinary("10101");
        assertTrue(res == 21);
    }

    @Test()
    public void testPrepare() {
        NextSubsetGenerator generator = new NextSubsetGenerator();
        generator.setSetSize(5);

        generator.prepare();
        //for (int num : generator.intSubsets) {
        //    System.out.println(num);
        //}

        int size = generator.intSubsets.size();
        assertTrue(size == 32);

        Integer[] intSubsetsArr = generator.intSubsets.toArray(
            new Integer[size]
        );
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < i; j++) {
                assertTrue(intSubsetsArr[i] != intSubsetsArr[j]);
            }
        }
    }

    @Test
    public void testGetNext() {
        NextSubsetGenerator generator = new NextSubsetGenerator();
        generator.setSetSize(5);

        generator.prepare();
        for (int i = 0; i < 32; i++) {
            System.out.println(generator.getNext());
        }
    }
}
