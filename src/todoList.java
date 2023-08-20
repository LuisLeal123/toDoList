import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/*
        To-do list with a GUI
 Create a simple to-do list application that allows users to add,
 delete, and mark tasks as complete.
 */

public class todoList extends JFrame {
    // array of 'JCheckBox' instances which acts sort of like a GUI boolean array to see which are checked
    private JCheckBox[] checkboxes;
    // array list which contains the tasks
    private final ArrayList<String> tasks;

    // Constructor to open the GUI
    public todoList(String[] items) {
        // sets the name for the window using a JFrame constructor
        super("Checklist GUI");
        // closes the GUI instead of keeping it running (JFrame inherits setDefaultCloseOperation from WindowConstants interface)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create instance variables of the 'tasks' & 'checkboxes'
        tasks = new ArrayList<>();
        tasks.addAll(Arrays.asList(items));
        checkboxes = new JCheckBox[items.length];
        JPanel panel = new JPanel();
        // uses grid layout: 1 column & n+3 rows
        panel.setLayout(new GridLayout(items.length + 3, 1));

        // fills the 'checkboxes' instance variable & fills the panel with JCheckBoxes
        for (int i = 0; i < items.length; i++) {
            checkboxes[i] = new JCheckBox(items[i]);
            panel.add(checkboxes[i]);
        }


        // code for the add task button
        JButton addButton = new JButton("Add Task");
        addButton.addActionListener(e -> {
            // prompts user to type out a new task
            String newTask = JOptionPane.showInputDialog(todoList.this, "Enter the new task:");
            // is now added to new task if it is not null or is not just blank space
            if (newTask != null && !newTask.trim().isEmpty()) {
                tasks.add(newTask);
                // checkboxes don't update without this
                updateCheckboxes();
            }
        });


        // code for the delete task button
        JButton deleteButton = new JButton("Delete Selected Tasks");
        deleteButton.addActionListener(e -> {
            // counts the tasks selected
            int numChecked = 0;
            for (JCheckBox checkbox : checkboxes) {
                if (checkbox.isSelected()) {
                    numChecked++;
                }
            }
            // if selected
            if (numChecked > 0) {
                // shows yes/no dialog box to confirm a deletion
                int confirm = JOptionPane.showConfirmDialog(todoList.this,
                        "Are you sure you want to delete the selected task(s)?",
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    // add tasks to be deleted into a list
                    ArrayList<String> tasksToDelete = new ArrayList<>();
                    for (int i = 0; i < checkboxes.length; i++) {
                        if (checkboxes[i].isSelected()) {
                            tasksToDelete.add(tasks.get(i));
                        }
                    }
                    // deletes all tasks
                    tasks.removeAll(tasksToDelete);
                    updateCheckboxes();
                }
                // if no boxes were selected, shows a dialog box
            } else {
                JOptionPane.showMessageDialog(todoList.this,
                        "Please select tasks to delete.", "No Tasks Selected", JOptionPane.INFORMATION_MESSAGE);
            }
        });


        // code for the 'mark task as complete' button
        JButton completeButton = new JButton("Mark Selected Tasks as Complete");
        completeButton.addActionListener(e -> {
            // checks for number of tasks complete
            int numChecked = 0;
            for (JCheckBox checkbox : checkboxes) {
                if (checkbox.isSelected()) {
                    numChecked++;
                }
            }
            if (numChecked > 0) {
                // checks if you want to confirm
                int confirm = JOptionPane.showConfirmDialog(todoList.this,
                        "Are you sure you want to mark the selected task(s) as complete?",
                        "Confirm Completion", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    for (int i = 0; i < checkboxes.length; i++) {
                        if (checkboxes[i].isSelected()) {
                            tasks.set(i, "[Complete] " + tasks.get(i));
                        }
                    }
                    // helper function accounts for tasks which are already complete
                    updateCheckboxes();
                }
            } else {
                JOptionPane.showMessageDialog(todoList.this,
                        "Please select tasks to mark as complete.", "No Tasks Selected", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // adds the buttons to JPanel
        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(completeButton);

        // adds the panel to the top level container of the GUI
        getContentPane().add(panel);
        // makes the GUI an appropriate size
        pack();
        // centers the frame
        setLocationRelativeTo(null);
        // displays the GUI (no longer behind the curtains)
        setVisible(true);
    }

    // Keeps the GUI up to date with the underlying list of tasks
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
            String newTask = JOptionPane.showInputDialog(todoList.this, "Enter the new task:");
            if (newTask != null && !newTask.trim().isEmpty()) {
                tasks.add(newTask);
                updateCheckboxes();
            }
        });

        // code for deleting the tasks
        JButton deleteButton = new JButton("Delete Selected Tasks");
        deleteButton.addActionListener(e -> {
            int numChecked = 0;
            for (JCheckBox checkbox : checkboxes) {
                if (checkbox.isSelected()) {
                    numChecked++;
                }
            }
            if (numChecked > 0) {
                int confirm = JOptionPane.showConfirmDialog(todoList.this,
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
                JOptionPane.showMessageDialog(todoList.this,
                        "Please select tasks to delete.", "No Tasks Selected", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // code for marking tasks as complete
        JButton completeButton = new JButton("Mark Selected Tasks as Complete");
        completeButton.addActionListener(e -> {
            int numChecked = 0;
            for (JCheckBox checkbox : checkboxes) {
                if (checkbox.isSelected()) {
                    numChecked++;
                }
            }
            if (numChecked > 0) {
                int confirm = JOptionPane.showConfirmDialog(todoList.this,
                        "Are you sure you want to mark the selected task(s) as complete?",
                        "Confirm Completion", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    for (int i = 0; i < checkboxes.length; i++) {
                        if (checkboxes[i].isSelected() && !tasks.get(i).startsWith("[Complete] ")) {
                            tasks.set(i, "[Complete] " + tasks.get(i));
                        }
                    }
                    updateCheckboxes();
                }
            } else {
                JOptionPane.showMessageDialog(todoList.this,
                        "Please select tasks to mark as complete.", "No Tasks Selected", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(completeButton);
        getContentPane().add(panel);
        pack();
        revalidate();
        repaint();
    }

    // main function
    public static void main(String[] args) {
        String[] items = {};
        // use a lambda to invoke the GUI (starts with nothing
        SwingUtilities.invokeLater(() -> new todoList(items));
    }
}