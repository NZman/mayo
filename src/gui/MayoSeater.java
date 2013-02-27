package gui;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Component;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import base.Organization;
import base.Person;
import base.Area;

import test.TestLoader;

public class MayoSeater extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1065761336704032574L;
	static final int PREFERRED_APP_WIDTH = 800;
	static final int PREFERRED_APP_HEIGHT = 600;
	static final int STARTING_PANEL_WIDTH = 160;
	static final int TOOLBAR_HEIGHT = 40;
	static final String APPLICATION_NAME = "Mayo Seater";
	
	private final JFrame frame;
	Organization organization;
	DefaultListModel<Person> personModel;
	DefaultListModel<Area> areaModel;

	public MayoSeater(JFrame frame) {
		super();
		//Create the frame;
		this.frame = frame;
		frame.setJMenuBar(makeMenuBar());
		
		setLayout(new BorderLayout());		
		setPreferredSize(
				new Dimension(PREFERRED_APP_WIDTH,PREFERRED_APP_HEIGHT));

		//Create and add the tool bar
		JToolBar toolBar = new JToolBar();
		toolBar.setPreferredSize(
				new Dimension(PREFERRED_APP_WIDTH, TOOLBAR_HEIGHT));
		add(toolBar,BorderLayout.NORTH);
		toolBar.add(new JButton("Delete Area"));
		toolBar.add(new JButton("Delete Person"));

		//Create and add the Person Panel
		personModel = new DefaultListModel<Person>();
		JPanel personPanel = new JPanel();
		personPanel.setLayout(new GridLayout());
		personPanel.add(new JList<Person>(personModel));
		personPanel.setBorder(BorderFactory.createEtchedBorder());
		personPanel.setPreferredSize(
				new Dimension(STARTING_PANEL_WIDTH, PREFERRED_APP_HEIGHT));
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
		
		setOrganization(new Organization("New Organization"));
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
		frame.setTitle(APPLICATION_NAME+": "+org.getName());
	}

	public JMenuBar makeMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.add(makeMenuItem("New"));
		fileMenu.add(makeMenuItem("Open"));
		fileMenu.add(makeMenuItem("Save"));
		fileMenu.add(makeMenuItem("Quit"));
		
		menuBar.add(fileMenu);
		JMenu editMenu = new JMenu("Edit");
		editMenu.add(makeMenuItem("Add Area"));
		editMenu.add(makeMenuItem("Add Person"));
		menuBar.add(editMenu);
		return menuBar;
	}
	
	private JMenuItem makeMenuItem(String name) {
		JMenuItem item = new JMenuItem(name);
		item.addActionListener(this);
		return item;
	}
	
	public void viewArea(String areaKey) {
		add(new AreaCanvas(organization.getArea(areaKey)));
	}
	
	public void actionPerformed(ActionEvent event) {
		switch (event.getActionCommand()) {
		case "New" : 
			setOrganization(new Organization("New Organization")); 
			break;
		case "Open": break;
		case "Save": break;
		case "Quit": System.exit(0); break;
		}
	}

	private static void createAndShowGUI() {
		//Build the frame and start the mayoApp
		JFrame frame = new JFrame("Mayo Seater");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MayoSeater ms = new MayoSeater(frame);
		frame.setContentPane(ms);
		frame.setJMenuBar(ms.makeMenuBar());
		
		//Create an organization using the test loader
		Organization org = new Organization(new TestLoader());
		ms.setOrganization(org);
		frame.setTitle(APPLICATION_NAME+": "+org.getName());
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
