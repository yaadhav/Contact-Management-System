package com.projects.cms;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;

public class AddFilter implements Filter
{
    final private int maxLengths[] = {30, 50, 30, 30, 30, 30, 30};

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getParameter("contactID") != null)
        {
            int contactID = Integer.parseInt(request.getParameter("contactID"));
            request.setAttribute("contactID", contactID);
        }

        BufferedReader reader = request.getReader();
        String jsonString = reader.readLine();

        JSONObject jsonObject = new JSONObject(jsonString);
        String fields[] = {"contactName", "emailAddress", "addressLine1", "addressLine2", "city", "state", "country"};

        request.setAttribute("mobile", jsonObject.getString("mobile"));
        for (int i = 0; i < 7; i++)
        {
            String value = jsonObject.getString(fields[i]);

            if (value.length() > maxLengths[i])
            {
                response.setContentType("application/json");
                response.getWriter().write("{\"message\": \"" + fields[i] + " exceeds " + maxLengths[i] + " Characters, So Please Try Again!\", \"status\":\"failure\"}");
                return;
            }

            servletRequest.setAttribute(fields[i], value);
        }

        filterChain.doFilter(request, response);
    }
}
