

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.valves.JDBCAccessLogValve;

/**
 * Servlet implementation class RegisterUser
 */
@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
String fname = request.getParameter("user_data[first_name]");
String lname = request.getParameter("user_data[last_name]");
String pwd = request.getParameter("user_data[password1]");
String email = request.getParameter("user_data[email]");
System.out.print(fname);
System.out.print(lname);
System.out.print(pwd);
String password = MD5.getMD5(pwd);

JDBC jdbc = new JDBC();
Statement st = jdbc.getconn();
ResultSet rs;

/*Request attribute values
 * 1: successfully inserted
 * 2: email already exists
 * 3: insertion unsuccessful 
 * 
 * 
 */
try {
	PrintWriter out=response.getWriter();  
	request.setAttribute("insertStatus", "0");
	rs = st.executeQuery("select * from auctionms_user_mst where email = '"+email+"'");
	if(!rs.isBeforeFirst()) {
		int i =	st.executeUpdate("insert into auctionms_user_mst(first_name,last_name,email,pwd) values ('"+fname+"','"+lname+"','"+email+"','"+password+"')");
		if (i > 0) {
			request.setAttribute("insertStatus", "1");
			
			HttpSession session = request.getSession(true);
			session.setAttribute("user", email);
			request.getRequestDispatcher("index.jsp").forward(request,response);
//			out.print("Successful login"+session.getAttribute("user"));
			//request.getRequestDispatcher("index.jsp").forward(request,response);
			
		}
		else {
			request.setAttribute("insertStatus","3");
			request.getRequestDispatcher("register.jsp").forward(request,response);
		}
	}
	else {
		request.setAttribute("insertStatus", 2);
		request.getRequestDispatcher("register.jsp").forward(request,response);
	}
	

} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	
}
