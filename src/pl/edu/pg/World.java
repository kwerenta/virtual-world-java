package pl.edu.pg;

import pl.edu.pg.animals.Human;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class World {
    public final static int MAX_WIDTH = 50;
    public final static int MAX_HEIGHT = 50;
    private final int width, height;
    private Human human;
    private final Organism[][] map;
    private final PriorityQueue<Organism> actionOrder = new PriorityQueue<>(new OrganismComparator());
    private final Stack<String> logs = new Stack<>();

    public World(int width, int height, Scanner scanner) {
        this.width = width;
        this.height = height;
        map = new Organism[height][width];

        if (scanner == null)
            populate();
        else
            load(scanner);
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
        if (organism instanceof Human) human = null;
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

    public Human getHuman() {
        return human;
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

    public void save() {
        try (FileWriter writer = new FileWriter("save.txt")) {
            writer.write(width + " " + height + '\n');
            for (Organism[] organismArr : map) {
                for (Organism organism : organismArr) {
                    if (organism == null) continue;
                    writer.write(organism.toString() + '\n');
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void load(Scanner scanner) {
        while (scanner.hasNext()) {
            Organism.Species species = Organism.Species.values()[scanner.nextInt()];
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int age = scanner.nextInt();
            int strength = scanner.nextInt();

            Organism organism = OrganismsFactory.getOrganism(species, this, new Point(x, y), age);
            spawn(organism);
            organism.setStrength(strength);
            if (organism instanceof Human h) {
                h.setAbilityTimer(scanner.nextInt());
                this.human = h;
            }
        }
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

        Human h = (Human) OrganismsFactory.getOrganism(Organism.Species.HUMAN, this, getRandomPosition(), rand.nextInt(1, 20));
        human = h;
        spawn(h);

        for (Organism.Species species : Organism.Species.values()) {
            if (species == Organism.Species.HUMAN) continue;

            for (int i = 0; i < 2; i++) {
                Point position = getRandomPosition();
                if (position == null)
                    return;

                spawn(OrganismsFactory.getOrganism(species, this, position, rand.nextInt(1, 20)));
            }
        }
    }
}
