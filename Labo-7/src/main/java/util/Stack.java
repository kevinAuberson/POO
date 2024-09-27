package util;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * @param <T> The type of elements held in the stack
 * @author Kevin Auberson, Leonard Klasen
 * @version 1.0
 * @file Stack.java
 * @brief This file contains a generic stack implementation.
 * @date 07.12.2023
 */
public class Stack<T> implements Iterable<T> {
    private Element<T> top; // Reference to the top element of the stack
    private int size; // Number of elements in the stack

    /**
     * @param data The data to be pushed onto the stack
     * @throws NullPointerException if the provided data is null
     * @brief Pushes an element onto the stack.
     */
    public void push(T data) {
        if (data == null) {
            throw new NullPointerException();
        }
        top = new Element<>(data, top);
        size++;
    }

    /**
     * @return The data of the element that was removed from the top of the stack
     * @throws EmptyStackException if the stack is empty
     * @brief Removes the element at the top of the stack and returns it.
     */
    public T pop() {
        Element<T> pop = top;
        T data;
        if (pop == null) {
            throw new EmptyStackException();
        } else {
            data = pop.getData();
            top = top.getNext();
            size--;
        }
        return data;
    }

    /**
     * @return true if the stack is empty, false otherwise
     * @brief Checks if the stack is empty.
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * @return The size of the stack
     * @brief Returns the number of elements in the stack.
     */
    public int size() {
        return size;
    }

    /**
     * @return A string representation of the stack
     * @brief Returns a string representation of the stack.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        Object[] array = toArray();
        result.append("[");
        for (Object element : array) {
            result.append(element).append(", ");
        }
        result = new StringBuilder(result.substring(0, result.length() - 2));
        result.append("]");
        return result.toString();
    }

    /**
     * @return An array containing the elements of the stack
     * @brief Converts the stack elements to an array.
     */
    public Object[] toArray() {
        Object[] t = new Object[size];
        Element<T> element = top;
        int i = 0;
        while (element != null) {
            t[i] = element.getData();
            element = element.getNext();
            i++;
        }
        return t;
    }

    /**
     * Returns an iterator over the elements in the stack.
     *
     * @return An iterator over the elements in the stack
     */
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Element<T> nextElement = top;

            public boolean hasNext() {
                return nextElement != null;
            }

            public T next() {
                if (nextElement != null) {
                    T nextData = nextElement.getData();
                    nextElement = nextElement.getNext();
                    return nextData;
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    /**
     * @param <T> The type of data stored in the element
     * @brief Represents an element in the stack.
     */
    private static class Element<T> {
        private final T data; // Data stored in the element
        private final Element<T> next; // Reference to the next element

        /**
         * @param data The data to be stored in the element
         * @param next Reference to the next element in the stack
         * @brief Constructs an element with the given data and reference to
         * the next element.
         */
        public Element(T data, Element<T> next) {
            this.data = data;
            this.next = next;
        }

        /**
         * @return The data of the element
         * @brief Gets the data stored in the element.
         */
        public T getData() {
            return data;
        }

        /**
         * @return The reference to the next element
         * @brief Gets the reference to the next element.
         */
        public Element<T> getNext() {
            return next;
        }

        /**
         * @return A string representation of the element's data
         * @brief Returns a string representation of the element's data.
         */
        @Override
        public String toString() {
            return data.toString();
        }
    }
}