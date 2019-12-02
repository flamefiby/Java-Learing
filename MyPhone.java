/**
 * @author Dong
 * @Date Dec 2 2019
 * Studying propose: understand the connection between different class and using
 * package to contain different class to make the entire program more logical.
 * Reference: https://time.geekbang.org/course/detail/181-97293  chapter 38
 */
package com.Phone;

import com.Phone.Part.*;

public class MyPhone {
    public static void main(String[] args) {
        // set up a new iPhone
        Phone iPhone = new Phone();
        iPhone.PhoneName = "iPhone";
        iPhone.color = "Black";
        iPhone.price = 4999;
        // CPU for the new iPhone
        iPhone.cpu = new CPU();
        iPhone.cpu.manufacturer = "Apple";
        iPhone.cpu.speed = 9;
        iPhone.cpu.version = 1;
        // memory for the new iPhone
        iPhone.memory = new Memory();
        iPhone.memory.manufacturer = "Kingstorm";
        iPhone.memory.memory = 512;
        iPhone.memory.memoryType = "SSD";

        // (*) use the prephone object in the main class
        iPhone.prePhone = new Phone();


    }
}
