
package prog6112_exam1;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

/** Question 2: Java Swing sales-entry form and data.txt storage. */
public class MattressSalesGUI extends JFrame {
    private final JTextField customerField = new JTextField(22);
    private final JComboBox<String> productBox = new JComboBox<>(ProductSales.PRODUCT_NAMES);
    private final JTextField priceField = new JTextField(22);

    public MattressSalesGUI() {
        super("Product App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(570, 320);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> dispose());
        file.add(exit);
        JMenu tools = new JMenu("Tools");
        JMenuItem process = new JMenuItem("Process");
        process.addActionListener(e -> processSale());
        JMenuItem clear = new JMenuItem("Clear");
        clear.addActionListener(e -> clearForm());
        tools.add(process);
        tools.add(clear);
        menuBar.add(file);
        menuBar.add(tools);
        setJMenuBar(menuBar);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(new EmptyBorder(22, 22, 22, 22));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(9, 8, 9, 8);
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        form.add(new JLabel("Customer Name:"), c);
        c.gridx = 1;
        form.add(customerField, c);
        c.gridx = 0;
        c.gridy = 1;
        form.add(new JLabel("Select Product:"), c);
        c.gridx = 1;
        form.add(productBox, c);
        c.gridx = 0;
        c.gridy = 2;
        form.add(new JLabel("Enter Price:"), c);
        c.gridx = 1;
        form.add(priceField, c);
        JButton processButton = new JButton("PROCESS");
        processButton.addActionListener(e -> processSale());
        c.gridx = 1;
        c.gridy = 3;
        form.add(processButton, c);
        add(form, BorderLayout.CENTER);
        getRootPane().setDefaultButton(processButton);
    }

    private void processSale() {
        String customer = customerField.getText().trim();
        String product = (String) productBox.getSelectedItem();
        if (customer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter a customer name.", "Missing name", JOptionPane.WARNING_MESSAGE);
            return;
        }
        double price;
        try {
            price = Double.parseDouble(priceField.getText().trim());
            if (!Double.isFinite(price) || price <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(this, "Enter a valid price greater than zero.", "Invalid price", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ProductCalculations calc = new ProductCalculations(price);
        String details = String.format(Locale.US,
                "Customer Name: %s%nProduct: %s%nVAT: R %.2f%nCOMMISSION: R %.2f%nTOTAL: R %.2f",
                customer, product, calc.GetVat(), calc.GetCommission(), calc.GetTotal());

        // Save the sale as well as showing the results, as specified in Q2.3.
        String log = "DATA LOG\n********************************************\n"
                + details + "\n********************************************\n\n";
        Path file = Paths.get("data.txt").toAbsolutePath();
        try {
            Files.write(file, log.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            JOptionPane.showMessageDialog(this, details, "Message", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException error) {
            JOptionPane.showMessageDialog(this,
                    "Unable to save data.txt: " + error.getMessage(), "Save error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        customerField.setText("");
        priceField.setText("");
        productBox.setSelectedIndex(0);
        customerField.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MattressSalesGUI().setVisible(true));
    }
}