package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * BadUIView.java
 * Provides the graphical interface for the bad UI form application.
 */
public class BadUIView {
    public JFrame frame;
    public JLabel firstNameLabel;
    public JLabel lastNameLabel;
    public JLabel usernameLabel;
    public JLabel passwordLabel;

    public JPanel firstNamePanel;
    public JPanel lastNamePanel;
    public JPanel usernamePanel;

    public JTextField passwordField;
    public JButton submitButton;

    // Sliders and Date
    public JSlider daySlider, monthSlider, yearSlider;
    public JLabel dateLabel;

    public BadUIView() {
        frame = new JFrame("Bad UI Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new GridLayout(7, 1));

        // First Name Input
        firstNameLabel = new JLabel("First Name: ");
        firstNamePanel = new JPanel();
        firstNamePanel.add(new JLabel("Click to type:"));
        frame.add(createInputPanel(firstNameLabel, firstNamePanel));

        // Last Name Input
        lastNameLabel = new JLabel("Last Name: ");
        lastNamePanel = new JPanel();
        lastNamePanel.add(new JLabel("Click to type:"));
        frame.add(createInputPanel(lastNameLabel, lastNamePanel));

        // Username Input
        usernameLabel = new JLabel("Username: ");
        usernamePanel = new JPanel();
        usernamePanel.add(new JLabel("Click to type:"));
        frame.add(createInputPanel(usernameLabel, usernamePanel));

        // Password Field
        passwordLabel = new JLabel("Password: ");
        passwordField = new JTextField(20); // Visible password
        JPanel passwordPanel = new JPanel();
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        frame.add(passwordPanel);

        // Age Slider for Birthdate
        JPanel agePanel = new JPanel(new GridLayout(4, 1));
        daySlider = new JSlider(1, 31, 1);
        daySlider.setPaintTicks(true);
        daySlider.setPaintLabels(true);
        agePanel.add(new JLabel("Day:"));
        agePanel.add(daySlider);

        monthSlider = new JSlider(1, 31, 1);
        monthSlider.setPaintTicks(true);
        monthSlider.setPaintLabels(true);
        agePanel.add(new JLabel("Month:"));
        agePanel.add(monthSlider);

        yearSlider = new JSlider(1900, 2024, 2000);
        yearSlider.setPaintTicks(true);
        yearSlider.setPaintLabels(true);
        agePanel.add(new JLabel("Year:"));
        agePanel.add(yearSlider);

        dateLabel = new JLabel("Selected Date (MM/DD/YYYY): 01/01/2000");
        agePanel.add(dateLabel);
        frame.add(agePanel);

        // Submit Button
        submitButton = new JButton("S\nU\nB\nM\nI\nT");
        submitButton.setPreferredSize(new Dimension(50, 150));
        frame.add(submitButton);

        frame.setVisible(true);
    }

    /**
     * Creates an input panel with the label and buttons for entering text.
     *
     * @param label  The label for the field.
     * @param panel  The panel to add buttons to.
     * @return A JPanel containing the label and input panel.
     */
    private JPanel createInputPanel(JLabel label, JPanel panel) {
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(label, BorderLayout.WEST);
        inputPanel.add(panel, BorderLayout.CENTER);
        return inputPanel;
    }

    /**
     * Creates letter buttons for manual text input.
     *
     * @return A list of buttons from A to Z.
     */
    public ArrayList<JButton> createLetterButtons() {
        ArrayList<JButton> buttons = new ArrayList<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            JButton button = new JButton(String.valueOf(c));
            buttons.add(button);
        }
        return buttons;
    }
}
