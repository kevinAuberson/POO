package operations;

/**
 * @file Multiplication.java
 * @brief This file contains the Multiplication class, which represents
 *        a multiplication operation.
 *
 * @author Kevin Auberson, Leonard Klasen
 * @version  3.0 - 01.11.2023
 */
public class Multiplication extends Operation{
    /**
     * Constructor for the Multiplication class.
     */
    public Multiplication() {
        symbol = "*";
    }

    /**
     * Execute the multiplication operation.
     *
     * @param x The first operand.
     * @param y The second operand.
     * @return The result of multiplying x by y.
     */
    public int exec(int x, int y) { return x * y; }

    /**
     * Get the string representation of the multiplication operation.
     *
     * @return The string symbol representing multiplication ("*").
     */
    public String toString(){
        return symbol;
    }
}