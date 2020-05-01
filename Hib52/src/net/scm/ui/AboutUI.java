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

public class AboutUI extends JDialog
{
	private static final long serialVersionUID = 1L;
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
	public AboutUI(JFrame parent)
	{
		super(parent, "Vendor Management System", true);
		parent1=parent;
	}
	
    //Method to display UI for accepting Product inputs from User to Save into the DB  
    public void AboutAddProjectUI()
    {   	

    	//Create Panel for Title 
    	JPanel title = new JPanel();
    	title.setBackground(Color.decode("#006666"));
    	title.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
	    title.setPreferredSize(new Dimension(720,30));
        title.setOpaque(true);
       	//Create Panel for Menu       
    	JPanel formpanel = new JPanel(new BorderLayout());
        formpanel.setPreferredSize(new Dimension(720, 400));   
		formpanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
		formpanel.setBackground(Color.white);

       	//Create Panel for Bottom (Adding Buttons for Operations)               
	    JPanel bp = new JPanel();
        bp.setBackground(Color.decode("#87CEFA"));
        bp.setPreferredSize(new Dimension(720, 40));  
    	title.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
        bp.setOpaque(true);
        
    	//Adding Components for the Title Panel    
	    JLabel ltitle = new JLabel("Vendor Management Portal >> About >> Software");
	    ltitle.setHorizontalAlignment(JLabel.CENTER);
	    ltitle.setVerticalAlignment(JLabel.CENTER);
	    ltitle.setFont(new Font("Arial",Font.TRUETYPE_FONT,16));
	    ltitle.setForeground(Color.WHITE);
	    title.add(ltitle); 
	    
        JTextArea taAboutSW = new JTextArea();
        String AboutSWP1 = 
        		"The Vendor Management System Software is a portal developed for Manufacturing Companies to aid them in the process of Planning the entire "+
        		"Purchase Cycle for Manufactuing a Product by creating and managing information on the Product, the parts it is made of, the suppliers "+
        		"who are involved which is to ensure proper Vendor Management Process from collecting Parts Needed, to Supplier info  and Manufacturing Plan.";
        String AboutSWP2 =
        		"This begins with the Production Planning and Design team to Define the PRODUCT for manufacturing and define the the PARTs that are needed "+
        		"for manufacturing a given PRODUCT. It also enables the team responsible for purchasing the PARTs to define and create a Database of VENDORs "+
        		"with their profile information such as address, quality and Profile of products they supply.";
        String AboutSWP3 =                                                                                                                                       
        		"Based on Product, Parts and Supplier Information, the Planning and Desing team can build a BILL OF MATERIALS(BOM) to enable the "+
        		"Purchase and the Manufacturing teams to Purchase and Manufacture the Product.The Production Team can define the Production Batch to enable "+
        		"the Purchase Organisation to create an Order List based on the Product chosen and the timelines defined. The Order List is generated based "+
        		"on the pruning of the Vendor List either on Price competitiveness, Quality OR Timeliness of Delivery Or Optimal of the three. This OrderList "+ 
        		"if further process to generate Purchase Orders that can be emailed to the vendors to trigger the Purchase cycle and be ready for Manufacturing.";
       
        taAboutSW.setText("\n\n\n"+AboutSWP1+"\n\n"+AboutSWP2+"\n\n"+AboutSWP3);
        taAboutSW.setLineWrap(true);
        taAboutSW.setWrapStyleWord(true);
        taAboutSW.setFont(new Font("Arial", Font.PLAIN, 14));
        taAboutSW.setEditable(false);
        formpanel.add(taAboutSW);

        btnMenu   = new JButton("Back to Menu");
        bp.add(btnMenu);
      
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
        
     } 
    	
    
    public void AboutAddDevUI()
    {   	

    	//Create Panel for Title 
    	JPanel title = new JPanel();
    	title.setBackground(Color.decode("#006666"));
    	title.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
	    title.setPreferredSize(new Dimension(720,30));
        title.setOpaque(true);
       	//Create Panel for Menu       
    	JPanel formpanel = new JPanel(new BorderLayout());
        formpanel.setPreferredSize(new Dimension(720, 400));   
		formpanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
		formpanel.setBackground(Color.white);

       	//Create Panel for Bottom (Adding Buttons for Operations)               
	    JPanel bp = new JPanel();
        bp.setBackground(Color.decode("#87CEFA"));
        bp.setPreferredSize(new Dimension(720, 40));  
    	title.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
        bp.setOpaque(true);
        
    	//Adding Components for the Title Panel    
	    JLabel ltitle = new JLabel("Vendor Management Portal >> About >> Software");
	    ltitle.setHorizontalAlignment(JLabel.CENTER);
	    ltitle.setVerticalAlignment(JLabel.CENTER);
	    ltitle.setFont(new Font("Arial",Font.TRUETYPE_FONT,16));
	    ltitle.setForeground(Color.WHITE);
	    title.add(ltitle); 
	    
        JTextArea taAboutSW = new JTextArea();
        String AboutSWP1 = 
        		"DEVELOPER NAME: MUKUND.G                                                                                                                       "+
        		"GITHUB: Mukund324";
        String AboutSWP2 =
        		"DEVELOPER NAME: SRIRANJAN.S                                                                                                                     "+
                "GITHUB: craysri06";
        String AboutSWP3 =                                                                                                                                       
        		"Based on Product, Parts and Supplier Information, the Planning and Desing team can build a BILL OF MATERIALS(BOM) to enable the "+
        		"Purchase and the Manufacturing teams to Purchase and Manufacture the Product.The Production Team can define the Production Batch to enable ";
       
        taAboutSW.setText("\n\n\n"+AboutSWP1+"\n\n"+AboutSWP2+"\n\n"+AboutSWP3);
        taAboutSW.setLineWrap(true);
        taAboutSW.setWrapStyleWord(true);
        taAboutSW.setFont(new Font("Arial", Font.PLAIN, 14));
        taAboutSW.setEditable(false);
        formpanel.add(taAboutSW);

        btnMenu   = new JButton("Back to Menu");
        bp.add(btnMenu);
      
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
        
     } 
}//End of Class()

