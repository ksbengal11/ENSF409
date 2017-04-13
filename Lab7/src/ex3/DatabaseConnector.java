package ex3;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.DefaultListModel;

import com.mysql.jdbc.Statement;

public class DatabaseConnector {
	private final static String USER = "root";
	private final static String PASSWORD = "root";
	
	private Connection connect;
	private java.sql.Statement statement;
	private ResultSet resultSet;
	private ArrayList<Client> clientList;
	private DefaultListModel<String> searchResult;
	
	public DatabaseConnector(){
		try {
			createTable();
			clientList = new ArrayList<Client> ();
			searchResult = new DefaultListModel<String>();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getConnection() throws Exception{
		try{
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/clienttable?verifyServerCertificate=false&useSSL=true";
			Class.forName(driver);
			
			connect = DriverManager.getConnection(url, USER, PASSWORD);
			System.out.println("Connected");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private void createTable() throws Exception {
		try{
			getConnection();
		      String create_client_table = "CREATE TABLE IF NOT EXISTS clients ("
			      		+ "id INTEGER(4) NOT NULL AUTO_INCREMENT, "
			      		+ "firstname VARCHAR(20) NOT NULL, "
				    	+ "lastname VARCHAR(20) NOT NULL, "
				    	+ "address VARCHAR(50) NOT NULL, "
				    	+ "postalCode CHAR(7) NOT NULL, "
				    	+ "phoneNumber CHAR(12) NOT NULL, "
				    	+ "clientType CHAR(1) NOT NULL, "
				    	+ "PRIMARY KEY ( id ) "
				    	+ ")";
			PreparedStatement create = connect.prepareStatement(create_client_table);
			create.executeUpdate();
			System.out.println("Table Created");
		}catch (Exception e ){
			e.printStackTrace();
		}
	}
	public ArrayList<Client> searchClient(String searchParam, String searchType){
		try{
			PreparedStatement pStat = null;
			String query = null;
			switch(searchType){
			case "clientType":
				query = "SELECT * FROM clients where clientType = ?";
				pStat = connect.prepareStatement(query);
				pStat.setString(1, String.valueOf(searchParam.charAt(0)));
				break;
			case "lastname":
				query = "SELECT * FROM clients where lastname = ?";
				pStat = connect.prepareStatement(query);
				pStat.setString(1, searchParam);
				break;
			case "clientId":
				query = "SELECT * FROM clients where id = ?";
				pStat = connect.prepareStatement(query);
				pStat.setInt(1, Integer.parseInt(searchParam));
				break;
			}
			resultSet = pStat.executeQuery();
			Client client;
			while(resultSet.next()){
				client = new Client();
				client.setClientId(resultSet.getInt("id"));
				client.setFirstName(resultSet.getString("firstname"));
				client.setLastName(resultSet.getString("lastname"));
				client.setAddress(resultSet.getString("address"));
				client.setPostalCode(resultSet.getString("postalCode"));
				client.setPhoneNumber(resultSet.getString("phoneNumber"));
				client.setClientType(resultSet.getString("clientType").charAt(0));

				clientList.add(client);
				searchResult.addElement(client.toString());
			}
			pStat.close();
			
		}catch (SQLException e){
			System.out.println("Search error");
			e.printStackTrace();
		}

		return clientList;
	}
	
	public boolean addClient(int clientId, String firstName, String lastName, String address,
			String postalCode, String phoneNumber, char clientType){
		try {
			String query = 
					"INSERT INTO clients"
					+ "(firstname,lastname,address,postalCode,"
					+ "phoneNumber,clientType) "
					+ "values (?,?,?,?,?,?)";
			PreparedStatement pStat = connect.prepareStatement(query);

			pStat.setString(1, firstName);
			pStat.setString(2, lastName);
			pStat.setString(3, address);
			pStat.setString(4, postalCode);
			pStat.setString(5, phoneNumber);
			pStat.setString(6, String.valueOf(clientType));
			
			int rowCount = pStat.executeUpdate();
			pStat.close();
			
		    return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateClient(int clientId, String firstName, String lastName, String address,
			String postalCode, String phoneNumber, char clientType){
		// TODO Optimize this function. Too hacky.
		try {
			String query = 
					"UPDATE clienttable .clients"
					+ " SET firstname = ?"
					+ " WHERE id = ?";
			PreparedStatement pStat = connect.prepareStatement(query);
			
			pStat.setString(1, firstName);
			pStat.setString(2, String.valueOf(clientId));
			pStat.executeUpdate();

			query = 
					"UPDATE clienttable .clients"
					+ " SET lastname = ?"
					+ " WHERE id = ?";
			pStat = connect.prepareStatement(query);
			
			pStat.setString(1, lastName);
			pStat.setString(2, String.valueOf(clientId));
			pStat.executeUpdate();
			
			query = 
					"UPDATE clienttable .clients"
					+ " SET address = ?"
					+ " WHERE id = ?";
			pStat = connect.prepareStatement(query);
			
			pStat.setString(1, address);
			pStat.setString(2, String.valueOf(clientId));
			pStat.executeUpdate();
			
			query = 
					"UPDATE clienttable .clients"
					+ " SET postalCode = ?"
					+ " WHERE id = ?";
			pStat = connect.prepareStatement(query);
			
			pStat.setString(1, postalCode);
			pStat.setString(2, String.valueOf(clientId));
			pStat.executeUpdate();
			
			query = 
					"UPDATE clienttable .clients"
					+ " SET phoneNumber = ?"
					+ " WHERE id = ?";
			pStat = connect.prepareStatement(query);
			
			pStat.setString(1, phoneNumber);
			pStat.setString(2, String.valueOf(clientId));
			pStat.executeUpdate();
			
			query = 
					"UPDATE clienttable .clients"
					+ " SET clientType = ?"
					+ " WHERE id = ?";
			pStat = connect.prepareStatement(query);
			
			pStat.setString(1, String.valueOf(clientType));
			pStat.setString(2, String.valueOf(clientId));
			pStat.executeUpdate();
			
			pStat.close();
			
		    return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteClient(int clientId){
		try{
			String delete = "DELETE FROM clients WHERE id = ?";
			PreparedStatement pStat = connect.prepareStatement(delete);
			pStat.setInt(1, clientId);
			int rowCount = pStat.executeUpdate();
			pStat.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void loadClientsFromFile(String fileName){
		String record = null;
		int clientId = 1;
		try{
			Scanner in = new Scanner(new FileInputStream(fileName));
			while(in.hasNextLine()){
				record = in.nextLine();
				String [] line = record.split(";");
				if(!addClient(clientId, line[0], line[1], line[2], line[3], line[4], line[5].charAt(0))){
					System.out.println("Client could not be added. Quitting now");
					System.exit(1);
				}
			}
			in.close();
		}catch(IOException e){
			System.out.println("File could not be opened");
			e.printStackTrace();
		}
	}
	
	public void clearTable(){
		String sql = "TRUNCATE clients";
		try {
			statement = connect.createStatement();
			statement.executeUpdate(sql);
			sql = "DELETE FROM clients";
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Table Cleared");
	}
}