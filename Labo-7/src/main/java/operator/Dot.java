package operator;
import calculator.State;

/**
 * @file Dot.java
 * @brief Represents the dot (decimal point) operator.
 * @author Kevin Auberson, Leonard Klasen
 * @version 1.0
 * @date 07.12.2023
 *
 * This class extends the Operator abstract class and implements the exec() method
 * to execute the operation related to adding a decimal point.
 */
public class Dot extends Operator {

    /**
     * @brief Executes the operation related to adding a decimal point.
     *
     * Calls the State class to perform the operation that adds a decimal point.
     */
    void exec() {
        State.getInstance().opDot();
    }
}
