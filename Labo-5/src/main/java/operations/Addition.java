package operations;

/**
 * @file Addition.java
 * @brief This file contains the Addition class, which represents
 *        an addition operation.
 *
 * @author Kevin Auberson, Leonard Klasen
 * @version  3.0 - 01.11.2023
 */
public class Addition extends Operation{

    /**
     * Constructor for the Addition class.
     */
    public Addition() {
        symbol = "+";
    }

    /**
     * Execute the addition operation.
     *
     * @param x The first operand.
     * @param y The second operand.
     * @return The result of adding x and y.
     */
    public int exec(int x, int y) {
        return x + y;
    }

    /**
     * Get the string representation of the addition operation.
     *
     * @return The string symbol representing addition ("+").
     */
    public String toString(){
        return symbol;
    }
}