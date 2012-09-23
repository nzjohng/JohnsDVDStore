<HTML>
<!--
  DVD Search JSP
-->
<HEAD><TITLE>
DVD Search
</TITLE></HEAD>

<H3>DVD Search</H3>

<body bgcolor="white">
<font size = 3 color="#CC0000"></font>
<form method=POST action=DVD_search2.jsp>

<%@ page language="java" import="au.edu.swin.ict.dvdstore.*" %>
<%@ page import="java.util.*" %>

<jsp:useBean id='DVD_interface' scope='session' class='au.edu.swin.ict.dvdstore.DVDInterface'  />
<jsp:useBean id='customer_data' scope='session' class='au.edu.swin.ict.dvdstore.CustomerData' />

<jsp:useBean id="DVD_data" scope = 'page' class='au.edu.swin.ict.dvdstore.DVDData' />

<jsp:setProperty name="DVD_data" property="*" />

<%
  DVD_interface.processRequest(request.getParameter("action"),customer_data,DVD_data);
%>

<%= DVD_interface.getMessage() %>
<br>
<%

	if(DVD_interface.getDVD() != null) {
		out.println("ID: "+DVD_interface.getDVD().getID()+"<br>");
		out.println("Name: "+DVD_interface.getDVD().getTitle()+"<br>");
		out.println("Category: "+DVD_interface.getDVD().getCategory()+"<br>");
		out.println("Cost: "+DVD_interface.getDVD().getCost()+"<br>");
		out.println("Nights: "+DVD_interface.getDVD().getNights()+"<br>");
		out.println("Rating: "+DVD_interface.getDVD().getRating()+"<br>");
		if(DVD_interface.getDVD().getNumCopies() > 0)
			out.println("AVAILABLE<br>");
		else
			out.println("NOT AVAILABLE<br>");
	}

%>

</form>

<BR>
<A href="DVD_search3.jsp">Another Search</a>

</HTML>

