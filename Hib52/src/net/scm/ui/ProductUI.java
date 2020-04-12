package net.scm.ui;
import net.scm.model.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import javax.swing.*;
import java.util.List;
import java.util.Locale;

import org.hibernate.stat.spi.StatisticsImplementor;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

import net.scm.ui.Product;

public class ProductUI extends JDialog
{
	private static final long serialVersionUID = 1L;
	public static final String RUPEE = "\u20B9";
	public JFrame parent1;
	public Session session;

    public JTextField tfProdName;
    public JTextField tfPrice;
    public JComboBox tfIndustry;
    public JComboBox tfMake;
    public JComboBox<Country> tfCountry;
    public JButton btnSave;
    public JButton btnReset;
    public JButton btnMenu;
    public JButton btnModify;
    public JButton btnDelete;
    public String  insProdName; 
    public String  insProdId;
    public Integer insId;
	
    
    //Constructor 
	public ProductUI(JFrame parent)
	{
		super(parent, "Vendor Management System", true);
		parent1=parent;
	}
	
    //Method to display UI for accepting Product inputs from User to Save into the DB  
    public void ProductAddUI()
    {   	

    	//Create Panel for Title , Form(Content), Bottom(Button Ops) and Add Label for Title
    	JPanel title     = FormatHelper.getFormattedTitlePanel();   
    	JPanel formpanel = FormatHelper.getFormattedFormPanel();        
	    JPanel bp        = FormatHelper.getFormattedBpPanel();  
	    FormatHelper.setTitleLabel(title, "Vendor Management Portal >> Create >> Product" );

	    //Create and Add Labels to formpanel 
	    FormatHelper.setFormattedFormLabel(formpanel, "ID",                    0, 0,14);
	    FormatHelper.setFormattedFormLabel(formpanel, "Name",                  0, 3,14);
	    FormatHelper.setFormattedFormLabel(formpanel, "Price( "+ RUPEE +" )",  0, 5,14);
	    FormatHelper.setFormattedFormLabel(formpanel, "Industry",              0, 7,14);
	    FormatHelper.setFormattedFormLabel(formpanel, "Fuel Type",             0, 9,14);
	    FormatHelper.setFormattedFormLabel(formpanel, "Supplied To",           0,11,14);
	    
	    //Create and Add TextFields to formpanel 
		JTextField tfProdID   =FormatHelper.getFormattedTextField(14);
		JTextField tfProdName =FormatHelper.getFormattedTextField(14);
		JTextField tfPrice    =FormatHelper.getFormattedTextField(14);
		
		//Create and Add Assign grid constraints for Text Fields  
		GridBagConstraints gbc_prodID      =FormatHelper.getFormattedTFgbc(1,0);
		GridBagConstraints gbc_prodName    =FormatHelper.getFormattedTFgbc(1,3);
		GridBagConstraints gbc_prodPrice   =FormatHelper.getFormattedTFgbc(1,5);
		GridBagConstraints gbc_prodIndustry=FormatHelper.getFormattedTFgbc(1,7);
		GridBagConstraints gbc_prodMake    =FormatHelper.getFormattedTFgbc(1,9);
		GridBagConstraints gbc_prodCountry =FormatHelper.getFormattedTFgbc(1,11);
					
		JComboBox tfRating = new JComboBox();
		tfRating.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfRating.setModel(new DefaultComboBoxModel(new String[] {"None", "Automotive", "Aircraft", "Home Appliances", "Toys", "Pharmacy", "Electronics", "Farm Machinery" }));
	
		JComboBox tfMake = new JComboBox();
		tfMake.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfMake.setModel(new DefaultComboBoxModel(new String[] {"None","Petrol", "Diesel", "Electric", "Hybrid"}));
		
	    Country[] listCountry = createCountryList();	   
	    JComboBox<Country> tfCountry = new JComboBox<>(listCountry);
	    tfCountry.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    
        //Add all the Text Fields 	
		formpanel.add(tfProdID, gbc_prodID);
		formpanel.add(tfProdName, gbc_prodName);
		formpanel.add(tfPrice, gbc_prodPrice);
		formpanel.add(tfRating, gbc_prodIndustry);
		formpanel.add(tfMake, gbc_prodMake);
		formpanel.add(tfCountry, gbc_prodCountry); 
		 //Add all the Buttons to Bottom Panel 
        btnSave = new JButton("Save"); btnReset = new JButton("Reset"); btnMenu   = new JButton("Back to Menu");
		 
        //Action Listener for Save Button 
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
        //Action Listener for Main Menu Button 
        btnMenu.addActionListener(new ActionListener() 
        {
       	 
           public void actionPerformed(ActionEvent e) {
                dispose();
            	MainMenuHelper mmframe=new MainMenuHelper(parent1);
            	mmframe.MainMenuUI();
         		mmframe.setVisible(true);
            }
        });
        //Action Listener for Rest Button 
        btnReset.addActionListener(new ActionListener() 
        {        	 
            public void actionPerformed(ActionEvent e) 
            {
            	tfProdID.setText("");
            	tfProdName.setText("");
            	tfPrice.setText("");
            	tfRating.setSelectedIndex(0);
            	tfMake.setSelectedIndex(0);
            	tfCountry.setSelectedIndex(0);
            }
        });
        
        bp.add(btnSave); bp.add(btnReset); bp.add(btnMenu);
  
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
    	//Create Panel for Title , Table(Content), Bottom(Button Ops) and Add Label for Title
    	JPanel title = FormatHelper.getFormattedTitlePanel();     
    	JPanel tblpanel = FormatHelper.getFormattedTablePanel(); 
	    JPanel bp = FormatHelper.getFormattedBpPanel();  
	    FormatHelper.setTitleLabel(title, "Vendor Management Portal >> View >> Product" );
	    
    	List<ProductModel> Products;
    	{
     		SessionFactory sessFact = HibernateUtil.getSessionFactory();
    		session = sessFact.getCurrentSession();
    		org.hibernate.Transaction tr = session.beginTransaction();
			Criteria criteria = session.createCriteria(ProductModel.class);
    		Products = criteria.list();
    		tr.commit();
    		
    		/* 
    		Iterator<ProductModel> itr = Products.iterator();
    		while (itr.hasNext()) {
    			ProductModel prodM = itr.next();
      		    System.out.println(prodM.getProdName());  System.out.println(prodM.getId());      System.out.println(prodM.getProdPrice());
    			System.out.println(prodM.getProdRating());System.out.println(prodM.getProdMake());System.out.println(prodM.getProdCountry()); 
    		}*/
    		//System.out.println("Data displayed for Product");
    		//sessFact.close();
    	}
    	
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
        tableHeader.setBackground(Color.decode("#808000"));tableHeader.setForeground(Color.WHITE);
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
    
    //Method to Update/Delete selected Products from the DB 
    public void ProductManageUI()
    {   	
    	//Create Panel for Title , Table(Content), Bottom(Button Ops) and Add Label for Title
    	JPanel title = FormatHelper.getFormattedTitlePanel();     
    	JPanel selpanel = FormatHelper.getFormattedSelectPanel(); 
    	JPanel editpanel = FormatHelper.getFormattedEditPanel(); 
	    JPanel bp = FormatHelper.getFormattedBpPanel();  
	    FormatHelper.setTitleLabel(title, "Vendor Management Portal >> View >> Product" );
	    
	    FormatHelper.setFormattedFormLabel(selpanel, "Select Product to Edit",  0, 0,12);
	
		SessionFactory sessFact1 = HibernateUtil.getSessionFactory();
		session = sessFact1.getCurrentSession();
		org.hibernate.Transaction tr1 = session.beginTransaction();
		CriteriaBuilder builder1 = session.getCriteriaBuilder();

        javax.persistence.criteria.CriteriaQuery<String> query1 = builder1.createQuery(String.class);
	    Root<ProductModel> root1 = query1.from(ProductModel.class); 
        query1.select(root1.get("prodName"));
        Query<String> q1=session.createQuery(query1);
        List<String> prodNameList=q1.getResultList();
        tr1.commit();
        //for (String name : partNameList) {
        //    System.out.println(name);
        //}
        
        String[] prodStrArr = new String[prodNameList.size()];
        prodNameList.toArray(prodStrArr );
        
	    JComboBox<String> tfProdName = new JComboBox<String>();
		tfProdName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfProdName.setModel(new DefaultComboBoxModel<String>(prodStrArr));
		GridBagConstraints gbc_prodName    =FormatHelper.getFormattedTFgbc(0,1);
		selpanel.add(tfProdName, gbc_prodName);
        
        //create the button component to return to main menu 
       	btnModify = new JButton("Modify"); btnDelete = new JButton("Delete");
       	btnMenu   = new JButton("Back to Menu"); 
       	bp.add(btnModify);bp.add(btnDelete);bp.add(btnMenu);
       	
       	
	    // Create an ActionListener for the PRODUCT JComboBox component.
		tfProdName.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent event) {
                JComboBox<String> tfProdName = (JComboBox<String>)event.getSource();
                Object selected = tfProdName.getSelectedItem();
                System.out.println("Selected Item  = " + selected);
                insProdName = (String) selected;
                
        		SessionFactory sessFact = HibernateUtil.getSessionFactory();
        		session = sessFact.getCurrentSession();
        		org.hibernate.Transaction tr = session.beginTransaction();

        		CriteriaBuilder builder = session.getCriteriaBuilder();
                javax.persistence.criteria.CriteriaQuery<ProductModel> query = builder.createQuery(ProductModel.class);
                Root<ProductModel> root = query.from(ProductModel.class);    
                query.select(root).where(builder.equal(root.get("prodName"),selected));  
                Query<ProductModel> q=session.createQuery(query);             
                ProductModel product=q.getSingleResult();
                //System.out.println(product.getProdName());               

                tr.commit();
        		insProdId = product.getProdId();
        		insProdName = product.getProdName();
        		insId = product.getId();
                
        	    FormatHelper.setFormattedFormLabel(editpanel, "Product ID",            0, 0,12);
        	    FormatHelper.setFormattedFormLabel(editpanel, "Name",                  0, 3,12);
        	    FormatHelper.setFormattedFormLabel(editpanel, "Price( "+ RUPEE +" )",  0, 5,12);
        	    FormatHelper.setFormattedFormLabel(editpanel, "Industry",              0, 7,12);
        	    FormatHelper.setFormattedFormLabel(editpanel, "Fuel Type",             0, 9,12);
        	    FormatHelper.setFormattedFormLabel(editpanel, "Supplied To",           0,11,12);
                //Non Editable entries , as they are dependent values in other tables
        	    FormatHelper.setFormattedFormLabel(editpanel, product.getProdId(),            1, 0,12);
           	    FormatHelper.setFormattedFormLabel(editpanel, product.getProdName(),          3, 0,12);
           	    
 
        		
        		//Create and Add Assign grid constraints for Text Fields  
        		GridBagConstraints gbc_prodID      =FormatHelper.getFormattedTFgbc(1,0);
        		GridBagConstraints gbc_prodName    =FormatHelper.getFormattedTFgbc(1,3);
        		GridBagConstraints gbc_prodPrice   =FormatHelper.getFormattedTFgbc(1,5);
        		GridBagConstraints gbc_prodIndustry=FormatHelper.getFormattedTFgbc(1,7);
        		GridBagConstraints gbc_prodMake    =FormatHelper.getFormattedTFgbc(1,9);
        		GridBagConstraints gbc_prodCountry =FormatHelper.getFormattedTFgbc(1,11);
      		
           		tfPrice    =FormatHelper.getFormattedTextField(12);
        		tfPrice.setText(Integer.toString(product.getProdPrice()));
 
        		
        		tfIndustry = new JComboBox();
        		tfIndustry.setFont(new Font("Tahoma", Font.PLAIN, 12));
        		tfIndustry.setModel(new DefaultComboBoxModel(new String[] {"None", "Automotive", "Aircraft", "Home Appliances", "Toys", "Pharmacy", "Electronics", "Farm Machinery" }));
        		ArrayList<String> listIndustry = new ArrayList<>(Arrays.asList("None", "Automotive", "Aircraft", "Home Appliances", "Toys", "Pharmacy", "Electronics", "Farm Machinery"));
        		int indexI = listIndustry.indexOf(product.getProdRating());
        		System.out.println("Selected Industry is: "+ product.getProdRating());
        		System.out.println("Selected Industry index is: "+ indexI);
        		tfIndustry.setSelectedIndex(indexI);

        	
        		tfMake = new JComboBox();
        		tfMake.setFont(new Font("Tahoma", Font.PLAIN, 12));
        		tfMake.setModel(new DefaultComboBoxModel(new String[] {"None","Petrol", "Diesel", "Electric", "Hybrid"}));
        		ArrayList<String> listMake = new ArrayList<>(Arrays.asList("None","Petrol", "Diesel", "Electric", "Hybrid"));
        		int indexM = listMake.indexOf(product.getProdMake());
        		System.out.println("Selected Make is: "+ product.getProdMake());
        		System.out.println("Selected Make index is: "+ indexM);
        		tfMake.setSelectedIndex(indexM);
        		
        	    Country[] listCountry = createCountryList();	   
        	    tfCountry = new JComboBox<>(listCountry);
        	    tfCountry.setFont(new Font("Tahoma", Font.PLAIN, 12));
        	    int indexC = getCountryIndex(product.getProdCountry(), listCountry);
        	    tfCountry.setSelectedIndex(indexC);
        	    	
           		editpanel.add(tfPrice, gbc_prodPrice);
           		editpanel.add(tfIndustry, gbc_prodIndustry);
           		editpanel.add(tfMake, gbc_prodMake);
           		editpanel.add(tfCountry, gbc_prodCountry);
        		
                editpanel.removeAll();
                editpanel.invalidate();
        	    FormatHelper.setFormattedFormLabel(editpanel, "Product ID",            0, 0,12);
        	    FormatHelper.setFormattedFormLabel(editpanel, "Name",                  0, 3,12);
        	    FormatHelper.setFormattedFormLabel(editpanel, "Price( "+ RUPEE +" )",  0, 5,12);
        	    FormatHelper.setFormattedFormLabel(editpanel, "Industry",              0, 7,12);
        	    FormatHelper.setFormattedFormLabel(editpanel, "Fuel Type",             0, 9,12);
        	    FormatHelper.setFormattedFormLabel(editpanel, "Supplied To",           0,11,12);
        	    FormatHelper.setFormattedFormLabel(editpanel, product.getProdId(),     1, 0,12);
           	    FormatHelper.setFormattedFormLabel(editpanel, product.getProdName(),   1, 3,12);
           		editpanel.add(tfPrice, gbc_prodPrice);
        		editpanel.add(tfIndustry, gbc_prodIndustry);
        		editpanel.add(tfMake, gbc_prodMake);
        		editpanel.add(tfCountry, gbc_prodCountry);
        		editpanel.revalidate();
        		editpanel.repaint();
            }
        });
		
        //Action Listener for Modify Button 
        btnModify.addActionListener(new ActionListener() 
        {        	 
            public void actionPerformed(ActionEvent e) 
            {
        		System.out.println("! You have chosen Manage->Product->Modify !!!");  
         		SessionFactory sessFact = HibernateUtil.getSessionFactory();
        		session = sessFact.getCurrentSession();
        		org.hibernate.Transaction tr = session.beginTransaction();
        		ProductModel prodModel = new ProductModel();
        		prodModel.setId(insId);
        		prodModel.setProdId(insProdId);
        		prodModel.setProdName(insProdName);
        		prodModel.setProdPrice(Integer.parseInt(tfPrice.getText()));
        		prodModel.setProdRating(tfIndustry.getSelectedItem().toString());
        		prodModel.setProdMake(tfMake.getSelectedItem().toString());
        		prodModel.setProdCountry(tfCountry.getSelectedItem().toString());
        		session.update(prodModel);
        		tr.commit();
        		System.out.println("Successfully Modified Product Info");        		
        		//sessFact.close();
            }
        });
        
        //Action Listener for Delete Button 
        btnDelete.addActionListener(new ActionListener() 
        {        	 
            public void actionPerformed(ActionEvent e) 
            {
        		System.out.println("! You have chosen Manage->Product->Delete !!!");  
         		SessionFactory sessFact = HibernateUtil.getSessionFactory();
        		session = sessFact.getCurrentSession();
        		org.hibernate.Transaction tr = session.beginTransaction();	
        		
                CriteriaBuilder cb = session.getCriteriaBuilder();
                CriteriaDelete<ProductModel> deleteProd = cb.createCriteriaDelete(ProductModel.class); // create delete 
                Root<ProductModel> prod = deleteProd.from(ProductModel.class);                                       // set the root class
                deleteProd.where(cb.equal(prod.get("prodName"), insProdName));                         // set where clause
                session.createQuery(deleteProd).executeUpdate();                                       // perform update
        		System.out.println("Successfully Deleted from Product Table ");    
                
                CriteriaBuilder cb1 = session.getCriteriaBuilder();
                CriteriaDelete<BoMModel> deleteBoM = cb1.createCriteriaDelete(BoMModel.class);
                Root<BoMModel> bom = deleteBoM.from(BoMModel.class);
                deleteBoM.where(cb1.equal(bom.get("bomProdName"), insProdName));
                session.createQuery(deleteBoM).executeUpdate();
           		System.out.println("Successfully Deleted from BoM Table ");  
           		
                CriteriaBuilder cb2 = session.getCriteriaBuilder();
                CriteriaDelete<ProdCycleModel> deleteProdCyc = cb2.createCriteriaDelete(ProdCycleModel.class);
                Root<ProdCycleModel> prodCyc = deleteProdCyc.from(ProdCycleModel.class);
                deleteProdCyc.where(cb2.equal(prodCyc.get("prodcycProd"), insProdName));
                session.createQuery(deleteProdCyc).executeUpdate();
           		System.out.println("Successfully Deleted from ProdCycle Table ");   
        		tr.commit();      		
        		//sessFact.close();
            }
        });
       	
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
  
        getContentPane().add(title, BorderLayout.PAGE_START);
        getContentPane().add(selpanel, BorderLayout.LINE_START);
        getContentPane().add(editpanel, BorderLayout.LINE_END);
        getContentPane().add(bp, BorderLayout.PAGE_END);
        pack();
        setResizable(true);
        setLocationRelativeTo(parent1);
        
 
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
    
    private int getCountryIndex(String Country, Country[] listCountry) {
        String[] countryCodes = Locale.getISOCountries();
        int i;
        for (i = 0; i < countryCodes.length; i++) 
        {
        	//System.out.println(i+":"+ " "+listCountry[i].getName()+ " : "+ Country);
        	if (listCountry[i].getName().equals(Country))
        	{
        		break;
        	}
        }
		return i;
    } // End of getCountryIndex()
} //End of Class Product()