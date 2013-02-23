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
		organization = new Organization("New Organization");
		
		//setLayout(new BoxLayout(this, BoxLayout.X_AXIS));		
		personModel = new DefaultListModel<Person>();
		JPanel personPanel = new JPanel();
		JList<Person> personList = new JList<Person>(personModel);
		personPanel.add(personList);
		add(personPanel);
		personPanel.setPreferredSize(new Dimension(300,300));

		areaModel = new DefaultListModel<Area>();
		JPanel areaPanel = new JPanel();
		areaPanel.setLayout(new BoxLayout(areaPanel,BoxLayout.Y_AXIS));
		areaPanel.add(new JList<Area>(areaModel));		
		JButton button = new JButton("View Area");
		button.setMaximumSize(new Dimension(200,50));
		areaPanel.add(button);
		areaPanel.setBorder(BorderFactory.createEtchedBorder());
		add(areaPanel);//, BorderLayout.EAST);
		areaPanel.setPreferredSize(new Dimension(300,300));
		
		areaPanel.setVisible(true);
		personPanel.setVisible(true);
		
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
	
	public void viewArea(String areaKey) {
		add(new AreaCanvas(organization.getArea(areaKey)));
	}

	private static void createAndShowGUI() {
		//Build the frame and start the mayoApp
		JFrame frame = new JFrame("Project Mayo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MayoSeater ms = new MayoSeater();
		frame.setContentPane(ms);
		
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
