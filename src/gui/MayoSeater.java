package gui;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;
import javax.swing.BoxLayout;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;

import base.Organization;
import base.Person;
import base.Area;

import test.TestLoader;

public class MayoSeater extends JPanel {
	static final int PREFERRED_APP_WIDTH = 800;
	static final int PREFERRED_APP_HEIGHT = 600;
	
	Organization organization;
	DefaultListModel<Person> personModel;
	DefaultListModel<Area> areaModel;

	public MayoSeater() {
		super();
		organization = new Organization();
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
				
		personModel = new DefaultListModel<Person>();
		areaModel = new DefaultListModel<Area>();
		JList<Person> personList = new JList<Person>(personModel);

		JPanel areaList = new JPanel();
		areaList.setLayout(new BoxLayout(areaList,BoxLayout.Y_AXIS));
		areaList.add(new JList<Area>(areaModel));		
		JButton button = new JButton("View Area");
		button.setMaximumSize(new Dimension(200,50));
		areaList.add(button);
		areaList.setBorder(BorderFactory.createEtchedBorder());

		add(personList, BorderLayout.WEST);
		add(areaList, BorderLayout.EAST);
		setPreferredSize(new Dimension(PREFERRED_APP_WIDTH,PREFERRED_APP_HEIGHT));
	}
			
	public void setOrganization(Organization org) {
		this.organization = org;
		personModel.clear();
		for (Person bloke : org.getPeople()) {
			personModel.addElement(bloke);
		}
		areaModel.clear();
		for (Area area : org.getAreas()) {
			areaModel.addElement(area);
		}
	}
	
	public void viewArea(int areaKey) {
		add(new SeatCanvas(organization.getArea(areaKey)));
	}

	private static void createAndShowGUI() {
		//Build the frame and start the mayoApp
		JFrame frame = new JFrame("Project Mayo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MayoSeater ms = new MayoSeater();
		frame.add(ms);
		
		//Create an organization using the test loader
		Organization org = new Organization(new TestLoader());
		ms.setOrganization(org);
		frame.pack();
		frame.setVisible(true);
	}
    
    public static void main(String[] args) {
    	//No idea what this does
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        });
    }
}
