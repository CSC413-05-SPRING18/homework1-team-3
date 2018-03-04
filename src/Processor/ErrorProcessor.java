package Processor;

public class ErrorProcessor extends Processor {

    public ErrorProcessor(String args) {
        super(args);
    }

    @Override
    public String process() {
        return "{\"Error\": 0}";

    }
}
