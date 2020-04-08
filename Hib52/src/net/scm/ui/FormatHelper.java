package net.scm.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class FormatHelper {
	
	public static JPanel getFormattedTitlePanel()
	{
		JPanel title = new JPanel();
		title.setBackground(Color.decode("#006666")); title.setPreferredSize(new Dimension(720,30)); title.setOpaque(true);
    	title.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
		return title;
	}
	
	public static JPanel getFormattedFormPanel()
	{
		JPanel formpanel = new JPanel(new GridBagLayout());
        formpanel.setPreferredSize(new Dimension(720, 400)); formpanel.setBackground(Color.white);  
		formpanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
		GridBagLayout gBL = new GridBagLayout();
		gBL.columnWidths = new int[]{100, 67, 86, 86, 0};
		gBL.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gBL.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE}; 
		gBL.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		return formpanel;
	}
	
	public static JPanel getFormattedTablePanel()
	{
    	JPanel tblpanel = new JPanel(new BorderLayout());
        tblpanel.setPreferredSize(new Dimension(720, 320));   
		tblpanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
		tblpanel.setBackground(Color.white);   
		return tblpanel;
	}
	
	public static JPanel getFormattedBpPanel()
	{
	    JPanel bp = new JPanel();
        bp.setBackground(Color.decode("#87CEFA")); bp.setPreferredSize(new Dimension(720, 40)); bp.setOpaque(true); 
    	bp.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
    	return bp;
	}
	
	public static JPanel getFormattedSelectPanel()
	{
		JPanel selpanel = new JPanel(new GridBagLayout());
        selpanel.setPreferredSize(new Dimension(200, 400)); selpanel.setBackground(Color.white);  
		selpanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
		GridBagLayout gBL = new GridBagLayout();
		gBL.columnWidths = new int[]{100, 67, 86, 86, 0};
		gBL.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gBL.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE}; 
		gBL.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		return selpanel;
	}
	
	public static JPanel getFormattedEditPanel()
	{
		JPanel editpanel = new JPanel(new GridBagLayout());
        editpanel.setPreferredSize(new Dimension(520, 400)); editpanel.setBackground(Color.white);  
		editpanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY));
		GridBagLayout gBL = new GridBagLayout();
		gBL.columnWidths = new int[]{100, 67, 86, 86, 0};
		gBL.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gBL.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE}; 
		gBL.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		return editpanel;
	}
	
	public static void setTitleLabel(JPanel pnl, String lbTxt)
	{
	    JLabel tLbl = new JLabel(lbTxt);
	    tLbl.setHorizontalAlignment(JLabel.CENTER); 
	    tLbl.setVerticalAlignment(JLabel.CENTER);
	    tLbl.setFont(new Font("Arial",Font.TRUETYPE_FONT,16)); 
	    tLbl.setForeground(Color.WHITE);
	    pnl.add(tLbl); 
	}
	
	public static void setFormattedFormLabel(JPanel pnl, String lbTxt, int gridx, int gridy, int fsize)
	{
	    JLabel fLbl = new JLabel(lbTxt);
		fLbl.setFont(new Font("Tahoma", Font.PLAIN, fsize));
		GridBagConstraints gbc_fLbl = new GridBagConstraints();
		gbc_fLbl.anchor = GridBagConstraints.EAST;
		gbc_fLbl.insets = new Insets(10, 10, 10, 5); 
		gbc_fLbl.gridx = gridx; 
		gbc_fLbl.gridy = gridy;
    	pnl.add(fLbl,gbc_fLbl);
	}
	
	public static JTextField getFormattedTextField(int fsize)
	{
		JTextField tfTxtfld = new JTextField();
		tfTxtfld.setText(" "); 
		tfTxtfld.setColumns(30);
		tfTxtfld.setFont(new Font("Tahoma", Font.PLAIN, fsize));
		return tfTxtfld;
	}
	
	public static GridBagConstraints getFormattedTFgbc(int gridx, int gridy)
	{
		GridBagConstraints gbc_tfTxtfld = new GridBagConstraints();
		gbc_tfTxtfld.anchor = GridBagConstraints.NORTHWEST;
		gbc_tfTxtfld.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfTxtfld.gridwidth = 2;
		gbc_tfTxtfld.insets = new Insets(10, 10, 10, 5);
		gbc_tfTxtfld.gridx = gridx;
		gbc_tfTxtfld.gridy = gridy;
		return gbc_tfTxtfld;
	}
	

}//End of FormatHelper Class 
