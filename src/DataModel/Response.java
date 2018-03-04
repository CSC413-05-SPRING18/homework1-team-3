package DataModel;

public class Response {

    public String status;
    public iData[] data;
    public int entries = 0;

    public Response(String status, int  entries, iData[] data){
        this.status = status;
        this.data = data;
        this.entries = entries;
    }
}
