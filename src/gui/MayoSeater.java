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
import javax.swing.Box;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Component;

import base.Organization;
import base.Person;
import base.Area;

import test.TestLoader;

public class MayoSeater extends JPanel {
	static final int PREFERRED_APP_WIDTH = 800;
	static final int PREFERRED_APP_HEIGHT = 600;
	static final int STARTING_PANEL_WIDTH = 160;
	static final int TOOLBAR_HEIGHT = 40;
	
	Organization organization;
	DefaultListModel<Person> personModel;
	DefaultListModel<Area> areaModel;

	public MayoSeater() {
		super();
		organization = new Organization("New Organization");
		setLayout(new BorderLayout());		
		setPreferredSize(new Dimension(PREFERRED_APP_WIDTH,PREFERRED_APP_HEIGHT));

		//Create and add the toolbar
		JToolBar toolBar = new JToolBar();
		toolBar.setPreferredSize(new Dimension(PREFERRED_APP_WIDTH, TOOLBAR_HEIGHT));
		add(toolBar,BorderLayout.NORTH);
		toolBar.add(new JButton("Delete Area"));
		toolBar.add(new JButton("Delete Person"));

		//Create and add the Person Panel
		personModel = new DefaultListModel<Person>();
		JPanel personPanel = new JPanel();
		personPanel.setLayout(new GridLayout());
		personPanel.add(new JList<Person>(personModel));
		personPanel.setBorder(BorderFactory.createEtchedBorder());
		personPanel.setPreferredSize(new Dimension(STARTING_PANEL_WIDTH,
																							 PREFERRED_APP_HEIGHT));
		personPanel.setVisible(true);
		add(personPanel,BorderLayout.EAST);
		personPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

		//Create and add the Area Panel
		areaModel = new DefaultListModel<Area>();
		JPanel areaPanel = new JPanel();
		areaPanel.setLayout(new GridLayout());
		areaPanel.add(new JList<Area>(areaModel));
		areaPanel.setBorder(BorderFactory.createEtchedBorder());
		areaPanel.setPreferredSize(
			new Dimension(STARTING_PANEL_WIDTH,PREFERRED_APP_HEIGHT));
		areaPanel.setVisible(true);	
		add(areaPanel,BorderLayout.WEST);
		areaPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
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

	public JMenuBar makeMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.add(new JMenuItem("New"));
		fileMenu.add(new JMenuItem("Open"));
		fileMenu.add(new JMenuItem("Save"));
		fileMenu.add(new JMenuItem("Quit"));
		menuBar.add(fileMenu);
		JMenu editMenu = new JMenu("Edit");
		editMenu.add(new JMenuItem("Add Area"));
		editMenu.add(new JMenuItem("Add Person"));
		menuBar.add(editMenu);
		return menuBar;
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
		frame.setJMenuBar(ms.makeMenuBar());
		
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
