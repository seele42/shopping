package control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DbClose;
import db.DbConn;
import entity.Register;

/**
 * 注册相关功能
 * @author felixcui42
 *
 */
public class HandleRegister extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public HandleRegister() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		Register userBean = new Register();
		request.setAttribute("userBean", userBean);
		
		String username = "";
		String userpass = "";
		String again_userpass = "";
		String phone = "";
		String address = "";
		String realname = "";
		
		username = request.getParameter("username").trim();
		userpass = request.getParameter("userpass").trim();
		again_userpass = request.getParameter("again_userpass").trim();
		phone = request.getParameter("phone").trim();
		address = request.getParameter("address").trim();
		realname = request.getParameter("realname").trim();
		
		if (username==null) {
			username = "";
		}
		if (userpass==""|userpass==null) {
			userpass = "error";
		}
		
		
		String regex = "[\\d]{11}";
		if (!(again_userpass.equals(userpass))) {
			userBean.setBackNews("两次密码不一致,请重新输入");
			request.getRequestDispatcher("/jsp/join/register.jsp").forward(request, response);
		}else if (phone!=null&&phone.length()>0&&!phone.matches(regex)) {
						userBean.setBackNews("请正确填写11位手机号");
						request.getRequestDispatcher("/jsp/join/register.jsp").forward(request, response);
				}else {
						if (userpass.length() > 5) {
							Connection        conn  = null;
							PreparedStatement pstmt = null;
							
							conn = DbConn.getConn();
							
							String sql = "INSERT INTO vip(username,userpass,phone,address,realname) VALUES(?,?,?,?,?)";
							
							//在WEB-INF/lib导入数据库jar包，不然会出现空指针异常
							try {
								pstmt = conn.prepareStatement(sql);
								pstmt.setString(1,username);
								pstmt.setString(2,userpass); 
								pstmt.setString(3,phone);
								pstmt.setString(4,address);
								pstmt.setString(5,realname);
								
								int rs = pstmt.executeUpdate();
								if (rs>0) {
									userBean.setBackNews("注册成功");
									request.getRequestDispatcher("/jsp/join/registerSuccess.jsp").forward(request, response);
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
								userBean.setBackNews("该用户名已被注册!"+"<br>");
								request.getRequestDispatcher("/jsp/join/register.jsp").forward(request, response);
							}finally {
								DbClose.close(pstmt, conn);
							}
						}else {
							userBean.setBackNews("密码不合法");
							request.getRequestDispatcher("/jsp/join/register.jsp").forward(request, response);
						}
					}
	}

	public void init() throws ServletException {
	}
}
