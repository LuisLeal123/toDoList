import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/*
        To-do list with a GUI
 Create a simple to-do list application that allows users to add,
 delete, and mark tasks as complete.
 */
// current issue to fix: if complete, they can only be deleted
// if you select a complete task, the non-complete tasks gray out
public class mainGUI extends JFrame {
    // checklist
    private JCheckBox[] checkboxes;
    // array list which contains the tasks
    private final ArrayList<String> tasks;

    // Constructor to open the GUI
    public mainGUI(String[] items) {
        // sets the name for the window using a JFrame constructor
        super("Checklist GUI");
        // closes the GUI instead of keeping it running
        // JFrame inherits setDefaultCloseOperation from WindowConstants interface
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // initialize it as an instance
        // add tasks from input to this instance
        tasks = new ArrayList<>();
        tasks.addAll(Arrays.asList(items));

        // initialize array as length of tasks in input
        checkboxes = new JCheckBox[items.length];
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(items.length + 3, 1));

        for (int i = 0; i < items.length; i++) {
            checkboxes[i] = new JCheckBox(items[i]);
            panel.add(checkboxes[i]);
        }

        JButton addButton = new JButton("Add Task");
        addButton.addActionListener(e -> {
            String newTask = JOptionPane.showInputDialog(mainGUI.this, "Enter the new task:");
            if (newTask != null && !newTask.trim().isEmpty()) {
                tasks.add(newTask);
                updateCheckboxes();
            }
        });

        JButton deleteButton = new JButton("Delete Selected Tasks");
        deleteButton.addActionListener(e -> {
            int numChecked = 0;
            for (int i = 0; i < checkboxes.length; i++) {
                if (checkboxes[i].isSelected()) {
                    numChecked++;
                }
            }
            if (numChecked > 0) {
                int confirm = JOptionPane.showConfirmDialog(mainGUI.this,
                        "Are you sure you want to delete the selected task(s)?",
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    ArrayList<String> tasksToDelete = new ArrayList<>();
                    for (int i = 0; i < checkboxes.length; i++) {
                        if (checkboxes[i].isSelected()) {
                            tasksToDelete.add(tasks.get(i));
                        }
                    }
                    tasks.removeAll(tasksToDelete);
                    updateCheckboxes();
                }
            } else {
                JOptionPane.showMessageDialog(mainGUI.this,
                        "Please select tasks to delete.", "No Tasks Selected", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton completeButton = new JButton("Mark Selected Tasks as Complete");
        completeButton.addActionListener(e -> {
            int numChecked = 0;
            for (int i = 0; i < checkboxes.length; i++) {
                if (checkboxes[i].isSelected()) {
                    numChecked++;
                }
            }
            if (numChecked > 0) {
                int confirm = JOptionPane.showConfirmDialog(mainGUI.this,
                        "Are you sure you want to mark the selected task(s) as complete?",
                        "Confirm Completion", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    for (int i = 0; i < checkboxes.length; i++) {
                        if (checkboxes[i].isSelected()) {
                            tasks.set(i, "[Complete] " + tasks.get(i));
                        }
                    }
                    updateCheckboxes();
                }
            } else {
                JOptionPane.showMessageDialog(mainGUI.this,
                        "Please select tasks to mark as complete.", "No Tasks Selected", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(completeButton);

        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // updates the checkboxes?
    private void updateCheckboxes() {
        getContentPane().removeAll();
        checkboxes = new JCheckBox[tasks.size()];
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(tasks.size() + 3, 1));

        for (int i = 0; i < tasks.size(); i++) {
            checkboxes[i] = new JCheckBox(tasks.get(i));
            panel.add(checkboxes[i]);
        }

        JButton addButton = new JButton("Add Task");
        addButton.addActionListener(e -> {
            String newTask = JOptionPane.showInputDialog(mainGUI.this, "Enter the new task:");
            if (newTask != null && !newTask.trim().isEmpty()) {
                tasks.add(newTask);
                updateCheckboxes();
            }
        });

        JButton deleteButton = new JButton("Delete Selected Tasks");
        deleteButton.addActionListener(e -> {
            int numChecked = 0;
            for (int i = 0; i < checkboxes.length; i++) {
                if (checkboxes[i].isSelected()) {
                    numChecked++;
                }
            }
            if (numChecked > 0) {
                int confirm = JOptionPane.showConfirmDialog(mainGUI.this,
                        "Are you sure you want to delete the selected task(s)?",
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    ArrayList<String> tasksToDelete = new ArrayList<>();
                    for (int i = 0; i < checkboxes.length; i++) {
                        if (checkboxes[i].isSelected()) {
                            tasksToDelete.add(tasks.get(i));
                        }
                    }
                    tasks.removeAll(tasksToDelete);
                    updateCheckboxes();
                }
            } else {
                JOptionPane.showMessageDialog(mainGUI.this,
                        "Please select tasks to delete.", "No Tasks Selected", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton completeButton = new JButton("Mark Selected Tasks as Complete");
        completeButton.addActionListener(e -> {
            int numChecked = 0;
            for (int i = 0; i < checkboxes.length; i++) {
                if (checkboxes[i].isSelected()) {
                    numChecked++;
                }
            }
            if (numChecked > 0) {
                int confirm = JOptionPane.showConfirmDialog(mainGUI.this,
                        "Are you sure you want to mark the selected task(s) as complete?",
                        "Confirm Completion", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    for (int i = 0; i < checkboxes.length; i++) {
                        if (checkboxes[i].isSelected()) {
                            tasks.set(i, "[Complete] " + tasks.get(i));
                        }
                    }
                    updateCheckboxes();
                }
            } else {
                JOptionPane.showMessageDialog(mainGUI.this,
                        "Please select tasks to mark as complete.", "No Tasks Selected", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(completeButton);

        getContentPane().add(panel);
        revalidate();
        repaint();
    }

    // Main Function to open GUI
    public static void main(String[] args) {
        String[] items = {"workout", "breakfast", "run"};
        SwingUtilities.invokeLater(() -> new mainGUI(items));
    }
}