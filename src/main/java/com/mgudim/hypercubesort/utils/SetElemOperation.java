package com.mgudim.hypercubesort.utils;


//may represent operation of adding or deleting
//element represented by elem, from subset represented by subset
public interface SetElemOperation {
    Integer doOp(int elem, int subset);
}
