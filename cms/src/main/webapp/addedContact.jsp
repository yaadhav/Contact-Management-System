<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Added Contact</title>
</head>
<body>
<h2>Added Contact Details</h2>

Name : <%=request.getParameter("contactName")%><br>
Mobile : <%=request.getParameter("mobile")%><br>
Email Address : <%=request.getParameter("emailAddress")%><br>
Address Line 1 : <%=request.getParameter("addressLine1")%><br>
Address Line 2 : <%=request.getParameter("addressLine2")%><br>
City : <%=request.getParameter("city")%><br>
State : <%=request.getParameter("state")%><br>
Country : <%=request.getParameter("country")%>

<form>
    <p>
        <button type="submit" formaction="addContactForm.jsp" formmethod="post">Add Another Contact</button>
        <button type="submit" formaction="contacts" formmethod="get">View Contacts</button>
    </p>
</form>

</body>
</html>
