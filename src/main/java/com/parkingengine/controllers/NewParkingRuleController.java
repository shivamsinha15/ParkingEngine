package com.parkingengine.controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkingengine.domain.entities.PERule;
import com.parkingengine.domain.entities.PERule.DaysOfTheWeek;
import com.parkingengine.persistance.PERuleDAO;

@Controller
public class NewParkingRuleController {

	@Autowired
	PERuleDAO peRuleDAOImpl;
	@Resource
	private PlatformTransactionManager txManager;

	private static final String FROM_DAY = "fromDay";
	private static final String TO_DAY = "toDay";
	private static final String FROM_TIME = "fromTime";
	private static final String TO_TIME = "toTime";
	private static final String TIME_LIMIT = "timeLimit";
	private static final String COST = "cost";

	/*
	 * var saveDeleteAction = '<a href=\'javascript:alert(\"test\")\';
	 * class="btn btn-small btn-warning"><i
	 * class="btn-icon-only icon-ok"></i></a> <a href="javascript:;"
	 * class="btn btn-small"><i class="btn-icon-only icon-remove"></i></a>'; var
	 * saveDeleteAction = '<a href=\'javascript:alert(\"test\")\';
	 * class="btn btn-small btn-warning"><i
	 * class="btn-icon-only icon-ok"></i></a> <a href="javascript:;"
	 * class="btn btn-small"><i class="btn-icon-only icon-remove"></i></a>';
	 * <li> <a id="group" class="textLink"
	 * href="<c:url value="/data/save?fromDay
	 * =foo&toDay=xxx&cost=5&fromTime=baz&toTime=baz&timeLimit=baz" />">Group of
	 * query parameters</a> </li>
	 */

	@RequestMapping("/NewParkingRule/save")
	public @ResponseBody
	boolean withParamGroup(HttpServletRequest request) {
		PERule peRule = new PERule();
		peRule.setCost(Double.valueOf(request.getParameter(COST)));
		peRule.setFromTime(LocalTime.parse(request.getParameter(FROM_TIME),
				PERule.hoursMinFormatter));
		peRule.setToTime(LocalTime.parse(request.getParameter(TO_TIME),
				PERule.hoursMinFormatter));
		peRule.setTimeLimit(LocalTime.parse(request.getParameter(TIME_LIMIT),
				PERule.hoursMinFormatter));
		peRule.setToDay(DaysOfTheWeek.valueOf(request.getParameter(TO_DAY)));
		peRule.setFromDay(DaysOfTheWeek.valueOf(request.getParameter(FROM_DAY)));
		save(peRule);
		return true;
	}

    /*
	private void save(PERule peRule) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = txManager.getTransaction(def);
		// explicitly setting the transaction name is something that can only be
		// done programmatically
		def.setName("SomeTxName");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		try {
			peRuleDAOImpl.persist(peRule);
		} catch (Exception ex) {
			txManager.rollback(status);
		}
		txManager.commit(status);
	}
	*/

 @Transactional(propagation = Propagation.REQUIRES_NEW)
	    private void save(PERule peRule) {

	            peRuleDAOImpl.persist(peRule);
	 
	    }

	@RequestMapping("/test")
	public @ResponseBody
	String simple() {
		return "Hello Shiv!";
	}

}
