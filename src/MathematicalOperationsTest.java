import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MathematicalOperationsTest {
    static MathematicalOperations mathematicalOperations;

    @BeforeClass
    public static void init() {
        mathematicalOperations = new MathematicalOperations();
    }

    //! A method for testing the binaryPlus method
    /*!
      checks whether the method works for some known cases
    */
    @org.junit.Test
    public void testBinaryPlus() {
        assertEquals(8,mathematicalOperations.binaryPlus(3,5));
        assertEquals(1,mathematicalOperations.binaryPlus(-4,5));
        assertEquals(0,mathematicalOperations.binaryPlus(64,-64));
    }

    //! A method for testing the binaryMinus method
    /*!
      checks whether the method works for some known cases
    */
    @org.junit.Test
    public void testBinaryMinus() {
        assertEquals(-5,mathematicalOperations.binaryMinus(0,5));
        assertEquals(-9,mathematicalOperations.binaryMinus(-2, 7));
        assertEquals(0,mathematicalOperations.binaryMinus(53, 53));
        assertEquals(9,mathematicalOperations.binaryMinus(5, -4));
    }

    //! A method for testing the binaryMultiplication method
    /*!
      checks whether the method works for some known cases
    */
    @org.junit.Test
    public void testBinaryMultiplication() {
        assertEquals(8,mathematicalOperations.binaryMultiplication(2, 4));
        assertEquals(-50,mathematicalOperations.binaryMultiplication(-25, 2));
        assertEquals(0,mathematicalOperations.binaryMultiplication(50, 0));
       
    }

    //! A method for testing the isSmaller method
    /*!
      checks whether the method works for some known cases
    */
    @org.junit.Test
    public void testIsSmaller() {
        assertEquals(true,mathematicalOperations.isSmaller(-7, 5));
        assertEquals(false,mathematicalOperations.isSmaller(12, 3));
        assertEquals(false,mathematicalOperations.isSmaller(4, 4));
        
    }

    //! A method for testing the isGreater method
    /*!
      checks whether the method works for some known cases
    */
    @org.junit.Test
    public void testIsGreater() {
        assertEquals(false,mathematicalOperations.isGreater(0, 5));
        assertEquals(false,mathematicalOperations.isGreater(7, 7));
        assertEquals(false,mathematicalOperations.isGreater(-1, 1));
    }

    //! A method for testing the remainder method
    /*!
      checks whether the method works for some known cases
    */
    @org.junit.Test
    public void testRemainder() {
        assertEquals(1,mathematicalOperations.remainder(10, 3));
        assertEquals(0,mathematicalOperations.remainder(12,1));
        assertEquals(4,mathematicalOperations.remainder(19, 5));

    }

    //! A method for testing the isEqual method
    /*!
      checks whether the method works for some known cases
    */
    @org.junit.Test
    public void testIsEqual() {
        assertEquals(true,mathematicalOperations.isEqual(8, 8));
        assertEquals(false,mathematicalOperations.isEqual(-1, 1));
        assertEquals(true,mathematicalOperations.isEqual(0, 0));
        assertEquals(false,mathematicalOperations.isEqual(0, 8));
    }

}
