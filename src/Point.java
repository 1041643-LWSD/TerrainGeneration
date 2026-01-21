public class Point {
    private double x;
    private double y;
    private double z;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean inBox(double xMin, double xMax, double yMin, double yMax, double zMin, double zMax) {
        return (x >= xMin && x <= xMax) &&
               (y >= yMin && y <= yMax) &&
               (z >= zMin && z <= zMax);
    }
}