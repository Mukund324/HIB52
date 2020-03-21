package net.scm.ui;
import net.scm.model.*;

import javax.swing.*;
import java.util.List;

import org.hibernate.stat.spi.StatisticsImplementor;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import net.scm.model.HibernateUtil;
import net.scm.model.ProductModel;

import net.scm.ui.Product;

public class Product extends JDialog
{
	private static final long serialVersionUID = 1L;
	public JFrame parent1;
	public Session session;

	private JLabel lbProductName;
	private JTextField tfProductName;
    private JLabel lbPrice;
    private JTextField tfPrice;
    private JLabel lbRating;
    private JTextField tfRating;
    private JLabel lbMake;
    private JTextField tfMake;
    private JLabel lbMakeCountry;
    private JTextField tfMakeCountry;
    private JButton btnSave;
    private JButton btnReset;
    private JButton btnMenu;

	public Product(JFrame parent)
	{
		super(parent, "Supply Chain Management", true);
		parent1=parent;
	}
	
    //Creating constructor of ProductFrame() class
    public void ProductAddUI()
    {   	

    	JPanel panel = new JPanel(new GridBagLayout());
    	GridBagConstraints gs = new GridBagConstraints();
    	gs.fill = GridBagConstraints.HORIZONTAL;
    	
    	lbProductName = new JLabel("Product Name ");
        gs.gridx = 0;
        gs.gridy = 0;
        gs.gridwidth = 1;
        panel.add(lbProductName, gs);
         
        tfProductName = new JTextField(20);
        gs.gridx = 1;
        gs.gridy = 0;
        gs.gridwidth = 1;
        panel.add(tfProductName, gs); 
        
        lbPrice = new JLabel("Product Price ");
        gs.gridx = 0;
        gs.gridy = 1;
        gs.gridwidth = 1;
        panel.add(lbPrice, gs);
         
        tfPrice = new JTextField(10);
        gs.gridx = 1;
        gs.gridy = 1;
        gs.gridwidth = 1;
        panel.add(tfPrice, gs);  
        
        lbRating = new JLabel("Product Rating");
        gs.gridx = 0;
        gs.gridy = 2;
        gs.gridwidth = 1;
        panel.add(lbRating, gs);
         
        tfRating = new JTextField(50);
        gs.gridx = 1;
        gs.gridy = 2;
        gs.gridwidth = 1;
        panel.add(tfRating, gs);  
        
        lbMake= new JLabel("Product Make ");
        gs.gridx = 0;
        gs.gridy = 3;
        gs.gridwidth = 1;
        panel.add(lbMake, gs);
         
        tfMake = new JTextField(50);
        gs.gridx = 1;
        gs.gridy = 3;
        gs.gridwidth = 1;
        panel.add(tfMake, gs);
        
        lbMakeCountry = new JLabel("Country ");
        gs.gridx = 0;
        gs.gridy = 4;
        gs.gridwidth = 1;
        panel.add(lbMakeCountry, gs);
         
        tfMakeCountry = new JTextField(50);
        gs.gridx = 1;
        gs.gridy = 4;
        gs.gridwidth = 1;
        panel.add(tfMakeCountry, gs);
        
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
            	JOptionPane.showMessageDialog(Product.this, "! You have chosen Define->Product->Save !!!", "PRODUCT",
                         JOptionPane.INFORMATION_MESSAGE);
            	
         		SessionFactory sessFact = HibernateUtil.getSessionFactory();
        		session = sessFact.getCurrentSession();
        		org.hibernate.Transaction tr = session.beginTransaction();
        		ProductModel prodModel = new ProductModel();
        		prodModel.setProdName(tfProductName.getText());
        		prodModel.setProdPrice(Integer.parseInt(tfPrice.getText()));
        		prodModel.setProdRating(tfRating.getText());
        		prodModel.setProdMake(tfMake.getText());
        		prodModel.setProdCountry(tfMakeCountry.getText());
        		session.save(prodModel);
        		tr.commit();
        		System.out.println("Successfully inserted");
        		
        		//sessFact.close();
        		
            	JOptionPane.showMessageDialog(Product.this, "! You have saved the Product !!!", "PRODUCT",
                        JOptionPane.INFORMATION_MESSAGE);
        		         		
            	dispose();
            	MainMenuHelper mmframe=new MainMenuHelper(parent1);
            	mmframe.MainMenuUI();
         		mmframe.setVisible(true);

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
        
        JPanel bp = new JPanel();
        bp.add(btnSave);
        bp.add(btnReset);
        bp.add(btnMenu);
  
        panel.setPreferredSize(new Dimension(640, 480));       
        getContentPane().add(panel, BorderLayout.PAGE_START);
        getContentPane().add(bp, BorderLayout.PAGE_END);
        pack();
        setResizable(true);
        setLocationRelativeTo(parent1);
        
     }
 
    public void ProductListAllUI()
    {   	
    	List<ProductModel> Products;
    	JPanel panel = new JPanel();
    	panel.setLayout(new BorderLayout());

    	GridBagConstraints gs = new GridBagConstraints();
    	gs.fill = GridBagConstraints.HORIZONTAL;
    	
    	JOptionPane.showMessageDialog(Product.this, "! You are in List Product !!!", "PRODUCT",
                JOptionPane.INFORMATION_MESSAGE);   	
    	{
     		SessionFactory sessFact = HibernateUtil.getSessionFactory();
    		session = sessFact.getCurrentSession();
    		org.hibernate.Transaction tr = session.beginTransaction();

			Criteria criteria = session.createCriteria(ProductModel.class);
    		Products = criteria.list();

    		Iterator<ProductModel> itr = Products.iterator();
    		
    		while (itr.hasNext()) {

    			ProductModel prodM = itr.next();
    			System.out.println(prodM.getId());
    			/*System.out.println(prodM.getProdName());
    			System.out.println(prodM.getProdPrice());
    			System.out.println(prodM.getProdRating());
    			System.out.println(prodM.getProdMake());
       			System.out.println(prodM.getProdCountry());
       			*/
    		}
    		tr.commit();
    		System.out.println("Data displayed");

    		//sessFact.close();
    	}
    	
        JPanel hp = new JPanel();
        hp.setLayout(new BorderLayout());
            	
       	JLabel lblHeading = new JLabel("List of Products", SwingConstants.CENTER);
       	lblHeading.setFont(new Font("Arial",Font.TRUETYPE_FONT,24));
       	lblHeading.setForeground (Color.green);
       	lblHeading.setBackground (Color.white);
       	lblHeading.setOpaque(true); 
       	hp.add(lblHeading,BorderLayout.CENTER);
       	
    	//create the model
        ProductTableModel model = new ProductTableModel(Products);
        
        //create the table
        JTable table = new JTable(model);
    	panel.add(table, BorderLayout.CENTER);
    	panel.add(table.getTableHeader(), BorderLayout.PAGE_START);

       	btnMenu   = new JButton("Back to Menu");
        
        JPanel bp = new JPanel();
        bp.add(btnMenu);
  
        panel.setPreferredSize(new Dimension(640, 480));   
        getContentPane().add(hp, BorderLayout.PAGE_START);
        getContentPane().add(panel, BorderLayout.LINE_START);
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
        
        
     }

    
}

