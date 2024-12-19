package controller;

import model.UserModel;
import view.BadUIView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * BadUIController.java
 * This class acts as the controller in the MVC pattern, managing interactions between 
 * the view (BadUIView) and the model (UserModel).
 */
public class BadUIController {
    private BadUIView view;
    private UserModel model;
    public BadUIController(BadUIView view, UserModel model) {
        this.view = view;
        this.model = model;

        setupLetterButtons(view.firstNamePanel, model::setFirstName, view.firstNameLabel);
        setupLetterButtons(view.lastNamePanel, model::setLastName, view.lastNameLabel);
        setupUsernameField();
        setupPasswordField();
        setupAgeSlider();
        setupSubmitButton();
    }

    /**
     * Configures letter buttons for input and binds them to update the model and label.
     *
     * @param panel  the panel to contain the buttons
     * @param setter a consumer to update the model with the input
     * @param label  the label to display the input
     */
    private void setupLetterButtons(JPanel panel, java.util.function.Consumer<String> setter, JLabel label) {
        StringBuilder buffer = new StringBuilder();
        panel.setLayout(new GridLayout(3, 9));
        for (JButton button : view.createLetterButtons()) {
            panel.add(button);
            button.addActionListener(e -> {
                buffer.append((char) ('A' + (button.getText().charAt(0) - 'A' + 1) % 26)); // Shift letter
                setter.accept(buffer.toString());
                label.setText(label.getText().split(":")[0] + ": " + buffer.toString());
            });
        }
    }

    /**
     * Configures the username field by setting up letter buttons to allow input.
     */
    private void setupUsernameField() {
        view.usernamePanel.setLayout(new GridLayout(3, 9));
        for (JButton button : view.createLetterButtons()) {
            view.usernamePanel.add(button);
            button.addActionListener(e -> {
                String repeatChar = button.getText().toLowerCase();
                model.setUsername(repeatChar.repeat(1)); // Repeat the first character
                view.usernameLabel.setText("Username: " + model.getUsername());
            });
        }
    }

    /**
     * Configures the password field to update the model when a password is entered.
     */
    private void setupPasswordField() {
        view.passwordField.addActionListener(e -> model.setPassword(view.passwordField.getText()));
    }

    /**
     * Configures sliders for selecting a date and updates the model with the selected date.
     */
    private void setupAgeSlider() {
        ChangeListener updateDate = (ChangeEvent e) -> {
            int day = view.daySlider.getValue();
            int month = view.monthSlider.getValue();
            int year = view.yearSlider.getValue();
            String swappedDate = String.format("%02d/%02d/%04d", day, month, year); // Swapped date
            model.setSelectedDate(swappedDate);
            view.dateLabel.setText("Selected Date (MM/DD/YYYY): " + swappedDate);
        };

        view.daySlider.addChangeListener(updateDate);
        view.monthSlider.addChangeListener(updateDate);
        view.yearSlider.addChangeListener(updateDate);
    }

    /**
     * Configures the submit button with actions that visually alter the button but 
     * do not perform any actual submission.
     */
    private void setupSubmitButton() {
        view.submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prevent any real submission
                System.out.println("Submit button clicked, but no action performed.");

                // Change button size
                view.submitButton.setPreferredSize(new Dimension(100, 200));
                view.submitButton.revalidate();

                // Set vertical text
                String verticalText = "<html>S<br>U<br>B<br>M<br>I<br>T</html>";
                view.submitButton.setText(verticalText);
            }
        });
    }
}
