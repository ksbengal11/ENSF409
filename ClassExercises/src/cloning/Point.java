package cloning;

public class Point implements Cloneable{
	
	double x, y;
	
	public Point (){
		this.x = this.y = 0;
	}
	
	public Point (double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
	
	public static void main(String[] args) {
		Point a, b;
		a = new Point (2.0, -5.0);
		try{
			b = (Point) a.clone();	
		}catch(CloneNotSupportedException e){
			System.out.println("Could not clone Point a");
		}
		a.setX(5.0);
	}

}
