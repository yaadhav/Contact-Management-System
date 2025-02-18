package com.projects.cms;

import com.projects.cms.model.Contacts;
import com.projects.cms.repo.ContactsRepo;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class UpdateServlet extends HttpServlet
{
    final private ContactsRepo jdbc = new ContactsRepo();

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Contacts c = new Contacts();

        c.setContactID((int) request.getAttribute("contactID"));
        c.setContactName((String) request.getAttribute("contactName"));
        c.setMobile((String) request.getAttribute("mobile"));
        c.setEmailAddress((String) request.getAttribute("emailAddress"));
        c.setAddressLine1((String) request.getAttribute("addressLine1"));
        c.setAddressLine2((String) request.getAttribute("addressLine2"));
        c.setCity((String) request.getAttribute("city"));
        c.setState((String) request.getAttribute("state"));
        c.setCountry((String) request.getAttribute("country"));

        jdbc.updateByContactID(c);

        response.setContentType("application/json");
        response.getWriter().write("{\"message\":\"Contact Updated successfully\", \"status\":\"ok\"}");
    }
}