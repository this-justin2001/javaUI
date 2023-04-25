package homework;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.time.*;
import java.io.*;
import java.text.*;

public class javaUI extends JFrame implements ActionListener{
	static JMenuBar menuBar; 
	private JMenu menu;
	private JMenuItem item1, item2, item3, item4,item5;
	static JLabel dateTimeLabel;
	static JTextField dateTimeField;
	static JPanel myPanel;
	static JTextField infoField;
	static JLabel infoLabel;

	javaUI() {//JFrame constructor
		
	GridBagConstraints layoutConst = null;
		
	setTitle("Your Interface"); //creates title
		
	dateTimeLabel = new JLabel("Date & Time"); //label for date and time
	infoLabel = new JLabel("Info"); //label for info 
	
	infoField = new JTextField(20); //sets info field size
	infoField.setText(""); //sets info field text to void
	infoField.setEditable(false); //does not allow editing
	
	dateTimeField = new JTextField(20); //sets date and time field size
	dateTimeField.setText(""); //sets date and time field text to void
	dateTimeField.setEditable(false);//does not allow editing
	
	setLayout(new GridBagLayout()); //creates the grid layout for JFrame
	
	layoutConst = new GridBagConstraints(); //info label layout
	layoutConst.insets = new Insets(10,0,1,10);
	layoutConst.gridx = 1;
	layoutConst.gridy = 0;
	add(infoLabel, layoutConst);
	
	layoutConst = new GridBagConstraints(); //info field layout
	layoutConst.insets = new Insets(1,0,10,10);
	layoutConst.gridx = 1;
	layoutConst.gridy = 1;
	add(infoField, layoutConst);
	
	layoutConst = new GridBagConstraints(); //date and time label layout
	layoutConst.insets = new Insets(10,0,1,10);
	layoutConst.gridx = 1;
	layoutConst.gridy = 2;
	add(dateTimeLabel, layoutConst);
	 
	layoutConst = new GridBagConstraints(); //date and time field layout
	layoutConst.insets = new Insets(1,0,10,10);
	layoutConst.gridx = 1;
	layoutConst.gridy = 3;
	add(dateTimeField, layoutConst);
	
	menuBar = new JMenuBar(); //creates JMenu bar
	menu = new JMenu("Menu"); //creates menu, names it "menu"
	menuBar.add(menu); //adds menu to menu bar
	
	item1 = new JMenuItem("Option 1"); //creates menu item
	item1.addActionListener(this); //adds action listener
	menu.add(item1); //adds menu item to menu
	
	item2 = new JMenuItem("Option 2");//creates menu item
	item2.addActionListener(this);//adds action listener
	menu.add(item2);//adds menu item to menu
	
	item3 = new JMenuItem("Option 3");//creates menu item
	item3.addActionListener(this);//adds action listener
	menu.add(item3);//adds menu item to menu
	
	item4 = new JMenuItem("Close");//creates menu item
	item4.addActionListener(this);//adds action listener
	menu.add(item4);//adds menu item to menu
	
	item5 = new JMenuItem("Clear Text Box");//creates menu item
	item5.addActionListener(this);//adds action listener
	menu.add(item5);//adds menu item to menu
	
	}
	public static void main(String[] args) {
		javaUI myFrame = new javaUI(); //creates JFrame
		
		myFrame.setJMenuBar(menuBar); //sets menu bar on JFrame
		myFrame.setDefaultCloseOperation(EXIT_ON_CLOSE); //allows JFrame to exit when closed
		myFrame.setSize(350,250); //sets frame size, .pack() constrains frame too much
		myFrame.setVisible(true); //allows frame to be visible
	}
		public void actionPerformed(ActionEvent event) {
		
			Date formatTime = new Date(); //creates formatted time object
			String strDateFormat = "HH:mm:ss a"; //formats time to a more common version, also displays AM/PM
			SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat); //creates format object
			LocalDate date = LocalDate.now(); //creates date object
			
			Random rand = new Random();//generates randomizer
			float r = rand.nextFloat()/2f; //generates random RED variable, reduces number to make green hue
			float g = rand.nextFloat(); //generates random green variable, unrestricted
			float b = rand.nextFloat()/2f;//generates random BLUE variable, reduces number to make green hue
			Color randomGreen = new Color((r/3),g,(b/3)); //combines RGB values to create green hue
			
			Object src = event.getSource(); // compares src object to event and allows each menu item to have its own action event
			if (src == item1) { //displays date and time in textField
				dateTimeField.setText("" + date + " || " + sdf.format(formatTime)); //sets textField to date and time variables
			}else if(src == item2) {  //writes date and time to file
				try {
				FileWriter myWriter = new FileWriter("log.txt");
				myWriter.write("Date:" + date + " || Time: " + sdf.format(formatTime));
				infoField.setText("Text sent to log.txt!");//validates that text has been written to file
				myWriter.close();//closes writer
				}catch(IOException msg) {
					infoField.setText("ERROR: text not sent to file"); //IOException in case file cannot be written
				}
			}else if(src == item3) {//sets background of JFrame to random green hue generated
				this.getContentPane().setBackground(randomGreen);//sets color
			}else if(src == item4) {//closes window on selection of menu item
				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));//window event that closes window
			}else if(src == item5) { //added functionality, clears text boxes on selection
				dateTimeField.setText("");
				infoField.setText("");
			}
	}

}