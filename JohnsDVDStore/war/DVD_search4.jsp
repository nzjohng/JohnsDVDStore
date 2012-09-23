<HTML>
<!--
  Video Search JSP
-->
<HEAD><TITLE>
Video Search
</TITLE></HEAD>

<H3>DVD Search</H3>

<body bgcolor="white">
<font size = 3 color="#CC0000"></font>

<%@ page language="java" import="au.edu.swin.ict.dvdstore.*" %>
<%@ page import="java.util.*" %>

<jsp:useBean id='video_interface' scope='session' class='au.edu.swin.ict.dvdstore.DVDInterface'  />
<jsp:useBean id='customer_data' scope='session' class='au.edu.swin.ict.dvdstore.CustomerData' />

<jsp:useBean id="video_data" scope = 'page' class='au.edu.swin.ict.dvdstore.DVDData' />

<jsp:setProperty name="video_data" property="*" />

<%

  video_interface.processRequest(request.getParameter("action"),customer_data,video_data);

%>

<%= video_interface.getMessage() %>
<br>
<br>
<%

Enumeration e = video_interface.getDVDs().elements();
while(e.hasMoreElements())
{
	DVDData video = (DVDData) e.nextElement();
	out.println(video.getTitle()+"<a href=video_search5.jsp?ID="+video.getID()+"&action=Details"+">Details</a><BR>");
}
%>

<br>

<BR>
<A href="video_search3.jsp">Another Search</a>

</HTML>

