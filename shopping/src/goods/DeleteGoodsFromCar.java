package goods;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Login;

/**
 * 处理购物车内的商品
 * @author felixcui42
 *
 */
public class DeleteGoodsFromCar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteGoodsFromCar() {
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
        response.setContentType("text/html;setchar=UTF-8");
        request.setCharacterEncoding("UTF-8");
        int deleteID = -1;
        deleteID =  Integer.parseInt(request.getParameter("ID"));
        System.out.println("删除数组下标为："+deleteID);
        
        HttpSession session = request.getSession(true);
        Login loginBean = (Login)session.getAttribute("loginBean");
        LinkedList<String> car = null;
        car = loginBean.getCar();
        car.remove(deleteID);
        loginBean.setCar(car);
        
        request.getRequestDispatcher("/jsp/shoppingCar/lookShoppingCar.jsp").forward(request, response);
    }

    public void init()
        throws ServletException {
    }
}
