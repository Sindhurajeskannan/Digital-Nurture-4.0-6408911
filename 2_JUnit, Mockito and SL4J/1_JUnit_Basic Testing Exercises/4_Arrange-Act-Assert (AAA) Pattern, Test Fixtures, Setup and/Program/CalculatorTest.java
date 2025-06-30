import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class CalculatorTest {

    private Calculator calc;

    // Setup: runs before each test
    @Before
    public void setUp() {
        System.out.println("Setting up...");
        calc = new Calculator();  // Arrange
    }

    // Teardown: runs after each test
    @After
    public void tearDown() {
        System.out.println("Cleaning up...");
        calc = null;
    }

    // Test method using Arrange-Act-Assert (AAA)
    @Test
    public void testAddition() {
        // Arrange: done in @Before
        // Act
        int result = calc.add(4, 6);
        // Assert
        assertEquals(10, result);
    }

    @Test
    public void testSubtraction() {
        // Act
        int result = calc.subtract(10, 3);
        // Assert
        assertEquals(7, result);
    }
}
