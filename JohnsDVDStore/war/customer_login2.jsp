<HTML>
<!--
  Customer Login JSP
-->
<HEAD><TITLE>
Customer Login
</TITLE></HEAD>

<H3>Customer Login</H3>

<body bgcolor="white">
<font size = 3 color="#CC0000"> </font>
<form method=POST action=customer_login2.jsp>
<BR>

<%@ page language="java" import="au.edu.swin.ict.dvdstore.*" %>


<jsp:useBean id='customer_data' scope = 'session' class='au.edu.swin.ict.dvdstore.CustomerData' />

<jsp:setProperty name="customer_data" property="*" />

<%
// Do initial connection to DB/init store etc
//
// Need to refactor into generic database/datastore initialiser...
//

DVDStoreFactory.getInstance().init();

if(customer_data != null && customer_data.getAction() != null) {
	String bad = "";

	CustomerData c2 = DVDStoreFactory.getInstance().getCustomerManager().findCustomer(customer_data.getID());

	if(customer_data.getID() == 0)
		bad = "Enter customer ID";
	else if(customer_data.getPassword() == null)
		bad = "Enter customer password";
	else if(c2 == null)
		bad = "No such Customer ID";
	else if(!c2.matchesPassword(customer_data.getPassword()))
		bad = "Wrong password";

	if(!bad.equals(""))
		out.println(bad);
	else
        	try {
        		
        	  customer_data.setName(c2.getName());
        	  customer_data.setAction(null);
				
           	  getServletConfig().getServletContext().getRequestDispatcher("/DVD_search2.jsp").forward(request, response);
        	} catch (Exception ex) {
            	  ex.printStackTrace ();
        	}
}

%>
<br>
Customer ID: <input type=text name= ID size=12>
<br>
Password: <input type=password name= password size=12>
<br>
<INPUT TYPE=submit name="action" value="Login">

</form>

</HTML>

