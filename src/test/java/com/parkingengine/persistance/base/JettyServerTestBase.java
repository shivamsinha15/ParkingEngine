package com.parkingengine.persistance.base;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;

import javax.inject.Inject;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public abstract class JettyServerTestBase {

  protected static Server server;
  protected WebAppContext webAppContext;
  protected static Collection<Field> fields;
  private static ApplicationContext context;

  @BeforeClass
  public static void startWebapp() throws Exception {

    server = new Server();
    Connector connector = new SelectChannelConnector();
    connector.setPort(8081);

    server.addConnector(connector);

    WebAppContext webAppContext = new WebAppContext();
    webAppContext.setParentLoaderPriority(true);
    webAppContext.setContextPath("/parking-engine");
    webAppContext.setWar("src/main/webapp/");
    webAppContext.setDescriptor("WEB-INF/web.xml");
    server.setHandler(webAppContext);
    server.start();

    context =
        WebApplicationContextUtils.getWebApplicationContext(webAppContext.getServletContext());
    

  }

  @AfterClass
  public static void stopWebapp() throws Exception {
    server.dump();
  }

  /*
   * There are two ways of test either directly within a spring container
   * (ApplicationContextTestBase.java) OR within the JettyServer which contains the spring container
   * (JettyServerTestBase). Note if you try and use them at the same time the application context
   * loaded from the JUnit conflicts with the application context loaded within spring.
   * 
   * (Note this was because they are using the name class loader. I believe the unique class =
   * classloader + fully qualified class name Hence setting the jetty webAppContext to use a
   * different class from the JUnit class loader will resolve this issue e.g.:
   * webAppContext.setClassLoader(ClassLoader.getSystemClassLoader());)
   * 
   * 
   * Without the JUnit loaded application context you are unable to inject anything into the JUnit
   * test itself. Hence this method retrieves the applicationContext from the JettyServer and
   * injected the member variables of the extended where annotated with @Inject.
   */

  @Before
  public void intialiseFields() throws BeansException, IllegalArgumentException,
      IllegalAccessException {
    setFields();
    for (Field field : fields) {
      field.setAccessible(true);
      Annotation annot = field.getAnnotation(Inject.class);
      if (annot != null) {
        field.set(this, context.getBean(field.getType()));
      }
    }
  }


  protected abstract void setFields();


}
