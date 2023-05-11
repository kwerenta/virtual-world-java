package pl.edu.pg;

import pl.edu.pg.ui.GameScreen;
import pl.edu.pg.ui.MainMenu;
import pl.edu.pg.ui.Screens;

import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {
    private static UI instance;
    public final static int WIDTH = 800;
    public final static int HEIGHT = 600;
    private final JPanel container;
    private World world;

    private UI() {
        super();

        setTitle("Virtual World");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(WIDTH, HEIGHT);

        container = new JPanel();
        container.setLayout(new CardLayout());
        container.add(new MainMenu(), Screens.MAIN_MENU.toString());

        add(container);
        setVisible(true);
    }

    public void createWorld(int width, int height) {
        if (world == null) {
            world = new World(width, height);
            world.makeTurn();
            container.add(new GameScreen(), Screens.GAME.toString());
        }
    }

    public World getWorld() {
        return world;
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