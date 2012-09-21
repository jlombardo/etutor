package edu.wctc.etutor.controller;

import java.io.Serializable;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author jlombardo
 */
@ManagedBean
@SessionScoped
public class CounterBean implements Serializable {
    private static final long serialVersionUID = 1L;

	private int count;
        private int gaugeCount;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public void increment() {
		count++;
	}
        
        public void incrementGauges() {
            Random r = new Random(System.nanoTime());
            gaugeCount = r.nextInt(101);
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("gauge1.refresh(" + gaugeCount + ")");
            requestContext.execute("gauge2.setValue(" + gaugeCount + ")");            
        }
}