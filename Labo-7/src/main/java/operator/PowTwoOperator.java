package operator;
import calculator.State;

/**
 * @file PowTwoOperator.java
 * @brief Represents the power of two operator.
 * @author Kevin Auberson, Leonard Klasen
 * @version 1.0
 * @date 07.12.2023
 *
 * This class extends the Operator abstract class and implements the exec() method
 * to execute the operation related to squaring a number (power of two).
 */
public class PowTwoOperator extends Operator {

    /**
     * @brief Executes the operation related to squaring a number (power of two).
     *
     * Calls the State class to perform the operation that calculates the square of a number.
     */
    void exec() {
        State.getInstance().opSquare();
    }
}
