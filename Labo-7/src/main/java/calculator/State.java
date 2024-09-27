package calculator;

import util.Stack;

/**
 * @file State.java
 * @brief Represents the state of the calculator.
 * @author Kevin Auberson, Leonard Klasen
 * @version 1.0
 * @date 07.12.2023
 */
public class State {

    private static State instance; // Singleton instance of the State class

    private String value; // Current value displayed on the calculator

    private String memory; // Value stored in memory by MemoryStore

    private boolean error; // Flag indicating if the current value has an error

    private String errorMessage; // Error message displayed to the user

    private boolean isMutable = true; // Flag indicating if the value is mutable
                                      // (while typing) or not
    private Stack<Double> stack; // Reference to the stack of computed values

    /**
     * @brief Private constructor for the State class.
     */
    private State() {
        clear(); // Initialize/reset the state
    }

    /**
     * @brief Retrieves the singleton instance of the State class.
     *
     * @return The singleton instance of the State class
     */
    public static State getInstance() {
        if (instance == null) {
            instance = new State();
        }
        return instance;
    }

    /**
     * @brief Clears the state of the calculator.
     */
    public void clear() {
        clearError();
        stack = new Stack<>();
        memory = ""; // nothing more in the memory
    }

    /**
     * @brief Clears the error state.
     */
    private void clearError() {
        value = "";
        error = false;
        errorMessage = "";
        isMutable = true;
    }

    /**
     * @brief Appends the provided digit to the current value.
     *
     * @param x The digit to be appended
     */
    public void opDigit(int x) {
        value += x;
    }

    /**
     * @brief Appends a decimal point to the current value.
     */
    public void opDot() {

        // If the value is empty, adds "0" before the decimal point
        if (value.isEmpty()) {
            value += "0";
        }

        // If the value doesn't contain a decimal point, appends it
        if (!value.contains(".")) {
            value += ".";
        }
    }

    /**
     * @brief Changes the sign of the current value.
     */
    public void opSign() {
        double val = value();
        if (!error) {
            if (val > 0) {
                value = "-" + value;
            } else { // val < 0
                // Removes the negative sign if negative
                value = value.substring(1);
            }
        }
    }

    /**
     * @brief Performs the division operation.
     */
    public void opDiv() {
        if (value() == 0) {
            error = true;
            errorMessage = "cannot divide by 0";
        } else {
            setValue(popStack() / value());
        }
    }

    /**
     * @brief Performs the addition operation.
     */
    public void opAdd() {
        setValue(popStack() + value());
    }

    /**
     * @brief Performs the subtraction operation.
     */
    public void opSub() {
        setValue(popStack() - value());
    }

    /**
     * @brief Performs the multiplication operation.
     */
    public void opMult() {
        setValue(popStack() * value());
    }

    /**
     * @brief Performs the reciprocal operation (1/x).
     */
    public void opOver1() {
        if (value() == 0) {
            error = true;
            errorMessage = "cannot divide by 0";
        } else {
            setValue(1 / value());
        }
    }

    /**
     * @brief Performs the square operation (x^2).
     */
    public void opSquare() {
        setValue(Math.pow(value(), 2));
    }

    /**
     * @brief Performs the square root operation.
     */
    public void opSqrt() {
        if (value() < 0) {
            error = true;
            errorMessage = "cannot sqrt values under 0";
        } else {
            setValue(Math.sqrt(value()));
        }
    }

    /**
     * @brief Adds the current value to the stack.
     */
    public void opEnter() {
        pushStack();
    }

    /**
     * @brief Performs the backspace operation (removing the last character).
     */
    public void opBackSpace() {
        if (isMutable) {
            if (!value.isEmpty()) {
                value = value.substring(0, value.length() - 1);
            }
        }
    }

    /**
     * @brief Stores the current value in memory.
     */
    public void opMemoryStore() {
        value();
        if (!error) {
            memory = value;
        }
    }

    /**
     * @brief Recalls the value stored in memory.
     */
    public void opMemoryRecall() {
        clearError();
        value = memory;
        isMutable = false;
    }

    /**
     * @brief Clears the state of the calculator.
     */
    public void opClear() {
        clear();
    }

    /**
     * @brief Clears any error state.
     */
    public void opClearError() {
        clearError();
    }

    /**
     * @brief Pushes the current value onto the stack.
     */
    private void pushStack() {
        double value = value();
        if (!error) {
            stack.push(value);
            clearError();
        }
    }

    /**
     * @brief Pops a value from the stack.
     *
     * @return The popped value from the stack
     */
    private double popStack() {
        if (stackHasNext()) {
            return stack.pop();
        }

        return 0;
    }

    /**
     * @brief Checks if the stack has a next element.
     *
     * @return True if the stack has a next element, otherwise False
     */
    private boolean stackHasNext() {
        if (stack.isEmpty()) {
            error = true;
            errorMessage = "The stack is empty...";
            return false;
        }
        return true;
    }

    /**
     * @brief Retrieves the numeric value from the current string value.
     *
     * @return The numeric value parsed from the string, or 0 if empty
     */
    private double value() {
        try {
            if (value.isEmpty()) {
                return 0;
            }
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            error = true;
            errorMessage = "Error : format isn't correct " + value;
            return 0;
        }
    }

    /**
     * @brief Sets the current value to the provided numeric value.
     *
     * @param x The numeric value to set
     */
    private void setValue(double x) {
        value = Double.toString(x); // Converts the numeric value to string for display
        isMutable = false; // Indicates that the value is no longer mutable
    }

    /**
     * @brief Retrieves the current value as a string.
     *
     * @return The string representation of the current value or error message
     */
    public String getValueString() {
        if (error) {
            return errorMessage;
        } else if (value.isEmpty()) {
            return "0";
        }
        return value;
    }

    /**
     * @brief Retrieves the current state of the stack.
     *
     * @return An array representing the current state of the stack
     */
    public Object[] getStackState() {
        return stack.toArray();
    }
}
