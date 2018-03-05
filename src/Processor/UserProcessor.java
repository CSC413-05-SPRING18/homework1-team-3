package Processor;

public class UserProcessor extends Processor {



    @Override
    public String process() {
        return "{\"User\": 0}";
    }
}

import DataModel.Post;
import DataModel.ResponseBuilder;
import DataModel.User;
import com.google.gson.Gson;
import DataModel.Response;

import java.util.*;

public class UserProcessor extends Processor {

    public UserProcessor(String args) {
        super(args);
    }

    @Override
    public String process() {
        ResponseBuilder responseBuilder = new ResponseBuilder();

        //if no arguments, return all users
        if (this.args == null) {
            User allUsers[] = User.getAll();
            responseBuilder.setStatus("OK");
            responseBuilder.setData(allUsers);
        } else {

            String[] argParts = new String[0];
            ArrayList<String> argNames = new ArrayList<String>();
            ArrayList<String> argValues = new ArrayList<String>();


            //splits up the argument parts
            argParts = this.args.split("&");
            for(int i = 0; i < argParts.length; i++)
            {
                String[] argSplit = argParts[i].split("=");
                argNames.add(argSplit[0]);
                argValues.add(argSplit[1]);
            }


            ArrayList<User> users = new ArrayList<>();

            String status = "OK";
            for(int i = 0; i < argParts.length; i++) {
                String argName = argNames.get(i);
                System.out.println("Argument "+i+": "+argName);

                User user = null;
                if (argName.equals("userid")) {                                                     //if argName is userid, search up by user's ID
                    int userid = Integer.parseInt(argValues.get(i));
                    user = User.getUserByUserID(userid);
                }

                //if one of proper arguments
                if(user!=null) {
                    //determines if post already exists in response
                    boolean duplicate = false;
                    for(int x = 0; x < users.size(); x++) {
                        if (users.get(x) == user) {
                            duplicate = true;
                            break;
                        }
                    }

                    //don't add duplicate posts to response
                    if(duplicate == false)
                        users.add(user);
                }
                else
                    status = "Error";

            }
            responseBuilder.setStatus(status);
            responseBuilder.setData(users.toArray(new User[users.size()]));
        }

        Gson gson = new Gson();
        Response response = responseBuilder.build();

        return gson.toJson(response);
    }
}

