package pis.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pis.dbutil.CrudOperation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Account extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPasswordField txtpass;
	private Connection con;
	private PreparedStatement ps;
	private JLabel idlabel;
	private JTextField txtid;
	private ResultSet rs;
	private JLabel lblPrecursorInfoSolutions;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Account frame = new Account();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Account() {
		setBackground(new Color(240, 248, 255));
		setTitle("Login Frame\r\n");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(300, 300, 523, 589);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel.setBounds(431, 303, 76, 17);
		contentPane.add(lblNewLabel);
		
		txtpass = new JPasswordField();
		txtpass.setBounds(551, 301, 100, 20);
		contentPane.add(txtpass);
		
		JButton login = new JButton("Login");
		login.setBounds(520, 516, 80, 23);
		login.addActionListener(this);
		contentPane.add(login);
		
		
		idlabel = new JLabel("Userid");
		idlabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		idlabel.setBounds(438, 234, 59, 14);
		contentPane.add(idlabel);
		
		txtid = new JTextField();
		txtid.setBounds(546, 231, 100, 20);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		lblPrecursorInfoSolutions = new JLabel("Precursor Info. Solution");
		lblPrecursorInfoSolutions.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblPrecursorInfoSolutions.setBounds(420, 54, 372, 57);
		contentPane.add(lblPrecursorInfoSolutions);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Krati Rastogi\\Pictures\\Saved Pictures\\user-info-icon.png"));
		label.setBounds(72, 108, 313, 301);
		contentPane.add(label);
		
		con=CrudOperation.createConnection();
		
		
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	String userid=txtid.getText();
	
	
	
	char[]pwd=txtpass.getPassword();
	String userpass=new String(pwd).trim();
	if(userid.isEmpty()||userid.length()==0||userpass.isEmpty())
	{
		JOptionPane.showMessageDialog(this, "data required");
		
	}
		
	else
	{String strsql="Select* from account where userid=? and userpassword=?";
	try {
		ps=con.prepareStatement(strsql);
		ps.setString(1, userid);
		ps.setString(2, userpass);
	 rs=ps.executeQuery();
		if(rs.next())
		{
			
			String user=rs.getString("usertype");
			if(user.equals("admin"))
			{
				AdminFrame a=new AdminFrame();
				a.setVisible(true);
				this.dispose();
		}
		if(user.equals("trainer"))
		{
			TrainerFrame t=new TrainerFrame();
			t.setVisible(true);
			this.dispose();
			}
		}
		else
		{JOptionPane.showMessageDialog(this, "invalid entry");
		txtid.setText("");
		txtpass.setText("");
		}
		}
	
	
	 catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		System.out.println(e1);
	}
	}
	}
}