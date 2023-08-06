import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainGUI extends JFrame {
    private final JCheckBox[] checkboxes;

    public mainGUI(String[] items) {
        super("Checklist GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        checkboxes = new JCheckBox[items.length];
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(items.length, 1));

        for (int i = 0; i < items.length; i++) {
            checkboxes[i] = new JCheckBox(items[i]);
            panel.add(checkboxes[i]);
        }

        JButton button = new JButton("Submit");
        button.addActionListener(e -> {
            StringBuilder result = new StringBuilder("Selected Items:\n");
            for (JCheckBox checkbox : checkboxes) {
                if (checkbox.isSelected()) {
                    result.append(checkbox.getText()).append("\n");
                }
            }
            JOptionPane.showMessageDialog(mainGUI.this, result.toString());
        });

        panel.add(button);
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        String[] items = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
        SwingUtilities.invokeLater(() -> new mainGUI(items));
    }
}