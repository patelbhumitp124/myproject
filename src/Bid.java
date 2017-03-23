import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class Bid
 */
@WebServlet("/Bid")
public class Bid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bid() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String user = request.getParameter("user");
		String prdId = request.getParameter("prdId");
		String insertUpdate = request.getParameter("insertUpdate");
		JDBC jdbc = new JDBC();
		Statement st = jdbc.getconn();
		HttpSession sess = request.getSession(false);
		if(sess.getAttribute("user")==null || sess.getAttribute("user")=="0")
		{	
			response.sendRedirect("login.jsp");
		}
		else
		{
		String user = sess.getAttribute("user").toString();
		
		int user_id=0;
		float bid = Float.parseFloat(request.getParameter("bid").toString());
		ResultSet rs;
		try {
			String query = "select user_id as userId from auctionms_user_mst where email='"+user+"'";
			rs = st.executeQuery(query);
			if(rs.next())
			{
				user_id = rs.getInt("userId");
			}
			rs = st.executeQuery("select min_bid as minBid from auctionms_product_mst where product_id ="+prdId);
			if(rs.next())
			{
				HttpSession session = request.getSession(false); // reuse existing
				float minBid = rs.getFloat("minBid");
				if (bid < minBid)
				{
					
					session.setAttribute("bid", "less");
					response.sendRedirect("product.jsp?prdId="+prdId);
				}
				else
				{
					String sql=null;
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
					if(insertUpdate.equals("insert"))
					{
					//System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48
					 sql = "insert into auctionms_bid_mst (product_id,bid_amount,user_id,active_flag,dateTime) values ('"+prdId+"','"+bid+"','"+user_id+"','Y','"+dateFormat.format(date)+"')";
					 session.setAttribute("bid","success");
					}
					else if(insertUpdate.equals("update"))
					{
					 sql = "update auctionms_bid_mst set bid_amount='"+bid+"',dateTime='"+dateFormat.format(date)+"' where user_id = '"+user_id+"' and product_id = '"+prdId+"'";
					 session.setAttribute("bid","updateSuccess");
					}
					st.executeUpdate(sql);
					
					
					response.sendRedirect("product.jsp?prdId="+prdId);
					
				}
		
			}
			
		
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
