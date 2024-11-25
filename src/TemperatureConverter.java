import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverter {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Aplikasi Konversi Suhu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Konverter Suhu", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204));
        frame.add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel inputLabel = new JLabel("Masukkan nilai suhu:");
        inputLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(inputLabel, gbc);

        JTextField inputField = new JTextField(10);
        inputField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 0;
        centerPanel.add(inputField, gbc);

        JLabel scaleLabel = new JLabel("Pilih skala suhu:");
        scaleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(scaleLabel, gbc);

        String[] scales = {"Celcius", "Fahrenheit", "Kelvin"};
        JComboBox<String> scaleDropdown = new JComboBox<>(scales);
        scaleDropdown.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        centerPanel.add(scaleDropdown, gbc);

        JLabel resultCelcius = new JLabel("Celcius: -");
        resultCelcius.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(resultCelcius, gbc);

        JLabel resultFahrenheit = new JLabel("Fahrenheit: -");
        resultFahrenheit.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridy = 3;
        centerPanel.add(resultFahrenheit, gbc);

        JLabel resultKelvin = new JLabel("Kelvin: -");
        resultKelvin.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridy = 4;
        centerPanel.add(resultKelvin, gbc);

        frame.add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton convertButton = new JButton("Konversi");
        convertButton.setFont(new Font("Arial", Font.BOLD, 16));
        convertButton.setBackground(new Color(0, 153, 76));
        convertButton.setForeground(Color.WHITE);

        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.setBackground(new Color(204, 0, 0));
        resetButton.setForeground(Color.WHITE);

        buttonPanel.add(convertButton);
        buttonPanel.add(resetButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double inputValue = Double.parseDouble(inputField.getText());
                    String selectedScale = (String) scaleDropdown.getSelectedItem();

                    double celcius, fahrenheit, kelvin;

                    switch (selectedScale) {
                        case "Celcius":
                            celcius = inputValue;
                            fahrenheit = (celcius * 9 / 5) + 32;
                            kelvin = celcius + 273.15;
                            break;
                        case "Fahrenheit":
                            fahrenheit = inputValue;
                            celcius = (fahrenheit - 32) * 5 / 9;
                            kelvin = celcius + 273.15;
                            break;
                        case "Kelvin":
                            kelvin = inputValue;
                            celcius = kelvin - 273.15;
                            fahrenheit = (celcius * 9 / 5) + 32;
                            break;
                        default:
                            throw new IllegalArgumentException("Skala tidak dikenal");
                    }

                    resultCelcius.setText(String.format("Celcius: %.2f", celcius));
                    resultFahrenheit.setText(String.format("Fahrenheit: %.2f", fahrenheit));
                    resultKelvin.setText(String.format("Kelvin: %.2f", kelvin));

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Masukkan angka yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputField.setText("");
                resultCelcius.setText("Celcius: -");
                resultFahrenheit.setText("Fahrenheit: -");
                resultKelvin.setText("Kelvin: -");
                scaleDropdown.setSelectedIndex(0);
            }
        });

        frame.setVisible(true);
    }
}