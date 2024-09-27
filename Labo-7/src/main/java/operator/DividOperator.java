package operator;
import calculator.State;

/**
 * @file DividOperator.java
 * @brief Represents the division operator.
 * @author Kevin Auberson, Leonard Klasen
 * @version 1.0
 * @date 07.12.2023
 *
 * This class extends the Operator abstract class and implements the exec() method
 * to execute the division operation.
 */
public class DividOperator extends Operator {

    /**
     * @brief Executes the division operation.
     *
     * Calls the State class to perform the division operation.
     */
    void exec() {
        State.getInstance().opDiv();
    }
}