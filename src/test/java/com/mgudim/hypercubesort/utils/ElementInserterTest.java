package com.mgudim.hypercubesort.utils;

import org.junit.Test;
import static org.junit.Assert.*;


public class ElementInserterTest {
    @Test()
    public void testDoOp() {
    ElementInserter inserter = new ElementInserter();
    Integer res;

    res = inserter.doOp(1, 2);
    assertTrue(res.intValue() == 3);

    res = inserter.doOp(1, 16);
    assertTrue(res.intValue() == 17);

    res = inserter.doOp(2, 16);
    assertTrue(res.intValue() == 18);

    res = inserter.doOp(2, 18);
    assertTrue(res.intValue() == 18);

    res = inserter.doOp(1, 18);
    assertTrue(res.intValue() == 19);
    return;
    }
}
