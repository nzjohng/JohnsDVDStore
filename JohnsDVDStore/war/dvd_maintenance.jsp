<HTML>
<!--
  DVD Maintenance JSP
-->
<HEAD><TITLE>
DVD Maintenance
</TITLE></HEAD>

<H3>DVD Maintenance</H3>

<body bgcolor="white">
<font size = 3 color="#CC0000"></font>
<form method=POST action=dvd_maintenance.jsp>

<%@ page import="au.edu.swin.ict.dvdstore.*" %>
<%@ page import="java.util.*" %>


<jsp:useBean id='dvd_interface' scope='page' class='au.edu.swin.ict.dvdstore.DVDInterface' />
<jsp:useBean id='dvd_data' scope='page' class='au.edu.swin.ict.dvdstore.DVDData' />

<jsp:setProperty name="dvd_data" property="*" />

<%

DVDStoreFactory.getInstance().init();

  dvd_interface.processRequest(request.getParameter("action"),null,dvd_data);
%>
<%= dvd_interface.getMessage() %>

<br>


ID: <input type=text name=ID size=5 value=
<%= (dvd_data.getID() > 0) ? ""+dvd_data.getID() : "" %>
>
<br>
Title: <input type=text name=title size=60 value=
<%= (dvd_data.getTitle() != null) ? "'"+dvd_data.getTitle()+"'" : "" %> >
<br>

Category: <SELECT NAME="category" >
<OPTION></OPTION>
<OPTION <% if("Childrens".equals(dvd_data.getCategory())) out.println("selected=selected"); %>>Childrens</OPTION>
<OPTION <% if("Drama".equals(dvd_data.getCategory())) out.println("selected=selected"); %>>Drama</OPTION>
<OPTION>Comedy</OPTION>
<OPTION>Horror</OPTION>
<OPTION>JSP Book</OPTION>

</SELECT>

<br>

Number nights: <input type=text name=nights size=10 value=
<%= (dvd_data.getNights() != 0) ? "'"+dvd_data.getNights()+"'" : "" %>
>
<br>

Cost: $<input type=text name=cost size=10 value=
<%= (dvd_data.getCost() != 0) ? "'"+dvd_data.getCost()+"'" : "" %>
>
<br>
Number copies: <input type=text name=numCopies size=10 value=
<%= (dvd_data.getNumCopies() != 0) ? "'"+dvd_data.getNumCopies()+"'" : "" %>
>
<br>


<br><p>


<br>
<INPUT TYPE=submit name="action" value="find">
<INPUT TYPE=submit name="action" value="new">
<INPUT TYPE=submit name="action" value="add">
<INPUT TYPE=submit name="action" value="update">
<INPUT TYPE=submit name="action" value="delete">

</form>

</HTML>

