package pl.edu.pg;

import pl.edu.pg.animals.*;
import pl.edu.pg.plants.Belladonna;
import pl.edu.pg.plants.Dandelion;
import pl.edu.pg.plants.Grass;
import pl.edu.pg.plants.Guarana;

public class OrganismsFactory {
    public static Organism getOrganism(Organism.Species species, World world, Point position) {
        return getOrganism(species, world, position, 0);
    }

    public static Organism getOrganism(Organism.Species species, World world, Point position, int age) {
        return switch (species) {
            case SHEEP -> new Sheep(world, position, age);
            case WOLF -> new Wolf(world, position, age);
            case FOX -> new Fox(world, position, age);
            case ANTELOPE -> new Antelope(world, position, age);
            case TURTLE -> new Turtle(world, position, age);
            case GRASS -> new Grass(world, position, age);
            case DANDELION -> new Dandelion(world, position, age);
            case BELLADONNA -> new Belladonna(world, position, age);
            case GUARANA -> new Guarana(world, position, age);
        };
    }
}