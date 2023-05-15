package pl.edu.pg.plants;

import pl.edu.pg.Animal;
import pl.edu.pg.Plant;
import pl.edu.pg.Point;
import pl.edu.pg.World;

public class Guarana extends Plant {

    public Guarana(World world, Point position, int age) {
        super(world, position, age, 0);
    }

    @Override
    public void collision(Animal attakcer) {
        super.collision(attakcer);
        attakcer.setStrength(attakcer.getStrength() + 3);
        getWorld().addLog("Strength of " + attakcer.getSymbol() + " has increased to " + attakcer.getStrength());
    }

    @Override
    public String getSymbol() {
        return "U";
    }

    @Override
    public Species getSpecies() {
        return Species.GUARANA;
    }
}