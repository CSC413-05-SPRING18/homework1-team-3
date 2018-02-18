package response;

public class Response {
  private final String status;
  private final int entries;
  private final Data[] data;

  public Response(String status, int entries, Data[] data){
    this.status = status;
    this.entries = entries;
    this.data = data;
  }

  public String getStatusString(){
    return this.status;
  }
}
