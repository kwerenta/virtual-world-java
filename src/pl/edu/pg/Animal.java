package pl.edu.pg;

import java.awt.*;

public abstract class Animal extends Organism {
    public Animal(World world, Point position, int strength, int initiative, int age) {
        super(world, position, strength, initiative, age);
    }

    public void move(Point position) {
        this.position.setLocation(position);
    }
}
