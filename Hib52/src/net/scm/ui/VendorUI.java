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
import javax.swing.table.JTableHeader;
import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import net.scm.model.HibernateUtil;
import net.scm.model.ProductModel;

import net.scm.ui.Product;

public class VendorUI extends JDialog
{
	public JFrame parent1;
	public Session session;
	
	public JLabel lbVendorID;
	public JTextField tfVendID;
	public JLabel lblVendName;
	public JTextField tfVendName;
	public JLabel lblVendCity;
	public JTextField tfVendCity;
	public JLabel lblVendCountry;
	public JTextField tfVendCountry;
	public JLabel lbVendPin;
	public JTextField tfVendPin;
	public JLabel lbVendProfile;
	public JTextField tfVendProfile;
	public JLabel lbVendTin;
	public JTextField tfVendTin;
	public JButton btnSave;
	public JButton btnReset;
	public JButton btnMenu;
	
	public VendorUI(JFrame parent)
	{
		super(parent, "Supply Chain Management", true);
		parent1=parent;
	}
	public void VendorAddUI()
    {   	

    	//Create Panel for Title 
    	JPanel title = new JPanel();
    	title.setBackground(Color.decode("#006666"));
    	title.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
	    title.setPreferredSize(new Dimension(640,30));
        title.setOpaque(true);

    	//Create Panel for Menu       
    	JPanel formpanel = new JPanel(new GridBagLayout());
        formpanel.setPreferredSize(new Dimension(640, 400));   
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
        bp.setPreferredSize(new Dimension(640, 40));  
        bp.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
        bp.setOpaque(true);
   
		//Adding Components for the Title Panel    
	    JLabel ltitle = new JLabel("Vendor Management Portal");
	    ltitle.setHorizontalAlignment(JLabel.CENTER);
	    ltitle.setVerticalAlignment(JLabel.CENTER);
	    ltitle.setFont(new Font("Arial",Font.TRUETYPE_FONT,16));
	    ltitle.setForeground(Color.WHITE);
	    title.add(ltitle);  
	    
	    //Vendor ID
	    JLabel lblVendID = new JLabel("ID");
	    lblVendID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblVendID = new GridBagConstraints();
		gbc_lblVendID.anchor = GridBagConstraints.EAST;
		gbc_lblVendID.insets = new Insets(10, 10, 10, 5);
		gbc_lblVendID.gridx = 0;
		gbc_lblVendID.gridy = 0;
		formpanel.add(lblVendID, gbc_lblVendID);
		
		
		//vendor Id
		JTextField tfVendID = new JTextField();
		tfVendID.setText(" ");
		tfVendID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfVendID.setColumns(30);
		GridBagConstraints gbc_vendID = new GridBagConstraints();
		gbc_vendID.gridwidth = 2;
		gbc_vendID.insets = new Insets(10, 10, 10, 5);
		gbc_vendID.anchor = GridBagConstraints.NORTHWEST;
		gbc_vendID.gridx = 1;
		gbc_vendID.gridy = 0;
		formpanel.add(tfVendID, gbc_vendID);
		
		
		
		 //Vendor Name
	    JLabel lblVendName = new JLabel("NAME");
	    lblVendName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblVendName = new GridBagConstraints();
		gbc_lblVendName.anchor = GridBagConstraints.EAST;
		gbc_lblVendName.insets = new Insets(10, 10, 10, 5);
		gbc_lblVendName.gridx = 0;
		gbc_lblVendName.gridy = 1;
		formpanel.add(lblVendName, gbc_lblVendName);
		
		//Vendor Name
		JTextField tfVendName = new JTextField();
		tfVendName.setText(" ");
		tfVendName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfVendName.setColumns(30);
		GridBagConstraints gbc_vendName = new GridBagConstraints();
		gbc_vendName.gridwidth = 2;
		gbc_vendName.insets = new Insets(10, 10, 10, 5);
		gbc_vendName.anchor = GridBagConstraints.NORTHWEST;
		gbc_vendName.gridx = 1;
		gbc_vendName.gridy = 1;
		formpanel.add(tfVendName, gbc_vendName);
		
		
		//Building Label
	    JLabel lblVendAddr1 = new JLabel("Bldg/Plot #");
	    lblVendAddr1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblVendAddr1 = new GridBagConstraints();
		gbc_lblVendAddr1.anchor = GridBagConstraints.EAST;
		gbc_lblVendAddr1.insets = new Insets(10, 10, 10, 5);
		gbc_lblVendAddr1.gridx = 0;
		gbc_lblVendAddr1.gridy = 2;
		formpanel.add(lblVendAddr1, gbc_lblVendAddr1);
		
		//building Name
		JTextField tfVendAddr1 = new JTextField();
		tfVendAddr1.setText(" ");
		tfVendAddr1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfVendAddr1.setColumns(30);
		GridBagConstraints gbc_vendAddr1 = new GridBagConstraints();
		gbc_vendAddr1.gridwidth = 2;
		gbc_vendAddr1.insets = new Insets(10, 10, 10, 5);
		gbc_vendAddr1.anchor = GridBagConstraints.NORTHWEST;
		gbc_vendAddr1.gridx = 1;
		gbc_vendAddr1.gridy = 2;
		formpanel.add(tfVendAddr1, gbc_vendAddr1);
		
		//Building Label
	    JLabel lblVendAddr2 = new JLabel("Street Name");
	    lblVendAddr2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblVendAddr2 = new GridBagConstraints();
		gbc_lblVendAddr2.anchor = GridBagConstraints.EAST;
		gbc_lblVendAddr2.insets = new Insets(10, 10, 10, 5);
		gbc_lblVendAddr2.gridx = 0;
		gbc_lblVendAddr2.gridy = 3;
		formpanel.add(lblVendAddr2, gbc_lblVendAddr2);
		
		//building Name
		JTextField tfVendAddr2 = new JTextField();
		tfVendAddr2.setText(" ");
		tfVendAddr2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfVendAddr2.setColumns(30);
		GridBagConstraints gbc_vendAddr2 = new GridBagConstraints();
		gbc_vendAddr2.gridwidth = 2;
		gbc_vendAddr2.insets = new Insets(10, 10, 10, 5);
		gbc_vendAddr2.anchor = GridBagConstraints.NORTHWEST;
		gbc_vendAddr2.gridx = 1;
		gbc_vendAddr2.gridy = 3;
		formpanel.add(tfVendAddr2, gbc_vendAddr2);
		
		 //City Label
	    JLabel lblVendCity = new JLabel("CITY");
	    lblVendCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblVendCity = new GridBagConstraints();
		gbc_lblVendCity.anchor = GridBagConstraints.EAST;
		gbc_lblVendCity.insets = new Insets(10, 10, 10, 5);
		gbc_lblVendCity.gridx = 0;
		gbc_lblVendCity.gridy = 4;
		formpanel.add(lblVendCity, gbc_lblVendCity);
		
		//City Name
		JTextField tfVendCity = new JTextField();
		tfVendCity.setText(" ");
		tfVendCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfVendCity.setColumns(30);
		GridBagConstraints gbc_vendCity = new GridBagConstraints();
		gbc_vendCity.gridwidth = 2;
		gbc_vendCity.insets = new Insets(10, 10, 10, 5);
		gbc_vendCity.anchor = GridBagConstraints.NORTHWEST;
		gbc_vendCity.gridx = 1;
		gbc_vendCity.gridy = 4;
		formpanel.add(tfVendCity, gbc_vendCity);
		
		
		 //Country Label
	    JLabel lblVendCountry = new JLabel("COUNTRY");
	    lblVendCountry.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblVendCountry = new GridBagConstraints();
		gbc_lblVendCountry.anchor = GridBagConstraints.EAST;
		gbc_lblVendCountry.insets = new Insets(10, 10, 10, 5);
		gbc_lblVendCountry.gridx = 0;
		gbc_lblVendCountry.gridy = 5;
		formpanel.add(lblVendCountry, gbc_lblVendCountry);
		
		//Country Name
		Country[] listCountry = createCountryList();		   
		JComboBox<Country> tfVendCountry = new JComboBox<>(listCountry);
		tfVendCountry.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_vendCountry = new GridBagConstraints();
		gbc_vendCountry.gridwidth = 2;
		gbc_vendCountry.insets = new Insets(10, 10, 10, 5);
		gbc_vendCountry.anchor = GridBagConstraints.NORTHWEST;
		gbc_vendCountry.gridx = 1;
		gbc_vendCountry.gridy = 5;
		formpanel.add(tfVendCountry, gbc_vendCountry);
		
		
		 //Pin Label
	    JLabel lblVendPin = new JLabel("PIN/ZIP");
	    lblVendPin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblVendPin = new GridBagConstraints();
		gbc_lblVendPin.anchor = GridBagConstraints.EAST;
		gbc_lblVendPin.insets = new Insets(10, 10, 10, 5);
		gbc_lblVendPin.gridx = 0;
		gbc_lblVendPin.gridy = 6;
		formpanel.add(lblVendPin, gbc_lblVendPin);
		
		//Pin Name
		JTextField tfVendPin = new JTextField();
		tfVendPin.setText(" ");
		tfVendPin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfVendPin.setColumns(30);
		GridBagConstraints gbc_vendPin = new GridBagConstraints();
		gbc_vendPin.gridwidth = 2;
		gbc_vendPin.insets = new Insets(10, 10, 10, 5);
		gbc_vendPin.anchor = GridBagConstraints.NORTHWEST;
		gbc_vendPin.gridx = 1;
		gbc_vendPin.gridy = 6;
		formpanel.add(tfVendPin, gbc_vendPin );
		
		 //prodprof Label
	    JLabel lblVendProfile = new JLabel("Product Profile");
	    lblVendProfile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblVendProfile = new GridBagConstraints();
		gbc_lblVendProfile.anchor = GridBagConstraints.EAST;
		gbc_lblVendProfile.insets = new Insets(10, 10, 10, 5);
		gbc_lblVendProfile.gridx = 0;
		gbc_lblVendProfile.gridy = 7;
		formpanel.add(lblVendProfile, gbc_lblVendProfile);
		
		//ProdprofName
		JTextField tfVendProfile = new JTextField();
		tfVendProfile.setText(" ");
		tfVendProfile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfVendProfile.setColumns(30);
		GridBagConstraints gbc_vendProfile = new GridBagConstraints();
		gbc_vendProfile.gridwidth = 2;
		gbc_vendProfile.insets = new Insets(10, 10, 10, 5);
		gbc_vendProfile.anchor = GridBagConstraints.NORTHWEST;
		gbc_vendProfile.gridx = 1;
		gbc_vendProfile.gridy = 7;
		formpanel.add(tfVendProfile, gbc_vendProfile );
		
		 //prodTin Label
	    JLabel lblVendTin = new JLabel("TAX ID NO");
	    lblVendTin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblVendTin = new GridBagConstraints();
		gbc_lblVendTin.anchor = GridBagConstraints.EAST;
		gbc_lblVendTin.insets = new Insets(10, 10, 10, 5);
		gbc_lblVendTin.gridx = 0;
		gbc_lblVendTin.gridy = 8;
		formpanel.add(lblVendTin, gbc_lblVendTin);
		
		//ProdTin Name
		JTextField tfVendTin = new JTextField();
		tfVendTin.setText(" ");
		tfVendTin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfVendTin.setColumns(30);
		GridBagConstraints gbc_vendTin = new GridBagConstraints();
		gbc_vendTin.gridwidth = 2;
		gbc_vendTin.insets = new Insets(10, 10, 10, 5);
		gbc_vendTin.anchor = GridBagConstraints.NORTHWEST;
		gbc_vendTin.gridx = 1;
		gbc_vendTin.gridy = 8;
		formpanel.add(tfVendTin, gbc_vendTin );
		
		
		btnSave = new JButton("Save");
		btnReset = new JButton("Reset");
	    btnMenu = new JButton("Back to Menu");
	    bp.add(btnSave);
	    bp.add(btnReset);
	    bp.add(btnMenu);
	      
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
		
   
        getContentPane().add(title, BorderLayout.NORTH);
        getContentPane().add(formpanel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.SOUTH);
        pack();
        setResizable(true);
        setLocationRelativeTo(parent1);
      	
    }//End of Method for Product Add Ui
	
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
	    }//End of Method for create country list Ui
	
	 public void VendorListAllUI()
	    {   	
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
	    		
	    		while (itr.hasNext()) {

	    			VendorModel vendM = itr.next();
	    			System.out.println(vendM.getvendName());
	    			/* 
	    			 * Add more field to be displayed on the console
	    			 * System.out.println(vendM.getvendAddr1());
	       			*/
	    		}
	    		tr.commit();
	    		System.out.println("Data displayed");

	    		//sessFact.close();
	    	 } 
	    	
	    	JPanel title = new JPanel();
	    	title.setBackground(Color.decode("#006666"));
	    	title.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
		    title.setPreferredSize(new Dimension(640,30));
	        title.setOpaque(true);
	        
	       	//Create Panel for Menu       
	    	JPanel tblpanel = new JPanel(new BorderLayout());
	        tblpanel.setPreferredSize(new Dimension(640, 320));   
			tblpanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
			tblpanel.setBackground(Color.white);
			
	       	//Create Panel for Bottom (Adding Buttons for Operations)               
		    JPanel bp = new JPanel();
	        bp.setBackground(Color.decode("#87CEFA"));
	        bp.setPreferredSize(new Dimension(640, 40));  
	    	title.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
	        bp.setOpaque(true);
	        
		    JLabel ltitle = new JLabel("Vendor Management Portal");
		    ltitle.setHorizontalAlignment(JLabel.CENTER);
		    ltitle.setVerticalAlignment(JLabel.CENTER);
		    ltitle.setFont(new Font("Arial",Font.TRUETYPE_FONT,16));
		    ltitle.setForeground(Color.WHITE);
		    title.add(ltitle);    
	    	
	    	
	        JPanel hp = new JPanel();
	        hp.setLayout(new BorderLayout());
	            	
	       	JLabel lblHeading = new JLabel("List of Vendors", SwingConstants.CENTER);
	       	lblHeading.setFont(new Font("Arial",Font.TRUETYPE_FONT,24));
	       	lblHeading.setForeground (Color.green);
	       	lblHeading.setBackground (Color.white);
	       	lblHeading.setOpaque(true); 
	       	hp.add(lblHeading,BorderLayout.CENTER);
	       	
	    	//create the model
	        VendorTableModel model = new VendorTableModel(Vendors);
	        
	        //create the table
	        JTable table = new JTable(model);
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
	        
	        
	     }//End of Method for Product List All Ui
}