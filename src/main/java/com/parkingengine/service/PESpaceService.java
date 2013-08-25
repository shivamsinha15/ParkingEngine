package com.parkingengine.service;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.parkingengine.domain.entities.PERule;
import com.parkingengine.domain.entities.PESpace;
import com.parkingengine.persistance.PERuleDAO;
import com.parkingengine.persistance.PESpaceDAO;



@Service
public class PESpaceService {

  @Inject
  PERuleService peRuleServiceImpl;

  @Inject
  PESpaceDAO peSpaceDAOImpl;

  @Inject
  PERuleDAO peRuleDAOImpl;


  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public long save(PESpace peSpace) {
    long peSpaceId = peSpaceDAOImpl.saveOrUpdate(peSpace).getId();
    return peSpaceId;
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public long mapPESpaceToRule(Long peSpaceId, Long peRuleId) {
    PESpace peSpace = peSpaceDAOImpl.findById(peSpaceId);
    PERule peRule = peRuleDAOImpl.findById(peRuleId);
    peSpace.setParkingEngineRules((Arrays.asList(peRule)));
    return peSpace.getId();
  }

  public List<PESpace> getAllPESpaces() {
    return peSpaceDAOImpl.findAll();
  }


}
