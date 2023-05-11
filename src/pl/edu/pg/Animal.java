package pl.edu.pg;

import java.awt.*;

public abstract class Animal extends Organism {
    public Animal(World world, Point position, int age, int strength, int initiative) {
        super(world, position, age, strength, initiative);
    }

    @Override
    public void action() {
        System.out.println(getSymbol());
    }
}
