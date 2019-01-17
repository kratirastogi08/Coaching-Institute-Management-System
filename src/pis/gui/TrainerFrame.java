package pis.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Color;

public class TrainerFrame extends JFrame implements ActionListener{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrainerFrame frame = new TrainerFrame();
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
	public TrainerFrame() {
		setBackground(new Color(230, 230, 250));
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Trainer Frame");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAssignProject = new JButton("Assign Project");
		btnAssignProject.setBounds(500, 100, 200, 30);
		btnAssignProject.addActionListener(this);
		contentPane.add(btnAssignProject);
		
		JButton btnlogout = new JButton("LOG OUT");
		btnlogout.setBounds(1000, 50, 150, 40);
		btnlogout.addActionListener(this);
		contentPane.add(btnlogout);
		
		JButton btnSubmissionStatus = new JButton("Submission status");
		btnSubmissionStatus.setBounds(500, 300, 200, 30);
		btnSubmissionStatus.addActionListener(this);
		contentPane.add(btnSubmissionStatus);
		
		JButton btnShowReport = new JButton("Show Report");
		
		btnShowReport.setBounds(500, 500, 200, 30);
		contentPane.add(btnShowReport);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Krati Rastogi\\Pictures\\Saved Pictures\\Actions-project-open-icon.png"));
		label.setBounds(194, 29, 230, 159);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("C:\\Users\\Krati Rastogi\\Pictures\\Saved Pictures\\Status-mail-task-icon.png"));
		label_1.setBounds(186, 180, 238, 199);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon("C:\\Users\\Krati Rastogi\\Pictures\\Saved Pictures\\report-icon (1).png"));
		label_2.setBounds(186, 400, 216, 185);
		contentPane.add(label_2);
		btnShowReport.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String caption=e.getActionCommand();
		if(caption.equals("Assign Project"))
		{
			AssignProject a=new AssignProject();
			a.setVisible(true);
			this.dispose();
		}
		if(caption.equals("Submission status"))
		{
			SubmissionStatus s=new SubmissionStatus();
			s.setVisible(true);
			this.dispose();
		}
		if(caption.equals("Show Report"))
		{
			Report u=new Report();
			u.setVisible(true);
			this.dispose();
		}
		if(caption.equals("LOG OUT"))
		{
			Account u=new Account();
			u.setVisible(true);
			this.dispose();
		}
		
		// TODO Auto-generated method stub
		
	}
}
