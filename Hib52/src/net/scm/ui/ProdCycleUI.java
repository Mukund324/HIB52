package net.scm.ui;

import net.scm.model.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.swing.*;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.hibernate.stat.spi.StatisticsImplementor;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Formatter;
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


public class ProdCycleUI extends JDialog		
{
	public JFrame parent1;
	public Session session;
	
	public static final String RUPEE = "\u20B9";
	public Date selStDt;
	public String selStDay;
	public String selStMonth;
	public String selStYear;
	public String insProdName;
	public String insProdId;
	public JButton btnSave;
	public JButton btnReset;
	public JButton btnMenu;
		
	public ProdCycleUI(JFrame parent)
	{
		super(parent, "Supply Chain Management", true);
		parent1=parent;
	}
	public void ProdCycleAddUI()
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
		
		btnSave = new JButton("Save");
		btnReset = new JButton("Reset");
	    btnMenu = new JButton("Back to Menu");
	    bp.add(btnSave);
	    bp.add(btnReset);
	    bp.add(btnMenu);
			
	    //Production Cycle ID
	    JLabel lbProdCycId = new JLabel("PRODUCTION BATCH ID");
	    lbProdCycId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbProdCycId = new GridBagConstraints();
		gbc_lbProdCycId.anchor = GridBagConstraints.EAST;
		gbc_lbProdCycId.insets = new Insets(10, 10, 10, 5);
		gbc_lbProdCycId.gridx = 0;
		gbc_lbProdCycId.gridy = 0;
		formpanel.add(lbProdCycId, gbc_lbProdCycId);
		
		JTextField tfProdCycId = new JTextField();
		tfProdCycId.setText(" ");
		tfProdCycId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfProdCycId.setColumns(10);
		GridBagConstraints gbc_ProdCycId = new GridBagConstraints();
		gbc_ProdCycId.gridwidth = 1;
		gbc_ProdCycId.insets = new Insets(10, 10, 10, 5);
		gbc_ProdCycId.anchor = GridBagConstraints.NORTHWEST;
		gbc_ProdCycId.gridx = 1;
		gbc_ProdCycId.gridy = 0;
		formpanel.add(tfProdCycId, gbc_ProdCycId);
		
	    //Production Cycle Name
	    JLabel lbProdCycName = new JLabel("PRODUCTION BATCH NAME");
	    lbProdCycName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbProdCycName = new GridBagConstraints();
		gbc_lbProdCycName.anchor = GridBagConstraints.EAST;
		gbc_lbProdCycName.insets = new Insets(10, 10, 10, 5);
		gbc_lbProdCycName.gridx = 0;
		gbc_lbProdCycName.gridy = 3;
		formpanel.add(lbProdCycName, gbc_lbProdCycName);
		
		JTextField tfProdCycName = new JTextField();
		tfProdCycName.setText(" ");
		tfProdCycName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfProdCycName.setColumns(10);
		GridBagConstraints gbc_ProdCycName = new GridBagConstraints();
		gbc_ProdCycName.gridwidth = 1;
		gbc_ProdCycName.insets = new Insets(10, 10, 10, 5);
		gbc_ProdCycName.anchor = GridBagConstraints.NORTHWEST;
		gbc_ProdCycName.gridx = 1;
		gbc_ProdCycName.gridy = 3;
		formpanel.add(tfProdCycName, gbc_ProdCycName);
		
	    //Production Cycle Product
	    JLabel lbProdCycProd = new JLabel("PRODUCT");
	    lbProdCycProd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbProdCycProd = new GridBagConstraints();
		gbc_lbProdCycProd.anchor = GridBagConstraints.EAST;
		gbc_lbProdCycProd.insets = new Insets(10, 10, 10, 5);
		gbc_lbProdCycProd.gridx = 0;
		gbc_lbProdCycProd.gridy = 5;
		formpanel.add(lbProdCycProd, gbc_lbProdCycProd);
		
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
		
	    JComboBox<String> tfProdCycProd = new JComboBox<String>();
		tfProdCycProd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfProdCycProd.setModel(new DefaultComboBoxModel<String>(prodStrArr));
		GridBagConstraints gbc_ProdCycProd = new GridBagConstraints();
		gbc_ProdCycName.gridwidth = 1;
		gbc_ProdCycName.insets = new Insets(10, 10, 10, 5);
		gbc_ProdCycName.anchor = GridBagConstraints.NORTHWEST;
		gbc_ProdCycName.gridx = 1;
		gbc_ProdCycName.gridy = 5;
		formpanel.add(tfProdCycProd, gbc_ProdCycName);
		
	    //Production Cycle Start Date

	    JLabel lbProdCycStDt = new JLabel("START DATE");
	    lbProdCycStDt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbProdCycStDt = new GridBagConstraints();
		gbc_lbProdCycStDt.anchor = GridBagConstraints.EAST;
		gbc_lbProdCycStDt.insets = new Insets(10, 10, 10, 5);
		gbc_lbProdCycStDt.gridx = 3;
		gbc_lbProdCycStDt.gridy = 0;
		formpanel.add(lbProdCycStDt, gbc_lbProdCycStDt);

		JComboBox tfProdCycStDtDay = new JComboBox();
		tfProdCycStDtDay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfProdCycStDtDay.setModel(new DefaultComboBoxModel(new String[] {
																		 "01","02", "03", "04","05",
																		 "06","07", "08", "09","10",
																		 "11","12", "13", "14","15",
																		 "16","17", "18", "19","20",
																		 "21","22", "23", "24","25",
																		 "26","27", "28", "29","30",
																		 "31"
															}));
		tfProdCycStDtDay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_ProdCycStDtDay = new GridBagConstraints();
		gbc_ProdCycStDtDay.gridwidth = 1;
		gbc_ProdCycStDtDay.insets = new Insets(10, 10, 10, 5);
		gbc_ProdCycStDtDay.anchor = GridBagConstraints.NORTHWEST;
		gbc_ProdCycStDtDay.gridx = 4;
		gbc_ProdCycStDtDay.gridy = 0;
		formpanel.add(tfProdCycStDtDay, gbc_ProdCycStDtDay);
	
		JComboBox tfProdCycStDtMonth = new JComboBox();
		tfProdCycStDtMonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfProdCycStDtMonth.setModel(new DefaultComboBoxModel(new String[] {
																		 "Jan","Feb", "Mar", "Apr","May",
																		 "Jun","Jul", "Aug", "Sep","Oct",
																		 "Nov","Dec"
																		}));
		tfProdCycStDtMonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_ProdCycStDtMonth = new GridBagConstraints();
		gbc_ProdCycStDtMonth.gridwidth = 1;
		gbc_ProdCycStDtMonth.insets = new Insets(10, 10, 10, 5);
		gbc_ProdCycStDtMonth.anchor = GridBagConstraints.NORTHWEST;
		gbc_ProdCycStDtMonth.gridx = 5;
		gbc_ProdCycStDtMonth.gridy = 0;
		formpanel.add(tfProdCycStDtMonth, gbc_ProdCycStDtMonth);
	
		JComboBox tfProdCycStDtYear = new JComboBox();
		tfProdCycStDtYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfProdCycStDtYear.setModel(new DefaultComboBoxModel(new String[] {
																		 "2020","2021", "2022", "2023","2024",
																		 "2025","2026", "2027", "2028","2029",
																		 "2030"
																		}));
		tfProdCycStDtYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		//tfProdCycStDtYear.setColumns(5);
		GridBagConstraints gbc_ProdCycStDtYear = new GridBagConstraints();
		gbc_ProdCycStDtYear.gridwidth = 1;
		gbc_ProdCycStDtYear.insets = new Insets(10, 10, 10, 5);
		gbc_ProdCycStDtYear.anchor = GridBagConstraints.NORTHWEST;
		gbc_ProdCycStDtYear.gridx = 6;
		gbc_ProdCycStDtYear.gridy = 0;
		formpanel.add(tfProdCycStDtYear, gbc_ProdCycStDtYear);

	    //Production Cycle Class
	    JLabel lbProdCycClass = new JLabel("PRODUCT CLASS");
	    lbProdCycClass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbProdCycClass = new GridBagConstraints();
		gbc_lbProdCycClass.anchor = GridBagConstraints.EAST;
		gbc_lbProdCycClass.insets = new Insets(10, 10, 10, 5);
		gbc_lbProdCycClass.gridx = 0;
		gbc_lbProdCycClass.gridy = 9;
		formpanel.add(lbProdCycClass, gbc_lbProdCycClass);
		
		JComboBox tfProdCycClass = new JComboBox();
		tfProdCycClass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfProdCycClass.setModel(new DefaultComboBoxModel(new String[] {"Others", "Gold", "Silver", "Bronze"}));
		tfProdCycClass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_ProdCycClass = new GridBagConstraints();
		gbc_ProdCycClass.gridwidth = 2;
		gbc_ProdCycClass.insets = new Insets(10, 10, 10, 5);
		gbc_ProdCycClass.anchor = GridBagConstraints.NORTHWEST;
		gbc_ProdCycClass.gridx = 1;
		gbc_ProdCycClass.gridy = 9;
		formpanel.add(tfProdCycClass, gbc_ProdCycClass);
		
	    //Production Cycle Price Cap
	    JLabel lbProdCycPriceCap = new JLabel("PER UNIT PRICE CAP(" + RUPEE +")");
	    lbProdCycPriceCap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lbProdCycPriceCap = new GridBagConstraints();
		gbc_lbProdCycPriceCap.anchor = GridBagConstraints.EAST;
		gbc_lbProdCycPriceCap.insets = new Insets(10, 10, 10, 5);
		gbc_lbProdCycPriceCap.gridx = 0;
		gbc_lbProdCycPriceCap.gridy = 11;
		formpanel.add(lbProdCycPriceCap, gbc_lbProdCycPriceCap);
		
		JTextField tfProdCycPriceCap = new JTextField();
		tfProdCycPriceCap.setText(" ");
		tfProdCycPriceCap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfProdCycPriceCap.setColumns(10);
		GridBagConstraints gbc_ProdCycPriceCap = new GridBagConstraints();
		gbc_ProdCycPriceCap.gridwidth = 2;
		gbc_ProdCycPriceCap.insets = new Insets(10, 10, 10, 5);
		gbc_ProdCycPriceCap.anchor = GridBagConstraints.NORTHWEST;
		gbc_ProdCycPriceCap.gridx = 1;
		gbc_ProdCycPriceCap.gridy = 11;
		formpanel.add(tfProdCycPriceCap, gbc_ProdCycPriceCap);		
		
		tfProdCycProd.addActionListener(new ActionListener() 
		{       	 
		    public void actionPerformed(ActionEvent e) 
		    {
		    	insProdName = tfProdCycProd.getSelectedItem().toString();
		    }
		});
		
		tfProdCycStDtDay.addActionListener(new ActionListener() 
		{       	 
		    public void actionPerformed(ActionEvent e) 
		    {
		    	selStDay = tfProdCycStDtDay.getSelectedItem().toString();
		    }
		});
		
		tfProdCycStDtMonth.addActionListener(new ActionListener() 
		{       	 
		    public void actionPerformed(ActionEvent e) 
		    {
		    	selStMonth = tfProdCycStDtMonth.getSelectedItem().toString();
		    }
		});
		
		tfProdCycStDtYear.addActionListener(new ActionListener() 
		{       	 
		    public void actionPerformed(ActionEvent e) 
		    {
		    	selStYear = tfProdCycStDtYear.getSelectedItem().toString();
		    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		    	try {
		    	String chDate = new String(selStDay +"-"+selStMonth+"-"+selStYear);
		    	selStDt = formatter.parse(chDate);
		    	System.out.println("Date Selected is :" + selStDt.toString() );
		    	}
		    	catch (ParseException e1) {
		            e1.printStackTrace();
		    	}
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
	    
	    btnSave.addActionListener(new ActionListener() 
        {       	 
            public void actionPerformed(ActionEvent e) 
            {
            	System.out.println("You have chosen Create->ProdCycle->Save !!!");
         		SessionFactory sessFact = HibernateUtil.getSessionFactory();
        		session = sessFact.getCurrentSession();
        		org.hibernate.Transaction tr = session.beginTransaction();
        		ProdCycleModel prodcycModel = new ProdCycleModel();
        		prodcycModel.setprodcycId(tfProdCycId.getText());
        		prodcycModel.setprodcycName(tfProdCycName.getText());
        		prodcycModel.setprodcycProd(insProdName);
        		prodcycModel.setprodcycProdId("PID101");	
        		prodcycModel.setprodcycClass(tfProdCycClass.getSelectedItem().toString());
        		prodcycModel.setprodcycStDt(selStDt);
        		prodcycModel.setprodcycCapPrice(Integer.parseInt(tfProdCycPriceCap.getText()));

        		
        		session.save(prodcycModel);
        		tr.commit();
        		System.out.println("Successfully inserted ProductionCycle Info");	        		
        		//sessFact.close();

            }
        });   
       getContentPane().add(title, BorderLayout.NORTH);
       getContentPane().add(formpanel, BorderLayout.CENTER);
       getContentPane().add(bp, BorderLayout.SOUTH);
       pack();
       setResizable(true);
       setLocationRelativeTo(parent1);
	}//End of Method for ProdCycleAddUI()
	
	 public void ProdCycleListAllUI()
	    {   	
	    	List<ProdCycleModel> ProdCycles;
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

				Criteria criteria = session.createCriteria(ProdCycleModel.class);
				ProdCycles = criteria.list();

	    		Iterator<ProdCycleModel> itr = ProdCycles.iterator();
	    		
	    		while (itr.hasNext()) {

	    			ProdCycleModel prodcycM = itr.next();
	    			System.out.println(prodcycM.getprodcycName());
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
	            	
	       	JLabel lblHeading = new JLabel("List of Production Batches", SwingConstants.CENTER);
	       	lblHeading.setFont(new Font("Arial",Font.TRUETYPE_FONT,24));
	       	lblHeading.setForeground (Color.green);
	       	lblHeading.setBackground (Color.white);
	       	lblHeading.setOpaque(true); 
	       	hp.add(lblHeading,BorderLayout.CENTER);
	       	
	    	//create the model
	        ProdCycleTableModel model = new ProdCycleTableModel(ProdCycles);
	        
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
}//End of Prod Cycle Class
