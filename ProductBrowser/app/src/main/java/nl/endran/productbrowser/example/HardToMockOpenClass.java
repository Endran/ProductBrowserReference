/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser.example;

import java.util.HashMap;
import java.util.Map;

public class HardToMockOpenClass {

    private static Map<String, String> map = new HashMap<>();

    static {
        map.put("1", "2");
    }

    public static void goodLuckMockingThisStaticMethod() {
        throw new RuntimeException("You oughta mock this static method out!");
    }

    public final void goodLuckMockingThisFinalMethod() {
        throw new RuntimeException("You oughta mock this final method out!");
    }

    public void goodLuckMockingThisNormalMethod() {
        throw new RuntimeException("You oughta mock this method out!");
    }

    public Map<String, String> getMap() {
        return map;
    }

    public static Map<String, String> getMapStatic() {
        return map;
    }
}
