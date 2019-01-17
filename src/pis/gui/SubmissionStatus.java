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
import javax.swing.ImageIcon;
import java.awt.Color;

public class SubmissionStatus extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtstdid;
	private JTextField txtcid;
	private JTextField txtpid;
	private Connection con;
	private ResultSet rs;
	private PreparedStatement ps ;
	private JRadioButton rdbtnNo,rdbtnYes;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubmissionStatus frame = new SubmissionStatus();
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
	public SubmissionStatus() {
		setTitle("Submission Status");
		setBackground(new Color(176, 196, 222));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 365);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 196, 222));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblstdid = new JLabel("studentid");
		lblstdid.setBounds(500, 100, 59, 14);
		contentPane.add(lblstdid);
		
		txtstdid = new JTextField();
		txtstdid.setBounds(600, 100, 100, 20);
		contentPane.add(txtstdid);
		txtstdid.setColumns(10);
		
		JLabel lblCid = new JLabel("cid");
		lblCid.setBounds(500, 300, 46, 14);
		contentPane.add(lblCid);
		
		txtcid = new JTextField();
		txtcid.setBounds(600, 300, 100, 20);
		contentPane.add(txtcid);
		txtcid.setColumns(10);
		
		JLabel lblpid = new JLabel("pid");
		lblpid.setBounds(500, 400, 46, 14);
		contentPane.add(lblpid);
		
		txtpid = new JTextField();
		txtpid.setBounds(600, 400, 100, 20);
		contentPane.add(txtpid);
		txtpid.setColumns(10);
		
		JLabel lblSubmission = new JLabel("Submission");
		lblSubmission.setBounds(500, 500, 69, 14);
		contentPane.add(lblSubmission);
	
		
		rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.setBounds(600, 500, 59, 23);
		contentPane.add(rdbtnYes);
		
		
		rdbtnNo = new JRadioButton("No");
		rdbtnNo.setBounds(700, 500, 59, 23);
		contentPane.add(rdbtnNo);
		
		ButtonGroup radio=new ButtonGroup();
		radio.add(rdbtnYes);
		radio.add(rdbtnNo);
		
		
		JButton btnUpdate = new JButton("update");
		btnUpdate.setBounds(600, 600, 100, 50);
		contentPane.add(btnUpdate);
		btnUpdate.addActionListener(this);
		
		JButton btnInfo = new JButton("info");
		btnInfo.setBounds(600, 200, 100, 50);
		contentPane.add(btnInfo);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Krati Rastogi\\Pictures\\Saved Pictures\\Distributor-report-icon.png"));
		lblNewLabel.setBounds(70, 50, 356, 279);
		contentPane.add(lblNewLabel);
		btnInfo.addActionListener(this);
		
		JButton btnback = new JButton("Back");
		btnback.setBounds(1000, 50, 120, 23);
		btnback.addActionListener(this);
		contentPane.add(btnback);
		con=CrudOperation.createConnection();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String stdid=txtstdid.getText();
		String caption=e.getActionCommand();
		
		if(caption.equals("Back"))
		{
			TrainerFrame a=new TrainerFrame();
			a.setVisible(true);
			this.dispose();
		}
		if(caption.equals("info"))
		{
			
		
		String strsql="select cid,pid from student where studentid=?";
		try
		{
		ps=con.prepareStatement(strsql);
		ps.setString(1, stdid);
		rs=ps.executeQuery();
		if(rs.next())
		{
			String cd=rs.getString("cid");
			txtcid.setText(cd);
			String pd=rs.getString("pid");
			txtpid.setText(pd);
			
		}
		else
		{JOptionPane.showMessageDialog(this, "invalid id");
		txtstdid.setText("");
		}
		}
		catch(SQLException se1)
		{
			System.out.print(se1);
		}
		
		
	}
		
		if(caption.equals("update"))
		{
			
		
			String strupdate="update student set submission=? where studentid=?";
            String btn="";
			
			if(rdbtnYes.isSelected())
			btn=rdbtnYes.getText();
			else
		    btn=rdbtnNo.getText();
			try
			{
		ps=con.prepareStatement(strupdate);
				
			    ps.setString(1, btn);
			    ps.setString(2,stdid);
			  
			int rw=ps.executeUpdate();
			if(rw>0)
			{
				JOptionPane.showMessageDialog(this, "data updated");
				txtstdid.setText("");
				txtcid.setText("");
				txtpid.setText("");
			}
			
			}
			
			catch(SQLException se2)
			{
				System.out.print(se2);
			}
				
		
		
		}

	}
}
