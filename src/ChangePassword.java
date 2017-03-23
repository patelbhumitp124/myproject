
import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class changePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
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
		
		String currentPassword = request.getParameter("user_data[current_password]");
		String currentPwd = MD5.getMD5(currentPassword);
		String pwd = request.getParameter("user_data[password1]");
		String password = MD5.getMD5(pwd);
		
		System.out.println(currentPassword);
		System.out.println(currentPwd);

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
			//request.setAttribute("passChanged", "0");
			
			HttpSession sessChangePwd = request.getSession(false);
			String email;
			email = (String)sessChangePwd.getAttribute("user");
			
			rs = st.executeQuery("select pwd AS currentpwd from auctionms_user_mst where email = '"+email+"'");
			rs.next();
			System.out.println(rs.getString("currentpwd"));
			if(currentPwd.equals(rs.getString("currentpwd")))
			{
				if(currentPwd.equals(password))
				{
					sessChangePwd.setAttribute("passChanged","4");
					response.sendRedirect("change_password.jsp");	
				}
				else
				{
				int i = st.executeUpdate("update auctionms_user_mst set pwd='"+password+"' where email = '"+email+"'");
				if(i>0)
				{
					sessChangePwd.setAttribute("passChanged","1");
					response.sendRedirect("index.jsp");
				}
				else
				{
					sessChangePwd.setAttribute("passChanged","2");
					response.sendRedirect("change_password.jsp");
				}
				}
			}
			else
			{
					sessChangePwd.setAttribute("passChanged","3");
					response.sendRedirect("change_password.jsp");
			}
			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
	}

	
	
}
