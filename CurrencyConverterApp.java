import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyConverterApp extends JFrame {

    private JTextField amountTextField;
    private JLabel resultLabel;
    private JComboBox<String> currencyComboBox;

    public CurrencyConverterApp() {
        // Set up the frame
        setTitle("Currency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

        // Create components
        amountTextField = new JTextField();
        currencyComboBox = new JComboBox<>(new String[]{"USD to INR", "INR to USD"});
        JButton convertButton = new JButton("Convert");
        resultLabel = new JLabel("Converted amount will be displayed here");

        // Add components to the frame
        add(new JLabel("Select Conversion Direction:"));
        add(currencyComboBox);
        add(new JLabel("Enter amount:"));
        add(amountTextField);
        add(convertButton);
        add(resultLabel);

        // Add action listener for the convert button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountTextField.getText());
            String selectedConversion = currencyComboBox.getSelectedItem().toString();
            double result = 0;

            if (selectedConversion.equals("USD to INR")) {
                result = amount * 74.5; // You can change this rate to the current rate
            } else if (selectedConversion.equals("INR to USD")) {
                result = amount / 74.5; // Reverse conversion
            }

            resultLabel.setText("Converted amount: " + result);
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input. Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CurrencyConverterApp converter = new CurrencyConverterApp();
                converter.setVisible(true);
            }
        });
    }
}
