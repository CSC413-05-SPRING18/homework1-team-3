package DataModel;

public class ResponseBuilder {
    public String status;
    public iData[] data;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(iData[] data) {
        this.data = data;
    }

    //if single item is passed, makes an array of 1
    public void setData(iData data) {
        if(data!=null) {
            this.data = new iData[1];
            this.data[0] = data;
        }
        else
            this.data = new iData[0];
    }


    public Response build() {
        Response response = new Response(status, data.length, data);
        return response;
    }


}
