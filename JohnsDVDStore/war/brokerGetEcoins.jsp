<html>
<body>


<%@ page import="au.edu.swin.ict.dvdstore.*" %>

<%

 DVDStoreFactory.getInstance().init();
  
	String touchstone = "ABCDEFG";
	String ecoins = "[1,2,3,4,5]";

%>
Vendor name = <%= request.getParameter("vendor_name") %>
<br/>
Touchstone=<%= touchstone %> 
<br/>
Ecoins=<%= ecoins %>
<br/>

</body></html>