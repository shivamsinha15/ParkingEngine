package com.parkingengine.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.parkingengine.domain.entities.PESpace;
import com.parkingengine.persistance.PEMeterDAO;
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

  @Inject
  PEMeterDAO peMeterDAOImpl;

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public long save(PESpace peSpace) {
    long peSpaceId = peSpaceDAOImpl.saveOrUpdate(peSpace).getId();
    return peSpaceId;
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public long setIsOccupied(long spaceId, boolean isOccupied) {
    PESpace peSpace = peSpaceDAOImpl.findById(spaceId);
    peSpace.setOccupied(isOccupied);
    return peSpaceDAOImpl.saveOrUpdate(peSpace).getId();
  }
  
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public List<Long> getRuleIds(long peSpaceId){
    return peSpaceDAOImpl.getRuleIds(peSpaceId);
  }


  public List<PESpace> getAllPESpaces() {
    return peSpaceDAOImpl.findAll();
  }



}
