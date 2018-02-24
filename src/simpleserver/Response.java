package simpleserver;




public class Response {
  private final String status;
  private final int entries;
  private final Data[] data;


  public Response(String status, int entries, Data[] data) {
    this.status = status;
    this.entries = entries;
    this.data = data;
  }


  public String getStatusString() {
    StringBuilder builder = new StringBuilder();
    builder.append("status: ").append(status).append(",");
    builder.append("Entries: ").append((entries)).append(",");
    builder.append("Data: ").append(data).append(":");
    return builder.toString();
  }
}

