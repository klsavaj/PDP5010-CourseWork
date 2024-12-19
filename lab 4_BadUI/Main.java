import view.BadUIView;
import controller.BadUIController;
import model.UserModel;

import javax.swing.*;

/**
 * Main.java
 * Entry point for the bad UI form application.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BadUIView view = new BadUIView();
            UserModel model = new UserModel();
            new BadUIController(view, model);
        });
    }
}
