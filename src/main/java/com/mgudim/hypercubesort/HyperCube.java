package com.mgudim.hypercubesort;

import java.util.LinkedList;
import java.util.Iterator;

import com.mgudim.hypercubesort.utils.SetElemOperation;
import com.mgudim.hypercubesort.utils.ElementRemover;
import com.mgudim.hypercubesort.utils.ElementInserter;
import com.mgudim.hypercubesort.utils.NextSubsetGenerator;


public class HyperCube {
    protected Integer[] arr;
    protected int dim = -1;
    protected NextSubsetGenerator generator;

    public HyperCube() {
        this.generator = new NextSubsetGenerator();
        return;
    }

    public void setArr(Integer[] arr) {
        //check if size of array is a power of two.
        int size = arr.length;
        assert size != 0;
        assert ((size & (size - 1)) == 0);

        this.dim = (int) (Math.log(size) / Math.log(2));
        this.arr = arr;
        this.generator.setSetSize(this.dim);
        this.generator.prepare();

        return;
    }

    protected void swap(int i, int j) {
        int temp = this.arr[i];
        this.arr[i] = this.arr[j];
        this.arr[j] = temp;

        return;
    }

    protected Iterable<Integer> getNbrs(int idx, SetElemOperation op) {
        LinkedList<Integer> nbrs = new LinkedList<Integer>();

        for (int i = 0; i < this.dim; i++) {
            Integer nbrCandidate = op.doOp(
                (int) Math.pow(2, i),
                idx
            );
            if (nbrCandidate.intValue() != idx) {
                nbrs.add(nbrCandidate);
            }
        }

        return nbrs;
    }

    protected Iterable<Integer> getInNbrs(int idx) {
        ElementRemover remover = new ElementRemover();
        return this.getNbrs(idx, remover);
    }

    protected Iterable<Integer> getOutNbrs(int idx) {
        ElementInserter inserter = new ElementInserter();
        return this.getNbrs(idx, inserter);
    }

    public void lowerPriority(int idx, Integer newPriority) {
        this.arr[idx] = newPriority;
        int currIdx = idx;
        int currVal = this.arr[currIdx];
        boolean violating = true;

        while (violating) {
            Iterator<Integer> inNbrsIter = (
                this
                .getInNbrs(currIdx)
                .iterator()
            );

            //get index and value of the largest violating neighbour
            int largestViolatingIdx = -1;
            Integer largestViolatingVal = Integer.MIN_VALUE;
            while(inNbrsIter.hasNext()) {
                int nbrIdx = inNbrsIter.next().intValue();
                int nbrVal = this.arr[nbrIdx];
                if (nbrVal > currVal) {
                    if (nbrVal > largestViolatingVal) {
                        largestViolatingIdx = nbrIdx;
                        largestViolatingVal = nbrVal;
                    }
                }
            }

            if (largestViolatingIdx == -1) {
                violating = false;
                continue;
            } else {
                this.swap(currIdx, largestViolatingIdx);
                currIdx = largestViolatingIdx;
            }
        }

        return;
    }

    public void raisePriority(int idx, Integer newPriority) {
        //symmetrical to lowerPriority, but we repeat
        //almost the same code here for clarity
        this.arr[idx] = newPriority;
        int currIdx = idx;
        int currVal = this.arr[currIdx];
        boolean violating = true;

        while (violating) {
            Iterator<Integer> inNbrsIter = (
                this
                .getOutNbrs(currIdx)
                .iterator()
            );

            //get index and value of the smallest violating neighbour
            int smallestViolatingIdx = -1;
            Integer smallestViolatingVal = Integer.MAX_VALUE;
            while(inNbrsIter.hasNext()) {
                int nbrIdx = inNbrsIter.next().intValue();
                int nbrVal = this.arr[nbrIdx];
                if (nbrVal < currVal) {
                    if (nbrVal < smallestViolatingVal) {
                        smallestViolatingIdx = nbrIdx;
                        smallestViolatingVal = nbrVal;
                    }
                }
            }

            if (smallestViolatingIdx == -1) {
                violating = false;
                continue;
            } else {
                this.swap(currIdx, smallestViolatingIdx);
                currIdx = smallestViolatingIdx;
            }
        }

        return;
    }

    public Integer[] sort() {
        for (int i = 0; i < this.arr.length; i++) {
            //insert all elements;
            int nextIndxToInsert = this.generator.getNext();
            this.lowerPriority(
                nextIndxToInsert,
                this.arr[nextIndxToInsert]
            );
        }

        Integer[] res = new Integer[this.arr.length];
        for (int i = 0; i < this.arr.length; i++) {
            res[i] = this.arr[0];
            this.raisePriority(0, Integer.MAX_VALUE);
        }

        return res;
    }
}
