package com.projects.cms.model;

public class Contacts
{
    private int contactID;
    private String contactName;
    private String mobile;
    private String emailAddress;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;

    public int getContactID()
    {
        return contactID;
    }

    public void setContactID(int contactID)
    {
        this.contactID = contactID;
    }

    public String getContactName()
    {
        return contactName;
    }

    public void setContactName(String contactName)
    {
        this.contactName = contactName;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getEmailAddress()
    {
        if( emailAddress==null )
            emailAddress="";
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String getAddressLine1()
    {
        if( addressLine1==null )
            addressLine1="";
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1)
    {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2()
    {
        if(addressLine2==null)
            addressLine2="";
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2)
    {
        this.addressLine2 = addressLine2;
    }

    public String getCity()
    {
        if( city==null )
            city="";
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        if( state==null )
            state="";
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getCountry()
    {
        if( country==null )
            country="";
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }
}

