package pl.edu.pg.plants;

import pl.edu.pg.*;

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
                getWorld().addLog(getSymbol() + " killed nearby " + target.getSymbol() + " " + target.getPosition());
            }
        }

        super.action();
    }

    @Override
    public void collision(Animal attacker) {
        super.collision(attacker);
        getWorld().despawn(attacker);
        getWorld().addLog(getSymbol() + " killed " + attacker.getSymbol());
    }

    @Override
    public String getSymbol() {
        return "O";
    }

    @Override
    public Species getSpecies() {
        return Species.HOGWEED;
    }
}