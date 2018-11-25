import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

public class AdminHome {

	private JFrame frmAdminHome;

	/**
	 * Launch the application.
	 */
	public static void admain(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminHome window = new AdminHome();
					window.frmAdminHome.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminHome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdminHome = new JFrame();
		frmAdminHome.setTitle("Admin Home");
		frmAdminHome.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frmAdminHome.setBounds(100, 100, 1100, 720);
		frmAdminHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdminHome.getContentPane().setLayout(null);
		
		JButton btnAddNewStudent = new JButton("Add New Student");
		btnAddNewStudent.setForeground(new Color(255, 0, 0));
		btnAddNewStudent.setBackground(new Color(0, 0, 0));
		btnAddNewStudent.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAddNewStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminStudent ob2 = new AdminStudent();
				ob2.ADSmain(null);
				frmAdminHome.dispose();
			}
		});
		btnAddNewStudent.setBounds(718, 24, 150, 40);
		frmAdminHome.getContentPane().add(btnAddNewStudent);
		
		JButton btnNewButton = new JButton("Teacher Info");
		btnNewButton.setBackground(new Color(51, 153, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminTeacher ob3 = new AdminTeacher();
				ob3.main(null);
				frmAdminHome.dispose();
			}
		});
		btnNewButton.setBounds(907, 24, 150, 40);
		frmAdminHome.getContentPane().add(btnNewButton);
		
		JLabel lblAdminHome = new JLabel("Admin Home");
		lblAdminHome.setForeground(new Color(102, 0, 204));
		lblAdminHome.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblAdminHome.setBounds(389, 12, 258, 54);
		frmAdminHome.getContentPane().add(lblAdminHome);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Salauddin\\Downloads\\Pro_Photos\\720843_1084x574.jpg"));
		lblNewLabel.setBounds(0, 107, 1084, 574);
		frmAdminHome.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Log Out");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginHome lh = new LoginHome();
				lh.main(null);
				frmAdminHome.dispose();
			}
		});
		btnNewButton_1.setForeground(new Color(255, 51, 51));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.setBackground(new Color(0, 0, 0));
		btnNewButton_1.setBounds(10, 50, 100, 30);
		frmAdminHome.getContentPane().add(btnNewButton_1);
	}
}
