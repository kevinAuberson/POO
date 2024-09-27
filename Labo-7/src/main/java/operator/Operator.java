package operator;

/**
 * @file Operator.java
 * @brief Contains the definition of the abstract Operator class.
 * @author Kevin Auberson, Leonard Klasen
 * @version 1.0
 * @date 07.12.2023
 */
public abstract class Operator {

    /**
     * @brief Executes the operation defined by the specific operator.
     */
    public void execute() {
          exec();
      }

    /**
     * @brief Abstract method to be implemented by concrete operator classes.
     *
     * This method defines the operation to be executed by the specific operator.
     */
    abstract void exec();
}
