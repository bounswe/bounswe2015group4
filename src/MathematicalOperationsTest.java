import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MathematicalOperationsTest {
    static MathematicalOperations mathematicalOperations;

    @BeforeClass
    public static void init() {
        mathematicalOperations = new MathematicalOperations();
    }

    @org.junit.Test
    public void testBinaryPlus() {
        assertEquals(8,mathematicalOperations.binaryPlus(3,5));
        assertEquals(1,mathematicalOperations.binaryPlus(-4,5));
        assertEquals(0,mathematicalOperations.binaryPlus(64,-64));
    }
    
    @org.junit.Test
    public void testBinaryMinus() {
        assertEquals(-5,mathematicalOperations.binaryMinus(0,5));
        assertEquals(-9,mathematicalOperations.binaryMinus(-2,7));
        assertEquals(0,mathematicalOperations.binaryMinus(53,53));
        assertEquals(9,mathematicalOperations.binaryMinus(5,-4));
    }
    
        @org.junit.Test
    public void testBinaryMultiplication() {
        assertEquals(8,mathematicalOperations.binaryMultiplication(2,4));
        assertEquals(-50,mathematicalOperations.binaryMultiplication(-25,2));
        assertEquals(0,mathematicalOperations.binaryMultiplication(50,0));
       
    }
}
