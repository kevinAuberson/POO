package operator;
import calculator.State;

/**
 * @file SqrtOperator.java
 * @brief Represents the square root operator.
 * @author Kevin Auberson, Leonard Klasen
 * @version 1.0
 * @date 07.12.2023
 *
 * This class extends the Operator abstract class and implements the exec() method
 * to execute the square root operation.
 */
public class SqrtOperator extends Operator {

    /**
     * @brief Executes the square root operation.
     *
     * Calls the State class to perform the square root operation.
     */
    void exec() {
        State.getInstance().opSqrt();
    }
}