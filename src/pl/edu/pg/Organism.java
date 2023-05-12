package pl.edu.pg;

import java.awt.*;
import java.util.Comparator;

class OrganismComparator implements Comparator<Organism> {
    @Override
    public int compare(Organism o1, Organism o2) {
        if (o1.getInitiative() > o2.getInitiative())
            return 1;
        else if (o1.getInitiative() == o2.getInitiative() && o1.getAge() > o2.getAge())
            return 1;
        else if (o1.getInitiative() < o2.getInitiative())
            return -1;
        return 0;
    }
}

public abstract class Organism {
    public enum Species {
        SHEEP,
        WOLF
    }

    private final World world;
    protected Point position;
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

    public World getWorld() {
        return world;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public Point getPosition() {
        return position;
    }

    public int getAge() {
        return age;
    }

    public void updateAge() {
        age++;
    }

    public void kill() {
        age = -1;
    }

    public int getInitiative() {
        return initiative;
    }

    public abstract void action();

    protected abstract void collision(Animal attacker);

    public abstract String getSymbol();

    public abstract Species getSpecies();
}
