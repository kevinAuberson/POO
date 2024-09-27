package operator;
import calculator.State;

/**
 * @file MemoryRecall.java
 * @brief Represents the memory recall operator.
 * @author Kevin Auberson, Leonard Klasen
 * @version 1.0
 * @date 07.12.2023
 *
 * This class extends the Operator abstract class and implements the exec() method
 * to execute the operation related to recalling a value from memory.
 */
public class MemoryRecall extends Operator {

    /**
     * @brief Executes the operation related to recalling a value from memory.
     *
     * Calls the State class to perform the operation of recalling a value from memory.
     */
    void exec() {
        State.getInstance().opMemoryRecall();
    }
}
