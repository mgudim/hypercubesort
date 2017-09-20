package com.mgudim.hypercubesort.utils;


//removes element
public class ElementRemover implements SetElemOperation {
    public Integer doOp(int elem, int subset) {
        return new Integer((~elem) & subset);
    }
}
