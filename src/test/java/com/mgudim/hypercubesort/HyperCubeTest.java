package com.mgudim.hypercubesort;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Iterator;

import com.mgudim.hypercubesort.TestUtils;


public class HyperCubeTest {
    @Test()
    public void testSetArr() {
        HyperCube hypercube = new HyperCube();

        try{
            hypercube.setArr(
                new Integer[]{
                    new Integer(1),
                    new Integer(2),
                    new Integer(3)
                }
            );
            fail("should throw AssertionError");
        } catch(AssertionError e) {
        }

        hypercube.setArr(
            new Integer[]{
            new Integer(1),
            new Integer(2),
            new Integer(3),
            new Integer(4)
            }
        );
        assertTrue(hypercube.dim == 2);
        assertTrue(
            Arrays.deepEquals(hypercube.arr, new Integer[]{1, 2, 3, 4})
        );

        return;
    }

    @Test
    public void testSwap() {
        HyperCube hypercube = new HyperCube();
        Integer[] arr = new Integer[4];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Integer(i);
        }
        hypercube.setArr(arr);

        hypercube.swap(3, 0);
        assertTrue(
            Arrays.deepEquals(
                hypercube.arr,
                new Integer[]{
                    new Integer(3),
                    new Integer(1),
                    new Integer(2),
                    new Integer(0)
                }
            )
        );
        hypercube.swap(1, 2);
        assertTrue(
            Arrays.deepEquals(
                hypercube.arr,
                new Integer[]{
                    new Integer(3),
                    new Integer(2),
                    new Integer(1),
                    new Integer(0)
                }
            )
        );

        return;
    }

    @Test
    public void testGetInNbrs() {
        HyperCube hypercube = new HyperCube();
        Integer[] arr = new Integer[16];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Integer(i);
        }
        hypercube.setArr(arr);

        Iterator<Integer> res = hypercube.getInNbrs(11).iterator();
        assertTrue(res.next().intValue() == 10);
        assertTrue(res.next().intValue() == 9);
        assertTrue(res.next().intValue() == 3);

        return;
    }

    @Test
    public void testOutInNbrs() {
        HyperCube hypercube = new HyperCube();
        Integer[] arr = new Integer[16];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Integer(i);
        }
        hypercube.setArr(arr);
        Iterator<Integer> res = null;

        res = hypercube.getOutNbrs(10).iterator();
        assertTrue(res.next().intValue() == 11);
        assertTrue(res.next().intValue() == 14);

        res = hypercube.getOutNbrs(3).iterator();
        assertTrue(res.next().intValue() == 7);
        assertTrue(res.next().intValue() == 11);

        return;
    }

    @Test
    public void testLowerPriority() {
        HyperCube hypercube = new HyperCube();
        Integer[] arr = new Integer[]{
            new Integer(3),
            new Integer(8),
            new Integer(2),
            new Integer(4),
            new Integer(7),
            new Integer(6),
            new Integer(5),
            new Integer(1)
        };
        hypercube.setArr(arr);

        hypercube.lowerPriority(0, 3);
        assertTrue(Arrays.deepEquals(hypercube.arr, arr));

        hypercube.lowerPriority(1, 8);
        assertTrue(Arrays.deepEquals(hypercube.arr, arr));

        hypercube.lowerPriority(2, 2);
        assertTrue(
            Arrays.deepEquals(
                hypercube.arr,
                new Integer[]{
                    new Integer(2),
                    new Integer(8),
                    new Integer(3),
                    new Integer(4),
                    new Integer(7),
                    new Integer(6),
                    new Integer(5),
                    new Integer(1)
                }
            )
        );

        hypercube.lowerPriority(3, 4);
        assertTrue(
            Arrays.deepEquals(
                hypercube.arr,
                new Integer[]{
                    new Integer(2),
                    new Integer(4),
                    new Integer(3),
                    new Integer(8),
                    new Integer(7),
                    new Integer(6),
                    new Integer(5),
                    new Integer(1)
                }
            )
        );

        hypercube.lowerPriority(4, 7);
        assertTrue(
            Arrays.deepEquals(
                hypercube.arr,
                new Integer[]{
                    new Integer(2),
                    new Integer(4),
                    new Integer(3),
                    new Integer(8),
                    new Integer(7),
                    new Integer(6),
                    new Integer(5),
                    new Integer(1)
                }
            )
        );

        hypercube.lowerPriority(5, 6);
        assertTrue(
            Arrays.deepEquals(
                hypercube.arr,
                new Integer[]{
                    new Integer(2),
                    new Integer(4),
                    new Integer(3),
                    new Integer(8),
                    new Integer(6),
                    new Integer(7),
                    new Integer(5),
                    new Integer(1)
                }
            )
        );

        hypercube.lowerPriority(6, 5);
        assertTrue(
            Arrays.deepEquals(
                hypercube.arr,
                new Integer[]{
                    new Integer(2),
                    new Integer(4),
                    new Integer(3),
                    new Integer(8),
                    new Integer(5),
                    new Integer(7),
                    new Integer(6),
                    new Integer(1)
                }
            )
        );

        hypercube.lowerPriority(7, 1);
        assertTrue(
            Arrays.deepEquals(
                hypercube.arr,
                new Integer[]{
                    new Integer(1),
                    new Integer(2),
                    new Integer(3),
                    new Integer(4),
                    new Integer(5),
                    new Integer(7),
                    new Integer(6),
                    new Integer(8)
                }
            )
        );

        hypercube.lowerPriority(7, Integer.MIN_VALUE);
        assertTrue(
            Arrays.deepEquals(
                hypercube.arr,
                new Integer[]{
                    Integer.MIN_VALUE,
                    new Integer(2),
                    new Integer(3),
                    new Integer(4),
                    new Integer(1),
                    new Integer(5),
                    new Integer(6),
                    new Integer(7)
                }
            )
        );

        return;
    }

    @Test
    public void testRaisePriority() {
        HyperCube hypercube = new HyperCube();
        Integer[] arr = new Integer[]{
            new Integer(1),
            new Integer(2),
            new Integer(3),
            new Integer(4),
            new Integer(5),
            new Integer(7),
            new Integer(6),
            new Integer(8)
        };
        hypercube.setArr(arr);

        hypercube.raisePriority(0, Integer.MAX_VALUE);
        assertTrue(
            Arrays.deepEquals(
                hypercube.arr,
                new Integer[]{
                    new Integer(2),
                    new Integer(4),
                    new Integer(3),
                    new Integer(8),
                    new Integer(5),
                    new Integer(7),
                    new Integer(6),
                    Integer.MAX_VALUE
                }
            )
        );

        hypercube.raisePriority(2, 4);
        assertTrue(
            Arrays.deepEquals(
                hypercube.arr,
                new Integer[]{
                    new Integer(2),
                    new Integer(4),
                    new Integer(4),
                    new Integer(8),
                    new Integer(5),
                    new Integer(7),
                    new Integer(6),
                    Integer.MAX_VALUE
                }
            )
        );

        return;
    }

    @Test
    public void testSort() {
        HyperCube hypercube = new HyperCube();
        TestUtils utils = new TestUtils();

        for (int i = 2; i < 15; i++) {
            int size = (int) Math.pow(2, i);
            System.out.println(
                "Testing with array size: "
                + Integer.toString(size)
            );
            for (int j = 0; j < 100; j++) {
                Integer[] arr = utils.getRandomArray(size, 10000);
                hypercube.setArr(arr);
                Integer[] res = hypercube.sort();
                assertTrue(utils.isSorted(res));
            }
            System.out.println("OK.");

        }

    return;
    }
}
