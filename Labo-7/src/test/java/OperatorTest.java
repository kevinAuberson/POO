import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import calculator.State;

/**
 * Unit tests
 */
public class OperatorTest {

    @BeforeEach
    void setUp() {
        State calculator = State.getInstance();
        calculator.clear();
    }
    @Test
    public void testAddition() {
        State calculator = State.getInstance();
        calculator.opDigit(5);
        calculator.opEnter(); // Push 7 to the stack
        calculator.opDigit(7);
        calculator.opAdd(); // Add 5 + 7 from the stack

        assertEquals("12.0", calculator.getValueString());
    }

    @Test
    public void testSubtraction() {
        State calculator = State.getInstance();
        calculator.opDigit(9);
        calculator.opEnter(); // Push 4 to the stack
        calculator.opDigit(4);
        calculator.opSub();

        assertEquals("5.0", calculator.getValueString());
    }

    @Test
    public void testMultiplication() {
        State calculator = State.getInstance();
        calculator.opDigit(3);
        calculator.opEnter(); // Push 3 to the stack
        calculator.opDigit(6);
        calculator.opMult(); // Multiply 3 * 6

        assertEquals("18.0", calculator.getValueString());
    }

    @Test
    public void testDivisionByZero() {
        State calculator = State.getInstance();
        calculator.opDigit(8);
        calculator.opEnter(); // Push 8 to the stack
        calculator.opDiv(); // Divide 8 by 0

        assertTrue(calculator.getValueString().contains("cannot divide by 0"));
    }

    @Test
    public void testReciprocal() {
        State calculator = State.getInstance();
        calculator.opDigit(4);
        calculator.opOver1(); // Calculate 1/4

        assertEquals("0.25", calculator.getValueString());
    }

    @Test
    public void testSquare() {
        State calculator = State.getInstance();
        calculator.opDigit(9);
        calculator.opSquare(); // Calculate 9^2

        assertEquals("81.0", calculator.getValueString());
    }

    @Test
    public void testSquareRoot() {
        State calculator = State.getInstance();
        calculator.opDigit(25);
        calculator.opSqrt(); // Calculate sqrt(25)

        assertEquals("5.0", calculator.getValueString());
    }

    @Test
    public void testMemoryStoreAndRecall() {
        State calculator = State.getInstance();
        calculator.opDigit(7);
        calculator.opMemoryStore(); // Store 7 in memory
        calculator.opMemoryRecall(); // Recall the value from memory

        assertEquals("7", calculator.getValueString());
    }

    @Test
    public void testChangeSign() {
        State calculator = State.getInstance();
        calculator.opDigit(5);
        calculator.opSign(); // Change the sign of 5

        assertEquals("-5", calculator.getValueString());
    }

    @Test
    public void testBackspace() {
        State calculator = State.getInstance();
        calculator.opDigit(8);
        calculator.opDigit(9);
        calculator.opBackSpace(); // Remove the last digit (9)

        assertEquals("8", calculator.getValueString());
    }

    @Test
    public void testClearError() {
        State calculator = State.getInstance();
        calculator.opDigit(2);
        calculator.opEnter(); // Push 2 to the stack
        calculator.opDiv(); // Attempt division by 0

        assertTrue(calculator.getValueString().contains("cannot divide by 0"));

        calculator.opClearError(); // Clear the error state
        assertEquals("0", calculator.getValueString()); // Calculator should reset to 0
    }

    public void testStackOperations() {
        State calculator = State.getInstance();
        calculator.opDigit(3);
        calculator.opEnter(); // Push 3 to the stack
        calculator.opDigit(5);
        calculator.opEnter(); // Push 5 to the stack

        Object[] stackState = calculator.getStackState();
        assertEquals(2, stackState.length); // Stack should have 2 elements

        assertEquals(5.0, stackState[0]); // Top of stack should be 5
        assertEquals(3.0, stackState[1]); // Second element should be 3

        calculator.opAdd(); // Add 5 + 3 from the stack

        assertEquals("8.0", calculator.getValueString()); // Expected result after addition operation
        assertEquals(1, calculator.getStackState().length); // After operation, stack should have 1 element
    }

    @Test
    public void testClear() {
        State calculator = State.getInstance();
        calculator.opDigit(2);
        calculator.opEnter(); // Push 2 to the stack
        calculator.opAdd();
        calculator.opClear(); // Clear the calculator

        assertEquals("0", calculator.getValueString()); // Calculator should reset to 0
        assertEquals(0, calculator.getStackState().length); // Stack should be empty
    }


    @Test
    public void testValueSqrtError() {
        State calculator = State.getInstance();
        calculator.opDigit(-5);
        calculator.opSqrt(); // Attempt square root of a negative number

        assertTrue(calculator.getValueString().contains("cannot sqrt values under 0"));
    }
}