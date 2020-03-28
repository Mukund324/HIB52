package net.scm.ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuHelper extends JDialog
{
	private static final long serialVersionUID = 1L;
	static JTextField tfMessage;
    static JMenuBar menu;
    static JMenu x,y,z,e,f,v;
    static JMenuItem d1,d2,d3, p1,p2,p3, m1,m2,m3, r1,r2,r3, a1,a2,a3, v1,v2,v3,v4,v5;
    
	public JFrame parent1;
	
	public MainMenuHelper(JFrame parent)
	{
		super(parent, "Vendor Management System", true);
		parent1=parent;
	}
	/*
	@Override
    public Insets getInsets()
    {
        return new Insets(2,2,2,2);
    }
    */

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
    	menupanel.setPreferredSize(new Dimension(638, 350));
     	menupanel.setOpaque(true);
     	
     	//Create Panel for Displaying Scorecards of #Products, #Vendors, #Parts, #Production Plans, #Orders 
     	EtchedBorder eBorder = new EtchedBorder(EtchedBorder.LOWERED, Color.GRAY, Color.gray);
    	JPanel scardpanel = new JPanel(new BorderLayout(10,10));
    	scardpanel.setBackground(Color.WHITE);
    	scardpanel.setPreferredSize(new Dimension(638, 300));
    	scardpanel.setOpaque(true);
    	menupanel.add(scardpanel, BorderLayout.PAGE_END);
    	
    	JPanel scardpanel1 = new JPanel(new BorderLayout(10,10));
    	scardpanel1.setBackground(Color.WHITE);
    	scardpanel1.setPreferredSize(new Dimension(638, 150));
    	scardpanel1.setOpaque(true);
    	scardpanel1.setBorder(BorderFactory.createEmptyBorder(5, 5,5, 5));
    	scardpanel.add(scardpanel1, BorderLayout.PAGE_START);
    	JPanel scpProduct = new JPanel(new BorderLayout());
    	scpProduct.setBackground(Color.WHITE);
    	scpProduct.setPreferredSize(new Dimension(200, 150));
    	scpProduct.setOpaque(true);
    	scpProduct.setBorder(eBorder);
    	scardpanel1.add(scpProduct, BorderLayout.LINE_START);
    	JPanel scpVendor = new JPanel(new BorderLayout());
    	scpVendor.setBackground(Color.WHITE);
    	scpVendor.setPreferredSize(new Dimension(200, 150));
    	scpVendor.setOpaque(true);
    	scpVendor.setBorder(eBorder);
    	scardpanel1.add(scpVendor, BorderLayout.CENTER);
    	JPanel scpPart = new JPanel(new BorderLayout());
    	scpPart.setBackground(Color.WHITE);
    	scpPart.setPreferredSize(new Dimension(200, 150));
    	scpPart.setOpaque(true);
    	scpPart.setBorder(eBorder);
    	scardpanel1.add(scpPart, BorderLayout.LINE_END);    	
    	
    	JLabel sclProduct = new JLabel("Products");
    	sclProduct.setHorizontalAlignment(SwingConstants.CENTER);
    	JLabel sclVendor = new JLabel("Vendor");
    	sclVendor.setHorizontalAlignment(SwingConstants.CENTER);
    	JLabel sclParts = new JLabel("Parts");
    	sclParts.setHorizontalAlignment(SwingConstants.CENTER);
    	scpProduct.add(sclProduct, BorderLayout.PAGE_END);
    	scpVendor.add(sclVendor, BorderLayout.PAGE_END);
    	scpPart.add(sclParts, BorderLayout.PAGE_END);
   	
    	JPanel scardpanel2 = new JPanel(new BorderLayout(10,10));
    	scardpanel2.setBackground(Color.WHITE);
    	scardpanel2.setPreferredSize(new Dimension(638, 150));
    	scardpanel2.setOpaque(true);
    	scardpanel2.setBorder(BorderFactory.createEmptyBorder(5, 5,5, 5));
    	scardpanel.add(scardpanel2, BorderLayout.PAGE_END);
    	
    	JPanel scpSupply = new JPanel(new BorderLayout());
    	scpSupply.setBackground(Color.WHITE);
    	scpSupply.setPreferredSize(new Dimension(200, 150));
    	scpSupply.setOpaque(true);
    	scpSupply.setBorder(eBorder);
    	scardpanel2.add(scpSupply, BorderLayout.LINE_START);
    	JPanel scpOrder = new JPanel(new BorderLayout());
    	scpOrder.setBackground(Color.WHITE);
    	scpOrder.setPreferredSize(new Dimension(200, 150));
    	scpOrder.setOpaque(true);
    	scpOrder.setBorder(eBorder);
    	scardpanel2.add(scpOrder, BorderLayout.CENTER);
    	JPanel scpProd = new JPanel(new BorderLayout());
    	scpProd.setBackground(Color.WHITE);
    	scpProd.setPreferredSize(new Dimension(200, 150));
    	scpProd.setOpaque(true);
    	scpProd.setBorder(eBorder);
    	scardpanel2.add(scpProd, BorderLayout.LINE_END);    
    	
    	JLabel sclSupplies = new JLabel("Supplies");
    	sclSupplies.setHorizontalAlignment(SwingConstants.CENTER);
    	JLabel sclOrders = new JLabel("Orders");
    	sclOrders.setHorizontalAlignment(SwingConstants.CENTER);
    	JLabel sclProduction = new JLabel("Production Plans");  
    	sclProduction.setHorizontalAlignment(SwingConstants.CENTER);
    	scpSupply.add(sclSupplies, BorderLayout.PAGE_END);
    	scpOrder.add(sclOrders, BorderLayout.PAGE_END);
    	scpProd.add(sclProduction, BorderLayout.PAGE_END);
    	
       	//Create Panel for Bottom (Adding Buttons for Operations)               
	    JPanel bp = new JPanel();
        bp.setBackground(Color.decode("#87CEFA"));
        bp.setPreferredSize(new Dimension(638,25));
        bp.setOpaque(true);
        
    	//Adding Components for the Title Panel    
	    JLabel ltitle = new JLabel("Vendor Management Portal");
	    ltitle.setHorizontalAlignment(JLabel.CENTER);
	    ltitle.setVerticalAlignment(JLabel.CENTER);
	    ltitle.setFont(new Font("Arial",Font.TRUETYPE_FONT,16));
	    ltitle.setForeground(Color.WHITE);
	    title.add(ltitle);    
	    
	    //Set global UI settings for Menu and Menu Bar
	    UIManager.put("MenuBar.background", Color.decode("#D2B48C"));
        UIManager.put("Menu.background", Color.gray);
        UIManager.put("MenuItem.background", Color.decode("#B0C4DE"));       

        //creating the Menu and corresponding Menu Items
        menu=new JMenuBar();    

        x = new JMenu("CREATE"); d1 = new JMenuItem("VENDOR"); d2 = new JMenuItem("PRODUCT");d3 = new JMenuItem("PART");
        x.add(d1); x.add(d2); x.add(d3);    
        v = new JMenu("VIEW");v1 = new JMenuItem("VENDOR"); v2 = new JMenuItem("PRODUCT");v3 = new JMenuItem("PART");v4 = new JMenuItem("BILL OF MATERIALS");
        	v5 = new JMenuItem("SUPPLY OF MATERIALS");
        v.add(v1); v.add(v2); v.add(v3); v.add(v4);v.add(v5);      
        y = new JMenu("PLAN"); p1 = new JMenuItem("BILL OF MATERIALS");p2 = new JMenuItem("SUPPLY OF MATERIALS");p3 = new JMenuItem("BILL OF MATERIALS(BOM)");
        y.add(p1); y.add(p2); //y.add(m6);     
        z = new JMenu("MANAGE"); m1 = new JMenuItem("VENDOR"); m2 = new JMenuItem("PRODUCT"); m3 = new JMenuItem("PART");
        z.add(m1); z.add(m2); z.add(m3);
        e = new JMenu("GENERATE");r1 = new JMenuItem("VENDOR LIST"); r2 = new JMenuItem("PURCHASE ORDERS");//r3 = new JMenuItem("PRINT INVOICE");
        e.add(r1); e.add(r2); //e.add(r3);
        f = new JMenu("ABOUT"); a1 = new JMenuItem("ABOUT SOFTWARE"); a2 = new JMenuItem("ABOUT DEVELOPER"); a3 = new JMenuItem("ABOUT COMPANY");
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
                        
       //Construct the frame from the panels created for Top, Center and South        
        getContentPane().add(title, BorderLayout.PAGE_START);
        getContentPane().add(menupanel, BorderLayout.LINE_START);  
        //getContentPane().add(scardpanel, BorderLayout.LINE_START);  
        getContentPane().add(bp, BorderLayout.PAGE_END);
        setResizable(false);
        pack();
        setLocationRelativeTo(parent1);       
    }
}

