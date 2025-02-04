package com.projects.cms;

import com.projects.cms.model.Contacts;
import com.projects.cms.repo.JdbcTemplate;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ViewServlet extends HttpServlet
{
    final private JdbcTemplate jdbc = new JdbcTemplate();

    public int printContacts(HttpServletResponse response, String low, String up, int tc) throws IOException
    {
        PrintWriter out = response.getWriter();

        List<Contacts> contacts = jdbc.findByNamesLike(low + "%", up + "%");

        if (contacts.isEmpty())
            return 0;

        Map<String, List<Contacts>> dict = new TreeMap<>();

        for (Contacts c : contacts)
        {
            String contactName = c.getContactName().toLowerCase();
            dict.computeIfAbsent(contactName, cName -> new ArrayList<>()).add(c);
        }

        if (low.equals(up) && tc == 0)
            out.println("<h2>#</h2>");
        else if (!low.equals(up))
            out.println("<h2>" + up + "</h2>");

        for (List<Contacts> conts : dict.values())
        {
            for (Contacts c : conts) {
                out.println("<p>");
                out.println("Name : " + c.getContactName() + "<br>");
                out.println("Mobile : " + c.getMobile() + "<br>");
                out.println("Email Address : " + c.getEmailAddress() + "<br>");
                out.println("AddressLine1 : " + c.getAddressLine1() + "<br>");
                out.println("AddressLine2 : " + c.getAddressLine2() + "<br>");
                out.println("City : " + c.getCity() + "<br>");
                out.println("State : " + c.getState() + "<br>");
                out.println("Country : " + c.getCountry() + "<br>");

                out.println(
                        "<form>" +
                        "   <p>" +
                        "        <button type=\"submit\" formmethod=\"post\"" +
                        "           formaction=\"updateContactForm.jsp?contactID=" + c.getContactID() + "\">Edit</button>  " +
                        "         <button type=\"submit\" formmethod=\"post\"" +
                        "               formaction=\"deleteContactForm.jsp?contactID=" + c.getContactID() + "\">Delete</button>" +
                        "   </p>" +
                        "</form>");

                out.println("<hr>");
            }
        }

        out.println("<br>");

        return contacts.size();
    }

    public int printAllSorted(HttpServletResponse response) throws IOException
    {
        int totalCount = 0;
        for (int num = 0; num <= 9; num++)
            totalCount += printContacts(response, num + "", num + "", totalCount);

        for (char low = 'a', up = 'A'; low <= 'z'; low++, up++)
            totalCount += printContacts(response, low + "", up + "", totalCount);

        return totalCount;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        PrintWriter out = response.getWriter();

        out.println(
                "<html>" +
                "<body>" +
                "<h1>Contacts Information </h1>" +
                "<form action=\"index.jsp\"> " +
                "   <button type=\"submit\">Menu</button>" +
                "</form>"
        );

        int totalCount = printAllSorted(response);

        out.println(
                "Total Contacts Stored : " + totalCount +
                "<form action=\"addContactForm.jsp\" method=\"post\"> " +
                "   <p><button type=\"submit\">+ Add a New Contact</button></p>" +
                "</form>" +
                "</body>" +
                "</html>"
        );
    }
}
