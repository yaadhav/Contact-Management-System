<%@ page import="com.projects.cms.model.Contacts" %>
<%@ page import="com.projects.cms.repo.JdbcTemplate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Contact</title>
</head>
<body>
<h2> Edit Contact Details </h2>

<%! JdbcTemplate jdbc = new JdbcTemplate(); %>

<%
    int contactID = Integer.parseInt(request.getParameter("contactID"));
    Contacts c = jdbc.findByContactID(contactID);
%>

<form action="updatedContact.jsp?contactID=<%=contactID%>" id="updateContactForm" method="post">
    <p>
        <label for="contactName">Name : </label>
        <input type="text" id="contactName" name="contactName" pattern="[a-z,A-Z, ,0-9]{1,}"
               title="Only Alphabets and Numerical allowed"
               value="<%= c.getContactName() %>" required autofocus>
    </p>
    <p>
        <label for="mobile">Mobile : </label>
        <input type="tel" id="mobile" name="mobile" pattern="[0-9]{10}"
               title="Enter a 10-Digit Mobile Number"
               value="<%= c.getMobile() %>" required>
    </p>
    <p>
        <label for="emailAddress">Email ID : </label>
        <input type="text" id="emailAddress" name="emailAddress"
               value="<%= c.getEmailAddress() %>"
               pattern="[a-z,0-9]{1,}[a-z,0-9,+,.,-,_,%]{0,}@[a-z,0-9,-,.]{1,}\.[a-z]{2,}"
               title="example@domain.com">
    </p>
    <p>
        <label for="addressLine1">Address Line 1 : </label>
        <input type="text" id="addressLine1" name="addressLine1"
               value="<%= c.getAddressLine1() %>"><br>

        <label for="addressLine2">Address Line 2 : </label>
        <input type="text" id="addressLine2" name="addressLine2"
               value="<%= c.getAddressLine2() %>"><br>

        <label for="city">City : </label>
        <input type="text" id="city" name="city"
               value="<%= c.getCity() %>"><br>

        <label for="state">State : </label>
        <input type="text" id="state" name="state"
               value="<%= c.getState() %>"><br>

        <label for="country">Country : </label>
        <input type="text" id="country" name="country"
               value="<%= c.getCountry() %>">
    </p>

    <button type="submit">Submit</button>
    <button type="reset">Reset</button>
</form>

<script>

    document.getElementById("updateContactForm").addEventListener("submit", function (event) {
        event.preventDefault();

        let formData = new FormData(document.getElementById("updateContactForm"));
        let obj = {};

        formData.forEach((value, key) => {
            obj[key] = value;
        });

        fetch(`http://localhost:8080/cms_war_exploded/update?contactID=<%=contactID%>`, {
            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(obj)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                console.log('PUT Response:', data);

                if (data.status == "ok") {
                    let form = document.getElementById("updateContactForm");
                    form.submit();
                } else
                    alert(data.message);
            })
            .catch(error => {
                console.error('Error:', error);
                alert("Failed to update contact. Please try again.");
            });
    });

</script>


</body>
</html>
