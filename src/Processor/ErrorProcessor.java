package Processor;


public class ErrorProcessor extends Processor{


    @Override
    public String process() {
        return "{\"ERROR\": 0}";

public class ErrorProcessor extends Processor {

    public ErrorProcessor(String args) {
        super(args);
    }

    @Override
    public String process() {
        return "{\"Error\": 0}";


    }
}
