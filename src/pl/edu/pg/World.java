package pl.edu.pg;

import java.util.*;

public class World {
    private final int width, height;
    private final Organism[][] map;
    private final PriorityQueue<Organism> actionOrder = new PriorityQueue<>(new OrganismComparator());

    private final Stack<String> logs = new Stack<>();

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        map = new Organism[height][width];
        populate();
    }

    public Organism getMap(Point pos) {
        return map[pos.getY()][pos.getX()];
    }

    public Organism getMap(int x, int y) {
        return map[y][x];
    }

    public void setMap(Point pos, Organism organism) {
        map[pos.getY()][pos.getX()] = organism;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void spawn(Organism organism) {
        setMap(organism.position, organism);
        actionOrder.add(organism);
    }

    public void despawn(Organism organism) {
        setMap(organism.position, null);
        organism.kill();
        actionOrder.remove(organism);
    }

    public void makeTurn() {
        PriorityQueue<Organism> currentOrder = new PriorityQueue<>(actionOrder);
        while (!currentOrder.isEmpty()) {
            Organism organism = currentOrder.poll();
            if (organism instanceof Animal animal && animal.getShouldSkipTurn()) {
                animal.skipTurn();
                animal.updateAge();
                continue;
            }

            if (organism.getAge() > 0)
                organism.action();

            organism.updateAge();
        }
    }

    public void addLog(String message) {
        logs.push(message);
    }

    public String popLog() {
        return logs.pop();
    }

    public boolean areLogsEmpty() {
        return logs.empty();
    }

    public boolean isValidPosition(Point position) {
        return position.getX() >= 0 && position.getX() < width && position.getY() >= 0 && position.getY() < height;
    }

    public Point getFreePosition(Point position, int range) {
        List<Point> positions = getAdjacentPositions(position, range, true);
        if (positions.isEmpty()) return position;
        Random rand = new Random();
        return positions.get(rand.nextInt(positions.size()));
    }

    public Point getAdjacentPosition(Point position, int range) {
        List<Point> positions = getAdjacentPositions(position, range);
        Random rand = new Random();
        return positions.get(rand.nextInt(positions.size()));
    }

    public List<Point> getAdjacentPositions(Point position, int range) {
        return getAdjacentPositions(position, range, false);
    }

    private List<Point> getAdjacentPositions(Point position, int range, boolean shouldBeFree) {
        List<Point> vectors = new ArrayList<>(
                Arrays.asList(new Point(range, 0), new Point(-1 * range, 0), new Point(0, range), new Point(0, -1 * range))
        );
        return vectors.stream()
                .map(vector -> vector.getTranslated(position))
                .filter(this::isValidPosition)
                .filter(pos -> !shouldBeFree || getMap(pos) == null)
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
