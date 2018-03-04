package DataModel;

import java.util.HashMap;
import java.util.Map;

public class Post implements iData {

    private static Map<Integer, Post> postByID = new HashMap<Integer, Post>();
    private static Map<Integer, Post> postByUser = new HashMap<Integer, Post>();
    private static Map<Integer, String> contentByID = new HashMap<Integer, String>();
    private static Map<Integer, String> contentByUser = new HashMap<Integer, String>();

    private static Post[] allPosts;
    private int postid;
    private int userid;
    private String data;

    public void Register() {
        postByID.put(this.postid, this);
        postByUser.put(this.userid, this);
        contentByID.put(this.postid, data);
        contentByUser.put(this.userid, data);

    }

    public static Post getPostByPostID(int postid) {
        return postByID.get(postid);
    }

    public static Post getPostByUser(int userid) {
        return postByUser.get(userid);
    }

    public static String getContentByPostID(int postid) {
        return contentByID.get(postid);
    }

    public static String getContentByUser(int userid) {
        return contentByUser.get(userid);
    }

    public static void setAll(Post[] allPosts){
        Post.allPosts = allPosts;
    }

    public static Post[] getAll(){
        return allPosts;
    }

}
