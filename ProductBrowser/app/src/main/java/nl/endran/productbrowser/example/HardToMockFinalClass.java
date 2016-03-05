/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser.example;

public final class HardToMockFinalClass {

    public static void goodLuckMockingThisStaticMethod() {
        throw new RuntimeException("You oughta mock this static method out!");
    }

    public final void goodLuckMockingThisFinalMethod() {
        throw new RuntimeException("You oughta mock this final method out!");
    }

    public void goodLuckMockingThisNormalMethod() {
        throw new RuntimeException("You oughta mock this method out!");
    }
}
