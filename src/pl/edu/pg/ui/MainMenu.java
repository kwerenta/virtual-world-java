package pl.edu.pg.ui;

import pl.edu.pg.UI;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {

    public MainMenu() {
        super();

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel authorLabel = new JLabel("Kamil Wenta 193437");
        authorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(authorLabel);

        JLabel titleLabel = new JLabel("Virtual World");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);

        add(new JSlider());
        add(new JSlider());

        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> UI.getInstace().setScreen(Screens.GAME));
        add(startButton);
    }
}
