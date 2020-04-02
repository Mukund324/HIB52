package net.scm.ui;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class AppMain 
{	
	public static void main(String[] args)
	{	
		final JFrame frame = new JFrame("Supply Chain Management");
		//frame.setBounds(30,30,370,600);
		frame.setTitle("Supply Chain Management");
		frame.setSize(720,480);
		frame.setVisible(true);
		
		LoginHelper lframe=new LoginHelper(frame);
		frame.setVisible(false);
		lframe.LoginUI();
		lframe.setVisible(true);	
		
    }
}
