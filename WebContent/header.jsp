<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>

<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Auction - Auction</title>



<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">

<meta name="description" content="">


<meta name="keywords" content="">

<meta name="robots" content="noindex">



<link href="" rel="shortcut icon">
<link type="text/css" rel="stylesheet" href="css/style.css">

<script type="text/javascript" src="js/scripts-1a88f655e8ab1c4db84fa44deee9ef861419583848.js"></script><style type="text/css"></style><script type="text/javascript">

(function(_, $) {

        _.tr({
            cannot_buy: 'You cannot buy the product with these option variants',
            no_products_selected: 'No products selected',
            error_no_items_selected: 'No items selected! At least one check box must be selected to perform this action.',
            delete_confirmation: 'Are you sure you want to delete the selected items?',
            text_out_of_stock: 'Out of stock',
            items: 'item(s)',
            text_required_group_product: 'Please select a product for the required group [group_name]',
            save: 'Save',
            close: 'Close',
            notice: 'Notice',
            warning: 'Warning',
            error: 'Error',
            text_are_you_sure_to_proceed: 'Are you sure you want to proceed?',
            text_invalid_url: 'You have entered an invalid URL',
            error_validator_email: 'The email address in the <b>[field]<\/b> field is invalid.',
			error_validator_fname: 'The first name in the <b>[field]<\/b> field is required.',
            error_validator_lname: 'The last name in the <b>[field]<\/b> field is required.',
			error_validator_title: 'Product <b>[field]<\/b> is required.',
			error_validator_description: 'Product description is required.',
			error_validator_mrp: 'MRP is invalid.',
			error_validator_minimum: 'Minimum bid is invalid.',
			error_validator_picture: 'Profile pic is invalid.',
            error_validator_phone: 'The phone number in the <b>[field]<\/b> field is invalid. The correct format is (555) 555-55-55 or 55 55 555 5555.',
            error_validator_integer: 'The value of the <b>[field]<\/b> field is invalid. It should be integer.',
            error_validator_multiple: 'The <b>[field]<\/b> field does not contain the selected options.',
            error_validator_password: 'The passwords in the <b>[field2]<\/b> and <b>[field]<\/b> fields do not match.',
            error_validator_required: 'The <b>[field]<\/b> field is mandatory.',
            error_validator_zipcode: 'The ZIP / Postal code in the <b>[field]<\/b> field is incorrect. The correct format is [extra].',
            error_validator_message: 'The value of the <b>[field]<\/b> field is invalid.',
            text_page_loading: 'Loading... Your request is being processed, please wait.',
            error_ajax: 'Oops, something went wrong ([error]). Please try again.',
            text_changes_not_saved: 'Your changes have not been saved.',
            text_data_changed: 'Your changes have not been saved.Press OK to continue, or Cancel to stay on the current page.',
            more: 'More'
        });
        
        $.extend(_, {
            index_script: 'index.php',
            changes_warning: /*'Y'*/'N',
            currencies: {
                'primary': {
                    'decimals_separator': '.',
                    'thousands_separator': ',',
                    'decimals': '2'
                },
                'secondary': {
                    'decimals_separator': '.',
                    'thousands_separator': ',',
                    'decimals': '2',
                    'coefficient': '1.00000'
                }
            },
            default_editor: 'redactor',
            default_previewer: 'magnific',    
            current_path: '/auction',
            current_location: 'http://demo.openthestore.com/auction',
            images_dir: 'http://demo.openthestore.com/auction/design/themes/moda/media/images',
            notice_displaying_time: 0,
            cart_language: 'en',
            default_language: 'en',
            cart_prices_w_taxes: false,
            translate_mode: false,
            theme_name: 'moda',
            regexp: [],
            current_url: 'index.php?dispatch=auction.view',
            current_host: 'demo.openthestore.com',
            init_context: ''
        });
    
    
        $(document).ready(function(){
            $.runCart('C');
        });

    
}(Tygh, Tygh.$));
</script>
<script type="text/javascript">
CloudZoom = {
    path: 'http://demo.openthestore.com/auction/js/addons/image_zoom'
};
</script>
<script type="text/javascript">
//<![CDATA[
    Tygh.tr('auction_autorize', 'You should authorize in order to make bids.');
    Tygh.tr('auction_bid_too_low', 'Your Bid amount is too low. Please increase your bid and try again.');
    Tygh.tr('auction_already_closed', 'The auction already closed');
    Tygh.tr('auction_not_started', 'The auction has not started yet');
    Tygh.tr('auction_add_to_cart', 'Add to cart');


            var auction_autorized = false;
        var auction_user_id = 0;
    
    var auction_status_active = 2;
//]]>
</script>
<script type="text/javascript">
//<![CDATA[
    var discount_current_time = 1458923257;
//]]>
</script>
<script type="text/javascript">
$(document).ready(function() {

$(".tab_content").hide();
$(".tab_content:first").show();

$("ul.tabs li").click(function() {
$("ul.tabs li").removeClass("active");
$(this).addClass("active");
$(".tab_content").hide();
var activeTab = $(this).attr("id");
$("."+activeTab).fadeIn();
});
});

</script>


<style type="text/css">.cm-noscript {display:none}</style></head>

<body>

<div class="ty-tygh  " id="tygh_container">

<div id="ajax_overlay" class="ty-ajax-overlay" style="display: none;"></div>
<div id="ajax_loading_box" class="ty-ajax-loading-box" style="display: none;"></div>

<div class="cm-notification-container notification-container">
</div>

<div class="ty-helper-container no-touch" id="tygh_main_container">
    

        


<div class="tygh-header clearfix">
    <div class="container-fluid  header-grid">
                    


<div class="row-fluid ">    <div class="span4 top-logo-grid">
                    <div class=" top-logo">
        <div class="ty-logo-container">
    <a href="index.jsp" title="">
        <img src="images/esyauction.png" alt="" class="ty-logo-container__image" style="">
    </a>
</div>
    </div>
            </div>
                


    <div class="span8 search-block-grid">
                    <div class=" top-search">
        <div class="ty-search-block">
    <form action="index.jsp" name="search_form" class="cm-processed-form">
        <input type="hidden" name="subcats" value="Y">
        <input type="hidden" name="status" value="A">
        <input type="hidden" name="pshort" value="Y">
        <input type="hidden" name="pfull" value="Y">
        <input type="hidden" name="pname" value="Y">
        <input type="hidden" name="pkeywords" value="Y">
        <input type="hidden" name="search_performed" value="Y">

        


        <input type="text" name="search_input" value="" id="search_input" title="Search products" class="ty-search-block__input cm-hint"><button title="Search" class="ty-search-magnifier" type="submit">Go</button>
<input type="hidden" name="dispatch" value="products.search">
            </form>
</div>


    </div>
            </div>
                


    <div class="span4 cart-content-grid">
        <div class="ty-dropdown-box  top-my-account ty-float-right">
        <div id="sw_dropdown_329" class="ty-dropdown-box__title cm-combination unlogged">
            
                                <a class="ty-account-info__title" href="http://demo.openthestore.com/auction/profiles-update/">
        <i class="ty-icon-user"></i>&nbsp;
					<% 
					HttpSession sessionHeader = request.getSession(false);
					String UserID = (String)sessionHeader.getAttribute("user");
					if(UserID == null)
					{
						UserID = "0";
					}
					if(UserID.equals("0"))
					{
					%>
        			<span class="hidden-phone">
            		Account
        			</span>
					<%
					}
					else
					{
					%>
					<span class="hidden-phone">
            		<%=UserID %>
        			</span>
					<%
					}
					%>      
        
        <i class="ty-icon-down-micro ty-account-info__user-arrow"></i>
    </a>

                        

        </div>
        <div id="dropdown_329" class="cm-popup-box ty-dropdown-box__content hidden">
            

<div id="account_info_329">


    
    <div class="ty-account-info__buttons buttons-container">
					<% 
					if(UserID.equals("0"))
					{
					%>
                    <a href="login.jsp" rel="nofollow" class="ty-btn ty-btn__primary">Log In</a>
					<a href="register.jsp" rel="nofollow" class="ty-btn ty-btn__primary">Register</a>
					<%
					}
					else
					{
					%>
					<a href="change_password.jsp" class="ty-btn ty-btn__primary">Change Password</a>
					<a href="${pageContext.request.contextPath}/Logout" rel="nofollow" class="ty-btn ty-btn__primary">Logout</a>
					<%
					}
					%>      
    </div>
<!--account_info_329--></div>
        </div>
    </div>            
            </div>
</div>                


<div class="row-fluid ">    <div class="span16 top-menu-grid">
                    <div class=" top-menu">
        


    <ul class="ty-menu__items cm-responsive-menu">
        
            <li class="ty-menu__item ty-menu__menu-btn visible-phone">
                <a class="ty-menu__item-link">
                    <i class="ty-icon-short-list"></i>
                    <span>Menu</span>
                </a>
            </li>
			
			<li class="ty-menu__item  cm-menu-item-responsive ">
				<a href="index.jsp" class="ty-menu__item-link">
					Home
				</a>
			</li>
			
			<li class="ty-menu__item  cm-menu-item-responsive ">
				<a href="upload_product.jsp" class="ty-menu__item-link">
					Sell Your Product
				</a>
			</li>
			
			<li class="ty-menu__item  cm-menu-item-responsive ">
				<a href="profile.jsp" class="ty-menu__item-link">
					Your Profile
				</a>
			</li>
    </ul>
	

<script type=text/javascript>
		$.ceNotification('show', {
	    type: 'W',
	    title: Tygh.tr('warning'),
	    message: Tygh.tr('auction_not_started')
	});
</script>

    </div>
            </div>
</div>
</div>
</div>