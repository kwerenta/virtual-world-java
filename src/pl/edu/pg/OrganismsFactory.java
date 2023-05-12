package pl.edu.pg;

import pl.edu.pg.animals.Sheep;
import pl.edu.pg.animals.Wolf;

import java.awt.*;

public class OrganismsFactory {
    public static Organism getOrganism(Organism.Species species, World world, Point position) {
        return getOrganism(species, world, position, 0);
    }

    public static Organism getOrganism(Organism.Species species, World world, Point position, int age) {
        return switch (species) {
            case SHEEP -> new Sheep(world, position, age);
            case WOLF -> new Wolf(world, position, age);
        };
    }
}