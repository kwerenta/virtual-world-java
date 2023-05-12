package pl.edu.pg;

import java.awt.*;
import java.util.List;
import java.util.Random;

public abstract class Animal extends Organism {
    private boolean shouldSkipTurn = false;

    public Animal(World world, Point position, int age, int strength, int initiative) {
        super(world, position, age, strength, initiative);
    }

    @Override
    public void action() {
        Random rand = new Random();
        List<Point> adjacentPositions = getWorld().getAdjacentPositions(getPosition());

        Point newPosition = adjacentPositions.get(rand.nextInt(adjacentPositions.size()));

        Organism target = getWorld().getMap(newPosition);
        if (target == null)
            move(newPosition);
        else
            target.collision(this);
    }

    public void move(Point newPosition) {
        getWorld().setMap(newPosition, this);
        getWorld().setMap(position, null);
        position.setLocation(newPosition);
    }

    @Override
    protected void collision(Animal attacker) {
        if (getSpecies() == attacker.getSpecies()) {
            breed();
        } else {
            System.out.println("FIGHT");
        }
    }

    private void breed() {
        if (getAge() == 0 || shouldSkipTurn) return;

        Point newPosition = getWorld().getFreePosition(getPosition());
        if (newPosition != getPosition())
            getWorld().spawn(OrganismsFactory.getOrganism(getSpecies(), getWorld(), newPosition));

        shouldSkipTurn = true;
    }

    public boolean getShouldSkipTurn() {
        return shouldSkipTurn;
    }

    public void skipTurn() {
        shouldSkipTurn = false;
    }
}
