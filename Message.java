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
        //��������  
        columnNames.add("����id");  
        columnNames.add("��������");  
        columnNames.add("ҩ��");  
        columnNames.add("����");
        columnNames.add("�۸�");
        
        Vector rowData = new Vector();  
        //rowData���Դ�Ŷ���,��ʼ�����ݿ���ȡ  
        
        
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
			
			System.out.println("���ӳɹ�");
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
			        
                 //���뵽rowData  
                 rowData.add(hang);  
			}
			
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		final DefaultTableModel tmd=new DefaultTableModel(rowData,columnNames);

		 final JTable  jt = new JTable(tmd);
		
		
		JButton btnNewButton_1 = new JButton("��ҩ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				DefaultTableModel tableModel = (DefaultTableModel) jt.getModel();
				int selectedRow = jt.getSelectedRow();//���ѡ���е�����   
				      
                if(selectedRow!=-1)  //����ѡ����
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
  					//�������ݿ�����
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
			               
							e.printStackTrace();//��ɾ��һ����executeUpdate����
							
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
 						e.printStackTrace();//��ɾ��һ����executeUpdate����
 					}
 					
 					try {
 						sm.close();
 						con.close();
 						//con.close();
 					} catch (SQLException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					}
 					
 					  
                	 tmd.removeRow(selectedRow);  //ɾ����
                	 JOptionPane.showMessageDialog(null, "��ҩ�ɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
               	 
				}
             else
            	 JOptionPane.showMessageDialog(null, "ҩ���޸���ҩ��ҩ������", "����", JOptionPane.INFORMATION_MESSAGE);
              
              
			
                }
				 else
                    JOptionPane.showMessageDialog(null, "��ѡ��Ҫ��ҩ�Ĳ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			
		}});
		
		btnNewButton_1.setBounds(330, 220, 93, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		
		
		
		JScrollPane JSP= new JScrollPane(jt); 
		frame.add(JSP); 		
		frame.setVisible(true);

		
	}

}	