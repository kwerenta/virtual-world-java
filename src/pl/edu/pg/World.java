package pl.edu.pg;

import pl.edu.pg.animals.Sheep;
import pl.edu.pg.animals.Wolf;

import java.awt.Point;
import java.util.*;

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
            if (organism instanceof Animal animal && animal.getShouldSkipTurn()) {
                animal.skipTurn();
                animal.updateAge();
                return;
            }

            if (organism.getAge() > 0)
                organism.action();
            organism.updateAge();
        });
    }

    public boolean isValidPosition(Point position) {
        return position.x >= 0 && position.x < width && position.y >= 0 && position.y < height;
    }

    public Point getFreePosition(Point position) {
        List<Point> positions = getAdjacentPositions(position, true);
        if (positions.isEmpty()) return position;
        Random rand = new Random();
        return positions.get(rand.nextInt(positions.size()));
    }

    public List<Point> getAdjacentPositions(Point position) {
        return getAdjacentPositions(position, false);
    }

    public List<Point> getAdjacentPositions(Point position, boolean isFree) {
        List<Point> positions = new ArrayList<>(
                Arrays.asList(new Point(1, 0), new Point(-1, 0), new Point(0, 1), new Point(0, -1))
        );
        return positions.stream()
                .map(vector -> new Point(position.x + vector.x, position.y + vector.y))
                .filter((this::isValidPosition))
                .filter(pos -> !isFree || getMap(pos) == null)
                .toList();
    }

    private Point getRandomPosition() {
        if (actionOrder.size() == width * height) return null;
        Random rand = new Random();
        Point position;
        do {
            position = new Point(rand.nextInt(width), rand.nextInt(height));
        } while (getMap(position) != null);

        return position;
    }

    private void populate() {
        Random rand = new Random();
        for (Organism.Species species : Organism.Species.values()) {
            for (int i = 0; i < 2; i++) {
                Point position = getRandomPosition();
                if (position == null)
                    return;

                spawn(OrganismsFactory.getOrganism(species, this, position, rand.nextInt(1, 20)));
            }
        }
    }
}
