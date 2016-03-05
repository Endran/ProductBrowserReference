/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser.example;

public class ClassUsingFinalHardToMock {

    public boolean callHardStaticMethodAndReturnParameter(boolean parameter) {
        HardToMockFinalClass.goodLuckMockingThisStaticMethod();
        return parameter;
    }

    public boolean callHardFinalMethodAndReturnParameter(HardToMockFinalClass hardToMockFinalClass, boolean parameter) {
        hardToMockFinalClass.goodLuckMockingThisFinalMethod();
        return parameter;
    }

    public boolean callHardMethodAndReturnParameter(HardToMockFinalClass hardToMockFinalClass, boolean parameter) {
        hardToMockFinalClass.goodLuckMockingThisNormalMethod();
        return parameter;
    }
}
