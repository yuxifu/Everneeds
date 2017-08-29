package com.yuxifu.everneeds._templates;

/**
 * Created by Yuxi on 8/25/17.
 */

/**
 * Java Class Static Initializer, Instance Initializer, and Constructor
 * The output will be:
 * Static initialization.
 * Instance initialization.
 * Constructor executed.
 * Instance initialization.
 * Constructor executed.
 */
public class ClassInitializer {
    static int staticVariable;
    int instanceVariable;

    // Static initialization block:
    static {
        System.out.println("Static initialization.");
        staticVariable = 5;
    }

    // Instance initialization block:
    {
        System.out.println("Instance initialization.");
        instanceVariable = 10;
    }

    // Constructor
    public ClassInitializer() {
        System.out.println("Constructor executed.");
    }

    public static void main(String[] args) {
        new ClassInitializer();
        new ClassInitializer();
    }
}
