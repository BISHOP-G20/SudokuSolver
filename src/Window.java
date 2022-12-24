import javax.swing.*;
import java.awt.*;



public class Window extends JFrame {

    Window() {

        Panel panel = new Panel();

        ButtonPanel buttonPanel = new ButtonPanel(panel);

        this.setLayout(new GridBagLayout());

        this.add(buttonPanel, buttonPanel.buttonConstraints());
        this.add(panel, panel.panelConstraints());

        this.setBackground(Color.BLACK);
        this.setSize(650, 800);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Sudoku Solver");

    }

}