package pl.edu.pg.ui;

import pl.edu.pg.UI;
import pl.edu.pg.World;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    public GameScreen() {
        super();

        add(new Label("Game"));
        String str = "Width: " + UI.getInstace().getWorld().getWidth();
        String str2 = "Height: " + UI.getInstace().getWorld().getHeight();
        add(new Label(str));
        add(new Label(str2));
    }
}