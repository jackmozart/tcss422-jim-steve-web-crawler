package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * 
 * @author Steven Cozart
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CrawlerGUI extends JFrame implements ActionListener{
	
	private static final String END_TEXT = "End City";

	private static final String START_TEXT = "Start City";

	JPanel my_panel;
	
	JFileChooser my_fileChooser;
	
	JButton my_fileButton;

	private JComboBox my_startList;

	private JComboBox my_endList;

	private JButton my_path;

	private JLabel my_output;

	private JLabel my_fileLabel;

	private Object my_analyzer;
	
	public CrawlerGUI (){
		super();
		//sets up the window
		this.setTitle("I SAID GOOD DAY SIR");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//adds panel to the window
		my_panel = new JPanel(new FlowLayout());
		this.setContentPane(my_panel);
		my_panel.setBackground(Color.white);
		
		//add file chooser 
		my_fileChooser = new JFileChooser((new File(".")).getAbsolutePath());
		my_fileButton = new JButton("Load File");
		my_fileButton.addActionListener(this);
		
		my_fileLabel = new JLabel();
		
		my_panel.add(my_fileLabel);
		my_panel.add(my_fileButton);
		
		//make drop down lists
		my_startList = new JComboBox();
		my_startList.addItem(START_TEXT);
		my_panel.add(my_startList);
		
		my_endList = new JComboBox();
		my_endList.addItem(END_TEXT);
		my_panel.add(my_endList);
		
		//make path button to do work
		my_path = new JButton("Compute fastest Route");
		my_panel.add(my_path);
		my_path.addActionListener(this);
		
		//make output label
		my_output = new JLabel();
		my_output.setFont(new Font("Helvetica",Font.BOLD, 22));
		my_panel.add(my_output);

		
		
		this.pack();
	}

	@Override
	public void actionPerformed(ActionEvent the_event) {
		if (the_event.getSource() == my_path) {
			//find path button was selected get the selected starting and ending cites.
			
			if(my_startList.getSelectedItem().equals(START_TEXT) || my_endList.getSelectedItem().equals(END_TEXT)){
				//used for error checking (if no city is selected)
				my_output.setText("No City selected");
				this.pack();
				return;
			}

			my_output.setText(my_analyzer.toString());			
	
		} else if (the_event.getSource() == my_fileButton) { // chooose file
																// button was
																// selected
			int result = my_fileChooser.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				my_fileLabel.setText("Selected File is:"
						+ my_fileChooser.getSelectedFile().getAbsolutePath());

				//clear out any old information
				my_startList.removeAll();
				my_endList.removeAll();
				
				//fill in all new start and end possibilities  

			}
		}
		this.pack();

	}

	
	public void start() {
		this.setVisible(true);		
	}
	
}
