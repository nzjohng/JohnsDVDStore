<HTML>
<HEAD>

<TITLE>
DVD Rental
</TITLE>
</HEAD>
<BODY>
<H1>
DVD Rental
</H1>

<body bgcolor="white">
<font size = 3 color="#CC0000"></font>
<form method=POST action=DVD_rental.jsp>

<%@ page import="au.edu.swin.ict.dvdstore.*" %>

<jsp:useBean id="rental_interface" scope="session" class="au.edu.swin.ict.dvdstore.RentalInterface" />

<jsp:useBean id="staff_data" scope="session" class="au.edu.swin.ict.dvdstore.StaffData" />
<jsp:useBean id="customer_data" scope="session" class="au.edu.swin.ict.dvdstore.CustomerData" />
<jsp:useBean id="video_data" scope="session" class="au.edu.swin.ict.dvdstore.DVDData" />


<%

 DVDStoreFactory.getInstance().init();

  /* do using setProperty */
  try {
  if(request.getParameter("staffID") != null)
    staff_data.setID(Integer.parseInt(request.getParameter("staffID")));
  staff_data.setPassword(request.getParameter("staffPassword"));
  if(request.getParameter("customerID") != null)
    customer_data.setID(Integer.parseInt(request.getParameter("customerID")));
  if(request.getParameter("videoID") != null)
    video_data.setID(Integer.parseInt(request.getParameter("videoID")));
  } catch (Exception e) { }

  rental_interface.processRequest(request.getParameter("action"),
    staff_data,customer_data,video_data);

%>

<%= rental_interface.getMessage() %>

<br>
<table>
<tr><td></td><td>ID</td><td>Password</td><td>Name/Title</td><td></td></tr>
<tr>
<td>Staff:</td><td><input type=text name=staffID size=12 value=
<%= (staff_data.getID() > 0) ? ""+staff_data.getID() : "" %>
></td>
<td><input type=password name=staffPassword size=12 value=''></td>

<td><%= (staff_data.getName() != null) ? ""+staff_data.getName() : "" %></td>

<td><INPUT TYPE=submit name="action" value="findStaff"></td>
</tr>

<tr>
<td>Customer:</td><td><input type=text name=customerID size=12 value=
<%= (customer_data.getID() > 0) ? ""+customer_data.getID() : "" %>
></td>
<td></td>
<td>Name:</td><td> <%= (customer_data.getName() != null) ? ""+customer_data.getName() : "" %>
<td>Address:</td><td> <%= (customer_data.getAddress() != null) ? ""+customer_data.getAddress() : "" %>
</td>
<td><INPUT TYPE=submit name="action" value="findCustomer"></td>
</tr>
<tr>
<td>DVD:</td><td><input type=text name=videoID size=12 value=
<%= (video_data.getID() > 0) ? ""+video_data.getID() : "" %>
></td>
<td>Title:</td><td><%= (video_data.getTitle() != null) ? ""+video_data.getTitle() : "" %></td>

<td><INPUT TYPE=submit name="action" value="findDVD"></td>
</tr>
</table>

<input type=submit name="action" value="rentDVD">
<input type=submit name="action" value="returnDVD">

</form>

</BODY>
</HTML>
