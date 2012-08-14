package edu.wctc.etutor.util;

import org.springframework.stereotype.Component;

/**
 * A simple to allow demonstration of unit testing with Spring
 * 
 * @author Jim Lombardo, WCTC Lead Java Instructor
 * @see unit test in test package
 */
@Component
public class HelloWorld {

    private String message = "Hello world. Default setting.";

    public String greet() {
        return message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
