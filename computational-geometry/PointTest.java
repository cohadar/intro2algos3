import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class PointTest {

	@Test
	public void testDistance() {
		Point p1 = randomPoint();
		double dx = randomX();
		double dy = randomY();
		Point p2 = new Point(p1.x + dx, p1.y + dy);
		double distance = Math.sqrt(dx * dx + dy * dy);
		assertEquals(distance, p1.distance(p2), 1e-6);
		assertEquals(distance, p2.distance(p1), 1e-6);
	}

	@Test
	public void testAdd() {
		Point p1 = randomPoint();
		Point p2 = randomPoint();
		Point p3 = new Point(p1.x + p2.x, p1.y + p2.y);
		assertEquals(p3, p1.add(p2));
		assertEquals(p3, p2.add(p1));
	}

	@Test
	public void testSubtract() {
		Point p1 = randomPoint();
		Point p2 = randomPoint();
		Point p3 = new Point(p1.x - p2.x, p1.y - p2.y);
		Point p4 = new Point(p2.x - p1.x, p2.y - p1.y);
		assertEquals(p3, p1.subtract(p2));
		assertEquals(p4, p2.subtract(p1));
	}	

	@Test
	public void testMultiply() {
		Point p1 = randomPoint();
		double f = randomX();
		Point p2 = new Point(p1.x * f, p1.y * f);
		Point p3 = p1.multiply(f);
		assertEquals(p2, p3);
	}		

	@Test
	public void testMagnitude() {
		double dx = randomX();
		double dy = randomY();
		Point p1 = new Point(dx, dy);
		double distance = Math.sqrt(dx * dx + dy * dy);
		assertEquals(distance, p1.magnitude(), 1e-6);
	}	

	@Test 
	public void testNormalize() {
		Point p1 = randomPoint().normalize();
		double dx = p1.x;
		double dy = p1.y;
		assert -1.0 <= dx && dx <= 1.0 : "out of range, dx: " + dx;
		assert -1.0 <= dy && dy <= 1.0 : "out of range, dy: " + dy;
		double distance = Math.sqrt(dx * dx + dy * dy);
		assertEquals(distance, 1.0, 1e-6);
	}

	@Test
	public void testMidpoint() {
		Point p1 = randomPoint();
		Point p2 = randomPoint();	
		Point p3 = p1.midpoint(p2);
		assertEquals(p1.distance(p3), p2.distance(p3), 1e-6);
	}

	@Test
	public void testDotProduct() {
		Point p1 = randomPoint();
		Point p2 = randomPoint();	
		assertEquals(p1.dotProduct(p2), p1.x * p2.x + p1.y * p2.y, 1e-6);
	}

	@Test
	public void testCrossProduct() {
		Point p1 = randomPoint();
		Point p2 = randomPoint();	
		assertEquals(p1.crossProduct(p2), p1.x * p2.y - p1.y * p2.x, 1e-6);
	}	

	static Point randomPoint() {
		return new Point(randomX(), randomY());
	}

	static double randomX() {
		return Math.random() * 1000 - 500;
	}

	static double randomY() {
		return Math.random() * 1000 - 500;
	}

}

