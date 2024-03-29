package pl.edu.pg.plants;

import pl.edu.pg.Plant;
import pl.edu.pg.Point;
import pl.edu.pg.World;

import java.awt.*;

public class Dandelion extends Plant {
    public Dandelion(World world, Point position, int age) {
        super(world, position, age, 0);
    }

    @Override
    public void action() {
        for (int i = 0; i < 3; i++)
            super.action();
    }

    @Override
    public String getSymbol() {
        return "D";
    }

    @Override
    public Color getColor() {
        return new Color(0xfde047);
    }

    @Override
    public Species getSpecies() {
        return Species.DANDELION;
    }
}