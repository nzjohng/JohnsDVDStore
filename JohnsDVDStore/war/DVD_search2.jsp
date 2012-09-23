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

<!-- <jsp:setProperty name="DVD_data" property="*" />  -->


<%

  DVD_interface.processRequest(request.getParameter("action"),customer_data, DVD_data);

%>

<H4>Hello <%= customer_data.getName() %></H4>

<br>

<%= DVD_interface.getMessage() %>

<hr>
<%

	if(DVD_interface.getDVD() != null) {
%>		
ID: <input type="text" name="ID" value="<%= DVD_interface.getDVD().getID() %>" class="field left" readonly="readonly" ><br/>
Title: <input type="text" name="Title" value="<%= DVD_interface.getDVD().getTitle() %>" class="field left" readonly="readonly" ><br/>
Category: <input type="text" name="Category" value="<%= DVD_interface.getDVD().getCategory() %>" class="field left" readonly="readonly" ><br/>
Cost: <input type="text" name="Cost" value="<%= DVD_interface.getDVD().getID() %>" class="field left" readonly="readonly" ><br/>
Nights: <input type="text" name="Nights" value="<%= DVD_interface.getDVD().getNights() %>" class="field left" readonly="readonly" ><br/>
Rating: <input type="text" name="Rating" value="<%= DVD_interface.getDVD().getRating() %>" class="field left" readonly="readonly" ><br/>
<br/>	
	
<%	

		if(DVD_interface.getDVD().getNumCopies() > 0)
			out.println("AVAILABLE - "+DVD_interface.getDVD().getNumCopies()+" copies<br>");
		else
			out.println("<red>NOT AVAILABLE</red><br>");
		
	}

%>

<hr>
<SELECT NAME="ID" SIZE=8>
<OPTION VALUE=0> List of DVDs found... <BR>
<%

Enumeration e = DVD_interface.getDVDs().elements();
while(e.hasMoreElements())
{
	DVDData DVD = (DVDData) e.nextElement();
	out.println("<OPTION VALUE="+DVD.getID()+"> "+DVD.getTitle()+" <BR>");
}
%>
</SELECT>
<br>
Part of title: <input type=text name= title size=12>
Category:
<SELECT NAME="category">
<OPTION>
<OPTION>Childrens
<OPTION>Drama
<OPTION>Comedy
<OPTION>Horror
<OPTION>JSP Book
</SELECT>
<br>
<INPUT TYPE=submit name="action" value="Search">
<INPUT TYPE=submit name="action" value="Details">
<INPUT TYPE=submit name="action" value="Rent">

</form>

</HTML>

