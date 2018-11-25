import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

public class StudentCheck {

	private JFrame frame;
	private JTextField search;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentCheck window = new StudentCheck();
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
	public StudentCheck() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	Connection con = null;
	private JTable table;
	private JTextField ruclas;
	private JTextField asid;
	private JTextField afnam;
	private JTextField alnam;
	private JTextField adob;
	private JTextField asadd;
	private JTextField acon;
	private String Gender;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField rsid;
	private JTextField efid;
	private void initialize() {
		con = Connect.dbConnector();
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(102, 0, 51));
		frame.setBounds(100, 100, 1100, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblStudentCheck = new JLabel("Student Home Menu");
		lblStudentCheck.setForeground(new Color(204, 255, 0));
		lblStudentCheck.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 30));
		lblStudentCheck.setBounds(315, 11, 363, 48);
		frame.getContentPane().add(lblStudentCheck);
		
		JButton btnResult = new JButton("Result");
		btnResult.setForeground(new Color(178, 34, 34));
		btnResult.setFont(new Font("Microsoft Tai Le", Font.BOLD, 12));
		btnResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					String shre = "select * from Result where Student_id=?";
					PreparedStatement sh = con.prepareStatement(shre);
					
					sh.setString(1, rsid.getText());
					
					ResultSet rh = sh.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rh));
				}catch(Exception e27)
				{
					JOptionPane.showMessageDialog(null, e27);
				}
				
			}
		});
		btnResult.setBounds(961, 364, 113, 21);
		frame.getContentPane().add(btnResult);
		
	
		
		
		JButton btnRoutine = new JButton("Routine");
		btnRoutine.setForeground(new Color(0, 51, 51));
		btnRoutine.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRoutine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					
			      String rutin = "select Co_name,Room_num,Class_time,Section_nam from Room inner join Course on Course.Co_name = Room.Course_name where class_id = ' "+ruclas.getText()+" '  ";
			    
					PreparedStatement rups = con.prepareStatement(rutin);
					
					ResultSet rurs = rups.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rurs));
					
					
				}catch(Exception e14)
				{
					JOptionPane.showMessageDialog(null, e14);
				}
				
				
			}
		});
		btnRoutine.setBounds(961, 260, 113, 21);
		frame.getContentPane().add(btnRoutine);
				
				
				
				search = new JTextField();
				search.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent arg0)
					{
						try
						{
							
							String sec = "select S_name,S_id,Class,Class_Roll,Section,DOB,Gender,Address,ContactNO from StudentInfo left join Student_Additional on StudentInfo.S_id = Student_Additional.StudentID where S_id=? ";
						
							
					        
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
				search.setBounds(895, 514, 141, 20);
				frame.getContentPane().add(search);
				search.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 545, 1049, 125);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		ruclas = new JTextField();
		ruclas.setBounds(816, 261, 137, 20);
		frame.getContentPane().add(ruclas);
		ruclas.setColumns(10);
		
		
		JLabel lblClassId = new JLabel("Class ID");
		lblClassId.setForeground(Color.ORANGE);
		lblClassId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblClassId.setBounds(747, 261, 59, 19);
		frame.getContentPane().add(lblClassId);
		
		JButton btnAddInformation = new JButton("Add Information");
		btnAddInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame internalFrame = new JInternalFrame("Addtional Student Information");
				internalFrame.setClosable(true);
				internalFrame.getContentPane().setBackground(new Color(51, 153, 153));
				internalFrame.setBounds(10, 56, 511, 439);
				frame.getContentPane().add(internalFrame);
				internalFrame.getContentPane().setLayout(null);
				
				JLabel lblAdditionalInformations = new JLabel("Additional Informations");
				lblAdditionalInformations.setForeground(new Color(255, 255, 0));
				lblAdditionalInformations.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
				lblAdditionalInformations.setBounds(159, 11, 196, 38);
				internalFrame.getContentPane().add(lblAdditionalInformations);
				
				JLabel lblStudentId = new JLabel("Student ID");
				lblStudentId.setForeground(new Color(0, 255, 0));
				lblStudentId.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblStudentId.setBounds(10, 97, 83, 15);
				internalFrame.getContentPane().add(lblStudentId);
				
				JLabel lblFirstName = new JLabel("First Name");
				lblFirstName.setForeground(new Color(102, 255, 51));
				lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblFirstName.setBounds(10, 138, 72, 14);
				internalFrame.getContentPane().add(lblFirstName);
				
				JLabel lblLastName = new JLabel("Last Name");
				lblLastName.setForeground(new Color(102, 255, 51));
				lblLastName.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblLastName.setBounds(10, 177, 72, 14);
				internalFrame.getContentPane().add(lblLastName);
				
				JLabel lblDateOfBirth = new JLabel("Date of Birth");
				lblDateOfBirth.setForeground(new Color(102, 255, 51));
				lblDateOfBirth.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblDateOfBirth.setBounds(10, 202, 83, 14);
				internalFrame.getContentPane().add(lblDateOfBirth);
				
				JLabel lblGender = new JLabel("Gender");
				lblGender.setForeground(new Color(0, 255, 0));
				lblGender.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblGender.setBounds(10, 237, 57, 14);
				internalFrame.getContentPane().add(lblGender);
				
				JLabel lblAddress = new JLabel("Address");
				lblAddress.setForeground(new Color(0, 255, 0));
				lblAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblAddress.setBounds(10, 274, 57, 14);
				internalFrame.getContentPane().add(lblAddress);
				
				JLabel lblPhoneNo = new JLabel("Contact No");
				lblPhoneNo.setForeground(new Color(0, 255, 0));
				lblPhoneNo.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblPhoneNo.setBounds(10, 314, 72, 14);
				internalFrame.getContentPane().add(lblPhoneNo);
				
				asid = new JTextField();
				asid.setBounds(103, 96, 116, 20);
				internalFrame.getContentPane().add(asid);
				asid.setColumns(10);
				
				afnam = new JTextField();
				afnam.setBounds(103, 138, 116, 20);
				internalFrame.getContentPane().add(afnam);
				afnam.setColumns(10);
				
				alnam = new JTextField();
				alnam.setBounds(102, 173, 117, 20);
				internalFrame.getContentPane().add(alnam);
				alnam.setColumns(10);
				
				adob = new JTextField();
				adob.setToolTipText("YY/MM/DD");
				adob.setBounds(103, 200, 116, 20);
				internalFrame.getContentPane().add(adob);
				adob.setColumns(10);
				
				JRadioButton rdbtnMale = new JRadioButton("Male");
				rdbtnMale.setFont(new Font("Tahoma", Font.BOLD, 12));
				rdbtnMale.setForeground(new Color(0, 0, 0));
				rdbtnMale.setBackground(new Color(51, 153, 153));
				rdbtnMale.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Gender = "Male";
					}
				});
				buttonGroup.add(rdbtnMale);
				rdbtnMale.setBounds(72, 237, 72, 14);
				internalFrame.getContentPane().add(rdbtnMale);
				
				JRadioButton rdbtnFemale = new JRadioButton("Female");
				rdbtnFemale.setFont(new Font("Tahoma", Font.BOLD, 12));
				rdbtnFemale.setBackground(new Color(51, 153, 153));
				rdbtnFemale.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Gender = "Female";
					}
				});
				buttonGroup.add(rdbtnFemale);
				rdbtnFemale.setBounds(159, 237, 81, 14);
				internalFrame.getContentPane().add(rdbtnFemale);
				
				asadd = new JTextField();
				asadd.setBounds(103, 267, 158, 38);
				internalFrame.getContentPane().add(asadd);
				asadd.setColumns(10);
				
				acon = new JTextField();
				acon.setBounds(103, 312, 125, 20);
				internalFrame.getContentPane().add(acon);
				acon.setColumns(10);
				
				JButton btnSave = new JButton("Save");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try
						{
							String asq ="insert into Student_Additional(StudentID,FirstName,LastName,DOB,Gender,Address,ContactNO) values (?,?,?,?,?,?,?) ";
							PreparedStatement adp = con.prepareStatement(asq);
							
							adp.setString(1, asid.getText());
							adp.setString(2, afnam.getText());
							adp.setString(3, alnam.getText());
							adp.setString(4, adob.getText());
							adp.setString(5, Gender);
							adp.setString(6, asadd.getText());
							adp.setString(7, acon.getText());
							
							
							
							adp.execute();
							btnSave.setEnabled(true);
							
							JOptionPane.showMessageDialog(null, "Data Saved");
							
							adp.close();
							
						}catch(Exception e15)
						{
							JOptionPane.showMessageDialog(null, e15);
						}
						
						
					}
				});
				btnSave.setBounds(326, 347, 89, 23);
				internalFrame.getContentPane().add(btnSave);
				internalFrame.setVisible(true);
				
			}
		});
		btnAddInformation.setBackground(new Color(0, 153, 255));
		btnAddInformation.setForeground(new Color(255, 255, 255));
		btnAddInformation.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 13));
		btnAddInformation.setBounds(906, 160, 153, 41);
		frame.getContentPane().add(btnAddInformation);
		
		JLabel lblNewLabel = new JLabel("Provide Student ID to show all your Information");
		lblNewLabel.setForeground(new Color(240, 255, 240));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(714, 475, 322, 28);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblStudentId_1 = new JLabel("Student ID");
		lblStudentId_1.setForeground(new Color(153, 255, 102));
		lblStudentId_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblStudentId_1.setBounds(786, 515, 86, 17);
		frame.getContentPane().add(lblStudentId_1);
		
		JLabel lblStudentAllInformations = new JLabel("Student All Informations");
		lblStudentAllInformations.setForeground(new Color(0, 204, 51));
		lblStudentAllInformations.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblStudentAllInformations.setBounds(321, 497, 334, 41);
		frame.getContentPane().add(lblStudentAllInformations);
		
		JLabel lblProvideClassId = new JLabel("Provide Class ID to view your class routine Information");
		lblProvideClassId.setForeground(new Color(102, 255, 153));
		lblProvideClassId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProvideClassId.setBounds(709, 227, 350, 23);
		frame.getContentPane().add(lblProvideClassId);
		
		rsid = new JTextField();
		rsid.setBounds(816, 364, 137, 20);
		frame.getContentPane().add(rsid);
		rsid.setColumns(10);
		
		JLabel lblStudentId_2 = new JLabel("Student ID");
		lblStudentId_2.setForeground(new Color(255, 255, 0));
		lblStudentId_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblStudentId_2.setBounds(714, 363, 98, 20);
		frame.getContentPane().add(lblStudentId_2);
		
		JLabel lblProvideStudentId = new JLabel("Provide Student ID and Press \"Result\" Button to view result");
		lblProvideStudentId.setForeground(new Color(0, 191, 255));
		lblProvideStudentId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProvideStudentId.setBounds(698, 307, 376, 20);
		frame.getContentPane().add(lblProvideStudentId);
		
		JLabel lblForAddingadditional = new JLabel("For adding \"Additional Information\" Just press the Button ");
		lblForAddingadditional.setForeground(new Color(255, 204, 0));
		lblForAddingadditional.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblForAddingadditional.setBounds(687, 130, 387, 19);
		frame.getContentPane().add(lblForAddingadditional);
		
		JButton btnNewButton = new JButton("Log Out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginHome lh = new LoginHome();
				lh.main(null);
				frame.dispose();
			}
		});
		btnNewButton.setForeground(new Color(255, 51, 51));
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(959, 32, 100, 30);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblExamFeesStatus = new JLabel("Exam Fees Status");
		lblExamFeesStatus.setForeground(Color.CYAN);
		lblExamFeesStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblExamFeesStatus.setBounds(714, 443, 113, 21);
		frame.getContentPane().add(lblExamFeesStatus);
		
		efid = new JTextField();
		efid.setBounds(837, 444, 116, 20);
		frame.getContentPane().add(efid);
		efid.setColumns(10);
		
		JButton btnCheck = new JButton("Exam_Fees");
		btnCheck.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					String fs = "select * from Exam_fees where student_id=? ";
					PreparedStatement fps = con.prepareStatement(fs);
					
					fps.setString(1, efid.getText());
					
                    ResultSet frs  = fps.executeQuery();
                    
                    table.setModel(DbUtils.resultSetToTableModel(frs));

                    frs.close();
				}catch(Exception e30)
				{
					JOptionPane.showMessageDialog(null, e30);
				}
				
			}
		});
		btnCheck.setBounds(961, 444, 113, 21);
		frame.getContentPane().add(btnCheck);
		
		JLabel lblProvideStudentId_1 = new JLabel("Provide Student ID to Check Exam Fees Status");
		lblProvideStudentId_1.setForeground(Color.ORANGE);
		lblProvideStudentId_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProvideStudentId_1.setBounds(714, 411, 341, 14);
		frame.getContentPane().add(lblProvideStudentId_1);
		
		
		

		
	}
}
