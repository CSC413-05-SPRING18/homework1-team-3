package Processor;

public class ErrorProcessor extends Processor{


    @Override
    public String process() {
        return "{\"ERROR\": 0}";
    }
}
