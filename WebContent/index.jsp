<%-- <META HTTP-EQUIV="refresh" CONTENT="<%=session.getMaxInactiveInterval()%>URL=login.jsp"/> --%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%
HttpSession sess1 = request.getSession(false);
String passChanged=null;
String alreadyLoggedIn;
System.out.print(request.getAttribute("alreadyLoggedIn"));

if(sess1.getAttribute("alreadyLoggedIn") == null)
{
	
	alreadyLoggedIn = "false";
}
else
{
	
	alreadyLoggedIn = (String)sess1.getAttribute("alreadyLoggedIn");
	if(alreadyLoggedIn.equals("true"))
	{
		%><script type="text/javascript">alert("You are already logged in")</script> <%
				sess1.setAttribute("alreadyLoggedIn", null);
	}
}
if(sess1.getAttribute("logout")==null || sess1.getAttribute("logout") == "0")
{
	
}
else
{
if(sess1.getAttribute("logout").equals("1"))
{
	%><script type="text/javascript">alert("You have been logged out")</script> <%
			sess1.setAttribute("logout", "0");
}
}
passChanged = (String)sess1.getAttribute("passChanged");
if(passChanged == "1")
{
	%><script>
		alert("Password changed");
	</script>
	<%
	sess1.setAttribute("passChanged","0");
}

%>

<jsp:directive.include file="header.jsp" />
<!-- <script>
 //   setTimeout("window.location.href='login.html';",time);
 //example:

    setTimeout("window.location.href='login.jsp';",120); // after 2 minutes
</script> -->
<div class="tygh-content clearfix">
    <div class="container-fluid  ">
                    


<div class="row-fluid ">    <div class="span16 ">
                    <div id="breadcrumbs_1">

    <div class="ty-breadcrumbs clearfix">
        <a href="index.jsp" class="ty-breadcrumbs__a">Home</a><span class="ty-breadcrumbs__slash">/</span><span class="ty-breadcrumbs__current">Auction</span>
    </div>

<!--breadcrumbs_1--></div>
            </div>
</div>                
<%System.out.print("This is "+session.getAttribute("user")); %>

<div class="row-fluid ">    <div class="span16 ">
                    <div class="ty-pagination-container cm-pagination-container" id="pagination_contents">

    
    

    



<div class="grid-list">

			 
		</div>
		<%
		String searchPrd = null;		  
		Statement st = null;
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/auction", "root", "");
		 st=con.createStatement();
		ResultSet rs;
		if(request.getParameter("search_input")==null || request.getParameter("search_input")=="")
		{
		rs = st.executeQuery("SELECT PRD.PRODUCT_ID AS prdId , PRD.PRODUCT_TITLE AS prdTitle, PRD.PRODUCT_PRICE AS prdPrice, PRD.MIN_BID  AS minBid, USERS.FIRST_NAME AS firstName , PRD.IMAGE AS imagePath FROM auctionms_product_mst PRD , auctionms_user_mst USERS WHERE PRD.SELLER_ID = USERS.USER_ID ORDER BY prd.uploaded_date DESC");
		}
		else
		{
			
			searchPrd = request.getParameter("search_input").toString();
			rs = st.executeQuery("SELECT PRD.PRODUCT_ID AS prdId , PRD.product_desc AS prdDesc, PRD.PRODUCT_TITLE AS prdTitle, PRD.PRODUCT_PRICE AS prdPrice, PRD.MIN_BID  AS minBid, USERS.FIRST_NAME AS firstName , PRD.IMAGE AS imagePath FROM auctionms_product_mst PRD , auctionms_user_mst USERS WHERE PRD.SELLER_ID = USERS.USER_ID AND (prd.product_title LIKE '%"+searchPrd+"%' OR prd.product_desc LIKE '%"+searchPrd+"%') ORDER BY prd.`uploaded_date` DESC");
			
		}
		int prdId;
		String prdTitle;
		float prdPrice;
		float minBid;
		String 
		firstName;
		String imgPath;
		while(rs.next())
		{
			 prdId = rs.getInt("prdId");
			 prdTitle = rs.getString("prdTitle");
			prdPrice = rs.getFloat("prdPrice");
			 minBid = rs.getFloat("minBid");
			 firstName = rs.getString("firstName");
			 imgPath = rs.getString("imagePath");		
		%>
	
	<div class="ty-column5">
	<div class="center" style="text-align: center;border: 1px solid #ccc;background-color:#fff;padding: 8px 10px;margin: 10px;overflow: hidden;-webkit-box-shadow:0 0 5px rgba(204, 204, 204, 0.6);box-shadow:0 0 5px rgba(204, 204, 204, 0.6);transition: all 120ms cubic-bezier(0, 0, 0.58, 1) 0s;">
        <p>
        <a href="<%=request.getContextPath()%>/product.jsp?prdId=<%=prdId%>" class="product-title"><%=prdTitle%></a>
        </p>

        <a href="<%=request.getContextPath()%>/product.jsp?prdId=<%=prdId%>"><img class="ty-pict" src=<%=imgPath%> alt="" title="" style="width:150px;height:150px;"></a>
        <p class="center " style="color:#999999;">Retail:  <strike><span class="auction_retail_price">$</span><span class="auction_retail_price"><%=prdPrice%></span></strike></p>

        <div class="center" id="auction_block_update_auction_list_27491">
            <div class="center" id="auction_bg_color_27491_auction_list">
                <span class="price auction_current_price"><span class="price">$</span><span class="price"><%=minBid%></span></span>
            </div>
            <p style="margin-top:0px;" class="auction_username"><%=firstName%></p>
        </div>
       </span>
    </div>
</div>
	<%} %>		
	


    <!--pagination_contents--></div>
            </div>
</div>
</div>
</div>

<jsp:directive.include file="footer.jsp" />

