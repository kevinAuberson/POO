package operator;
import calculator.State;

/**
 * @file ClearValue.java
 * @brief Represents the clear value operator.
 * @author Kevin Auberson, Leonard Klasen
 * @version 1.0
 * @date 07.12.2023
 *
 * This class extends the Operator abstract class and implements the exec() method
 * to execute the operation that clears a value.
 */
public class ClearValue extends Operator {

    /**
     * @brief Executes the operation to clear a value.
     *
     * Calls the State class to perform the operation that clears a value.
     */
    void exec() {
        State.getInstance().opClear();
    }
}