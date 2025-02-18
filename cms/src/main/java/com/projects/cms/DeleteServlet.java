package com.projects.cms;

import com.projects.cms.repo.ContactsRepo;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeleteServlet extends HttpServlet
{
    final private ContactsRepo jdbc = new ContactsRepo();

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        int contactID = Integer.parseInt(request.getParameter("contactID"));

        jdbc.deleteByContactID(contactID);

        response.setContentType("application/json");
        response.getWriter().write("{\"message\": \"Contact Deleted successfully\"}");
    }
}