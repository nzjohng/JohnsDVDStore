<HTML>
<!--
  Customer Maintenance JSP
-->
<HEAD><TITLE>
Customer Maintenance
</TITLE></HEAD>

<H3>Customer Maintenance</H3>

<body bgcolor="white">
<font size = 3 color="#CC0000"></font>
<form method=POST action=customer_maintenance2.jsp>

<%@ page import="au.edu.swin.ict.dvdstore.*" %>
<%@ page import="java.util.*" %>


<jsp:useBean id='customer_interface' scope='page' class='au.edu.swin.ict.dvdstore.CustomerInterface' />
<jsp:useBean id='customer_data' scope='page' class='au.edu.swin.ict.dvdstore.CustomerData' />

<jsp:setProperty name="customer_data" property="*" />

<%

DVDStoreFactory.getInstance().init();

  customer_interface.processRequest(customer_data);
%>
<%= customer_interface.getMessage() %>

<br>


ID: <input type=text name= ID size=5 value=
<%= (customer_data.getID() > 0) ? ""+customer_data.getID() : "" %>
>
<br>
Name: <input type=text name=name size=25 value=
<%= (customer_data.getName() != null) ? "'"+customer_data.getName()+"'" : "" %>>
<br>

Age: <input type=text name= age size=4 value=
<%= (customer_data.getAge() > 0) ? ""+customer_data.getAge() : "" %>
>
<br>

Address: <input type=text name= address size=40 value=
<%= (customer_data.getAddress() != null) ? "'"+customer_data.getAddress()+"'" : "" %>
>
<br>

Phone: <input type=text name= phone size=15 value=
<%= (customer_data.getPhone() != null) ? "'"+customer_data.getPhone()+"'" : "" %>
>
<br>

Password: <input type=text name=password size=15 value=
<%= (customer_data.getPassword() != null) ? "'"+customer_data.getPassword()+"'" : "" %>
>

<br><p>


<br>
<INPUT TYPE=submit name="action" value="find">
<INPUT TYPE=submit name="action" value="new">
<INPUT TYPE=submit name="action" value="add">
<INPUT TYPE=submit name="action" value="update">
<INPUT TYPE=submit name="action" value="delete">

</form>

</HTML>

