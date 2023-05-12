package pl.edu.pg.plants;

import pl.edu.pg.Plant;
import pl.edu.pg.World;

import java.awt.*;

public class Grass extends Plant {
    public Grass(World world, Point position, int age) {
        super(world, position, age, 0);
    }

    @Override
    public String getSymbol() {
        return "G";
    }

    @Override
    public Species getSpecies() {
        return Species.GRASS;
    }
}