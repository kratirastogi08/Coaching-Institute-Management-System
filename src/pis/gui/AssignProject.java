package pis.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pis.dbutil.CrudOperation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.SwingConstants;
import java.awt.Color;

public class AssignProject extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtstdid;
	private JTextField txtname;
	private JTextField txtcid;
	private JTextField txtpid;
	private Connection con;
	private PreparedStatement ps,pss;
	private ResultSet rs;
	private JDateChooser dateChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssignProject frame = new AssignProject();
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
	public AssignProject() {
		setTitle("Assign Project");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 425);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStudentid = new JLabel("Studentid");
		lblStudentid.setBounds(500, 100, 100, 20);
		contentPane.add(lblStudentid);
		
		txtstdid = new JTextField();
		txtstdid.setBounds(650, 100, 100, 20);
		contentPane.add(txtstdid);
		txtstdid.setColumns(10);
		
		JLabel lblName = new JLabel("name");
		lblName.setBounds(500, 200, 100, 20);
		contentPane.add(lblName);
		
		txtname = new JTextField();
		txtname.setBounds(650, 200, 100, 20);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblCid = new JLabel("cid");
		lblCid.setBounds(500, 300, 100, 20);
		contentPane.add(lblCid);
		
		txtcid = new JTextField();
		txtcid.setBounds(650, 300, 100, 20);
		contentPane.add(txtcid);
		txtcid.setColumns(10);
		
		JLabel lblPid = new JLabel("pid");
		lblPid.setBounds(500, 400, 100, 20);
		contentPane.add(lblPid);
		
		JLabel lblDateOfSubmission = new JLabel("date of submission");
		lblDateOfSubmission.setBounds(500, 500, 150, 20);
		contentPane.add(lblDateOfSubmission);
		 
		 JButton btnstudentinfo = new JButton("student info");
		 btnstudentinfo.setBounds(900, 150, 150, 50);
		 btnstudentinfo.addActionListener(this);
		 contentPane.add(btnstudentinfo);
		 
		 txtpid = new JTextField();
		 txtpid.setBounds(650, 400, 100, 20);
		 contentPane.add(txtpid);
		 txtpid.setColumns(10);
		 
		 JButton btnAssignProject = new JButton("Assign Project");
		 btnAssignProject.setBounds(900, 500, 150, 50);
		 btnAssignProject.addActionListener(this);
		 contentPane.add(btnAssignProject);
		
		 dateChooser = new JDateChooser();
		dateChooser.setBounds(650, 500, 100, 20);
		contentPane.add(dateChooser);
		
		JButton btnback = new JButton("Back");
		btnback.setBounds(1000, 50, 120, 23);
		btnback.addActionListener(this);
		contentPane.add(btnback);
		
		
		
		con=CrudOperation.createConnection();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String caption=e.getActionCommand();
	
		
		if(caption.equals("Back"))
		{
			TrainerFrame a=new TrainerFrame();
			a.setVisible(true);
			this.dispose();
		}
		if(caption.equals("student info"))
		{
		 String stdid=txtstdid.getText();
		String strsql="select name,cid from student where studentid=?";
		try {
			ps=con.prepareStatement(strsql);
			ps.setString(1, stdid);
			rs=ps.executeQuery();
			if(rs.next())
			{
				String nm=rs.getString("name");
				txtname.setText(nm);
				String courseid=rs.getString("cid");
				txtcid.setText(courseid);
				
			
			
			
			
			}
			else
			{
				JOptionPane.showMessageDialog(this, "invalid id");
				txtstdid.setText("");
			} 
		} catch (Exception e2) {
			System.out.println(e2);
			// TODO: handle exception
		}}
		
    
    
		if(caption.equals("Assign Project"))
		{
			
			String strupdate="update student set pid=?,date_of_submission=? where studentid=?";
			try {
				String sid=txtstdid.getText();
				String pid=txtpid.getText();
				
				if(sid.length()==0)
				{
					JOptionPane.showMessageDialog(this, "all fields should be filled "); 
				}
					else
					{
						if(pid.length()==0)
					
					{
						JOptionPane.showMessageDialog(this, "enter project id first ");  
					}
					else
					{    pss=con.prepareStatement(strupdate);
					pss.setString(1, pid);
					java.util.Date d=  dateChooser.getDate();
					java.sql.Date sd=new java.sql.Date(d.getTime());
					 Calendar mcurrentDate = Calendar.getInstance();
			         int mYear = mcurrentDate.get(Calendar.YEAR);
			         int mMonth = mcurrentDate.get(Calendar.MONTH);
			         int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
			         mcurrentDate.set(mYear, mMonth, mDay);
			         java.util.Date d1 = mcurrentDate.getTime();
			         java.sql.Date  dated = new java.sql.Date(d1.getTime());
			      String ds= dated.toString();
			     String f= ds.substring(8);
			     int q=Integer.parseInt(f);
			     String f2= ds.substring(5,7);
			     int q1=Integer.parseInt(f2);
			     String j= sd.toString();
			     String f1= j.substring(8);
			     int q2=Integer.parseInt(f1);
			     String f3= j.substring(5,7);
			     int q3=Integer.parseInt(f3);
			    System.out.println(q1+" "+q3);
			    
			     
			    	 if(q2>q && q3==q1)
					     {
					     
						pss.setDate(2, sd);
					     }
			    	 else
			    		 if( q3>q1)
			    		 {
			    			 pss.setDate(2, sd); 
			    		 }
						 else
						 {
							
							JOptionPane.showMessageDialog(this, "past date cannot be choosen "); 
							
						 }
						 
					}
					}
					
				
				
				
				
				
				pss.setString(3, sid);
				int rw=pss.executeUpdate();
				if(rw>0)
				{JOptionPane.showMessageDialog(this, "project assigned");
				txtstdid.setText("");
				txtname.setText("");
				txtcid.setText("");
				txtpid.setText("");
				
			} }
				catch (Exception e2) {
					System.out.println(e2);
				// TODO: handle exception
			}
		}
		// TODO Auto-generated method stub
		
	}
}
