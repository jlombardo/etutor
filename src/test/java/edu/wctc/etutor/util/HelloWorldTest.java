package edu.wctc.etutor.util;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class HelloWorldTest {

  @Autowired
  HelloWorld helloWorld;
  
  private final static Logger LOG = LoggerFactory
      .getLogger(HelloWorldTest.class);

  @Test
  public void test() {    
//    LOG.debug(helloWorld.greet());
    assertEquals(helloWorld.greet(), "Hello world. Default setting.");
  }
}
