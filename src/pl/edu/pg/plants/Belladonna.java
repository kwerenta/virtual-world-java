package pl.edu.pg.plants;

import pl.edu.pg.Animal;
import pl.edu.pg.Plant;
import pl.edu.pg.Point;
import pl.edu.pg.World;

// PL: Wilcze jagody
public class Belladonna extends Plant {

    public Belladonna(World world, Point position, int age) {
        super(world, position, age, 99);
    }

    @Override
    public void collision(Animal attacker) {
        super.collision(attacker);
        getWorld().despawn(attacker);
        getWorld().addLog(getSymbol() + " killed " + attacker.getSymbol());
    }

    @Override
    public String getSymbol() {
        return "B";
    }

    @Override
    public Species getSpecies() {
        return Species.BELLADONNA;
    }
}