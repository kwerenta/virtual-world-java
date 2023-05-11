package pl.edu.pg;

import pl.edu.pg.animals.Sheep;

import java.awt.Point;
import java.util.PriorityQueue;

public class World {
    private final int width, height;
    private final Organism[][] map;

    private PriorityQueue<Organism> actionOrder = new PriorityQueue<>(new OrganismComparator());

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

    public void spawn(Organism organism) {
        map[organism.getPosition().y][organism.getPosition().x] = organism;
        actionOrder.add(organism);
    }

    private void populate() {
        spawn(new Sheep(this, new Point(1, 0), 1));
        spawn(new Sheep(this, new Point(2, 3), 1));
    }
}
