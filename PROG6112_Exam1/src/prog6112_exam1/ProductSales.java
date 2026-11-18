
package prog6112_exam1;

/** Stores sales for three mattress products over three months. */
public class ProductSales implements Product {
    public static final String[] PRODUCT_NAMES = {
        "Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"
    };

    // Rows: mattresses; columns: months 1, 2, and 3.
    private final int[][] sales = new int[3][3];

    public ProductSales(int[][] values) {
        if (values == null || values.length != 3) {
            throw new IllegalArgumentException("Enter sales for exactly three products.");
        }
        for (int product = 0; product < 3; product++) {
            if (values[product] == null || values[product].length != 3) {
                throw new IllegalArgumentException("Each product needs exactly three months.");
            }
            for (int month = 0; month < 3; month++) {
                if (values[product][month] < 0) {
                    throw new IllegalArgumentException("Sales cannot be negative.");
                }
                sales[product][month] = values[product][month];
            }
        }
    }

    public int[][] getSales() {
        int[][] copy = new int[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(sales[i], 0, copy[i], 0, 3);
        }
        return copy;
    }

    @Override
    public int GetTotalProductsSold() {
        int total = 0;
        for (int[] productSales : sales) {
            for (int monthSales : productSales) {
                total += monthSales;
            }
        }
        return total;
    }

    @Override
    public double GetAverageProductsSold() {
        return GetTotalProductsSold() / 9.0;
    }

    @Override
    public String GetMostPopularProduct() {
        int largestTotal = -1;
        int bestProduct = 0;
        for (int product = 0; product < 3; product++) {
            int productTotal = 0;
            for (int month : sales[product]) {
                productTotal += month;
            }
            if (productTotal > largestTotal) {
                largestTotal = productTotal;
                bestProduct = product;
            }
        }
        int productTotal = 0;
        for (int value : sales[bestProduct]) {
            productTotal += value;
        }
        return PRODUCT_NAMES[bestProduct] + " (" + productTotal + ")";
    }
}