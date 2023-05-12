package pl.edu.pg;

import java.awt.*;
import java.util.Random;

public abstract class Plant extends Organism {
    /**
     * Value from 0 to 1
     */
    private static final double SPREAD_CHANCE = 0.08;

    public Plant(World world, Point position, int age, int strength) {
        super(world, position, age, strength, 0);
    }

    @Override
    public void action() {
        Random rand = new Random();
        if (rand.nextInt(1, 100) <= SPREAD_CHANCE * 100)
            spread();
    }

    @Override
    protected void collision(Animal attacker) {
        getWorld().despawn(this);
        attacker.move(position);
        System.out.println(attacker.getSymbol() + " ate " + getSymbol());
    }

    private void spread() {
        Point newPosition = getFreePosition();
        if (newPosition != null) {
            getWorld().spawn(OrganismsFactory.getOrganism(getSpecies(), getWorld(), newPosition));
            System.out.println("New " + getSymbol() + " has grown");
        }
    }
}