package pl.edu.pg;

import pl.edu.pg.animals.Sheep;

import java.awt.Point;
import java.util.PriorityQueue;

public class World {
    private final int width, height;
    private final Organism[][] map;

    private final PriorityQueue<Organism> actionOrder = new PriorityQueue<>(new OrganismComparator());

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        map = new Organism[height][width];
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

    public void makeTurn() {
        PriorityQueue<Organism> currentOrder = new PriorityQueue<>(actionOrder);
        currentOrder.forEach(organism -> {
            if (organism.getAge() > 0)
                organism.action();
            organism.updateAge();
        });
    }

    private void populate() {
        spawn(new Sheep(this, new Point(1, 0), 3));
        spawn(new Sheep(this, new Point(2, 3), 1));
    }
}
