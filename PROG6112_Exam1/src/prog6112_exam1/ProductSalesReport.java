
package prog6112_exam1;

import java.util.Scanner;

/** Question 1: console-based sales report using 1D and 2D arrays. */
public class ProductSalesReport {
    public static void main(String[] args) {
        run(new Scanner(System.in));
    }

    public static void run(Scanner scanner) {
        int[][] values = new int[3][3];
        for (int product = 0; product < ProductSales.PRODUCT_NAMES.length; product++) {
            for (int month = 0; month < 3; month++) {
                while (true) {
                    System.out.print("Enter number of sales for month " + (month + 1)
                            + " for " + ProductSales.PRODUCT_NAMES[product] + ": ");
                    String input = scanner.nextLine().trim();
                    try {
                        int number = Integer.parseInt(input);
                        if (number < 0) {
                            System.out.println("Please enter zero or a positive whole number.");
                            continue;
                        }
                        values[product][month] = number;
                        break;
                    } catch (NumberFormatException error) {
                        System.out.println("Invalid input. Please enter a whole number.");
                    }
                }
            }
        }
        ProductSales report = new ProductSales(values);
        System.out.println("\nPRODUCT SALES REPORT - 2026");
        System.out.println("********************************************");
        System.out.println("TOTAL PRODUCT SALES: " + report.GetTotalProductsSold());
        System.out.printf("AVERAGE PRODUCT SALES: %.2f%n", report.GetAverageProductsSold());
        System.out.println("MOST POPULAR PRODUCT: " + report.GetMostPopularProduct());
    }
}