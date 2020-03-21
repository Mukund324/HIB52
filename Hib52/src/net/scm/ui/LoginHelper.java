package net.scm.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginHelper extends JDialog{
	 
		private static final long serialVersionUID = 1L;
		public JFrame parent1;
		
		public LoginHelper(JFrame parent)
		{
			super(parent, "Vendor Management System", true);
			parent1=parent;
		}
	    
	    //Creating UI of the Login Screen
	    public  void LoginUI()
	    {   
	    		    	
			JTextField tfUsername;
		    JPasswordField pfPassword;
		    JLabel lbUsername;
		    JLabel lbPassword;
		    JButton btnLogin;
		    JButton btnCancel;

		    JPanel title = new JPanel();
		    title.setBackground(Color.decode("#006666"));
		    title.setPreferredSize(new Dimension(635,30));
	        title.setOpaque(true);
	        
		    JLabel ltitle = new JLabel("Welcome to Vendor Management Portal");
		    ltitle.setHorizontalAlignment(JLabel.CENTER);
		    ltitle.setVerticalAlignment(JLabel.CENTER);
		    ltitle.setFont(new Font("Arial",Font.TRUETYPE_FONT,16));
		    ltitle.setForeground(Color.WHITE);
		    title.add(ltitle);
		    
		    //Border blackline = BorderFactory.createLineBorder(Color.black);    
		    BufferedImage LoginPicture = null;
			try {
				LoginPicture = ImageIO.read(new File("C:\\Users\\Mukund\\eclipse-workspace\\Hib52\\src\\net\\scm\\ui\\WM.jpg"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			ImageIcon imageIcon = new ImageIcon(new ImageIcon("C:\\Users\\Mukund\\eclipse-workspace\\Hib52\\src\\net\\scm\\ui\\WM.jpg").getImage().getScaledInstance(320, 400, Image.SCALE_DEFAULT));
			
			
			//Panel for Picture
			JPanel picpanel = new JPanel(new BorderLayout());
	    	picpanel.setBackground(Color.WHITE);
	    	picpanel.setPreferredSize(new Dimension(320, 400));
	     	picpanel.setOpaque(true);
	    	//picpanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,Color.gray, Color.gray));
			
	    	JLabel picLabel = new JLabel();
		    //JLabel picLabel = new JLabel(new ImageIcon(LoginPicture));
	    	picLabel.setBackground(Color.WHITE);
		    picLabel.setIcon(imageIcon);
		    picLabel.setPreferredSize(new Dimension(320, 400));
		    picLabel.setHorizontalAlignment(JLabel.CENTER);
	        picLabel.setVerticalAlignment(JLabel.CENTER);
		    //picLabel.setBorder(blackline);
	        picpanel.add(picLabel, BorderLayout.CENTER);
	        
			//Panel for Username and Input 
	        JPanel inppanel = new JPanel(new GridBagLayout());
	        inppanel.setPreferredSize(new Dimension(320, 400));
	    	inppanel.setBackground(Color.WHITE);
	    	inppanel.setOpaque(true);
		    //inppanel.setBorder(blackline);
		    
	    	GridBagConstraints cs = new GridBagConstraints();
	        cs.fill = GridBagConstraints.HORIZONTAL;
	        
	        cs.gridx = 0;
	        cs.gridy = 0;
	        cs.insets = new Insets(3,3,3,3);
	        cs.gridwidth = 1;
	        
	        lbUsername = new JLabel("Username ");
	        lbUsername.setFont(new Font("Arial",Font.TRUETYPE_FONT,14));
	        inppanel.add(lbUsername,cs);
	        
	        cs.gridx = 1;
	        cs.gridy = 0;
	        cs.insets = new Insets(3,3,3,3);
	        cs.gridwidth = 1;
               
	        tfUsername = new JTextField(15);
	        tfUsername.setFont(new Font("Arial",Font.TRUETYPE_FONT,14));
	        tfUsername.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, Color.gray, Color.gray));
	        tfUsername.setToolTipText("Please enter your UserName here");
	        inppanel.add(tfUsername,cs);
	        
	        lbPassword = new JLabel("Password ");
	        lbPassword.setFont(new Font("Arial",Font.TRUETYPE_FONT,14));
	        cs.gridx = 0;
	        cs.gridy = 1;
	        cs.gridwidth = 1;
	        cs.insets = new Insets(3,3,3,3);
	        inppanel.add(lbPassword, cs);
	        
	        pfPassword = new JPasswordField(15);
	        pfPassword.setFont(new Font("Arial",Font.TRUETYPE_FONT,14));
	        pfPassword.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, Color.gray, Color.gray));
	        pfPassword.setToolTipText("Please enter your password here");
	        cs.gridx = 1;
	        cs.gridy = 1;
	        cs.gridwidth = 2;
	        cs.insets = new Insets(3,3,3,3);
	        inppanel.add(pfPassword, cs);
		    

	        btnLogin = new JButton("Login");
	        
	        btnLogin.addActionListener(new ActionListener() 
	        {
	        	 
				public void actionPerformed(ActionEvent e) 
	            {
				   char[] password = pfPassword.getPassword();	
				   String pwd = new String(password);
	               if (authenticate(tfUsername.getText().trim(), pwd))
	               {
	                   // JOptionPane.showMessageDialog(null , "Hi " + tfUsername.getText().trim() + "! You have successfully logged in.", "Login",
	                   //         JOptionPane.INFORMATION_MESSAGE);
	                    dispose();
	                    
	            		MainMenuHelper mmframe=new MainMenuHelper(parent1);
	            		mmframe.MainMenuUI();
	            		mmframe.setVisible(true);	
	               } 
	               else 
	               {
	                   JOptionPane.showMessageDialog(null, "Invalid username or password", "Login", JOptionPane.ERROR_MESSAGE);
	                    // reset username and password
	                    tfUsername.setText("");
	                    pfPassword.setText("");

	              }
	           }
	        });
	    
	        btnCancel = new JButton("Cancel");
	        btnCancel.addActionListener(new ActionListener() {
	 
	            public void actionPerformed(ActionEvent e) {
	            }
	        });
	        
	        JPanel bp = new JPanel();
	        bp.setBackground(Color.decode("#87CEFA"));
	        bp.setOpaque(true);
	        bp.add(btnLogin);
	        bp.add(btnCancel);
	        //panel.add(bp);
	        //panel.setPreferredSize(new Dimension(640, 480));  
	      
	        getContentPane().add(title, BorderLayout.NORTH);
	        getContentPane().add(picpanel, BorderLayout.WEST);
	        getContentPane().add(inppanel, BorderLayout.EAST);
	        getContentPane().add(bp, BorderLayout.PAGE_END);
	 
	        pack();
	        setResizable(false);
	        setLocationRelativeTo(parent1);
	    }
	    
		public static boolean authenticate(String username,String password )
		
		{
			if(username.contentEquals("abcd") && password.equals("123"))
			{
				return true;
			}
				return false;
		}					    
	   
}
