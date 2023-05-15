package pl.edu.pg.animals;

import pl.edu.pg.Animal;
import pl.edu.pg.Organism;
import pl.edu.pg.Point;
import pl.edu.pg.World;

import java.awt.*;

public class Fox extends Animal {

    public Fox(World world, Point position, int age) {
        super(world, position, age, 3, 7);
    }

    @Override
    public void action() {
        Point newPosition = getAdjacentPosition();
        Organism target = getWorld().getMap(newPosition);
        if (target != null) {
            if (getSpecies() != target.getSpecies() && target.getStrength() > getStrength())
                getWorld().addLog(getName() + " sensed that there was a stronger organism on its way " + position.toString());
            else
                target.collision(this);
        } else
            move(newPosition);

    }

    @Override
    public String getSymbol() {
        return "F";
    }

    @Override
    public Color getColor() {
        return new Color(0xc2410c);
    }

    @Override
    public Species getSpecies() {
        return Species.FOX;
    }
}