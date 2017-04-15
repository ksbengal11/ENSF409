package cloning;

public class Rectangle extends Shape implements Cloneable {
	public double width, length;
	Color c;
	
	public Rectangle(double x, double y, double w, double l){
		super(x,y);
		c = new Color();
		this.width = w;
		this.length = l;
	}
	
	public void setWidth(double w){
		this.width = w;
	}
	
	public void setLength(double l){
		this.length = l;
	}
	
	public double getArea(){
		return this.width*this.length;
	}
	
	public double getPerimeter(){
		return this.width*2 + this.length*2;
	}
	
	public Object clone() throws CloneNotSupportedException{
		Rectangle obj = (Rectangle) super.clone();
		obj.c = (Color) c.clone();
		return obj;
	}

}
