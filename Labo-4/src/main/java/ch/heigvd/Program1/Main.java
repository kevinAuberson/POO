/*−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−------------------------------------------------
File name       : Main.java
Author(s)       : Kevin Auberson, Léonard Klasen
Created         : 20 oct. 2023
Description     : Program that convert the given command line String arguments as int,
                  store them in an array, then sorts the array with a bubbleSort algorithm.
Remark(s)       : This program automatically closes if there aren't argument.
                  This program automatically closes after displaying the result.
                  This program throw an exception if an argument is not accepted.
−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−------------------------------------------------*/
package main.java.ch.heigvd.Program1;

public class Main {

    public static void main(String[] args) {
        if(args.length == 0) {
            System.out.println("Le tableau est vide !");
            System.exit(-1);
        }
        int size = args.length;
        int [] array = new int[size];
        for(int i = 0; i < size; i++) {
            array[i] = stringToint(args[i]);
        }
        System.out.print("String array change into int array :");
        displayArray(array);
        bubbleSort(array);
        System.out.print("int array sorted :");
        displayArray(array);
        System.exit(0);
    }

    /**
     * Change a string into an int
     *
     * @param str to be changed
     * @return int
     */
    public static int stringToint(String str)throws RuntimeException{
        if(str == null || !str.matches("[-+]?\\d+"))
            throw new RuntimeException("The given string : (" + str +") is not accepted !");

        int i = 0, number = 0;
        boolean isNegative = false;
        int len = str.length();
        if(str.charAt(0) == '-'){
            isNegative = true;
            i = 1;
        } else if (str.charAt(0) == '+') {
            i = 1;
        }
        while (i < len){
            number *= 10;
            number += (str.charAt(i++) - '0');
        }

        return isNegative ? -number : number;
    }

    /**
     * Sort(bubbleSort) an int array
     *
     * @param array to be sorted
     */
    public static void bubbleSort(int [] array) {
        if(array != null){
            for(int i = 0; i < array.length; i++){
                for(int j = 1; j < (array.length - i);j++){
                    if(array[j-1] > array[j])
                        swap(array, j-1, j);
                }
            }
        }
    }

    /**
     * Swaps the value of 2 elements in an int array
     *
     * @param array which the value is swap
     * @param index1 index of first element
     * @param index2 index of second element
     */
    public static void swap(int[] array, int index1, int index2){
        int tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    /**
     * Display an int array
     *
     * @param array to display
     */
    public static void displayArray(int[] array){
        System.out.print("[");
        for(int i = 0; i < array.length; i++){
            if(i == 0)
                System.out.print(array[i]);
            else
                System.out.print(", " + array[i]);
        }
        System.out.println("]");
    }
}