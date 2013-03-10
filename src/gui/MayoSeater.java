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
import javax.swing.BoxLayout;
import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.event.ActionEvent;

import base.Organization;
import base.Person;
import base.Area;

/**
 * The MayoSeater is the base structure of the seating application.
 * It consists of the frame and outer interface of the app.
 *
 * @author Oren Ely
 *
 */
public class MayoSeater extends JPanel {
  /** The SVUID. */
  private static final long serialVersionUID = -1065761336704032574L;
  /** The preferred width of the Application Panel. */
  static final int PREFERRED_APP_WIDTH = 800;
  /** The preferred height of the Application Panel. */
  static final int PREFERRED_APP_HEIGHT = 600;
  /** The starting width of the area and people panels. */
  static final int STARTING_PANEL_WIDTH = 160;
  /** The height of the tool bar panel. */
  static final int TOOLBAR_HEIGHT = 40;
  /** The height of the buttons. */
  static final int BUTTON_HEIGHT = 20;
  /** The name of the application. Used in the title bar. */
  static final String APPLICATION_NAME = "Mayo Seater";
  /** A reference to the containing frame. */
  private final JFrame frame;
  /** A reference to the organization being manipulated. */
  private Organization organization;
  /** Represents an up-to-date model of the People in the organization. */
  private DefaultListModel<Person> personModel;
  /** Represents an up-to-date model of the Areas in the organization. */
  private DefaultListModel<Area> areaModel;

  /** This Action creates a new blank Organization. */
  private final Action newAction = new AbstractAction("New") {
    private static final long serialVersionUID = 3003587364492370843L;
    @Override
    public void actionPerformed(final ActionEvent e) {
      setOrganization(new Organization("New Organization"));
    }
  };

  /** This Action quits the Application. */
  private final Action quitAction = new AbstractAction("Quit") {
    private static final long serialVersionUID = -4841054297554920462L;
    @Override
    public void actionPerformed(final ActionEvent e) {
      System.exit(0);
    }
  };

  /** This Action opens an Organization. */
  private final Action openAction = new AbstractAction("Open") {
    private static final long serialVersionUID = 8673814562827992271L;
    @Override
    public void actionPerformed(final ActionEvent e) {
      JFileChooser jfc = new JFileChooser();
      int returnval = jfc.showOpenDialog(MayoSeater.this);
    }
  };

  /** This Action writes the Organization to a file. */
  private final Action saveAction = new AbstractAction("Save") {
    private static final long serialVersionUID = 733808816987333191L;
    @Override
    public void actionPerformed(final ActionEvent e) {
      JFileChooser jfc = new JFileChooser();
      int returnval = jfc.showSaveDialog(MayoSeater.this);
    }
  };

  /** This Action sets the Area to be viewed in the AreaCanvas. */
  private final Action viewAreaAction = new AbstractAction() {
    private static final long serialVersionUID = 6565006504766219153L;
    @Override
    public void actionPerformed(final ActionEvent e) {
      viewArea(e.getActionCommand());
    }
  };
  
  /** This Action adds a new Person to the Organization. */
  private final Action addPersonAction = new AbstractAction("Add Person") {
    @Override
    public void actionPerformed(final ActionEvent e) {
      String result = JOptionPane.showInputDialog("Person's name?");
      final Person person = new Person(result);
      organization.addPerson(person);
      personModel.addElement(person);
    }
  };
  
  /** This Action adds a new Area to the Organization */
  private final Action addAreaAction = new AbstractAction("Add Area") {
    @Override
    public void actionPerformed(final ActionEvent e) {
      JOptionPane.showInputDialog("Area name?");
    }
  };

  /**
   * Builds and initializes the application.  Requires a reference to the
   * containing frame.
   * @param mFrame The parent JFrame.
   */
  public MayoSeater(final JFrame mFrame) {
    super();
    //Create the frame;
    this.frame = mFrame;
    frame.setJMenuBar(makeMenuBar());

    setLayout(new BorderLayout());
    setPreferredSize(
        new Dimension(PREFERRED_APP_WIDTH, PREFERRED_APP_HEIGHT));

    JToolBar toolBar = new JToolBar();
    toolBar.setPreferredSize(
        new Dimension(PREFERRED_APP_WIDTH, TOOLBAR_HEIGHT));
    add(toolBar, BorderLayout.NORTH);
    toolBar.add(new JButton("Delete Area"));
    toolBar.add(new JButton("Delete Person"));

    personModel = new DefaultListModel<>();
    add(buildPanel("Add Person", new JList<>(personModel), addPersonAction), 
        BorderLayout.EAST);

    areaModel = new DefaultListModel<>();
    add(buildPanel("Add Area", new JList<>(areaModel), addAreaAction),
        BorderLayout.WEST);

    setOrganization(new Organization("New Organization"));
  }

  /**
   * Builds a JPanel with a vertical BoxLayout containing a button.
   * @param buttonName The name of the button being placed in the panel.
   * @return the JPanel.
   */
  private JPanel buildPanel(final String buttonName, 
          JList list, Action action) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(BorderFactory.createEtchedBorder());
    panel.setPreferredSize(
        new Dimension(STARTING_PANEL_WIDTH, PREFERRED_APP_HEIGHT));
    panel.setVisible(true);

    //JButton button = new JButton(buttonName);
    JButton button = new JButton(action);
    //button.setAction(action);
    button.setAlignmentX(JButton.CENTER_ALIGNMENT);
    button.setMaximumSize(new Dimension(STARTING_PANEL_WIDTH, BUTTON_HEIGHT));

    panel.add(button);
    list.setMaximumSize(new Dimension(STARTING_PANEL_WIDTH,
        PREFERRED_APP_HEIGHT - BUTTON_HEIGHT));
    panel.add(list);

    return panel;
  }

  /**
   * Sets the organization being viewed in the application.
   * @param org The organization to be viewed in the application.
   */
  private void setOrganization(final Organization org) {
    this.organization = org;
    personModel.clear();
    for (Person bloke : org.getPeople()) {
      personModel.addElement(bloke);
    }
    areaModel.clear();
    for (Area area : org.getAreas()) {
      areaModel.addElement(area);
    }
    frame.setTitle(APPLICATION_NAME + ": " + org.getName());
  }

  /**
   * Creates and returns a Menu Bar customized to work with the application.
   * @return the created Menu Bar.
   */
  private JMenuBar makeMenuBar() {
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    fileMenu.add(new JMenuItem(newAction));
    fileMenu.add(new JMenuItem(openAction));
    fileMenu.add(new JMenuItem(saveAction));
    fileMenu.add(new JMenuItem(quitAction));
    menuBar.add(fileMenu);

    JMenu editMenu = new JMenu("Edit");
    //editMenu.add(makeMenuItem("Add Area", null));
    //editMenu.add(makeMenuItem("Add Person", null));
    menuBar.add(editMenu);

    return menuBar;
  }

  /**
   * Sets the area being viewed in the AreaCanvas.
   * @param areaKey The ID of the Area to be viewed.
   */
  private void viewArea(final String areaKey) {
    add(new AreaCanvas(organization.getArea(areaKey)));
  }

  /**
   * Creates and begins the application.
   */
  private static void createAndShowGUI() {
    //Build the frame and start the mayoApp
    JFrame frame = new JFrame("Mayo Seater");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    MayoSeater ms = new MayoSeater(frame);
    frame.setContentPane(ms);
    frame.setJMenuBar(ms.makeMenuBar());

    //Create an organization using the test loader
    Organization org = new Organization("New Organization");
    ms.setOrganization(org);
    frame.setTitle(APPLICATION_NAME + ": " + org.getName());
    frame.pack();
    frame.setVisible(true);
  }

  /**
   * Runs the application.
   * @param args No purpose here.
   */
  public static void main(final String[] args) {
    //No idea what this does
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        createAndShowGUI();
        //new TestLoader();
//        xload.clear();  // this clears out binding
//        xload.test(); // this creates a test sample "test2.xml"
//        xload.read("test2.xml");
        //if (!tload.personStackEmpty()) {
        //  System.out.println("Popping a person");
        //  System.out.println(tload.popPerson().getName());
        //}
      }
    });
  }
}
