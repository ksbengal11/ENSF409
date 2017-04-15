package cloning;

public class Shape implements Cloneable{
	protected Point origin;
	
	public Shape(){
		origin = new Point();
	}
	
	public Shape(double x, double y){
		origin = new Point(x,y);
	}
	
	public Point getPoint(){
		return origin;
	}
	
	public void setPoint(double x, double y){
		this.origin.setX(x);
		this.origin.setY(y);
	}

	public Object clone() throws CloneNotSupportedException {
		Shape obj = (Shape) super.clone();
		obj.origin = (Point) origin.clone();
		return obj;
	}
}
