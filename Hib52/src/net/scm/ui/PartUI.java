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

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import net.scm.model.*;

public class PartUI extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JFrame parent1;
	public Session session;
	
	public JLabel lbPartID;
	public JTextField tfPartID;
	public JLabel lblPartName;
	public JTextField tfPartName;
	public JLabel lbPartProfile;
	public JTextField tfPartProfile;
	public JLabel lblPartPriceTyp;
	public JTextField tfPriceRangeTyp;
	public JLabel lblPartSpec;
	public JTextField tfPartSpec;
	public JLabel lbPartPin;
	public JTextField tfPartPin;	
	public JLabel lbPartSuppSchTyp;
	public JTextField tfPartSuppSchTyp;
	public JLabel lbPartQltyStd;
	public JTextField tfPartQltyStd;
	public JButton btnSave;
	public JButton btnReset;
	public JButton btnMenu;
	
	public PartUI(JFrame parent)
	{
		super(parent, "Supply Chain Management", true);
		parent1=parent;
	}
	public void PartAddUI()
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
	    
	    //PartID
	    JLabel lblPartID = new JLabel("ID");
	    lblPartID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblPartID = new GridBagConstraints();
		gbc_lblPartID.anchor = GridBagConstraints.EAST;
		gbc_lblPartID.insets = new Insets(10, 10, 10, 5);
		gbc_lblPartID.gridx = 0;
		gbc_lblPartID.gridy = 0;
		formpanel.add(lblPartID, gbc_lblPartID);

		JTextField tfPartID = new JTextField();
		tfPartID.setText(" ");
		tfPartID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfPartID.setColumns(30);
		GridBagConstraints gbc_partID = new GridBagConstraints();
		gbc_partID.gridwidth = 2;
		gbc_partID.insets = new Insets(10, 10, 10, 5);
		gbc_partID.anchor = GridBagConstraints.NORTHWEST;
		gbc_partID.gridx = 1;
		gbc_partID.gridy = 0;
		formpanel.add(tfPartID, gbc_partID);
		
		 //Part Name
	    JLabel lblPartName = new JLabel("NAME");
	    lblPartName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblPartName = new GridBagConstraints();
		gbc_lblPartName.anchor = GridBagConstraints.EAST;
		gbc_lblPartName.insets = new Insets(10, 10, 10, 5);
		gbc_lblPartName.gridx = 0;
		gbc_lblPartName.gridy = 1;
		formpanel.add(lblPartName, gbc_lblPartName);

		JTextField tfPartName = new JTextField();
		tfPartName.setText(" ");
		tfPartName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfPartName.setColumns(30);
		GridBagConstraints gbc_partName = new GridBagConstraints();
		gbc_partName.gridwidth = 2;
		gbc_partName.insets = new Insets(10, 10, 10, 5);
		gbc_partName.anchor = GridBagConstraints.NORTHWEST;
		gbc_partName.gridx = 1;
		gbc_partName.gridy = 1;
		formpanel.add(tfPartName, gbc_partName);
	    
		 //Part profile 
	    JLabel lblPartProfile = new JLabel("Profile");
	    lblPartProfile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblPartProfile = new GridBagConstraints();
		gbc_lblPartProfile.anchor = GridBagConstraints.EAST;
		gbc_lblPartProfile.insets = new Insets(10, 10, 10, 5);
		gbc_lblPartProfile.gridx = 0;
		gbc_lblPartProfile.gridy = 2;
		formpanel.add(lblPartProfile, gbc_lblPartProfile);
		
		JComboBox<String> tfPartProfile = new JComboBox();
		tfPartProfile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfPartProfile.setModel(new DefaultComboBoxModel<String>(new String[] 
															   {
															     "Engine Assembly", "Sheet Metal", 
															     "Wheels", "Electrical",
															     "Electronics", "Paint",
															     "Accessories", "Audio/Video",
															     "Seats", "Fastners"
															    }));
		GridBagConstraints gbc_partProfile = new GridBagConstraints();
		gbc_partProfile.gridwidth = 2;
		gbc_partProfile.insets = new Insets(10, 10, 10, 5);
		gbc_partProfile.anchor = GridBagConstraints.NORTHWEST;
		gbc_partProfile.gridx = 1;
		gbc_partProfile.gridy = 2;
		formpanel.add(tfPartProfile, gbc_partProfile );	
		
		 //Part Material 
	    JLabel lblPartMaterial = new JLabel("Material");
	    lblPartMaterial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblPartMaterial = new GridBagConstraints();
		gbc_lblPartMaterial.anchor = GridBagConstraints.EAST;
		gbc_lblPartMaterial.insets = new Insets(10, 10, 10, 5);
		gbc_lblPartMaterial.gridx = 0;
		gbc_lblPartMaterial.gridy = 3;
		formpanel.add(lblPartMaterial, gbc_lblPartMaterial);
		
		JComboBox<String> tfPartMaterial = new JComboBox<String>();
		tfPartMaterial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfPartMaterial.setModel(new DefaultComboBoxModel<String>(new String[] 
															   {
															     "Aluminium", "Plastic", 
															     "Leather", "Fabric",
															     "Steel", "Others",
															     "Rubber", "Fibre",
															     "Glass", "ABS"
															    }));
		GridBagConstraints gbc_partMaterial = new GridBagConstraints();
		gbc_partMaterial.gridwidth = 2;
		gbc_partMaterial.insets = new Insets(10, 10, 10, 5);
		gbc_partMaterial.anchor = GridBagConstraints.NORTHWEST;
		gbc_partMaterial.gridx = 1;
		gbc_partMaterial.gridy = 3;
		formpanel.add(tfPartMaterial, gbc_partMaterial );	
		
		//Typical Price 
	    JLabel lblPartTypPrice = new JLabel("Typical Price($)");
	    lblPartTypPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblPartTypPrice = new GridBagConstraints();
		gbc_lblPartTypPrice.anchor = GridBagConstraints.EAST;
		gbc_lblPartTypPrice.insets = new Insets(10, 10, 10, 5);
		gbc_lblPartTypPrice.gridx = 0;
		gbc_lblPartTypPrice.gridy = 4;
		formpanel.add(lblPartTypPrice, gbc_lblPartTypPrice);

		JTextField tfPartTypPrice = new JTextField();
		tfPartTypPrice.setText(" ");
		tfPartTypPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfPartTypPrice.setColumns(30);
		GridBagConstraints gbc_partTypPrice = new GridBagConstraints();
		gbc_partTypPrice.gridwidth = 2;
		gbc_partTypPrice.insets = new Insets(10, 10, 10, 5);
		gbc_partTypPrice.anchor = GridBagConstraints.NORTHWEST;
		gbc_partTypPrice.gridx = 1;
		gbc_partTypPrice.gridy = 4;
		formpanel.add(tfPartTypPrice, gbc_partTypPrice);
		
		//Specifications
	    JLabel lblPartSpec = new JLabel("Specifications");
	    lblPartSpec.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblPartSpec = new GridBagConstraints();
		gbc_lblPartSpec.anchor = GridBagConstraints.EAST;
		gbc_lblPartSpec.insets = new Insets(10, 10, 10, 5);
		gbc_lblPartSpec.gridx = 0;
		gbc_lblPartSpec.gridy = 5;
		formpanel.add(lblPartSpec, gbc_lblPartSpec);

		JTextArea tfPartSpec = new JTextArea(2,30);
		tfPartSpec.setText(" ");
		tfPartSpec.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		tfPartSpec.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_partSpec = new GridBagConstraints();
		gbc_partSpec.gridwidth = 2;
		gbc_partSpec.insets = new Insets(10, 10, 10, 5);
		gbc_partSpec.anchor = GridBagConstraints.NORTHWEST;
		gbc_partSpec.gridx = 1;
		gbc_partSpec.gridy = 5;
		formpanel.add(tfPartSpec, gbc_partSpec);
		
		
		//Typical Price 
	    JLabel lblPartTypDlvTime = new JLabel("Typical Delivery Time");
	    lblPartTypDlvTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblPartTypDlvTime = new GridBagConstraints();
		gbc_lblPartTypDlvTime.anchor = GridBagConstraints.EAST;
		gbc_lblPartTypDlvTime.insets = new Insets(10, 10, 10, 5);
		gbc_lblPartTypDlvTime.gridx = 0;
		gbc_lblPartTypDlvTime.gridy = 6;
		formpanel.add(lblPartTypDlvTime, gbc_lblPartTypDlvTime);

		JTextField tfPartTypDlvTime = new JTextField();
		tfPartTypDlvTime.setText(" ");
		tfPartTypDlvTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfPartTypDlvTime.setColumns(30);
		GridBagConstraints gbc_partTypDlvTime = new GridBagConstraints();
		gbc_partTypDlvTime.gridwidth = 2;
		gbc_partTypDlvTime.insets = new Insets(10, 10, 10, 5);
		gbc_partTypDlvTime.anchor = GridBagConstraints.NORTHWEST;
		gbc_partTypDlvTime.gridx = 1;
		gbc_partTypDlvTime.gridy = 6;
		formpanel.add(tfPartTypDlvTime, gbc_partTypDlvTime);
		
		//Quality Standards
	    JLabel lblPartQltyStd = new JLabel("Quality Standards");
	    lblPartQltyStd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblPartQltyStd = new GridBagConstraints();
		gbc_lblPartQltyStd.anchor = GridBagConstraints.EAST;
		gbc_lblPartQltyStd.insets = new Insets(10, 10, 10, 5);
		gbc_lblPartQltyStd.gridx = 0;
		gbc_lblPartQltyStd.gridy = 7;
		formpanel.add(lblPartQltyStd, gbc_lblPartQltyStd);

		JTextField tfPartQltyStd = new JTextField();
		tfPartQltyStd.setText(" ");
		tfPartQltyStd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfPartQltyStd.setColumns(30);
		GridBagConstraints gbc_partQltyStd = new GridBagConstraints();
		gbc_partQltyStd.gridwidth = 2;
		gbc_partQltyStd.insets = new Insets(10, 10, 10, 5);
		gbc_partQltyStd.anchor = GridBagConstraints.NORTHWEST;
		gbc_partQltyStd.gridx = 1;
		gbc_partQltyStd.gridy = 7;
		formpanel.add(tfPartQltyStd, gbc_partQltyStd);
		
	    btnSave.addActionListener(new ActionListener() 
        {
        	 
            public void actionPerformed(ActionEvent e) 
            {
            	System.out.println("You have chosen Create->Part->Save !!!");
         		SessionFactory sessFact = HibernateUtil.getSessionFactory();
        		session = sessFact.getCurrentSession();
        		org.hibernate.Transaction tr = session.beginTransaction();
        		PartModel partModel = new PartModel();
        		partModel.setpartId(tfPartID.getText());
        		partModel.setpartName(tfPartName.getText());
        		partModel.setpartProf(tfPartProfile.getSelectedItem().toString());
        		partModel.setpartMaterial(tfPartMaterial.getSelectedItem().toString());
        		partModel.setpartSpec(tfPartSpec.getText());
        		partModel.setpartTypPrice(Integer.parseInt(tfPartTypPrice.getText()));	
        		partModel.setpartTypSuppSch(Integer.parseInt(tfPartTypDlvTime.getText()));
        		partModel.setpartQlyStd(tfPartQltyStd.getText());

        		session.save(partModel);
        		tr.commit();
        		System.out.println("Successfully inserted Part Info");        		
        		//sessFact.close();
            }
        });//End of Save button action 
		
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
    
    } //End of PartAddUI()
	
	 public void PartListAllUI()
	    {   	
	    	List<PartModel> Parts;
	    	JPanel panel = new JPanel();
	    	panel.setLayout(new BorderLayout());

	    	GridBagConstraints gs = new GridBagConstraints();
	    	gs.fill = GridBagConstraints.HORIZONTAL;
	    	
	    	System.out.println("You are in View->Parts");
	    	{
	     		SessionFactory sessFact = HibernateUtil.getSessionFactory();
	    		session = sessFact.getCurrentSession();
	    		org.hibernate.Transaction tr = session.beginTransaction();

				Criteria criteria = session.createCriteria(PartModel.class);
				Parts = criteria.list();

	    		Iterator<PartModel> itr = Parts.iterator();
	    		
	    		while (itr.hasNext()) {

	    			PartModel vendM = itr.next();
	    			System.out.println(vendM.getpartName());
	    			/* 
	    			 * Add more field to be displayed on the console
	    			 * System.out.println(vendM.getvendAddr1());
	       			*/
	    		}
	    		tr.commit();
	    		System.out.println("Data displayed for Parts");

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
	            	
	       	JLabel lblHeading = new JLabel("List of Parts", SwingConstants.CENTER);
	       	lblHeading.setFont(new Font("Arial",Font.TRUETYPE_FONT,24));
	       	lblHeading.setForeground (Color.green);
	       	lblHeading.setBackground (Color.white);
	       	lblHeading.setOpaque(true); 
	       	hp.add(lblHeading,BorderLayout.CENTER);
	       	
	    	//create the model
	        PartTableModel model = new PartTableModel(Parts);
	        
	        //create the table
	        JTable table = new JTable(model);
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
}//?End of Class 
