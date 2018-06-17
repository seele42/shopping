<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/navbar.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询商品</title>
</head>
<body>
    <div class="search-box" align="center">
	    <form class="search-form" action="/shopping/dao/GoodsDao?key=2" target="_self" id="search-form" method="post">
			<input type="text" class="search-text" name="keyWord" id="search_input" autocomplete="off" placeholder="鞋"/>
			<input type="hidden" name="key" value="2" />
			<input type="submit" class="search-button" value="查询"/>
	    </form>
	</div>
</body>
</html>