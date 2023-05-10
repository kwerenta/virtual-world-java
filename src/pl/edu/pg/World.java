package pl.edu.pg;

import pl.edu.pg.animals.Sheep;

import java.awt.Point;

public class World {
    private int width, height;
    private final Organism[][] map;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        map = new Organism[width][height];
        populate();
    }

    public Organism getMap(int x, int y) {
        return map[y][x];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    private void populate() {
        map[0][1] = new Sheep(this, new Point(1, 0), 1);
    }
}
