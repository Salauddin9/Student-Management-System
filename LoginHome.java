import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import java.awt.SystemColor;
import javax.swing.UIManager;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class LoginHome {

	private JFrame frame;
	private JTextField Aduser;
	private JPasswordField Adpass;
	private JInternalFrame internalFrame;




	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginHome window = new LoginHome();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	

	public LoginHome() {
		
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	Connection Con = null;
	private JTextField adusernam;
	private JTextField adpass;
	private JTextField teausa;
	private JTextField teapass;
	private JTextField csid;
	private JTextField lu;
	private JPasswordField lpa;
	private JTextField ylog;
	private JPasswordField ypas;
	private JTextField tusa;
	private JPasswordField tpas;
	private JTextField tcid;
	private JTextField tlusa;
	private JPasswordField tlpass;
	
	
	
	
	private void initialize() {
		Con = Connect.dbConnector();
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(175, 238, 238));
		frame.setBounds(100, 100, 1100, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.setForeground(new Color(0, 0, 0));
		btnAdmin.setBackground(new Color(255, 165, 0));
		btnAdmin.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame AdminFrame = new JInternalFrame("Admin Login");
				AdminFrame.getContentPane().setBackground(new Color(0, 0, 0));
				AdminFrame.setClosable(true);
				AdminFrame.setBounds(281, 159, 394, 234);
				frame.getContentPane().add(AdminFrame);
				AdminFrame.getContentPane().setLayout(null);
				JLabel lblUserName = new JLabel("User Name");
				lblUserName.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblUserName.setForeground(new Color(255, 0, 0));
				lblUserName.setBackground(new Color(0, 0, 0));
				lblUserName.setBounds(90, 37, 80, 24);
				AdminFrame.getContentPane().add(lblUserName);
				
				JLabel lblPassword = new JLabel("Password");
				lblPassword.setForeground(new Color(72, 209, 204));
				lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblPassword.setBounds(90, 72, 80, 24);
				AdminFrame.getContentPane().add(lblPassword);
				Aduser = new JTextField();
				Aduser.setBounds(190, 39, 89, 20);
				AdminFrame.getContentPane().add(Aduser);
				Aduser.setColumns(10);
				
				Adpass = new JPasswordField();
				Adpass.setBounds(190, 70, 89, 20);
				AdminFrame.getContentPane().add(Adpass);
				
				JButton AdminbtnLogin = new JButton("LogIn");
				AdminbtnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
				AdminbtnLogin.setForeground(new Color(245, 245, 245));
				AdminbtnLogin.setBackground(new Color(0, 0, 0));
				AdminbtnLogin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent agr0)
					{
						try {
						String aqure = "select * from Admin_Logs where Auser=? and Apass=?";
						PreparedStatement apst = Con.prepareStatement(aqure);
						apst.setString(1, Aduser.getText());
						apst.setString(2, Adpass.getText());
						
						ResultSet ars = apst.executeQuery();
						
						int count=0; 
						
						while(ars.next())
						{
							count++;
						}
						if(count==1)
						{
							JOptionPane.showMessageDialog(null, "Succesfully Logged In");
							AdminHome ob = new AdminHome();
							ob.admain(null);
							frame.dispose();
							
						}
						else if(count<1)
						{
							JOptionPane.showMessageDialog(null, "Access Denied!!");
						}
						
						ars.close();
						apst.close();
						
						}catch(Exception ae)
						{
							JOptionPane.showMessageDialog(null, ae);
						}
					}
				});
				AdminbtnLogin.setBounds(92, 126, 78, 24);
				AdminFrame.getContentPane().add(AdminbtnLogin);
				
				JButton btnCancel = new JButton("Cancel");
				btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnCancel.setForeground(new Color(255, 255, 0));
				btnCancel.setBackground(new Color(0, 0, 0));
				btnCancel.setBounds(199, 126, 80, 24);
				AdminFrame.getContentPane().add(btnCancel);
				AdminFrame.setVisible(true);
				
				
			}
		});
		btnAdmin.setBounds(27, 254, 89, 57);
		frame.getContentPane().add(btnAdmin);
		
		
		
		
		JButton btnStudent = new JButton("Student");
		btnStudent.setBackground(new Color(0, 191, 255));
		btnStudent.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JInternalFrame internalFrame = new JInternalFrame("Student Login");
				internalFrame.setResizable(true);
				internalFrame.setIconifiable(true);
				internalFrame.setClosable(true);
				internalFrame.setBackground(new Color(102, 153, 153));
				internalFrame.setBounds(188, 144, 430, 469);
				frame.getContentPane().add(internalFrame);
				internalFrame.getContentPane().setLayout(null);
				
			
				
				JLabel lblUsername = new JLabel("UserName");
				lblUsername.setForeground(new Color(204, 255, 0));
				lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblUsername.setBounds(45, 93, 76, 29);
				internalFrame.getContentPane().add(lblUsername);
				
				JLabel lblPassword_2 = new JLabel("Password");
				lblPassword_2.setForeground(new Color(204, 255, 51));
				lblPassword_2.setBackground(new Color(0, 153, 153));
				lblPassword_2.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblPassword_2.setBounds(45, 153, 76, 20);
				internalFrame.getContentPane().add(lblPassword_2);
				
				
				csid = new JTextField();
				csid.setToolTipText("Student ID");
				csid.setBounds(49, 357, 138, 28);
				internalFrame.getContentPane().add(csid);
				csid.setColumns(10);
				
				JButton btnNew = new JButton("Create New Accaount");
				btnNew.setForeground(new Color(255, 51, 0));
				btnNew.setFont(new Font("Segoe UI", Font.BOLD, 11));
				btnNew.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try
						{
							String lqr = "select S_id from StudentInfo where S_id=?";
							PreparedStatement lps = Con.prepareStatement(lqr);
							lps.setString(1, csid.getText());
							
							ResultSet lrs = lps.executeQuery();
							int co = 0;
							while(lrs.next())
							{
								co++;
							}
							if(co==1)
							{
								JInternalFrame internalFrame_Ex = new JInternalFrame("Create Student Login Account");
								internalFrame_Ex.getContentPane().setBackground(new Color(102, 102, 153));
								internalFrame_Ex.setBounds(575, 144, 439, 416);
								frame.getContentPane().add(internalFrame_Ex);
								internalFrame_Ex.getContentPane().setLayout(null);
								internalFrame_Ex.setClosable(true);
								
								JLabel lblUsername_1 = new JLabel("User Name");
								lblUsername_1.setForeground(new Color(204, 102, 102));
								lblUsername_1.setFont(new Font("Tahoma", Font.BOLD, 12));
								lblUsername_1.setBounds(24, 107, 68, 24);
								internalFrame_Ex.getContentPane().add(lblUsername_1);
								
								JLabel lblPassword_3 = new JLabel("Password");
								lblPassword_3.setForeground(new Color(204, 102, 102));
								lblPassword_3.setFont(new Font("Tahoma", Font.BOLD, 12));
								lblPassword_3.setBounds(24, 161, 68, 15);
								internalFrame_Ex.getContentPane().add(lblPassword_3);
								
								lu = new JTextField();
								lu.setBounds(113, 110, 86, 20);
								internalFrame_Ex.getContentPane().add(lu);
								lu.setColumns(10);
								
								lpa = new JPasswordField();
								lpa.setBounds(113, 159, 86, 20);
								internalFrame_Ex.getContentPane().add(lpa);
								
								JButton btnCreate = new JButton("Confrim");
								btnCreate.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										int action=JOptionPane.showConfirmDialog(null, "Do you really wanted to Create a Account ?","Account Confirmed",JOptionPane.YES_NO_OPTION);
										if(action==0)
										try
										{
											String ipu = "insert into StudentLogs(Suser,Spass) values(?,?) ";
											PreparedStatement ipst = Con.prepareStatement(ipu);
											
											ipst.setString(1, lu.getText());
											ipst.setString(2, lpa.getText());
											
											ipst.execute();
											JOptionPane.showMessageDialog(null, "Account Added");
											
											ipst.close();
										}catch(Exception e16)
										{
											JOptionPane.showMessageDialog(null, e16);
											JOptionPane.showMessageDialog(null, "You must Provide a Value for Creating an Account");
										}
									}
								});
								btnCreate.setBounds(199, 243, 89, 30);
								internalFrame_Ex.getContentPane().add(btnCreate);
								internalFrame_Ex.setVisible(true);
								internalFrame.setVisible(true);
								
							}
							else if(co<1)
							{
								JOptionPane.showMessageDialog(null, "Invalid Student ID");
							}
							
						}catch(Exception e17)
						{
							JOptionPane.showMessageDialog(null, e17);
						}
					}
				});
				btnNew.setBounds(197, 356, 163, 29);
				internalFrame.getContentPane().add(btnNew);
				
				ylog = new JTextField();
				ylog.setBounds(145, 93, 115, 24);
				internalFrame.getContentPane().add(ylog);
				ylog.setColumns(10);
				
				ypas = new JPasswordField();
				ypas.setBounds(145, 154, 115, 20);
				internalFrame.getContentPane().add(ypas);
				
				JButton btnSignIn_1 = new JButton("Sign In");
				btnSignIn_1.setForeground(new Color(0, 51, 102));
				btnSignIn_1.setFont(new Font("Verdana", Font.BOLD, 11));
				btnSignIn_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try
						{
							String fre = "select * from StudentLogs where Suser=? and Spass=?";
							PreparedStatement fpat = Con.prepareStatement(fre);
							
							fpat.setString(1, ylog.getText());
							fpat.setString(2, ypas.getText());
							
							ResultSet frs = fpat.executeQuery();
							int coun = 0;
							
							while(frs.next())
							{
								coun++;
							}
							if(coun==1)
							{
								//JOptionPane.showMessageDialog(null, "Welcome to Student Page");
								StudentCheck ob3 = new StudentCheck();
								ob3.main(null);
								frame.dispose();
							}
							else if(coun<1)
							{
								
								JOptionPane.showMessageDialog(null, "Wrong User Name or Password");
								
							}
							frs.close();
							fpat.close();
						}catch(Exception e18)
						{
							JOptionPane.showMessageDialog(null, e18);
						}
						
						
					}
				});
				btnSignIn_1.setBounds(165, 213, 89, 23);
				internalFrame.getContentPane().add(btnSignIn_1);
				
				JLabel lblStudentLogin = new JLabel("Sign in to view profiles");
				lblStudentLogin.setForeground(new Color(0, 255, 153));
				lblStudentLogin.setFont(new Font("Forte", Font.PLAIN, 20));
				lblStudentLogin.setBounds(109, 11, 229, 29);
				internalFrame.getContentPane().add(lblStudentLogin);
				
				JLabel lblNew = new JLabel("New Admitted?");
				lblNew.setForeground(new Color(255, 255, 153));
				lblNew.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 12));
				lblNew.setBounds(22, 284, 89, 20);
				internalFrame.getContentPane().add(lblNew);
				
				JLabel lblPress = new JLabel("*Provide Student ID to Create a New Account");
				lblPress.setForeground(new Color(0, 0, 0));
				lblPress.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 12));
				lblPress.setBounds(22, 316, 265, 14);
				internalFrame.getContentPane().add(lblPress);
				
				internalFrame.setVisible(true);
			}
		});
		btnStudent.setBounds(27, 186, 89, 57);
		frame.getContentPane().add(btnStudent);
		
		JButton btnTeacher = new JButton("Teacher");
		btnTeacher.setBackground(new Color(199, 21, 133));
		btnTeacher.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JInternalFrame internalFrame_1 = new JInternalFrame("Teacher Login");
				internalFrame_1.getContentPane().setBackground(new Color(51, 0, 102));
				internalFrame_1.setClosable(true);
				internalFrame_1.setBounds(155, 124, 457, 517);
				frame.getContentPane().add(internalFrame_1);
			    
				internalFrame_1.getContentPane().setLayout(null);
				
				JLabel lblSignInTo = new JLabel("Sign In to View Profiles");
				lblSignInTo.setForeground(new Color(255, 255, 0));
				lblSignInTo.setFont(new Font("Footlight MT Light", Font.BOLD, 17));
				lblSignInTo.setBounds(123, 26, 220, 47);
				internalFrame_1.getContentPane().add(lblSignInTo);
				
				JLabel lblUsername_2 = new JLabel("UserName");
				lblUsername_2.setForeground(new Color(0, 204, 0));
				lblUsername_2.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblUsername_2.setBounds(82, 137, 84, 28);
				internalFrame_1.getContentPane().add(lblUsername_2);
				
				JLabel lblPassword_4 = new JLabel("Password");
				lblPassword_4.setForeground(new Color(51, 204, 51));
				lblPassword_4.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblPassword_4.setBounds(84, 199, 70, 21);
				internalFrame_1.getContentPane().add(lblPassword_4);
				
				tusa = new JTextField();
				tusa.setBounds(184, 141, 130, 23);
				internalFrame_1.getContentPane().add(tusa);
				tusa.setColumns(10);
				
				tpas = new JPasswordField();
				tpas.setBounds(184, 200, 130, 20);
				internalFrame_1.getContentPane().add(tpas);
				
				JButton btnSignIn_2 = new JButton("Sign In");
				btnSignIn_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try
						{
							String tl = "select * from TeacherLogs where Tuser=? and Tpass=? ";
							PreparedStatement tls = Con.prepareStatement(tl);
							
							tls.setString(1, tusa.getText());
							tls.setString(2, tpas.getText());
							
							ResultSet lrs = tls.executeQuery();
							
							int q = 0;
							
							while(lrs.next())
							{
								q++;
							}
							if(q==1)
							{
								JOptionPane.showMessageDialog(null, "Login Successfull");
								TeacherHome th = new TeacherHome();
								th.main(null);
								frame.dispose();
							}
							else if(q<1)					
							{
								JOptionPane.showMessageDialog(null, "Worng UserName or Password");
							}
							lrs.close();
							tls.close();
							
						}catch(Exception e19)
						{
							JOptionPane.showMessageDialog(null, e19);
						}
					}
				});
				btnSignIn_2.setForeground(new Color(51, 0, 102));
				btnSignIn_2.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnSignIn_2.setBounds(188, 261, 89, 23);
				internalFrame_1.getContentPane().add(btnSignIn_2);
			
				tcid = new JTextField();
				tcid.setBackground(new Color(204, 204, 204));
				tcid.setToolTipText("Student ID");
				tcid.setBounds(63, 415, 158, 28);
				internalFrame_1.getContentPane().add(tcid);
				tcid.setColumns(10);
				
				JButton btnNewButton = new JButton("Create Account");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try
						{
							String tid = "select Tea_id from Teacher where Tea_id=?";
							PreparedStatement tps = Con.prepareStatement(tid);
							tps.setString(1, tcid.getText());
							
							ResultSet ltrs = tps.executeQuery();
							
							int s = 0;
							while(ltrs.next())
							{
								s++;
							}
							if(s==1)
							{
								JInternalFrame internalFrame_2 = new JInternalFrame("Create Teacher Login Accaount");
								internalFrame_2.setBackground(new Color(153, 102, 204));
								internalFrame_2.setClosable(true);
								internalFrame_2.setBounds(638, 124, 425, 376);
								frame.getContentPane().add(internalFrame_2);
								internalFrame_2.getContentPane().setLayout(null);
								
								JLabel lblUserName_3 = new JLabel("User Name");
								lblUserName_3.setForeground(new Color(0, 255, 0));
								lblUserName_3.setFont(new Font("Tahoma", Font.BOLD, 14));
								lblUserName_3.setBounds(43, 83, 76, 21);
								internalFrame_2.getContentPane().add(lblUserName_3);
								
								JLabel lblPassword_5 = new JLabel("Password");
								lblPassword_5.setForeground(new Color(0, 255, 0));
								lblPassword_5.setFont(new Font("Tahoma", Font.BOLD, 13));
								lblPassword_5.setBounds(47, 146, 72, 21);
								internalFrame_2.getContentPane().add(lblPassword_5);
								
								tlusa = new JTextField();
								tlusa.setBounds(148, 80, 123, 30);
								internalFrame_2.getContentPane().add(tlusa);
								tlusa.setColumns(10);
								
								tlpass = new JPasswordField();
								tlpass.setBounds(148, 147, 123, 20);
								internalFrame_2.getContentPane().add(tlpass);
								
								JButton btnConfrim = new JButton("Confrim");
								btnConfrim.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
										try
										{
											String ltr = "insert into TeacherLogs(Tuser,Tpass) values(?,?) ";
											PreparedStatement lpst = Con.prepareStatement(ltr);
											
											lpst.setString(1, tlusa.getText());
											lpst.setString(2, tlpass.getText());
											
											lpst.execute();
											JOptionPane.showMessageDialog(null, "Accaount Confrimed");
											lpst.close();
											
										}catch(Exception e18)
										{
											JOptionPane.showMessageDialog(null, "You must Provide a Value for Creating an Account");
										}
									}
								});
								btnConfrim.setBackground(new Color(51, 153, 255));
								btnConfrim.setForeground(new Color(255, 255, 255));
								btnConfrim.setFont(new Font("Sitka Text", Font.BOLD, 13));
								btnConfrim.setBounds(228, 216, 107, 30);
								internalFrame_2.getContentPane().add(btnConfrim);
								internalFrame_2.setVisible(true);
							}
							else if(s<1)
							{
								JOptionPane.showMessageDialog(null, "Inavlid Teacher ID");
							}
							
							ltrs.close();
						}catch(Exception e18)
						{
							JOptionPane.showMessageDialog(null, e18);
						}
						
					}
				});
				btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnNewButton.setForeground(new Color(255, 0, 153));
				btnNewButton.setBounds(250, 415, 130, 28);
				internalFrame_1.getContentPane().add(btnNewButton);
				
				JLabel lblIfYouDont = new JLabel("If you Don't have a account ........");
				lblIfYouDont.setForeground(new Color(153, 255, 204));
				lblIfYouDont.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 12));
				lblIfYouDont.setBounds(20, 328, 178, 21);
				internalFrame_1.getContentPane().add(lblIfYouDont);
				
				JLabel lblProvideTeacherId = new JLabel("Provide Teacher ID to create a Accaount");
				lblProvideTeacherId.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 12));
				lblProvideTeacherId.setForeground(new Color(51, 255, 204));
				lblProvideTeacherId.setBounds(20, 360, 294, 21);
				internalFrame_1.getContentPane().add(lblProvideTeacherId);
				
				
				internalFrame_1.setVisible(true);
			
				
				
			}
		});
		btnTeacher.setBounds(27, 118, 89, 57);
		frame.getContentPane().add(btnTeacher);
		
		JLabel lblWelcomeTo = new JLabel("WELCOME TO ");
		lblWelcomeTo.setForeground(new Color(255, 0, 0));
		lblWelcomeTo.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblWelcomeTo.setBounds(415, 11, 193, 44);
		frame.getContentPane().add(lblWelcomeTo);
		
		JLabel lblNewLabel_1 = new JLabel("AL-Hera Academy School");
		lblNewLabel_1.setFont(new Font("Old English Text MT", Font.PLAIN, 45));
		lblNewLabel_1.setForeground(new Color(204, 0, 102));
		lblNewLabel_1.setBounds(292, 40, 564, 100);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblLoginAs = new JLabel("Login As:");
		lblLoginAs.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLoginAs.setBounds(27, 62, 80, 23);
		frame.getContentPane().add(lblLoginAs);
		
	

	
		
	}

	
}
