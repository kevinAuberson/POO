package operator;
import calculator.State;

/**
 * @file Sign.java
 * @brief Represents the sign change operator.
 * @author Kevin Auberson, Leonard Klasen
 * @version 1.0
 * @date 07.12.2023
 *
 * This class extends the Operator abstract class and implements the exec() method
 * to execute the operation related to changing the sign of a number.
 */
public class Sign extends Operator {
    /**
     * @brief Executes the operation related to changing the sign of a number.
     *
     * Calls the State class to perform the operation that changes the sign of a number.
     */
    void exec() {
        State.getInstance().opSign();
    }
}
