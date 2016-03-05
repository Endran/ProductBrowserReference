/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser.example;

import java.util.Map;

public class ClassUsingOpenHardToMock {

    public boolean callHardStaticMethodAndReturnParameter(boolean parameter) {
        HardToMockOpenClass.goodLuckMockingThisStaticMethod();
        return parameter;
    }

    public boolean callHardOpenMethodAndReturnParameter(HardToMockOpenClass hardToMockOpenClass, boolean parameter) {
        hardToMockOpenClass.goodLuckMockingThisFinalMethod();
        return parameter;
    }

    public boolean callHardMethodAndReturnParameter(HardToMockOpenClass hardToMockOpenClass, boolean parameter) {
        hardToMockOpenClass.goodLuckMockingThisNormalMethod();
        return parameter;
    }

    public  Map<String, String> getMap() {
        return HardToMockOpenClass.getMapStatic();
    }
}
