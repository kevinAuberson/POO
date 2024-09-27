package operator;
import calculator.State;

/**
 * @file BackSpace.java
 * @brief Represents the backspace operator.
 * @author Kevin Auberson, Leonard Klasen
 * @version 1.0
 * @date 07.12.2023
 *
 * This class extends the Operator abstract class and implements the exec() method
 * to execute the backspace operation.
 */
public class BackSpace extends Operator {

    /**
     * @brief Executes the backspace operation.
     *
     * Calls the State class to perform the backspace operation.
     */
    void exec() {
        State.getInstance().opBackSpace();
    }
}
