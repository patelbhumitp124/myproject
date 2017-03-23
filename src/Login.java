

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */ 
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*Request attribute values
		 * 0: successfully logged in
		 * 100: login failed
		 */
	String user = request.getParameter("user_data[email]");
	String pwd = request.getParameter("user_data[password1]");

	String password = MD5.getMD5(pwd);
	
	JDBC jdbc = new JDBC();
	Statement st = jdbc.getconn();
	ResultSet rs;
	HttpSession session = request.getSession(true);
	try {
		rs = st.executeQuery("select * from auctionms_user_mst where email = '"+user+"' and pwd = '"+password+"'");

	if (rs.isBeforeFirst())
	{
		
		 // reuse existing
		session.setAttribute("user", user);
	//	session.setMaxInactiveInterval(15); // 30 seconds
		System.out.print("login");
		response.sendRedirect("index.jsp");
		
	}
	else
	{
		session.setAttribute("loginStatus", "100");
		response.sendRedirect("login.jsp");
	//	request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}

}
