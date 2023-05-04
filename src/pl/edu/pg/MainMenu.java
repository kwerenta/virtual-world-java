package pl.edu.pg;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    public MainMenu() {
        super("Hello world");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(800,600);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JLabel authorLabel = new JLabel("Kamil Wenta 193437");
        authorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(authorLabel);

        JLabel titleLabel = new JLabel("Virtual World");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        panel.add(new JSlider());
        panel.add(new JSlider());

        panel.add(new JButton("Start"));

        add(panel);

        setVisible(true);
    }
}
