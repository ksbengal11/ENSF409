
class Text implements Cloneable, Resizeable {
	private final Double DEFAULT_SIZE = 10.0;

    private Colour colour;
    private Double fontSize;

    private String text;

	public Text(String text) {
       this.text = text;
       fontSize = DEFAULT_SIZE;
	}
	public Object clone() throws CloneNotSupportedException {
		Text obj = (Text)super.clone();
		if(colour != null) obj.colour = colour;
		return obj;
	}

	public Double getFontSize(){
		return fontSize;
	}

	public void setColour(String s){
		colour = new Colour(s);
	}

	public void setText(String newText){
		text = newText;
	}

	public String getText(){
		return text ;
	}

	@Override
	public String toString(){
		return (text);
	}

	public void shrink (double divisor) throws SizeFactorException {
		if(divisor < LIMIT) throw new SizeFactorException();
		fontSize = fontSize/divisor;
	}
	public void enlarge (double multiplier) throws SizeFactorException{
		if(multiplier < LIMIT) throw new SizeFactorException();
		fontSize = fontSize * multiplier;
	}


}
