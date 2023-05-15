package pl.edu.pg.animals;

import pl.edu.pg.Animal;
import pl.edu.pg.Organism;
import pl.edu.pg.Point;
import pl.edu.pg.World;

import java.awt.event.KeyEvent;

public class Human extends Animal {
    private Point queuedPosition;

    public Human(World world, Point position, int age) {
        super(world, position, age, 5, 4);
    }

    public void handleUserInput(int keyCode) {
        Point newPosition = new Point(position);
        switch (keyCode) {
            case KeyEvent.VK_UP -> newPosition.translate(0, -1);
            case KeyEvent.VK_RIGHT -> newPosition.translate(1, 0);
            case KeyEvent.VK_DOWN -> newPosition.translate(0, 1);
            case KeyEvent.VK_LEFT -> newPosition.translate(-1, 0);
            default -> {
                return;
            }
        }
        if (getWorld().isValidPosition(newPosition))
            queuedPosition = newPosition;
    }

    @Override
    public void action() {
        if (queuedPosition == null) return;
        Organism target = getWorld().getMap(queuedPosition);
        if (target == null)
            move(queuedPosition);
        else
            target.collision(this);

        queuedPosition = null;
    }

    @Override
    public String getSymbol() {
        return "H";
    }

    @Override
    public Species getSpecies() {
        return Species.HUMAN;
    }
}