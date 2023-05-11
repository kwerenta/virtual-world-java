package pl.edu.pg.animals;

import pl.edu.pg.Animal;
import pl.edu.pg.World;

import java.awt.*;

public class Sheep extends Animal {
    public Sheep(World world, Point position, int age) {
        super(world, position, age, 4, 4);
    }

    @Override
    public String getSymbol() {
        return "S";
    }
}
