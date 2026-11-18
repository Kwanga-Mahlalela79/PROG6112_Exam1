
package prog6112_exam1;

import java.awt.GridLayout;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Student ST10505829 - Kwanga Mahlalela
 */
/** Two questions in One NetBeans Project consisting of a console application menu and GUI */

public class Main {
    public static void main(String[] args) {
        // Optional console mode: java mattresssalesassignment.Main console
        if (args.length > 0 && args[0].equalsIgnoreCase("console")) {
            ProductSalesReport.run(new Scanner(System.in));
            return;
        }
        SwingUtilities.invokeLater(Main::showLauncher);
    }

    private static void showLauncher() {
        JFrame frame = new JFrame("Mattress Sales Assignment - Questions 1 and 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(480, 175);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(2, 1, 12, 12));
        JButton question1 = new JButton("QUESTION 1 - Three-month Sales Report");
        JButton question2 = new JButton("QUESTION 2 - Sales Processing GUI");
        question1.addActionListener(e -> showQuestion1(frame));
        question2.addActionListener(e -> {
            new MattressSalesGUI().setVisible(true);
            frame.dispose();
        });
        frame.add(question1);
        frame.add(question2);
        frame.setVisible(true);
    }

    private static void showQuestion1(JFrame parent) {
        int[][] sales = new int[3][3];
        for (int product = 0; product < 3; product++) {
            for (int month = 0; month < 3; month++) {
                while (true) {
                    String prompt = "Enter number of sales for month " + (month + 1)
                            + " for " + ProductSales.PRODUCT_NAMES[product] + ":";
                    String answer = JOptionPane.showInputDialog(parent, prompt, "Question 1",
                            JOptionPane.QUESTION_MESSAGE);
                    if (answer == null) {
                        return; // User cancelled: do not create a partial report.
                    }
                    try {
                        int value = Integer.parseInt(answer.trim());
                        if (value < 0) {
                            throw new NumberFormatException();
                        }
                        sales[product][month] = value;
                        break;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(parent,
                                "Enter a non-negative whole number.", "Invalid sales",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        ProductSales report = new ProductSales(sales);
        String result = String.format("PRODUCT SALES REPORT - 2026%n"
                        + "TOTAL PRODUCT SALES: %d%n"
                        + "AVERAGE PRODUCT SALES: %.2f%n"
                        + "MOST POPULAR PRODUCT: %s",
                report.GetTotalProductsSold(), report.GetAverageProductsSold(),
                report.GetMostPopularProduct());
        System.out.println(result);
        JOptionPane.showMessageDialog(parent, result, "Question 1: Sales Report",
                JOptionPane.INFORMATION_MESSAGE);
    }
}