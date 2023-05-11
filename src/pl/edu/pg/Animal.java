package pl.edu.pg;

import java.awt.*;
import java.util.List;
import java.util.Random;

public abstract class Animal extends Organism {
    public Animal(World world, Point position, int age, int strength, int initiative) {
        super(world, position, age, strength, initiative);
    }

    @Override
    public void action() {
        Random rand = new Random();
        List<Point> adjacentPositions = world.getAdjacentPositions(getPosition());

        Point newPosition = adjacentPositions.get(rand.nextInt(adjacentPositions.size()));

        if (world.getMap(newPosition) == null)
            move(newPosition);
        else
            System.out.println("NOT EMPTY");
    }

    public void move(Point newPosition) {
        world.setMap(newPosition, this);
        world.setMap(position, null);
        position.setLocation(newPosition);
    }
}
