import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class MedLogin {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MedLogin window = new MedLogin();
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
	public MedLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		
		
		frame = new JFrame();
		frame.setTitle("\u836F\u5E08\u767B\u5F55");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(185, 66, 107, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(185, 121, 107, 21);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u8D26\u53F7");
		lblNewLabel.setBounds(109, 69, 54, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801");
		lblNewLabel_1.setBounds(109, 124, 54, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("\u767B\u5F55");
		btnNewButton.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent arg0) {
				
				int n=0;
				Connection con=null;
				Statement stmt=null;
				String strTemp=" ";
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433; DatabaseName=hospital","sa","sa");
					stmt=con.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Statement st=null;
				if(con!=null)
					System.out.println("连接成功");	
				strTemp="select * from  MLogin ";
				ResultSet rs = null;
				try {
					rs = stmt.executeQuery(strTemp);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					while(rs.next())
					{
						 
						if(rs.getString("Mid").equals (textField.getText()))
					      if(rs.getString("Mpwd").equals(textField_1.getText()))
		    
					           {
					                  n=1;
						        Message message=new Message();
					           }
						
				}
					if(n==0)
					JOptionPane.showMessageDialog(null, "请填写正确的用户名和密码", "提示", JOptionPane.INFORMATION_MESSAGE);
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(266, 184, 91, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u9000\u51FA");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(103, 184, 93, 23);
		frame.getContentPane().add(btnNewButton_1);
	}
}
