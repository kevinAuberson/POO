package operator;

import calculator.State;

/**
 * @file MemoryStore.java
 * @brief Represents the memory store operator.
 * @author Kevin Auberson, Leonard Klasen
 * @version 1.0
 * @date 07.12.2023
 *
 *         This class extends the Operator abstract class and implements the
 *         exec() method
 *         to execute the operation related to storing a value in memory.
 */
public class MemoryStore extends Operator {

    /**
     * @brief Executes the operation related to storing a value in memory.
     *
     *        Calls the State class to perform the operation of storing a value in
     *        memory.
     * 
     */

    void exec() {
        State.getInstance().opMemoryStore();
    }
}
