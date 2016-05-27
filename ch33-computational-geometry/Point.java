/**
  * @author Mighty Cohadar 
  */
public class Point {
	public final double x;
	public final double y;
	private int hash = 0;
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public double distance(Point p) {
		return Math.hypot(x - p.x, y - p.y);
	}
	public Point add(Point p) {
		return new Point(x + p.x, y + p.y);
	}
	public Point subtract(Point p) {
		return new Point(x - p.x, y - p.y);
	}
	public Point multiply(double factor) {
		return new Point(x * factor, y * factor);
	}
	public Point normalize() {
		final double mag = magnitude();
		if (mag == 0.0) {
			return new Point(0.0, 0.0);
		}
		return new Point(x / mag, y / mag);
	}
	public double magnitude() {
		return Math.hypot(x, y);
	}
	public Point midpoint(Point p) {
		return new Point((x + p.x) / 2, (y + p.y) / 2);
	}
	public double dotProduct(Point p) {
		return x * p.x + y * p.y;
	}
	public double crossProduct(Point p) {
		return x * p.y - y * p.x;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj instanceof Point) {
			Point other = (Point) obj;
			return x == other.x && y == other.y;
		} else return false;
	}
	@Override
	public int hashCode() {
		if (hash == 0) {
			long bits = 7L;
			bits = 31L * bits + Double.doubleToLongBits(x);
			bits = 31L * bits + Double.doubleToLongBits(y);
			hash = (int) (bits ^ (bits >> 32));
		}
		return hash;
	}
	public String toString() {
		return String.format("(x=%.17g, y=%.17g)", x, y);
	}
}
