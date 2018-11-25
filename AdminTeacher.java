import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JInternalFrame;
import java.awt.SystemColor;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class AdminTeacher {

	private JFrame frame;
	private JTextField teaid;
	private JTextField teanam;
	private JTextField teajoin;
	private JTextField teasal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminTeacher window = new AdminTeacher();
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
	public AdminTeacher() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	Connection Con = null;
	private JTable table;
	private JTextField tsalid;
	private JTextField tpdate;
	private JTextField tsama;
	private JTable table_1;
	private String depna;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private void initialize() {
		Con = Connect.dbConnector();
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 102, 102));
		frame.setBounds(100, 100, 1100, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTeachersInformation = new JLabel("Admin Teachers Information ");
		lblTeachersInformation.setForeground(new Color(204, 255, 0));
		lblTeachersInformation.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 22));
		lblTeachersInformation.setBounds(441, 11, 332, 36);
		frame.getContentPane().add(lblTeachersInformation);
	
	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(569, 197, 505, 473);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnRefreshTable = new JButton("Refresh Table");
		btnRefreshTable.setBackground(new Color(0, 0, 0));
		btnRefreshTable.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRefreshTable.setForeground(new Color(204, 0, 0));
		btnRefreshTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					String tearef = "select Tea_id,Tea_name,Joining_Date,Salary,Dep_name from Teacher left join Department on Teacher.Dp_name = Department.Dep_name";
					PreparedStatement trefp = Con.prepareStatement(tearef);
					
					ResultSet tefrs = trefp.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(tefrs));
					
				}catch(Exception tetb)
				{
					JOptionPane.showMessageDialog(null, tetb);
				}
				
			}
		});
		btnRefreshTable.setBounds(892, 130, 148, 45);
		frame.getContentPane().add(btnRefreshTable);
		
		
		
		JButton btnSalary = new JButton("Pay Salary");
		btnSalary.setBackground(new Color(153, 102, 153));
		btnSalary.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSalary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalFrame internalFrame_1 = new JInternalFrame("Teacher Salary Information");
				internalFrame_1.setBackground(new Color(135, 206, 235));
				internalFrame_1.setClosable(true);
				internalFrame_1.setBounds(10, 197, 549, 418);
				frame.getContentPane().add(internalFrame_1);
				internalFrame_1.getContentPane().setLayout(null);
				
				JLabel lblTeachersSalaryTable = new JLabel("Teacher's Salary Table");
				lblTeachersSalaryTable.setForeground(new Color(153, 50, 204));
				lblTeachersSalaryTable.setFont(new Font("Microsoft YaHei", Font.BOLD, 20));
				lblTeachersSalaryTable.setBounds(178, 11, 232, 28);
				internalFrame_1.getContentPane().add(lblTeachersSalaryTable);
				
				JLabel lblNewLabel_2 = new JLabel("Teacher ID");
				lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNewLabel_2.setBounds(10, 76, 75, 22);
				internalFrame_1.getContentPane().add(lblNewLabel_2);
				
				JLabel lblPayementDate = new JLabel("Payement Date");
				lblPayementDate.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblPayementDate.setBounds(10, 109, 99, 22);
				internalFrame_1.getContentPane().add(lblPayementDate);
				
				JLabel lblAmount = new JLabel("Amount");
				lblAmount.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblAmount.setBounds(10, 142, 58, 22);
				internalFrame_1.getContentPane().add(lblAmount);
				
				tsalid = new JTextField();
				tsalid.setBounds(118, 78, 99, 20);
				internalFrame_1.getContentPane().add(tsalid);
				tsalid.setColumns(10);
				
				tpdate = new JTextField();
				tpdate.setBounds(119, 111, 98, 22);
				internalFrame_1.getContentPane().add(tpdate);
				tpdate.setColumns(10);
				
				tsama = new JTextField();
				tsama.setBounds(118, 144, 99, 20);
				internalFrame_1.getContentPane().add(tsama);
				tsama.setColumns(10);
				
				JButton btnNewButton = new JButton("Pay");
				btnNewButton.setForeground(new Color(0, 0, 102));
				btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try
						{
							String salr = "insert into TeacherSalary(teacher_id,Trans_date,Amount) values (?,?,?) ";
							PreparedStatement sapst = Con.prepareStatement(salr);
							sapst.setString(1, tsalid.getText());
							sapst.setString(2, tpdate.getText());
							sapst.setString(3, tsama.getText());
							
							String ic = "select Tea_id from Teacher where Tea_id = ?";
							PreparedStatement it = Con.prepareStatement(ic);
							
							it.setString(1, tsalid.getText());
							ResultSet is  = it.executeQuery();
							
							String sa = tsama.getText();
							int tami = Integer.parseInt(sa);
							
							if(is.next())
							{
								if(tami==25000)
								{

									sapst.execute();
									
									JOptionPane.showMessageDialog(null, "Payment Complete");
									
									sapst.close();
								}
								else
								{
									JOptionPane.showMessageDialog(null, "You Must Pay Fixed Salary 25000TK");
								}
							}
							else
							{
								JOptionPane.showInternalMessageDialog(null, "Invalid Teacher ID");
							}
							
						}catch(Exception sae)
						{
							JOptionPane.showMessageDialog(null, "You Must Provide Valid Teacher ID");
						}
					}
				
					
				});
				btnNewButton.setBounds(71, 173, 99, 28);
				internalFrame_1.getContentPane().add(btnNewButton);
				
				JButton btnAlready = new JButton("Already Paid");
				btnAlready.setBackground(new Color(0, 0, 0));
				btnAlready.setForeground(new Color(255, 51, 0));
				btnAlready.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnAlready.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try
						{
							String tbls = "select Tea_name,Trans_num,Amount,Trans_date from TeacherSalary left join Teacher on TeacherSalary.teacher_id = Teacher.Tea_id ";
							PreparedStatement tbps = Con.prepareStatement(tbls);
							
							ResultSet tbrs = tbps.executeQuery();
							
							table_1.setModel(DbUtils.resultSetToTableModel(tbrs));
							tbrs.close();
							
						}catch(Exception tbx)
						{
							JOptionPane.showMessageDialog(null, tbx);
						}
						
					}
				});
				
				btnAlready.setBounds(368, 197, 155, 28);
				internalFrame_1.getContentPane().add(btnAlready);
				
				
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(35, 236, 488, 141);
				internalFrame_1.getContentPane().add(scrollPane_1);
				
				table_1 = new JTable();
				scrollPane_1.setViewportView(table_1);
				internalFrame_1.setVisible(true);
			}	
		});
		btnSalary.setBounds(166, 15, 123, 32);
		frame.getContentPane().add(btnSalary);
		
		JButton btnAddTeacher = new JButton("Add Teacher");
		btnAddTeacher.setBackground(new Color(153, 102, 153));
		btnAddTeacher.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAddTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalFrame internalFrame = new JInternalFrame("Teacher Information");
				internalFrame.setResizable(true);
				internalFrame.setClosable(true);
				internalFrame.getContentPane().setBackground(SystemColor.activeCaption);
				internalFrame.setBounds(22, 204, 534, 466);
				frame.getContentPane().add(internalFrame);
				internalFrame.getContentPane().setLayout(null);
				
				JLabel lblNewLabel = new JLabel("Teacher ID");
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNewLabel.setBounds(23, 105, 73, 26);
				internalFrame.getContentPane().add(lblNewLabel);
				
				JLabel lblTeacherName = new JLabel("Teacher Name");
				lblTeacherName.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblTeacherName.setBounds(23, 162, 84, 26);
				internalFrame.getContentPane().add(lblTeacherName);
				
				JLabel lblJoiningDate = new JLabel("Joining Date");
				lblJoiningDate.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblJoiningDate.setBounds(23, 266, 84, 26);
				internalFrame.getContentPane().add(lblJoiningDate);
				
				JLabel lblSalary = new JLabel("Salary");
				lblSalary.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblSalary.setBounds(28, 331, 55, 26);
				internalFrame.getContentPane().add(lblSalary);
				
				JLabel lblDepartment = new JLabel("Department");
				lblDepartment.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblDepartment.setBounds(23, 213, 84, 26);
				internalFrame.getContentPane().add(lblDepartment);
				
				teaid = new JTextField();
				teaid.setBounds(110, 109, 124, 26);
				internalFrame.getContentPane().add(teaid);
				teaid.setColumns(10);
				
				teanam = new JTextField();
				teanam.setBounds(110, 166, 124, 26);
				internalFrame.getContentPane().add(teanam);
				teanam.setColumns(10);
				
				teajoin = new JTextField();
				teajoin.setToolTipText("YY/MM/DD");
				teajoin.setBounds(117, 269, 117, 22);
				internalFrame.getContentPane().add(teajoin);
				teajoin.setColumns(10);
				
				teasal = new JTextField();
				teasal.setBounds(118, 334, 116, 22);
				internalFrame.getContentPane().add(teasal);
				teasal.setColumns(10);
				

				JRadioButton rdbtnScience = new JRadioButton("SCIENCE");
				rdbtnScience.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						depna = "SCIENCE";
					}
				});
				buttonGroup.add(rdbtnScience);
				rdbtnScience.setForeground(new Color(128, 0, 128));
				rdbtnScience.setFont(new Font("Tahoma", Font.PLAIN, 12));
				rdbtnScience.setBackground(SystemColor.activeCaption);
				rdbtnScience.setBounds(113, 216, 84, 22);
				internalFrame.getContentPane().add(rdbtnScience);
				
				JRadioButton rdbtnArts = new JRadioButton("ARTS");
				rdbtnArts.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						depna = "ARTS";
					}
				});
				buttonGroup.add(rdbtnArts);
				rdbtnArts.setForeground(new Color(138, 43, 226));
				rdbtnArts.setFont(new Font("Tahoma", Font.PLAIN, 12));
				rdbtnArts.setBackground(SystemColor.activeCaption);
				rdbtnArts.setBounds(199, 214, 73, 26);
				internalFrame.getContentPane().add(rdbtnArts);
				
				JRadioButton rdbtnNewRadioButton = new JRadioButton("COMMERCE");
				rdbtnNewRadioButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						depna = "COMMERCE";
					}
				});
				buttonGroup.add(rdbtnNewRadioButton);
				rdbtnNewRadioButton.setForeground(new Color(128, 0, 128));
				rdbtnNewRadioButton.setBackground(SystemColor.activeCaption);
				rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
				rdbtnNewRadioButton.setBounds(272, 215, 109, 23);
				internalFrame.getContentPane().add(rdbtnNewRadioButton);
				
				
				JLabel lblNewLabel_1 = new JLabel("*Provide ID only for Update and Delete Purpose");
				lblNewLabel_1.setForeground(new Color(165, 42, 42));
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
				lblNewLabel_1.setBounds(244, 110, 219, 19);
				internalFrame.getContentPane().add(lblNewLabel_1);
				internalFrame.setVisible(true);
				
				JButton btnUpdate = new JButton("Update");
				btnUpdate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try
						{
							String teup = "update Teacher set Tea_name =' "+teanam.getText()+" ', Joining_Date = ' "+teajoin.getText()+" ', Salary = ' "+teasal.getText()+" ', Dp_name = ' "+depna+" ' where Tea_id = ' "+teaid.getText()+" ' ";
							
							PreparedStatement upts = Con.prepareStatement(teup);
							
							upts.execute();
							
							JOptionPane.showMessageDialog(null, "Data Updated");
							upts.close();
						}catch(Exception e4)
						{
							JOptionPane.showMessageDialog(null, e4);
						}
						
					}
				});
				btnUpdate.setBounds(389, 246, 89, 23);
				internalFrame.getContentPane().add(btnUpdate);
				
				JButton btnDelete = new JButton("Delete");
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int action=JOptionPane.showConfirmDialog(null, "Do you really wanted to delete?","Deleted",JOptionPane.YES_NO_OPTION);
						if(action==0)
						{
						try
						{
							String tead = "delete from Teacher where Tea_id= ' "+teaid.getText()+" ' ";
							PreparedStatement dtpst = Con.prepareStatement(tead);
							
							dtpst.execute();
							JOptionPane.showMessageDialog(null, "Data Deleted");
							
							dtpst.close();
							
						}catch(Exception dtp)
						{
							JOptionPane.showMessageDialog(null, dtp);
						}
					}
					}
				});
				btnDelete.setBounds(389, 302, 89, 23);
				internalFrame.getContentPane().add(btnDelete);
			
				
				
				JButton btnSave = new JButton("Save");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try
						{
							String tsqre = "insert into Teacher(Tea_name,Joining_Date,Salary,Dp_name) values(?,?,?,?) ";
							PreparedStatement tspst = Con.prepareStatement(tsqre);
							
							tspst.setString(1, teanam.getText());
							tspst.setString(2, teajoin.getText());
							tspst.setString(3, teasal.getText());
							tspst.setString(4, depna);
							
							String atas = teanam.getText();
							
							if(atas.equals(null)||atas.equals(""))
							{
								JOptionPane.showInternalMessageDialog(null, "You Must Provide Values!!");
							}
							else
							{
								    tspst.execute();
									
									JOptionPane.showMessageDialog(null, "Data Saved");
									
									tspst.close();
							}
							
						
							
						}catch(Exception tsts)
						{
							JOptionPane.showMessageDialog(null, "You Must Provide Values!!");
						}
						
						
					}
				});
				btnSave.setBounds(389, 355, 89, 23);
				internalFrame.getContentPane().add(btnSave);
				
			}
		});
		btnAddTeacher.setBounds(27, 16, 117, 32);
		frame.getContentPane().add(btnAddTeacher);
		
		JButton btnNewButton_1 = new JButton("Home");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminHome ah = new AdminHome();
				ah.admain(null);
				frame.dispose();
			}
		});
		btnNewButton_1.setForeground(new Color(255, 51, 51));
		btnNewButton_1.setFont(new Font("Century", Font.BOLD, 12));
		btnNewButton_1.setBackground(new Color(0, 0, 0));
		btnNewButton_1.setBounds(937, 24, 100, 30);
		frame.getContentPane().add(btnNewButton_1);
		
		

		
		
	}
}
