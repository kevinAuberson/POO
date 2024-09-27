import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import util.Stack;

/**
 * @file StackTest.java
 * @brief Contains unit tests for the Stack class.
 * @author Kevin Auberson, Leonard Klasen
 * @version 1.0
 * @date 07.12.2023
 */
public class StackTest {
    private Stack<Integer> stack;

    /**
     * @brief Sets up the test fixture.
     */
    @BeforeEach
    public void setUp() {
        stack = new Stack<>();
    }

    /**
     * @brief Tests push and pop operations.
     */
    @Test
    public void testPushAndPop() {
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    /**
     * @brief Tests if the stack is initially empty.
     */
    @Test
    public void testIsEmpty() {
        assertTrue(stack.isEmpty());
    }

    /**
     * @brief Tests the size of the stack.
     */
    @Test
    public void testSize() {
        assertEquals(0, stack.size());
        stack.push(1);
        assertEquals(1, stack.size());
        stack.push(2);
        assertEquals(2, stack.size());
        stack.pop();
        assertEquals(1, stack.size());
    }

    /**
     * @brief Ensures that EmptyStackException is thrown when popping from an empty stack.
     */
    @Test
    public void testPopOnEmptyStack() {
        assertThrows(EmptyStackException.class, () -> stack.pop());
    }

    /**
     * @brief Tests the iterator functionality.
     */
    @Test
    public void testIterator() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        int sum = 0;
        for (int n : stack) {
            sum += n;
        }
        assertEquals(6, sum);
    }

    /**
     * @brief Ensures that adding a null item to the stack results in a NullPointerException.
     */
    @Test
    public void testPushNullItem() {
        assertThrows(NullPointerException.class, () -> stack.push(null));
    }

    /**
     * @brief Tests the stack behavior with a large number of items.
     */
    @Test
    public void testLargeNumberOfItems() {
        for (int i = 0; i < 10000; i++) {
            stack.push(i);
        }
        for (int i = 9999; i >= 0; i--) {
            assertEquals(Integer.valueOf(i), stack.pop());
        }
    }

    /**
     * @brief Ensures that calling next() on an empty stack iterator throws a NoSuchElementException.
     */
    @Test
    public void testIteratorNextOnEmptyStack() {
        Iterator<Integer> iterator = stack.iterator();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testPrintStack(){
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals("[3, 2, 1]", stack.toString());
    }

    /**
     * @brief Verifies the string representation of the stack.
     */
    @Test
    public void testArrayObject(){
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.toArray()[0]);
        assertEquals(2, stack.toArray()[1]);
        assertEquals(1, stack.toArray()[2]);
    }
}