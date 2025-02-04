<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Contact</title>
</head>
<body>
<h2> Add Contact Details </h2>

<form action="addedContact.jsp" id="addContactForm" method="post">
    <p>
        <label for="contactName">Name : </label>
        <input type="text" id="contactName" name="contactName" pattern="[a-z,A-Z, ,0-9]{1,}"
               title="Only Alphabets and Numerical allowed" required autofocus>
    </p>
    <p>
        <label for="mobile">Mobile : </label>
        <input type="tel" id="mobile" name="mobile" pattern="[0-9]{10}"
               title="Enter a 10-Digit Mobile Number" required>
    </p>
    <p>
        <label for="emailAddress">Email ID : </label>
        <input type="text" id="emailAddress" name="emailAddress"
               pattern="[a-z,0-9]{1,}[a-z,0-9,+,.,-,_,%]{0,}@[a-z,0-9,-,.]{1,}\.[a-z]{2,}"
               title="example@domain.com">
    </p>
    <p>
        <label for="addressLine1">Address Line 1 : </label>
        <input type="text" id="addressLine1" name="addressLine1"><br>

        <label for="addressLine2">Address Line 2 : </label>
        <input type="text" id="addressLine2" name="addressLine2"><br>

        <label for="city">City : </label>
        <input type="text" id="city" name="city"><br>

        <label for="state">State : </label>
        <input type="text" id="state" name="state"><br>

        <label for="country">Country : </label>
        <input type="text" id="country" name="country" value="India">
    </p>

    <button type="submit">Submit</button>
    <button type="reset">Reset</button>
</form>

<script>

    document.getElementById("addContactForm").addEventListener("submit", function (event)
    {
        event.preventDefault();

        let formData = new FormData(document.getElementById("addContactForm"));
        let obj = {};

        formData.forEach((value, key) => {
            obj[key] = value;
        });

        fetch(`http://localhost:8080/cms_war_exploded/insert`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(obj)
        })
            .then(response => {
                if (!response.ok)
                    throw new Error(`HTTP error! Status: ${response.status}`);
                return response.json();
            })
            .then(data => {
                console.log('POST Response:', data);

                if (data.status == "ok") {
                    let form = document.getElementById("addContactForm");
                    form.submit();
                } else
                    alert(data.message);
            })
            .catch(error => {
                console.error('Error:', error);
                alert("Failed to create new contact. Please try again.");
            });
    });

</script>

</body>
</html>
