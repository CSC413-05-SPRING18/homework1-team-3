package simpleserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.*;
import java.util.*;

public class ResponseBuilder {

  private Response response;
  private StatusCode statusCode;
  private Gson gson;

  public enum StatusCode {
    OK,
    ERROR_GENERAL,
  }


  public ResponseBuilder()
  {
    //defaults to error because if it's not changed to OK, then things aren't OK
    statusCode = StatusCode.ERROR_GENERAL;
    //initializes the gson variable so that it's not initialized every time a response is built
    gson = new Gson();
  }


  public void setStatus(StatusCode statusCode)
  {
    this.statusCode = statusCode;
  }

  //reads json file and returns Json version
  public JsonObject readData()
  {
    BufferedReader br;
    try {
      br = new BufferedReader(new FileReader("src/simpleserver/data.json"));
      JsonParser jsonParser = new JsonParser();
      JsonObject obj = jsonParser.parse(br).getAsJsonObject();

      return obj;

    } catch (Exception e) {
      e.printStackTrace();
      return new JsonObject();
    }
  }

  //returns Data array, but with User data, for user_id
  public Data[] getUsers(int user_id)
  {
    JsonObject obj = readData();

    //currently, error saying that "simpleserver.User declares multiple JSON fields named userid"
    Data[] users = gson.fromJson(obj.get("users"), User[].class);

    //if user_id=-1, then return all users. Else return user specified by user_id
    if(user_id == -1)
    {
      return users;
    }
    else
    {
      //supposed to return a length 1 array containing User object relating to user_id

//      Data[] user = new User[1];
//      for(int i = 0; i < users.length; i++)
//      {
//        if(users[i].getUserID()==user_id)
//        {
//          user[0] = users[i];
//          break;
//        }
//      }

      return users;

    }

  }

  public Response build()
  {
    String statusString;
    switch (this.statusCode) {
      case OK:
        statusString = "OK";
        break;
      default:
        statusString = "ERROR_GENERAL";
    }

    //for testing, gets all users
    Data[] data = getUsers(-1);

    //prints user data
    for(int i = 0; i < data.length; i++)
    {
      System.out.println(data[i].getUserID());
    }

    return new Response(statusString, 0, data);
  }

  public static void main(String args[]){
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setStatus(StatusCode.ERROR_GENERAL);
    Response response = responseBuilder.build();
    System.out.println(response.getStatusString());
  }
}
