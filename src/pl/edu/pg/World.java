package pl.edu.pg;

public class World {
    private Organism[][] map;
    public World(int width, int height) {
        map = new Organism[width][height];
    }

    public Organism getMap(int x, int y) {
        return map[y][x];
    }
}
