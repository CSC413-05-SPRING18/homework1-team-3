package jsonDemo;

import com.google.gson.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class Main {

    public static void main(String[] args) {
        Gson gson = new Gson();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("src/data.json"));
            JsonParser jsonParser = new JsonParser();
            JsonObject obj = jsonParser.parse(br).getAsJsonObject();

            User[] users = gson.fromJson(obj.get("users"), User[].class);
            User.loadAll();

            Response response = new Response();
            response.setUsers(users);
            String jsonString = gson.toJson(User.getUser(0));

            System.out.println(jsonString);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}

class Response{
    User[] users;

    public void setUsers(User[] users){
        this.users = users;
    }
}