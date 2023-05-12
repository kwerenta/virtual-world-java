package pl.edu.pg;

import pl.edu.pg.animals.Sheep;
import pl.edu.pg.animals.Wolf;

import java.awt.*;

public class OrganismsFactory {
    public static Organism getOrganism(Organism.Species species, World world, Point position) {
        return switch (species) {
            case SHEEP -> new Sheep(world, position, 0);
            case WOLF -> new Wolf(world, position, 0);
        };
    }
}