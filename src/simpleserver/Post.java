package simpleserver;


public class Post {
    private int postid;
    private int userid;
    private String data;

    public int getPostid(){return postid;}
    public void setPostid(int postid){this.postid = postid; }

    public int getUserid(){ return userid; }
    public void setUserid(int userid){this.userid = userid;}

    public String getData(){return data;}
    public void setData (String data){this.data = data;}

    //should be like User class, but with its own data
}
