package ex3;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ClientMangementSystemGUI implements ListSelectionListener{

	private JFrame frame;
	private JTextField textField;
	private String [] labelNames = {"Client Id:", "First Name:", "Last Name:", "Address:", "Postal Code", "Phone Number:", "Client Type:"};
	private JLabel [] clientInformation;
	private JTextField [] clientInformationDisplay;
	private String btnSelected;

	private DefaultListModel<String> listModel;
    private JList<String> listArea;
    private ArrayList<Client> clientList;
    
    private DatabaseConnector db;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientMangementSystemGUI window = new ClientMangementSystemGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientMangementSystemGUI() {
		initialize();
		db = new DatabaseConnector();
		db.clearTable();
		db.loadClientsFromFile("clients.txt");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		listModel = new DefaultListModel<String>();
	    listArea = new JList<String>(listModel);
	    clientList = new ArrayList<Client>();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblClientMangementSystem = new JLabel("Client Mangement System");
		lblClientMangementSystem.setBounds(312, 11, 160, 14);
		frame.getContentPane().add(lblClientMangementSystem);
		
		JRadioButton rdbtnclientId = new JRadioButton("Client Id");
		rdbtnclientId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnSelected = "clientId";
			}
		});
		rdbtnclientId.setBounds(6, 61, 109, 23);
		frame.getContentPane().add(rdbtnclientId);
		
		JRadioButton rdbtnLastName = new JRadioButton("Last Name");
		rdbtnLastName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnSelected = "lastname";
			}
		});
		rdbtnLastName.setBounds(6, 87, 109, 23);
		frame.getContentPane().add(rdbtnLastName);
		
		JRadioButton rdbtnClientType = new JRadioButton("Client Type");
		rdbtnClientType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSelected = "clientType";
			}
		});
		rdbtnClientType.setBounds(6, 113, 109, 23);
		frame.getContentPane().add(rdbtnClientType);
		
		ButtonGroup newGroup = new ButtonGroup();
		newGroup.add(rdbtnclientId);
		newGroup.add(rdbtnLastName);
		newGroup.add(rdbtnClientType);
		
		JLabel lblSelectTypeOf = new JLabel("Select type of search to be performed:");
		lblSelectTypeOf.setBounds(6, 40, 278, 14);
		frame.getContentPane().add(lblSelectTypeOf);
		
		listArea.setVisibleRowCount(8);
		listArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane listPanel = new JScrollPane(listArea);
		listPanel.setBounds(6, 199, 409, 301);
		frame.getContentPane().add(listPanel);
		
		JLabel lblEnterSearchParameter = new JLabel("Enter search parameter below:");
		lblEnterSearchParameter.setBounds(6, 143, 278, 14);
		frame.getContentPane().add(lblEnterSearchParameter);
		
		textField = new JTextField();
		textField.setBounds(6, 168, 109, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				listModel.removeAllElements();
				listArea.setModel(listModel);
				if(!invalidSearch(textField.getText(), btnSelected)){
					clientList = db.searchClient(textField.getText(), btnSelected);
					for(int i = 0; i < clientList.size(); i++){
						String [] result = clientList.get(i).toString().split(";");
						String displayLine = result[0] + "  " + result[1] + "  " + result[2] + "  " + result[6];
						listModel.addElement(displayLine);
					}
				}
				listArea.setModel(listModel);				
			}
		});
		btnNewButton.setBounds(125, 165, 78, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnClearSearch = new JButton("Clear Search");
		btnClearSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DefaultListModel<String> list = (DefaultListModel<String>) listArea.getModel();
				list.removeAllElements();
				clientList.clear();
			}
		});
		btnClearSearch.setBounds(213, 165, 109, 23);
		frame.getContentPane().add(btnClearSearch);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Update the list display if the database class returns no errors
				if(!invalidEntry()){
					db.updateClient(Integer.parseInt(clientInformationDisplay[0].getText()), 
							clientInformationDisplay[1].getText(), 
							clientInformationDisplay[2].getText(), 
							clientInformationDisplay[3].getText(), 
							clientInformationDisplay[4].getText(), 
							clientInformationDisplay[5].getText(), 
							clientInformationDisplay[6].getText().charAt(0));
				}
			}
		});
		btnUpdate.setBounds(425, 437, 89, 23);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Display a message saying client has been removed and clear textfields
				db.deleteClient(Integer.parseInt(clientInformationDisplay[0].getText()));
			}
		});
		btnDelete.setBounds(553, 437, 89, 23);
		frame.getContentPane().add(btnDelete);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < clientInformationDisplay.length; i++){
					clientInformationDisplay[i].setText("");
				}
			}
		});
		btnClear.setBounds(685, 437, 89, 23);
		frame.getContentPane().add(btnClear);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!invalidEntry()){
					db.addClient(0, 
							clientInformationDisplay[1].getText(), 
							clientInformationDisplay[2].getText(), 
							clientInformationDisplay[3].getText(), 
							clientInformationDisplay[4].getText(), 
							clientInformationDisplay[5].getText(), 
							clientInformationDisplay[6].getText().charAt(0));
				}
			}
		});
		btnAdd.setBounds(425, 477, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		JButton btnView = new JButton("View");
		btnView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = listArea.getSelectedIndex();
				System.out.println(index);
				clientInformationDisplay[0].setText(String.valueOf(clientList.get(index).getClientId()));
				clientInformationDisplay[1].setText(clientList.get(index).getFirstName());
				clientInformationDisplay[2].setText(clientList.get(index).getLastName());
				clientInformationDisplay[3].setText(clientList.get(index).getAddress());
				clientInformationDisplay[4].setText(clientList.get(index).getPostalCode());
				clientInformationDisplay[5].setText(clientList.get(index).getPhoneNumber());
				clientInformationDisplay[6].setText(String.valueOf(clientList.get(index).getClientType()));
			}
		});
		btnView.setBounds(332, 165, 83, 23);
		frame.getContentPane().add(btnView);

		clientInformation = new JLabel [7];
		clientInformationDisplay = new JTextField [7];
		
		int startY = 65;
		for (int i = 0; i < labelNames.length; i++){
			clientInformation[i] = new JLabel(labelNames[i]);
			clientInformation[i].setBounds(469, startY, 93, 14);
			frame.getContentPane().add(clientInformation[i]);
			startY += 50;
		}
		
		startY = 62;
		for(int i = 0; i < labelNames.length; i++){
			clientInformationDisplay[i] = new JTextField();
			clientInformationDisplay[i].setBounds(574, startY, 200, 20);
			clientInformationDisplay[i].setColumns(10);
			frame.getContentPane().add(clientInformationDisplay[i]);
			startY += 50;
		}
		clientInformationDisplay[0].setEditable(false);
	}
	
	public boolean invalidSearch(String searchParam, String searchType){
		switch(searchType){
		case "lastname":
			if(searchParam.length() > 20){
				JOptionPane.showMessageDialog(null, "Invalid last name length. Max 20 characters","Invalid last Name", JOptionPane.ERROR_MESSAGE);
				return true;
			}else if(searchParam.isEmpty()){
				JOptionPane.showMessageDialog(null, "Invalid last name length. Cannot be empty","Invalid last Name", JOptionPane.ERROR_MESSAGE);
				return true;
			}
			break;
		case "clientId":
			String regexId = "^(?:[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]|[1-9])$";
			if(!searchParam.matches(regexId)) {
				JOptionPane.showMessageDialog(null, "Class id must be between 1 - 9999","Invalid client id", JOptionPane.ERROR_MESSAGE);
				return true;
			}
			break;
		case "clientType":
			if(searchParam.equals("C")) return false;
			else if(searchParam.equals("R")) return false;
			else{
				JOptionPane.showMessageDialog(null, "Invalid client type. Must be either C or R","Invalid Client Type", JOptionPane.ERROR_MESSAGE);
				return true;	
			}
		}
		return false;
	}
	
	public boolean invalidEntry(){
		// Check lengths of first name, last name, and address
		if(clientInformationDisplay[1].getText().length() > 20){
			JOptionPane.showMessageDialog(null, "Invalid first name length. Max 20 characters","Invalid First Name", JOptionPane.ERROR_MESSAGE);
			return true;
		}
		
		else if(clientInformationDisplay[2].getText().length() > 20){
			JOptionPane.showMessageDialog(null, "Invalid last name length. Max 20 characters","Invalid Last Name", JOptionPane.ERROR_MESSAGE);
			return true;			
		}
		
		else if(clientInformationDisplay[3].getText().length() > 50){
			JOptionPane.showMessageDialog(null, "Invalid address length. Max 50 characters","Invalid Address", JOptionPane.ERROR_MESSAGE);
			return true;
		}
		
		// Check format of postal code
		String postalCode = clientInformationDisplay[4].getText().replaceAll("\\s+", "");
		if(postalCode.length() != 6) {
			JOptionPane.showMessageDialog(null, "Invalid postal code length","Invalid Postal Code", JOptionPane.ERROR_MESSAGE);
			return true;
		}
		
		postalCode = postalCode.toUpperCase();
		String regexPostalCode = "[ABCDEFGHIJKLMNOPQRSTUVWXYZ][0-9][ABCDEFGHIJKLMNOPQRSTUVWXYZ][0-9][ABCDEFGHIJKLMNOPQRSTUVWXYZ][0-9]";
		if(!postalCode.matches(regexPostalCode)) {
			JOptionPane.showMessageDialog(null, "Invalid postal code format","Invalid Postal Code", JOptionPane.ERROR_MESSAGE);
			return true;
		}
		
		// Check format of phone number
		String regexPhoneNumber = "^(1\\-)?[0-9]{3}\\-?[0-9]{3}\\-?[0-9]{4}$";
		if(!clientInformationDisplay[5].getText().matches(regexPhoneNumber)) {
			JOptionPane.showMessageDialog(null, "Invalid phone number format or length","Invalid Phone Number", JOptionPane.ERROR_MESSAGE);
			return true;
		}
		
		// Check client type
		if(clientInformationDisplay[6].getText().equals("C")) return false;
		else if(clientInformationDisplay[6].getText().equals("R")) return false;
		else{
			JOptionPane.showMessageDialog(null, "Invalid client type. Must be either C or R","Invalid Client Type", JOptionPane.ERROR_MESSAGE);
			return true;	
		}
	}
	
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
	}
}
