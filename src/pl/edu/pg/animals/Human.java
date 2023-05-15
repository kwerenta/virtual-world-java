package pl.edu.pg.animals;

import pl.edu.pg.Animal;
import pl.edu.pg.Organism;
import pl.edu.pg.Point;
import pl.edu.pg.World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Human extends Animal {
    private static final int ABILITY_COOLDOWN = 5;
    private static final int ABILITY_LENGTH = 5;
    private static final int ABILITY_RANGE = 2;
    private Point queuedPosition;
    private int abilityTimer = 1 - ABILITY_COOLDOWN;

    public Human(World world, Point position, int age) {
        super(world, position, age, 5, 4);
    }

    public void handleUserInput(int keyCode) {
        Random rand = new Random();
        Point newPosition = new Point(position);
        int range = 1;
        if (abilityTimer >= 3 || (abilityTimer > 0 && rand.nextBoolean())) {
            range = ABILITY_RANGE;
            getWorld().addLog("Your ability worked");
        }

        switch (keyCode) {
            case KeyEvent.VK_UP -> newPosition.translate(0, -1 * range);
            case KeyEvent.VK_RIGHT -> newPosition.translate(range, 0);
            case KeyEvent.VK_DOWN -> newPosition.translate(0, range);
            case KeyEvent.VK_LEFT -> newPosition.translate(-1 * range, 0);
            case KeyEvent.VK_F -> ability();
            default -> {
                return;
            }
        }
        if (getWorld().isValidPosition(newPosition))
            queuedPosition = newPosition;
    }

    @Override
    public void action() {
        if (abilityTimer > 1 - ABILITY_COOLDOWN)
            abilityTimer--;

        if (queuedPosition == null) return;

        Organism target = getWorld().getMap(queuedPosition);
        if (target == null)
            move(queuedPosition);
        else
            target.collision(this);

        queuedPosition = null;
    }

    @Override
    public String getSymbol() {
        return "H";
    }

    @Override
    public Color getColor() {
        return new Color(0xfdba74);
    }

    @Override
    public Species getSpecies() {
        return Species.HUMAN;
    }

    @Override
    public String toString() {
        return super.toString() + " " + abilityTimer;
    }

    public void setAbilityTimer(int abilityTimer) {
        this.abilityTimer = abilityTimer;
    }

    private void ability() {
        if (abilityTimer > 0)
            getWorld().addLog("Your ability is still active for " + abilityTimer + " turn(s)");
        else if (abilityTimer > 1 - ABILITY_COOLDOWN)
            getWorld().addLog("Your ability will be available in " + (ABILITY_COOLDOWN + abilityTimer - 1) + " turn(s)");
        else {
            getWorld().addLog("Your ability has been enabled");
            abilityTimer = ABILITY_LENGTH;
        }
    }
}