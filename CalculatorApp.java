import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp extends JFrame {
    // Create components
    private JTextField display;
    private JButton[] digitButtons;
    private JButton[] operatorButtons;

    private double firstOperand = 0;
    private String operator = "";
    private boolean isTyping = false;

    public CalculatorApp() {
        setTitle("Simple Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300, 400));
        setLayout(new BorderLayout());

        // Create the display
        
        
        display = new JTextField();
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Create and configure digit buttons
        JPanel digitPanel = new JPanel(new GridLayout(4, 3));
        digitButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            digitButtons[i] = new JButton(String.valueOf(i));
            digitButtons[i].addActionListener(new DigitButtonListener());
            digitPanel.add(digitButtons[i]);
        }
        add(digitPanel, BorderLayout.CENTER);

        // Create and configure operator buttons
        JPanel operatorPanel = new JPanel(new GridLayout(5, 1));
        operatorButtons = new JButton[5];
        String[] operators = {"+", "-", "*", "/", "="};
        for (int i = 0; i < 5; i++) {
            operatorButtons[i] = new JButton(operators[i]);
            operatorButtons[i].addActionListener(new OperatorButtonListener());
            operatorPanel.add(operatorButtons[i]);
        }
        add(operatorPanel, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null); // Center the window
    }

    private class DigitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String digit = source.getText();
            
            if (isTyping) {
                display.setText(display.getText() + digit);
            } else {
                display.setText(digit);
                isTyping = true;
            }
        }
    }

    private class OperatorButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String currentOperator = source.getText();

            if (!operator.isEmpty()) {
                double secondOperand = Double.parseDouble(display.getText());
                switch (operator) {
                    case "+":
                        firstOperand += secondOperand;
                        break;
                    case "-":
                        firstOperand -= secondOperand;
                        break;
                    case "*":
                        firstOperand *= secondOperand;
                        break;
                    case "/":
                        if (secondOperand != 0) {
                            firstOperand /= secondOperand;
                        } else {
                            display.setText("Error");
                            return;
                        }
                        break;
                }
                display.setText(String.valueOf(firstOperand));
            }
            
            operator = currentOperator;
            isTyping = false;
            firstOperand = Double.parseDouble(display.getText());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorApp calculator = new CalculatorApp();
            calculator.setVisible(true);
        });
    }
}

