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
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;

public class DeleteStudent extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtid;
	private PreparedStatement ps,pss;
	private ResultSet rs;
	private Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteStudent frame = new DeleteStudent();
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
	public DeleteStudent() {
		setBackground(new Color(245, 245, 220));
		setTitle("Delete Student");
		
		con=CrudOperation.createConnection();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStudentid = new JLabel("Studentid");
		lblStudentid.setBounds(500, 300, 100, 20);
		contentPane.add(lblStudentid);
		
		txtid = new JTextField();
		txtid.setBounds(600, 300, 200, 20);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(600, 400, 200, 50);
		contentPane.add(btnDelete);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Krati Rastogi\\Pictures\\Saved Pictures\\Close-2-icon.png"));
		label.setBounds(47, 28, 357, 397);
		contentPane.add(label);
		btnDelete.addActionListener(this);
		
		JButton btnback = new JButton("Back");
		btnback.setBounds(1000, 50, 120, 23);
		btnback.addActionListener(this);
		contentPane.add(btnback);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
String caption=e.getActionCommand();
		if(caption.equals("Back"))
		{
			AdminFrame a=new AdminFrame();
			a.setVisible(true);
			this.dispose();
		}	
		else
		{
	
	
	
	 String stdid=txtid.getText();
			String strdelete="delete from student where studentid=?";
			
			try
			{
				pss=con.prepareStatement(strdelete);
			pss.setString(1, stdid);
			int rw=pss.executeUpdate();
			if(rw>0)
			{
				int option=JOptionPane.showConfirmDialog(this, "are u sure");
		
				if(option==0)
				{ 
			
				JOptionPane.showMessageDialog(this, "deleted succesfully");
			}
			}
				else
				{
					JOptionPane.showMessageDialog(this, "id not found");
				}
		}
			catch(SQLException se)
			{System.out.print(se);
		}
}
	
		}
	
}

