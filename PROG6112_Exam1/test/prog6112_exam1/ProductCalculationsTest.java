package prog6112_exam1;

import org.junit.Test;
import static org.junit.Assert.*;

/** Required Q2.6 unit tests. */
public class ProductCalculationsTest {
    private final ProductCalculations calculations = new ProductCalculations(3500.0);

    @Test
    public void GetVat_WithValidData_ReturnsCorrectValue() {
        assertEquals(525.0, calculations.GetVat(), 0.0001);
    }

    @Test
    public void GetTotal_WithValidData_ReturnsCorrectValue() {
        assertEquals(4200.0, calculations.GetTotal(), 0.0001);
    }

    @Test
    public void GetCommission_WithValidData_ReturnsCorrectValue() {
        assertEquals(175.0, calculations.GetCommission(), 0.0001);
    }
}