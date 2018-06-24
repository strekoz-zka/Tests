package com.example.sbertech;

import com.example.sbertech.Tests.TestSberbankSite;

import org.testng.TestNG;

public class Solution {

    public static void main(String[] args) {
        final TestNG testNG = new TestNG(true);
        testNG.setTestClasses(new Class[] {TestSberbankSite.class});
        testNG.setExcludedGroups("optional");
        testNG.run();
    }


}
