package db;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class DbConnTest {

	@Test
	void testGetConn() {
		String username = "seele42";
		String userpass = "123456";
		String again_userpass = "123456";
		String phone = "";
		String address = "";
		String realname = "";
		System.out.println(username);
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		
		conn = DbConn.getConn();
		
		String sql = "INSERT INTO vip(username,userpass,phone,address,realname) VALUES(?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,username);
			pstmt.setString(2,userpass); 
			pstmt.setString(3,phone);
			pstmt.setString(4,address);
			pstmt.setString(5,realname);
			int rs = pstmt.executeUpdate();
			if (rs > 0) {
				System.out.println("success");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbClose.close(pstmt, conn);
		}
	}
}
