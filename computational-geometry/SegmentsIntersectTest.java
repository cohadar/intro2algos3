import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class SegmentsIntersectTest {

	static class Point2D {
		final int x;
		final int y;
		Point2D(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public Point2D subtract(Point2D that) {
			return new Point2D(this.x - that.x, this.y - that.y);
		}
		public String toString() {
			return String.format("(x=%d, y=%d)", x, y);
		}	
	}

	public static int crossProduct(Point2D p1, Point2D p2) {
		return p1.x * p2.y - p1.y * p2.x;
	}		

	public int direction(Point2D r, Point2D a, Point2D b) {
		return crossProduct(a.subtract(r), b.subtract(r));
	}

	public boolean onSegment(Point2D r, Point2D a, Point2D b) {
		return Math.min(a.x, b.x) <= r.x && r.x <= Math.max(a.x, b.x) 
		    && Math.min(a.y, b.y) <= r.y && r.y <= Math.max(a.y, b.y);
	}	

	public boolean segmentsIntersect(Point2D p1, Point2D p2, Point2D p3, Point2D p4) {
		int d1 = direction(p1, p3, p4);
		int d2 = direction(p2, p3, p4);
		int d3 = direction(p3, p1, p2);
		int d4 = direction(p4, p1, p2);
		if (d1 < 0 && d2 > 0 || d1 > 0 && d2 < 0) {
			return true;
		}
		if (d3 < 0 && d4 > 0 || d3 > 0 && d4 < 0) {
			return true;
		}
		if (d1 == 0 && onSegment(p1, p3, p4)) {
			return true;
		}
		if (d2 == 0 && onSegment(p2, p3, p4)) {
			return true;
		}
		if (d3 == 0 && onSegment(p3, p1, p2)) {
			return true;
		}
		if (d4 == 0 && onSegment(p4, p1, p2)) {
			return true;
		}
		return false;
	}

	@Test
	public void testSegmentsIntersect() {
		// straddle
		assertTrue(segmentsIntersect(new Point2D(2, 1), new Point2D(3, 3), new Point2D(1, 4), new Point2D(4, 2)));
		// endpoint p1 on segment p3p4
		assertTrue(segmentsIntersect(new Point2D(1, 1), new Point2D(6, 7), new Point2D(2, 0), new Point2D(0, 2)));
		// endpoint p2 on segment p3p4
		assertTrue(segmentsIntersect(new Point2D(6, 7), new Point2D(1, 1), new Point2D(2, 0), new Point2D(0, 2)));		
		// endpoint p3 on segment p1p2
		assertTrue(segmentsIntersect(new Point2D(2, 0), new Point2D(0, 2), new Point2D(1, 1), new Point2D(6, 7)));		
		// endpoint p4 on segment p1p2
		assertTrue(segmentsIntersect(new Point2D(2, 0), new Point2D(0, 2), new Point2D(6, 7), new Point2D(1, 1)));				
		// no intersection
		assertFalse(segmentsIntersect(new Point2D(1, 4), new Point2D(3, 1), new Point2D(3, 3), new Point2D(5, 2)));
	}

}
