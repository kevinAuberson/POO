package operator;
import calculator.State;

/**
 * @file Digit.java
 * @brief Represents a digit input operator.
 * @author Kevin Auberson, Leonard Klasen
 * @version 1.0
 * @date 07.12.2023
 *-
 * This class extends the Operator abstract class and represents an operator
 * that inputs a digit value.
 */
public class Digit extends Operator {
    private int value;

    /**
     * @brief Constructs a Digit object with a specified value.
     *
     * @param value The value of the digit to be input
     */
    public Digit(int value){
        this.value = value;
    }

    /**
     * @brief Executes the operation to input a digit value.
     *
     * Calls the State class to perform the operation that inputs a digit value.
     */
    void exec() {
        State.getInstance().opDigit(value);
    }
}
