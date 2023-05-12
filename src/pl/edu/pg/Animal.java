package pl.edu.pg;

import java.awt.*;


public abstract class Animal extends Organism {
    private boolean shouldSkipTurn = false;

    public Animal(World world, Point position, int age, int strength, int initiative) {
        super(world, position, age, strength, initiative);
    }

    @Override
    public void action() {
        Point newPosition = getAdjacentPosition();
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
        getWorld().addLog(getSymbol() + " moved to " + position);
    }

    @Override
    protected void collision(Animal attacker) {
        if (getSpecies() == attacker.getSpecies()) {
            breed();
            return;
        }

        if (hasRepelled(attacker)) {
            getWorld().addLog(getSymbol() + " repelled attack of " + attacker.getSymbol());
            return;
        }

        if (hasRunAway()) {
            shouldSkipTurn = true;
            attacker.move(position);
            getWorld().addLog(getSymbol() + " avoided fight with " + attacker.getSymbol());
            move(getFreePosition());
            getWorld().setMap(position, this);
            return;
        }

        if (attacker.getStrength() >= getStrength()) {
            getWorld().despawn(this);
            attacker.move(position);
            getWorld().addLog(attacker.getSymbol() + " attacked and won with " + getSymbol());
        } else {
            getWorld().despawn(attacker);
            getWorld().addLog(attacker.getSymbol() + " attacked and lost with " + getSymbol());
        }
    }

    private void breed() {
        if (getAge() == 0 || shouldSkipTurn) return;
        shouldSkipTurn = true;

        Point newPosition = getWorld().getFreePosition(getPosition());
        if (newPosition == getPosition()) return;

        getWorld().spawn(OrganismsFactory.getOrganism(getSpecies(), getWorld(), newPosition));
        getWorld().addLog("New " + getSymbol() + " has born " + newPosition);

    }

    protected boolean hasRepelled(Animal attacker) {
        return false;
    }

    protected boolean hasRunAway() {
        return false;
    }

    public boolean getShouldSkipTurn() {
        return shouldSkipTurn;
    }

    public void skipTurn() {
        shouldSkipTurn = false;
    }
}
