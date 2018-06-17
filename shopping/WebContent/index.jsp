<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/navbar.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <base href="<%=basePath%>">
<title>首页</title>

<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
</head>
<body>
    <jsp:useBean id="loginBean" class="entity.Login" scope="session"/>
    <% request.setCharacterEncoding("UTF-8"); %>
    <ul class="user">
   			<li>
   			
   			<%	   
			    String str = null;
				str = loginBean.getUsername();
				if(str.equals("userNull")||str==null) {
	                HttpSession s= request.getSession(true);
	                s.invalidate();
            %>
    	        <a href="<%= path %>/jsp/join/login.jsp">登录</a>&nbsp&nbsp&nbsp<a href="jsp/join/register.jsp">注册</a>
   			<%
   				    return;
   			}
   			%>
  			    <dl>
  					<dt>
    				 <a>欢迎您,<b><font color="red"><%= str %></font></b></a>
    				 <a href="<%= path %>/control/HandleExit"><font color="#CDC9C9">退出</font></a>
  					</dt>
  				</dl>
   			</li>
    </ul>
    		<%  
    		    String value = null;
                value = request.getParameter("first");
                if(value!=null && "FP".equals(value)) {
            %>
                    <center>
                    <br><br><br>
                        <h1><p>本项目实现完成注册、登陆、浏览商品、购物车、查询商品、退出等模块。
                            <p>涉及技术内容：sql\JDBC\Javase\HTML\CSS\JSP\Servlet
                        </h1>
                    </center>
                <%}%>
</body>
</html>