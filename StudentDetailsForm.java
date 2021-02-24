// This is a GUI for a Students Detail Form, practical 1 of Advance Java

// imports

import com.sun.deploy.panel.JSmartTextArea;
import jdk.nashorn.internal.runtime.regexp.joni.ScanEnvironment;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;


public class StudentDetailsForm extends WriteToFile {
	JFrame frame = new JFrame("Student Details Form");
	JTabbedPane tab = new JTabbedPane();

	public StudentDetailsForm() throws IOException {
		super();
		tab.addTab("Personal Details", new PersonalDetails());
		tab.addTab("Education Details", new EducationDetails());
		frame.add(tab);
		frame.setSize(1000, 1000);
		frame.setVisible(true);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
	
	}
	
	public static class PersonalDetails extends JPanel implements ActionListener {
		JButton btg1 = new JButton("New");
		JButton btg2 = new JButton("Exit ");
		ButtonGroup btg = new ButtonGroup();
		JRadioButton male = new JRadioButton("Male");
		JRadioButton female = new JRadioButton("Female");
		JLabel name = new JLabel("Name: ");
		JLabel roll = new JLabel("Roll No. :");
		JTextField nameT = new JTextField();
		JTextField rollT = new JTextField();
		JLabel Add =  new JLabel("Address :");
		JLabel Gen =  new JLabel("Gender :");
		JTextArea AddT = new JTextArea();
		JTextArea infoArea = new JTextArea();

		public PersonalDetails() 
		{
			setLayout(null);
			name.setBounds(20, 20, 50,10);
			nameT.setBounds(100,20, 100, 20);
			nameT.setEditable(false);
			
			roll.setBounds(20, 60, 50, 10);
			rollT.setBounds(100,50, 100, 20);
			rollT.setEditable(false);
			
			Add.setBounds(20, 90, 60, 10);
			AddT.setBounds(100,80, 200, 50);
			AddT.setEditable(false);

			Gen.setBounds(20, 150, 50, 10);
			btg.add(male);
			btg.add(female);
			
			male.setBounds(100, 150, 70,15);
			female.setBounds(170, 150, 70,15);
			male.setEnabled(false);
			female.setEnabled(false);
			add(name);
			add(nameT);
			add(roll);
			add(rollT);
			add(Add);
			add(AddT);
			add(Gen);
			add(male);
			add(female);

			btg1.setBounds(100, 200, 70, 20);
			btg1.addActionListener(this);
			add(btg1);
			
			btg2.setBounds(180, 200, 70, 20);
			btg2.addActionListener(this);
			add(btg2);

			infoArea.setBounds(500,20,400,500);
			infoArea.setBackground(Color.LIGHT_GRAY);
			add(infoArea);
			infoArea.append(" NAME              |   ROLL        |   ADDRESS   |    GENDER   \n");
			infoArea.append("\n");


		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()== btg1) {
				if (btg1.getText().equals("New")) {
					nameT.setEditable(true);
					rollT.setEditable(true);
					AddT.setEditable(true);
					male.setEnabled(true);
					female.setEnabled(true);
					btg1.setText("Save");
				} else if (btg1.getText().equals("Save")) {
					try {
						saveToFile();
					} catch (IOException ioException) {
						ioException.printStackTrace();
					}
					nameT.setEditable(false);
					nameT.setText("");
					rollT.setEditable(false);
					rollT.setText("");
					AddT.setEditable(false);
					AddT.setText("");
					btg.clearSelection();
					male.setEnabled(false);
					female.setEnabled(false);
					btg1.setText("New");

				}
			}
			else if(e.getSource() == btg2) {
				System.exit(0);
			}

			
		}

		public void saveToFile() throws IOException {
			WriteToFile data = new WriteToFile("./saved_details.txt", true);
			// formating for showing on panel
			String name = nameT.getText() + "   |  ";
			String roll = rollT.getText() + "   |  ";
			String address = AddT.getText() + "         |   ";
			infoArea.setEditable(false);

			WriteToFile readData = new WriteToFile("./saved_details.txt");
			if(male.isSelected()){
				String[] input = {name, roll, address , " male\n"};
				for (String s : input) {
					data.write(s);
					infoArea.append(s);
				}
//				readData.readFromFile("./saved_details.txt");
//				StringBuilder localBuffer = readData.stringBuffRead;
//				System.out.println("From buffer "+ localBuffer);
			}else {
				String[] input = {name, roll, address, "female\n"};
				for (String s : input) {
					data.write(s);
					infoArea.append(s);
				}
			}

		}

	}


	public static class EducationDetails extends JPanel implements ItemListener {


		JLabel info = new JLabel("Please choose a year of study and check respective subjects");
		String[] years = {"Options","First year", "Second year", "Third year", "Fourth year"};
		JComboBox<String> cmb = new JComboBox<String>(years);
		// checkboxes for academic year topics
			//first year
		JCheckBox cbSub1 = new JCheckBox();
		JCheckBox cbSub2 = new JCheckBox();
		JCheckBox cbSub3 = new JCheckBox();
			//second year
		JCheckBox cb1sy = new JCheckBox("Molecular biology");
		JCheckBox cb2sy = new JCheckBox("Java");
		JCheckBox cb3sy = new JCheckBox("Electronics");

		public EducationDetails(){
			setLayout(null);
			// Set bounds to all components here
			info.setBounds(30,30,400,20);
			cmb.setBounds(30,50,200,20);
			cbSub1.setBounds(250,50,200,15);
			cbSub2.setBounds(250,70,200,15);
			cbSub3.setBounds(250,90,200,15);
			cbSub1.setEnabled(true);
			cbSub2.setEnabled(true);
			cbSub3.setEnabled(true);
			add(cbSub1); cbSub1.setVisible(false);
			add(cbSub2); cbSub2.setVisible(false);
			add(cbSub3); cbSub3.setVisible(false);

			// register the listener
			cmb.addItemListener(this);

			// add all components to the panel
			add(info);
			add(cmb);


		}

		@Override
		public void itemStateChanged(ItemEvent e) {
//			JPanel cbPanel = new JPanel();
//			cbPanel.setBounds(30,200,200,200);
//			cbPanel.setBackground(new Color(16, 50, 116));
//			add(cbPanel);
			if(e.getSource() == cmb){
				if(e.getStateChange() == ItemEvent.SELECTED){
					if (Objects.equals(cmb.getSelectedItem(), "First year")) {
						System.out.println("First year was selected");
						cbSub1.setVisible(true);
						cbSub2.setVisible(true);
						cbSub3.setVisible(true);
						cbSub1.setText("Applied Maths");
						cbSub2.setText("Mechanical Engg");
						cbSub3.setText("Electrical Engg");

					} else if (Objects.equals(cmb.getSelectedItem(), "Second year")) {
						// hide the previous checkboxes
						cbSub1.setText("Molecular Biology");
						cbSub2.setText("Java");
						cbSub3.setText("Electronics");

					}else if(Objects.equals((String)cmb.getSelectedItem(), "Third year")){
						cbSub1.setText("Genomics");
						cbSub2.setText("ML/AI");
						cbSub3.setText("Pharmacology");

					}else if(Objects.equals((String)cmb.getSelectedItem(), "Fourth year")){
						cbSub1.setText("Robotics");
						cbSub2.setText("Medical Imaging");
						cbSub3.setText("Bio Transport");
					}else if(Objects.equals((String)cmb.getSelectedItem(), "Options")){
						cbSub1.setVisible(false);
						cbSub2.setVisible(false);
						cbSub3.setVisible(false);
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new StudentDetailsForm();
		
	}

}















