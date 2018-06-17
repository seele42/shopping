package dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;


import db.DbClose;
import db.DbConn;
import entity.Goods;
import entity.Login;
import entity.OrderForm;

public class GoodsDao extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public GoodsDao() {
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

		response.setContentType("text/html;chartset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String value = "";
		value = request.getParameter("key");
		int key = Integer.parseInt(value);
		System.out.println("检测是否有key:"+key);
		String keyWord = "";
		keyWord = request.getParameter("keyWord");
		System.out.println("keyWord:"+keyWord);
		try {
			queryGoods(request, response, key,keyWord);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void init() throws ServletException
	{
		// Put your code here
	}
	
	/**
	 * 商品查询
	 * @param request
	 * @param response
	 * @param key 查询的条件/int:4(简单查询)
	 * @return 商品信息数组
	 * @throws Exception 
	 */
	@SuppressWarnings("null")
	public void queryGoods(HttpServletRequest request, HttpServletResponse response,int key,String keyWord)
			throws Exception
	{
	    response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        RowSetFactory factory = RowSetProvider.newFactory();
        CachedRowSet rowSet = factory.createCachedRowSet();
        //行集对象
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Goods goods = null;
		Login username = null;
		OrderForm orderForm = null;
		
		HttpSession session = request.getSession(true);
		username = (Login)session.getAttribute("loginBean");
		goods = (Goods)session.getAttribute("goods");
		orderForm = (OrderForm)session.getAttribute("orderForm");
		ArrayList<Goods> goodsList = new ArrayList<Goods>();
		if (goods==null)
		{
			goods = new Goods();
			session.setAttribute("goods", goods);
		}
		if (username==null)
		{
		    username = new Login();
		    session.setAttribute("username", username);
		}
		if (orderForm==null)
		{
		    orderForm = new OrderForm();
		    session.setAttribute("orderForm", orderForm);
		}
		  //判断用户是否登录
		  String user = "";
          user = username.getUsername();//登录者的用户名
          System.out.println("用户："+user);
          if (user.equals("userNull"))
          {
              out.print("<br>");
              out.print("<center><font color=#008B8B> 请先登录 </font>");
              out.print("<a href=/shopping/jsp/join/login.jsp><font color=red size=6>登录</font></a></center>");
              return;
          }
		
		conn = DbConn.getConn();	

		switch (key)
		{
			case 1:
					//key=1 商品 数量 升序查询
					String sqlGnum = "SELECT * FROM GOODS ORDER BY GNUM ASC";
					try
					{
						pstmt = conn.prepareStatement(sqlGnum);
						rs = pstmt.executeQuery();
						while (rs.next())
						{
							
						}
					} catch (SQLException e)
					{
						e.printStackTrace();
					}finally
							{
								DbClose.allClose(pstmt, rs, conn);
							}
				break;
			case 2:
        			   //key=2 按照关键字查询 商品信息
                      
                        String sqlShowGoodsByKey =  
                        "select * from commodity WHERE commodity_name LIKE ?";
                        try
                        {
                            pstmt = conn.prepareStatement(sqlShowGoodsByKey);
                            pstmt.setString(1, "%"+keyWord+"%"); //%不要写进sqlShowGoodsByKey
                            rs = pstmt.executeQuery();
                            System.out.println("--2查看订单执行数据库操作--");
                            if(rs.next())
                            {
                                rs = pstmt.executeQuery();//重新查询的原因是rs.next时光标偏移后，丢掉记录。
                                
                                rowSet.populate(rs);
                                goods.setRowSet(rowSet);
                                System.out.println("2已经从数据库中获取到值，并塞进行集");
                                request.getRequestDispatcher("/jsp/browse/showGoods.jsp").forward(request, response);
                            }else 
                                {
                                    out.print("<br><br><br><center>");
                                    out.print("<font color=green> 查询出错啦 </font>");
                                    out.print("<a href=/shopping/jsp/browse/searchByKeyWord.jsp><font color=red size=6>查询</font></a>");
                                    out.print("</center>");     
                                }
                        } catch (SQLException e)
                        {
                            System.out.println("key=2查看订单异常："+e);
                            
                        }finally
                                {
                                    System.out.println("查看订单执行关闭流");
                                    DbClose.allClose(pstmt, rs, conn);
                                }
        				break;
			case 3:
                    //key=3 按照登录人查询订单 商品名字+单价+数量
			      
                    String sqlOrder= 
                    "select commodity_name,commodity_price,sum from orderform where username=?";
                    try
                    {
                        pstmt = conn.prepareStatement(sqlOrder);
                        pstmt.setString(1, user);
                        rs = pstmt.executeQuery();
                        System.out.println("--查看订单执行数据库操作--");
                        if(rs.next())
                        {
                            rs = pstmt.executeQuery();//重新查询的原因是rs.next时光标偏移后，丢掉记录。
                            rowSet.populate(rs);
                            goods.setRowSet(rowSet);
                            System.out.println("3已经从数据库中获取到值，并塞进行集");
                            request.getRequestDispatcher("/jsp/order/lookOrderForm.jsp").forward(request, response);
                        }else 
                            {
                                out.print("<br><br><br><center>");
                                out.print("<font color=blue> 订单为空 </font>");
                                out.print("<a href=/shopping/dao/GoodsDao?key=4><font color=red size=6>去购物</font></a>");
                                out.print("</center>");		
                            }
                    } catch (SQLException e)
                    {
                        System.out.println("key=3查看订单异常："+e);
                        
                    }finally
                            {
                                System.out.println("查看订单执行关闭流");
                                DbClose.allClose(pstmt, rs, conn);
                            }
                    break;
			case 4:
					//key=4 浏览商品
					String sqlList= "select * from commodity";
					try
					{
						pstmt = conn.prepareStatement(sqlList);
						rs = pstmt.executeQuery();
						
						System.out.println("--4浏览商品执行数据库操作--");
						if(rs.next())
						{
							rs = pstmt.executeQuery();//重新查询的原因是rs.next时光标偏移后，丢掉记录。
							rowSet.populate(rs);
                            goods.setRowSet(rowSet);
							System.out.println("4浏览商品已经从数据库中获取到值");
							request.getRequestDispatcher("/jsp/browse/showGoods.jsp").forward(request, response);
						}else 
                        {
                                out.print("<br><br><br><center>");
                                out.print("<font color=green> 暂无商品 </font>");
                                out.print("<a href=/shopping/dao/GoodsDao?key=4><font color=red size=6>进入首页</font></a>");
                                out.print("</center>");     
                            }
					} catch (SQLException e)
					{
						e.printStackTrace();
						response.sendRedirect("shopping/jsp/browse/showGoods.jsp");
					}finally
							{
								DbClose.allClose(pstmt, rs, conn);
							}
					break;
			default:
				break;
		}
	}
}
