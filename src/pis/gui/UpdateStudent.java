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
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;

public class UpdateStudent extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtid;
	private JTextField txtname;
	private JTextField txtadd;
	private JTextField txtphno;
	private JTextField txtemailid;
	private JTextField txtcid;
	private JTextField txtpid;
	private PreparedStatement ps;
	private ResultSet rs;
	private Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateStudent frame = new UpdateStudent();
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
	public UpdateStudent() {
		setTitle("Update Student");
		setBackground(new Color(255, 218, 185));
		con=CrudOperation.createConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 499);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 218, 185));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblStudentid = new JLabel("studentid");
		lblStudentid.setBounds(500, 100, 60, 14);
		contentPane.add(lblStudentid);
		
		JLabel lblName = new JLabel("name");
		lblName.setBounds(500, 150, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblAddress = new JLabel("address");
		lblAddress.setBounds(500, 200, 60, 14);
		contentPane.add(lblAddress);
		
		JLabel lblPhno = new JLabel("phno");
		lblPhno.setBounds(500, 250, 46, 14);
		contentPane.add(lblPhno);
		
		JLabel lblEmailid = new JLabel("emailid");
		lblEmailid.setBounds(500, 300, 46, 14);
		contentPane.add(lblEmailid);
		
		JLabel lblCid = new JLabel("cid");
		lblCid.setBounds(500, 350, 46, 14);
		contentPane.add(lblCid);
		
		JLabel lblPid = new JLabel("pid");
		lblPid.setBounds(500, 400, 46, 14);
		contentPane.add(lblPid);
		
		JButton btnShowDetails = new JButton("show details");
		btnShowDetails.setBounds(550, 500, 120, 23);
		btnShowDetails.addActionListener(this);
		contentPane.add(btnShowDetails);
		
		JButton btnUpdate = new JButton("update");
		btnUpdate.setBounds(750, 500, 89, 23);
		btnUpdate.addActionListener(this);
		contentPane.add(btnUpdate);
		
		JButton btnback = new JButton("Back");
		btnback.setBounds(1000, 50, 120, 23);
		btnback.addActionListener(this);
		contentPane.add(btnback);
		
		txtid = new JTextField();
		txtid.setBounds(600, 100, 100, 20);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		txtname = new JTextField();
		txtname.setBounds(600, 150, 100, 20);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		txtadd = new JTextField();
		txtadd.setBounds(600, 200, 100, 20);
		contentPane.add(txtadd);
		txtadd.setColumns(10);
		
		txtphno = new JTextField();
		txtphno.setBounds(600, 250, 100, 20);
		contentPane.add(txtphno);
		txtphno.setColumns(10);
		
		txtemailid = new JTextField();
		txtemailid.setBounds(600, 300, 100, 20);
		contentPane.add(txtemailid);
		txtemailid.setColumns(10);
		
		txtcid = new JTextField();
		txtcid.setBounds(600, 350, 100, 20);
		contentPane.add(txtcid);
		txtcid.setColumns(10);
		
		txtpid = new JTextField();
		txtpid.setBounds(600, 400, 100, 20);
		contentPane.add(txtpid);
		txtpid.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Krati Rastogi\\Pictures\\Saved Pictures\\Text-Edit-icon.png"));
		label.setBounds(108, 53, 343, 317);
		contentPane.add(label);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	String id=txtid.getText();
	String caption=e.getActionCommand();
	if(caption.equals("show details"))
	{
		String strsql="select name,address,phno,emailid,cid,pid from student where studentid=?";
		try
		{
			ps=con.prepareStatement(strsql);
			ps.setString(1, id);
			rs=ps.executeQuery();
			if(rs.next())
			{
				String nm=rs.getString("name");
				txtname.setText(nm);
				String add=rs.getString("address");
				txtadd.setText(add);
				String pn=rs.getString("phno");
				txtphno.setText(pn);
				String eid=rs.getString("emailid");
				txtemailid.setText(eid);
				String cid=rs.getString("cid");
				txtcid.setText(cid);
				String pid=rs.getString("pid");
				txtpid.setText(pid);
			}
			else
			{ JOptionPane.showMessageDialog(this, "no such id");
	
}
		}
		catch(SQLException s)
		{System.out.println(s);
}
}
	if(caption.equals("update"))
	{
		String strupdate="update student set name=?,address=?,phno=?,emailid=?,cid=?,pid=? where studentid=?";
		String name=txtname.getText();
		String address=txtadd.getText();
		String phno=txtphno.getText();
		String eid=txtemailid.getText();
		String cid=txtcid.getText();
		String pid=txtpid.getText();
		
		try {
			ps=con.prepareStatement(strupdate);
			ps.setString(1, name);
			ps.setString(2,address);
			ps.setString(3, phno);
			ps.setString(4, eid);
			ps.setString(5, cid);
			ps.setString(6, pid);
			ps.setString(7, id);
			int rw=ps.executeUpdate();
			if(rw>0)
			{
				JOptionPane.showMessageDialog(this, "updated");
				
			}
			
			
			else
			{
				JOptionPane.showMessageDialog(this, "no such id");
			}
			txtid.setText("");
			txtname.setText("");
			txtadd.setText("");
			txtphno.setText("");
			txtemailid.setText("");
			txtcid.setText("");
			txtpid.setText("");
			
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}
	
	if(caption.equals("Back"))
	{
		AdminFrame a=new AdminFrame();
		a.setVisible(true);
		this.dispose();
	}
		
	}
}
