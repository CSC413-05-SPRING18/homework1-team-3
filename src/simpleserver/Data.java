package simpleserver;

import java.util.*;


public class Data {

    private int userid;
    private int postid;
    private String username;
    private String data;

    public void setUserID(int new_id)
    {
        userid = new_id;
    }

    public void setUsername(String new_username)
    {
        username = new_username;
    }

    public int getUserID()
    {
        return userid;
    }

    public String getUsername()
    {
        return username;
    }

    //add methods for Post data
}
