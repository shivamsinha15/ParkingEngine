package com.parkingengine.controllers;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.LocalTime;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkingengine.domain.entities.PERule;
import com.parkingengine.domain.entities.PERule.DaysOfTheWeek;
import com.parkingengine.domain.entities.PERule.ParkSpaceType;
import com.parkingengine.service.PERuleService;

@Controller
public class PERuleController {

  @Inject
  PERuleService peRuleServiceImpl;
  @Resource
  private PlatformTransactionManager txManager;

  private static final String FROM_DAY = "fromDay";
  private static final String TO_DAY = "toDay";
  private static final String FROM_TIME = "fromTime";
  private static final String TO_TIME = "toTime";
  private static final String TIME_LIMIT = "timeLimit";
  private static final String COST = "cost";
  private static final String PARK_SPACE_TYPE = "parkSpaceType";

  /*-
  var saveDeleteAction = '<a href=\'javascript:alert(\"test\")\';class="btn btn-small btn-warning"><i class="btn-icon-only icon-ok"></i></a> <a href="javascript:;" class="btn btn-small"><i class="btn-icon-only icon-remove"></i></a>'; 
  var saveDeleteAction = '<a href=\'javascript:alert(\"test\")\';class="btn btn-small btn-warning"><i class="btn-icon-only icon-ok"></i></a> <a href="javascript:;" class="btn btn-small"><i class="btn-icon-only icon-remove"></i></a>';
   * <li> 
   *  <a id="group" class="textLink" href="<c:url value="/data/save?fromDay=foo&toDay=xxx&cost=5&fromTime=baz&toTime=baz&timeLimit=baz&parkSpaceType=Normal" />">Group ofquery parameters</a> 
   * </li>
   */

  @RequestMapping("/NewParkingRule/save")
  public @ResponseBody
  boolean withParamGroup(HttpServletRequest request) {
    PERule peRule = new PERule();
    peRule.setCost(Double.valueOf(request.getParameter(COST)));
    peRule.setFromTime(LocalTime.parse(request.getParameter(FROM_TIME), PERule.hoursMinFormatter));
    peRule.setToTime(LocalTime.parse(request.getParameter(TO_TIME), PERule.hoursMinFormatter));
    peRule
        .setTimeLimit(LocalTime.parse(request.getParameter(TIME_LIMIT), PERule.hoursMinFormatter));
    peRule.setToDay(DaysOfTheWeek.valueOf(request.getParameter(TO_DAY)));
    peRule.setFromDay(DaysOfTheWeek.valueOf(request.getParameter(FROM_DAY)));
    peRule.setParkSpaceType(ParkSpaceType.valueOf(request.getParameter(PARK_SPACE_TYPE)));
    return peRuleServiceImpl.save(peRule);
  }

  @RequestMapping("/PERule/all")
  public @ResponseBody
  List<PERule> getAllPERules() {
    return peRuleServiceImpl.getAllPERules();
  }

  @RequestMapping(value = "/PERule/alljsonp", produces = "application/json")
  public @ResponseBody
  String jsonP(HttpServletRequest request) throws JsonGenerationException, JsonMappingException,
      IOException {

    String callBackValue = request.getParameter("callback");
    ObjectMapper objectMapper = new ObjectMapper();

    List<PERule> peRules = peRuleServiceImpl.getAllPERules();
    String responseString = callBackValue + "(" + objectMapper.writeValueAsString(peRules) + ");";
    return responseString;
  }


  @RequestMapping("/test")
  public @ResponseBody
  String simple() {
    return "Hello Shiv!";
  }

}
