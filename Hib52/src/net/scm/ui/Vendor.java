package net.scm.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Vendor extends JDialog 
{
	private static final long serialVersionUID = 1L;
    /*
	private JLabel lbVendorName;
    private JTextField tfVendorName;
    private JLabel lbVendorBldgNo;
    private JTextField tfVendorBldgNo;
    private JLabel lbVendorStreet;
    private JTextField tfVendorStreet;
    private JLabel lbVendorCity;
    private JTextField tfVendorCity;
    private JLabel lbVendorCountry;
    private JTextField tfVendorCountry;
    private JLabel lbVendorClass;
    private JTextField tfVendorClass;
    private JButton btnSave;
    private JButton btnCancel;
    
    //Creating constructor of vendorFrame() class
    Vendor(JFrame parent)
    {
    	super(parent, "Vendor", true);

    	JPanel panel = new JPanel(new GridBagLayout());
    	GridBagConstraints gs = new GridBagConstraints();
    	gs.fill = GridBagConstraints.HORIZONTAL;
    	
    	lbVendorName = new JLabel("Name ");
        gs.gridx = 0;
        gs.gridy = 0;
        gs.gridwidth = 1;
        panel.add(lbVendorName, gs);
         
        tfVendorName = new JTextField(20);
        gs.gridx = 1;
        gs.gridy = 0;
        gs.gridwidth = 1;
        panel.add(tfVendorName, gs); 
        
        lbVendorBldgNo = new JLabel("Bldg/Apt # ");
        gs.gridx = 0;
        gs.gridy = 1;
        gs.gridwidth = 1;
        panel.add(lbVendorBldgNo, gs);
         
        tfVendorBldgNo = new JTextField(10);
        gs.gridx = 1;
        gs.gridy = 1;
        gs.gridwidth = 1;
        panel.add(tfVendorBldgNo, gs);  
        
        lbVendorStreet = new JLabel("Street/Area");
        gs.gridx = 0;
        gs.gridy = 2;
        gs.gridwidth = 1;
        panel.add(lbVendorStreet, gs);
         
        tfVendorStreet = new JTextField(50);
        gs.gridx = 1;
        gs.gridy = 2;
        gs.gridwidth = 1;
        panel.add(tfVendorStreet, gs);  
        
        lbVendorCity = new JLabel("City ");
        gs.gridx = 0;
        gs.gridy = 3;
        gs.gridwidth = 1;
        panel.add(lbVendorCity, gs);
         
        tfVendorCity = new JTextField(50);
        gs.gridx = 1;
        gs.gridy = 3;
        gs.gridwidth = 1;
        panel.add(tfVendorCity, gs);
        
        lbVendorCountry = new JLabel("Country ");
        gs.gridx = 0;
        gs.gridy = 4;
        gs.gridwidth = 1;
        panel.add(lbVendorCountry, gs);
         
        tfVendorCountry = new JTextField(50);
        gs.gridx = 1;
        gs.gridy = 4;
        gs.gridwidth = 1;
        panel.add(tfVendorCountry, gs);
        
        btnSave = new JButton("Save");
        btnCancel = new JButton("Cancel");
        //btnMenu   = new JButton("Back to Menu");
        
        btnCancel.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        btnSave.addActionListener(new ActionListener() 
        {
        	 
            public void actionPerformed(ActionEvent e) 
            {
            	 JOptionPane.showMessageDialog(Vendor.this, "! You have saved the VendorName !!!.", "Vendor",
                         JOptionPane.INFORMATION_MESSAGE);
            	 dispose();
            	 MainMenu mframe=new MainMenu(parent);
         		mframe.setVisible(true);
            }
        });
        
        btnCancel.addActionListener(new ActionListener() 
        {
        	 
            public void actionPerformed(ActionEvent e) 
            {
            	dispose();
            	MainMenu mframe=new MainMenu(parent);
        		mframe.setVisible(true);
            }
        });
        
        JPanel bp = new JPanel();
        bp.add(btnSave);
        bp.add(btnCancel);
  
        panel.setPreferredSize(new Dimension(640, 480));       
        getContentPane().add(panel, BorderLayout.PAGE_START);
        getContentPane().add(bp, BorderLayout.PAGE_END);
        pack();
        setResizable(true);
        setLocationRelativeTo(parent);

     }
             */
   }

