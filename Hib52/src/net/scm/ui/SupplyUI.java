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

import net.scm.model.BoMModel;
import net.scm.model.HibernateUtil;
import net.scm.model.PartModel;
import net.scm.model.ProductModel;
import net.scm.model.SupplyModel;
import net.scm.model.VendorModel;

public class SupplyUI extends JDialog {
	
	private static final long serialVersionUID = 1L;
	public JFrame parent1;
	public Session session;
	
	public JLabel lbPartName;
	public JTextField tfPartName;
	public JLabel lblPartId;
	public JTextField tfPartId;
	public JLabel lblVendorName;
	public JTextField tfVendorName;
	public JLabel lblVendorId;
	public JTextField tfVendorId;
	public JLabel lblVendPartPrice;
	public JTextField tfVendPartPrice;
	public JLabel lblVendPartClass;

	public JLabel lblVendSupLeadTime;
	public JTextField tfVendSupLeadTime;
	public JLabel lblVendShipMode;
	public JTextField tfVendShipMode;
	public JLabel lblVendShipUnitSize;
	public JTextField tfVendShipUnitSize;
	public JLabel lblVendShipUnitCost;
	
	public JTextField tfVendPrice;
	public JTextField tfVendSuppTime;
	public JTextField tfVendSuppClass;

	public String insPartId;
	public String insVendId;
	public String insPartName;
	public String insVendName;
	public Integer insSupplyLeadTime;
	public String insSupplyClass;
	public Integer insSupplyPrice;
	
	public JButton btnSave;
	public JButton btnReset;
	public JButton btnMenu;
	
	public SupplyUI(JFrame parent)
	{
		super(parent, "Vendor Management System", true);
		parent1=parent;
	}//End of Constructor 
	
	//Method to Add Line Items to the BOM DB 
	public void SupplyAddUI()
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
	    
		JLabel lblPartName = new JLabel("Select Part");
		lblPartName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblPartName = new GridBagConstraints();
		gbc_lblPartName.anchor = GridBagConstraints.EAST;
		gbc_lblPartName.insets = new Insets(10, 10, 10, 5);
		gbc_lblPartName.gridx = 0;
		gbc_lblPartName.gridy = 1;
		formpanel.add(lblPartName, gbc_lblPartName);
		
		JTextField tfVendPrice = new JTextField();
		JTextField tfVendSuppTime = new JTextField();
		JComboBox tfVendSuppClass = new JComboBox();
		
		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		session = sessFact.getCurrentSession();
		org.hibernate.Transaction tr = session.beginTransaction();

		CriteriaBuilder builder = session.getCriteriaBuilder();

        javax.persistence.criteria.CriteriaQuery<String> query = builder.createQuery(String.class);
	    Root<PartModel> root = query.from(PartModel.class); 
        query.select(root.get("partName"));
        Query<String> q=session.createQuery(query);
        List<String> partNameList=q.getResultList();
        //for (String name : partNameList) {
        //    System.out.println(name);
        //}	
        tr.commit();
        
        String[] partStrArr = new String[partNameList.size()];
        partNameList.toArray(partStrArr );
		
	    JComboBox<String> tfPartName = new JComboBox<String>();
		tfPartName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfPartName.setModel(new DefaultComboBoxModel<String>(partStrArr));
		GridBagConstraints gbc_tfPartName = new GridBagConstraints();
		gbc_tfPartName.gridwidth = 2;
		gbc_tfPartName.insets = new Insets(10, 10, 10, 5);
		gbc_tfPartName.anchor = GridBagConstraints.NORTHWEST;
		gbc_tfPartName.gridx = 1;
		gbc_tfPartName.gridy = 1;
		formpanel.add(tfPartName, gbc_tfPartName);
		
		
		JLabel lblVendName = new JLabel("Select Vendor to Add");
		lblVendName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblVendName = new GridBagConstraints();
		gbc_lblVendName.anchor = GridBagConstraints.EAST;
		gbc_lblVendName.insets = new Insets(10, 10, 10, 5);
		gbc_lblVendName.gridx = 0;
		gbc_lblVendName.gridy = 1;
		pformpanel.add(lblVendName, gbc_lblVendName);
		
		SessionFactory sessFact1 = HibernateUtil.getSessionFactory();
		session = sessFact1.getCurrentSession();
		org.hibernate.Transaction tr1 = session.beginTransaction();
		CriteriaBuilder builder1 = session.getCriteriaBuilder();

        javax.persistence.criteria.CriteriaQuery<String> query1 = builder1.createQuery(String.class);
	    Root<VendorModel> root1 = query1.from(VendorModel.class); 
        query1.select(root1.get("vendName"));
        Query<String> q1=session.createQuery(query1);
        List<String> vendNameList=q1.getResultList();
        //for (String name : partNameList) {
        //    System.out.println(name);
        //}
		
        tr1.commit();
        
        String[] vendStrArr = new String[vendNameList.size()];
        vendNameList.toArray(vendStrArr );
        
	    JComboBox<String> tfVendName = new JComboBox<String>();
		tfVendName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfVendName.setModel(new DefaultComboBoxModel<String>(vendStrArr));
		GridBagConstraints gbc_vendName = new GridBagConstraints();
		gbc_vendName.gridwidth = 2;
		gbc_vendName.insets = new Insets(10, 10, 10, 5);
		gbc_vendName.anchor = GridBagConstraints.NORTHWEST;
		gbc_vendName.gridx = 1;
		gbc_vendName.gridy = 1;
		pformpanel.add(tfVendName, gbc_vendName);
		
		
	    // Create an ActionListener for the PART Listing JComboBox component.
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
                org.hibernate.query.Query<PartModel> q=session.createQuery(query);             
                PartModel part=q.getSingleResult();
                System.out.println(part.getpartName());               

                tr.commit();
                
                JLabel lblPartID = new JLabel("Product ID");
        		lblPartID.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_lblPartID = new GridBagConstraints();
        		gbc_lblPartID.anchor = GridBagConstraints.EAST;
        		gbc_lblPartID.insets = new Insets(10, 10, 10, 5);
        		gbc_lblPartID.gridx = 0;
        		gbc_lblPartID.gridy = 3;
        		formpanel.add(lblPartID, gbc_lblPartID);
        		
        		JLabel flblPartID = new JLabel(part.getpartId());
        		insPartId = part.getpartId();
        		flblPartID.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_flblPartID = new GridBagConstraints();
        		gbc_flblPartID.anchor = GridBagConstraints.NORTHWEST;
        		gbc_flblPartID.insets = new Insets(10, 10, 10, 5);
        		gbc_flblPartID.gridx = 1;
        		gbc_flblPartID.gridy = 3;
        		formpanel.add(flblPartID, gbc_flblPartID);
        		
        		JLabel lblPartProfile = new JLabel("Profile");
        		lblPartProfile.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_lblPartProfile = new GridBagConstraints();
        		gbc_lblPartProfile.anchor = GridBagConstraints.EAST;
        		gbc_lblPartProfile.insets = new Insets(10, 10, 10, 5);
        		gbc_lblPartProfile.gridx = 0;
        		gbc_lblPartProfile.gridy = 5;
        		formpanel.add(lblPartProfile, gbc_lblPartProfile);
        		
        		JLabel flblPartProfile = new JLabel(part.getpartProf());
        		flblPartProfile.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_flblPartProfile = new GridBagConstraints();
        		gbc_flblPartProfile.anchor = GridBagConstraints.NORTHWEST;
        		gbc_flblPartProfile.insets = new Insets(10, 10, 10, 5);
        		gbc_flblPartProfile.gridx = 1;
        		gbc_flblPartProfile.gridy = 5;
        		formpanel.add(flblPartProfile, gbc_flblPartProfile);
        		
        		JLabel lblPartSpec = new JLabel("Specification");
        		lblPartSpec.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_lblPartSpec = new GridBagConstraints();
        		gbc_lblPartSpec.anchor = GridBagConstraints.EAST;
        		gbc_lblPartSpec.insets = new Insets(10, 10, 10, 5);
        		gbc_lblPartSpec.gridx = 0;
        		gbc_lblPartSpec.gridy = 7;
        		formpanel.add(lblPartSpec, gbc_lblPartSpec);
        		
           		JLabel flblPartSpec = new JLabel(part.getpartSpec());
        		flblPartSpec.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_flblPartSpec = new GridBagConstraints();
        		gbc_flblPartSpec.anchor = GridBagConstraints.NORTHWEST;
        		gbc_flblPartSpec.insets = new Insets(10, 10, 10, 5);
        		gbc_flblPartSpec.gridx = 1;
        		gbc_flblPartSpec.gridy = 7;
        		formpanel.add(flblPartSpec, gbc_flblPartSpec);
        		
        		JLabel lblPartMat = new JLabel("Material");
        		lblPartMat.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_lblPartMat = new GridBagConstraints();
        		gbc_lblPartMat.anchor = GridBagConstraints.EAST;
        		gbc_lblPartMat.insets = new Insets(10, 10, 10, 5);
        		gbc_lblPartMat.gridx = 0;
        		gbc_lblPartMat.gridy = 9;
        		formpanel.add(lblPartMat, gbc_lblPartMat);
        		
        		JLabel flblPartMat = new JLabel(part.getpartMaterial());
        		flblPartMat.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_flblPartMat = new GridBagConstraints();
        		gbc_flblPartMat.anchor = GridBagConstraints.NORTHWEST;
        		gbc_flblPartMat.insets = new Insets(10, 10, 10, 5);
        		gbc_flblPartMat.gridx = 1;
        		gbc_flblPartMat.gridy = 9;
        		formpanel.add(flblPartMat, gbc_flblPartMat);
        		
        		//Typical Delivery Schedule 
        	    JLabel lblPartTypDlvTime = new JLabel("Typical Delivery Time");
        	    lblPartTypDlvTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_lblPartTypDlvTime = new GridBagConstraints();
        		gbc_lblPartTypDlvTime.anchor = GridBagConstraints.EAST;
        		gbc_lblPartTypDlvTime.insets = new Insets(10, 10, 10, 5);
        		gbc_lblPartTypDlvTime.gridx = 0;
        		gbc_lblPartTypDlvTime.gridy = 11;
        		formpanel.add(lblPartTypDlvTime, gbc_lblPartTypDlvTime);
        		
        	    JLabel tflblPartTypDlvTime = new JLabel(Integer.toString(part.getpartTypSuppSch()));
        	    tflblPartTypDlvTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_tflblPartTypDlvTime = new GridBagConstraints();
        		gbc_tflblPartTypDlvTime.anchor = GridBagConstraints.NORTHWEST;
        		gbc_tflblPartTypDlvTime.insets = new Insets(10, 10, 10, 5);
        		gbc_tflblPartTypDlvTime.gridx = 1;
        		gbc_tflblPartTypDlvTime.gridy = 11;
        		formpanel.add(tflblPartTypDlvTime, gbc_tflblPartTypDlvTime);
        		
        		//Typical Price 
        	    JLabel lblPartTypPrice = new JLabel("Typical Price($)");
        	    lblPartTypPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_lblPartTypPrice = new GridBagConstraints();
        		gbc_lblPartTypPrice.anchor = GridBagConstraints.EAST;
        		gbc_lblPartTypPrice.insets = new Insets(10, 10, 10, 5);
        		gbc_lblPartTypPrice.gridx = 0;
        		gbc_lblPartTypPrice.gridy = 13;
        		formpanel.add(lblPartTypPrice, gbc_lblPartTypPrice);
        		
           	    JLabel tflblPartTypPrice = new JLabel(Integer.toString(part.getpartTypPrice()));
        	    tflblPartTypPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_tflblPartTypPrice = new GridBagConstraints();
        		gbc_tflblPartTypPrice.anchor = GridBagConstraints.NORTHWEST;
        		gbc_tflblPartTypPrice.insets = new Insets(10, 10, 10, 5);
        		gbc_tflblPartTypPrice.gridx = 1;
        		gbc_tflblPartTypPrice.gridy = 13;
        		formpanel.add(tflblPartTypPrice, gbc_tflblPartTypPrice);

        		
                formpanel.removeAll();
                formpanel.invalidate();
                
        		formpanel.add(lblPartName, 			gbc_lblPartName);
        		formpanel.add(tfPartName, 			gbc_tfPartName);
        		formpanel.add(lblPartID, 			gbc_lblPartID);
        		formpanel.add(flblPartID, 			gbc_flblPartID);
        		formpanel.add(lblPartProfile, 		gbc_lblPartProfile);
        		formpanel.add(flblPartProfile, 		gbc_flblPartProfile);
        		formpanel.add(lblPartSpec, 			gbc_lblPartSpec);
        		formpanel.add(flblPartSpec, 		gbc_flblPartSpec);
        		formpanel.add(lblPartMat,			gbc_lblPartMat);
        		formpanel.add(flblPartMat, 			gbc_flblPartMat);
        		formpanel.add(lblPartTypDlvTime, 	gbc_lblPartTypDlvTime);
        		formpanel.add(tflblPartTypDlvTime, 	gbc_tflblPartTypDlvTime);
        		
        		formpanel.revalidate();
        		formpanel.repaint();
            }
        });
		
	    // Create an ActionListener for the VENDOR JComboBox component.		
		tfVendName.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent event) 
            {
                JComboBox<String> tfVendName = (JComboBox<String>)event.getSource();
                Object selected = tfVendName.getSelectedItem();
                System.out.println("Selected Item  = " + selected);
                insVendName = (String) selected;
                
        		SessionFactory sessFact = HibernateUtil.getSessionFactory();
        		session = sessFact.getCurrentSession();
        		org.hibernate.Transaction tr = session.beginTransaction();

        		CriteriaBuilder builder = session.getCriteriaBuilder();
                javax.persistence.criteria.CriteriaQuery<VendorModel> query = builder.createQuery(VendorModel.class);
                Root<VendorModel> root = query.from(VendorModel.class);    
                query.select(root).where(builder.equal(root.get("vendName"),selected));  
                Query<VendorModel> q=session.createQuery(query);             
                VendorModel vendor=q.getSingleResult();
                System.out.println(vendor.getvendName());               

                tr.commit();
                
                JLabel lblVendId = new JLabel("PartID");
        		lblVendId.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_lblVendId = new GridBagConstraints();
        		gbc_lblVendId.anchor = GridBagConstraints.EAST;
        		gbc_lblVendId.insets = new Insets(10, 10, 10, 5);
        		gbc_lblVendId.gridx = 0;
        		gbc_lblVendId.gridy = 3;
        		pformpanel.add(lblVendId, gbc_lblVendId);
        		
        		JLabel flblVendId = new JLabel(vendor.getvendId());
        		insVendId = vendor.getvendId();
        		flblVendId.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_flblVendId = new GridBagConstraints();
        		gbc_flblVendId.anchor = GridBagConstraints.NORTHWEST;
        		gbc_flblVendId.insets = new Insets(10, 10, 10, 5);
        		gbc_flblVendId.gridx = 1;
        		gbc_flblVendId.gridy = 3;
        		pformpanel.add(flblVendId, gbc_flblVendId);
                
        		JLabel lblVendAddr = new JLabel("Address");
        		lblVendAddr.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_lblVendAddr = new GridBagConstraints();
        		gbc_lblVendAddr.anchor = GridBagConstraints.EAST;
        		gbc_lblVendAddr.insets = new Insets(10, 10, 10, 5);
        		gbc_lblVendAddr.gridx = 0;
        		gbc_lblVendAddr.gridy = 5;
        		pformpanel.add(lblVendAddr, gbc_lblVendAddr);
        		
        		JLabel fVendAddr = new JLabel(vendor.getvendAddr1());
        		fVendAddr.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_fVendAddr = new GridBagConstraints();
        		gbc_fVendAddr.anchor = GridBagConstraints.NORTHWEST;
        		gbc_fVendAddr.insets = new Insets(10, 10, 10, 5);
        		gbc_fVendAddr.gridx = 1;
        		gbc_fVendAddr.gridy = 5;
        		pformpanel.add(fVendAddr, gbc_fVendAddr);
        		
        		JLabel lblVendCity = new JLabel("City");
        		lblVendCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_lblVendCity = new GridBagConstraints();
        		gbc_lblVendCity.anchor = GridBagConstraints.EAST;
        		gbc_lblVendCity.insets = new Insets(10, 10, 10, 5);
        		gbc_lblVendCity.gridx = 0;
        		gbc_lblVendCity.gridy = 7;
        		pformpanel.add(lblVendCity, gbc_lblVendCity);
        		
           		JLabel fVendCity = new JLabel(vendor.getvendCity());
        		fVendCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_fVendCity = new GridBagConstraints();
        		gbc_fVendCity.anchor = GridBagConstraints.NORTHWEST;
        		gbc_fVendCity.insets = new Insets(10, 10, 10, 5);
        		gbc_fVendCity.gridx = 1;
        		gbc_fVendCity.gridy = 7;
        		pformpanel.add(fVendCity, gbc_fVendCity);
        		
        		JLabel lblVendCountry = new JLabel("Country");
        		lblVendCountry.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_lblVendCountry = new GridBagConstraints();
        		gbc_lblVendCountry.anchor = GridBagConstraints.EAST;
        		gbc_lblVendCountry.insets = new Insets(10, 10, 10, 5);
        		gbc_lblVendCountry.gridx = 0;
        		gbc_lblVendCountry.gridy = 9;
        		pformpanel.add(lblVendCountry, gbc_lblVendCountry);
        		
           		JLabel fVendCountry = new JLabel(vendor.getvendCountry());
        		fVendCountry.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_fVendCountry = new GridBagConstraints();
        		gbc_fVendCountry.anchor = GridBagConstraints.NORTHWEST;
        		gbc_fVendCountry.insets = new Insets(10, 10, 10, 5);
        		gbc_fVendCountry.gridx = 1;
        		gbc_fVendCountry.gridy = 9;
        		pformpanel.add(fVendCountry, gbc_fVendCountry);
        		
        		//Typical Delivery Class
        	    JLabel lblVendSuppClass = new JLabel("Supply Quality Class");
        	    lblVendSuppClass.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_lblVendSuppClass = new GridBagConstraints();
        		gbc_lblVendSuppClass.anchor = GridBagConstraints.EAST;
        		gbc_lblVendSuppClass.insets = new Insets(10, 10, 10, 5);
        		gbc_lblVendSuppClass.gridx = 0;
        		gbc_lblVendSuppClass.gridy = 11;
        		pformpanel.add(lblVendSuppClass, gbc_lblVendSuppClass);
        		
        		//JComboBox tfVendSuppClass = new JComboBox();
        		tfVendSuppClass.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		tfVendSuppClass.setModel(new DefaultComboBoxModel(new String[] {"None", "Gold", "Silver", "Bronze"}));
        		tfVendSuppClass.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_tfVendSuppClass = new GridBagConstraints();
        		gbc_tfVendSuppClass.gridwidth = 2;
        		gbc_tfVendSuppClass.insets = new Insets(10, 10, 10, 5);
        		gbc_tfVendSuppClass.anchor = GridBagConstraints.NORTHWEST;
        		gbc_tfVendSuppClass.gridx = 1;
        		gbc_tfVendSuppClass.gridy =11;
        		pformpanel.add(tfVendSuppClass, gbc_tfVendSuppClass);  
        		insSupplyClass=tfVendSuppClass.getSelectedItem().toString();
        		
        		//Typical Delivery Schedule 
        	    JLabel lblVendSuppTime = new JLabel("Supply Lead Time");
        	    lblVendSuppTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_lblVendSuppTime = new GridBagConstraints();
        		gbc_lblVendSuppTime.anchor = GridBagConstraints.EAST;
        		gbc_lblVendSuppTime.insets = new Insets(10, 10, 10, 5);
        		gbc_lblVendSuppTime.gridx = 0;
        		gbc_lblVendSuppTime.gridy = 13;
        		pformpanel.add(lblVendSuppTime, gbc_lblVendSuppTime);
        		
        		//JTextField tfVendSuppTime = new JTextField();
        		tfVendSuppTime.setText(" ");
        		tfVendSuppTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		tfVendSuppTime.setColumns(5);
        		GridBagConstraints gbc_tfVendSuppTime = new GridBagConstraints();
        		gbc_tfVendSuppTime.gridwidth = 2;
        		gbc_tfVendSuppTime.insets = new Insets(10, 10, 10, 5);
        		gbc_tfVendSuppTime.anchor = GridBagConstraints.NORTHWEST;
        		gbc_tfVendSuppTime.gridx = 1;
        		gbc_tfVendSuppTime.gridy =13;
        		pformpanel.add(tfVendSuppTime, gbc_tfVendSuppTime);    
        		
        	
        		//Vendor Price 
        	    JLabel lblVendPrice = new JLabel("Price($)");
        	    lblVendPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_lblVendPrice = new GridBagConstraints();
        		gbc_lblVendPrice.anchor = GridBagConstraints.EAST;
        		gbc_lblVendPrice.insets = new Insets(10, 10, 10, 5);
        		gbc_lblVendPrice.gridx = 0;
        		gbc_lblVendPrice.gridy = 15;
        		pformpanel.add(lblVendPrice, gbc_lblVendPrice);
        		
           		//JTextField tfVendPrice = new JTextField();
           		tfVendPrice.setText(" ");
           		tfVendPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
           		tfVendPrice.setColumns(5);
        	    tfVendPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        		GridBagConstraints gbc_tfVendPrice = new GridBagConstraints();
        		gbc_tfVendPrice.anchor = GridBagConstraints.NORTHWEST;
        		gbc_tfVendPrice.insets = new Insets(10, 10, 10, 5);
        		gbc_tfVendPrice.gridx = 1;
        		gbc_tfVendPrice.gridy = 15;
        		pformpanel.add(tfVendPrice, gbc_tfVendPrice);
        		     	    
                
        		pformpanel.removeAll();
                pformpanel.invalidate();
                
                pformpanel.add(lblVendName, 	gbc_lblVendName);
        		pformpanel.add(tfVendName, 		gbc_vendName);
        		pformpanel.add(lblVendId, 		gbc_lblVendId);        		
        		pformpanel.add(flblVendId, 		gbc_flblVendId);
        		pformpanel.add(lblVendAddr, 	gbc_lblVendAddr);
         		pformpanel.add(fVendAddr, 		gbc_fVendAddr);
        		pformpanel.add(lblVendCity, 	gbc_lblVendCity);
        		pformpanel.add(fVendCity, 		gbc_fVendCity);
        		pformpanel.add(lblVendCountry, 	gbc_lblVendCountry);       		
        		pformpanel.add(fVendCountry, 	gbc_fVendCountry);          		
        		pformpanel.add(lblVendSuppTime, gbc_lblVendSuppTime);
        		pformpanel.add(tfVendSuppTime, 	gbc_tfVendSuppTime);    
        		pformpanel.add(lblVendSuppClass,gbc_lblVendSuppClass);
        		pformpanel.add(tfVendSuppClass, gbc_tfVendSuppClass);  
        		pformpanel.add(lblVendPrice,	gbc_lblVendPrice);
        		pformpanel.add(tfVendPrice, 	gbc_tfVendPrice);

        		pformpanel.revalidate();
        		pformpanel.repaint();
            }
        });
		
		tfVendPrice.addActionListener(new ActionListener() 
		{	       	 
	         public void actionPerformed(ActionEvent e) {
	        	 //System.out.println("! You have chosen Plan->BoM->Qty !!!");  
	        	 insSupplyPrice = (Integer.parseInt(tfVendPrice.getText()));
	         }
	    });	
		
		tfVendSuppTime.addActionListener(new ActionListener() 
		{	       	 
	         public void actionPerformed(ActionEvent e) {
	        	 //System.out.println("! You have chosen Plan->BoM->Qty !!!");  
	        	 insSupplyLeadTime = (Integer.parseInt(tfVendSuppTime.getText()));
	         }
	    });	
		
		tfVendSuppClass.addActionListener(new ActionListener() 
		{	       	 
	         public void actionPerformed(ActionEvent e) {
	        	 //System.out.println("! You have chosen Plan->BoM->Qty !!!"); 
	        	 Object selected = tfVendSuppClass.getSelectedItem();
	        	 insSupplyClass = (String)selected;
	         }
	    });	
		
        btnSave.addActionListener(new ActionListener() 
        {        	 
            public void actionPerformed(ActionEvent e) 
            {
        		
         		SessionFactory sessFact = HibernateUtil.getSessionFactory();
        		session = sessFact.getCurrentSession();
        		org.hibernate.Transaction tr = session.beginTransaction();
        		SupplyModel suppModel = new SupplyModel();
        		suppModel.setpartId(insPartId);
        		suppModel.setpartName(insPartName);
        		suppModel.setvendId(insVendId);
        		suppModel.setvendName(insVendName);
        		//suppModel.setvendSupplyClass(insSupplyClass);
        		suppModel.setvendSupplyClass(tfVendSuppClass.getSelectedItem().toString());
        		suppModel.setvendSupplyLeadTime(Integer.parseInt(tfVendSuppTime.getText()));
        		suppModel.setvendSupplyPrice(Integer.parseInt(tfVendPrice.getText())); 
         		session.save(suppModel);
        		tr.commit();
        		System.out.println("Successfully inserted Supplies Info");        		
        		//sessFact.close();
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
    }//End of SupplyUI method 
	
	 public void SupplyListAllUI()
	    {   	
	    	List<SupplyModel> Supplies;
	    	JPanel panel = new JPanel();
	    	panel.setLayout(new BorderLayout());

	    	GridBagConstraints gs = new GridBagConstraints();
	    	gs.fill = GridBagConstraints.HORIZONTAL;
	    	
	    	System.out.println("You are in View->Parts");
	    	{
	     		SessionFactory sessFact = HibernateUtil.getSessionFactory();
	    		session = sessFact.getCurrentSession();
	    		org.hibernate.Transaction tr = session.beginTransaction();

				Criteria criteria = session.createCriteria(SupplyModel.class);
				Supplies = criteria.list();

	    		Iterator<SupplyModel> itr = Supplies.iterator();
	    		
	    		while (itr.hasNext()) {

	    			SupplyModel suppM = itr.next();
	    			System.out.println(suppM.getpartName());
	    			/* 
	    			 * Add more field to be displayed on the console
	    			 * System.out.println(vendM.getvendAddr1());
	       			*/
	    		}
	    		tr.commit();
	    		System.out.println("Data displayed for Supplies");

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
	            	
	       	JLabel lblHeading = new JLabel("List of Supplies", SwingConstants.CENTER);
	       	lblHeading.setFont(new Font("Arial",Font.TRUETYPE_FONT,24));
	       	lblHeading.setForeground (Color.green);
	       	lblHeading.setBackground (Color.white);
	       	lblHeading.setOpaque(true); 
	       	hp.add(lblHeading,BorderLayout.CENTER);
	       	
	    	//create the model
	        SupplyTableModel model = new SupplyTableModel(Supplies);
	        
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
}//End of Class 
