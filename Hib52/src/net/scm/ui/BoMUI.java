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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.persistence.criteria.*;

import org.hibernate.Criteria;
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
	public JTextField tfPartQty;
	public JLabel flblPartID;
	public String insProdId;
	public String insPartId;
	public String insProdName;
	public String insPartName;
	public Integer insPartQty;
	public JLabel flblProdID;
	public JButton btnSave;
	public JButton btnReset;
	public JButton btnMenu;
	
	public BoMUI(JFrame parent)
	{
		super(parent, "Vendor Management System", true);
		parent1=parent;
	}//End of Constructor 
	
	//Method to Add Line Items to the BOM DB 
	public void BoMAddUI()
    {   
    	//Create Panel for Title 
    	JPanel title = new JPanel();
    	title.setBackground(Color.decode("#006666"));
    	title.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
	    title.setPreferredSize(new Dimension(720,30));
        title.setOpaque(true);

    	//Create Panel for Menu       
    	JPanel formpanel = new JPanel(new GridBagLayout());
        formpanel.setPreferredSize(new Dimension(200, 400));
        formpanel.setMaximumSize(new Dimension(200, 400));
        formpanel.setMinimumSize(new Dimension(200, 400));
		formpanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
		formpanel.setBackground(Color.white);
		GridBagLayout gBL = new GridBagLayout();
		gBL.columnWidths = new int[]{100, 67, 86, 86, 0};
		gBL.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gBL.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gBL.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		
    	JPanel pformpanel = new JPanel(new GridBagLayout());
        pformpanel.setPreferredSize(new Dimension(520, 400));
        pformpanel.setMaximumSize(new Dimension(520, 400));
        pformpanel.setMinimumSize(new Dimension(520, 400));
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
        bp.setPreferredSize(new Dimension(720, 40));  
        bp.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
        bp.setOpaque(true);
   
		//Adding Components for the Title Panel    
	    JLabel ltitle = new JLabel("Vendor Management Portal >> Plan >> Bill Of Materials");
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
	    
		tfPartQty = new JTextField();
	    
		JLabel lblProdName = new JLabel("Select Product");
		lblProdName.setFont(new Font("Tahoma", Font.PLAIN, 12));
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
        //for (String name : prodNameList) {
        //    System.out.println(name);
        //}	
        tr.commit();
        
        String[] prodStrArr = new String[prodNameList.size()];
        prodNameList.toArray(prodStrArr );
		
	    JComboBox<String> tfProdName = new JComboBox<String>();
		tfProdName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfProdName.setModel(new DefaultComboBoxModel<String>(prodStrArr));
		GridBagConstraints gbc_prodName = new GridBagConstraints();
		gbc_prodName.gridwidth = 2;
		gbc_prodName.insets = new Insets(10, 10, 10, 5);
		gbc_prodName.anchor = GridBagConstraints.NORTHWEST;
		gbc_prodName.gridx = 0;
		gbc_prodName.gridy = 2;
		formpanel.add(tfProdName, gbc_prodName);
		
		JLabel lblPartName = new JLabel("Select Part to Add");
		lblPartName.setFont(new Font("Tahoma", Font.PLAIN, 12));
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
        //for (String name : partNameList) {
        //    System.out.println(name);
        //}
		
        tr1.commit();
        
        String[] partStrArr = new String[partNameList.size()];
        partNameList.toArray(partStrArr );
        
	    JComboBox<String> tfPartName = new JComboBox<String>();
		tfPartName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfPartName.setModel(new DefaultComboBoxModel<String>(partStrArr));
		GridBagConstraints gbc_partName = new GridBagConstraints();
		gbc_partName.gridwidth = 2;
		gbc_partName.insets = new Insets(10, 10, 10, 5);
		gbc_partName.anchor = GridBagConstraints.NORTHWEST;
		gbc_partName.gridx = 1;
		gbc_partName.gridy = 1;
		pformpanel.add(tfPartName, gbc_partName);
	    
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
                
                JLabel lblProdID = new JLabel("Product ID");
        		lblProdID.setFont(new Font("Tahoma", Font.PLAIN, 12));
        		GridBagConstraints gbc_lblProdID = new GridBagConstraints();
        		gbc_lblProdID.anchor = GridBagConstraints.EAST;
        		gbc_lblProdID.insets = new Insets(10, 10, 10, 5);
        		gbc_lblProdID.gridx = 0;
        		gbc_lblProdID.gridy = 3;
        		formpanel.add(lblProdID, gbc_lblProdID);
        		
        		JLabel flblProdID = new JLabel(product.getProdId());
        		insProdId = product.getProdId();
        		flblProdID.setFont(new Font("Tahoma", Font.PLAIN, 12));
        		GridBagConstraints gbc_flblProdID = new GridBagConstraints();
        		gbc_flblProdID.anchor = GridBagConstraints.NORTHWEST;
        		gbc_flblProdID.insets = new Insets(10, 10, 10, 5);
        		gbc_flblProdID.gridx = 1;
        		gbc_flblProdID.gridy = 3;
        		formpanel.add(flblProdID, gbc_flblProdID);
                
        		JLabel lbPrice = new JLabel("Price ");
        		lbPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
        		GridBagConstraints gbc_lblProductPrice = new GridBagConstraints();
        		gbc_lblProductPrice.anchor = GridBagConstraints.EAST;
        		gbc_lblProductPrice.insets = new Insets(10, 10, 10, 5);
        		gbc_lblProductPrice.gridx = 0;
        		gbc_lblProductPrice.gridy = 5;
        		formpanel.add(lbPrice, gbc_lblProductPrice);
        		
        		JLabel flbPrice = new JLabel(Integer.toString(product.getProdPrice()));
        		flbPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
        		GridBagConstraints gbc_flblProductPrice = new GridBagConstraints();
        		gbc_flblProductPrice.anchor = GridBagConstraints.NORTHWEST;
        		gbc_flblProductPrice.insets = new Insets(10, 10, 10, 5);
        		gbc_flblProductPrice.gridx = 1;
        		gbc_flblProductPrice.gridy = 5;
        		formpanel.add(flbPrice, gbc_flblProductPrice);
        		
        		JLabel lbRating = new JLabel("Rating");
        		lbRating.setFont(new Font("Tahoma", Font.PLAIN, 12));
        		GridBagConstraints gbc_lblProdRating = new GridBagConstraints();
        		gbc_lblProdRating.anchor = GridBagConstraints.EAST;
        		gbc_lblProdRating.insets = new Insets(10, 10, 10, 5);
        		gbc_lblProdRating.gridx = 0;
        		gbc_lblProdRating.gridy = 7;
        		formpanel.add(lbRating, gbc_lblProdRating);
        		
           		JLabel flbRating = new JLabel(product.getProdRating());
        		flbRating.setFont(new Font("Tahoma", Font.PLAIN, 12));
        		GridBagConstraints gbc_flblProdRating = new GridBagConstraints();
        		gbc_flblProdRating.anchor = GridBagConstraints.NORTHWEST;
        		gbc_flblProdRating.insets = new Insets(10, 10, 10, 5);
        		gbc_flblProdRating.gridx = 1;
        		gbc_flblProdRating.gridy = 7;
        		formpanel.add(flbRating, gbc_flblProdRating);
        		
        		JLabel lbMake = new JLabel("Make");
        		lbMake.setFont(new Font("Tahoma", Font.PLAIN, 12));
        		GridBagConstraints gbc_lblProdMake = new GridBagConstraints();
        		gbc_lblProdMake.anchor = GridBagConstraints.EAST;
        		gbc_lblProdMake.insets = new Insets(10, 10, 10, 5);
        		gbc_lblProdMake.gridx = 0;
        		gbc_lblProdMake.gridy = 9;
        		formpanel.add(lbMake, gbc_lblProdMake);
        		
        		JLabel flbMake = new JLabel(product.getProdMake());
        		flbMake.setFont(new Font("Tahoma", Font.PLAIN, 12));
        		GridBagConstraints gbc_flblProdMake = new GridBagConstraints();
        		gbc_flblProdMake.anchor = GridBagConstraints.NORTHWEST;
        		gbc_flblProdMake.insets = new Insets(10, 10, 10, 5);
        		gbc_flblProdMake.gridx = 1;
        		gbc_flblProdMake.gridy = 9;
        		formpanel.add(flbMake, gbc_flblProdMake);
        		
        		JLabel lbCountry = new JLabel("Country");
        		lbCountry.setFont(new Font("Tahoma", Font.PLAIN, 12));
        		GridBagConstraints gbc_lblProdCountry = new GridBagConstraints();
        		gbc_lblProdCountry.anchor = GridBagConstraints.EAST;
        		gbc_lblProdCountry.insets = new Insets(10, 10, 10, 5);
        		gbc_lblProdCountry.gridx = 0;
        		gbc_lblProdCountry.gridy = 11;
        		formpanel.add(lbCountry, gbc_lblProdCountry);
        		
        		JLabel flbCountry = new JLabel(product.getProdCountry());
        		flbCountry.setFont(new Font("Tahoma", Font.PLAIN, 12));
        		GridBagConstraints gbc_flblProdCountry = new GridBagConstraints();
        		gbc_flblProdCountry.anchor = GridBagConstraints.NORTHWEST;
        		gbc_flblProdCountry.insets = new Insets(10, 10, 10, 5);
        		gbc_flblProdCountry.gridx = 1;
        		gbc_flblProdCountry.gridy = 11;
        		formpanel.add(flbCountry, gbc_flblProdCountry);
        		
                formpanel.removeAll();
                formpanel.invalidate();
        		formpanel.add(lblProdName, gbc_lblProdName);
        		formpanel.add(tfProdName, gbc_prodName);
        		formpanel.add(lblProdID, gbc_lblProdID);
        		formpanel.add(flblProdID, gbc_flblProdID);
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
		
	    // Create an ActionListener for the PART JComboBox component.		
		tfPartName.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent event) 
            {
                JComboBox<String> tfPartName = (JComboBox<String>)event.getSource();
                Object selected = tfPartName.getSelectedItem();
                System.out.println("Selected Item  = " + selected);
                insPartName = (String) selected;
                
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
                
                JLabel lblPartID = new JLabel("PartID");
        		lblPartID.setFont(new Font("Tahoma", Font.PLAIN, 12));
        		GridBagConstraints gbc_lblPartID = new GridBagConstraints();
        		gbc_lblPartID.anchor = GridBagConstraints.EAST;
        		gbc_lblPartID.insets = new Insets(10, 10, 10, 5);
        		gbc_lblPartID.gridx = 0;
        		gbc_lblPartID.gridy = 3;
        		pformpanel.add(lblPartID, gbc_lblPartID);
        		
        		JLabel flblPartID = new JLabel(product.getpartId());
        		insPartId = product.getpartId();
        		flblPartID.setFont(new Font("Tahoma", Font.PLAIN, 12));
        		GridBagConstraints gbc_flblPartID = new GridBagConstraints();
        		gbc_flblPartID.anchor = GridBagConstraints.NORTHWEST;
        		gbc_flblPartID.insets = new Insets(10, 10, 10, 5);
        		gbc_flblPartID.gridx = 1;
        		gbc_flblPartID.gridy = 3;
        		pformpanel.add(flblPartID, gbc_flblPartID);
                
        		JLabel lblPartProfile = new JLabel("Profile");
        		lblPartProfile.setFont(new Font("Tahoma", Font.PLAIN, 12));
        		GridBagConstraints gbc_lblPartProfile = new GridBagConstraints();
        		gbc_lblPartProfile.anchor = GridBagConstraints.EAST;
        		gbc_lblPartProfile.insets = new Insets(10, 10, 10, 5);
        		gbc_lblPartProfile.gridx = 0;
        		gbc_lblPartProfile.gridy = 5;
        		pformpanel.add(lblPartProfile, gbc_lblPartProfile);
        		
        		JLabel flblPartProfile = new JLabel(product.getpartProf());
        		flblPartProfile.setFont(new Font("Tahoma", Font.PLAIN, 12));
        		GridBagConstraints gbc_flblPartProfile = new GridBagConstraints();
        		gbc_flblPartProfile.anchor = GridBagConstraints.NORTHWEST;
        		gbc_flblPartProfile.insets = new Insets(10, 10, 10, 5);
        		gbc_flblPartProfile.gridx = 1;
        		gbc_flblPartProfile.gridy = 5;
        		pformpanel.add(flblPartProfile, gbc_flblPartProfile);
        		
        		JLabel lblPartSpec = new JLabel("Specification");
        		lblPartSpec.setFont(new Font("Tahoma", Font.PLAIN, 12));
        		GridBagConstraints gbc_lblPartSpec = new GridBagConstraints();
        		gbc_lblPartSpec.anchor = GridBagConstraints.EAST;
        		gbc_lblPartSpec.insets = new Insets(10, 10, 10, 5);
        		gbc_lblPartSpec.gridx = 0;
        		gbc_lblPartSpec.gridy = 7;
        		pformpanel.add(lblPartSpec, gbc_lblPartSpec);
        		
           		JLabel flblPartSpec = new JLabel(product.getpartSpec());
        		flblPartSpec.setFont(new Font("Tahoma", Font.PLAIN, 12));
        		GridBagConstraints gbc_flblPartSpec = new GridBagConstraints();
        		gbc_flblPartSpec.anchor = GridBagConstraints.NORTHWEST;
        		gbc_flblPartSpec.insets = new Insets(10, 10, 10, 5);
        		gbc_flblPartSpec.gridx = 1;
        		gbc_flblPartSpec.gridy = 7;
        		pformpanel.add(flblPartSpec, gbc_flblPartSpec);
        		
        		JLabel lblPartMat = new JLabel("Material");
        		lblPartMat.setFont(new Font("Tahoma", Font.PLAIN, 12));
        		GridBagConstraints gbc_lblPartMat = new GridBagConstraints();
        		gbc_lblPartMat.anchor = GridBagConstraints.EAST;
        		gbc_lblPartMat.insets = new Insets(10, 10, 10, 5);
        		gbc_lblPartMat.gridx = 0;
        		gbc_lblPartMat.gridy = 9;
        		pformpanel.add(lblPartMat, gbc_lblPartMat);
        		
        		JLabel flblPartMat = new JLabel(product.getpartMaterial());
        		flblPartMat.setFont(new Font("Tahoma", Font.PLAIN, 12));
        		GridBagConstraints gbc_flblPartMat = new GridBagConstraints();
        		gbc_flblPartMat.anchor = GridBagConstraints.NORTHWEST;
        		gbc_flblPartMat.insets = new Insets(10, 10, 10, 5);
        		gbc_flblPartMat.gridx = 1;
        		gbc_flblPartMat.gridy = 9;
        		pformpanel.add(flblPartMat, gbc_flblPartMat);
        		
        		//Typical Delivery Schedule 
        	    JLabel lblPartTypDlvTime = new JLabel("Typical Delivery Time");
        	    lblPartTypDlvTime.setFont(new Font("Tahoma", Font.PLAIN, 12));
        		GridBagConstraints gbc_lblPartTypDlvTime = new GridBagConstraints();
        		gbc_lblPartTypDlvTime.anchor = GridBagConstraints.EAST;
        		gbc_lblPartTypDlvTime.insets = new Insets(10, 10, 10, 5);
        		gbc_lblPartTypDlvTime.gridx = 0;
        		gbc_lblPartTypDlvTime.gridy = 11;
        		pformpanel.add(lblPartTypDlvTime, gbc_lblPartTypDlvTime);
        		
        	    JLabel tflblPartTypDlvTime = new JLabel(Integer.toString(product.getpartTypSuppSch()));
        	    tflblPartTypDlvTime.setFont(new Font("Tahoma", Font.PLAIN, 12));
        		GridBagConstraints gbc_tflblPartTypDlvTime = new GridBagConstraints();
        		gbc_tflblPartTypDlvTime.anchor = GridBagConstraints.NORTHWEST;
        		gbc_tflblPartTypDlvTime.insets = new Insets(10, 10, 10, 5);
        		gbc_tflblPartTypDlvTime.gridx = 1;
        		gbc_tflblPartTypDlvTime.gridy = 11;
        		pformpanel.add(tflblPartTypDlvTime, gbc_tflblPartTypDlvTime);
        		
        		//Typical Price 
        	    JLabel lblPartTypPrice = new JLabel("Typical Price($)");
        	    lblPartTypPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
        		GridBagConstraints gbc_lblPartTypPrice = new GridBagConstraints();
        		gbc_lblPartTypPrice.anchor = GridBagConstraints.EAST;
        		gbc_lblPartTypPrice.insets = new Insets(10, 10, 10, 5);
        		gbc_lblPartTypPrice.gridx = 0;
        		gbc_lblPartTypPrice.gridy = 13;
        		pformpanel.add(lblPartTypPrice, gbc_lblPartTypPrice);
        		
           	    JLabel tflblPartTypPrice = new JLabel(Integer.toString(product.getpartTypPrice()));
        	    tflblPartTypPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
        		GridBagConstraints gbc_tflblPartTypPrice = new GridBagConstraints();
        		gbc_tflblPartTypPrice.anchor = GridBagConstraints.NORTHWEST;
        		gbc_tflblPartTypPrice.insets = new Insets(10, 10, 10, 5);
        		gbc_tflblPartTypPrice.gridx = 1;
        		gbc_tflblPartTypPrice.gridy = 13;
        		pformpanel.add(tflblPartTypPrice, gbc_tflblPartTypPrice);
        		
        		//Quantity need for Bill of Materials 
        	    JLabel lblPartQty = new JLabel("Quantity");
        	    lblPartQty.setFont(new Font("Tahoma", Font.PLAIN, 12));
        		GridBagConstraints gbc_lblPartQty = new GridBagConstraints();
        		gbc_lblPartQty.anchor = GridBagConstraints.EAST;
        		gbc_lblPartQty.insets = new Insets(10, 10, 10, 5);
        		gbc_lblPartQty.gridx = 0;
        		gbc_lblPartQty.gridy = 15;
        		pformpanel.add(lblPartQty, gbc_lblPartQty);
        		
        		//JTextField tfPartQty = new JTextField();
        		tfPartQty.setText(" ");
        		tfPartQty.setFont(new Font("Tahoma", Font.PLAIN, 12));
        		tfPartQty.setColumns(5);
        		GridBagConstraints gbc_tfpartQty = new GridBagConstraints();
        		gbc_tfpartQty.insets = new Insets(10, 10, 10, 5);
        		gbc_tfpartQty.anchor = GridBagConstraints.NORTHWEST;
        		gbc_tfpartQty.gridx = 1;
        		gbc_tfpartQty.gridy =15;
        		pformpanel.add(tfPartQty, gbc_tfpartQty);         	    
                
        		pformpanel.removeAll();
                pformpanel.invalidate();
                
        		pformpanel.add(lblPartName, gbc_lblPartName);
        		pformpanel.add(tfPartName, gbc_partName);
        		pformpanel.add(lblPartID, gbc_lblPartID);
        		pformpanel.add(flblPartID, gbc_flblPartID);
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
            	pformpanel.add(lblPartQty, gbc_lblPartQty);
            	pformpanel.add(tfPartQty, gbc_tfpartQty);

        		pformpanel.revalidate();
        		pformpanel.repaint();
            }
        });
		
		tfPartQty.addActionListener(new ActionListener() 
		{	       	 
	         public void actionPerformed(ActionEvent e) {
	        	 System.out.println("! You have chosen Plan->BoM->Qty !!!");  
	        	 insPartQty = (Integer.parseInt(tfPartQty.getText()));
	         }
	    });	
		
        btnSave.addActionListener(new ActionListener() 
        {        	 
            public void actionPerformed(ActionEvent e) 
            {
        		
         		SessionFactory sessFact = HibernateUtil.getSessionFactory();
        		session = sessFact.getCurrentSession();
        		org.hibernate.Transaction tr = session.beginTransaction();
        		BoMModel bomModel = new BoMModel();
        		bomModel.setbomProdId(insProdId);
        		bomModel.setbomPartId(insPartId);
        		bomModel.setbomProdName(insProdName);
        		bomModel.setbomPartName(insPartName);
        		bomModel.setbomPartQty(Integer.parseInt(tfPartQty.getText().trim()));
         		session.save(bomModel);
        		tr.commit();
        		System.out.println("Successfully inserted BoM Info");        		
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
	    getContentPane().add(formpanel, BorderLayout.WEST);
	    getContentPane().add(pformpanel, BorderLayout.EAST);
	    getContentPane().add(bp, BorderLayout.SOUTH);
	    pack();
	    setResizable(true);
	    setLocationRelativeTo(parent1);	    
    }//End of BoMAddUI()
	
	//Method to display all the BOM info from the DB 
    public void BoMListAllUI()
    {   	
    	List<BoMModel> BoMs;
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

			Criteria criteria = session.createCriteria(BoMModel.class);
    		BoMs = criteria.list();

    		Iterator<BoMModel> itr = BoMs.iterator();
    		
    		/*
    		while (itr.hasNext()) {
    			BoMModel BoMM = itr.next();
      		    System.out.println(BoMM.getbomProdName());
    		    System.out.println(prodM.getId());
    			System.out.println(prodM.getProdPrice());
    			System.out.println(prodM.getProdRating());
    			System.out.println(prodM.getProdMake());
       			System.out.println(prodM.getProdCountry());
       		 
    		}*/
    		tr.commit();
    		System.out.println("Data displayed for Product");

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
        bp.setPreferredSize(new Dimension(720, 40));  
    	title.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
        bp.setOpaque(true);
        
	    JLabel ltitle = new JLabel("Vendor Management Portal >> View >> Bill of Materials");
	    ltitle.setHorizontalAlignment(JLabel.CENTER);
	    ltitle.setVerticalAlignment(JLabel.CENTER);
	    ltitle.setFont(new Font("Arial",Font.TRUETYPE_FONT,16));
	    ltitle.setForeground(Color.WHITE);
	    title.add(ltitle);    
    	
    	
        JPanel hp = new JPanel();
        hp.setLayout(new BorderLayout());
            	
       	JLabel lblHeading = new JLabel("List of BOM entries", SwingConstants.CENTER);
       	lblHeading.setFont(new Font("Arial",Font.TRUETYPE_FONT,24));
       	lblHeading.setForeground (Color.green);
       	lblHeading.setBackground (Color.white);
       	lblHeading.setOpaque(true); 
       	hp.add(lblHeading,BorderLayout.CENTER);
       	
    	//create the model
        BoMTableModel model = new BoMTableModel(BoMs);
        
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
     } //End of BoMListAllUI()


}//End of Class BomUI
