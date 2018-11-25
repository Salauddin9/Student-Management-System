import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

public class AdminStudent {

	private JFrame frame;
	private JTextField adstuname;
	
	

	/**
	 * Launch the application.
	 */
	public static void ADSmain(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminStudent window = new AdminStudent();
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
	public AdminStudent() {
		initialize();
	}
	
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	Connection con = null;
	private JTextField depclass;
	private JTextField deptsec;
	private JTextField deptc1;
	private JTextField deptc2;
	private JTextField deptc3;
	private JTextField deptc4;
	private JTextField adsid;
	private JTable table;
	private JTextField search;
	private JButton btnResultEntry;
	private JButton btnExamFees;
	private JTextField fsid;
	private JTextField fsnam;
	private JLabel lblPaymentDate;
	private JTextField fstym;
	private JLabel lblExamFees;
	private JLabel lblAmount;
	private JTextField fsama;
	private JButton btnConfrim;
	private JTextField rc1;
	private JTextField rc2;
	private JTextField rc3;
	private JTextField rc4;
	private JTextField rsid;
	private JTextField rcla;
	private JTextField mr1;
	private JTextField mr2;
	private JTextField mr3;
	private JTextField mr4;
	private String Dpnam;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnNewButton;
	private JTextField rclid;
	private JTextField rsec;
	private JTextField crs1;
	private JTextField crs2;
	private JTextField crs3;
	private JTextField crs4;
	private JTextField rm1;
	private JTextField rm2;
	private JTextField rm3;
	private JTextField rm4;
	private JTextField ctm1;
	private JTextField ctm2;
	private JTextField ctm3;
	private JTextField ctm4;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JTextField croll;
	private String jav;
	private String avg;
	
	
	
	private void initialize() {
		con = Connect.dbConnector();
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(102, 102, 153));
		frame.setBounds(100, 100, 1100, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAdminStudent = new JLabel("Admin Student Home ");
		lblAdminStudent.setForeground(new Color(0, 255, 51));
		lblAdminStudent.setFont(new Font("Microsoft JhengHei", Font.BOLD, 23));
		lblAdminStudent.setBounds(447, 11, 252, 30);
		frame.getContentPane().add(lblAdminStudent);
		
		JButton btnShowData = new JButton("Refresh Table");
		btnShowData.setForeground(new Color(153, 0, 0));
		btnShowData.setFont(new Font("Nirmala UI", Font.BOLD, 15));
		btnShowData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String showad = "select S_id,S_name,Dep_name,Class,Class_Roll,Section from StudentInfo inner join Department on StudentInfo.S_Dept = Department.Dep_name";
					PreparedStatement pshd = con.prepareStatement(showad);
					
					ResultSet adrs = pshd.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(adrs));
				}catch(Exception shed)
				{
					JOptionPane.showMessageDialog(null, shed);
				}
			}
		});
		btnShowData.setBounds(943, 159, 141, 41);
		frame.getContentPane().add(btnShowData);
		
		JScrollPane scrollPaneAd = new JScrollPane();
		scrollPaneAd.setBounds(562, 211, 512, 459);
		frame.getContentPane().add(scrollPaneAd);
		
		table = new JTable();
		scrollPaneAd.setViewportView(table);
		
		JComboBox comboBoxSec = new JComboBox();
		comboBoxSec.setForeground(new Color(255, 51, 0));
		comboBoxSec.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBoxSec.setBackground(new Color(0, 0, 0));
		comboBoxSec.setBounds(829, 103, 89, 20);
		comboBoxSec.setModel(new DefaultComboBoxModel(new String[] {"S_name","S_id","Class","Section","S_Dept"}));
		frame.getContentPane().add(comboBoxSec);
		
		search = new JTextField();
		search.setBackground(new Color(255, 255, 255));
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
		search.setBounds(943, 103, 141, 20);
		frame.getContentPane().add(search);
		search.setColumns(10);
		
		btnResultEntry = new JButton("Result Entry");
		btnResultEntry.setForeground(new Color(204, 51, 153));
		btnResultEntry.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnResultEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JInternalFrame internalFrame_rs = new JInternalFrame("Result Entry");
				internalFrame_rs.setEnabled(false);
				internalFrame_rs.setResizable(true);
				internalFrame_rs.setClosable(true);
				internalFrame_rs.getContentPane().setBackground(new Color(255, 102, 153));
				internalFrame_rs.setBounds(45, 217, 515, 453);
				frame.getContentPane().add(internalFrame_rs);
				internalFrame_rs.getContentPane().setLayout(null);
				
				JLabel lblResultSheet = new JLabel("Result Sheet");
				lblResultSheet.setForeground(new Color(255, 255, 0));
				lblResultSheet.setFont(new Font("Segoe UI Black", Font.BOLD, 17));
				lblResultSheet.setBounds(200, 22, 137, 24);
				internalFrame_rs.getContentPane().add(lblResultSheet);
				
				JLabel lblStudentId_2 = new JLabel("Student ID");
				lblStudentId_2.setForeground(new Color(0, 0, 0));
				lblStudentId_2.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblStudentId_2.setBounds(10, 114, 93, 24);
				internalFrame_rs.getContentPane().add(lblStudentId_2);
				
				JLabel lblClass_1 = new JLabel("Class");
				lblClass_1.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblClass_1.setBounds(10, 163, 57, 14);
				internalFrame_rs.getContentPane().add(lblClass_1);
				
				JLabel lblCourse_1 = new JLabel("Course 1:");
				lblCourse_1.setFont(new Font("Tahoma", Font.ITALIC, 12));
				lblCourse_1.setBounds(10, 252, 57, 24);
				internalFrame_rs.getContentPane().add(lblCourse_1);
				
				JLabel lblCourse_2 = new JLabel("Course 2:");
				lblCourse_2.setFont(new Font("Tahoma", Font.ITALIC, 12));
				lblCourse_2.setBounds(10, 287, 57, 24);
				internalFrame_rs.getContentPane().add(lblCourse_2);
				
				JLabel lblCourse_3 = new JLabel("Course 3:");
				lblCourse_3.setFont(new Font("Tahoma", Font.ITALIC, 12));
				lblCourse_3.setBounds(10, 322, 57, 14);
				internalFrame_rs.getContentPane().add(lblCourse_3);
				
				JLabel lblCourse_4 = new JLabel("Course 4:");
				lblCourse_4.setFont(new Font("Tahoma", Font.ITALIC, 12));
				lblCourse_4.setBounds(10, 355, 57, 14);
				internalFrame_rs.getContentPane().add(lblCourse_4);
				
				rc1 = new JTextField();
				rc1.setBounds(111, 255, 86, 20);
				internalFrame_rs.getContentPane().add(rc1);
				rc1.setColumns(10);
				
				rc2 = new JTextField();
				rc2.setBounds(111, 290, 86, 20);
				internalFrame_rs.getContentPane().add(rc2);
				rc2.setColumns(10);
				
				rc3 = new JTextField();
				rc3.setBounds(111, 320, 86, 20);
				internalFrame_rs.getContentPane().add(rc3);
				rc3.setColumns(10);
				
				rc4 = new JTextField();
				rc4.setBounds(111, 353, 86, 20);
				internalFrame_rs.getContentPane().add(rc4);
				rc4.setColumns(10);
				rcla.setBounds(111, 161, 86, 20);
				internalFrame_rs.getContentPane().add(rcla);
				rcla.setColumns(10);
				
				JLabel lblCourseName = new JLabel("Course Name");
				lblCourseName.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblCourseName.setBounds(111, 211, 86, 24);
				internalFrame_rs.getContentPane().add(lblCourseName);
				
				JLabel lblMarks = new JLabel("Marks");
				lblMarks.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblMarks.setBounds(234, 216, 46, 14);
				internalFrame_rs.getContentPane().add(lblMarks);
				
				mr1 = new JTextField();
				mr1.setBounds(219, 255, 86, 20);
				internalFrame_rs.getContentPane().add(mr1);
				mr1.setColumns(10);
				
				mr2 = new JTextField();
				mr2.setBounds(219, 290, 86, 20);
				internalFrame_rs.getContentPane().add(mr2);
				mr2.setColumns(10);
				
				mr3 = new JTextField();
				mr3.setBounds(219, 320, 86, 20);
				internalFrame_rs.getContentPane().add(mr3);
				mr3.setColumns(10);
				
				mr4 = new JTextField();
				mr4.setBounds(219, 353, 86, 20);
				internalFrame_rs.getContentPane().add(mr4);
				mr4.setColumns(10);
				rsid = new JTextField();
				rsid.setBounds(111, 117, 86, 20);
				internalFrame_rs.getContentPane().add(rsid);
				rsid.setColumns(10);
				
				
				JButton btnSave = new JButton("Save");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try
						{
							String rqe = "insert into Result(Student_id,Course1Marks,Course2Marks,Course3Marks,Course4Marks,Total_marks,GPA) values (?,?,?,?,?,?,?) ";
							PreparedStatement rpst = con.prepareStatement(rqe);
							
							rpst.setString(1, rsid.getText());
							rpst.setString(2, mr1.getText());
							rpst.setString(3, mr2.getText());
							rpst.setString(4, mr3.getText());
							rpst.setString(5, mr4.getText());							
						
							
							
							String m1 = mr1.getText();
							float mr1 = Float.parseFloat(m1);
							
							String m2 = mr2.getText();
							float mr2 = Float.parseFloat(m2);
							
							String m3 = mr3.getText();
							float mr3 = Float.parseFloat(m3);
							
							String m4 = mr4.getText();
							float mr4 = Float.parseFloat(m4);
							
							float ava = (mr1+mr2+mr3+mr4);							
							jav = String.valueOf(ava);
							rpst.setString(6, jav);
							
							
							float gva = ava/64;							
							avg = String.valueOf(gva);
							
							if(gva>=4.99)
							{
								rpst.setString(7, avg="5");
							}
							else
							{
								rpst.setString(7, avg);
							}
														
							
						
							rpst.execute();
							
							JOptionPane.showMessageDialog(null, "Data Saved");
							
							rpst.close();
						}catch(Exception e15)
						{
							JOptionPane.showMessageDialog(null, "You Must Provide Values");
						}
						
					}
				});
				btnSave.setBounds(380, 390, 89, 23);
				internalFrame_rs.getContentPane().add(btnSave);
				
				internalFrame_rs.setVisible(true);
				
			}
		});
		btnResultEntry.setBounds(179, 52, 112, 28);
		frame.getContentPane().add(btnResultEntry);
		
		btnExamFees = new JButton("Exam Fees");
		btnExamFees.setForeground(new Color(255, 51, 153));
		btnExamFees.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExamFees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
			
				JInternalFrame internalFrame_fees = new JInternalFrame("Exam Fees Payment");
				internalFrame_fees.getContentPane().setBackground(new Color(51, 102, 153));
				internalFrame_fees.setClosable(true);
				internalFrame_fees.setBounds(10, 104, 500, 410);
				frame.getContentPane().add(internalFrame_fees);
				internalFrame_fees.getContentPane().setLayout(null);
				
				fsid = new JTextField();
				fsid.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent arg0) {
						
						try
						{
							
							String fssn = "select S_name from StudentInfo where S_id=? ";
							PreparedStatement fspt = con.prepareStatement(fssn);
							
							fspt.setString(1, fsid.getText());
							ResultSet fsrs = fspt.executeQuery();
							
							while(fsrs.next())
							{
								fsnam.setText(fsrs.getString("S_name"));
								
							}
							
							fspt.close();
						}catch(Exception e10)
						{
							JOptionPane.showMessageDialog(null, e10);
						}
						
					}
				});
				fsid.setBounds(116, 80, 120, 24);
				internalFrame_fees.getContentPane().add(fsid);
				fsid.setColumns(10);
				
				JLabel lblStudentId_1 = new JLabel("Student ID");
				lblStudentId_1.setForeground(new Color(255, 153, 51));
				lblStudentId_1.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblStudentId_1.setBounds(10, 81, 78, 23);
				internalFrame_fees.getContentPane().add(lblStudentId_1);
				
				JLabel lblStudentName_1 = new JLabel("Student Name");
				lblStudentName_1.setForeground(new Color(255, 153, 0));
				lblStudentName_1.setBackground(new Color(0, 102, 153));
				lblStudentName_1.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblStudentName_1.setBounds(10, 126, 88, 26);
				internalFrame_fees.getContentPane().add(lblStudentName_1);
				
				fsnam = new JTextField();
				fsnam.setBounds(116, 129, 120, 24);
				internalFrame_fees.getContentPane().add(fsnam);
				fsnam.setColumns(10);
				
				lblPaymentDate = new JLabel("Payment Date");
				lblPaymentDate.setForeground(new Color(255, 153, 51));
				lblPaymentDate.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblPaymentDate.setBounds(10, 181, 115, 26);
				internalFrame_fees.getContentPane().add(lblPaymentDate);
				
				fstym = new JTextField();
				fstym.setBounds(116, 185, 120, 24);
				internalFrame_fees.getContentPane().add(fstym);
				fstym.setColumns(10);
				
				lblExamFees = new JLabel("Exam Fees");
				lblExamFees.setForeground(new Color(255, 153, 51));
				lblExamFees.setFont(new Font("Segoe UI", Font.BOLD, 16));
				lblExamFees.setBounds(184, 11, 97, 23);
				internalFrame_fees.getContentPane().add(lblExamFees);
				
				lblAmount = new JLabel("Amount");
				lblAmount.setForeground(new Color(255, 153, 0));
				lblAmount.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblAmount.setBounds(10, 237, 88, 23);
				internalFrame_fees.getContentPane().add(lblAmount);
				
				fsama = new JTextField();
				fsama.setBounds(116, 237, 120, 23);
				internalFrame_fees.getContentPane().add(fsama);
				fsama.setColumns(10);
				
				btnConfrim = new JButton("Confrim");
				btnConfrim.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int action=JOptionPane.showConfirmDialog(null, "Confirm Payment ?","Confirm",JOptionPane.YES_NO_OPTION);
						if(action==0)
							try
						{
								String fes = "insert into Exam_fees(student_id,Fees_date,FAmount) values(?,?,?) ";
								PreparedStatement ffs = con.prepareStatement(fes);
								
								ffs.setString(1, fsid.getText());
								ffs.setString(2, fstym.getText());
								ffs.setString(3, fsama.getText());
								
								String am = fsama.getText();
								int ag = Integer.parseInt(am);
								
								if(ag==150)
								{
									ffs.execute();
									
									JOptionPane.showMessageDialog(null, "Payment Sucessfull");
									ffs.close();
								}
								else
								{
									JOptionPane.showMessageDialog(null, "You Must Pay Fixed Fess 150TK");
								}
								
						}catch(Exception e12)
						{
							JOptionPane.showMessageDialog(null, "You Must Fill the Box");
						}
						
					}
				});
				btnConfrim.setBounds(172, 307, 89, 23);
				internalFrame_fees.getContentPane().add(btnConfrim);
				internalFrame_fees.setVisible(true);
			}
		});
		btnExamFees.setBounds(179, 13, 112, 28);
		frame.getContentPane().add(btnExamFees);
		
		
		
		JButton btnAdmit = new JButton("Register");
		btnAdmit.setForeground(new Color(204, 0, 255));
		btnAdmit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAdmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalFrame internalFrame_sf = new JInternalFrame("Student Information");
				internalFrame_sf.setBounds(10, 195, 551, 475);
				frame.getContentPane().add(internalFrame_sf);
				internalFrame_sf.setClosable(true);
				internalFrame_sf.setBorder(new CompoundBorder());
				internalFrame_sf.getContentPane().setBackground(SystemColor.inactiveCaption);
				internalFrame_sf.setResizable(true);
				internalFrame_sf.getContentPane().setLayout(null);
				
				JLabel lblStudentName = new JLabel("Student Name");
				lblStudentName.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblStudentName.setBounds(22, 82, 100, 32);
				internalFrame_sf.getContentPane().add(lblStudentName);
				
				JLabel lblNewLabel = new JLabel("Department");
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNewLabel.setBounds(341, 64, 89, 23);
				internalFrame_sf.getContentPane().add(lblNewLabel);
				
				JLabel lblClass = new JLabel("Class");
				lblClass.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblClass.setBounds(22, 134, 46, 14);
				internalFrame_sf.getContentPane().add(lblClass);
				
				JLabel lblSection = new JLabel("Section");
				lblSection.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblSection.setBounds(22, 217, 46, 14);
				internalFrame_sf.getContentPane().add(lblSection);
				
				JLabel lblCourse = new JLabel("Course");
				lblCourse.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblCourse.setBounds(22, 251, 46, 14);
				internalFrame_sf.getContentPane().add(lblCourse);
				
				adstuname = new JTextField();
				adstuname.setBounds(134, 88, 120, 26);
				internalFrame_sf.getContentPane().add(adstuname);
				adstuname.setColumns(10);
				
				depclass = new JTextField();
				depclass.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent arg0) {
						try
						{
							String shcr = "select course1,course2,course3,course4 from Class where Class_id=? ";
							PreparedStatement crps = con.prepareStatement(shcr);
							
							crps.setString(1, depclass.getText());
							ResultSet crsr = crps.executeQuery();
							
							while(crsr.next())
								{
								     deptc1.setText(crsr.getString("course1"));
								     deptc2.setText(crsr.getString("course2"));
								     deptc3.setText(crsr.getString("course3"));
								     deptc4.setText(crsr.getString("course4"));
								
								}
							crps.close();
						}catch(Exception e11)
						{
							JOptionPane.showMessageDialog(null, e11);
						}
						
						
					}
				});
				depclass.setBounds(134, 131, 120, 23);
				internalFrame_sf.getContentPane().add(depclass);
				depclass.setColumns(10);
				
				deptsec = new JTextField();
				deptsec.setBounds(134, 214, 120, 23);
				internalFrame_sf.getContentPane().add(deptsec);
				deptsec.setColumns(10);
				
				deptc1 = new JTextField();
				deptc1.setBounds(134, 248, 120, 23);
				internalFrame_sf.getContentPane().add(deptc1);
				deptc1.setColumns(10);
				
				deptc2 = new JTextField();
				deptc2.setBounds(134, 293, 120, 23);
				internalFrame_sf.getContentPane().add(deptc2);
				deptc2.setColumns(10);
				
				deptc3 = new JTextField();
				deptc3.setBounds(134, 338, 120, 23);
				internalFrame_sf.getContentPane().add(deptc3);
				deptc3.setColumns(10);
				
				deptc4 = new JTextField();
				deptc4.setBounds(134, 372, 120, 23);
				internalFrame_sf.getContentPane().add(deptc4);
				deptc4.setColumns(10);
				

				JRadioButton rdbtnCse = new JRadioButton("SCIENCE");
				rdbtnCse.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Dpnam = "SCIENCE";
					}
				});
				buttonGroup.add(rdbtnCse);
				rdbtnCse.setForeground(new Color(0, 51, 255));
				rdbtnCse.setFont(new Font("Tahoma", Font.BOLD, 11));
				rdbtnCse.setBackground(SystemColor.inactiveCaption);
				rdbtnCse.setBounds(338, 94, 109, 23);
				internalFrame_sf.getContentPane().add(rdbtnCse);
				
				JRadioButton rdbtnSwe = new JRadioButton("ARTS");
				rdbtnSwe.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Dpnam = "ARTS";
					}
				});
				buttonGroup.add(rdbtnSwe);
				rdbtnSwe.setForeground(new Color(0, 51, 255));
				rdbtnSwe.setFont(new Font("Tahoma", Font.BOLD, 11));
				rdbtnSwe.setBackground(SystemColor.inactiveCaption);
				rdbtnSwe.setBounds(338, 130, 109, 23);
				internalFrame_sf.getContentPane().add(rdbtnSwe);
				
				JRadioButton rdbtnEee = new JRadioButton("COMMERCE");
				rdbtnEee.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Dpnam = "COMMERCE";
						
					}
				});
				buttonGroup.add(rdbtnEee);
				rdbtnEee.setForeground(new Color(0, 51, 255));
				rdbtnEee.setFont(new Font("Tahoma", Font.BOLD, 11));
				rdbtnEee.setBackground(SystemColor.inactiveCaption);
				rdbtnEee.setBounds(338, 166, 109, 23);
				internalFrame_sf.getContentPane().add(rdbtnEee);
				
				JRadioButton rdbtnNotapplicable = new JRadioButton("Not_Applicable");
				rdbtnNotapplicable.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Dpnam = "Not_Applicable";
					}
				});
				buttonGroup.add(rdbtnNotapplicable);
				rdbtnNotapplicable.setForeground(new Color(72, 61, 139));
				rdbtnNotapplicable.setFont(new Font("Tahoma", Font.BOLD, 12));
				rdbtnNotapplicable.setBackground(SystemColor.inactiveCaption);
				rdbtnNotapplicable.setBounds(341, 202, 133, 23);
				internalFrame_sf.getContentPane().add(rdbtnNotapplicable);
				
				JButton deptSave = new JButton("Save");
				deptSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try
						{
							String dpsqre = "insert into StudentInfo(S_name,Class,Class_Roll,Section,S_Dept,Course1,Course2,Course3,Course4) values (?,?,?,?,?,?,?,?,?) ";
							PreparedStatement dpsts = con.prepareStatement(dpsqre);
							
							dpsts.setString(1, adstuname.getText());
							dpsts.setString(2, depclass.getText());
							dpsts.setString(3, croll.getText());
							dpsts.setString(4, deptsec.getText());
							dpsts.setString(5, Dpnam);
							
							dpsts.setString(6, deptc1.getText());
							dpsts.setString(7, deptc2.getText());
							dpsts.setString(8, deptc3.getText());
							dpsts.setString(9, deptc4.getText());
							
							String ck = adstuname.getText();
							
							if(ck==null||ck.equals(""))
							{
								JOptionPane.showMessageDialog(null, "You Must Provide Values");
							}
							else
							{
                                dpsts.execute();
								
								JOptionPane.showMessageDialog(null, "Data Saved");
								
								dpsts.close();
							
								
							}
							
							
							
						}catch(Exception dps)
						{
							JOptionPane.showMessageDialog(null, dps);
						}
						
						
					}
				});
				deptSave.setForeground(SystemColor.textHighlight);
				deptSave.setFont(new Font("Rockwell", Font.BOLD, 11));
				deptSave.setBounds(402, 293, 89, 23);
				internalFrame_sf.getContentPane().add(deptSave);
				
				JButton btnUpdate = new JButton("Update");
				btnUpdate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try
						{
							String stup = "update StudentInfo  set S_name=' "+adstuname.getText()+" ', Class= ' "+depclass.getText()+" ', Section = ' "+deptsec.getText()+" ', S_Dept =  ' "+Dpnam+" '  ,Course1 = ' "+deptc1.getText()+" ', Course2 = ' "+deptc2.getText()+" ',Course3 = ' "+deptc3.getText()+" ', Course4 = ' "+deptc4.getText()+" ' where S_id = ' "+adsid.getText()+" ' "; 
						    
							PreparedStatement pstup = con.prepareStatement(stup);
							
							pstup.execute();
							
							JOptionPane.showMessageDialog(null, "Updated");
							
							pstup.close();
						}catch(Exception stdup)
						{
							JOptionPane.showMessageDialog(null, stdup);
						}
						
					}
				});
				btnUpdate.setForeground(SystemColor.textHighlight);
				btnUpdate.setFont(new Font("Rockwell", Font.BOLD, 11));
				btnUpdate.setBounds(402, 338, 89, 23);
				internalFrame_sf.getContentPane().add(btnUpdate);
				
				JButton btnDelete = new JButton("Delete");
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int action=JOptionPane.showConfirmDialog(null, "Do you really wanted to delete?","Deleted",JOptionPane.YES_NO_OPTION);
						if(action==0)
						{
							try {
								String sdqr = "delete from StudentInfo where S_id =' "+adsid.getText()+" ' ";
								PreparedStatement psdt = con.prepareStatement(sdqr);
								
								psdt.execute();
								JOptionPane.showMessageDialog(null, "Deleted");
								
								psdt.close();
							}catch(Exception dps)
							{
								JOptionPane.showMessageDialog(null, dps);
							}
						}
						
					}
				});
				btnDelete.setToolTipText("Provide Student ID to delete");
				btnDelete.setForeground(SystemColor.textHighlight);
				btnDelete.setFont(new Font("Rockwell", Font.BOLD, 11));
				btnDelete.setBounds(402, 387, 89, 23);
				internalFrame_sf.getContentPane().add(btnDelete);
				
				adsid = new JTextField();
				adsid.setToolTipText("Provide Student ID for only Delete and Update Purpose");
				adsid.setBounds(134, 39, 120, 27);
				internalFrame_sf.getContentPane().add(adsid);
				adsid.setColumns(10);
				
				JLabel lblStudentId = new JLabel("Student ID");
				lblStudentId.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblStudentId.setBounds(22, 43, 89, 23);
				internalFrame_sf.getContentPane().add(lblStudentId);
				
				JLabel lblNewLabel_1 = new JLabel("* For adding new student data don't provide Student ID");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 10));
				lblNewLabel_1.setForeground(new Color(255, 0, 0));
				lblNewLabel_1.setBounds(264, 39, 257, 14);
				internalFrame_sf.getContentPane().add(lblNewLabel_1);
				
				
				JLabel lblClassId_1 = new JLabel("Class Roll");
				lblClassId_1.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblClassId_1.setBounds(22, 165, 73, 22);
				internalFrame_sf.getContentPane().add(lblClassId_1);
				
				croll = new JTextField();
				croll.setBounds(134, 167, 120, 22);
				internalFrame_sf.getContentPane().add(croll);
				croll.setColumns(10);
				
				
				
				internalFrame_sf.setVisible(true);
				
				
	     
			}
		});
		
		btnAdmit.setBounds(28, 52, 112, 28);
		frame.getContentPane().add(btnAdmit);
		

		
		rcla = new JTextField();
		rcla.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try				
				{
					String sub = "select course1,course2,course3,course4 from Class where Class_id=?";
					PreparedStatement sbps = con.prepareStatement(sub);
					sbps.setString(1, rcla.getText());
					
					ResultSet rbrs = sbps.executeQuery();
					while(rbrs.next())
					{
						rc1.setText(rbrs.getString("course1"));
						rc2.setText(rbrs.getString("course2"));
						rc3.setText(rbrs.getString("course3"));
						rc4.setText(rbrs.getString("course4"));
						
					}
					rbrs.close();
				}catch(Exception e14)
				{
					JOptionPane.showMessageDialog(null, e14);
				}
				
				
			}
		});
		
		
		btnNewButton = new JButton("Routine Entry");
		btnNewButton.setForeground(new Color(204, 102, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalFrame internalFrame = new JInternalFrame("Entry Routine ");
				internalFrame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 13));
				internalFrame.setClosable(true);
				internalFrame.getContentPane().setBackground(new Color(153, 153, 255));
				internalFrame.setBounds(10, 188, 561, 482);
				frame.getContentPane().add(internalFrame);
				internalFrame.getContentPane().setLayout(null);
				
				JLabel lblClassId = new JLabel("Class ID");
				lblClassId.setForeground(new Color(0, 0, 0));
				lblClassId.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblClassId.setBounds(10, 74, 62, 24);
				internalFrame.getContentPane().add(lblClassId);
				
				JLabel lblSectionId = new JLabel("Section Name");
				lblSectionId.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblSectionId.setBounds(215, 76, 91, 21);
				internalFrame.getContentPane().add(lblSectionId);
				
				rclid = new JTextField();
				rclid.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent arg0) {
						try
						{
							String shr = "select course1,course2,course3,course4 from Class where Class_id=? ";
							
							
							PreparedStatement cps = con.prepareStatement(shr);
							
							
							cps.setString(1, rclid.getText());
							
							ResultSet crs = cps.executeQuery();
					
							
							while(crs.next())
								{
								     crs1.setText(crs.getString("course1"));
								     crs2.setText(crs.getString("course2"));
								     crs3.setText(crs.getString("course3"));
								     crs4.setText(crs.getString("course4"));
								
								}
							cps.close();
						}catch(Exception e19)
						{
							JOptionPane.showMessageDialog(null, e19);
						}
						
					}
					
				});
				
				rclid.setBounds(80, 77, 86, 20);
				internalFrame.getContentPane().add(rclid);
				rclid.setColumns(10);
				
				rsec = new JTextField();
				rsec.setBounds(331, 77, 91, 21);
				internalFrame.getContentPane().add(rsec);
				rsec.setColumns(10);
				
				JLabel lblCourse_5 = new JLabel("Course 1:");
				lblCourse_5.setFont(new Font("Tahoma", Font.ITALIC, 12));
				lblCourse_5.setBounds(10, 185, 62, 24);
				internalFrame.getContentPane().add(lblCourse_5);
				
				JLabel lblCourse_6 = new JLabel("Course 2:");
				lblCourse_6.setFont(new Font("Tahoma", Font.ITALIC, 12));
				lblCourse_6.setBounds(10, 236, 62, 21);
				internalFrame.getContentPane().add(lblCourse_6);
				
				JLabel lblCourse_7 = new JLabel("Course 3:");
				lblCourse_7.setFont(new Font("Tahoma", Font.ITALIC, 12));
				lblCourse_7.setBounds(10, 279, 62, 21);
				internalFrame.getContentPane().add(lblCourse_7);
				
				JLabel lblCourse_8 = new JLabel("Course 4:");
				lblCourse_8.setFont(new Font("Tahoma", Font.ITALIC, 12));
				lblCourse_8.setBounds(10, 328, 62, 15);
				internalFrame.getContentPane().add(lblCourse_8);
				
				crs1 = new JTextField();
				crs1.setBounds(80, 188, 86, 20);
				internalFrame.getContentPane().add(crs1);
				crs1.setColumns(10);
				
				crs2 = new JTextField();
				crs2.setBounds(80, 237, 86, 20);
				internalFrame.getContentPane().add(crs2);
				crs2.setColumns(10);
				
				crs3 = new JTextField();
				crs3.setBounds(80, 280, 86, 20);
				internalFrame.getContentPane().add(crs3);
				crs3.setColumns(10);
				
				crs4 = new JTextField();
				crs4.setBounds(80, 326, 86, 20);
				internalFrame.getContentPane().add(crs4);
				crs4.setColumns(10);
				
				JLabel lblRoomNumber = new JLabel("Room Number");
				lblRoomNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblRoomNumber.setBounds(202, 151, 91, 24);
				internalFrame.getContentPane().add(lblRoomNumber);
				
				JLabel lblClassTime = new JLabel("Class Time");
				lblClassTime.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblClassTime.setBounds(345, 151, 77, 24);
				internalFrame.getContentPane().add(lblClassTime);
				
				rm1 = new JTextField();
				rm1.setBounds(202, 188, 86, 20);
				internalFrame.getContentPane().add(rm1);
				rm1.setColumns(10);
				
				rm2 = new JTextField();
				rm2.setBounds(202, 237, 86, 20);
				internalFrame.getContentPane().add(rm2);
				rm2.setColumns(10);
				
				rm3 = new JTextField();
				rm3.setBounds(202, 280, 86, 20);
				internalFrame.getContentPane().add(rm3);
				rm3.setColumns(10);
				
				rm4 = new JTextField();
				rm4.setBounds(202, 326, 86, 20);
				internalFrame.getContentPane().add(rm4);
				rm4.setColumns(10);
				
				ctm1 = new JTextField();
				ctm1.setBounds(329, 188, 92, 21);
				internalFrame.getContentPane().add(ctm1);
				ctm1.setColumns(10);
				
				ctm2 = new JTextField();
				ctm2.setBounds(329, 237, 92, 20);
				internalFrame.getContentPane().add(ctm2);
				ctm2.setColumns(10);
				
				ctm3 = new JTextField();
				ctm3.setBounds(329, 280, 92, 20);
				internalFrame.getContentPane().add(ctm3);
				ctm3.setColumns(10);
				
				ctm4 = new JTextField();
				ctm4.setBounds(329, 326, 92, 21);
				internalFrame.getContentPane().add(ctm4);
				ctm4.setColumns(10);
				
				btnNewButton_2 = new JButton("Confirm");
				btnNewButton_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try
						{
							String co1 = "insert into Room(Room_num,Class_time,Section_nam,Course_name) values(?,?,?,?) ";
							
							
							PreparedStatement cps1 = con.prepareStatement(co1);
						
						
							
							cps1.setString(1, rm1.getText());
							cps1.setString(2, ctm1.getText());
							cps1.setString(3, rsec.getText());
							cps1.setString(4, crs1.getText());
							
	                    String nr1 = rm1.getText();
							
							if(nr1.equals(null)||nr1.equals(""))
							{
								JOptionPane.showMessageDialog(null, "You Must Fill the Box");
							}
							else
							{
								cps1.execute();
								JOptionPane.showMessageDialog(null, "Shedule Added");
								
								cps1.close();
							}
							
							
						}catch(Exception e20)
						{
							JOptionPane.showMessageDialog(null, e20);
						}
						
					}
				});
				btnNewButton_2.setBounds(445, 187, 89, 23);
				internalFrame.getContentPane().add(btnNewButton_2);
				
				btnNewButton_3 = new JButton("Confirm");
				btnNewButton_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try
						{
							String co2 = "insert into Room(Room_num,Class_time,Section_nam,Course_name) values(?,?,?,?) ";
							PreparedStatement cps2 = con.prepareStatement(co2);
							
							cps2.setString(1, rm2.getText());
							cps2.setString(2, ctm2.getText());
							cps2.setString(3, rsec.getText());
							cps2.setString(4, crs2.getText());
							
							String nr2 = rm2.getText();
							
							if(nr2.equals(null)||nr2.equals(""))
							{
								JOptionPane.showMessageDialog(null, "You Must Fill the Box");
							}
							else
							{
								cps2.execute();
								JOptionPane.showMessageDialog(null, "Shedule Added");
								
								cps2.close();
							}
							
							
						}catch(Exception e21)
						{
							JOptionPane.showMessageDialog(null, e21);
						}
					}
				});
				btnNewButton_3.setBounds(445, 236, 89, 23);
				internalFrame.getContentPane().add(btnNewButton_3);
				
				btnNewButton_4 = new JButton("Confirm");
				btnNewButton_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try
						{
							String co3 = "insert into Room(Room_num,Class_time,Section_nam,Course_name) values(?,?,?,?) ";
							PreparedStatement cps3 = con.prepareStatement(co3);
							
							cps3.setString(1, rm3.getText());
							cps3.setString(2, ctm3.getText());
							cps3.setString(3, rsec.getText());
							cps3.setString(4, crs3.getText());
	                        String nr3 = rm3.getText();
							
							if(nr3.equals(null)||nr3.equals(""))
							{
								JOptionPane.showMessageDialog(null, "You Must Fill the Box");
							}
							else
							{
								cps3.execute();
								JOptionPane.showMessageDialog(null, "Shedule Added");
								
								cps3.close();
							}
							
							
						}catch(Exception e22)
						{
							JOptionPane.showMessageDialog(null, e22);
						}
					}
				});
				btnNewButton_4.setBounds(445, 279, 89, 23);
				internalFrame.getContentPane().add(btnNewButton_4);
				
				btnNewButton_5 = new JButton("Confirm");
				btnNewButton_5.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try
						{
							String co4 = "insert into Room(Room_num,Class_time,Section_nam,Course_name) values(?,?,?,?) ";
							PreparedStatement cps4 = con.prepareStatement(co4);
							
							cps4.setString(1, rm4.getText());
							cps4.setString(2, ctm4.getText());
							cps4.setString(3, rsec.getText());
							cps4.setString(4, crs4.getText());
	                        String nr4 = rm4.getText();
							
							if(nr4.equals(null)||nr4.equals(""))
							{
								JOptionPane.showMessageDialog(null, "You Must Fill the Box");
							}
							else
							{
								cps4.execute();
								JOptionPane.showMessageDialog(null, "Shedule Added");
								
								cps4.close();
							}
							
							
						}catch(Exception e23)
						{
							JOptionPane.showMessageDialog(null, e23);
						}
					}
				});
				btnNewButton_5.setBounds(445, 325, 89, 23);
				internalFrame.getContentPane().add(btnNewButton_5);
				
				JLabel lblClassRoutinFor = new JLabel("Class Routin for Student");
				lblClassRoutinFor.setForeground(new Color(102, 0, 153));
				lblClassRoutinFor.setFont(new Font("Tahoma", Font.BOLD, 15));
				lblClassRoutinFor.setBounds(181, 11, 224, 24);
				internalFrame.getContentPane().add(lblClassRoutinFor);
				internalFrame.setVisible(true);
			}
		});
		btnNewButton.setBounds(28, 11, 112, 28);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Home");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminHome ah = new AdminHome();
				ah.admain(null);
				frame.dispose();
				
			}
		});
		btnNewButton_1.setBackground(new Color(0, 0, 0));
		btnNewButton_1.setForeground(new Color(255, 0, 0));
		btnNewButton_1.setFont(new Font("Century", Font.BOLD, 12));
		btnNewButton_1.setBounds(943, 21, 100, 30);
		frame.getContentPane().add(btnNewButton_1);
		
		
		

		
		
		

		
	}
}
