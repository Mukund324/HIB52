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

public class VendorUI extends JDialog
{
	public JFrame parent1;
	public Session session;
	
	
	public JTextField tfVendID;
	public JTextField tfVendName;
	public JTextField tfVendAddr1;
	public JTextField tfVendAddr2;
	public JTextField tfVendCity;
	public JComboBox<Country> tfVendCountry;
	public JComboBox<String> tfVendNameCh;
	public JTextField tfVendPin;
	public JTextField tfVendProfile;
	public JTextField tfVendTin;
	public JButton btnSave;
	public JButton btnReset;
	public JButton btnModify;
    public JButton btnDelete;
    public String  insvendName; 
    public String  insVendorId;
    public Integer insId;
	public JButton btnMenu;
	
	public VendorUI(JFrame parent)
	{
		super(parent, "Vendor Management System", true);
		parent1=parent;
	}
	public void VendorAddUI()
    {   	

		//Create Panel for Title , Form(Content), Bottom(Button Ops) and Add Label for Title
		JPanel title     = FormatHelper.getFormattedTitlePanel();       
		JPanel formpanel = FormatHelper.getFormattedFormPanel(); 
		JPanel bp        = FormatHelper.getFormattedBpPanel(); 
		FormatHelper.setTitleLabel(title, "Vendor Management Portal >> Create >> Vendor" );            
	       
		 //Create and Add Labels to formpanel 
		FormatHelper.setFormattedFormLabel(formpanel, "ID",                    0, 0,14);
		FormatHelper.setFormattedFormLabel(formpanel, "Name",                  0, 1,14);
		FormatHelper.setFormattedFormLabel(formpanel, "Bldg/Plot #",	       0, 2,14);
		FormatHelper.setFormattedFormLabel(formpanel, "State",                 0, 3,14);
		FormatHelper.setFormattedFormLabel(formpanel, "CITY",                  0, 4,14);
		FormatHelper.setFormattedFormLabel(formpanel, "COUNTRY",               0, 5,14);
		FormatHelper.setFormattedFormLabel(formpanel, "PIN/ZIP",               0, 6,14);
		FormatHelper.setFormattedFormLabel(formpanel, "Product Profile",       0, 7,14);
		FormatHelper.setFormattedFormLabel(formpanel, "TAX ID NO",             0, 8,14);
		
		 //Create and Add TextFields to formpanel 
		 tfVendID   	 =FormatHelper.getFormattedTextField(14);
		 tfVendName 	 =FormatHelper.getFormattedTextField(14);
		 tfVendAddr1     =FormatHelper.getFormattedTextField(14);
		 tfVendAddr2     =FormatHelper.getFormattedTextField(14);
		 tfVendCity      =FormatHelper.getFormattedTextField(14);
		 tfVendPin       =FormatHelper.getFormattedTextField(14);
		 tfVendProfile   =FormatHelper.getFormattedTextField(14);
		 tfVendTin    	 =FormatHelper.getFormattedTextField(14);
		 
		//Create and Add Assign grid constraints for Text Fields  
		GridBagConstraints gbc_vendID      =FormatHelper.getFormattedTFgbc(1,0);
		GridBagConstraints gbc_vendName    =FormatHelper.getFormattedTFgbc(1,1);
		GridBagConstraints gbc_vendAddr1   =FormatHelper.getFormattedTFgbc(1,2);
		GridBagConstraints gbc_vendAddr2   =FormatHelper.getFormattedTFgbc(1,3);
		GridBagConstraints gbc_vendCity    =FormatHelper.getFormattedTFgbc(1,4);
		GridBagConstraints gbc_vendCountry =FormatHelper.getFormattedTFgbc(1,5);
		GridBagConstraints gbc_vendPin 	   =FormatHelper.getFormattedTFgbc(1,6);
		GridBagConstraints gbc_vendProfile =FormatHelper.getFormattedTFgbc(1,7);
		GridBagConstraints gbc_vendTin     =FormatHelper.getFormattedTFgbc(1,8);
		
		Country[] listCountry = createCountryList();	   
	    tfVendCountry = new JComboBox<>(listCountry);
	    tfVendCountry.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    
		 //Add all the Text Fields 	
		formpanel.add(tfVendID, gbc_vendID);
		formpanel.add(tfVendName, gbc_vendName);
		formpanel.add(tfVendAddr1, gbc_vendAddr1);
		formpanel.add(tfVendAddr2, gbc_vendAddr2);
		formpanel.add(tfVendCity, gbc_vendCity);
		formpanel.add(tfVendCountry, gbc_vendCountry);
		formpanel.add(tfVendPin, gbc_vendPin); 
		formpanel.add(tfVendProfile, gbc_vendProfile); 
		formpanel.add(tfVendTin,gbc_vendTin); 
	
		//Add all the Buttons to Bottom Panel 
		btnSave = new JButton("Save"); btnReset = new JButton("Reset"); btnMenu = new JButton("Back to Menu");
	   
	      
	    btnSave.addActionListener(new ActionListener() 
	        {
	        	 
	            public void actionPerformed(ActionEvent e) 
	            {
	            	System.out.println("You have chosen Create->Vendor->Save !!!");
	         		SessionFactory sessFact = HibernateUtil.getSessionFactory();
	        		session = sessFact.getCurrentSession();
	        		org.hibernate.Transaction tr = session.beginTransaction();
	        		VendorModel vendModel = new VendorModel();
	        		vendModel.setvendId(tfVendID.getText());
	        		vendModel.setvendName(tfVendName.getText());
	        		vendModel.setvendAddr1(tfVendAddr1.getText());
	        		vendModel.setvendAddr2(tfVendAddr2.getText());	
	        		vendModel.setvendCity(tfVendCity.getText());
	        		vendModel.setvendCountry(tfVendCountry.getSelectedItem().toString());
	        		vendModel.setvendProdprof(tfVendProfile.getText());
	        		vendModel.setvendTin(tfVendTin.getText());
	        		vendModel.setvendPin(tfVendPin.getText());
	        		
	        		session.save(vendModel);
	        		tr.commit();
	        		System.out.println("Successfully inserted Vendor Info");	        		
	        		//sessFact.close();

	            }
	        });
	    btnMenu.addActionListener(new ActionListener() {
	        	 
	         public void actionPerformed(ActionEvent e) {
	          dispose();
	          MainMenuHelper mmframe=new MainMenuHelper(parent1);
	           mmframe.MainMenuUI();
	           mmframe.setVisible(true);
	            }
	        });
		
	    bp.add(btnSave);bp.add(btnReset);bp.add(btnMenu);
	    
        getContentPane().add(title, BorderLayout.NORTH);
        getContentPane().add(formpanel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.SOUTH);
        pack();
        setResizable(true);
        setLocationRelativeTo(parent1);
      	
    }//End of Method for Product Add Ui
	

	public void VendorListAllUI()
	{   	
		//Create Panel for Title , Table(Content), Bottom(Button Ops) and Add Label for Title
    	JPanel title = FormatHelper.getFormattedTitlePanel();     
    	JPanel tblpanel = FormatHelper.getFormattedTablePanel(); 
	    JPanel bp = FormatHelper.getFormattedBpPanel();  
	    FormatHelper.setTitleLabel(title, "Vendor Management Portal >> View >> VENDOR" );    
	
		List<VendorModel> Vendors;
	    JPanel panel = new JPanel();
	    panel.setLayout(new BorderLayout());

	    GridBagConstraints gs = new GridBagConstraints();
	    gs.fill = GridBagConstraints.HORIZONTAL;
	    	
	    //JOptionPane.showMessageDialog(Product.this, "! You are in List Product !!!", "PRODUCT",
	    // JOptionPane.INFORMATION_MESSAGE);   	
	    {
	    	SessionFactory sessFact = HibernateUtil.getSessionFactory();
	    	session = sessFact.getCurrentSession();
	    	org.hibernate.Transaction tr = session.beginTransaction();

			Criteria criteria = session.createCriteria(VendorModel.class);
			Vendors = criteria.list();

	    	Iterator<VendorModel> itr = Vendors.iterator();
	    	/*
	    	while (itr.hasNext()) {

	    	VendorModel vendM = itr.next();
	    	System.out.println(vendM.getvendName());
	    		 
	    	* Add more field to be displayed on the console
	    	* System.out.println(vendM.getvendAddr1());
	       			
	    	}
	    	*/
	    	tr.commit();
	    	System.out.println("Data displayed");

	    	//sessFact.close();
	    } 
	    	
	    	
	    //create the model
	    VendorTableModel model = new VendorTableModel(Vendors);
	        
	    //create the table
	    JTable table = new JTable(model);
	    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
	    renderer.setHorizontalAlignment(SwingConstants.CENTER);
	    for (int i=0; i<table.getColumnCount();i++)
	    {
	            table.setDefaultRenderer(table.getColumnClass(i),renderer);
	    }
	    JTableHeader tableHeader = table.getTableHeader();
	    tableHeader.setBackground(Color.decode("#808000"));
	    tableHeader.setForeground(Color.WHITE);
	    tblpanel.add(table, BorderLayout.CENTER);
	    tblpanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
	    JScrollPane scrlpane = new JScrollPane(table);
	    scrlpane.setSize(new Dimension(640,320));
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
	}//End of Method for Product List All Ui
	
	public void VendorManageUI()
	{   	
	    	//Create Panel for Title , Table(Content), Bottom(Button Ops) and Add Label for Title
	    	JPanel title = FormatHelper.getFormattedTitlePanel();     
	    	JPanel selpanel = FormatHelper.getFormattedSelectPanel(); 
	    	JPanel editpanel = FormatHelper.getFormattedEditPanel(); 
		    JPanel bp = FormatHelper.getFormattedBpPanel();  
		    FormatHelper.setTitleLabel(title, "Vendor Management Portal >> Manage >> Vendor" );
		    
		    FormatHelper.setFormattedFormLabel(selpanel, "Select Vendor to Edit",  0, 0,12);
		
			SessionFactory sessFact1 = HibernateUtil.getSessionFactory();
			session = sessFact1.getCurrentSession();
			org.hibernate.Transaction tr1 = session.beginTransaction();
			CriteriaBuilder builder1 = session.getCriteriaBuilder();

	        javax.persistence.criteria.CriteriaQuery<String> query1 = builder1.createQuery(String.class);
		    Root<VendorModel> root1 = query1.from(VendorModel.class); 
	        query1.select(root1.get("vendName"));
	        Query<String> q1=session.createQuery(query1);
	        List<String>  VendNameList=q1.getResultList();
	        tr1.commit();
	        //for (String name : partNameList) {
	        //    System.out.println(name);
	        //}
	        String[] VendStrArr = new String[VendNameList.size()];
	        VendNameList.toArray(VendStrArr);
	        
		    tfVendNameCh = new JComboBox<String>();
			tfVendNameCh.setFont(new Font("Tahoma", Font.PLAIN, 12));
			tfVendNameCh.setModel(new DefaultComboBoxModel<String>(VendStrArr));
			GridBagConstraints gbc_vendNamech =FormatHelper.getFormattedTFgbc(0,1);
			selpanel.add(tfVendNameCh, gbc_vendNamech);
	        
	        //create the button component to return to main menu 
	       	btnModify = new JButton("Modify"); btnDelete = new JButton("Delete");
	       	btnMenu   = new JButton("Back to Menu"); 
	       	bp.add(btnModify);bp.add(btnDelete);bp.add(btnMenu);
	       	
	     // Create an ActionListener for the PRODUCT JComboBox component.
			tfVendNameCh.addActionListener
			(new ActionListener() 
				{
	            	public void actionPerformed(ActionEvent event) 
	            	{
	            		JComboBox<String> tfVendName = (JComboBox<String>)event.getSource();
	            		Object selected = tfVendName.getSelectedItem();
	            		System.out.println("Selected Item  = " + selected);
	            		insvendName = (String) selected;
	                
	            		SessionFactory sessFact = HibernateUtil.getSessionFactory();
	            		session = sessFact.getCurrentSession();
	            		org.hibernate.Transaction tr = session.beginTransaction();

	            		CriteriaBuilder builder = session.getCriteriaBuilder();
	            		javax.persistence.criteria.CriteriaQuery<VendorModel> query = builder.createQuery(VendorModel.class);
	            		Root<VendorModel> root = query.from(VendorModel.class);    
	            		query.select(root).where(builder.equal(root.get("vendName"),selected));  
	            		Query<VendorModel> q=session.createQuery(query);             
	            		VendorModel vendor=q.getSingleResult();
	            		//System.out.println(product.getProdName());               

	            		tr.commit();
	            		insVendorId = vendor.getvendId();
	            		insvendName = vendor.getvendName();
	            		insId = vendor.getId();
	            		
	            		FormatHelper.setFormattedFormLabel(editpanel, "ID",            		   0, 0,12);
	             	    FormatHelper.setFormattedFormLabel(editpanel, "Name",                  0, 1,12);
	             	    FormatHelper.setFormattedFormLabel(editpanel, "Bldg/Plot #",           0, 2,12);
	             	    FormatHelper.setFormattedFormLabel(editpanel, "State",                 0, 3,12);
	             	    FormatHelper.setFormattedFormLabel(editpanel, "CITY",             	   0, 4,12);
	             	    FormatHelper.setFormattedFormLabel(editpanel, "COUNTRY",           	   0, 5,12);
	             	    FormatHelper.setFormattedFormLabel(editpanel, "PIN/ZIP",           	   0, 6,12);
	             	    FormatHelper.setFormattedFormLabel(editpanel, "Product Profile",       0, 7,12);
	             	    FormatHelper.setFormattedFormLabel(editpanel, "TAX ID NO",             0, 8,12);
	                     //Non Editable entries , as they are dependent values in other tables
	             	    FormatHelper.setFormattedFormLabel(editpanel, vendor.getvendId(),      1, 0,12);
	                	FormatHelper.setFormattedFormLabel(editpanel, vendor.getvendName(),    1, 1,12);
	                	    
	             		//Create and Add Assign grid constraints for Text Fields  
	             		GridBagConstraints gbc_vendAddr1   =FormatHelper.getFormattedTFgbc(1,2);
	             		GridBagConstraints gbc_vendAddr2   =FormatHelper.getFormattedTFgbc(1,3);
	             		GridBagConstraints gbc_vendCity    =FormatHelper.getFormattedTFgbc(1,4);
	             		GridBagConstraints gbc_vendCountry =FormatHelper.getFormattedTFgbc(1,5);
	             		GridBagConstraints gbc_vendPin     =FormatHelper.getFormattedTFgbc(1,6);
	             		GridBagConstraints gbc_vendProfile =FormatHelper.getFormattedTFgbc(1,7);
	             		GridBagConstraints gbc_vendTin 	   =FormatHelper.getFormattedTFgbc(1,8);
	                	tfVendAddr1=FormatHelper.getFormattedTextField(12);
	             		tfVendAddr1.setText(vendor.getvendAddr1());
	             		
	             		tfVendAddr2=FormatHelper.getFormattedTextField(12);
	             		tfVendAddr2.setText(vendor.getvendAddr2());
	             		
	             		tfVendCity=FormatHelper.getFormattedTextField(12);
	             		tfVendCity.setText(vendor.getvendCity());
	             		
	             		
	             		tfVendPin=FormatHelper.getFormattedTextField(12);
	             		tfVendPin.setText(vendor.getvendPin());
	             		
	             		tfVendProfile=FormatHelper.getFormattedTextField(12);
	             		tfVendProfile.setText(vendor.getvendProdprof());
	             		
	             		tfVendTin=FormatHelper.getFormattedTextField(12);
	             		tfVendTin.setText(vendor.getvendTin());
	             		
	             		
	             	    Country[] listCountry = createCountryList();	   
	             	    tfVendCountry = new JComboBox<>(listCountry);
	             	    tfVendCountry.setFont(new Font("Tahoma", Font.PLAIN, 12));
	             	    int indexC = getCountryIndex(vendor.getvendCountry(), listCountry);
	             	    tfVendCountry.setSelectedIndex(indexC);
	             	    	
	                	editpanel.add(tfVendAddr1, gbc_vendAddr1);
	                	editpanel.add(tfVendAddr2, gbc_vendAddr2);
	                	editpanel.add(tfVendCity, gbc_vendCity);
	                	editpanel.add(tfVendCountry, gbc_vendCountry);
	                	editpanel.add(tfVendPin, gbc_vendPin);
	                	editpanel.add(tfVendProfile, gbc_vendProfile);
	                	editpanel.add(tfVendTin, gbc_vendTin);
	                    
	                	
	                	editpanel.removeAll();
	                    editpanel.invalidate();
	             	    FormatHelper.setFormattedFormLabel(editpanel, "ID",                           0, 0,12);
	             	    FormatHelper.setFormattedFormLabel(editpanel, "Name",                         0, 1,12);
	             	    FormatHelper.setFormattedFormLabel(editpanel, "Bldg/Plot #",     		      0, 2,12);
	             	    FormatHelper.setFormattedFormLabel(editpanel, "State" ,                       0, 3,12);
	             	    FormatHelper.setFormattedFormLabel(editpanel, "CITY",             		      0, 4,12);
	             	    FormatHelper.setFormattedFormLabel(editpanel, "COUNTRY",          			  0, 5,12);
	             	    FormatHelper.setFormattedFormLabel(editpanel, "PIN/ZIP",          			  0, 6,12);
	             	    FormatHelper.setFormattedFormLabel(editpanel, "Product Profile",          	  0, 7,12);
	             	    FormatHelper.setFormattedFormLabel(editpanel, "TAX ID NO",          	 	  0, 8,12);
	             	    FormatHelper.setFormattedFormLabel(editpanel, vendor.getvendId(),             1, 0,12);
	             	    FormatHelper.setFormattedFormLabel(editpanel, vendor.getvendName(),           1, 1,12);
	                	editpanel.add(tfVendAddr1, gbc_vendAddr1);
	             		editpanel.add(tfVendAddr2, gbc_vendAddr2);
	             		editpanel.add(tfVendCity, gbc_vendCity);
	             		editpanel.add(tfVendCountry, gbc_vendCountry);
	             		editpanel.add(tfVendPin, gbc_vendPin);
	             		editpanel.add(tfVendProfile, gbc_vendProfile);
	             		editpanel.add(tfVendTin, gbc_vendTin);
	             		editpanel.revalidate();
	             		editpanel.repaint();
	        		
	            	}		
				}
			);
       	    
	        //Action Listener for Modify Button 
	        btnModify.addActionListener(new ActionListener() 
	        {        	 
	            public void actionPerformed(ActionEvent e) 
	            {
	        		System.out.println("! You have chosen Manage->Vendor->Modify !!!");  
	         		SessionFactory sessFact = HibernateUtil.getSessionFactory();
	        		session = sessFact.getCurrentSession();
	        		org.hibernate.Transaction tr = session.beginTransaction();
	        		
	        		VendorModel vendModel = new VendorModel();
	        		vendModel.setId(insId);
	        		vendModel.setvendId(insVendorId);
	        		vendModel.setvendName(insvendName);
	        		vendModel.setvendAddr1(tfVendAddr1.getText());
	        		vendModel.setvendAddr2(tfVendAddr2.getText());
	        		vendModel.setvendCity(tfVendCity.getText());
	        		vendModel.setvendCountry(tfVendCountry.getSelectedItem().toString());
	        		vendModel.setvendPin(tfVendPin.getText());
	        		vendModel.setvendProdprof(tfVendProfile.getText());
	        		vendModel.setvendTin(tfVendTin.getText());    		
	        		session.update(vendModel);
	        		
	        		tr.commit();
	        		System.out.println("Successfully Modified Vendor Info");        		
	        		//sessFact.close();
	            }
	        }); //End of Action Listener for btnModify
	        
	        //Action Listener for Delete Button 
	        btnDelete.addActionListener(new ActionListener() 
	        {        	 
	            public void actionPerformed(ActionEvent e) 
	            {
	        		System.out.println("! You have chosen Manage-> Vendor ->Delete !!!");  
	         		SessionFactory sessFact = HibernateUtil.getSessionFactory();
	        		session = sessFact.getCurrentSession();
	        		org.hibernate.Transaction tr = session.beginTransaction();	
	        		
	                CriteriaBuilder cb = session.getCriteriaBuilder();
	                CriteriaDelete<VendorModel> deleteVend = cb.createCriteriaDelete(VendorModel.class); // create delete 
	                Root<VendorModel> prod = deleteVend.from(VendorModel.class);                                       // set the root class
	                deleteVend.where(cb.equal(prod.get("vendName"),insvendName));                         // set where clause
	                session.createQuery(deleteVend).executeUpdate();                                       // perform update
	        		System.out.println("Successfully Deleted from Vendor Table ");    
	                
	                CriteriaBuilder cb1 = session.getCriteriaBuilder();
	                CriteriaDelete<SupplyModel> deleteSupply = cb1.createCriteriaDelete(SupplyModel.class);
	                Root<SupplyModel> supply = deleteSupply.from(SupplyModel.class);
	                deleteSupply.where(cb1.equal(supply.get("vendName"), insvendName));
	                session.createQuery(deleteSupply).executeUpdate();
	           		System.out.println("Successfully Deleted from Supply Table ");  
	           		 
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
	        
	} //Method to Manage Vendor 
	
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
    
    private int getCountryIndex(String Country, Country[] listCountry)
    {
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
 }//End of Class Vendor
	
