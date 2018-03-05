package Processor;

import DataModel.Response;
import DataModel.ResponseBuilder;
import DataModel.Post;
import DataModel.User;
import com.google.gson.Gson;
import java.util.*;

public class PostProcessor extends Processor {

    public PostProcessor(String args) {
        super(args);
    }

    @Override
    public String process() {

        //this function splits it up and does stuff if theres a post ID
        ResponseBuilder responseBuilder = new ResponseBuilder();

        String[] argParts = new String[0];
        ArrayList<String> argNames = new ArrayList<String>();
        ArrayList<String> argValues = new ArrayList<String>();

        try {
            //splits up the argument parts
            argParts = this.args.split("&");
            for(int i = 0; i < argParts.length; i++)
            {
                String[] argSplit = argParts[i].split("=");
                argNames.add(argSplit[0]);
                argValues.add(argSplit[1]);
            }


            ArrayList<Post> posts = new ArrayList<>();

            String status = "OK";
            for(int i = 0; i < argParts.length; i++) {
                String argName = argNames.get(i);

                Post post = null;
                if (argName.equals("postid")) {                                                     //if argName is postid, search by using the post's ID
                    int postid = Integer.parseInt(argValues.get(i));                                //converts string to int
                    post = Post.getPostByPostID(postid);
                } else if (argName.equals("userid")) {                                                     //if argName is userid, search up by user's ID
                    int userid = Integer.parseInt(argValues.get(i));
                    post = Post.getPostByUser(userid);
                }

                //if one of proper arguments
                if(post!=null) {
                    //determines if post already exists in response
                    boolean duplicate = false;
                    for(int x = 0; x < posts.size(); x++) {
                        if (posts.get(x) == post) {
                            duplicate = true;
                            break;
                        }
                    }

                    //don't add duplicate posts to response
                    if(duplicate == false)
                        posts.add(post);
                }
                else
                    status = "Error";

            }
            responseBuilder.setStatus(status);
            responseBuilder.setData(posts.toArray(new Post[posts.size()]));


        } catch(Exception ex){
            //no arguments, so return error
            Post post = null;
            responseBuilder.setStatus("Error");
            responseBuilder.setData(post);
        }


        Gson gson = new Gson();
        Response response = responseBuilder.build();

        return gson.toJson(response);

    }

}
