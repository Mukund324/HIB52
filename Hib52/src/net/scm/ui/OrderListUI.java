package net.scm.ui;
import net.scm.model.*;
import net.scm.core.*;

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
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
	
	public JButton btnGen;
	public JButton btnReset;
	public JButton btnMenu;
	
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
	    
	    btnGen = new JButton("Generate Order");
		btnReset = new JButton("Reset");
	    btnMenu = new JButton("Back to Menu");
	    bp.add(btnGen);
	    bp.add(btnReset);
	    bp.add(btnMenu);
	    
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
		gbc_ProdCycName.gridx = 1;
		gbc_ProdCycName.gridy = 0;
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
                ProdCycProdName= prodcyc.getprodcycProd() ;
                
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
        		
       		
        		pformpanel.removeAll();
                pformpanel.invalidate();
                
        		pformpanel.add(lbProdCycId, gbc_lbProdCycId);
        		pformpanel.add(tfProdCycId, gbc_ProdCycId);
        		pformpanel.add(lbProdCycName, gbc_lbProdCycName);  		
        		pformpanel.add(lbProdCycStDt, gbc_lbProdCycStDt); 
        		pformpanel.add(tfProdCycStDt, gbc_ProdCycStDt);      
        		pformpanel.add(tfProdCycName1, gbc_ProdCycName1);
        		pformpanel.add(lbProdCycClass, gbc_lbProdCycClass);
        		pformpanel.add(tfProdCycClass, gbc_ProdCycClass);
        		pformpanel.add(lbProdCycPriceCap, gbc_lbProdCycPriceCap);
        		pformpanel.add(tfProdCycPriceCap, gbc_ProdCycPriceCap);	

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

        		CriteriaBuilder builder = session.getCriteriaBuilder();
                javax.persistence.criteria.CriteriaQuery<BoMModel> query = builder.createQuery(BoMModel.class);
        	    Root<BoMModel> root = query.from(BoMModel.class); 
        	    System.out.println(ProdCycProdName);
                query.select(root).where(builder.equal(root.get("bomProdName"), ProdCycProdName));
                Query<BoMModel> q=session.createQuery(query);
                List<BoMModel> bomList=q.getResultList();
                bomList.forEach(System.out::println);

                for (BoMModel bomlineitem : bomList) 
                {
             	    System.out.println(bomlineitem.getbomPartId());
                	CriteriaBuilder builder1 = session.getCriteriaBuilder();
                    javax.persistence.criteria.CriteriaQuery<SupplyModel> query1 = builder1.createQuery(SupplyModel.class);
            	    Root<SupplyModel> root1 = query1.from(SupplyModel.class); 
                    query1.select(root1).where(builder1.equal(root1.get("partId"), bomlineitem.getbomPartId()));
                    Query<SupplyModel> q1=session.createQuery(query1);
                    List<SupplyModel> supList=q1.getResultList();
                    supList.forEach(System.out::println);
                }              
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
	    pack();
	    setResizable(true);
	    setLocationRelativeTo(parent1);	
    }

}

