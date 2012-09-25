package edu.wctc.bean.demo6;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author jlombardo
 */
@ManagedBean(name = "normal")
@SessionScoped
public class NormalBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String theValue = "Hello";
    private boolean flag = false;

    public void processActionListener(ActionEvent ae) {
        System.out.println("*** Processed actionListener *****");
        //Get submit button id
        theValue = theValue + " from actionListener";
    }

    public void processValueChangeListener(ValueChangeEvent ve) {
        System.out.println("*** Processed valueChangeListener *****");
        theValue = flag + " from valueChangeListener";
    }

    public String processAction() {
        System.out.println("*** Processed action *****");
        theValue = theValue + " from action event";
        return "page2";
    }

    public String getTheValue() {
        return theValue;
    }

    public void setTheValue(String theValue) {
        this.theValue = theValue;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}