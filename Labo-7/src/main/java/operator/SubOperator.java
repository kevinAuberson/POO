package operator;
import calculator.State;

/**
 * @file SubOperator.java
 * @brief Represents the memory recall operator.
 * @author Kevin Auberson, Leonard Klasen
 * @version 1.0
 * @date 07.12.2023
 *
 * This class extends the Operator abstract class and implements the exec() method
 * to execute the operation related to recalling a value from memory.
 */
public class SubOperator extends Operator {

    /**
     * @brief Executes the subtraction operation.
     *
     * Calls the State class to perform the subtraction operation.
     */
    void exec() {
        State.getInstance().opSub();
    }
}
