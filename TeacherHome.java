import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

public class TeacherHome {

	private JFrame frame;
	private JTextField tid;
	private JTable table;
	private JLabel lblClassRoutin;
	private JTextField tcr;
	private JTextField search;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherHome window = new TeacherHome();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TeacherHome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	Connection con = null;
	private JTextField tnam;
	private JScrollPane scrollPane;
	private JLabel lblForCheckingSalary;
	private JLabel lblTeacherName;
	private JLabel lblJustInputThe;
	private JLabel lblToViewYou;
	private JButton btnNewButton;
	private void initialize() {
		con = Connect.dbConnector();
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 204, 204));
		frame.setBounds(100, 100, 1100, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTeacherHome = new JLabel("Teacher Home");
		lblTeacherHome.setForeground(new Color(102, 51, 204));
		lblTeacherHome.setFont(new Font("Segoe UI Semibold", Font.BOLD | Font.ITALIC, 23));
		lblTeacherHome.setBounds(428, 7, 173, 44);
		frame.getContentPane().add(lblTeacherHome);
		
		JLabel lblTeacherId = new JLabel("Teacher ID");
		lblTeacherId.setForeground(new Color(0, 51, 102));
		lblTeacherId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTeacherId.setBounds(720, 129, 108, 27);
		frame.getContentPane().add(lblTeacherId);
		
		
		JComboBox comboBoxSec = new JComboBox();
		comboBoxSec.setBackground(new Color(0, 0, 0));
		comboBoxSec.setForeground(new Color(204, 0, 0));
		comboBoxSec.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBoxSec.setBounds(685, 352, 98, 27);
		comboBoxSec.setModel(new DefaultComboBoxModel(new String[] {"S_name","S_id","Class","Section","S_Dept"}));
		frame.getContentPane().add(comboBoxSec);
		
		search = new JTextField();
		search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0)
			{
				try
				{
					String selt = (String)comboBoxSec.getSelectedItem();
					String sec = "select S_name,S_id,Class,Class_Roll,Section,Dep_name from StudentInfo left join Department on StudentInfo.S_Dept = Department.Dep_name where "+selt+"=? ";
			        
					PreparedStatement spct = con.prepareStatement(sec);
					spct.setString(1, search.getText());
					
					ResultSet ssr = spct.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(ssr));
					
					spct.close();
					
				}catch(Exception sht)
				{
					JOptionPane.showMessageDialog(null, sht);
				}
				
			}
			});
		search.setBounds(797, 353, 146, 26);
		frame.getContentPane().add(search);
		search.setColumns(10);
		
		
		
		
		tid = new JTextField();
		tid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				try
				{
					String tsh = "select Tea_id,Tea_name,Joining_Date,Salary from Teacher where Tea_id=?";
					PreparedStatement tps = con.prepareStatement(tsh);
					
					tps.setString(1, tid.getText());
					
					ResultSet rrs = tps.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rrs));
					rrs.close();
				}catch(Exception e24)
				{
					JOptionPane.showMessageDialog(null, e24);
				}
				
			}
		});
		tid.setBounds(838, 133, 132, 20);
		frame.getContentPane().add(tid);
		tid.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 463, 948, 207);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		lblClassRoutin = new JLabel("Class Routin");
		lblClassRoutin.setForeground(new Color(0, 51, 102));
		lblClassRoutin.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblClassRoutin.setBounds(720, 204, 88, 27);
		frame.getContentPane().add(lblClassRoutin);
		
		tcr = new JTextField();
		tcr.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				try
				{
					String rutin = "select Co_name,Room_num,Class_time,Section_nam from Course inner join Room on Course.Co_name = Room.Course_name where class_id = ' "+tcr.getText()+" '  ";
					PreparedStatement rups = con.prepareStatement(rutin);
					
					ResultSet rurs = rups.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rurs));
					
					
				}catch(Exception e25)
				{
					JOptionPane.showMessageDialog(null, e25);
				}
				
			}
		});
		tcr.setBounds(838, 207, 132, 23);
		frame.getContentPane().add(tcr);
		tcr.setColumns(10);
		
		JButton btnSalaryStatus = new JButton("Salary Status");
		btnSalaryStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalaryStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					String sal = "select Tea_id,Tea_name,Amount,Trans_date,Trans_num from Teacher left join TeacherSalary  on Teacher.Tea_id = TeacherSalary.teacher_id where Tea_name = ?  ";
					PreparedStatement slv = con.prepareStatement(sal);
					slv.setString(1, tnam.getText());
					
					ResultSet srs = slv.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(srs));
					
				}catch(Exception e26)
				{
					JOptionPane.showMessageDialog(null, e26);
				}
	
				
			}
		});
		btnSalaryStatus.setBounds(940, 297, 116, 27);
		frame.getContentPane().add(btnSalaryStatus);
		
		tnam = new JTextField();
		
		tnam.setBounds(779, 297, 150, 27);
		frame.getContentPane().add(tnam);
		tnam.setColumns(10);
		
		lblForCheckingSalary = new JLabel("For Checking Salary Just input your Name and Press the Button");
		lblForCheckingSalary.setForeground(new Color(204, 255, 102));
		lblForCheckingSalary.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblForCheckingSalary.setBounds(671, 241, 413, 34);
		frame.getContentPane().add(lblForCheckingSalary);
		
		lblTeacherName = new JLabel("Teacher Name");
		lblTeacherName.setForeground(new Color(204, 102, 153));
		lblTeacherName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTeacherName.setBounds(667, 299, 114, 21);
		frame.getContentPane().add(lblTeacherName);
		
		lblJustInputThe = new JLabel("Just Input the Class ID for Class Routin");
		lblJustInputThe.setForeground(new Color(102, 51, 102));
		lblJustInputThe.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblJustInputThe.setBounds(720, 167, 308, 20);
		frame.getContentPane().add(lblJustInputThe);
		
		lblToViewYou = new JLabel("To view you Information Just Provide Your Teacher ID");
		lblToViewYou.setForeground(new Color(0, 0, 51));
		lblToViewYou.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblToViewYou.setBounds(720, 82, 360, 34);
		frame.getContentPane().add(lblToViewYou);
		
		JLabel lblNewLabel = new JLabel("Tpic");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Salauddin\\Downloads\\Pro_Photos\\Participation-Penalizes-Quiet-Learners-Making-the-Case-for-Standards-Based-Grading_SOURCE_stocksy_641x328.jpg"));
		lblNewLabel.setBounds(10, 95, 641, 328);
		frame.getContentPane().add(lblNewLabel);
		
		btnNewButton = new JButton("Log Out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginHome lh = new LoginHome();
				lh.main(null);
				frame.dispose();
			}
		});
		btnNewButton.setForeground(new Color(255, 51, 51));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setBounds(955, 24, 100, 30);
		frame.getContentPane().add(btnNewButton);
	}
}
