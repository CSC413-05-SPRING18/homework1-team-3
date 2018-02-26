package simpleserver;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;


public class Data {

    private int userid;
    private int postid;
    private String username;


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
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Users: ").append(",");
        builder.append("Username: ").append(username).append(",");
        builder.append("Userid: ").append(userid).append(",");
        return builder.toString();
    }

    private List<Data> data = null;

    public List<Data> getData() {
        return data;
    }
    public void setData(List<Data> data){
        this.data = data;
    }

    public abstract class JsonDeserializerData implements JsonDeserializer<Data> {
        public Data deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                JsonObject user = json.getAsJsonObject();
                String username = user.get("username: ").getAsString();
                int userid = user.get("userid: ").getAsInt();
                String data = user.get("data: ").getAsString();

                User users = new User();
                users.setUsername(username);
                users.setUserID(userid);

                return users;



            }
        }





    }

