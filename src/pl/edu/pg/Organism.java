package pl.edu.pg;

import java.awt.Point;

public abstract class Organism {
    private final World world;
    protected Point position;
    private int age;
    private int strength;
    private final int initiative;

    public Organism(World world, Point position, int strength, int initiative, int age){
        this.world = world;
        this.position = position;
        this.age = age;
        this.strength = strength;
        this.initiative = initiative;
    }
}
