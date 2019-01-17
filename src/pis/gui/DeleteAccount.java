package pis.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pis.dbutil.CrudOperation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;

public class DeleteAccount extends JFrame implements ActionListener {

	private JPanel txtpass;
	private JTextField accid;
	private JButton btnDeleteaccount;
	private Connection con;
	private PreparedStatement ps;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteAccount frame = new DeleteAccount();
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
	public DeleteAccount() {
		setBackground(new Color(211, 211, 211));
		setTitle("Delete Account");
		
		con=CrudOperation.createConnection();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		txtpass = new JPanel();
		txtpass.setBackground(new Color(211, 211, 211));
		txtpass.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(txtpass);
		txtpass.setLayout(null);
		txtpass.setLayout(null);
		
		JLabel lblAccountid = new JLabel("Accountid");
		lblAccountid.setBounds(500, 300, 100, 20);
		txtpass.add(lblAccountid);
		
		accid = new JTextField();
		accid.setBounds(600, 300, 200, 20);
		txtpass.add(accid);
		accid.setColumns(10);
		
		btnDeleteaccount = new JButton("DeleteAccount");
		btnDeleteaccount.setBounds(600, 500, 200, 50);
		txtpass.add(btnDeleteaccount);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Krati Rastogi\\Pictures\\Saved Pictures\\delete-file-icon.png"));
		label.setBounds(54, 45, 329, 327);
		txtpass.add(label);
		btnDeleteaccount.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String id=accid.getText();
		String strdelete="delete from account where userid=?";
		try {
			ps=con.prepareStatement(strdelete);
			ps.setString(1, id);
			int rw=ps.executeUpdate();
			if(rw>0)
			{
				int option=JOptionPane.showConfirmDialog(this, "are you sure");
				
				if(option==0)
				{
				JOptionPane.showMessageDialog(this, "account deleed successfully");
			}
		} 
			else
			{JOptionPane.showMessageDialog(this, "id not found");
				
				}
		}
			catch (Exception e2) {
		
				// TODO: handle exception
				System.out.print(e2);
		}
	}
}
