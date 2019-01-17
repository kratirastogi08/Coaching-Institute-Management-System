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
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Student extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtstdid;
	private JTextField txtname;
	private JTextField txtaddress;
	private JTextField txtphno;
	private JTextField txtemailid;
	private JTextField txtpid;
	private JLabel lblSubmitted;
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	private JButton btnAddStudent,btnback;
	private Connection con;

	private PreparedStatement ps,pss;
	private JTextField txtdos;
	private JComboBox comboBox;
	private JLabel lblNewLabel;
	private ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student frame = new Student();
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
	public Student() {
		setTitle("Student Frame");
		setBackground(new Color(144, 238, 144));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 683);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(144, 238, 144));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblstdid = new JLabel("Studentid");
		lblstdid.setBounds(500, 100, 58, 14);
		contentPane.add(lblstdid);
		
		txtstdid = new JTextField();
		txtstdid.setBounds(600, 100, 100, 20);
		contentPane.add(txtstdid);
		txtstdid.setColumns(10);
		
		JLabel lblname = new JLabel("Name");
		lblname.setBounds(500, 150, 46, 14);
		contentPane.add(lblname);
		
		txtname = new JTextField();
		txtname.setBounds(600, 150, 100, 20);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(500, 200, 55, 14);
		contentPane.add(lblAddress);
		
		txtaddress = new JTextField();
		txtaddress.setBounds(600, 200, 100, 20);
		contentPane.add(txtaddress);
		txtaddress.setColumns(10);
		
		JLabel lblPhno = new JLabel("Phno");
		lblPhno.setBounds(500, 250, 46, 14);
		contentPane.add(lblPhno);
		
		txtphno = new JTextField();
		txtphno.setBounds(600, 250, 100, 20);
		contentPane.add(txtphno);
		txtphno.setColumns(10);
		
		JLabel lblEmailid = new JLabel("Emailid");
		lblEmailid.setBounds(500, 300, 46, 14);
		contentPane.add(lblEmailid);
		
		txtemailid = new JTextField();
		txtemailid.setBounds(600, 300, 100, 20);
		contentPane.add(txtemailid);
		txtemailid.setColumns(10);
		
		JLabel lblCid = new JLabel("cid");
		lblCid.setBounds(500, 350, 46, 14);
		contentPane.add(lblCid);
		
		JLabel lblPid = new JLabel("Pid");
		lblPid.setBounds(500, 400, 46, 14);
		contentPane.add(lblPid);
		
		txtpid = new JTextField();
		txtpid.setBounds(600,400 , 100, 20);
		contentPane.add(txtpid);
		txtpid.setColumns(10);
		txtpid.setEditable(false);
		
		lblSubmitted = new JLabel("Submitted");
		lblSubmitted.setBounds(500, 450, 69, 14);
		contentPane.add(lblSubmitted);
		
		rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.setBounds(600, 450, 69, 23);
		contentPane.add(rdbtnYes);
		rdbtnYes.setEnabled(false);;
		
		rdbtnNo = new JRadioButton("No");
		rdbtnNo.setBounds(650, 450, 69, 23);
		contentPane.add(rdbtnNo);
		
		 ButtonGroup radio=new ButtonGroup();
		radio.add(rdbtnYes);
		radio.add(rdbtnNo);
		rdbtnNo.setSelected(true);
		btnAddStudent = new JButton("Add Student");
		btnAddStudent.setBounds(600, 600, 114, 23);
		btnAddStudent.addActionListener(this);
		contentPane.add(btnAddStudent);
		
		JLabel lblDateOfSubmission = new JLabel("date of submission");
		lblDateOfSubmission.setBounds(500, 500, 108, 14);
		contentPane.add(lblDateOfSubmission);
		
		txtdos = new JTextField();
		txtdos.setBounds(625, 500, 100, 20);
		contentPane.add(txtdos);
		txtdos.setColumns(10);
		txtdos.setEditable(false);
		String[] arr={"core java","advanced java","android","python"};
		 comboBox = new JComboBox(arr);
		comboBox.setBounds(600, 350, 96, 20);
		comboBox.setEditable(true);
		contentPane.add(comboBox);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Krati Rastogi\\Pictures\\Saved Pictures\\Actions-contact-new-icon.png"));
		lblNewLabel.setBounds(64, 62, 329, 319);
		contentPane.add(lblNewLabel);
		
		btnback = new JButton("Back");
		btnback.setBounds(1000, 50, 100, 23);
		btnback.addActionListener(this);
		contentPane.add(btnback);
		
		con=CrudOperation.createConnection();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String caption=e.getActionCommand();
		if(caption.equals("Back"))
		{
			AdminFrame a=new AdminFrame();
			a.setVisible(true);
			this.dispose();
		}
		else{
		
		String stdid=txtstdid.getText();
		String name=txtname.getText();
		String address=txtaddress.getText();
		String phno=txtphno.getText();
		String emailid=txtemailid.getText();
		String cid=(String)comboBox.getSelectedItem();
		
		String rbtn=rdbtnNo.getText();
		
		
		if(stdid.isEmpty()||name.isEmpty()||address.isEmpty()||phno.isEmpty()||emailid.isEmpty()||cid.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "data required");
		}
		else
		{
		String strinsert="insert into student values(?,?,?,?,?,?,?,?,?)";
		try {
			ps=con.prepareStatement(strinsert);
			
			String strsql="select name from student where studentid=?";
			try
			{
				pss=con.prepareStatement(strsql);
				pss.setString(1, stdid);
				rs=pss.executeQuery();
				if(rs.next())
				{
					JOptionPane.showMessageDialog(this, "id already exist");
					txtstdid.setText("");
					txtname.setText("");
					txtaddress.setText("");
					txtphno.setText("");
					txtemailid.setText("");
					
					txtdos.setText("");
					
				}
				else
				{ 
					ps.setString(1, stdid);
	}
			}
			catch(SQLException s)
			{System.out.println(s);
	}
			
			
			
			
			
			
			ps.setString(2, name);
			ps.setString(3, address);
			int co=0;
			for(int i=0;i<phno.length();i++)
			{
				char c=phno.charAt(i);
				if(c=='0'||c=='1'||c=='2'||c=='3'||c=='4'||c=='5'||c=='6'||c=='7'||c=='8'||c=='9')
					co++;
			}
			if(co==10||phno.length()==10)
			{
			ps.setString(4, phno);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "invalid phone number");
			}
			if(emailid.contains("@"))
			{
			ps.setString(5, emailid);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "invalid email");
			}
			ps.setString(6, cid);
			ps.setString(7, "pending");
			ps.setString(8, rbtn);
			ps.setString(9, "pending");
			int rw=ps.executeUpdate();
			if(rw>0)
			{
				JOptionPane.showMessageDialog(this, "student successfully added");
				txtstdid.setText("");
				txtname.setText("");
				txtaddress.setText("");
				txtphno.setText("");
				txtemailid.setText("");
				
				
				
				
			}
			
		
			
		} catch (Exception e2) {
			System.out.println(e2);
		}
			// TODO: handle exception
		}
		
		
		// TODO Auto-generated method stub
		
	}
}
}
