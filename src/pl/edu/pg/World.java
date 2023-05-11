package pl.edu.pg;

import pl.edu.pg.animals.Sheep;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public Organism getMap(Point pos) {
        return map[pos.y][pos.x];
    }

    public Organism getMap(int x, int y) {
        return map[y][x];
    }

    public void setMap(Point pos, Organism organism) {
        map[pos.y][pos.x] = organism;
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

    public boolean isValidPosition(Point position) {
        return position.x >= 0 && position.x < width && position.y >= 0 && position.y < height;
    }

    public List<Point> getAdjacentPositions(Point position) {
        List<Point> positions = new ArrayList<>(
                Arrays.asList(new Point(1, 0), new Point(-1, 0), new Point(0, 1), new Point(0, -1))
        );
        return positions.stream()
                .map(vector -> new Point(position.x + vector.x, position.y + vector.y))
                .filter((this::isValidPosition))
                .toList();
    }

    private void populate() {
        spawn(new Sheep(this, new Point(1, 0), 3));
        spawn(new Sheep(this, new Point(2, 3), 1));
    }
}
