package operator;
import calculator.State;

/**
 * @file Enter.java
 * @brief Represents the enter (or equals) operator.
 * @author Kevin Auberson, Leonard Klasen
 * @version 1.0
 * @date 07.12.2023
 *
 * This class extends the Operator abstract class and implements the exec() method
 * to execute the operation related to pressing the enter key.
 */
public class Enter extends Operator {

    /**
     * @brief Executes the operation related to pressing the enter key.
     *
     * Calls the State class to perform the operation associated with the enter key.
     */
    void exec() {
        State.getInstance().opEnter();
    }
}
