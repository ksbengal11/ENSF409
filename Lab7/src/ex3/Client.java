package ex3;

public class Client {
	private int clientId;
	private String firstName;
	private String lastName;
	private String address;
	private String postalCode;
	private String phoneNumber;
	private char clientType;
	
	public Client(){
		this.clientId = 0;
		this.firstName = lastName = address = postalCode = phoneNumber = null;
		this.clientType = '/';
	}
	
	public int getClientId(){
		return this.clientId;
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public String getAddress(){
		return this.address;
	}
	
	public String getPostalCode(){
		return this.postalCode;
	}
	
	public String getPhoneNumber(){
		return this.phoneNumber;
	}
	
	public char getClientType(){
		return this.clientType;
	}
	
	public void setClientId(int id){
		this.clientId = id;
	}
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public void setPostalCode(String postalCode){
		this.postalCode = postalCode;
	}
	
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	
	public void setClientType(char clientType){
		this.clientType = clientType;
	}
	
	public String toString(){
	return String.valueOf(this.getClientId()) + ";" 
				+ this.getFirstName() + ";"
				+ this.getLastName() + ";"
				+ this.getAddress() + ";"
				+ this.getPostalCode() + ";"
				+ this.getPhoneNumber() + ";"
				+ String.valueOf(this.getClientType());
	}
}
