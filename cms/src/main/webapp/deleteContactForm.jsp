<%@ page import="com.projects.cms.model.Contacts" %>
<%@ page import="com.projects.cms.repo.ContactsRepo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Delete Contact</title>
</head>
<body>
<h2> Do You Wish to Delete this Contact ?</h2>

<%! ContactsRepo jdbc = new ContactsRepo(); %>

<%
    int contactID = Integer.parseInt(request.getParameter("contactID"));
    Contacts c = jdbc.findByContactID(contactID);
%>

<form action="contacts" id="deleteContactForm" method="get">

    Name : <%= c.getContactName() %><br>
    Mobile : <%= c.getMobile() %><br>
    Email Address : <%= c.getEmailAddress() %><br>
    Address Line 1 : <%= c.getAddressLine1() %><br>
    Address Line 2 : <%= c.getAddressLine2() %><br>
    City : <%= c.getCity() %><br>
    State : <%= c.getState() %><br>
    Country : <%= c.getCountry() %><br>

    <p> <button type="submit">Confirm</button> </p>
</form>
<form action="contacts" method="get">
    <p> <button type="submit">Back to Contacts</button> </p>
</form>

<script>

    document.getElementById("deleteContactForm").addEventListener("submit", function(event)
    {
        event.preventDefault();

        fetch(`http://localhost:8080/cms_war_exploded/delete?contactID=<%=contactID%>`, {
            method: 'DELETE',
            headers: { 'Content-Type': 'application/json' }
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                console.log('PUT Response:', data);
                alert("Contact Deleted Successfully!")
                let form = document.getElementById("deleteContactForm");
                form.submit();
            })
            .catch(error => {
                console.error('Error:', error);
                alert("Failed to update contact. Please try again.");
            });
    });

</script>

</body>
</html>
