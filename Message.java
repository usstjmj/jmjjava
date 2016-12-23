import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Message {

	private JFrame frame;

	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Text1 window = new Text1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	*/
	public Message() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Vector columnNames=new Vector();  
        //设置列名  
        columnNames.add("病人id");  
        columnNames.add("病人姓名");  
        columnNames.add("药名");  
        columnNames.add("数量");
        columnNames.add("价格");
        
        Vector rowData = new Vector();  
        //rowData可以存放多行,开始从数据库里取  
        
        
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
		
		if(con!=null)
			
			System.out.println("连接成功");
		strTemp="select * from  Charge ";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(strTemp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while( rs.next())
			{
				 Vector hang=new Vector();  
                 hang.add(rs.getString("id"));  
                 hang.add(rs.getString("name"));  
                 hang.add(rs.getString("MName"));  
                 hang.add(rs.getInt("num"));  
                 hang.add(rs.getFloat("price"));  
			        
                 //加入到rowData  
                 rowData.add(hang);  
			}
			
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		final DefaultTableModel tmd=new DefaultTableModel(rowData,columnNames);

		 final JTable  jt = new JTable(tmd);
		
		
		JButton btnNewButton_1 = new JButton("发药");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				DefaultTableModel tableModel = (DefaultTableModel) jt.getModel();
				int selectedRow = jt.getSelectedRow();//获得选中行的索引   
				      
                if(selectedRow!=-1)  //存在选中行
                {
                	 String Medicine=(String) tableModel.getValueAt(selectedRow, 2);
                	 int num=(int) tableModel.getValueAt(selectedRow, 3);
                	 String id=(String) tableModel.getValueAt(selectedRow, 0);
                	 
                	 Connection con=null;
                	 try {
  						Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
  					} catch (ClassNotFoundException e) {
  						// TODO Auto-generated catch block
  						e.printStackTrace();
  					}
  					//建立数据库连接
  					try {
  						con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433; DatabaseName=Hospital","sa","sa");
  					} catch (SQLException e) {
  						// TODO Auto-generated catch block
  						e.printStackTrace();
  					}
                	 Statement st=null;
			         	try {
			         		 st=con.createStatement();
			         	} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  
			         	
			        	int r=50;
						String sq="update medicine set num=num-'"+num+"' where MName='"+Medicine+"'";
						try {
							r=st.executeUpdate(sq);
							
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
			               
							e.printStackTrace();//增删改一般用executeUpdate方法
							
						}
						
						
				
						
						try {
							st.close();
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                	 
                	 
                	 
             if(r!=0){ 	 
                	 
 					
 					Statement sm=null;
 					try {
 		         		 sm=con.createStatement();
 		         	} catch (SQLException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					} 
 				
 					String s="delete from Charge where id='"+id+"'";
 					try {
 						sm.executeUpdate(s);
 					} catch (SQLException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();//增删改一般用executeUpdate方法
 					}
 					
 					try {
 						sm.close();
 						con.close();
 						//con.close();
 					} catch (SQLException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					}
 					
 					  
                	 tmd.removeRow(selectedRow);  //删除行
                	 JOptionPane.showMessageDialog(null, "发药成功", "提示", JOptionPane.INFORMATION_MESSAGE);
               	 
				}
             else
            	 JOptionPane.showMessageDialog(null, "药库无该种药或药量不足", "警告", JOptionPane.INFORMATION_MESSAGE);
              
              
			
                }
				 else
                    JOptionPane.showMessageDialog(null, "请选择要发药的病人", "提示", JOptionPane.INFORMATION_MESSAGE);
			
		}});
		
		btnNewButton_1.setBounds(330, 220, 93, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		
		
		
		JScrollPane JSP= new JScrollPane(jt); 
		frame.add(JSP); 		
		frame.setVisible(true);

		
	}

}	