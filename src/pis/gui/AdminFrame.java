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
import javax.swing.SwingConstants;
import java.awt.Color;

public class AdminFrame extends JFrame implements ActionListener{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFrame frame = new AdminFrame();
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
	public AdminFrame() {
		setBackground(new Color(240, 248, 255));
		setTitle("Admin Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddStudent = new JButton("Add student");
		btnAddStudent.setBounds(450, 65, 220, 50);
		btnAddStudent.addActionListener(this);
		contentPane.add(btnAddStudent);
		
		JButton btnlogout = new JButton("LOG OUT");
		btnlogout.setBounds(1000, 50, 150, 40);
		btnlogout.addActionListener(this);
		contentPane.add(btnlogout);
		
		JButton btnupdate = new JButton("UpdateStudent");
		btnupdate.setBounds(450, 300, 220, 50);
		contentPane.add(btnupdate);
		btnupdate.addActionListener(this);
		
		JButton btndel = new JButton("DeleteStudent");
		btndel.setBounds(450, 535, 220, 50);
		contentPane.add(btndel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Krati Rastogi\\Pictures\\Saved Pictures\\user-add-icon.png"));
		label.setBounds(55, 44, 181, 114);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("C:\\Users\\Krati Rastogi\\Pictures\\Saved Pictures\\Actions-document-edit-icon.png"));
		label_1.setBounds(71, 226, 231, 177);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon("C:\\Users\\Krati Rastogi\\Pictures\\Saved Pictures\\Actions-window-close-icon.png"));
		label_2.setBounds(71, 426, 223, 208);
		contentPane.add(label_2);
		btndel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String caption=e.getActionCommand();
				if(caption.equals("Add student"))
				{
					Student s=new Student();
					s.setVisible(true);
					this.dispose();
				}
				
				if(caption.equals("UpdateStudent"))
				{
					UpdateStudent u=new UpdateStudent();
					u.setVisible(true);
					this.dispose();
				}
				if(caption.equals("DeleteStudent"))
				{
					DeleteStudent u=new DeleteStudent();
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
