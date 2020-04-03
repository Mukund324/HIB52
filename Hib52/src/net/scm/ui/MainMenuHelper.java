 package net.scm.ui;

import net.scm.model.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import net.scm.model.HibernateUtil;
import net.scm.model.PartModel;
import net.scm.model.ProductModel;
import net.scm.model.SupplyModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

public class MainMenuHelper extends JDialog
{
	private static final long serialVersionUID = 1L;
	static JTextField tfMessage;
    static JMenuBar menu;
    static JMenu x,y,z,e,f,v;
    static JMenuItem d1,d2,d3, p1,p2,p3, m1,m2,m3, r1,r2,r3, a1,a2,a3, v1,v2,v3,v4,v5,v6;
    
	public JFrame parent1;
	public Session session;
	public Integer countProducts;
	public Integer countVendors;
	public Integer countParts;
	public Integer countSupplies;
	public Integer countProdCycles;
	public Integer countOrders;
	public Integer countBoms;
	
	public MainMenuHelper(JFrame parent)
	{
		super(parent, "Vendor Management System ", true);
		parent1=parent;
	}

    //Method to Display UI for the Main Menu
    public void MainMenuUI()
    {
    	//Create Panel for Title 
    	JPanel title = new JPanel();
    	title.setBackground(Color.decode("#006666"));
	    title.setPreferredSize(new Dimension(635,30));
        title.setOpaque(true);
       	//Create Panel for Menu       
    	JPanel menupanel = new JPanel(new BorderLayout());
    	menupanel.setBackground(Color.WHITE);
    	menupanel.setPreferredSize(new Dimension(718, 350));
     	menupanel.setOpaque(true);
     	
     	//Create Panel for Displaying Scorecards of #Products, #Vendors, #Parts, #Production Plans, #Orders 
     	EtchedBorder eBorder = new EtchedBorder(EtchedBorder.LOWERED, Color.WHITE, Color.gray);
    	JPanel scardpanel = new JPanel(new BorderLayout(5,5));
    	scardpanel.setBackground(Color.WHITE);
    	scardpanel.setPreferredSize(new Dimension(718, 320));
    	scardpanel.setOpaque(true);
    	menupanel.add(scardpanel, BorderLayout.PAGE_END);
    	
    	JPanel scardpanel1 = new JPanel(new BorderLayout(10,10));
    	scardpanel1.setBackground(Color.WHITE);
    	scardpanel1.setPreferredSize(new Dimension(718, 150));
    	scardpanel1.setOpaque(true);
    	scardpanel1.setBorder(BorderFactory.createEmptyBorder(10, 5,0, 5));
    	scardpanel.add(scardpanel1, BorderLayout.PAGE_START);
    	JPanel scpProduct = new JPanel(new BorderLayout());
    	scpProduct.setBackground(Color.decode("#E0FFFF"));
    	scpProduct.setPreferredSize(new Dimension(240, 150));
    	scpProduct.setOpaque(true);
    	scardpanel1.add(scpProduct, BorderLayout.LINE_START);
    	JPanel scpVendor = new JPanel(new BorderLayout());
    	scpVendor.setBackground(Color.decode("#E0FFFF"));
    	scpVendor.setPreferredSize(new Dimension(240, 150));
    	scpVendor.setOpaque(true);
    	//scpVendor.setBorder(eBorder);
    	scardpanel1.add(scpVendor, BorderLayout.CENTER);
    	JPanel scpPart = new JPanel(new BorderLayout());
    	scpPart.setBackground(Color.decode("#E0FFFF"));
    	scpPart.setPreferredSize(new Dimension(240, 150));
    	scpPart.setOpaque(true);
    	//scpPart.setBorder(eBorder);
    	scardpanel1.add(scpPart, BorderLayout.LINE_END);  

 
    	//Dummy dashboard values to avoid fetch during testing.....
	   	/*
    	countProducts=2;
    	countVendors=3;
    	countParts=4;
    	countSupplies=5;
    	countProdCycles=6;
    	countOrders=7;
    	countBoms=8;
    	 */


    	 { 
        	List<ProductModel> Products;
        	List<VendorModel> Vendors;
    		List<PartModel> Parts;
    		List<BoMModel> Boms; 		
        	List<SupplyModel> Supplies;
        	List<ProdCycleModel> ProdCycles;

    		SessionFactory sessFact = HibernateUtil.getSessionFactory();
    		session = sessFact.getCurrentSession();
    		org.hibernate.Transaction tr = session.beginTransaction();
			Criteria criteria1 = session.createCriteria(ProductModel.class);
			Products = criteria1.list();
			Criteria criteria2 = session.createCriteria(VendorModel.class);
			Vendors = criteria2.list();
			Criteria criteria3 = session.createCriteria(PartModel.class);
			Parts = criteria3.list();
			Criteria criteria4 = session.createCriteria(BoMModel.class);
			Boms = criteria4.list();
			Criteria criteria5 = session.createCriteria(SupplyModel.class);
			Supplies = criteria5.list();
			Criteria criteria6 = session.createCriteria(ProdCycleModel.class);
			ProdCycles = criteria6.list();			
    		tr.commit();
    		//sessFact.close();
    		System.out.println("Data Fetched from tables for Dashboard");

            ProductTableModel model1 = new ProductTableModel(Products);
            countProducts = model1.getRowCount();
            VendorTableModel model2 = new VendorTableModel(Vendors);
            countVendors= model2.getRowCount();
            PartTableModel model3 = new PartTableModel(Parts);
            countParts = model3.getRowCount();
            BoMTableModel model4 = new BoMTableModel(Boms);
            countBoms = model4.getRowCount();
            SupplyTableModel model5 = new SupplyTableModel(Supplies);
            countSupplies = model5.getRowCount();
            ProdCycleTableModel model6 = new ProdCycleTableModel(ProdCycles);
            countProdCycles = model6.getRowCount();
    	}     	

    	JLabel sclProduct = new JLabel("Products");
    	sclProduct.setHorizontalAlignment(SwingConstants.CENTER);
    	sclProduct.setFont(new Font("Tahoma", Font.PLAIN, 18));
    	sclProduct.setBackground(Color.decode("#006666"));
    	sclProduct.setForeground(Color.WHITE);
    	sclProduct.setOpaque(true);
    	JLabel scProduct = new JLabel(Integer.toString(countProducts));
    	scProduct.setFont(new Font("Arial", Font.PLAIN, 75));
    	scProduct.setHorizontalAlignment(SwingConstants.CENTER);
    	scProduct.setForeground(Color.decode("#CD5C5C"));
    	scpProduct.add(sclProduct, BorderLayout.PAGE_END);
    	scpProduct.add(scProduct, BorderLayout.CENTER);
    	
    	JLabel sclVendor = new JLabel("Vendors");
    	sclVendor.setHorizontalAlignment(SwingConstants.CENTER);
    	sclVendor.setFont(new Font("Tahoma", Font.PLAIN, 18));
    	sclVendor.setBackground(Color.decode("#006666"));
    	sclVendor.setForeground(Color.WHITE);
    	sclVendor.setOpaque(true);
    	sclVendor.setHorizontalAlignment(SwingConstants.CENTER);
    	JLabel scVendor = new JLabel(Integer.toString(countVendors));
    	scVendor.setFont(new Font("Arial", Font.PLAIN, 75));
    	scVendor.setHorizontalAlignment(SwingConstants.CENTER);
    	scVendor.setForeground(Color.decode("#800000"));
    	scpVendor.add(scVendor, BorderLayout.CENTER);
    	scpVendor.add(sclVendor, BorderLayout.PAGE_END);
    	
    	JLabel sclPart = new JLabel("Parts");
    	sclPart.setHorizontalAlignment(SwingConstants.CENTER);
    	sclPart.setFont(new Font("Tahoma", Font.PLAIN, 18));
    	sclPart.setBackground(Color.decode("#006666"));
    	sclPart.setForeground(Color.WHITE);
    	sclPart.setOpaque(true);
    	sclPart.setHorizontalAlignment(SwingConstants.CENTER);
    	JLabel scPart = new JLabel(Integer.toString(countParts));
    	scPart.setFont(new Font("Arial", Font.PLAIN, 75));
    	scPart.setHorizontalAlignment(SwingConstants.CENTER);
    	scPart.setForeground(Color.decode("#006400"));
       	scpPart.add(scPart, BorderLayout.CENTER);
    	scpPart.add(sclPart, BorderLayout.PAGE_END);
 
   	
    	JPanel scardpanel2 = new JPanel(new BorderLayout(10,10));
    	scardpanel2.setBackground(Color.WHITE);
    	scardpanel2.setPreferredSize(new Dimension(718, 150));
    	scardpanel2.setOpaque(true);
    	scardpanel2.setBorder(BorderFactory.createEmptyBorder(0, 5,10, 5));
    	scardpanel.add(scardpanel2, BorderLayout.PAGE_END);
    	
    	JPanel scpSupply = new JPanel(new BorderLayout());
    	scpSupply.setBackground(Color.decode("#E0FFFF"));
    	scpSupply.setPreferredSize(new Dimension(240, 150));
    	scpSupply.setOpaque(true);
    	scardpanel2.add(scpSupply, BorderLayout.LINE_START);
    	JPanel scpOrder = new JPanel(new BorderLayout());
    	scpOrder.setBackground(Color.decode("#E0FFFF"));
    	scpOrder.setPreferredSize(new Dimension(240, 150));
    	scpOrder.setOpaque(true);
    	scardpanel2.add(scpOrder, BorderLayout.CENTER);
    	JPanel scpProd = new JPanel(new BorderLayout());
    	scpProd.setBackground(Color.decode("#E0FFFF"));
    	scpProd.setPreferredSize(new Dimension(240, 150));
    	scpProd.setOpaque(true);
    	scardpanel2.add(scpProd, BorderLayout.LINE_END);    
    	
    	JLabel sclSupplies = new JLabel("Supplies");
    	sclSupplies.setHorizontalAlignment(SwingConstants.CENTER);
    	sclSupplies.setFont(new Font("Tahoma", Font.PLAIN, 18));
    	sclSupplies.setBackground(Color.decode("#006666"));
    	sclSupplies.setForeground(Color.WHITE);
    	sclSupplies.setOpaque(true);
    	sclSupplies.setHorizontalAlignment(SwingConstants.CENTER);
    	JLabel scSupply = new JLabel(Integer.toString(countSupplies));
    	scSupply.setBackground(Color.decode("#AFEEEE"));
    	scSupply.setFont(new Font("Arial", Font.PLAIN, 75));
    	scSupply.setHorizontalAlignment(SwingConstants.CENTER);
    	scSupply.setForeground(Color.decode("#483D8B"));
       	scpSupply.add(scSupply, BorderLayout.CENTER);
    	scpSupply.add(sclSupplies, BorderLayout.PAGE_END);
    	
    	
    	JLabel sclOrder = new JLabel("Bills of Material");
    	sclOrder.setHorizontalAlignment(SwingConstants.CENTER);
    	sclOrder.setFont(new Font("Tahoma", Font.PLAIN, 18));
    	sclOrder.setBackground(Color.decode("#006666"));
    	sclOrder.setForeground(Color.WHITE);
    	sclOrder.setOpaque(true);
    	sclOrder.setHorizontalAlignment(SwingConstants.CENTER);
    	JLabel scOrder = new JLabel(Integer.toString(countBoms));
    	scOrder.setBackground(Color.decode("#AFEEEE"));
    	scOrder.setFont(new Font("Arial", Font.PLAIN, 75));
    	scOrder.setHorizontalAlignment(SwingConstants.CENTER);
    	scOrder.setForeground(Color.decode("#FF8C00"));
    	scpOrder.add(scOrder, BorderLayout.CENTER);
    	scpOrder.add(sclOrder, BorderLayout.PAGE_END);
    	
    	JLabel sclProd = new JLabel("Production Batches");  
    	sclProd.setHorizontalAlignment(SwingConstants.CENTER);
    	sclProd.setHorizontalAlignment(SwingConstants.CENTER);
    	sclProd.setFont(new Font("Tahoma", Font.PLAIN, 18));
    	sclProd.setBackground(Color.decode("#006666"));
    	sclProd.setForeground(Color.WHITE);
    	sclProd.setOpaque(true);
    	sclProd.setHorizontalAlignment(SwingConstants.CENTER);
    	JLabel scProd = new JLabel(Integer.toString(countProdCycles));
    	scProd.setFont(new Font("Arial", Font.PLAIN, 75));
    	scProd.setHorizontalAlignment(SwingConstants.CENTER);
    	scProd.setForeground(Color.decode("#B8860B"));
    	scpProd.add(scProd, BorderLayout.CENTER);
    	scpProd.add(sclProd, BorderLayout.PAGE_END);
    	
    	
       	//Create Panel for Bottom (Adding Buttons for Operations)               
	    JPanel bp = new JPanel();
        bp.setBackground(Color.decode("#87CEFA"));
        bp.setPreferredSize(new Dimension(718,25));
        bp.setOpaque(true);
        
    	//Adding Components for the Title Panel    
	    JLabel ltitle = new JLabel("Vendor Management Portal >> Menu & DashBoard");
	    ltitle.setHorizontalAlignment(JLabel.CENTER);
	    ltitle.setVerticalAlignment(JLabel.CENTER);
	    ltitle.setFont(new Font("Arial",Font.TRUETYPE_FONT,16));
	    ltitle.setForeground(Color.WHITE);
	    title.add(ltitle);    
	    
	    //Set global UI settings for Menu and Menu Bar
	    UIManager.put("MenuBar.background", Color.decode("#D2B48C"));
        UIManager.put("Menu.background", Color.gray);
        UIManager.put("MenuItem.background", Color.decode("#FFDAB9"));       

        //creating the Menu and corresponding Menu Items
        menu=new JMenuBar();    

        x = new JMenu("CREATE"); d1 = new JMenuItem("VENDOR"); d2 = new JMenuItem("PRODUCT");d3 = new JMenuItem("PART");
        x.add(d1); x.add(d2); x.add(d3);    
        v = new JMenu("VIEW");v1 = new JMenuItem("VENDOR"); v2 = new JMenuItem("PRODUCT");v3 = new JMenuItem("PART");v4 = new JMenuItem("BILL OF MATERIALS");
        	v5 = new JMenuItem("SUPPLY OF MATERIALS");v6 = new JMenuItem("PRODUCTION BATCHES");
        v.add(v1); v.add(v2); v.add(v3); v.add(v4);v.add(v5);v.add(v6);      
        y = new JMenu("PLAN"); p1 = new JMenuItem("BILL OF MATERIALS");p2 = new JMenuItem("SUPPLY OF MATERIALS");p3 = new JMenuItem("PRODUCTION BATCH");
        y.add(p1); y.add(p2); y.add(p3);     
        z = new JMenu("MANAGE"); m1 = new JMenuItem("VENDOR"); m2 = new JMenuItem("PRODUCT"); m3 = new JMenuItem("PART");
        m1.setEnabled(false);m2.setEnabled(false);m3.setEnabled(false);
        z.add(m1); z.add(m2); z.add(m3);
        e = new JMenu("GENERATE");r1 = new JMenuItem("ORDER LIST"); r2 = new JMenuItem("PURCHASE ORDERS");//r3 = new JMenuItem("PRINT INVOICE");
        r2.setEnabled(false);
        e.add(r1); e.add(r2); //e.add(r3);
        f = new JMenu("ABOUT"); a1 = new JMenuItem("ABOUT SOFTWARE"); a2 = new JMenuItem("ABOUT DEVELOPER"); a3 = new JMenuItem("ABOUT COMPANY");
        //a1.setEnabled(false);
        a2.setEnabled(false);a3.setEnabled(false);
        f.add(a1); f.add(a2); f.add(a3);
        
        //Add the Menus to the menubar 
        menu.add(x); menu.add(v); menu.add(y); menu.add(z); menu.add(e); menu.add(f); 

        //Add the menu to menupanel
        menupanel.add(menu, BorderLayout.PAGE_START);   
        
        //Define Action Listeners for each of the CREATE components 
        d1.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
            	 System.out.println("You have chosen Create->Vendor !!!");
            	 dispose();
            	 VendorUI vframe=new VendorUI(parent1);
            	 vframe.VendorAddUI();
            	 vframe.setVisible(true);
            }
        }); //End of Create->Vendor
        
        d2.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
            {
        		 System.out.println("You have chosen Create->Product !!!");
             	 dispose();
            	 Product pframe=new Product(parent1);
            	 pframe.ProductAddUI();
             	 pframe.setVisible(true);
            }
        }); //End of Create->Product
        
        d3.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
       		     System.out.println("You have chosen Create->Part !!!");
             	 dispose();
            	 PartUI prtframe=new PartUI(parent1);
            	 prtframe.PartAddUI();
             	 prtframe.setVisible(true);
            }
        }); //End of Create->Part
        
        //Define Action Listeners for each of the VIEW components 
        v1.addActionListener(new ActionListener() 
        {
        	 
            public void actionPerformed(ActionEvent e) 
            {
      		     System.out.println("You have chosen View->Vendor !!!");
             	 dispose();
            	 VendorUI vframe=new VendorUI(parent1);
             	 vframe.VendorListAllUI();
            	 vframe.setVisible(true);
             	
            }
        }); //End of View->Vendor
        
        v2.addActionListener(new ActionListener() 
        {     	 
            public void actionPerformed(ActionEvent e) 
            {
            	 System.out.println("You have chosen View->Product !!!");
             	 dispose();
            	 Product pframe=new Product(parent1);
             	 pframe.ProductListAllUI();
            	 pframe.setVisible(true);
             	
            }
        }); //End of View->Product
        
        v3.addActionListener(new ActionListener() 
        {     	 
            public void actionPerformed(ActionEvent e) 
            {
            	 System.out.println("You have chosen View->Part !!!");
             	 dispose();
            	 PartUI partframe=new PartUI(parent1);
             	 partframe.PartListAllUI();
            	 partframe.setVisible(true);            	
            }
        }); //End of View->Part
        
        v4.addActionListener(new ActionListener() 
        {     	 
            public void actionPerformed(ActionEvent e) 
            {
            	 System.out.println("You have chosen View->BoM !!!");
             	 dispose();
            	 BoMUI vbomframe=new BoMUI(parent1);
             	 vbomframe.BoMListAllUI();
            	 vbomframe.setVisible(true);            	
            }
        }); //End of View->BoM
        
        v5.addActionListener(new ActionListener() 
        {     	 
            public void actionPerformed(ActionEvent e) 
            {
            	 System.out.println("You have chosen View->Supplies !!!");
             	 dispose();
            	 SupplyUI vsuppframe=new SupplyUI(parent1);
             	 vsuppframe.SupplyListAllUI();
             	 vsuppframe.setVisible(true);            	
            }
        }); //End of View->Supplies
        
        v6.addActionListener(new ActionListener() 
        {     	 
            public void actionPerformed(ActionEvent e) 
            {
            	 System.out.println("You have chosen View->Production Batches !!!");
             	 dispose();
            	 ProdCycleUI vpcframe=new ProdCycleUI(parent1);
            	 vpcframe.ProdCycleListAllUI();
            	 vpcframe.setVisible(true);            	
            }
        }); //End of View->Supplies
        
        //Define Action Listeners for each of the PLAN components 
        p1.addActionListener(new ActionListener() 
        {     	 
            public void actionPerformed(ActionEvent e) 
            {
            	 System.out.println("You have chosen Plan->BOM !!!");
             	 dispose();
            	 BoMUI bomframe=new BoMUI(parent1);
             	 bomframe.BoMAddUI();
            	 bomframe.setVisible(true);            	
            }
        }); //End of Plan->Bill of Materials
        
        p2.addActionListener(new ActionListener() 
        {     	 
            public void actionPerformed(ActionEvent e) 
            {
            	 System.out.println("You have chosen Plan->Supply !!!");
             	 dispose();
            	 SupplyUI supframe=new SupplyUI(parent1);
             	 supframe.SupplyAddUI();
            	 supframe.setVisible(true);            	
            }
        }); //End of Plan->Bill of Materials
        
        p3.addActionListener(new ActionListener() 
        {     	 
            public void actionPerformed(ActionEvent e) 
            {
            	 System.out.println("You have chosen Plan->Production !!!");
             	 dispose();
            	 ProdCycleUI pcframe=new ProdCycleUI(parent1);
             	 pcframe.ProdCycleAddUI();
            	 pcframe.setVisible(true);            	
            }
        }); //End of Plan->Production Cycle
        
        
        r1.addActionListener(new ActionListener() 
        {     	 
            public void actionPerformed(ActionEvent e) 
            {
            	 System.out.println("You have chosen Generate->OrderList !!!");
             	 dispose();
            	 OrderListUI olframe=new OrderListUI(parent1);
             	 olframe.OrderListGenUI();
            	 olframe.setVisible(true);            	
            }
        }); //End of Generate->Order List
        
        a1.addActionListener(new ActionListener() 
        {     	 
            public void actionPerformed(ActionEvent e) 
            {
            	 System.out.println("You have chosen About->Software !!!");
             	 dispose();
            	 AboutUI abframe=new AboutUI(parent1);
             	 abframe.AboutAddUI();
            	 abframe.setVisible(true);            	
            }
        }); //End of Generate->Order List
                        
       //Construct the frame from the panels created for Top, Center and South        
        getContentPane().add(title, BorderLayout.PAGE_START);
        getContentPane().add(menupanel, BorderLayout.LINE_START);  
        getContentPane().add(bp, BorderLayout.PAGE_END);
        setResizable(false);
        pack();
        setLocationRelativeTo(parent1);       
    }
}

