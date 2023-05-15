package pl.edu.pg;

public class Point {
    private int x;
    private int y;

    public Point(Point point) {
        this.x = point.x;
        this.y = point.y;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void update(Point value) {
        x = value.x;
        y = value.y;
    }

    public void translate(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public Point getTranslated(Point vector) {
        return new Point(x + vector.x, y + vector.y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}