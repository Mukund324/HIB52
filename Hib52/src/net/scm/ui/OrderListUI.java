package net.scm.ui;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import net.scm.core.*;
import net.scm.model.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.hibernate.Session;

public class OrderListUI extends JDialog {
	
	private static final long serialVersionUID = 1L;
	public static final String RUPEE = "\u20B9";
	public JFrame parent1;
	public Session session;

	public String ProdCycProdId;
	public String ProdCycProdName;
	public String ProdCycName;
	public Date ProdCycStDt;
	public Integer ProdCycCapPrice;
	
	public JButton btnGen;
	public JButton btnReset;
	public JButton btnMenu;
	
	ButtonGroup btngrpOptCrit;
	public Boolean selCritPrice=false;
	public Boolean selCritQuality=false;
	public Boolean selCritLeadTime=false;
	public Boolean selCritOptimal=false;
	Popup po;
	JPanel p1;
	PopupFactory pf;
	
	
	public OrderListUI(JFrame parent)
	{
		super(parent, "Vendor Management System", true);
		parent1=parent;
	}//End of Constructor 
	
	//Method to Add Line Items to the BOM DB 
	public void OrderListGenUI()
    {   
    	//Create Panel for Title 
    	JPanel title = new JPanel();
    	title.setBackground(Color.decode("#006666"));
    	title.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
	    title.setPreferredSize(new Dimension(640,30));
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
        pformpanel.setPreferredSize(new Dimension(440, 400));
        pformpanel.setMaximumSize(new Dimension(440, 400));
        pformpanel.setMinimumSize(new Dimension(440, 400));
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
	    
	    btnGen = new JButton("Generate Order");
		btnReset = new JButton("Reset");
	    btnMenu = new JButton("Back to Menu");
	    bp.add(btnGen);
	    bp.add(btnReset);
	    bp.add(btnMenu);
	    
    	pf = new PopupFactory();  
	    p1 = new JPanel(); 
	    p1.setBackground(Color.blue); 
	    JLabel l = new JLabel("This  is a popup menu"); 
        p1.add(l);
        // create a popup 
	    Popup po = pf.getPopup(parent1, p1, 180, 100);        
	    
	    //Production Cycle Name
	    JLabel lbProdCycName = new JLabel("PRODUCTION BATCH NAME");
	    lbProdCycName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbProdCycName = new GridBagConstraints();
		gbc_lbProdCycName.anchor = GridBagConstraints.EAST;
		gbc_lbProdCycName.insets = new Insets(10, 10, 10, 5);
		gbc_lbProdCycName.gridx = 0;
		gbc_lbProdCycName.gridy = 0;
		formpanel.add(lbProdCycName, gbc_lbProdCycName);
		
		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		session = sessFact.getCurrentSession();
		org.hibernate.Transaction tr = session.beginTransaction();

		CriteriaBuilder builder = session.getCriteriaBuilder();

        javax.persistence.criteria.CriteriaQuery<String> query = builder.createQuery(String.class);
	    Root<ProdCycleModel> root = query.from(ProdCycleModel.class); 
        query.select(root.get("prodcycName"));
        Query<String> q=session.createQuery(query);
        List<String> prodcycNameList=q.getResultList();
        //for (String name : prodcycNameList) {
        //    System.out.println(name);
        //}	
        tr.commit();
        
        String[] prodcycStrArr = new String[prodcycNameList.size()];
        prodcycNameList.toArray(prodcycStrArr);
		
	    JComboBox<String> tfProdCycName = new JComboBox<String>();
		tfProdCycName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfProdCycName.setModel(new DefaultComboBoxModel<String>(prodcycStrArr));
		GridBagConstraints gbc_ProdCycName = new GridBagConstraints();
		gbc_ProdCycName.gridwidth = 1;
		gbc_ProdCycName.insets = new Insets(10, 10, 10, 5);
		gbc_ProdCycName.anchor = GridBagConstraints.NORTHWEST;
		gbc_ProdCycName.gridx = 0;
		gbc_ProdCycName.gridy = 1;
		formpanel.add(tfProdCycName, gbc_ProdCycName);
	    
	    // Create an ActionListener for the PRODUCT JComboBox component.
		tfProdCycName.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent event) 
            {
                JComboBox<String> tfProdCycName = (JComboBox<String>)event.getSource();
                Object selected = tfProdCycName.getSelectedItem();
                System.out.println("Selected Item  = " + selected);
                //String insProdName = (String) selected;
                
        		SessionFactory sessFact = HibernateUtil.getSessionFactory();
        		session = sessFact.getCurrentSession();
        		org.hibernate.Transaction tr = session.beginTransaction();

        		CriteriaBuilder builder = session.getCriteriaBuilder();
                javax.persistence.criteria.CriteriaQuery<ProdCycleModel> query = builder.createQuery(ProdCycleModel.class);
                Root<ProdCycleModel> root = query.from(ProdCycleModel.class);    
                query.select(root).where(builder.equal(root.get("prodcycName"),selected));  
                Query<ProdCycleModel> q=session.createQuery(query);             
                ProdCycleModel prodcyc=q.getSingleResult();
                System.out.println(prodcyc.getprodcycName()); 
                System.out.println(prodcyc.getprodcycId()); 

                tr.commit();
                //Assign the Product ID needed for search from the ProdCyc choice to search the BoM for the list of Part Names
                ProdCycProdId= prodcyc.getprodcycId();
                ProdCycName = prodcyc.getprodcycName();
                ProdCycProdName= prodcyc.getprodcycProd() ;
                ProdCycStDt = prodcyc.getprodcycStDt() ;
            	ProdCycCapPrice= prodcyc.getprodcycCapPrice();
                
        	    //Production Cycle ID
        	    JLabel lbProdCycId = new JLabel("PRODUCTION BATCH ID");
        	    lbProdCycId.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_lbProdCycId = new GridBagConstraints();
        		gbc_lbProdCycId.anchor = GridBagConstraints.EAST;
        		gbc_lbProdCycId.insets = new Insets(10, 10, 10, 5);
        		gbc_lbProdCycId.gridx = 0;
        		gbc_lbProdCycId.gridy = 0;
        		pformpanel.add(lbProdCycId, gbc_lbProdCycId);
        		
        		JLabel tfProdCycId = new JLabel(prodcyc.getprodcycId());
        		tfProdCycId.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_ProdCycId = new GridBagConstraints();
        		gbc_ProdCycId.gridwidth = 1;
        		gbc_ProdCycId.insets = new Insets(10, 10, 10, 5);
        		gbc_ProdCycId.anchor = GridBagConstraints.NORTHWEST;
        		gbc_ProdCycId.gridx = 1;
        		gbc_ProdCycId.gridy = 0;
        		pformpanel.add(tfProdCycId, gbc_ProdCycId);
        		
        	    //Production Cycle Name
        	    JLabel lbProdCycName = new JLabel("PRODUCTION BATCH NAME");
        	    lbProdCycName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_lbProdCycName = new GridBagConstraints();
        		gbc_lbProdCycName.anchor = GridBagConstraints.EAST;
        		gbc_lbProdCycName.insets = new Insets(10, 10, 10, 5);
        		gbc_lbProdCycName.gridx = 0;
        		gbc_lbProdCycName.gridy = 3;
        		pformpanel.add(lbProdCycName, gbc_lbProdCycName);
        		
        		JLabel tfProdCycName1 = new JLabel(prodcyc.getprodcycName());
        		tfProdCycName1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_ProdCycName1 = new GridBagConstraints();
        		gbc_ProdCycName1.gridwidth = 1;
        		gbc_ProdCycName1.insets = new Insets(10, 10, 10, 5);
        		gbc_ProdCycName1.anchor = GridBagConstraints.NORTHWEST;
        		gbc_ProdCycName1.gridx = 1;
        		gbc_ProdCycName1.gridy = 3;
        		pformpanel.add(tfProdCycName1, gbc_ProdCycName1);
        		
        	    //Production Cycle Start Date
        	    JLabel lbProdCycStDt = new JLabel("START DATE");
        	    lbProdCycStDt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_lbProdCycStDt = new GridBagConstraints();
        		gbc_lbProdCycStDt.anchor = GridBagConstraints.EAST;
        		gbc_lbProdCycStDt.insets = new Insets(10, 10, 10, 5);
        		gbc_lbProdCycStDt.gridx = 0;
        		gbc_lbProdCycStDt.gridy = 5;
        		pformpanel.add(lbProdCycStDt, gbc_lbProdCycStDt);
        		
		    	//SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
                DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
                String chDate = dateFormat.format(prodcyc.getprodcycStDt());  
		    	System.out.println("Date Selected is :" + chDate);
        		JLabel tfProdCycStDt = new JLabel(dateFormat.format(prodcyc.getprodcycStDt()));
		    	//JLabel tfProdCycStDt = new JLabel("Helleoooooo");
        		tfProdCycStDt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_ProdCycStDt = new GridBagConstraints();
        		gbc_ProdCycStDt.gridwidth = 1;
        		gbc_ProdCycStDt.insets = new Insets(10, 10, 10, 5);
        		gbc_ProdCycStDt.anchor = GridBagConstraints.NORTHWEST;
        		gbc_ProdCycStDt.gridx = 1;
        		gbc_ProdCycStDt.gridy = 5;
        		pformpanel.add(tfProdCycStDt, gbc_ProdCycStDt);
        		        		
        	    //Production Cycle Class
        	    JLabel lbProdCycClass = new JLabel("PRODUCT CLASS");
        	    lbProdCycClass.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_lbProdCycClass = new GridBagConstraints();
        		gbc_lbProdCycClass.anchor = GridBagConstraints.EAST;
        		gbc_lbProdCycClass.insets = new Insets(10, 10, 10, 5);
        		gbc_lbProdCycClass.gridx = 0;
        		gbc_lbProdCycClass.gridy = 7;
        		pformpanel.add(lbProdCycClass, gbc_lbProdCycClass);
        		
        		JLabel tfProdCycClass = new JLabel(prodcyc.getprodcycClass());
        		tfProdCycStDt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_ProdCycClass = new GridBagConstraints();
        		gbc_ProdCycClass.gridwidth = 2;
        		gbc_ProdCycClass.insets = new Insets(10, 10, 10, 5);
        		gbc_ProdCycClass.anchor = GridBagConstraints.NORTHWEST;
        		gbc_ProdCycClass.gridx = 1;
        		gbc_ProdCycClass.gridy = 7;
        		pformpanel.add(tfProdCycClass, gbc_ProdCycClass);
        		
        	    //Production Cycle Price Cap
        	    JLabel lbProdCycPriceCap = new JLabel("PER UNIT PRICE CAP(" + RUPEE +")");
        	    lbProdCycPriceCap.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_lbProdCycPriceCap = new GridBagConstraints();
        		gbc_lbProdCycPriceCap.anchor = GridBagConstraints.EAST;
        		gbc_lbProdCycPriceCap.insets = new Insets(10, 10, 10, 5);
        		gbc_lbProdCycPriceCap.gridx = 0;
        		gbc_lbProdCycPriceCap.gridy = 9;
        		pformpanel.add(lbProdCycPriceCap, gbc_lbProdCycPriceCap);
        		
        		JLabel tfProdCycPriceCap = new JLabel(Integer.toString(prodcyc.getprodcycCapPrice()));
        		tfProdCycPriceCap.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_ProdCycPriceCap = new GridBagConstraints();
        		gbc_ProdCycPriceCap.gridwidth = 2;
        		gbc_ProdCycPriceCap.insets = new Insets(10, 10, 10, 5);
        		gbc_ProdCycPriceCap.anchor = GridBagConstraints.NORTHWEST;
        		gbc_ProdCycPriceCap.gridx = 1;
        		gbc_ProdCycPriceCap.gridy = 9;
        		pformpanel.add(tfProdCycPriceCap, gbc_ProdCycPriceCap);	
        		
        	    //Production Cycle Batch Size
        	    JLabel lbProdCycBatchSize = new JLabel("PRODUCTION BATCH SIZE");
        	    lbProdCycBatchSize.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_lbProdCycBatchSize = new GridBagConstraints();
        		gbc_lbProdCycBatchSize.anchor = GridBagConstraints.EAST;
        		gbc_lbProdCycBatchSize.insets = new Insets(10, 10, 10, 5);
        		gbc_lbProdCycBatchSize.gridx = 0;
        		gbc_lbProdCycBatchSize.gridy = 11;
        		pformpanel.add(lbProdCycBatchSize, gbc_lbProdCycBatchSize);
        		
        		JLabel tfProdCycBatchSize = new JLabel(Integer.toString(prodcyc.getprodcycBatchSize()));
        		tfProdCycBatchSize.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_ProdCycBatchSize = new GridBagConstraints();
        		gbc_ProdCycBatchSize.gridwidth = 2;
        		gbc_ProdCycBatchSize.insets = new Insets(10, 10, 10, 5);
        		gbc_ProdCycBatchSize.anchor = GridBagConstraints.NORTHWEST;
        		gbc_ProdCycBatchSize.gridx = 1;
        		gbc_ProdCycBatchSize.gridy = 11;
        		pformpanel.add(tfProdCycBatchSize, gbc_ProdCycBatchSize);	
        		
           	    //Production Cycle Vendor Selection Criterion
        	    JLabel lbProdCycSelCrit = new JLabel("VENDOR SELECTION CRITERION");
        	    lbProdCycSelCrit.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_lbProdCycSelCrit = new GridBagConstraints();
        		gbc_lbProdCycSelCrit.anchor = GridBagConstraints.EAST;
        		gbc_lbProdCycSelCrit.insets = new Insets(10, 10, 10, 5);
        		gbc_lbProdCycSelCrit.gridx = 0;
        		gbc_lbProdCycSelCrit.gridy = 13;
        		pformpanel.add(lbProdCycSelCrit, gbc_lbProdCycSelCrit);
        		
                ActionListener rbPAction = new ActionListener() 
                {
                    public void actionPerformed(ActionEvent e) 
                    {
                        JRadioButton button = (JRadioButton) e.getSource();
                    	selCritPrice=true;	selCritQuality=false; selCritLeadTime=false; selCritOptimal=false;
                        System.out.println("Selected Price Optimization");
                    }
                };
                ActionListener rbLAction = new ActionListener() 
                {
                    public void actionPerformed(ActionEvent e) 
                    {
                        JRadioButton button = (JRadioButton) e.getSource();
                        selCritPrice=false;	selCritLeadTime=true; selCritQuality=false;  selCritOptimal=false;
                        System.out.println("Selected LeadTime Optimization");
                    }
                };
                ActionListener rbQAction = new ActionListener() 
                {
                    public void actionPerformed(ActionEvent e) 
                    {
                        JRadioButton button = (JRadioButton) e.getSource();
                        selCritPrice=false;	selCritLeadTime=false; selCritQuality=true; selCritOptimal=false;
                        System.out.println("Selected Quality Optimization");
                    }
                };
                ActionListener rbOAction = new ActionListener() 
                {
                    public void actionPerformed(ActionEvent e) 
                    {
                        JRadioButton button = (JRadioButton) e.getSource();
                        selCritPrice=false;	selCritLeadTime=false; selCritQuality=false; selCritOptimal=true;
                        System.out.println("Selected Combined Optimization");
                    }
                };
        		
        		JRadioButton rbPrice=new JRadioButton("Best Price"); 
        		rbPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
        		rbPrice.addActionListener(rbPAction);
           		JRadioButton rbLeadTime=new JRadioButton("Supply LeadTime"); 
           		rbLeadTime.setFont(new Font("Tahoma", Font.PLAIN, 12));
           		rbLeadTime.addActionListener(rbLAction);
           		JRadioButton rbQuality=new JRadioButton("Best Quality"); 
           		rbQuality.setFont(new Font("Tahoma", Font.PLAIN, 12));
           		rbQuality.addActionListener(rbQAction);
          		JRadioButton rbOptimal=new JRadioButton("Optimal"); 
           		rbOptimal.setFont(new Font("Tahoma", Font.PLAIN, 12));
           		rbOptimal.addActionListener(rbOAction);           		
  		
        		GridBagConstraints gbc_rbPrice = new GridBagConstraints();
        		gbc_rbPrice.gridwidth = 1;
        		gbc_rbPrice.insets = new Insets(10, 2, 2, 2);
        		gbc_rbPrice.anchor = GridBagConstraints.NORTHWEST;
        		gbc_rbPrice.gridx = 1;
        		gbc_rbPrice.gridy = 13;
        		pformpanel.add(rbPrice, gbc_rbPrice);	
        		

        		GridBagConstraints gbc_rbLeadTime = new GridBagConstraints();
        		gbc_rbLeadTime.gridwidth = 1;
        		gbc_rbLeadTime.insets = new Insets(2, 2, 2, 2);
        		gbc_rbLeadTime.anchor = GridBagConstraints.NORTHWEST;
        		gbc_rbLeadTime.gridx = 1;
        		gbc_rbLeadTime.gridy = 14;
        		pformpanel.add(rbLeadTime, gbc_rbLeadTime);	
        		

        		GridBagConstraints gbc_rbQuality = new GridBagConstraints();
        		gbc_rbQuality.gridwidth = 1;
        		gbc_rbQuality.insets = new Insets(2, 2, 2, 2);
        		gbc_rbQuality.anchor = GridBagConstraints.NORTHWEST;
        		gbc_rbQuality.gridx = 1;
        		gbc_rbQuality.gridy = 15;
        		pformpanel.add(rbQuality, gbc_rbQuality);	
        		
 
        		GridBagConstraints gbc_rbOptimal = new GridBagConstraints();
        		gbc_rbOptimal.gridwidth = 1;
        		gbc_rbOptimal.insets = new Insets(2,2, 2, 2);
        		gbc_rbOptimal.anchor = GridBagConstraints.NORTHWEST;
        		gbc_rbOptimal.gridx = 1;
        		gbc_rbOptimal.gridy = 16;
        		pformpanel.add(rbOptimal, gbc_rbOptimal);
        		
 
        		

        		
        		ButtonGroup btngrpOptCrit = new ButtonGroup();
        		btngrpOptCrit.add(rbPrice);
        		btngrpOptCrit.add(rbLeadTime);
        		btngrpOptCrit.add(rbQuality);
        		btngrpOptCrit.add(rbOptimal);   
       		
        		pformpanel.removeAll();
                pformpanel.invalidate();
                
        		pformpanel.add(lbProdCycId,        gbc_lbProdCycId);
        		pformpanel.add(tfProdCycId,        gbc_ProdCycId);
        		pformpanel.add(lbProdCycName,      gbc_lbProdCycName);  		
        		pformpanel.add(lbProdCycStDt,      gbc_lbProdCycStDt); 
        		pformpanel.add(tfProdCycStDt,      gbc_ProdCycStDt);      
        		pformpanel.add(tfProdCycName1,     gbc_ProdCycName1);
        		pformpanel.add(lbProdCycClass,     gbc_lbProdCycClass);
        		pformpanel.add(tfProdCycClass,     gbc_ProdCycClass);
        		pformpanel.add(lbProdCycPriceCap,  gbc_lbProdCycPriceCap);
        		pformpanel.add(tfProdCycPriceCap,  gbc_ProdCycPriceCap);	
           		pformpanel.add(lbProdCycBatchSize, gbc_lbProdCycBatchSize);
           		pformpanel.add(tfProdCycBatchSize, gbc_ProdCycBatchSize);	
        		pformpanel.add(lbProdCycSelCrit, gbc_lbProdCycSelCrit);
        		pformpanel.add(rbPrice, gbc_rbPrice);
        		pformpanel.add(rbLeadTime, gbc_rbLeadTime);	
        		pformpanel.add(rbQuality, gbc_rbQuality);	
           		pformpanel.add(rbOptimal, gbc_rbOptimal);	

        		pformpanel.revalidate();
        		pformpanel.repaint();
        		
            }
        });
		
        btnGen.addActionListener(new ActionListener() 
        {     	 
            public void actionPerformed(ActionEvent e) 
            {


            	SessionFactory sessFact = HibernateUtil.getSessionFactory();
        		session = sessFact.getCurrentSession();
        		org.hibernate.Transaction tr = session.beginTransaction();
        		
        		//Fetch the Parts for the Product from the BOM Table 
        		CriteriaBuilder builder = session.getCriteriaBuilder();
                javax.persistence.criteria.CriteriaQuery<BoMModel> query = builder.createQuery(BoMModel.class);
        	    Root<BoMModel> root = query.from(BoMModel.class); 
        	    System.out.println(ProdCycProdName);
                query.select(root).where(builder.equal(root.get("bomProdName"), ProdCycProdName));
                Query<BoMModel> q=session.createQuery(query);
                //q.setMaxResults(5);
                List<BoMModel> bomList=q.getResultList();
                bomList.forEach(System.out::println);
              
            	List<Order> ordListFullPrice = new ArrayList<Order>();
            	List<SupplyModel> supListFullLeadTime = new ArrayList<SupplyModel>();
               	List<SupplyModel> supListFullQlty = new ArrayList<SupplyModel>();
            	List<Order> orderListFull = new ArrayList<Order>();
            	
               if (selCritPrice==true)
                {
                	for (BoMModel bomlineitem : bomList) 
                	{   
                		//System.out.println(">>>>>>>>>>>>> BOM PART ID: "+bomlineitem.getbomPartId()+"----------------------------------------------------");               		
                		CriteriaBuilder cb = session.getCriteriaBuilder();
                		javax.persistence.criteria.CriteriaQuery<Order> cq = cb.createQuery(Order.class);
                		Root<SupplyModel> supply = cq.from(SupplyModel.class); 
                		Root<VendorModel> vend = cq.from(VendorModel.class); 
                		//Join<SupplyModel,VendorModel> suppJvend = supply.join("vendId",JoinType.INNER);
                		
                		Predicate eqToProdId = cb.equal(supply.get("partId"), bomlineitem.getbomPartId());
                		Predicate eqSupVendId = cb.equal(supply.get("vendId"), vend.get("vendId"));
                		cq.multiselect(
                						supply.get("partId"),
                						supply.get("partName"),
                						supply.get("vendId"),
                						supply.get("vendName"),
                						vend.get("vendAddr1"),
                						vend.get("vendCity"),
                						vend.get("vendCountry"),
                						vend.get("vendPin"),
                						vend.get("vendTin"),
                						supply.get("vendSupplyClass"),
                						supply.get("vendSupplyLeadTime"),
                						supply.get("vendSupplyPrice")                				       
                				      ).where(cb.and(eqToProdId, eqSupVendId)).orderBy(cb.asc(supply.get("vendSupplyPrice")));
                		TypedQuery<Order> tq = session.createQuery(cq);
                		tq.setMaxResults(1);
                		List<Order> ordList=tq.getResultList();
                		ordList.forEach(System.out::println);
                		ordListFullPrice.addAll(ordList);
                	} 
                	ordListFullPrice.forEach(System.out::println);
                }

                
                PdfPTable olTable = new PdfPTable(9);
                olTable.setHorizontalAlignment(Element.ALIGN_CENTER);
                olTable.setWidthPercentage(95);
                olTable.setSpacingBefore(10f); 
                try {
					olTable.setWidths(new float[]{1,3,3,4,1,1,1,1,1});
				} catch (DocumentException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
               
                PdfPCell cell;

                cell = new PdfPCell(new Phrase("Part ID", FontFactory.getFont(FontFactory.COURIER, 8, Font.PLAIN, new Color(255, 0, 0))));
                cell.setBackgroundColor(new Color(238,232,170));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                olTable.addCell(cell);
                cell = new PdfPCell(new Phrase("Part Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.PLAIN, new Color(255, 0, 0))));
                cell.setBackgroundColor(new Color(238,232,170));
                olTable.addCell(cell);
                cell = new PdfPCell(new Phrase("Vendor Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.PLAIN, new Color(255, 0, 0))));
                cell.setBackgroundColor(new Color(238,232,170));
                olTable.addCell(cell);
                cell = new PdfPCell(new Phrase("Vendor Addr1", FontFactory.getFont(FontFactory.COURIER, 8, Font.PLAIN, new Color(255, 0, 0))));
                cell.setBackgroundColor(new Color(238,232,170));
                olTable.addCell(cell);
                cell = new PdfPCell(new Phrase("City", FontFactory.getFont(FontFactory.COURIER, 8, Font.PLAIN, new Color(255, 0, 0))));
                cell.setBackgroundColor(new Color(238,232,170));
                olTable.addCell(cell);
                cell = new PdfPCell(new Phrase("Country", FontFactory.getFont(FontFactory.COURIER, 8, Font.PLAIN, new Color(255, 0, 0))));
                cell.setBackgroundColor(new Color(238,232,170));
                olTable.addCell(cell);
                cell = new PdfPCell(new Phrase("Supply Price", FontFactory.getFont(FontFactory.COURIER, 8, Font.PLAIN, new Color(255, 0, 0))));
                cell.setBackgroundColor(new Color(238,232,170));
                olTable.addCell(cell);
                cell = new PdfPCell(new Phrase("Lead Time", FontFactory.getFont(FontFactory.COURIER, 8, Font.PLAIN, new Color(255, 0, 0))));
                cell.setBackgroundColor(new Color(238,232,170));
                olTable.addCell(cell);
                cell = new PdfPCell(new Phrase("Quality", FontFactory.getFont(FontFactory.COURIER, 8, Font.PLAIN, new Color(255, 0, 0))));
                cell.setBackgroundColor(new Color(238,232,170));
                olTable.addCell(cell);
                olTable.setHeaderRows(0);
                
                for (Order ordLineItem : ordListFullPrice) 
                {
                	cell = new PdfPCell(new Phrase(ordLineItem.getpartId(), FontFactory.getFont(FontFactory.COURIER, 6, Font.PLAIN, new Color(0, 0, 0))));
                	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    olTable.addCell(cell);
                	cell = new PdfPCell(new Phrase(ordLineItem.getpartName(), FontFactory.getFont(FontFactory.COURIER, 6, Font.PLAIN, new Color(0, 0, 0))));
                	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    olTable.addCell(cell);
                	cell = new PdfPCell(new Phrase(ordLineItem.getvendName(), FontFactory.getFont(FontFactory.COURIER, 6, Font.PLAIN, new Color(0, 0, 0))));
                	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    olTable.addCell(cell);
                	cell = new PdfPCell(new Phrase(ordLineItem.getvendAddr1(), FontFactory.getFont(FontFactory.COURIER, 6, Font.PLAIN, new Color(0, 0, 0))));
                	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    olTable.addCell(cell);
                	cell = new PdfPCell(new Phrase(ordLineItem.getvendCity(), FontFactory.getFont(FontFactory.COURIER, 6, Font.PLAIN, new Color(0, 0, 0))));
                	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    olTable.addCell(cell);
                	cell = new PdfPCell(new Phrase(ordLineItem.getvendCountry(), FontFactory.getFont(FontFactory.COURIER, 6, Font.PLAIN, new Color(0, 0, 0))));
                	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    olTable.addCell(cell);
                	cell = new PdfPCell(new Phrase(Integer.toString(ordLineItem.getvendSupplyPrice()), FontFactory.getFont(FontFactory.COURIER, 6, Font.PLAIN, new Color(0, 0, 0))));
                	cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    olTable.addCell(cell);
                	cell = new PdfPCell(new Phrase(Integer.toString(ordLineItem.getvendSupplyLeadTime()), FontFactory.getFont(FontFactory.COURIER, 6, Font.PLAIN, new Color(0, 0, 0))));
                	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    olTable.addCell(cell);
                   	cell = new PdfPCell(new Phrase(ordLineItem.getvendSupplyClass(), FontFactory.getFont(FontFactory.COURIER, 6, Font.PLAIN, new Color(0, 0, 0))));
                   	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    olTable.addCell(cell);
                }
               
                FileOutputStream rfile = null;
                try {
					rfile = new FileOutputStream("D:\\OrderListPrice.pdf");
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
                
                Paragraph Title = new Paragraph(new Phrase(new Chunk("Order List for Production Batch# "+ ProdCycName,FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new Color(0,100,0)))));
                Title.setAlignment(Paragraph.ALIGN_CENTER);
                
                Paragraph Choice = new Paragraph(new Phrase(new Chunk("List Order Priority : Lowest Price",FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new Color(0,0,128)))));
                Choice.setAlignment(Paragraph.ALIGN_CENTER);
                Choice.setSpacingBefore(5f);
               
                Document doc = new Document();
                doc.setPageSize(PageSize.A4.rotate());
                doc.setMargins(0f,0f,10f,0f); 
                try 
                {
                	PdfWriter writer = PdfWriter.getInstance(doc, rfile);
				} catch (DocumentException e1) {e1.printStackTrace();}
                
                doc.open();
                
                try { doc.add(Title);} catch (DocumentException e1) {e1.printStackTrace();}  
                try { doc.add(Choice);} catch (DocumentException e1) {e1.printStackTrace();}    
                try { doc.add(olTable);} catch (DocumentException e1) {e1.printStackTrace();}   
                
                doc.close();
                try {rfile.close(); } catch (IOException e1) {e1.printStackTrace();}
                
            	//Iterate through the BOM part List to find the list of vendors for a given part for least lead time on top
/*                if (selCritPrice==true)
                {
                	for (BoMModel bomlineitem : bomList) 
                	{   
                		System.out.println(bomlineitem.getbomPartId());
                		CriteriaBuilder cb = session.getCriteriaBuilder();

                		javax.persistence.criteria.CriteriaQuery<SupplyModel> cq = cb.createQuery(SupplyModel.class);
                		Root<SupplyModel> supply = cq.from(SupplyModel.class); 
                		cq.select(supply);
                		cq.where(cb.equal(supply.get("partId"), bomlineitem.getbomPartId()));
                		cq.orderBy(cb.asc(supply.get("vendSupplyPrice")));
                		Query<SupplyModel> q1=session.createQuery(cq);
                		List<SupplyModel> supList=q1.getResultList();
                		//supList.forEach(System.out::println);
                		supListFullPrice.addAll(supList);
                	} 
                	supListFullPrice.forEach(System.out::println);
                }
              
                //Iterate through the BOM part List to find the list of vendors for a given part and arrange for least lead time on top 
                if (selCritLeadTime==true)
                {    	
                	
                	for (BoMModel bomlineitem : bomList) 
                	{   
                		System.out.println(bomlineitem.getbomPartId());
                		CriteriaBuilder cb = session.getCriteriaBuilder();
                		javax.persistence.criteria.CriteriaQuery<SupplyModel> cq = cb.createQuery(SupplyModel.class);
                		Root<SupplyModel> supply = cq.from(SupplyModel.class); 
                		cq.select(supply);
                		cq.where(cb.equal(supply.get("partId"), bomlineitem.getbomPartId()));
                		cq.orderBy(cb.asc(supply.get("vendSupplyLeadTime")));
                		Query<SupplyModel> q1=session.createQuery(cq);
                		List<SupplyModel> supList=q1.getResultList();
                		supListFullLeadTime.addAll(supList); 
                	}  
                	supListFullLeadTime.forEach(System.out::println);
                }
                
                if (selCritQuality==true)
                {    	
                	//Iterate through the BOM part List to find the list of vendors for a given part and arrange for least lead time on top 
                	for (BoMModel bomlineitem : bomList) 
                	{   
                		System.out.println(bomlineitem.getbomPartId());
                		CriteriaBuilder cb = session.getCriteriaBuilder();
                		javax.persistence.criteria.CriteriaQuery<SupplyModel> cq = cb.createQuery(SupplyModel.class);
                		Root<SupplyModel> supply = cq.from(SupplyModel.class); 
                		cq.select(supply);
                		cq.where(cb.equal(supply.get("partId"), bomlineitem.getbomPartId()));
                		cq.orderBy(cb.asc(supply.get("vendSupplyClass")));
                		Query<SupplyModel> q1=session.createQuery(cq);
                		List<SupplyModel> supList=q1.getResultList();
                		supListFullQlty.addAll(supList); 
                	}  
                	supListFullQlty.forEach(System.out::println);
                }*/ 
                tr.commit(); 
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
	    
	    getContentPane().add(title, BorderLayout.NORTH);
	    getContentPane().add(formpanel, BorderLayout.WEST);
	    getContentPane().add(pformpanel, BorderLayout.EAST);
	    getContentPane().add(bp, BorderLayout.SOUTH);
	    //getContentPane().add(p1, BorderLayout.CENTER);
	    pack();
	    setResizable(true);
	    setLocationRelativeTo(parent1);	
    }

}

