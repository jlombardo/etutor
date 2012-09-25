/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.bean.demo7;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author jlombardo
 */
@ManagedBean(name = "checkBean")
@SessionScoped
public class CheckBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // When offering multiple checkboxes, selects are stored ins an array of Strings
    private String[] checkedSelections;
    
    /**
     * Creates a new instance of CheckBean
     */
    public CheckBean() {
    }
    
    public String saveSelections() {
        // The data was already saved to the 'checkedSelections' array
        // above when the commandButton was pressed. All we need to do here
        // is navigate to the result page.
        return "page2";
    }

    public String[] getCheckedSelections() {
        return checkedSelections;
    }

    public void setCheckedSelections(String[] checkedSelections) {
        this.checkedSelections = checkedSelections;
    }
    
    
}
