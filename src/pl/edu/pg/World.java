package pl.edu.pg;

import pl.edu.pg.animals.Sheep;

import java.awt.Point;

public class World {
    private final Organism[][] map;

    public World(int width, int height) {
        map = new Organism[width][height];
        populate();
    }

    public Organism getMap(int x, int y) {
        return map[y][x];
    }

    private void populate() {
        map[0][1] = new Sheep(this, new Point(1,0),1);
    }
}
