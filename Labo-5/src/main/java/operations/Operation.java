package operations;

/**
 * @file Operation.java
 * @brief This file contains the Operation abstract class, which represents
 *        a basic operation.
 *
 * @author Kevin Auberson, Leonard Klasen
 * @version  3.0 - 01.11.2023
 */
public abstract class Operation{
    protected String symbol = "";

    /**
     * Execute the operation on two integers.
     *
     * @param a The first operand.
     * @param b The second operand.
     * @return The result of the operation on a and b.
     */
    public abstract int exec(int a, int b);

    /**
     * Get the string representation of the operation.
     *
     * @return The string symbol representing the operation.
     */
    public String toString(){
        return symbol;
    }
}