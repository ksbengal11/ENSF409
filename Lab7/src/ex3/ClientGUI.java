/**
 * 
 */
package ex3;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 * @author Karan Bengali
 *
 */
public class ClientGUI {
	JFrame frame;
	Box containerBox, infoBox;
	JTextArea textArea = new JTextArea();
	JPanel searchBtnPanel, displayPanel;
	String [] buttonNames = {"save", "delete", "clear", "New Search"};
	JButton[] buttons = new JButton[buttonNames.length];
	String [] labelNames = {"Client ID", "First Name", "Last Name", "Address", "Postal Code", "Phone Number", "Client Type", "Search Clients", "Client Information"};
	JLabel [] labels = new JLabel[labelNames.length];
	JTextField [] fields = new JTextField[labelNames.length];
	
	ClientGUI(){
		frame = new JFrame("Client Management Screen");
		
		containerBox 	= Box.createHorizontalBox();
		infoBox 		= Box.createVerticalBox();
		
		searchBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		for(int i = 0; i < buttonNames.length; i++){
			buttons[i] = new JButton(buttonNames[i]);
			if(i < buttonNames.length-1){
				searchBtnPanel.add(buttons[i]);
			}
		}
		
		for(int i = 0; i < labelNames.length; i++){
			labels[i] = new JLabel(labelNames[i]);
			if(i < labelNames.length-2){
				fields[i] = new JTextField();
				infoBox.add(labels[i]);
				infoBox.add(fields[i]);
			}
		}
		infoBox.add(searchBtnPanel);
		
		displayPanel = new JPanel(new BorderLayout());
		displayPanel.add(labels[labels.length-2], BorderLayout.NORTH);
		displayPanel.add(buttons[buttons.length-1], BorderLayout.SOUTH);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		displayPanel.add(scrollPane, BorderLayout.CENTER);
		
		containerBox.add(displayPanel);
		containerBox.add(infoBox);
		frame.add(containerBox);
		frame.setSize(800,500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void actionListeners(){
		buttons[0].addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String [] clientInfo = new String [7];
				for(int i = 0; i < clientInfo.length; i++){
					clientInfo[i] = fields[i].getText();
					System.out.println(clientInfo[i]);
				}
				
			}
			
		});
		buttons[1].addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		buttons[2].addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(int i = 0; i < 7; i++){
					fields[i].setText("");
				}
				
			}
			
		});
		buttons[3].addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JTextField searchType = new JTextField();
				JTextField searchParam = new JTextField();
				
				Object [] message ={
						"Type (Client Type, Last Name, ID", searchType,
						"Parameter", searchParam
				};
				int result = JOptionPane.showConfirmDialog(null, message, "Search Clients", JOptionPane.OK_CANCEL_OPTION);
				if(result == JOptionPane.OK_OPTION){
					System.out.println(searchType.getText() + " " + searchParam.getText());
					// TODO Auto-generated method stub
				}
			}
		});
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientGUI gui = new ClientGUI();
		gui.actionListeners();
	}
}
