package com.parkingengine.persistance.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = { "classpath:application-testcontext.xml",
		"classpath:test-infrastructure.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
public abstract class ApplicationContextTestBase {

	


	
	/*
	@Test
	@Rollback(false)
	public void persistPESpace(){
		PERule rule = new PERule();
		Period period = new Period(60);
		LocalTime fromTime = new LocalTime(12, 3, 1);
		LocalTime toTime = new LocalTime(1, 3, 1);
		rule.setId(1L);
		
		PESpace space = new PESpace();
		space.setAddress("sss");
		space.setBearing(34L);
		space.setEndLat(23L);
		space.setId(1L);
		space.setParkingEngineRules(new ArrayList<PERule>(Arrays.asList(rule)));
		peSpaceDAOImpl.merge(space);
	} 
	
	private static Server server;
	
	//Loads the Application Context dor JUNIT then loads the Jetty Webcontext, which has its own applicationContext.
	//This causes due to mulitple application contexts loaded.
	@BeforeClass
	public static void startWebapp() throws Exception {
		server = new Server();

		Connector connector = new SelectChannelConnector();
		connector.setPort(8081);

		server.addConnector(connector);

		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setContextPath("/parking-engine");

		webAppContext.setWar("src/main/webapp");

		server.setHandler(webAppContext);
		//server.start();
	} 
	
		@Test
	public void test() {
		server.isRunning();
		assertEquals(1, 1);
	}
	
		@AfterClass
	public static void stopWebapp() throws Exception {
		server.stop();
	}
	
	*/

	
	
	

}
