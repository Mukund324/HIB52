package net.scm.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.JTableHeader;
import javax.persistence.criteria.*;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaQuery;

import net.scm.model.*;

public class BoMUI extends JDialog {
	
	private static final long serialVersionUID = 1L;
	public JFrame parent1;
	public Session session;
	
	public JLabel lblProductName;
	public JTextField tfProductName;
	public JLabel lblProductID;
	public JTextField tfProductID;
	public JLabel lbPartID;
	public JTextField tfPartID;
	public JLabel lbPartName;
	public JTextField tfPartName;
	public JLabel lblPartPriceTyp;
	public JTextField tfPriceRangeTyp;
	public JLabel lblPartSpec;
	public JTextField tfPartSpec;
	public JLabel lbPartSuppSchTyp;
	public JTextField tfPartSuppSchTyp;
	public JLabel lbPartQltyStd;
	public JTextField tfPartQltyStd;
	public JButton btnSave;
	public JButton btnReset;
	public JButton btnMenu;
	
	public BoMUI(JFrame parent)
	{
		super(parent, "Supply Chain Management", true);
		parent1=parent;
	}//End of Constructor 
	
	public void BoMAddUI()
    {   
    	//Create Panel for Title 
    	JPanel title = new JPanel();
    	title.setBackground(Color.decode("#006666"));
    	title.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
	    title.setPreferredSize(new Dimension(640,30));
        title.setOpaque(true);

    	//Create Panel for Menu       
    	JPanel formpanel = new JPanel(new GridBagLayout());
        formpanel.setPreferredSize(new Dimension(300, 400));
        formpanel.setMaximumSize(new Dimension(300, 400));
        formpanel.setMinimumSize(new Dimension(300, 400));
		formpanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
		formpanel.setBackground(Color.white);
		GridBagLayout gBL = new GridBagLayout();
		gBL.columnWidths = new int[]{100, 67, 86, 86, 0};
		gBL.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gBL.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gBL.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		
    	JPanel pformpanel = new JPanel(new GridBagLayout());
        pformpanel.setPreferredSize(new Dimension(340, 400));
        pformpanel.setMaximumSize(new Dimension(340, 400));
        pformpanel.setMinimumSize(new Dimension(340, 400));
		pformpanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
		pformpanel.setBackground(Color.white);
		GridBagLayout pgBL = new GridBagLayout();
		pgBL.columnWidths = new int[]{100, 67, 86, 86, 0};
		pgBL.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		pgBL.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		pgBL.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		 
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
	    
	    btnSave = new JButton("Add");
		btnReset = new JButton("Reset");
	    btnMenu = new JButton("Back to Menu");
	    bp.add(btnSave);
	    bp.add(btnReset);
	    bp.add(btnMenu);
	    
		JLabel lblProdName = new JLabel("Select Product");
		lblProdName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblProdName = new GridBagConstraints();
		gbc_lblProdName.anchor = GridBagConstraints.EAST;
		gbc_lblProdName.insets = new Insets(10, 10, 10, 5);
		gbc_lblProdName.gridx = 0;
		gbc_lblProdName.gridy = 1;
		formpanel.add(lblProdName, gbc_lblProdName);
		
		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		session = sessFact.getCurrentSession();
		org.hibernate.Transaction tr = session.beginTransaction();

		CriteriaBuilder builder = session.getCriteriaBuilder();

        javax.persistence.criteria.CriteriaQuery<String> query = builder.createQuery(String.class);
	    Root<ProductModel> root = query.from(ProductModel.class); 
        query.select(root.get("prodName"));
        Query<String> q=session.createQuery(query);
        List<String> prodNameList=q.getResultList();
        for (String name : prodNameList) {
            System.out.println(name);
        }
		
        tr.commit();
        
        String[] prodStrArr = new String[prodNameList.size()];
        prodNameList.toArray(prodStrArr );
		
	    JComboBox<String> tfProdName = new JComboBox<String>();
		tfProdName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		//tfProdName.setModel(new DefaultComboBoxModel<String>(new String[] { "Car", "Bus", "Scooter", "Bicycle", "Van" }));
		tfProdName.setModel(new DefaultComboBoxModel<String>(prodStrArr));
		GridBagConstraints gbc_prodName = new GridBagConstraints();
		gbc_prodName.gridwidth = 2;
		gbc_prodName.insets = new Insets(10, 10, 10, 5);
		gbc_prodName.anchor = GridBagConstraints.NORTHWEST;
		gbc_prodName.gridx = 1;
		gbc_prodName.gridy = 1;
		formpanel.add(tfProdName, gbc_prodName);
		
		JLabel lblPartName = new JLabel("Select Part to Add");
		lblPartName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblPartName = new GridBagConstraints();
		gbc_lblPartName.anchor = GridBagConstraints.EAST;
		gbc_lblPartName.insets = new Insets(10, 10, 10, 5);
		gbc_lblPartName.gridx = 0;
		gbc_lblPartName.gridy = 1;
		pformpanel.add(lblPartName, gbc_lblPartName);
		
		SessionFactory sessFact1 = HibernateUtil.getSessionFactory();
		session = sessFact1.getCurrentSession();
		org.hibernate.Transaction tr1 = session.beginTransaction();
		CriteriaBuilder builder1 = session.getCriteriaBuilder();

        javax.persistence.criteria.CriteriaQuery<String> query1 = builder1.createQuery(String.class);
	    Root<PartModel> root1 = query1.from(PartModel.class); 
        query1.select(root1.get("partName"));
        Query<String> q1=session.createQuery(query1);
        List<String> partNameList=q1.getResultList();
        for (String name : partNameList) {
            System.out.println(name);
        }
		
        tr1.commit();
        
        String[] partStrArr = new String[partNameList.size()];
        partNameList.toArray(partStrArr );
        
	    JComboBox<String> tfPartName = new JComboBox<String>();
		tfPartName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		//tfProdName.setModel(new DefaultComboBoxModel<String>(new String[] { "Car", "Bus", "Scooter", "Bicycle", "Van" }));
		tfPartName.setModel(new DefaultComboBoxModel<String>(partStrArr));
		GridBagConstraints gbc_partName = new GridBagConstraints();
		gbc_partName.gridwidth = 2;
		gbc_partName.insets = new Insets(10, 10, 10, 5);
		gbc_partName.anchor = GridBagConstraints.NORTHWEST;
		gbc_partName.gridx = 1;
		gbc_partName.gridy = 1;
		pformpanel.add(tfPartName, gbc_partName);
	    
	    // Create an ActionListener for the JComboBox component.
		tfProdName.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent event) {
                JComboBox<String> tfProdName = (JComboBox<String>)event.getSource();
                Object selected = tfProdName.getSelectedItem();
                System.out.println("Selected Item  = " + selected);
                
        		SessionFactory sessFact = HibernateUtil.getSessionFactory();
        		session = sessFact.getCurrentSession();
        		org.hibernate.Transaction tr = session.beginTransaction();

        		CriteriaBuilder builder = session.getCriteriaBuilder();
                javax.persistence.criteria.CriteriaQuery<ProductModel> query = builder.createQuery(ProductModel.class);
                Root<ProductModel> root = query.from(ProductModel.class);    
                query.select(root).where(builder.equal(root.get("prodName"),selected));  
                Query<ProductModel> q=session.createQuery(query);             
                ProductModel product=q.getSingleResult();
                System.out.println(product.getProdName());               
                /*
                List<ProductModel> products=q.getResultList();
                for (ProductModel product : products) {
                    System.out.println(product.getProdName());
                 }
                 */
                tr.commit();
                
        		JLabel lbPrice = new JLabel("Price ");
        		lbPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_lblProductPrice = new GridBagConstraints();
        		gbc_lblProductPrice.anchor = GridBagConstraints.EAST;
        		gbc_lblProductPrice.insets = new Insets(10, 10, 10, 5);
        		gbc_lblProductPrice.gridx = 0;
        		gbc_lblProductPrice.gridy = 3;
        		formpanel.add(lbPrice, gbc_lblProductPrice);
        		
        		JLabel flbPrice = new JLabel(Integer.toString(product.getProdPrice()));
        		flbPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_flblProductPrice = new GridBagConstraints();
        		gbc_flblProductPrice.anchor = GridBagConstraints.EAST;
        		gbc_flblProductPrice.insets = new Insets(10, 10, 10, 5);
        		gbc_flblProductPrice.gridx = 1;
        		gbc_flblProductPrice.gridy = 3;
        		formpanel.add(flbPrice, gbc_flblProductPrice);
        		
        		JLabel lbRating = new JLabel("Rating");
        		lbRating.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_lblProdRating = new GridBagConstraints();
        		gbc_lblProdRating.anchor = GridBagConstraints.EAST;
        		gbc_lblProdRating.insets = new Insets(10, 10, 10, 5);
        		gbc_lblProdRating.gridx = 0;
        		gbc_lblProdRating.gridy = 5;
        		formpanel.add(lbRating, gbc_lblProdRating);
        		
           		JLabel flbRating = new JLabel(product.getProdRating());
        		flbRating.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_flblProdRating = new GridBagConstraints();
        		gbc_flblProdRating.anchor = GridBagConstraints.EAST;
        		gbc_flblProdRating.insets = new Insets(10, 10, 10, 5);
        		gbc_flblProdRating.gridx = 1;
        		gbc_flblProdRating.gridy = 5;
        		formpanel.add(flbRating, gbc_flblProdRating);
        		
        		JLabel lbMake = new JLabel("Make");
        		lbMake.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_lblProdMake = new GridBagConstraints();
        		gbc_lblProdMake.anchor = GridBagConstraints.EAST;
        		gbc_lblProdMake.insets = new Insets(10, 10, 10, 5);
        		gbc_lblProdMake.gridx = 0;
        		gbc_lblProdMake.gridy = 7;
        		formpanel.add(lbMake, gbc_lblProdMake);
        		
        		JLabel flbMake = new JLabel(product.getProdMake());
        		flbMake.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_flblProdMake = new GridBagConstraints();
        		gbc_flblProdMake.anchor = GridBagConstraints.EAST;
        		gbc_flblProdMake.insets = new Insets(10, 10, 10, 5);
        		gbc_flblProdMake.gridx = 1;
        		gbc_flblProdMake.gridy = 7;
        		formpanel.add(flbMake, gbc_flblProdMake);
        		
        		JLabel lbCountry = new JLabel("Country");
        		lbCountry.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_lblProdCountry = new GridBagConstraints();
        		gbc_lblProdCountry.anchor = GridBagConstraints.EAST;
        		gbc_lblProdCountry.insets = new Insets(10, 10, 10, 5);
        		gbc_lblProdCountry.gridx = 0;
        		gbc_lblProdCountry.gridy = 9;
        		formpanel.add(lbCountry, gbc_lblProdCountry);
        		
        		JLabel flbCountry = new JLabel(product.getProdCountry());
        		flbCountry.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_flblProdCountry = new GridBagConstraints();
        		gbc_flblProdCountry.anchor = GridBagConstraints.EAST;
        		gbc_flblProdCountry.insets = new Insets(10, 10, 10, 5);
        		gbc_flblProdCountry.gridx = 1;
        		gbc_flblProdCountry.gridy = 9;
        		formpanel.add(flbCountry, gbc_flblProdCountry);
        		
                formpanel.removeAll();
                formpanel.invalidate();
        		formpanel.add(lblProdName, gbc_lblProdName);
        		formpanel.add(tfProdName, gbc_prodName);
                formpanel.add(lbPrice, gbc_lblProductPrice);
           		formpanel.add(flbPrice, gbc_flblProductPrice);
        		formpanel.add(lbRating, gbc_lblProdRating);
        		formpanel.add(flbRating, gbc_flblProdRating);
           		formpanel.add(lbMake, gbc_lblProdMake);
        		formpanel.add(flbMake, gbc_flblProdMake);
        		formpanel.add(lbCountry, gbc_lblProdCountry);
        		formpanel.add(flbCountry, gbc_flblProdCountry);
        		formpanel.revalidate();
        		formpanel.repaint();
            }
        });
		
		tfPartName.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent event) {
                JComboBox<String> tfPartName = (JComboBox<String>)event.getSource();
                Object selected = tfPartName.getSelectedItem();
                System.out.println("Selected Item  = " + selected);
                
        		SessionFactory sessFact = HibernateUtil.getSessionFactory();
        		session = sessFact.getCurrentSession();
        		org.hibernate.Transaction tr = session.beginTransaction();

        		CriteriaBuilder builder = session.getCriteriaBuilder();
                javax.persistence.criteria.CriteriaQuery<PartModel> query = builder.createQuery(PartModel.class);
                Root<PartModel> root = query.from(PartModel.class);    
                query.select(root).where(builder.equal(root.get("partName"),selected));  
                Query<PartModel> q=session.createQuery(query);             
                PartModel product=q.getSingleResult();
                System.out.println(product.getpartName());               

                tr.commit();
                
        		JLabel lblPartProfile = new JLabel("Profile");
        		lblPartProfile.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_lblPartProfile = new GridBagConstraints();
        		gbc_lblPartProfile.anchor = GridBagConstraints.EAST;
        		gbc_lblPartProfile.insets = new Insets(10, 10, 10, 5);
        		gbc_lblPartProfile.gridx = 0;
        		gbc_lblPartProfile.gridy = 3;
        		pformpanel.add(lblPartProfile, gbc_lblPartProfile);
        		
        		JLabel flblPartProfile = new JLabel(product.getpartProf());
        		flblPartProfile.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_flblPartProfile = new GridBagConstraints();
        		gbc_flblPartProfile.anchor = GridBagConstraints.EAST;
        		gbc_flblPartProfile.insets = new Insets(10, 10, 10, 5);
        		gbc_flblPartProfile.gridx = 1;
        		gbc_flblPartProfile.gridy = 3;
        		pformpanel.add(flblPartProfile, gbc_flblPartProfile);
        		
        		JLabel lblPartSpec = new JLabel("Specification");
        		lblPartSpec.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_lblPartSpec = new GridBagConstraints();
        		gbc_lblPartSpec.anchor = GridBagConstraints.EAST;
        		gbc_lblPartSpec.insets = new Insets(10, 10, 10, 5);
        		gbc_lblPartSpec.gridx = 0;
        		gbc_lblPartSpec.gridy = 5;
        		pformpanel.add(lblPartSpec, gbc_lblPartSpec);
        		
           		JLabel flblPartSpec = new JLabel(product.getpartSpec());
        		flblPartSpec.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_flblPartSpec = new GridBagConstraints();
        		gbc_flblPartSpec.anchor = GridBagConstraints.EAST;
        		gbc_flblPartSpec.insets = new Insets(10, 10, 10, 5);
        		gbc_flblPartSpec.gridx = 1;
        		gbc_flblPartSpec.gridy = 5;
        		pformpanel.add(flblPartSpec, gbc_flblPartSpec);
        		
        		JLabel lblPartMat = new JLabel("Material");
        		lblPartMat.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_lblPartMat = new GridBagConstraints();
        		gbc_lblPartMat.anchor = GridBagConstraints.EAST;
        		gbc_lblPartMat.insets = new Insets(10, 10, 10, 5);
        		gbc_lblPartMat.gridx = 0;
        		gbc_lblPartMat.gridy = 7;
        		pformpanel.add(lblPartMat, gbc_lblPartMat);
        		
        		JLabel flblPartMat = new JLabel(product.getpartMaterial());
        		flblPartMat.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_flblPartMat = new GridBagConstraints();
        		gbc_flblPartMat.anchor = GridBagConstraints.EAST;
        		gbc_flblPartMat.insets = new Insets(10, 10, 10, 5);
        		gbc_flblPartMat.gridx = 1;
        		gbc_flblPartMat.gridy = 7;
        		pformpanel.add(flblPartMat, gbc_flblPartMat);
        		
        		//Typical Price 
        	    JLabel lblPartTypDlvTime = new JLabel("Typical Delivery Time");
        	    lblPartTypDlvTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_lblPartTypDlvTime = new GridBagConstraints();
        		gbc_lblPartTypDlvTime.anchor = GridBagConstraints.EAST;
        		gbc_lblPartTypDlvTime.insets = new Insets(10, 10, 10, 5);
        		gbc_lblPartTypDlvTime.gridx = 0;
        		gbc_lblPartTypDlvTime.gridy = 9;
        		pformpanel.add(lblPartTypDlvTime, gbc_lblPartTypDlvTime);
        		
        		//Typical Price 
        	    JLabel tflblPartTypDlvTime = new JLabel(Integer.toString(product.getpartTypSuppSch()));
        	    tflblPartTypDlvTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_tflblPartTypDlvTime = new GridBagConstraints();
        		gbc_tflblPartTypDlvTime.anchor = GridBagConstraints.EAST;
        		gbc_tflblPartTypDlvTime.insets = new Insets(10, 10, 10, 5);
        		gbc_tflblPartTypDlvTime.gridx = 1;
        		gbc_tflblPartTypDlvTime.gridy = 9;
        		pformpanel.add(tflblPartTypDlvTime, gbc_tflblPartTypDlvTime);
        		
        		//Typical Price 
        	    JLabel lblPartTypPrice = new JLabel("Typical Price($)");
        	    lblPartTypPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_lblPartTypPrice = new GridBagConstraints();
        		gbc_lblPartTypPrice.anchor = GridBagConstraints.EAST;
        		gbc_lblPartTypPrice.insets = new Insets(10, 10, 10, 5);
        		gbc_lblPartTypPrice.gridx = 0;
        		gbc_lblPartTypPrice.gridy = 11;
        		pformpanel.add(lblPartTypPrice, gbc_lblPartTypPrice);
        		
           	    JLabel tflblPartTypPrice = new JLabel(Integer.toString(product.getpartTypPrice()));
        	    tflblPartTypPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_tflblPartTypPrice = new GridBagConstraints();
        		gbc_tflblPartTypPrice.anchor = GridBagConstraints.EAST;
        		gbc_tflblPartTypPrice.insets = new Insets(10, 10, 10, 5);
        		gbc_tflblPartTypPrice.gridx = 1;
        		gbc_tflblPartTypPrice.gridy = 11;
        		pformpanel.add(tflblPartTypPrice, gbc_tflblPartTypPrice);
           	    
        		
                pformpanel.removeAll();
                pformpanel.invalidate();
                
        		pformpanel.add(lblPartName, gbc_lblPartName);
        		pformpanel.add(tfPartName, gbc_partName);
        		pformpanel.add(lblPartProfile, gbc_lblPartProfile);
                pformpanel.add(flblPartProfile, gbc_flblPartProfile);
                pformpanel.add(lblPartSpec, gbc_lblPartSpec);
                pformpanel.add(flblPartSpec, gbc_flblPartSpec);
                pformpanel.add(lblPartMat, gbc_lblPartMat);
            	pformpanel.add(flblPartMat, gbc_flblPartMat);
            	pformpanel.add(lblPartTypDlvTime, gbc_lblPartTypDlvTime);
            	pformpanel.add(tflblPartTypDlvTime, gbc_tflblPartTypDlvTime);
            	pformpanel.add(lblPartTypPrice, gbc_lblPartTypPrice);
            	pformpanel.add(tflblPartTypPrice, gbc_tflblPartTypPrice);

        		pformpanel.revalidate();
        		pformpanel.repaint();
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
	    getContentPane().add(formpanel, BorderLayout.WEST);
	    getContentPane().add(pformpanel, BorderLayout.EAST);
	    getContentPane().add(bp, BorderLayout.SOUTH);
	    pack();
	    setResizable(true);
	    setLocationRelativeTo(parent1);	    
    }//End of BoMAddUI()

}//End of Class BomUI
