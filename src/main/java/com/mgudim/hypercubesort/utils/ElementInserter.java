package com.mgudim.hypercubesort.utils;


public class ElementInserter implements SetElemOperation {
    public Integer doOp(int elem, int subset) {
        return new Integer(elem | subset);
    }
}
