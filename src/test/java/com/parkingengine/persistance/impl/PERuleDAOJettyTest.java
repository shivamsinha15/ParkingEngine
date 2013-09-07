package com.parkingengine.persistance.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.parkingengine.persistance.PERuleDAO;
import com.parkingengine.persistance.PESpaceDAO;
import com.parkingengine.persistance.base.JettyServerTestBase;

public class PERuleDAOJettyTest extends JettyServerTestBase {

  /*
  @Inject
  PERuleDAO peRuleDAOImpl;

  @Inject
  PESpaceDAO peSpaceDAOImpl;


  @Test
  @Rollback(false)
  public void persistPERule() {


  }
*/

  @Override
  protected void setFields() {
    fields = new ArrayList<Field>(Arrays.asList(PERuleDAOJettyTest.class.getDeclaredFields()));

  }



}
