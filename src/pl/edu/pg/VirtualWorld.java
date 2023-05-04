package pl.edu.pg;

import java.awt.*;

public class VirtualWorld {
    public static void main(String[] args) {
        EventQueue.invokeLater(MainMenu::new);
        World world = new World(20,10);
        world.getMap(1,0).draw();
    }
}