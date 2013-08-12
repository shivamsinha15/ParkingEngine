package com.parkingengine.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.parkingengine.domain.entities.PERule;
import com.parkingengine.persistance.PERuleDAO;

@Service
public class PERuleService {

  @Inject
  PERuleDAO peRuleDAOImpl;


  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public boolean save(PERule peRule) {
    long peRuleId = peRuleDAOImpl.saveOrUpdate(peRule).getId();
    if (peRuleId != 0L) {
      return true;
    }
    return false;
  }

}
