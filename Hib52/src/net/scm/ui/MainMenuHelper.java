package net.scm.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuHelper extends JDialog
{
	private static final long serialVersionUID = 1L;
	static JTextField tfMessage;
    static JMenuBar menu;
    static JMenu x,y,z,e,f,v;
    static JMenuItem d1,d2,d3, p1,p2,p3, m1,m2,m3, r1,r2,r3, a1,a2,a3, v1,v2,v3;
    
	public JFrame parent1;
	
	public MainMenuHelper(JFrame parent)
	{
		super(parent, "Vendor Management System", true);
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
    	menupanel.setPreferredSize(new Dimension(638, 350));
     	menupanel.setOpaque(true);
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

         x = new JMenu("CREATE");       
        d1 = new JMenuItem("VENDOR");
        d2 = new JMenuItem("PRODUCT");
        d3 = new JMenuItem("PART");
        x.add(d1); x.add(d2); x.add(d3);    
        
         v = new JMenu("VIEW");        
        v1 = new JMenuItem("VENDOR");
        v2 = new JMenuItem("PRODUCT");
        v3 = new JMenuItem("PART");
        v.add(v1); v.add(v2); v.add(v3);    
        
        y = new JMenu("PLAN");        
        p1 = new JMenuItem("PRODUCT RELEASE");
        p2 = new JMenuItem("PURCHASE");
        p3 = new JMenuItem("BILL OF MATERIALS(BOM)");
        y.add(p1); y.add(p2); //y.add(m6);     
        
        z = new JMenu("MANAGE");        
        m1 = new JMenuItem("VENDOR");
        m2 = new JMenuItem("PRODUCT");
        m3 = new JMenuItem("PART");
        z.add(m1); z.add(m2); z.add(m3);
        
        e = new JMenu("REPORT");        
        r1 = new JMenuItem("INVOICE ");
        r2 = new JMenuItem("SEND INVOICE");
        r3 = new JMenuItem("PRINT INVOICE");
        e.add(r1); e.add(r2); e.add(r3);
        
        f = new JMenu("ABOUT");        
        a1 = new JMenuItem("ABOUT SOFTWARE");
        a2 = new JMenuItem("ABOUT DEVELOPER");
        a3 = new JMenuItem("ABOUT COMPANY");
        f.add(a1); f.add(a2); f.add(a3);
        
        //Add the Menus to the menubar 
        menu.add(x); menu.add(v); menu.add(y);
        menu.add(z); menu.add(e); menu.add(f); 

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
        
        
        
        //Construct the frame from the panels created for Top, Center and South        
        getContentPane().add(title, BorderLayout.NORTH);
        getContentPane().add(menupanel, BorderLayout.CENTER);  
        getContentPane().add(bp, BorderLayout.PAGE_END);
        setResizable(false);
        pack();
        setLocationRelativeTo(parent1);       
    }
}

