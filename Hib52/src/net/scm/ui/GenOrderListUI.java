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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.hibernate.Session;

public class GenOrderListUI extends JDialog {
	
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
	
	public GenOrderListUI(JFrame parent)
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
	    
	    btnSave = new JButton("Generate Order");
		btnReset = new JButton("Reset");
	    btnMenu = new JButton("Back to Menu");
	    bp.add(btnSave);
	    bp.add(btnReset);
	    bp.add(btnMenu);
	    
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
