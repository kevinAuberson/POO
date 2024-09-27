import calculator.JCalculator;

/**
 * @file Main.java
 * @brief Contains the entry point to the application.
 * @author Kevin Auberson, Leonard Klasen
 * @version 1.0
 * @date 07.12.2023
 */
public class Main {
    /**
     * @brief Main method, entry point to the application.
     * <p>
     * Creates an instance of JCalculator (the calculator GUI) and sets it
     * visible.
     * </p>
     * @param args Command-line arguments (not used in this application)
     */
    public static void main(String... args) {
        new JCalculator().setVisible(true);
    }
}
