package pl.edu.pg.animals;

import pl.edu.pg.Animal;
import pl.edu.pg.Organism;
import pl.edu.pg.Point;
import pl.edu.pg.World;

import java.util.Random;

public class Antelope extends Animal {

    public Antelope(World world, Point position, int age) {
        super(world, position, age, 4, 4);
    }

    @Override
    public void action() {
        Point newPosition = getAdjacentPosition(2);
        Organism target = getWorld().getMap(newPosition);

        if (target == null)
            move(newPosition);
        else
            target.collision(this);
    }

    @Override
    public boolean hasRunAway() {
        Random rand = new Random();
        return rand.nextBoolean();
    }

    @Override
    public String getSymbol() {
        return "A";
    }

    @Override
    public Species getSpecies() {
        return Species.ANTELOPE;
    }
}