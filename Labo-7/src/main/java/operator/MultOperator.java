package operator;
import calculator.State;

/**
 * @file MultOperator.java
 * @brief Represents the multiplication operator.
 * @author Kevin Auberson, Leonard Klasen
 * @version 1.0
 * @date 07.12.2023
 *
 * This class extends the Operator abstract class and implements the exec() method
 * to execute the multiplication operation.
 */
public class MultOperator extends Operator {

    /**
     * @brief Executes the multiplication operation.
     *
     * Calls the State class to perform the multiplication operation.
     */
    void exec() {
        State.getInstance().opMult();
    }
}
