package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
		
		//Connection ������ ���̽� �����ϰ� ���ִ� �ϳ��� ��ü
		private Connection conn;
		private PreparedStatement pstmt;
		//�ϳ��� ������ ���� �� �ִ� �ϳ��� ��ü
		private ResultSet rs;
		
		//ctrl +shift + o �� ���� �ܺ� ���̺귯���� �־��ݴϴ�
		
		//�����ڸ� ������ݴϴ�.
		public UserDAO() { //������ mysql�� ������ �� �ֵ��� ������
			try {
				//localhost:�� �츮 ��ǻ�Ϳ� ��ġ�� mysql ���� ��ü�� �ǹ��ϰ� bbs�� �츮�� ���� ���̺� �̸��Դϴ�.
				String dbURL = "jdbc:mysql://localhost:43306/jspboard?serverTimezone=UTC";
				String dbID ="root"; //db ����
				String dbPassword ="javamysql"; //db ��й�ȣ
				
	            //driver�� mysql�� ������ �� �ֵ��� �����ִ� �ϳ��� ���̺귯�� �Ű�ü
	            Class.forName("com.mysql.jdbc.Driver"); 
	            
				conn=DriverManager.getConnection(dbURL, dbID, dbPassword);
			}catch(Exception e) {
				e.printStackTrace(); //������ �������� ���
			}
			
		}
	    // ������ �α����� �õ��ϴ� �Լ�
		public int login(String userID, String userPassword) { // ���̵�� ��й�ȣ�� �޾ƿ�
			//������ db�� �Է��� sql��
			String SQL = "SELECT userPassword FROM USER WHERE userID= ?";
			try {
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1,  userID);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					if(rs.getString(1).equals(userPassword)) 
						return 1; // �α��� ����
					else
						return 0; //��й�ȣ ����ġ
					
				}
				return -1; //���̵� ����
			}catch(Exception e) {
				e.printStackTrace();
			}
			return -2; // �����ͺ��̽� ������ �ǹ��մϴ�.
		}
	}

