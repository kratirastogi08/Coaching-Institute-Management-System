package pis.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import pis.dbutil.CrudOperation;

public class Report extends JFrame implements ActionListener {

	private JPanel contentPane;
	private Connection con;
	private PreparedStatement psdata,pscount;
	private ResultSet rsdata,rscount;

	
	String[]colNames={"name","phone","emailid","cid","pid","submission"};
	Object[][]data;
	JTable jt;
	JScrollPane jsp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Report frame = new Report();
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
	public Report() {
		setTitle("Report");
		 con=CrudOperation.createConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnback = new JButton("Back");
		btnback.setBounds(1100, 600, 120, 23);
		btnback.addActionListener(this);
		contentPane.add(btnback);
		
		
		fillData();
		
		jt=new JTable(new DefaultTableModel(data, colNames));
		jsp=new JScrollPane(jt);
		jsp.setBounds(30,90,300,300);
		contentPane.add(jsp);
		
		
	}

	
	public void fillData()
	{
		
		
		try{
			
			String strcount="select count(*) from student";
			
			pscount=con.prepareStatement(strcount);
			rscount=pscount.executeQuery();
				rscount.next();
				int cnt=rscount.getInt(1);
				data=new Object[cnt][6];
		String strdata="select * from student";
		psdata=con.prepareStatement(strdata);
		rsdata=psdata.executeQuery();
		int row=0;
			while(rsdata.next())
			{
				data[row][0]=rsdata.getString("name");
				data[row][1]=rsdata.getString("phno");
				data[row][2]=rsdata.getString("emailid");
				data[row][3]=rsdata.getString("cid");
				data[row][4]=rsdata.getString("pid");
				data[row][5]=rsdata.getString("submission");
				
				row++;
				
			}
		
		
		
	}
		catch(SQLException se)
		{
			System.out.println(se);
		}
	
	
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
		// TODO Auto-generated method stub
		
	}
}