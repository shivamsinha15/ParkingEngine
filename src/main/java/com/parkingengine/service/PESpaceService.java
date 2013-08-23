package com.parkingengine.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.parkingengine.domain.entities.PESpace;
import com.parkingengine.persistance.PESpaceDAO;

@Service
public class PESpaceService {

  @Inject
  PESpaceDAO peSpaceDAOImpl;

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public boolean save(PESpace peSpace) {
    long peSpaceId = peSpaceDAOImpl.saveOrUpdate(peSpace).getId();
    if (peSpaceId != 0L) {
      return true;
    }
    return false;
  }


}
