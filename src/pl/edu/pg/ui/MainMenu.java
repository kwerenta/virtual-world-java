package pl.edu.pg.ui;

import pl.edu.pg.UI;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {
    private final JSlider widthSlider;
    private final JSlider heightSlider;

    public MainMenu() {
        super();

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel authorLabel = new JLabel("Kamil Wenta 193437");
        authorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(authorLabel);

        JLabel titleLabel = new JLabel("Virtual World");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);

        widthSlider = new JSlider(1, 30, 10);
        heightSlider = new JSlider(1, 30, 10);
        add(widthSlider);
        add(heightSlider);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            UI ui = UI.getInstace();
            ui.createWorld(widthSlider.getValue(), heightSlider.getValue());
        });
        add(startButton);
    }
}
