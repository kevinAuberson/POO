/*−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−------------------------------------------------
File name       : Main.java
Author(s)       : Kevin Auberson, Leonard Klasen
Created         : 20 oct. 2023
Description     : Program that interprets the given command line arguments as
                  numbers, store them in 3 separate arrays, then sorts each array
                  with a bubbleSort algorithm that implements 3 different swapping
                  methods.
Remark(s)       : This program automatically closes after displaying the result.
                  This program throw an exception if an argument is not accepted.
−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−------------------------------------------------*/

package main.java.ch.heigvd.Program2;

public class Main {
    public static void main(String[] args) {
        if(args.length == 0) {
            System.out.println("Le tableau est vide !");
            System.exit(-1);
        }
        int size = args.length;
        Int [] array = new Int[size];

        for(int i = 0; i < size; i++) {
            array[i] = stringToint(args[i]);
        }

        //all the tests
        System.out.print("String array change into Int array :");
        displayArray(array);
        bubbleSort(array);
        System.out.print("Int array sorted :");
        displayArray(array);
        System.out.print("bubbleSortObject : ");
        bubbleSortObject(array);
        displayArray(array);
        bubbleSortSwapMethode(array);
        System.out.print("bubbleSortSwapMethode : ");
        displayArray(array);

        System.exit(0);
    }


    /**
     * Change a string into an Int object
     *
     * @param str to be changed
     * @return int
     */
    private static Int stringToint(String str)throws RuntimeException{
        if(str == null || !str.matches("[-+]?\\d+"))
            throw new RuntimeException("The given string : ("+ str +") is not accepted !");

        int i = 0;
        Int number = new Int();
        boolean isNegative = false;
        Int len = new Int(str.length());
        if(str.charAt(0) == '-'){
            isNegative = true;
            i = 1;
        } else if (str.charAt(0) == '+') {
            i = 1;
        }
        while (i < len.getDigit()){
            number.changeDigit(number.getDigit() * 10);
            number.changeDigit(number.getDigit() + str.charAt(i++) - '0');
        }

        if(isNegative)
            number.changeDigit(number.getDigit() * -1);

        return number;
    }

    /**
     * Sort(bubbleSort) an Int array
     *
     * @param array to be sorted
     */
    private static void bubbleSort(Int [] array) {
        int size = array.length;
        if(array != null && size > 1){
            for(int i = 0; i < size; i++){
                for(int j = 1; j < (size - i); j++){
                    if(array[j-1].getDigit()> array[j].getDigit())
                        swap(array, j-1, j);
                }
            }
        }
    }

    /**
     * Sort(bubbleSort) an Int array using a swap for Int objects
     *
     * @param array to be sorted
     */
    private static void bubbleSortObject(Int[] array) {
        int size = array.length;
        if(array != null && size > 1) {
            for(int i = 0; i < size; i++){
                for(int j = 1; j < (size - i); j++){
                    if(array[j-1].getDigit()> array[j].getDigit()) {
                        swapInt(array, i, j);
                    }
                }
            }
        }
    }


    /**
     * Sort(bubbleSort) an Int array using the swapMethode of the Int class
     *
     * @param array to be sorted
     */
    private static void bubbleSortSwapMethode(Int[] array) {
        int size = array.length;
        if(array != null && size > 1) {
            for(int i = 0; i < size; i++){
                for(int j = 1; j < (size - i); j++){
                    if(array[j-1].getDigit()> array[j].getDigit()) {
                        array[j-1].swapMethode(array[j]);
                    }
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
    private static void swap(Int[] array, int index1, int index2){
        int tmp = array[index1].getDigit();
        array[index1].changeDigit(array[index2].getDigit());
        array[index2].changeDigit(tmp);
    }

    /**
     * Swaps 2 objects in an Int array
     *
     * @param array which the value is swap
     * @param index1 index of first element
     * @param index2 index of second element
     */
    private static void swapInt(Int[] array, int index1, int index2) {
        Int tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    /**
     * Display an int array
     *
     * @param array to display
     */
    private static void displayArray(Int[] array){
        System.out.print("[");
        for(int i = 0; i < array.length; i++){
            if(i == 0) {
                System.out.print(array[i].toString());
            } else {
                System.out.print(", ");
                System.out.print(array[i].toString());
            }
        }
        System.out.println("]");
    }
}
