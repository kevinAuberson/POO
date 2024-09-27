/*−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−------------------------------------------------
File name       : Int.java
Author(s)       : Kevin Auberson, Leonard Klasen
Created         : 20 oct. 2023
Description     : A class Int encapsulating the primitive type int.
Remark(s)       :
−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−------------------------------------------------*/

package main.java.ch.heigvd.Program2;

public class Int {

    private int m_digit;

    //default Constructor
    public Int() {
        this.m_digit = 0;
    }

    //Constructor
    public Int(int number) {
        this.m_digit = number;
    }


    //Methods
    //get the value of digit of the Int
    public int getDigit() {
        return this.m_digit;
    }

    //Modify the value of the Int
    public void changeDigit(int number) {
        Int.this.m_digit = number;
    }

    //Override function of to.String to print the Int name and it's value
    @Override
    public String toString() {
        return "" + m_digit;
    }

    public void swapMethode(Int secondArray) {
        int tmp = secondArray.getDigit();
        secondArray.changeDigit(secondArray.getDigit());
        this.m_digit = tmp;
    }


}


