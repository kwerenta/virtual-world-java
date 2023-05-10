package pl.edu.pg;

import pl.edu.pg.ui.GameScreen;
import pl.edu.pg.ui.MainMenu;
import pl.edu.pg.ui.Screens;

import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {
    private static UI instance;
    private final JPanel container;

    private UI() {
        super();

        setTitle("Virtual World");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(800, 600);

        container = new JPanel();
        container.setLayout(new CardLayout());
        container.add(new MainMenu(), Screens.MAIN_MENU.toString());
        container.add(new GameScreen(), Screens.GAME.toString());

        add(container);
        setVisible(true);
    }

    public void setScreen(Screens name) {
        CardLayout layout = (CardLayout) container.getLayout();
        layout.show(container, name.toString());
    }

    public static UI getInstace() {
        if (instance == null)
            instance = new UI();

        return instance;
    }
}