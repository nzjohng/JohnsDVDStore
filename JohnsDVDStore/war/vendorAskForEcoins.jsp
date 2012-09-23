<html>
<BODY>
<H1>
Vendor - example of asking for Ecoins & Touchstone from Broker
</H1>
<p/>

<%@ page import="au.edu.swin.ict.dvdstore.*" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="static com.google.appengine.api.urlfetch.FetchOptions.Builder.*" %>
<%@ page import="com.google.appengine.api.urlfetch.*" %>


<jsp:useBean id="rental_interface" scope="session" class="au.edu.swin.ict.dvdstore.RentalInterface" />

<jsp:useBean id="staff_data" scope="session" class="au.edu.swin.ict.dvdstore.StaffData" />
<jsp:useBean id="customer_data" scope="session" class="au.edu.swin.ict.dvdstore.CustomerData" />
<jsp:useBean id="video_data" scope="session" class="au.edu.swin.ict.dvdstore.DVDData" />

<br/>

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

  
  String vendor_name = "JohnBookStore";
  String vendor_password = "abc";
  
  /*
  String post_result = Request.Post("http://localhost:8888/testPost.jsp")
  .useExpectContinue()
  .version(HttpVersion.HTTP_1_1)
  .bodyString("Important stuff", ContentType.DEFAULT_TEXT)
  .execute().returnContent().asString();
*/

String touchstone = null;
String ecoins = null;


//try {
    URL url = new URL("http://localhost:8888/brokerGetEcoins.jsp?vendor_name="+vendor_name+"&vendor_password="+vendor_password);
   
    
    URLFetchServiceFactory.getURLFetchService().fetch(new HTTPRequest(url, HTTPMethod.GET,  doNotValidateCertificate()));
    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
    String line;

    while ((line = reader.readLine()) != null) {  
    
    if(line.startsWith("Touchstone=")) {
    	touchstone = line.substring("Touchstone=".length());
    }
    if(line.startsWith("Ecoins=")) {
    	ecoins = line.substring("Ecoins=".length());
    }
    
  
   
    }
    reader.close();


//} catch (Exception e) {
//    System.err.println(e);
//}

  
%>

Touchstone = <%= touchstone %>
<br/>
Ecoins = <%= ecoins %>


<br/>

</body>
</html>
