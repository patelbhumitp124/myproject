

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
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
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class EditProduct
 */
@WebServlet("/EditProduct")
public class EditProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int THRESHOLD_SIZE 	= 1024 * 1024 * 3; 	// 3MB
	private static final int MAX_FILE_SIZE 		= 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE 	= 1024 * 1024 * 50; // 50MB	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String title = request.getParameter("product_data[title]");
		String description = request.getParameter("product_data[description]");
		String mrp = request.getParameter("product_data[mrp]");
		String minBid = request.getParameter("product_data[minimum_bid]");
		String imgPath = null;
		String sql=null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		System.out.print(title);
		System.out.print(description);
		System.out.print(mrp);
		System.out.print(minBid);

		JDBC jdbc = new JDBC();
		Statement st = jdbc.getconn();
		ResultSet rs;
		HttpSession sess = request.getSession(false);
		if(sess.getAttribute("user")==null || sess.getAttribute("user")=="0")
		{	
			response.sendRedirect("login.jsp");
		}
		else
		{
		String user = sess.getAttribute("user").toString();
		
		int seller_id=0;
		try {
			String query = "select user_id as userId from auctionms_user_mst where email='"+user+"'";
			rs = st.executeQuery(query);
			if(rs.next())
			{
				seller_id = rs.getInt("userId");
			}

			if (!ServletFileUpload.isMultipartContent(request)) {
			PrintWriter writer = response.getWriter();
			writer.println("Request does not contain upload data");
			writer.flush();
			return;
			}

		// configures upload settings
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(THRESHOLD_SIZE);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(MAX_FILE_SIZE);
		upload.setSizeMax(MAX_REQUEST_SIZE);
		

		
		String UPLOAD_DIRECTORY = "images";
		//String uploadPath = (request.getContextPath() + File.separator +"WebContent" + File.separator + UPLOAD_DIRECTORY);
		String uploadPath = getServletContext().getRealPath("")	+ File.separator + UPLOAD_DIRECTORY;
		// creates the directory if it does not exist
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		/*Request attribute values
		 * 1: successfully inserted
		 * 2: unsuccessfully inserted
		 * 
		 * 
		 */
			Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
		//	InputStream filecontent = filePart.getInputStream();
			String fileName = getFileName(filePart);
			OutputStream out = null;
		    InputStream filecontent = null;
		    final PrintWriter writer = response.getWriter();
		
		    
		    out = new FileOutputStream(new File(uploadPath + File.separator + fileName));
	        filecontent = filePart.getInputStream();

	        int read = 0;
	        final byte[] bytes = new byte[1024];

	        while ((read = filecontent.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
	        writer.println("New file " + fileName + " created at " + uploadPath);
	     imgPath = UPLOAD_DIRECTORY + File.separator + File.separator + fileName;
				String query1 = "insert into auctionms_product_mst (product_title,product_desc,product_price,min_bid,seller_id,uploaded_date,image) values ('"+title+"','"+description+"','"+mrp+"','"+minBid+"','"+seller_id+"','"+dateFormat.format(date)+"','"+imgPath+"') ";
	     int i = st.executeUpdate(query1);
	     if (i > 0) {
	    	 sess.setAttribute("productUpload","success");
	    	 response.sendRedirect("upload_product.jsp");
	     }
	     
	     
	     
	     
		}

		 catch (Exception e) {
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
