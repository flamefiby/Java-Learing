// use the package (import package should be the first line for the code)
package com.Phone;
// import the class under the package
import com.Phone.Part.*;

public class Phone {
    String PhoneName;
    int price;
    String color;
    // create object
    CPU cpu = new CPU();
    // create object
    Memory memory = new Memory();

    // a previous phone object in the class (*)
    Phone prePhone = new Phone();

}
