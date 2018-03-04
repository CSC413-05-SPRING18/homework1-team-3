package Processor;

public abstract class Processor {               //abstract: have to subclass to use it, final: can't subclass it

    public String args;

    public abstract String process(); //return a string based on resource
    //abstract: have to implement method

    public Processor(String args) {
        this.args = args;
    }
}
