package gui;

import java.awt.BorderLayout; // layout manager for main window
import java.awt.Color; // the color of widgets, text, or borders
import java.awt.FlowLayout; // layout manager for About dialog
import java.awt.Font; // rich text in a JLabel or similar widget
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.image.BufferedImage; // holds an image loaded from a file
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File; // opens a file
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException; // reports an error reading from a file
import javax.imageio.ImageIO; // loads an image from a file
import javax.swing.*;
import javax.swing.BorderFactory; // manufacturers Border objects around buttons
import javax.swing.Box; // to create toolbar spacer
import javax.swing.ImageIcon; // holds a custom icon
import javax.swing.JButton; // regular button
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame; // for main window
import javax.swing.JFrame;
import javax.swing.JLabel; // text or image holder
import javax.swing.JLabel;
import javax.swing.JMenu; // menu selection that offers another menu
import javax.swing.JMenuBar; // row of menu selections
import javax.swing.JMenuItem; // menu selection that does something
import javax.swing.JOptionPane; // for standard dialogs
import javax.swing.JToggleButton; // 2-state button
import javax.swing.JToolBar; // row of buttons under the menu
import javax.swing.SwingConstants; // useful values for Swing method calls
import javax.swing.UIManager; // to access default icons
import javax.swing.filechooser.FileNameExtensionFilter;
import store.*;

public class MainWin extends JFrame {

  public MainWin(String title) {
    super(title);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(990, 660);
    setLocationRelativeTo(null);
    // /////// ////////////////////////////////////////////////////////////////
    // M E N U
    // Add a menu bar to the PAGE_START area of the Border Layout

    JMenuBar menubar = new JMenuBar();

    JMenu file = new JMenu("File");
    JMenuItem save = new JMenuItem("Save");
    JMenuItem saveas = new JMenuItem("SaveAs");
    JMenuItem open = new JMenuItem("Open");
    JMenuItem neww = new JMenuItem("New Store");
    JMenuItem quit = new JMenuItem("Quit");
    JMenu insert = new JMenu("Insert");
    JMenuItem customer = new JMenuItem("Customer");
    JMenuItem option = new JMenuItem("Option");
    JMenuItem order = new JMenuItem("Order");
    JMenuItem computer = new JMenuItem("Computer");
    JMenu view = new JMenu("View");
    JMenuItem customers = new JMenuItem("Customer");
    JMenuItem options = new JMenuItem("Option");
    JMenuItem orders = new JMenuItem("Order");
    JMenuItem computers = new JMenuItem("Computer");
    JMenu help = new JMenu("Help");
    JMenuItem about = new JMenuItem("About");

    quit.addActionListener(event -> onQuitClick());
    save.addActionListener(event -> onSaveStoreClick());
    saveas.addActionListener(event -> onSaveStoreAsClick());
    neww.addActionListener(event -> onNewStoreClick());
    open.addActionListener(event -> onOpenStoreClick());
    customer.addActionListener(event -> onInsertCustomerClick());
    option.addActionListener(event -> onInsertOptionClick());
    order.addActionListener(event -> onInsertOrderClick());
    computer.addActionListener(event -> onInsertComputerClick());
    customers.addActionListener(event -> onViewClick(Record.CUSTOMER));
    options.addActionListener(event -> onViewClick(Record.OPTION));
    orders.addActionListener(event -> onViewClick(Record.ORDER));
    computers.addActionListener(event -> onViewClick(Record.COMPUTER));
    about.addActionListener(event -> onAboutClick());

    file.add(neww);
    file.add(open);
    file.add(save);
    file.add(saveas);
    file.add(quit);
    insert.add(customer);
    insert.add(option);
    insert.add(computer);
    insert.add(order);
    view.add(customers);
    view.add(options);
    view.add(computers);
    view.add(orders);
    help.add(about);

    menubar.add(file);
    menubar.add(insert);
    menubar.add(view);
    menubar.add(help);
    setJMenuBar(menubar);

    // ///////////// //////////////////////////////////////////////////////////
    // T O O L B A R
    // Add a toolbar to the PAGE_START region below the menu

    toolbar = new JToolBar("Store Controls");

    toolbar.add(Box.createHorizontalStrut(10));

    newW = new JButton(new ImageIcon("gui/resources/new.png"));
    newW.setActionCommand("New Store");
    newW.setToolTipText("New Store");
    toolbar.add(newW);
    newW.addActionListener(event -> onNewStoreClick());

    openn = new JButton(new ImageIcon("gui/resources/open.png"));
    openn.setActionCommand("Open Store");
    openn.setToolTipText("Open Store");
    toolbar.add(openn);
    openn.addActionListener(event -> onOpenStoreClick());

    saveass = new JButton(new ImageIcon("gui/resources/saveas.png"));
    saveass.setActionCommand("Save As ");
    saveass.setToolTipText("Save As");
    toolbar.add(saveass);
    saveass.addActionListener(event -> onSaveStoreAsClick());

    savee = new JButton(new ImageIcon("gui/resources/save.png"));
    savee.setActionCommand("Save the store");
    savee.setToolTipText("Save the store");
    toolbar.add(savee);
    savee.addActionListener(event -> onSaveStoreClick());

    toolbar.add(Box.createHorizontalStrut(25));

    // Create the 3 buttons using the icons provided
    ImageIcon ii = new ImageIcon("gui/resources/insertCustomer.png");
    insertCustomer =
      new JButton(new ImageIcon("gui/resources/insertCustomer.png"));
    insertCustomer.setActionCommand("Insert a customer");
    insertCustomer.setToolTipText("Insert a customer");
    toolbar.add(insertCustomer);
    insertCustomer.addActionListener(event -> onInsertCustomerClick());

    insertOption = new JButton(new ImageIcon("gui/resources/insertOption.png"));
    insertOption.setActionCommand("Insert a option");
    insertOption.setToolTipText("Insert a option");
    toolbar.add(insertOption);
    insertOption.addActionListener(event -> onInsertOptionClick());

    insertComputer =
      new JButton(new ImageIcon("gui/resources/insertComputer.png"));
    insertComputer.setActionCommand("Insert a Computer");
    insertComputer.setToolTipText("Insert a Computer");
    toolbar.add(insertComputer);
    insertComputer.addActionListener(event -> onInsertComputerClick());

    insertOrder =
      new JButton(new ImageIcon("gui/resources/insertOrder.png"));
    insertOrder.setActionCommand("Insert an Order");
    insertOrder.setToolTipText("Insert an Order");
    toolbar.add(insertOrder);
    insertOrder.addActionListener(event -> onInsertOrderClick());

    toolbar.add(Box.createHorizontalStrut(25));

    viewCustomer = new JButton(new ImageIcon("gui/resources/viewCustomer.png"));
    viewCustomer.setActionCommand("View customer");
    viewCustomer.setToolTipText("View customer");
    toolbar.add(viewCustomer);
    viewCustomer.addActionListener(event -> onViewClick(Record.CUSTOMER));

    viewOption = new JButton(new ImageIcon("gui/resources/viewOption.png"));
    viewOption.setActionCommand("View option");
    viewOption.setToolTipText("View a option");
    toolbar.add(viewOption);
    viewOption.addActionListener(event -> onViewClick(Record.OPTION));

    viewComputer = new JButton(new ImageIcon("gui/resources/viewComputer.png"));
    viewComputer.setActionCommand("View Computer");
    viewComputer.setToolTipText("View Computer");
    toolbar.add(viewComputer);
    viewComputer.addActionListener(event -> onViewClick(Record.COMPUTER));

    viewOrder = new JButton(new ImageIcon("gui/resources/viewOrder.png"));
    viewOrder.setActionCommand("View Order");
    viewOrder.setToolTipText("View Order");
    toolbar.add(viewOrder);
    viewOrder.addActionListener(event -> onViewClick(Record.ORDER));

    toolbar.add(Box.createHorizontalGlue());
    JButton quitB = new JButton("X");
    quitB.setActionCommand("Quit");
    quitB.setToolTipText("Exit Store");
    quitB.setBorder(null);
    toolbar.add(quitB);
    quitB.addActionListener(event -> onQuitClick());
    toolbar.addSeparator();

    getContentPane().add(toolbar, BorderLayout.PAGE_START);

    // /////////////////////////// ////////////////////////////////////////////
    // C O M P U T E R S   D I S P L A Y
    display = new JLabel();
    display.setFont(new Font("SansSerif", Font.BOLD, 18));
    add(display, BorderLayout.CENTER);
    display.setSize(600, 400);

    // S T A T U S   B A R   D I S P L A Y ////////////////////////////////////
    // Provide a status bar for game messages
    msg = new JLabel();
    add(msg, BorderLayout.PAGE_END);
    msg.setSize(600, 400);

    // Make everything in the JFrame visible
    setVisible(true);

    // Start a new store
    storee = new Store("ELSA");
  }

  protected void onNewStoreClick() { // Create a new store
    storee = new Store("ELSA");
    getContentPane().removeAll();
    repaint();
    getContentPane().add(toolbar, BorderLayout.PAGE_START);
    storee =
      new Store(
        JOptionPane.showInputDialog(null, "Enter the name of your store:")
      );
    //setSticks();
    msg.setFont(new JLabel().getFont()); // Reset to default font
  }

  protected void onSaveStoreAsClick() {
    final JFileChooser fc = new JFileChooser(filename);
    FileNameExtensionFilter storeFiles = new FileNameExtensionFilter(
      "Store Files",
      "store"
    );
    fc.addChoosableFileFilter(storeFiles);
    fc.setFileFilter(storeFiles);
    int result = fc.showSaveDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      filename = fc.getSelectedFile();
      if (!filename.getAbsolutePath().endsWith(".store")) filename =
        new File(filename.getAbsolutePath() + ".store");
      onSaveStoreClick(); // Delegate to Save method
    }
  }

  protected void onSaveStoreClick() {
    if (filename == null) {
      int result = JOptionPane.showConfirmDialog(
        this,
        "You have not saved your file yet.\nDo you want to save your file?",
        "Failed",
        JOptionPane.OK_CANCEL_OPTION
      );
      if (result == JOptionPane.OK_OPTION) onSaveStoreAsClick();
    } else {
      try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
        storee.save(bw);
      } catch (Exception e) {
        JOptionPane.showMessageDialog(
          this,
          "Unable to open " + filename + '\n' + e,
          "Failed",
          JOptionPane.ERROR_MESSAGE
        );
      }
    }
  }

  protected void onOpenStoreClick() {
    JFileChooser fc = new JFileChooser(filename); // Create a file chooser dialog
    FileNameExtensionFilter storeFiles = new FileNameExtensionFilter(
      "Store files",
      "store"
    );
    fc.addChoosableFileFilter(storeFiles);
    fc.setFileFilter(storeFiles); // Show Nim files only by default

    int result = fc.showOpenDialog(this); // Run dialog, return button clicked
    if (result == JFileChooser.APPROVE_OPTION) { // Also CANCEL_OPTION and ERROR_OPTION
      filename = fc.getSelectedFile(); // Obtain the selected File object
      try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        storee = new Store(br); // Open a new game
        getContentPane().removeAll();
        repaint();
        getContentPane().add(toolbar, BorderLayout.PAGE_START); // Update the user interface
      } catch (Exception e) {
        JOptionPane.showMessageDialog(
          this,
          "Unable to open " + filename + '\n' + e,
          "Failed",
          JOptionPane.ERROR_MESSAGE
        );
      }
    }
  }

  protected void onInsertCustomerClick() {
    try { //
      String[] fields = { "Enter name:", "Enter email:" };
      String[] values = UnifiedDialog(fields, "ADDING CUSTOMER ", null);
      if (values[0] == null && values[1] == null) {
        // User clicked cancel
      } else {
        Customer customer = new Customer(values[0], values[1]);
        storee.add(customer);
      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Invalid email address provided"+e);
    }
    onViewClick(Record.CUSTOMER);
  }

  protected void onInsertOptionClick() {
    //
    String[] fields = { "Enter name:", "Enter cost:" };
    String[] values = UnifiedDialog(fields, "ADDING OPTIONS ", null);
    if (values[0] != null && values[1] != null) {
      // User didn't click cancel
      double value = Double.parseDouble(values[1]);
      long cost = (long) (value * 100);
      Option my_option = new Option(values[0], cost);
      storee.add(my_option);
    }
    onViewClick(Record.OPTION);
  }

  protected void onInsertComputerClick() {
    //
    String[] fields = { "Enter Computer's name", "Enter Computer's model" };
    String[] values = UnifiedDialog(fields, "ADDING COMPUTER ", null);
    Computer my_computer = new Computer(values[0], values[1]);
    int optionsadded = 0;
    while (values[0]!=null && values[1]!=null) {
      JComboBox comboBox = new JComboBox(storee.options());
      int result = JOptionPane.showConfirmDialog(
        null,
        comboBox,
        "Select an Option",
        JOptionPane.OK_CANCEL_OPTION
      );
      if (result == JOptionPane.OK_OPTION) { //Please add while True
        Object selectedItem = comboBox.getSelectedItem();
        Option o = (Option) selectedItem;
        my_computer.addOption(o);
      }
      if (result != JOptionPane.OK_OPTION) { //Please add while True
        break;
      }
    }
    if (values[0] != null && values[1] != null) storee.add(my_computer);
    onViewClick(Record.COMPUTER);
    //  focus in : The first thing is refresh the page instantaneously abd other is insert computer click should have
  }

  protected void onInsertOrderClick() {
    boolean order=false;
    Customer choice=null;
    Order my_order;
    
    if(storee.customers().length==0){
      int addCustomers = JOptionPane.showConfirmDialog(null, "No customer to create order for?", "No Customers", JOptionPane.OK_CANCEL_OPTION);
      if (addCustomers == JOptionPane.OK_OPTION) {
        onInsertCustomerClick();
      }
      if (storee.customers().length==0)return;;
      order=true;
    }
    if(storee.customers().length==1){
      int addCustomer=JOptionPane.showConfirmDialog(null, "Order for customer"+storee.customers()[0],"Confirmation", JOptionPane.OK_CANCEL_OPTION);
      if(addCustomer== JOptionPane.OK_OPTION){
        choice=(Customer) storee.customers()[0];
        order=true;
      }
    }
    if(storee.customers().length>1){
      JComboBox comboBox = new JComboBox(storee.customers());
      int result = JOptionPane.showConfirmDialog(null, comboBox, "Select an Customer for your order", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
      Object selectedItem = comboBox.getSelectedItem();
      choice = (Customer) selectedItem;
      order=true;  
      }
    }
    if(storee.computers().length==0){
      int addComputers = JOptionPane.showConfirmDialog(null, "No computers to make Orders. Do you want to add computers?", "No Computers", JOptionPane.OK_CANCEL_OPTION);
      if (addComputers == JOptionPane.OK_OPTION) {
        onInsertComputerClick();
      }
      if(storee.computers().length==0)order=false;
    }
    if(order==true){
      my_order=new Order(choice);
      while(order==true){
        JComboBox computerList = new JComboBox(storee.computers());
        int result = JOptionPane.showConfirmDialog(null, computerList, "Select an Computer for your order", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
        Object selectedItem = computerList.getSelectedItem();
        Computer my_computer = (Computer) selectedItem;
        my_order.addComputer(my_computer);
        order=true;  
        }
        else order=false;
      }
      storee.add(my_order);
      onViewClick(Record.ORDER);
  }
    
  }
  protected String[] UnifiedDialog(
    String[] fields,
    String title,
    String iconFilename
  ) {
    ImageIcon icon = null;
    if (iconFilename != null && !iconFilename.isEmpty()) {
      try {
        icon = new ImageIcon(iconFilename);
      } catch (Exception e) {
        // Ignore errors loading the icon
      }
    }
    int numFields = fields.length;
    JLabel[] labels = new JLabel[numFields];
    JTextField[] textFields = new JTextField[numFields];
    for (int i = 0; i < numFields; i++) {
      labels[i] = new JLabel(fields[i]);
      textFields[i] = new JTextField();
    }

    // Create the panel with the labels and text fields
    JPanel panel = new JPanel(new GridLayout(numFields, 2, 5, 5));
    for (int i = 0; i < numFields; i++) {
      panel.add(labels[i]);
      panel.add(textFields[i]);
    }

    // Show the dialog
    int result = JOptionPane.showConfirmDialog(
      null,
      panel,
      title,
      JOptionPane.OK_CANCEL_OPTION,
      JOptionPane.PLAIN_MESSAGE,
      icon
    );

    // If OK was clicked, return the text from the text fields
    if (result == JOptionPane.OK_OPTION) {
      String[] values = new String[numFields];
      for (int i = 0; i < numFields; i++) {
        values[i] = textFields[i].getText();
      }
      return values;
    }

    // Otherwise, return null
    return null;
  }

  protected void onViewClick(Record obj) {
    //
    getContentPane().removeAll();
    repaint();
    getContentPane().add(toolbar, BorderLayout.PAGE_START);

    JLabel myLabel = new JLabel();
    myLabel.setText("");
    myLabel.repaint();
    String contents = "";
    myLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
    add(myLabel, BorderLayout.CENTER);
    myLabel.setSize(400, 300);
    myLabel.setHorizontalAlignment(JLabel.LEFT);
    myLabel.setVerticalAlignment(JLabel.TOP);
    setVisible(true);
    if (obj.equals(Record.CUSTOMER)) {
      contents = "<html><p><font size=+2>Customer</font></p></br><ol>";
      for (Object x : storee.customers()) {
        Customer c = (Customer) x;
        contents += "<li>" + c.toString() + "</li>";
      }
      contents += "</ol></html>";
    }
    if (obj.equals(Record.OPTION)) {
      contents = "<html><p><font size=+2>Option</font></p></br><ol>";
      for (Object x : storee.options()) {
        Option c = (Option) x;
        contents += "<li>" + c.toString() + "</li>";
      }
      contents += "</ol></html>";
    }

    if (obj.equals(Record.ORDER)) {
      contents = "<html><p><font size=+2>Order</font></p></br><ol>";
      for (Object x : storee.orders()) {
        Order c = (Order) x;
        contents += "<li>" + c.toString().replaceAll("\n", "<br/>").replaceAll("\t", "&emsp;") + "</li>";
      }
      contents += "</ol></html>";
    }
    if (obj.equals(Record.COMPUTER)) {
      contents = "<html><p><font size=+2>Computer</font></p></br><ol>";
      for (Object x : storee.computers()) {
        Computer c = (Computer) x;
        contents +=
          c.toString().replaceAll("\n", "<br/>").replaceAll("\t", "&emsp;");
      }
      contents += "</ol></html>";
    }
    myLabel.setText(contents);
    //
  }

  protected void onAboutClick() {
    //
    JFrame about = new JFrame();
    Canvas newpanel = new Canvas();
    about.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    about.add(newpanel);
    about.pack();
    about.setVisible(true);
    about.setSize(600, 800);
    about.setLocationRelativeTo(null);

    //testtttt

    JLabel title = new JLabel(
      "<html>" +
      "<br/><br/><br/><br/><p><font size=+4>E           L            S             A</font></p>" +
      "<br/><p>Exceptional Laptops and Supercomputers Always<br/>Version 0.3</p>" +
      "<br/><p>Copyright 2017-2023 by Ribesh Joshi</p>" +
      "<p>Licensed under Gnu GPL 3.0</p><br/>" +
      "<p>Save and SaveAs icon by brandcrowd, licensed under brandcrowd</p>" +
      "<p><font size=-2>https://www.brandcrowd.com/maker/logo</font></p>" +
      "<p>All other Icons by icons8.com, licensed for free use</p>" +
      "<p><font size=-2>https://icons8.com/icons</font></p>" +
      "</html>",
      SwingConstants.CENTER
    );
    about.add(title);
  }

  protected void onQuitClick() {
    System.exit(0);
  } // Exit the store

  private JLabel msg; // Status message display
  private JLabel display;
  private Store storee;
  private File filename;
  private JButton savee;
  private JButton saveass;
  private JButton newW;
  private JButton openn;
  private JButton insertComputer;
  private JButton insertCustomer;
  private JButton insertOption;
  private JButton insertOrder;
  private JButton viewComputer;
  private JButton viewCustomer;
  private JButton viewOption;
  private JButton viewOrder;
  private JToolBar toolbar;
}
