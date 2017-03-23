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
int prdId;
if(request.getParameter("prdId") == null)
{
	
}
else
{%>
<%

HttpSession sessionProduct = request.getSession(false); // reuse existing
if(sessionProduct.getAttribute("bid")==null)
{
   String bid = null;	
}
else
{
	if(sessionProduct.getAttribute("bid").toString().equals("success"))
	{%>
	<script type="text/javascript">alert("Bid successful")</script>
	<%}
	else if(sessionProduct.getAttribute("bid").toString().equals("less"))
	{%>
		<script type="text/javascript">alert("Please enter proper bid value");</script>
	<%
	}
	else if(sessionProduct.getAttribute("bid").toString().equals("updateSuccess"))
	{%>
		<script type="text/javascript">alert("Bid updated succesfully");</script>
	<%
	}
	sessionProduct.setAttribute("bid", null);
}
	
	
   prdId = Integer.parseInt(request.getParameter("prdId").toString());

	
	rs = st.executeQuery("SELECT DATE_FORMAT(UPLOADED_DATE,'%e %b %Y') AS date , PRD.PRODUCT_ID AS prdId, PRD.SELLER_ID AS sellerId, PRD.PRODUCT_DESC AS prdDesc , PRD.PRODUCT_TITLE AS prdTitle, PRD.PRODUCT_PRICE AS prdPrice, PRD.MIN_BID  AS minBid, USERS.FIRST_NAME AS firstName , PRD.IMAGE AS imagePath FROM auctionms_product_mst PRD , auctionms_user_mst USERS WHERE  PRD.SELLER_ID = USERS.USER_ID AND PRD.PRODUCT_ID = "+prdId);
	
	String prdTitle;
	float prdPrice;
	float minBid;
	String firstName;
	String imgPath;
	String prdDesc;
	String date;
	int sellerId=0;
	int userId1=0;
	
%>



<div class="tygh-content clearfix">
    <div class="container-fluid  content-grid">
                    


<div class="row-fluid ">    <div class="span16 breadcrumbs-grid">
                    <div id="breadcrumbs_1">
<%
while(rs.next())
{

	 prdId = rs.getInt("prdId");
	 prdTitle = rs.getString("prdTitle");
	prdPrice = rs.getFloat("prdPrice");
	 minBid = rs.getFloat("minBid");
	 firstName = rs.getString("firstName");
	 imgPath = rs.getString("imagePath");		
	prdDesc = rs.getString("prdDesc");
	date = rs.getString("date");
	sellerId = rs.getInt("sellerId");

%>
    <div class="ty-breadcrumbs clearfix">
        <a href="<%=request.getContextPath() %>/index.jsp" class="ty-breadcrumbs__a">Home</a><span class="ty-breadcrumbs__slash">/</span><span class="ty-breadcrumbs__current"><%=prdTitle %></span>	
		

    </div>

<!--breadcrumbs_1--></div>
            </div>
</div>                


<div class="row-fluid ">    <div class="span12 main-content-grid">
<script type="text/javascript" src="js/exceptions.js"></script>
<div class="ty-product-block">
    <div class="ty-product-block__wrapper clearfix">
<div class="ty-product-block__img-wrapper">
<div class="ty-product-block__img cm-reload-66" id="product_images_66_update">
<div class="ty-product-img cm-preview-wrapper">
<a id="det_img_link_6656f7b441cd583_394" data-ca-image-id="preview[product_images_6656f7b441cd583]" class="cm-image-previewer cm-previewer ty-previewer" href="<%=imgPath%>" title=""><img class="ty-pict" src="<%=imgPath%>" alt="" title="" data-cloudzoom="zoomImage: &quot;<%=imgPath %>&quot;"><span class="ty-previewer__icon hidden-phone"></span></a>
</div>
<script type="text/javascript" src="js/magnific.previewer.js"></script>
<script type="text/javascript" src="js/product_image_gallery.js"></script>
<script type="text/javascript" src="js/cloudzoom.js"></script>
<script type="text/javascript">
(function(_, $) {

    $.ceEvent('on', 'ce.commoninit', function(context) {
        context.find('.cm-previewer').each(function(){

            var elm = $(this).find('img');

            if(elm.data('CloudZoom') == undefined) {
                elm.attr('data-cloudzoom', 'zoomImage: "' + $(this).prop('href') + '"')
                    .CloudZoom({
                        tintColor: '#ffffff',
                        tintOpacity: 0.6,
                        animationTime: 200,
                        easeTime: 200,
                        zoomFlyOut: true,
                        zoomSizeMode: 'zoom',
                        captionPosition: 'bottom',
                                                zoomPosition: '3',
                        autoInside: 767
                });
            }

        });
    });

}(Tygh, Tygh.$));
</script>

                        <!--product_images_66_update--></div>
 </div>
		<div class="product-info">
        <div class="product-info-detail">
        <h1 class="ty-product-block-title"><%=prdTitle %></h1>
		 <div class="brand">
    
                        </div>
            <div id="auction_firework">
                <div class="clear2" id="auction_detail_update_27491">
                    <div class="auction_details_top">
                        <div class="auction_details_top_1">
                            <div class="auction_details_top_2"></div>
                            <div class="auction_details_top_3"> </div>
                            <div class="clear-both"></div>
                        </div>
                        <div class="auction_details_current_price" id="auction_bg_color_27491_detail"><span class="price-num">$</span><span class="price-num"><%=minBid %></span></div>
                        <div class ="clear-both"></div>
                    </div>
                    <p class="auction_time_left" id="auction_time_color_27491_detail"style="padding-left: 103px;">
                        <%=date %>
                    </p>
                </div>
<div id ="bidUpdateDiv">
                <p class="center" style="margin-top:15px;">
                    
                    
                    <form method="get" action="<%=request.getContextPath()%>/Bid">
                    <input type="hidden" name="prdId" value="<%=prdId%>">

                   <input type="text" name="bid" id="bid" class="input-text auction_bid cm-numeric" PlaceHolder="<%=minBid %>"  style="font-size:30px;height:45px;mrg" >                    
                    <%
                    if(!UserID.equals("0"))
                    {
                    	rs = st.executeQuery("select user_id as userId from auctionms_user_mst where email ='"+UserID+"'");
                    	while(rs.next())
                    	{
                    		userId1 = rs.getInt("userId");
                    	}
                    	if(userId1 == sellerId)
                    	{%>
                    		<script type="text/javascript">
                    		$(function() {
                    			//document.getElementById("bidUpdate").disabled=true;
                    			
                    		     $('#bidUpdateDiv').hide();
                    		    
                    		 });
                    		</script>
                    	<%}
                    	else
                    	{%>
                    	<script type="text/javascript">
                    		$(function() {
                    		
                    		 });
                    		</script>
                    	<%	
           				rs = st.executeQuery("SELECT user1.user_id FROM auctionms_user_mst user1, auctionms_bid_mst bid WHERE user1.email = '"+UserID+"' AND user1.user_id = bid.user_id AND bid.product_id='"+prdId+"'");
           				if(rs.next())
           				{%>
        	   				<input type="hidden" name="insertUpdate" value="update">
        	            	<span class="button-submit-action button-wrap-left" style="margin-left: 30px;"><span class="button-submit-action button-wrap-right"><input type="submit" id= "bidUpdate" value="UPDATE BID" style="padding:0px 30px; font-size: 34px;font-family: monospace;"></span></span>
           			    <%}
           				else
               			{%>
                 			<input type="hidden" name="insertUpdate" value="insert">
                   			<span class="button-submit-action button-wrap-left" style="margin-left: 30px;"><span class="button-submit-action button-wrap-right"><input type="submit" id= "bidUpdate" value="BID" style="padding:0px 30px; font-size: 34px;font-family: monospace;"></span></span>        	   
               			<%}
                        
                    	}
                    }
           			else
           			{%>
             			<input type="hidden" name="insertUpdate" value="insert">
               			<span class="button-submit-action button-wrap-left" style="margin-left: 30px;"><span class="button-submit-action button-wrap-right"><input type="submit" id= "bidUpdate" value="BID" style="padding:0px 30px; font-size: 34px;font-family: monospace;"></span></span>        	   
           			<%}
                    
                    %>

          
                   </form>
                </p>
     
           </div>
    </div>
            <div style="margin-top:110px; display:inline-block;" class="clear2" id="auction_detail_update_27491_footer">
              <%
              String query2 = "SELECT bid.bid_amount as bidAmount,bid.dateTime as bidTime,user1.email as bidderEmail,user1.first_name as firstName FROM auctionms_user_mst AS user1, auctionms_bid_mst AS bid WHERE user1.user_id = bid.user_id AND bid.product_id ='"+prdId+"' ORDER BY bid.bid_amount DESC";
        	rs=null;
        	String highestBidder=null;
              rs=st.executeQuery(query2);      
        	while(rs.next())
              {
        		if(rs.first())
        		{
            	   highestBidder = rs.getString("firstName");
        		
              %>  
                
             
<%
        		}
        		break;
        		}
        	if(highestBidder==null)
        	{
        	%>
        	<p>Highest Bidder: <b>No any Bids</b></p>
        	<%}
        	else{%>
        	<p>Highest Bidder: <b><%=highestBidder %></b></p>
                <%} %> 
                  <table class="bidhistory_table">
                    <tbody>
    
                        <tr>
                            <th>Bid Amount</th>
                            <th>Bid Time Submitted</th>
                            <th>Bider</th>
                            <th>Bidder email</th>
                        </tr>
                         <%
                         rs=st.executeQuery(query2);         
                         if(!rs.next())
                         {%>
                         
                         <tr>
                             <th colspan="4">NO Bids yet</th>
                             
                         </tr>
                         <%} 
                        	 rs=null;
                         rs=st.executeQuery(query2);
                         while(rs.next())
    					{
                        	 
    						float bidAmount = rs.getFloat("bidAmount");
    						String dateTime = rs.getString("bidTime");
    						String name = rs.getString("firstName");
    						String email = rs.getString("bidderEmail");
   						 %>
    
                        <tr>
                            <th><%=bidAmount %></th>
                            <th><%=dateTime %></th>
                            <th><%=name %></th>
                            <th><a href="mailto:<%=email%>"><%=email %></a></th>
                        </tr>
                        <%} 
                    
                        %>
      
                                          </tbody>
                </table>
             
                			</div>
            
         <p>&nbsp;</p>
		</div>
        </div>
    </div>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tabs.js"></script>
<div class="ty-tabs cm-j-tabs clearfix">
    <ul class="ty-tabs__list">
    <li id="description" class="ty-tabs__item cm-js active"><a class="ty-tabs__a">Description</a></li>
   </ul>
</div>

<div class="cm-tabs-content ty-tabs__content clearfix" id="tabs_content">
<div id="content_description" class="ty-wysiwyg-content content-description" style="display: block;">
                                                    
<p>
	<%=prdDesc%>
</p>
            </div>
            <div id="content_features" class="ty-wysiwyg-content content-features">
            </div>
            <div id="content_files" class="ty-wysiwyg-content content-files">
            </div>
            <div id="content_attachments" class="ty-wysiwyg-content content-attachments">
            </div>
            <div id="content_discussion" class="ty-wysiwyg-content content-discussion">
            </div>
            <div id="content_required_products" class="ty-wysiwyg-content content-required_products">
            </div>
            
</div>
<%}%>
</div>

<div class="product-details">
</div>
 </div>
</div>                
</div>
</div>
<jsp:directive.include file="footer.jsp" />
<%}%>