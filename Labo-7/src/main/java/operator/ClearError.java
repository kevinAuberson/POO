package operator;
import calculator.State;

/**
 * @file ClearError.java
 * @brief Represents the clear error operator.
 * @author Kevin Auberson, Leonard Klasen
 * @version 1.0
 * @date 07.12.2023
 *
 * This class extends the Operator abstract class and implements the exec() method
 * to execute the operation that clears an error state.
 */
public class ClearError extends Operator {

    /**
     * @brief Executes the operation to clear an error state.
     *
     * Calls the State class to perform the operation that clears an error state.
     */
    void exec() {
        State.getInstance().opClearError();
    }
}