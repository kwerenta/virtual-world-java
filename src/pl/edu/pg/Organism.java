package pl.edu.pg;

import java.awt.Point;

public abstract class Organism {
    private final World world;
    private Point position;
    private int age;
    private int strength;
    private final int initiative;

    public Organism(World world, Point position, int age, int strength, int initiative) {
        this.world = world;
        this.position = position;
        this.age = age;
        this.strength = strength;
        this.initiative = initiative;
    }

    public Point getPosition() {
        return position;
    }
    protected void move(Point position) {
        position.setLocation(position);
    }

    public abstract void draw();
}
