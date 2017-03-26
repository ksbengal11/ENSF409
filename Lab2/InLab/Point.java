
class Point {
	private int x, y;

	public Point(int x, int y) {
	this.x = x;
	this.y = y;
	}

	static public double distance(Point a, Point b){
		double diffx = a.x - b.x;
		double diffy = a.y - b.y;
		return Math.sqrt(diffx * diffx + diffy * diffy);
	}

	public String toString(){
    /*THIS METHOD DOESN'T WORK. AS PART OF EXERCISE-B STUDENTS MUST FIX IT
     	TO RETURN A STRING WITH THE COORDINATES OF A POINT IN THE FORMAT SHOWN
      IN THE EXAMPLE, BELOW:(20, 30)
		*/
		String s = "(" + x + ", " + y +")";
    //String s = "Opse... Point's toString doesn't know how to display point coordinates.";
		return  s;
	}
}
