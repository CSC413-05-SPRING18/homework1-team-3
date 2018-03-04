package simpleserver;

import DataModel.Post;
import DataModel.User;
import Processor.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


class SimpleServer {

    public static void main(String[] args) throws IOException {
        ServerSocket ding;
        Socket dong = null;
        String resource = null;
        String mainRequestLine = null;
        User[] users;
        Post[] posts;

        Gson gson = new Gson();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("src/data.json"));
            JsonParser jsonParser = new JsonParser();
            JsonObject obj = jsonParser.parse(br).getAsJsonObject();

            users = gson.fromJson(obj.get("users"), User[].class);
            posts = gson.fromJson(obj.get("posts"), Post[].class);

            for (int i = 0; i < users.length; i++) {
                users[i].register();
            }
            User.setAll(users);

            for (int i = 0; i < posts.length; i++) {
                posts[i].Register();
            }
            Post.setAll(posts);

        } catch (FileNotFoundException e) {
            System.exit(1);
        }


        try {
            ding = new ServerSocket(1299);
            System.out.println("Opened socket " + 1299);
            while (true) {

                // keeps listening for new clients, one at a time
                try {
                    dong = ding.accept(); // waits for client here
                } catch (IOException e) {
                    System.out.println("Error opening socket");
                    System.exit(1);
                }

                InputStream stream = dong.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(stream));
                try {

                    // read the first line to get the request method, URI and HTTP version
                    String line = in.readLine();
                    System.out.println("----------REQUEST START---------");
                    System.out.println(line);

                    mainRequestLine = line;                                                         //set mainRequestLine to line because we need the first line of the request "GET / HTTP/1.1

                    // read only headers
                    line = in.readLine();
                    while (line != null && line.trim().length() > 0) {
                        int index = line.indexOf(": ");
                        if (index > 0) {
                            System.out.println(line);
                        } else {
                            break;
                        }
                        line = in.readLine();
                    }
                    System.out.println("----------REQUEST END---------\n\n");
                } catch (IOException e) {
                    System.out.println("Error reading");
                    System.exit(1);
                }

                BufferedOutputStream out = new BufferedOutputStream(dong.getOutputStream());
                PrintWriter writer = new PrintWriter(out, true);  // char output to the client

                // every response will always have the status-line, date, and server name
                writer.println("HTTP/1.1 200 OK");
                writer.println("Server: TEST");
                writer.println("Connection: close");
                writer.println("Content-type: application/json");
                writer.println("");


                //do work here
                String linePartsarray[] = mainRequestLine.split(" ");                   //split main request line into an array and put it into linePartsarray[];

                String resourceString = linePartsarray[1];
                System.out.println("Resource String: "+resourceString);
                Processor processor = ProcessorFactory.getProcessor(resourceString);          //put it into processor to get the right processor


                // Body of our response
                writer.println(processor.process());

                dong.close();
            }
        } catch (IOException e) {
            System.out.println("Error opening socket");
            System.exit(1);
        }
    }
}
