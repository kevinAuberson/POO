import matrix.Matrix;
import operations.*;

/**
 * @file Main.java
 * @brief This file contains the main class of the application.
 *
 * @author Kevin Auberson, Leonard Klasen
 * @version  3.0 - 01.11.2023
 */
public class Main {
    /**
     * This function is the entry point of the application.
     * It initializes matrices, performs operations on them,
     * and displays the results.
     *
     * @param args Command-line arguments (not used in this example).
     */
    public static void main( String[] args )
    {

        int[][] data = new int[][] {{8, 4, 4, 2}, {17, 8, 20, 4}};
        try {
            Matrix matrix1 = new Matrix(3,4,5,
                    null);
            Matrix matrix2 = new Matrix(2, 4, 5,
                    data);

            System.out.println("The modulus is "
                    + Math.max(matrix1.getModulus(),matrix2.getModulus()));
            System.out.println("one:");
            System.out.println(matrix1);
            System.out.println("two:");
            System.out.println(matrix2);
            Operation[] operations = new Operation[]{new Addition(),
                    new Substraction(), new Multiplication()};
            for (Operation op: operations) {
                System.out.println("one " + op + " two:");
                System.out.println(matrix1.execOperation(matrix2, op));
            }
        }catch (Exception e){
            System.out.println("error: " + e);
        }
    }
}
