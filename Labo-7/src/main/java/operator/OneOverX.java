package operator;
import calculator.State;

/**
 * @file OneOverX.java
 * @brief Represents the reciprocal (1/x) operator.
 * @author Kevin Auberson, Leonard Klasen
 * @version 1.0
 * @date 07.12.2023
 *
 * This class extends the Operator abstract class and implements the exec() method
 * to execute the operation related to finding the reciprocal of a number.
 */
public class OneOverX extends Operator {

    /**
     * @brief Executes the operation related to finding the reciprocal of a number (1/x).
     *
     * Calls the State class to perform the operation that calculates the reciprocal of a number.
     */
    void exec() {
        State.getInstance().opOver1();
    }
}