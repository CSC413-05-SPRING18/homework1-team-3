package Processor;

public class ProcessorFactory {

        public static Processor getProcessor(String resource){
            Processor processor = new ErrorProcessor();

            switch (resource){
                case "/user":
                    processor = new UserProcessor();
                break;
                default:

                break;
            }
            return processor;
        }
}


    public static Processor getProcessor(String resource) {
        Processor processor = new ErrorProcessor(null);
        String endpoint;
        String queryString = null;
        String endpointParts[] = resource.split("\\?");
        endpoint = endpointParts[0];
        if (endpointParts.length == 2) {
            queryString = endpointParts[1];
        }

        switch (endpoint.toLowerCase()) {
            case "/user":
                processor = new UserProcessor(queryString);
                break;
            case "/posts":
                System.out.println("Is Posts!");
                processor = new PostProcessor(queryString);
                break;
            default:
                //later add error code?
                break;
        }
        return processor;
    }
}

