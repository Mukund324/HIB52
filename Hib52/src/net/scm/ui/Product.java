package net.scm.ui;
import net.scm.model.*;

import javax.swing.*;
import java.util.List;
import java.util.Locale;

import org.hibernate.stat.spi.StatisticsImplementor;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import net.scm.model.HibernateUtil;
import net.scm.model.ProductModel;

import net.scm.ui.Product;

public class Product extends JDialog
{
	private static final long serialVersionUID = 1L;
	public static final String RUPEE = "\u20B9";
	public JFrame parent1;
	public Session session;

	public JLabel lbProdName;
	public JTextField tfProdName;
    public JLabel lbPrice;
    public JTextField tfPrice;
    public JLabel lbRating;
    public JTextField tfRating;
    public JLabel lbMake;
    public JTextField tfMake;
    public JLabel lbMakeCountry;
    public JTextField tfCountry;
    public JButton btnSave;
    public JButton btnReset;
    public JButton btnMenu;
    
    //Constructor 
	public Product(JFrame parent)
	{
		super(parent, "Vendor Management System", true);
		parent1=parent;
	}
	
    //Method to display UI for accepting Product inputs from User to Save into the DB  
    public void ProductAddUI()
    {   	

    	//Create Panel for Title 
    	JPanel title = new JPanel();
    	title.setBackground(Color.decode("#006666"));
    	title.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
	    title.setPreferredSize(new Dimension(720,30));
        title.setOpaque(true);
       	//Create Panel for Menu       
    	JPanel formpanel = new JPanel(new GridBagLayout());
        formpanel.setPreferredSize(new Dimension(720, 400));   
		formpanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
		formpanel.setBackground(Color.white);
		GridBagLayout gBL = new GridBagLayout();
		gBL.columnWidths = new int[]{100, 67, 86, 86, 0};
		gBL.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gBL.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gBL.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		
       	//Create Panel for Bottom (Adding Buttons for Operations)               
	    JPanel bp = new JPanel();
        bp.setBackground(Color.decode("#87CEFA"));
        bp.setPreferredSize(new Dimension(720, 40));  
    	title.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
        bp.setOpaque(true);
        
    	//Adding Components for the Title Panel    
	    JLabel ltitle = new JLabel("Vendor Management Portal >> Create >> Product");
	    ltitle.setHorizontalAlignment(JLabel.CENTER);
	    ltitle.setVerticalAlignment(JLabel.CENTER);
	    ltitle.setFont(new Font("Arial",Font.TRUETYPE_FONT,16));
	    ltitle.setForeground(Color.WHITE);
	    title.add(ltitle); 

		JLabel lblProdID = new JLabel("ID");
		lblProdID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblProdID = new GridBagConstraints();
		gbc_lblProdID.anchor = GridBagConstraints.EAST;
		gbc_lblProdID.insets = new Insets(10, 10, 10, 5);
		gbc_lblProdID.gridx = 0;
		gbc_lblProdID.gridy = 1;
		formpanel.add(lblProdID, gbc_lblProdID);
		
		JTextField tfProdID = new JTextField();
		tfProdID.setText(" ");
		tfProdID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_prodID = new GridBagConstraints();
		gbc_prodID.gridwidth = 2;
		gbc_prodID.insets = new Insets(10, 10, 10, 5);
		gbc_prodID.anchor = GridBagConstraints.NORTHWEST;
		gbc_prodID.gridx = 1;
		gbc_prodID.gridy = 1;
		formpanel.add(tfProdID, gbc_prodID);
		tfProdID.setColumns(30);
	    
		JLabel lblProdName = new JLabel("Name");
		lblProdName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblProdName = new GridBagConstraints();
		gbc_lblProdName.anchor = GridBagConstraints.EAST;
		gbc_lblProdName.insets = new Insets(10, 10, 10, 5);
		gbc_lblProdName.gridx = 0;
		gbc_lblProdName.gridy = 3;
		formpanel.add(lblProdName, gbc_lblProdName);
		
		JTextField tfProdName = new JTextField();
		tfProdName.setText(" ");
		tfProdName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_prodName = new GridBagConstraints();
		gbc_prodName.gridwidth = 2;
		gbc_prodName.insets = new Insets(10, 10, 10, 5);
		gbc_prodName.anchor = GridBagConstraints.NORTHWEST;
		gbc_prodName.gridx = 1;
		gbc_prodName.gridy = 3;
		formpanel.add(tfProdName, gbc_prodName);
		tfProdName.setColumns(30);
		
		JLabel lbPrice = new JLabel("Price(" + RUPEE +")");
		lbPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblProductPrice = new GridBagConstraints();
		gbc_lblProductPrice.anchor = GridBagConstraints.EAST;
		gbc_lblProductPrice.insets = new Insets(10, 10, 10, 5);
		gbc_lblProductPrice.gridx = 0;
		gbc_lblProductPrice.gridy = 5;
		formpanel.add(lbPrice, gbc_lblProductPrice);
		
		JTextField tfPrice = new JTextField();
		tfPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(10, 10, 10, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 5;
		formpanel.add(tfPrice, gbc_textField);
		tfPrice.setColumns(10);
		
		JLabel lbRating = new JLabel("Industry");
		lbRating.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblProdRating = new GridBagConstraints();
		gbc_lblProdRating.anchor = GridBagConstraints.EAST;
		gbc_lblProdRating.insets = new Insets(10, 10, 10, 5);
		gbc_lblProdRating.gridx = 0;
		gbc_lblProdRating.gridy = 7;
		formpanel.add(lbRating, gbc_lblProdRating);
		
		JComboBox tfRating = new JComboBox();
		tfRating.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfRating.setModel(new DefaultComboBoxModel(new String[] {"None", "Automotive", "Aircraft", "Home Appliances",
															     "Toys", "Pharmacy", "Electronics", "Farm Machinery"
																}));
		GridBagConstraints gbc_ProdRating = new GridBagConstraints();
		gbc_ProdRating.gridwidth = 2;
		gbc_ProdRating.insets = new Insets(10, 10, 10, 5);
		gbc_ProdRating.fill = GridBagConstraints.HORIZONTAL;
		gbc_ProdRating.gridx = 1;
		gbc_ProdRating.gridy = 7;
		formpanel.add(tfRating, gbc_ProdRating);
		
		JLabel lbMake = new JLabel("Fuel Type");
		lbMake.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblProdMake = new GridBagConstraints();
		gbc_lblProdMake.anchor = GridBagConstraints.EAST;
		gbc_lblProdMake.insets = new Insets(10, 10, 10, 5);
		gbc_lblProdMake.gridx = 0;
		gbc_lblProdMake.gridy = 9;
		formpanel.add(lbMake, gbc_lblProdMake);
		
		JComboBox tfMake = new JComboBox();
		tfMake.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfMake.setModel(new DefaultComboBoxModel(new String[] {"Petrol", "Diesel", "Electric", "Hybrid"}));
		GridBagConstraints gbc_ProdMakeSelect = new GridBagConstraints();
		gbc_ProdMakeSelect.gridwidth = 2;
		gbc_ProdMakeSelect.insets = new Insets(10, 10, 10, 5);
		gbc_ProdMakeSelect.fill = GridBagConstraints.HORIZONTAL;
		gbc_ProdMakeSelect.gridx = 1;
		gbc_ProdMakeSelect.gridy = 9;
		formpanel.add(tfMake, gbc_ProdMakeSelect);
		
		JLabel lbCountry = new JLabel("Supplied To");
		lbCountry.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblProdCountry = new GridBagConstraints();
		gbc_lblProdCountry.anchor = GridBagConstraints.EAST;
		gbc_lblProdCountry.insets = new Insets(10, 10, 10, 5);
		gbc_lblProdCountry.gridx = 0;
		gbc_lblProdCountry.gridy =11;
		formpanel.add(lbCountry, gbc_lblProdCountry);
		
	    Country[] listCountry = createCountryList();
	   
	    JComboBox<Country> tfCountry = new JComboBox<>(listCountry);
	    tfCountry.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(10, 10, 10, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 11;
		formpanel.add(tfCountry, gbc_comboBox);   	
        
        btnSave = new JButton("Save");
        btnReset = new JButton("Reset");
        btnMenu   = new JButton("Back to Menu");
      
        btnMenu.addActionListener(new ActionListener() {
        	 
            public void actionPerformed(ActionEvent e) {
                dispose();
            	MainMenuHelper mmframe=new MainMenuHelper(parent1);
            	mmframe.MainMenuUI();
         		mmframe.setVisible(true);
            }
        });
        
        btnSave.addActionListener(new ActionListener() 
        {        	 
            public void actionPerformed(ActionEvent e) 
            {
        		System.out.println("! You have chosen Create->Product->Save !!!");  
         		SessionFactory sessFact = HibernateUtil.getSessionFactory();
        		session = sessFact.getCurrentSession();
        		org.hibernate.Transaction tr = session.beginTransaction();
        		ProductModel prodModel = new ProductModel();
        		prodModel.setProdId(tfProdID.getText());
        		prodModel.setProdName(tfProdName.getText());
        		prodModel.setProdPrice(Integer.parseInt(tfPrice.getText()));
        		prodModel.setProdRating(tfRating.getSelectedItem().toString());
        		prodModel.setProdMake(tfMake.getSelectedItem().toString());
        		prodModel.setProdCountry(tfCountry.getSelectedItem().toString());
        		session.save(prodModel);
        		tr.commit();
        		System.out.println("Successfully inserted Product Info");        		
        		//sessFact.close();
            }
        });
        
        btnReset.addActionListener(new ActionListener() 
        {        	 
            public void actionPerformed(ActionEvent e) 
            {
            	dispose();
            	MainMenuHelper mmframe=new MainMenuHelper(parent1);
            	mmframe.MainMenuUI();
        		mmframe.setVisible(true);
            }
        });
        
        bp.add(btnSave);
        bp.add(btnReset);
        bp.add(btnMenu);
  
        getContentPane().add(title, BorderLayout.NORTH);
        getContentPane().add(formpanel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.SOUTH);
        pack();
        setResizable(true);
        setLocationRelativeTo(parent1);
        
     } //End of ProductUI()
    
 //Method to display all the Products from the DB 
    public void ProductListAllUI()
    {   	
    	List<ProductModel> Products;
    	JPanel panel = new JPanel();
    	panel.setLayout(new BorderLayout());

    	GridBagConstraints gs = new GridBagConstraints();
    	gs.fill = GridBagConstraints.HORIZONTAL;
    	
    	{
     		SessionFactory sessFact = HibernateUtil.getSessionFactory();
    		session = sessFact.getCurrentSession();
    		org.hibernate.Transaction tr = session.beginTransaction();

			Criteria criteria = session.createCriteria(ProductModel.class);
    		Products = criteria.list();

    		Iterator<ProductModel> itr = Products.iterator();
    		 /* 
    		while (itr.hasNext()) {
    			ProductModel prodM = itr.next();
      		    System.out.println(prodM.getProdName());
    			System.out.println(prodM.getId());
    			System.out.println(prodM.getProdPrice());
    			System.out.println(prodM.getProdRating());
    			System.out.println(prodM.getProdMake());
       			System.out.println(prodM.getProdCountry());
       		 
    		}*/
    		tr.commit();
    		//System.out.println("Data displayed for Product");

    		//sessFact.close();
    	}
    	
    	JPanel title = new JPanel();
    	title.setBackground(Color.decode("#006666"));
    	title.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
	    title.setPreferredSize(new Dimension(720,30));
        title.setOpaque(true);
        
       	//Create Panel for Menu       
    	JPanel tblpanel = new JPanel(new BorderLayout());
        tblpanel.setPreferredSize(new Dimension(720, 320));   
		tblpanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
		tblpanel.setBackground(Color.white);
		
       	//Create Panel for Bottom (Adding Buttons for Operations)               
	    JPanel bp = new JPanel();
        bp.setBackground(Color.decode("#87CEFA"));
        bp.setPreferredSize(new Dimension(720, 30));  
    	title.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
        bp.setOpaque(true);
        
	    JLabel ltitle = new JLabel("Vendor Management Portal >> View >> Products");
	    ltitle.setHorizontalAlignment(JLabel.CENTER);
	    ltitle.setVerticalAlignment(JLabel.CENTER);
	    ltitle.setFont(new Font("Arial",Font.TRUETYPE_FONT,16));
	    ltitle.setForeground(Color.WHITE);
	    title.add(ltitle);    
    	
    	
        JPanel hp = new JPanel();
        hp.setLayout(new BorderLayout());
            	
       	JLabel lblHeading = new JLabel("List of Products", SwingConstants.CENTER);
       	lblHeading.setFont(new Font("Arial",Font.TRUETYPE_FONT,15));
       	lblHeading.setForeground (Color.green);
       	lblHeading.setBackground (Color.white);
       	lblHeading.setOpaque(true); 
       	hp.add(lblHeading,BorderLayout.CENTER);
       	
    	//create the model
        ProductTableModel model = new ProductTableModel(Products);
        
        //create the table
        JTable table = new JTable(model);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i=0; i<table.getColumnCount();i++){
            table.setDefaultRenderer(table.getColumnClass(i),renderer);
        }
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(Color.decode("#808000"));
        tableHeader.setForeground(Color.WHITE);
    	tblpanel.add(table, BorderLayout.CENTER);
    	tblpanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
        JScrollPane scrlpane = new JScrollPane(table);
        scrlpane.setSize(new Dimension(720,320));
        scrlpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrlpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        tblpanel.add(scrlpane, BorderLayout.CENTER);     

        //create the button component to return to main menu 
       	btnMenu   = new JButton("Back to Menu");
        bp.add(btnMenu);
  
        getContentPane().add(title, BorderLayout.PAGE_START);
        getContentPane().add(hp, BorderLayout.LINE_START);
        getContentPane().add(tblpanel, BorderLayout.LINE_START);
        getContentPane().add(bp, BorderLayout.PAGE_END);
        pack();
        setResizable(true);
        setLocationRelativeTo(parent1);
        
        btnMenu.addActionListener(new ActionListener() 
        {     	 
            public void actionPerformed(ActionEvent e) 
            {
            	dispose();
            	MainMenuHelper mmframe=new MainMenuHelper(parent1);
            	mmframe.MainMenuUI();
        		mmframe.setVisible(true);
            }
        });
     } //End of ProductListAllUI()
    
//Method to display all the Countries     
    private Country[] createCountryList() {
        String[] countryCodes = Locale.getISOCountries();
        Country[] listCountry = new Country[countryCodes.length];
 
        for (int i = 0; i < countryCodes.length; i++) {
            Locale locale = new Locale("", countryCodes[i]);
            String code = locale.getCountry();
            String name = locale.getDisplayCountry();
 
            listCountry[i] = new Country(code, name);
        }
 
        Arrays.sort(listCountry);
 
        return listCountry;
    } // End of createCountryList()
 
} //End of Class Product()

