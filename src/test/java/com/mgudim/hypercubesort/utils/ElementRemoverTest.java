package com.mgudim.hypercubesort.utils;

import org.junit.Test;
import static org.junit.Assert.*;


public class ElementRemoverTest {
    @Test()
    public void testDoOp() {
    ElementRemover remover = new ElementRemover();
    Integer res;

    res = remover.doOp(1, 2);
    assertTrue(res.intValue() == 2);

    res = remover.doOp(2, 18);
    assertTrue(res.intValue() == 16);

    res = remover.doOp(8, 7);
    assertTrue(res.intValue() == 7);

    res = remover.doOp(2, 7);
    assertTrue(res.intValue() == 5);

    return;
    }
}
