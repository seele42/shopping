package goods;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DataByPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public DataByPage() {
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
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	}
	    
	public void init()
	    throws ServletException {
	    }	
}
