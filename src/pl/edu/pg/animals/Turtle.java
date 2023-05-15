package pl.edu.pg.animals;

import pl.edu.pg.Animal;
import pl.edu.pg.Point;
import pl.edu.pg.World;

import java.util.Random;

public class Turtle extends Animal {

    public Turtle(World world, Point position, int age) {
        super(world, position, age, 2, 1);
    }

    @Override
    public void action() {
        Random rand = new Random();
        if (rand.nextInt(4) != 0) {
            shouldSkipTurn = true;
            getWorld().addLog(getSymbol() + " stayed at place " + position);
        } else
            super.action();
    }

    @Override
    public boolean hasRepelled(Animal attacker) {
        return attacker.getStrength() < 5;
    }

    @Override
    public String getSymbol() {
        return "T";
    }

    @Override
    public Species getSpecies() {
        return Species.TURTLE;
    }
}