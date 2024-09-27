package operator;
import calculator.State;

/**
 * @file AddOperator.java
 * @brief Represents the addition operator.
 * @author Kevin Auberson, Leonard Klasen
 * @version 1.0
 * @date 07.12.2023
 *
 * This class extends the Operator abstract class and implements the exec() method
 * to execute the addition operation.
 */
public class AddOperator extends Operator {

    /**
     * @brief Executes the addition operation.
     *
     * Calls the State class to perform the addition operation.
     */
    void exec(){
        State.getInstance().opAdd();
    }
}