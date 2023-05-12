package pl.edu.pg.animals;

import pl.edu.pg.Animal;
import pl.edu.pg.Organism;
import pl.edu.pg.World;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class Fox extends Animal {

    public Fox(World world, Point position, int age) {
        super(world, position, age, 3, 7);
    }

    @Override
    public void action() {
        List<Point> positions = getWorld().getAdjacentPositions(position);
        Random rand = new Random();
        Point newPosition = positions.get(rand.nextInt(positions.size()));
        Organism target = getWorld().getMap(newPosition);
        if (target != null && getSpecies() != target.getSpecies() && target.getStrength() > getStrength())
            System.out.println(getSymbol() + " sensed that there was a stronger organism on its way");
        else
            super.action();

    }

    @Override
    public String getSymbol() {
        return "F";
    }

    @Override
    public Species getSpecies() {
        return Species.FOX;
    }
}