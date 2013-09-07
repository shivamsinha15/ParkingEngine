package com.parkingengine.service;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.parkingengine.domain.entities.PERule;
import com.parkingengine.persistance.PERuleDAO;

@Service
public class PERuleService {

  @Inject
  PERuleDAO peRuleDAOImpl;

  @PersistenceUnit(unitName = "ParkDB")
  EntityManagerFactory emf;

  public List<PERule> getAllPERules() {
    return peRuleDAOImpl.findAll();
  }


  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public boolean save(PERule peRule) {
    long peRuleId = peRuleDAOImpl.saveOrUpdate(peRule).getId();
    if (peRuleId != 0L) {
      return true;
    }
    return false;
  }

}
