package operations;

/**
 * @file Substraction.java
 * @brief This file contains the Substraction class, which represents
 *        a subtraction operation.
 *
 * @author Kevin Auberson, Leonard Klasen
 * @version  3.0 - 01.11.2023
 */
public class Substraction extends Operation{
    /**
     * Constructor for the Substraction class.
     */
    public Substraction() {
        symbol = "-";
    }

    /**
     * Execute the subtraction operation.
     *
     * @param x The first operand.
     * @param y The second operand.
     * @return The result of subtracting y from x.
     */
    public int exec(int x, int y){
        return x - y;
    }

    /**
     * Get the string representation of the subtraction operation.
     *
     * @return The string symbol representing subtraction ("-").
     */
    public String toString(){
        return symbol;
    }
}