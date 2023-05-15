package pl.edu.pg;

import pl.edu.pg.ui.GameScreen;
import pl.edu.pg.ui.MainMenu;
import pl.edu.pg.ui.MenuBar;
import pl.edu.pg.ui.Screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UI extends JFrame implements KeyListener {
    private static UI instance;
    public final static int WIDTH = 800;
    public final static int HEIGHT = 600;
    private final JPanel container;
    private GameScreen gameScreen;
    private World world;

    private UI() {
        super();

        setTitle("Virtual World");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);

        container = new JPanel();
        container.setLayout(new CardLayout());
        container.add(new MainMenu(), Screens.MAIN_MENU.toString());

        setJMenuBar(new MenuBar());
        add(container);

        addKeyListener(this);
        setVisible(true);
        setFocusable(true);
        requestFocus();
    }

    public void createWorld(int width, int height, Scanner scanner) {
        if (world == null) {
            world = new World(width, height, scanner);

            gameScreen = new GameScreen();
            container.add(gameScreen, Screens.GAME.toString());
            setScreen(Screens.GAME);
        }
    }

    public void createWorld(int width, int height) {
        createWorld(width, height, null);
    }

    public void destroyWorld() {
        setScreen(Screens.MAIN_MENU);
        if (gameScreen != null)
            container.remove(gameScreen);
        gameScreen = null;
        world = null;
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

    @Override
    public void keyPressed(KeyEvent e) {
        if (world == null)
            return;

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            world.makeTurn();
            gameScreen.updateBoard();
            gameScreen.updateLogs();
        } else if (world.getHuman() != null) {
            world.getHuman().handleUserInput(e.getKeyCode());
        }
    }

    public void loadWorld() throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File("save.txt"))) {
            int width = scanner.nextInt();
            int height = scanner.nextInt();

            destroyWorld();
            createWorld(width, height, scanner);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}