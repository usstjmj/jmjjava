import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Medicine {

	
	private String user;
	private String password;
	
	
	public Medicine()
	{
			
		JFrame frm=new JFrame("药师登录");
		frm.setLayout(new BorderLayout());
		
			
		JPanel top=new JPanel();
		frm.getContentPane().add(top, BorderLayout.NORTH);
		
		JLabel jl=new JLabel("欢迎来到药师系统/n请填写登录信息");
		top.add(jl);
		
		JPanel contentPane=new JPanel();  
		
		
	 
		frm.setContentPane(contentPane);  
		  JPanel pane1=new JPanel();  
	        contentPane.add(pane1);  
	        JPanel pane2=new JPanel();  
	        contentPane.add(pane2);  
	        JLabel label1=new JLabel("用户名：");  
	          
	        JTextField textField1=new JTextField();  
	        textField1.setColumns(10);  
	        pane1.add(label1);  
	        pane1.add(textField1);  
	        JLabel label2=new JLabel("密码：");  
	        JTextField textField2=new JTextField();  
	        textField2.setColumns(10);  
	        pane2.add(label2);  
	        pane2.add(textField2);  
		
		JButton button=new JButton("登录");
        ButtonHandler btnHandler=new ButtonHandler();
		button.addActionListener(btnHandler);
		frm.add(button);
		 this.user=textField1.getText();
		 this.password=textField2.getText();
		frm.setVisible(true);
		frm.setBounds(500, 300, 500,300);
		
	}
	
	String getuser()
	{
		return user;	
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	void setuser(String user)
	{
		this.user=user;
	}
			
}

class ButtonHandler implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433; DatabaseName=hospital","sa","sa");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Statement st=null;
		if(con!=null)
			System.out.println("连接成功");
			String string=null;
		Medicine medicine = null;
		string=hospital.executeQuery("SELECT medicine.getuser() from MLogin") ;
		System.out.println(string);
		/*if(string!=null&&("select * from MLogin where Mid='string'"==medicine.getPassword()))
			System.out.print("登录成功");
		else 
			System.out.print("登录失败");
		*/
	}
	
}






