package matrix;

import java.util.Random;
import java.lang.Math;
import operations.Operation;

/**
 * @file Matrix.java
 * @brief This file contains the definition of the Matrix class.
 *
 * This class represents an integer matrix with basic operations.
 *
 * @author Kevin Auberson, Leonard Klasen
 * @version  3.0 - 01.11.2023
 */
public class Matrix
{
    private int[][] matrix;
    private int columns;
    private int rows;
    private int modulus;

    /**
     * @brief Constructor for the Matrix class.
     *
     * This constructor creates an instance of the Matrix class by initializing
     * the properties of the matrix.
     *
     * @param rows The number of rows in the matrix.
     * @param columns The number of columns in the matrix.
     * @param modulus The modulus to use for values in the matrix.
     * @param data The data for the matrix, or null for random generation.
     * @throws RuntimeException If the parameters are invalid.
     */
    public Matrix(int rows, int columns,
                  int modulus, int[][] data) throws RuntimeException {
        if(modulus <= 0) {
            throw new RuntimeException("error, modulo cannot be <= 0");
        }
        this.modulus = modulus;
        if(data == null){
            if(columns <= 0 || rows <= 0){
                throw new RuntimeException("error, rows or "+
                        "columns cannot be <= 0");
            }
            this.columns = columns;
            this.rows = rows;
            this.matrix = new int[rows][columns];
            Random random = new Random();


            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.columns; j++) {
                    this.matrix[i][j] = Math.abs(random.nextInt() % modulus);
                }
            }
        }else {
            this.rows = data.length;
            this.columns = data[0].length;
            this.matrix = new int[data.length][data[0].length];

            for (int i = 0; i < data.length; ++i) {
                if (data[i].length != data[0].length) {
                    throw new RuntimeException("The 2d array must have the same"
                            + "number of elements foreach rows.");
                }
                for (int j = 0; j < data[0].length; ++j) {
                    if (data[i][j] < 0 ) {
                        throw new RuntimeException("The given values "+
                                "must be > 0" );
                    }
                    //the % modulus here allow any values > 0 in the data array
                    matrix[i][j] = data[i][j] % modulus;
                }
            }
        }
    }

    /**
     * @brief Get the modulus of the matrix.
     *
     * @return The modulus of the matrix.
     */
    public int getModulus(){
        return this.modulus;
    }

    /**
     * Converts the matrix to a string representation.
     *
     * This method iterates through the matrix, converting each element to
     * a string and formatting them in rows and columns.
     *
     * @return A string representing the matrix with elements separated
     *         by spaces and rows separated by newline characters.
     */
    public String toString(){
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < rows; ++i){
            for(int j = 0; j < columns; ++j){
                result.append(matrix[i][j]).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * @brief Execute an operation on two matrices.
     *
     * This function performs the specified operation on two matrices,
     * checking the compatibility of modulos.
     *
     * @param rhs The second matrix to use in the operation.
     * @param op The operation to perform (Addition, Subtraction,
     *           Multiplication, etc.).
     * @return A new matrix containing the result of the operation.
     * @throws RuntimeException If the modulos of the two matrices do not match.
     */
    public Matrix execOperation (Matrix rhs,
                                 Operation op) throws RuntimeException{
        if (this.modulus != rhs.modulus) {
            throw new RuntimeException("Les modulos n des deux matrices ne "+
                    "correspondent pas!");

        }
        int maxRows = Math.max(this.rows, rhs.rows);
        int maxColumns = Math.max(this.columns, rhs.columns);

        int[][] result = new int[maxRows][maxColumns];

        try {
            for (int i = 0; i < maxRows; ++i) {
                for (int j = 0; j < maxColumns; ++j) {
                    int a = (i < this.rows && j < this.columns) ?
                            this.matrix[i][j] : 0;
                    int b = (i < rhs.rows && j < rhs.columns) ?
                            rhs.matrix[i][j] : 0;
                    result[i][j] = Math.floorMod(op.exec(a, b), modulus);
                }
            }

        } catch(RuntimeException e) {
            System.out.println("error" + e);
        }

        return new Matrix(maxRows, maxColumns, modulus, result);
    }
}

