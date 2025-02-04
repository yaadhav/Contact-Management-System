package com.projects.cms.repo;

import com.projects.cms.model.Contacts;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate
{
    final private static String url = "jdbc:postgresql://localhost:5432/sample";
    final private static String username = "postgres";
    final private static String password = "Yaadhav@7";

    public void insert(Contacts c)
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, username, password);

            String sql = "insert into contacts values( default, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, c.getContactName());
            pst.setString(2, c.getMobile());
            pst.setString(3, c.getEmailAddress());
            pst.setString(4, c.getAddressLine1());
            pst.setString(5, c.getAddressLine2());
            pst.setString(6, c.getCity());
            pst.setString(7, c.getState());
            pst.setString(8, c.getCountry());

            int rows = pst.executeUpdate();
            System.out.println(rows + " row/s affected");

            con.close();
        }
        catch (SQLException error)
        {
            error.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Class Not Found!");
        }
    }

    public List<Contacts> findByNamesLike(String c1, String c2)
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, username, password);

            String sql = "select * from contacts where contactname like ? or contactname like ?";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, c1);
            pst.setString(2, c2);

            ResultSet rs = pst.executeQuery();

            List<Contacts> contacts = new ArrayList<>();

            while (rs.next()) {
                Contacts c = new Contacts();

                c.setContactID(rs.getInt(1));
                c.setContactName(rs.getString(2));
                c.setMobile(rs.getString(3));
                c.setEmailAddress(rs.getString(4));
                c.setAddressLine1(rs.getString(5));
                c.setAddressLine2(rs.getString(6));
                c.setCity(rs.getString(7));
                c.setState(rs.getString(8));
                c.setCountry(rs.getString(9));

                contacts.add(c);
            }

            con.close();

            return contacts;
        }
        catch (SQLException error)
        {
            error.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Class Not Found!");
        }

        return new ArrayList<>();
    }

    public Contacts findByContactID(int contactID)
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, username, password);

            String sql = "select * from contacts where contactid=" + contactID;

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            Contacts c = new Contacts();

            rs.next();

            c.setContactID(rs.getInt(1));
            c.setContactName(rs.getString(2));
            c.setMobile(rs.getString(3));
            c.setEmailAddress(rs.getString(4));
            c.setAddressLine1(rs.getString(5));
            c.setAddressLine2(rs.getString(6));
            c.setCity(rs.getString(7));
            c.setState(rs.getString(8));
            c.setCountry(rs.getString(9));

            con.close();

            return c;
        }
        catch (SQLException error)
        {
            error.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Class Not Found!");
        }

        return new Contacts();
    }

    public List<Contacts> findAll()
    {
        try
        {
            Connection con = DriverManager.getConnection(url, username, password);

            String sql = "select * from contacts";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            List<Contacts> contacts = new ArrayList<>();

            while (rs.next())
            {
                Contacts c = new Contacts();

                c.setContactID(rs.getInt(1));
                c.setContactName(rs.getString(2));
                c.setMobile(rs.getString(3));
                c.setEmailAddress(rs.getString(4));
                c.setAddressLine1(rs.getString(5));
                c.setAddressLine2(rs.getString(6));
                c.setCity(rs.getString(7));
                c.setState(rs.getString(8));
                c.setCountry(rs.getString(9));

                contacts.add(c);
            }

            con.close();

            return contacts;
        }
        catch (SQLException error)
        {
            error.printStackTrace();
        }

        return new ArrayList<>();
    }

    public void updateByContactID(Contacts c)
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, username, password);

            String sql = "update contacts " +
                    "set contactname=?, mobile=?, emailaddress=?, addressline1=?," +
                    "addressline2=?, city=?, state=?, country=?" +
                    "where contactid=?";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, c.getContactName());
            pst.setString(2, c.getMobile());
            pst.setString(3, c.getEmailAddress());
            pst.setString(4, c.getAddressLine1());
            pst.setString(5, c.getAddressLine2());
            pst.setString(6, c.getCity());
            pst.setString(7, c.getState());
            pst.setString(8, c.getCountry());
            pst.setInt(9, c.getContactID());

            int rows = pst.executeUpdate();

            System.out.println(rows + " row/s affected");

        }
        catch (SQLException error)
        {
            error.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Class Not Found!");
        }
    }

    public void deleteByContactID(int contactID)
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, username, password);

            String sql = "delete from contacts where contactid=" + contactID;

            Statement st = con.createStatement();
            int rows = st.executeUpdate(sql);

            System.out.println(rows + " row/s affected");

        }
        catch (SQLException error)
        {
            error.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Class Not Found!");
        }
    }
}
