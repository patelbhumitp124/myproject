<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<jsp:directive.include file="header.jsp" />
<%
Statement st = null;
Class.forName("com.mysql.jdbc.Driver");

Connection con=DriverManager.getConnection("jdbc:mysql://localhost/auction", "root", "");
 st=con.createStatement();
ResultSet rs;

HttpSession sessionProfile = request.getSession(false);

String loginStatus = (String)sessionProfile.getAttribute("user");
if(sessionProfile.getAttribute("user") == null || sessionProfile.getAttribute("user") == "0")
{
	sessionProfile.setAttribute("user", "0");
	response.sendRedirect("login.jsp");
//	response.setStatus(response.SC_MOVED_TEMPORARILY);
//	response.setHeader("Location", site); 
}

else
{
	
	if(sessionProfile.getAttribute("product") == null || sessionProfile.getAttribute("product") == "0")
	{
		sessionProfile.setAttribute("product", "0");
	}
	else
	{
		String product = (String)sessionProfile.getAttribute("product");
		if(product.equals("1"))
		{%>
			<script type="text/javascript">
			alert("Product Uploaded");
</script>
		<%
		
		}
		else if(product.equals("2"))
		{%>
			<script type="text/javascript">
			alert("Product details Updated");
</script>
		<%}
		sessionProfile.setAttribute("product", "0");
//		response.setStatus(response.SC_MOVED_TEMPORARILY);
//		response.setHeader("Location", site);
	}
	
	
rs = st.executeQuery("SELECT USER_ID as userId FROM auctionms_user_mst WHERE email = '"+loginStatus+"'");
rs.next();
int userId;
userId = rs.getInt("userId");
%>

<div class="tygh-content clearfix">
    <div class="container-fluid  content-grid">
                    


<div class="row-fluid ">    <div class="span16 breadcrumbs-grid">
                    <div id="breadcrumbs_1">

    <div class="ty-breadcrumbs clearfix">
        <a href="index.jsp" class="ty-breadcrumbs__a">Home</a><span class="ty-breadcrumbs__slash">/</span><span class="ty-breadcrumbs__current">Wish list content</span>
    </div>

<!--breadcrumbs_1--></div>
            </div>
</div>                


<div class="row-fluid ">    <div class="span16 main-content-grid">                  
<div class="mainbox-container clearfix">
    <h2 class="ty-mainbox-title">
        Products you Bid on
    </h2>
                <div class="ty-mainbox-body">
    <script type="text/javascript" src="./Wish list content_files/product_image_gallery.js"></script>

<div class="grid-list">

<%
		String query = "SELECT PRD.PRODUCT_ID AS prdId , PRD.PRODUCT_TITLE AS prdTitle,  PRD.IMAGE AS imagePath, BID.bid_amount AS bidAmt FROM auctionms_bid_mst BID INNER JOIN auctionms_product_mst PRD ON PRD.PRODUCT_ID = BID.product_id INNER JOIN auctionms_user_mst USERS ON BID.user_id = USERS.USER_ID where BID.user_id='"+userId+"'";
		System.out.println(query);
		rs = st.executeQuery(query);
		int prdId;
		String prdTitle;
		float bidAmt;
		String imgPath;
		while(rs.next())
		{
			prdId = rs.getInt("prdId");
			prdTitle = rs.getString("prdTitle");
			bidAmt = rs.getFloat("bidAmt");
			imgPath = rs.getString("imagePath");		
		%>

<div class="ty-column4">    
<div class="ty-grid-list__item ty-quick-view-button__wrapper">

<div class="ty-grid-list__image">
                                            
<div class="ty-center-block">
    <div class="ty-thumbs-wrapper owl-carousel cm-image-gallery owl-one-theme" data-ca-items-count="1" data-ca-items-responsive="true" id="icons_230" style="opacity: 1; display: block; visibility: visible; position: relative;">
        <div class="owl-wrapper-outer">
		<div class="owl-wrapper" style="width: 1638px; left: 0px; display: block;">
			<div class="owl-item" style="width: 273px;">
				<div class="cm-gallery-item cm-item-gallery">
                    <a href="<%=request.getContextPath()%>/product.jsp?prdId=<%=prdId %>">
						<img class="ty-pict" id="det_img_230" src="<%=imgPath %>" alt="" title="" style="height:150px;width:150px;">
					</a>
				</div>
			</div>
		</div>
		</div>
	</div>
</div>
</div>

<div class="ty-grid-list__item-name">
	<a href="<%=request.getContextPath()%>/product.jsp?prdId=<%=prdId %>" class="product-title" title="2011 Pit Boss"><%=prdTitle %></a>    
</div>

<div class="ty-grid-list__item-name">
	<a href="<%=request.getContextPath()%>/product.jsp?prdId=<%=prdId %>" class="product-title" title="2011 Pit Boss" style="color:#009688;">Your Bid: <%=bidAmt %></a>    
</div>

</div>
</div>
		<%} %>


    </div>

    

</div>
    </div>
	<div class="mainbox-container clearfix">
    <h2 class="ty-mainbox-title">
        Your Products
    </h2>
                <div class="ty-mainbox-body">
    <script type="text/javascript" src="./Wish list content_files/product_image_gallery.js"></script>

<div class="grid-list">


<%
		String query2 = "SELECT PRD.PRODUCT_ID AS prdId , PRD.PRODUCT_TITLE AS prdTitle,  PRD.IMAGE AS imagePath FROM auctionms_product_mst PRD where PRD.SELLER_ID='"+userId+"'";
		System.out.println(query2);
		rs = st.executeQuery(query2);
		while(rs.next())
		{
			prdId = rs.getInt("prdId");
			prdTitle = rs.getString("prdTitle");
			imgPath = rs.getString("imagePath");		
		%>

<div class="ty-column4">    
<div class="ty-grid-list__item ty-quick-view-button__wrapper">

<div class="ty-grid-list__image">
                                            
<div class="ty-center-block">
    <div class="ty-thumbs-wrapper owl-carousel cm-image-gallery owl-one-theme" data-ca-items-count="1" data-ca-items-responsive="true" id="icons_230" style="opacity: 1; display: block; visibility: visible; position: relative;">
        <div class="owl-wrapper-outer">
		<div class="owl-wrapper" style="width: 1638px; left: 0px; display: block;">
			<div class="owl-item" style="width: 273px;">
				<div class="cm-gallery-item cm-item-gallery">
                    <a href="<%=request.getContextPath()%>/product.jsp?prdId=<%=prdId %>">
						<img class="ty-pict" id="det_img_230" src="<%=imgPath %>" alt="" title="" style="height:150px;width:150px;">
					</a>
				</div>
			</div>
		</div>
		</div>
	</div>
</div>
</div>


<div class="ty-grid-list__item-name">
	<a href="<%=request.getContextPath()%>/product.jsp?prdId=<%=prdId %>" class="product-title" title="2011 Pit Boss"><%=prdTitle %></a>    
</div>

<div>
	<a href="<%=request.getContextPath()%>/edit_product.jsp?prdId=<%=prdId %>"><button  class="ty-btn__secondary ty-btn" style="width:100%">Edit product</button></a>
</div>
</div>
</div>
		<%} %>


    </div>

    

</div>
    </div>
            </div>
</div>
</div>
</div>

<jsp:directive.include file="footer.jsp" />
<%}%>