package pl.edu.pg.animals;

import pl.edu.pg.Animal;
import pl.edu.pg.Point;
import pl.edu.pg.World;


public class Wolf extends Animal {
    public Wolf(World world, Point position, int age) {
        super(world, position, age, 9, 5);
    }

    @Override
    public String getSymbol() {
        return "W";
    }

    @Override
    public Species getSpecies() {
        return Species.WOLF;
    }
}