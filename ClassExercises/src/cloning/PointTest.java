package cloning;

import static org.junit.Assert.*;

import org.junit.Test;

public class PointTest {

	@Test
	public void testPoint() {
		Point a, b = null;
		a = new Point (2.0, -5.0);
		try{
			b = (Point) a.clone();	
		}catch(CloneNotSupportedException e){
			System.out.println("Could not clone Point a");
		}
		assertEquals(a.getX(),b.getX(), 0);
		assertEquals(a.getY(),b.getY(), 0);
		a.setX(5.0);
		assertNotSame(a.getX(),b.getX());
	}
	
	@Test
	public void testShapeClone(){
		Shape a, b = null;
		a = new Shape();
		try{
			b = (Shape) a.clone();
		}catch(CloneNotSupportedException e){
			System.out.println("Could not clone Shape a");
		}
		assertEquals(a.getPoint().x, b.getPoint().x, 0);
		assertEquals(a.getPoint().y, b.getPoint().y, 0);
		
		a.setPoint(10.0, 10.0);
		
		assertNotSame(a.getPoint().x, b.getPoint().x);
		assertNotSame(a.getPoint().y, b.getPoint().y);
	}
	
	@Test
	public void testRectangleClone(){
		Rectangle a, b = null;
		a = new Rectangle(0.0, 0.0, 5.0, 5.0);
		try{
			b = (Rectangle) a.clone();
		}catch(CloneNotSupportedException e){
			System.out.println("Could not clone Rectangle a");
		}
		
		assertEquals(a.getArea(), b.getArea(), 0);
		assertEquals(a.getPerimeter(), b.getPerimeter(), 0);
		
		assertEquals(a.getPoint().getX(), b.getPoint().getX(), 0);
		
		a.setLength(10);
		assertEquals(a.getArea(), 50.0, 0);
		assertNotSame(a.getArea(), b.getArea());
		
		a.setPoint(5.0, 5.0);
		assertEquals(a.getPoint().getX(), 5.0, 0);
		assertEquals(a.getPoint().getY(), 5.0, 0);
		assertNotSame(a.getPoint().getX(), b.getPoint().getY());
	}

}
