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
