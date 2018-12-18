package com;

public class HelloWorld {

    static {
        System.loadLibrary("HelloWorld");
    }

    public static native void sayHello(String msg);

    public void main(String[] args) {
        sayHello("White.");
    }
}
