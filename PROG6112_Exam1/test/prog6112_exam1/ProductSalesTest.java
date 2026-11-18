package prog6112_exam1;

import org.junit.Test;
import static org.junit.Assert.*;

/** Required Q1.4 unit tests, plus a popular-product check. */
public class ProductSalesTest {
    private final ProductSales sales = new ProductSales(new int[][] {
        {25, 18, 20}, {15, 10, 12}, {18, 15, 20}
    });

    @Test
    public void GetTotalProductsSold_WithValidData_ReturnsCorrectValue() {
        assertEquals(153, sales.GetTotalProductsSold());
    }

    @Test
    public void GetAverageSales_WithValidData_ReturnsCorrectValue() {
        assertEquals(17.0, sales.GetAverageProductsSold(), 0.0001);
    }

    @Test
    public void GetMostPopularProduct_WithValidData_ReturnsCorrectValue() {
        assertEquals("Cozy Nights Monarch (63)", sales.GetMostPopularProduct());
    }
}