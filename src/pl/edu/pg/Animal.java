package pl.edu.pg;

public abstract class Animal extends Organism {
    protected boolean shouldSkipTurn = false;

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
        position.update(newPosition);
        getWorld().addLog(getName() + " moved to " + position);
    }

    @Override
    public void collision(Animal attacker) {
        if (getSpecies() == attacker.getSpecies()) {
            breed();
            return;
        }

        if (hasRepelled(attacker)) {
            getWorld().addLog(getName() + " repelled attack of " + attacker.getName() + " " + position);
            return;
        }

        if (hasRunAway()) {
            shouldSkipTurn = true;
            attacker.move(position);
            getWorld().addLog(getName() + " avoided fight with " + attacker.getName() + " " + position);
            move(getFreePosition());
            getWorld().setMap(position, this);
            return;
        }

        if (attacker.getStrength() >= getStrength()) {
            getWorld().despawn(this);
            attacker.move(position);
            getWorld().addLog(attacker.getName() + " attacked and won with " + getName() + " " + position);
        } else {
            getWorld().despawn(attacker);
            getWorld().addLog(attacker.getName() + " attacked and lost with " + getName() + " " + attacker.position);
        }
    }

    private void breed() {
        if (getAge() == 0 || shouldSkipTurn) return;
        shouldSkipTurn = true;

        Point newPosition = getFreePosition();
        if (newPosition == getPosition()) return;

        getWorld().spawn(OrganismsFactory.getOrganism(getSpecies(), getWorld(), newPosition));
        getWorld().addLog("New " + getName() + " has born " + newPosition);

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
