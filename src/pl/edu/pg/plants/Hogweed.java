package pl.edu.pg.plants;

import pl.edu.pg.*;
import pl.edu.pg.Point;

import java.awt.*;
import java.util.List;

// PL: Barszcz Sosnowskiego
public class Hogweed extends Plant {

    public Hogweed(World world, Point position, int age) {
        super(world, position, age, 10);
    }

    @Override
    public void action() {
        List<Point> positions = getAdjacentPositions();

        for (Point position : positions) {
            Organism target = getWorld().getMap(position);
            if (target instanceof Animal) {
                getWorld().despawn(target);
                getWorld().addLog(getName() + " killed nearby " + target.getName() + " " + target.getPosition());
            }
        }

        super.action();
    }

    @Override
    public void collision(Animal attacker) {
        super.collision(attacker);
        getWorld().despawn(attacker);
        getWorld().addLog(getName() + " killed " + attacker.getName());
    }

    @Override
    public String getSymbol() {
        return "O";
    }

    @Override
    public Color getColor() {
        return new Color(0xdcfce7);
    }

    @Override
    public Species getSpecies() {
        return Species.HOGWEED;
    }
}