import static org.junit.jupiter.api.Assertions.*;
import matrix.Matrix;
import operations.*;
import org.junit.jupiter.api.Test;

/**
 * @file MatrixTest.java
 * @brief This file contains the tests
 *
 * @author Kevin Auberson, Leonard Klasen
 * @version  3.0 - 01.11.2023
 */
public class MatrixTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    /**
     * Test if the addition operation works fine.
     */
    @Test
    public void testMatrixAddition() {
        final int rows = 2;
        final int columns = 2;
        final int modulus = 3;

        Matrix test = new Matrix(rows, columns, modulus, new int[][]
                {{1, 2},
                 {2, 2}});
        Matrix test2 = new Matrix(rows, columns, modulus, new int [][]
                {{1, 2},
                 {1, 2}});
        int[][] result = new int[][]
                {{2, 1},
                 {0, 1}};

        Matrix expectedResult = new Matrix(2, 2, modulus, result);
        Addition plus = new Addition();
        Matrix resultAddition = test.execOperation(test2, plus);
        assertEquals(expectedResult.toString(), resultAddition.toString());

    }

    /**
     * Test if the substraction operation works fine.
     */
    @Test
    public void testMatrixSubstraction() {
        final int rows = 2;
        final int columns = 2;
        final int modulus = 3;

        Matrix test = new Matrix(rows, columns, modulus, new int[][]
                {{1, 1},
                 {2, 2}});
        Matrix test2 = new Matrix(rows, columns, modulus, new int [][]
                {{1, 2},
                 {1, 2}});

        int[][] result = new int[][]
                {{0, 2},
                 {1, 0}};

        Substraction sub = new Substraction();
        Matrix expectedResult = new Matrix(rows, columns, modulus, result);
        Matrix actualResult = test.execOperation(test2, sub);

        assertEquals(expectedResult.toString(), actualResult.toString());

    }

    /**
     * Test if the multiplication operation works fine.
     */
    @Test
    public void testMatrixMultiplication() {
        final int rows = 2;
        final int columns = 2;
        final int modulus = 3;
        Multiplication mult = new Multiplication();

        Matrix test = new Matrix(rows, columns, modulus, new int[][]
                {{1, 4, 5, 2},
                 {2, 2, 6, 3}});
        Matrix test2 = new Matrix(rows, columns, modulus, new int [][]
                {{1, 2, 2, 2},
                 {1, 2, 2, 2}});

        Matrix expectedResult = new Matrix(rows, columns, modulus, new int[][]{{1,2,1,1},{2,1,0,0}});

        assertEquals(expectedResult.toString(), test.execOperation(test2, mult).toString());
    }

    /**
     * Test if Matrix without same modulus, returns a RuntimeExcpetion.
     */
    @Test
    public void notSameModulus() {
        final int rows = 2;
        final int columns = 2;

        Matrix test = new Matrix(rows, columns, 3, null);
        Matrix test2 = new Matrix(rows, columns, 5, null);

        assertThrows(RuntimeException.class, () -> test.execOperation(test2, new Addition()));
        assertThrows(RuntimeException.class, () -> test.execOperation(test2, new Substraction()));
        assertThrows(RuntimeException.class, () -> test.execOperation(test2, new Multiplication()));
    }

    /**
     * Test if the randomized creation of Matrixes works.
     */
    @Test
    public void testRandomizeMatrixCreation() {
        final int rows = 2;
        final int columns = 2;
        final int modulus = 3;
        Matrix test = new Matrix(rows, columns, modulus, null);
        Matrix test2 = new Matrix(rows, columns, modulus, null);

       assertTrue(!test.toString().equals(test2.toString()));
    }

    /**
     * Test the constructor of the Matrix class
     */
    @Test
    public void testMatrixConstructor() {
        //test if rows are negative
        assertThrows(RuntimeException.class, () -> new Matrix(-1, 4, 3, null));

        //test if columns are negative
        assertThrows(RuntimeException.class, () -> new Matrix(1, -4, 3, null));

        //test if modulus is negative
        assertThrows(RuntimeException.class, () -> new Matrix(1, 4, -3, null));

        //test if Matrix is null
        assertThrows(RuntimeException.class, () -> new Matrix(0, 0, 3, null));
    }

    /**
     * Test if we operate on different sized Matrixes.
     */
    @Test
    public void testDifferentSizedMatrix() {

        Addition add = new Addition();
        Substraction sub = new Substraction();
        Multiplication mult = new Multiplication();
        Matrix test = new Matrix(2, 2, 3, new int[][]
                {{1, 2, 1},
                 {3, 2, 1}});
        Matrix test2 = new Matrix(2, 3, 3, new int[][]
                {{1, 2, 1, 2},
                 {1, 4, 4, 3}});

        Matrix expectedResultAdd = test.execOperation(test2, add);
        Matrix actualResultAdd = new Matrix(2, 3, 3, new int[][]
                {{2, 1, 2, 2},
                 {1, 0, 2, 0}});

        Matrix expectedResultSub = test.execOperation(test2, sub);
        Matrix actualResultSub = new Matrix(2, 3, 3, new int[][]
                {{0, 0, 0, 1},
                 {2, 1, 0, 0}});

        Matrix expectedResultMult = test.execOperation(test2, mult);
        Matrix actualResultMult = new Matrix(2, 3, 3, new int[][]
                {{1, 1, 1, 0},
                 {0, 2, 1, 0}});

        assertEquals(expectedResultAdd.toString(), actualResultAdd.toString());
        assertEquals(expectedResultSub.toString(), actualResultSub.toString());
        assertEquals(expectedResultMult.toString(), actualResultMult.toString());
    }



}