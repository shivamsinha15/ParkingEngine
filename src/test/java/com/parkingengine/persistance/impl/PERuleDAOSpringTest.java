package com.parkingengine.persistance.impl;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.joda.time.LocalTime;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.parkingengine.domain.entities.PERule;
import com.parkingengine.domain.entities.PERule.DaysOfTheWeek;
import com.parkingengine.persistance.PERuleDAO;
import com.parkingengine.persistance.PESpaceDAO;
import com.parkingengine.persistance.base.ApplicationContextTestBase;

public class PERuleDAOSpringTest extends ApplicationContextTestBase {


  @Inject
  PERuleDAO peRuleDAOImpl;

  @Inject
  PESpaceDAO peSpaceDAOImpl;


  @Test
  @Transactional
  @Rollback(false)
  public void persistPERule() {
    PERule rule = new PERule();
    LocalTime fromTime = new LocalTime(12, 3, 1);
    LocalTime toTime = new LocalTime(1, 3, 1);
    LocalTime timeLimit = new LocalTime(1, 3, 1);

    rule.setCost(5.0);
    rule.setFromDay(DaysOfTheWeek.Monday);
    rule.setToDay(DaysOfTheWeek.Friday);

    rule.setTimeLimit(timeLimit);
    rule.setFromTime(fromTime);
    rule.setToTime(toTime);
    peRuleDAOImpl.persist(rule);
    assertEquals(1, 1);
    assertEquals(1, 1);
  }
}
