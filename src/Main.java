import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JTextField heightField;
    private JTextField weightField;
    private JLabel resultLabel;

    public Main() {
        // Configuración de la ventana principal
        setTitle("Calculadora de IMC");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creación de los componentes
        JLabel heightLabel = new JLabel("Altura (metros):");
        heightField = new JTextField(10);

        JLabel weightLabel = new JLabel("Peso (kilogramos):");
        weightField = new JTextField(10);

        JButton calculateButton = new JButton("Calcular IMC");
        calculateButton.addActionListener(new CalculateButtonListener());

        resultLabel = new JLabel("Ingresa tus datos y presiona Calcular IMC", JLabel.CENTER);

        // Configuración del diseño
        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        inputPanel.add(heightLabel);
        inputPanel.add(heightField);
        inputPanel.add(weightLabel);
        inputPanel.add(weightField);
        inputPanel.add(calculateButton);

        add(inputPanel, BorderLayout.CENTER);
        add(resultLabel, BorderLayout.SOUTH);
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double height = Double.parseDouble(heightField.getText());
                double weight = Double.parseDouble(weightField.getText());
                double bmi = weight / (height * height);

                String category;
                if (bmi < 18.5) {
                    category = "Bajo peso";
                } else if (bmi >= 18.5 && bmi < 24.9) {
                    category = "Peso normal";
                } else if (bmi >= 25 && bmi < 29.9) {
                    category = "Sobrepeso";
                } else {
                    category = "Obesidad";
                }

                String message = String.format("Tu IMC es: %.2f\nCategoría: %s", bmi, category);
                resultLabel.setText(message);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(Main.this, "Por favor ingresa valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}
