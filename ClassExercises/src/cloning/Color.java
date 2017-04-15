package cloning;

public class Color implements Cloneable{
	String color;

	public Color(){
		
	}
	
	public void setColor(String c){
		this.color = c;
	}
	
	public String getColor() {
		return this.color;
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
