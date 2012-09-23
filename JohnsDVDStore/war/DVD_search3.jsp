<HTML>
<!--
  Video Search JSP
-->
<HEAD><TITLE>
DVD Search
</TITLE></HEAD>

<H3>Video Search</H3>

<body bgcolor="white">
<font size = 3 color="#CC0000"></font>
<form method=POST action=video_search4.jsp>

<%@ page language="java" import="au.edu.swin.ict.dvdstore.*" %>
<%@ page import="java.util.*" %>

<jsp:useBean id='customer_data' scope='session' class='au.edu.swin.ict.dvdstore.CustomerData' />

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

</form>

</HTML>

