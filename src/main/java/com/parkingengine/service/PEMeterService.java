package com.parkingengine.service;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.parkingengine.domain.entities.PEMeter;
import com.parkingengine.domain.entities.PERule;
import com.parkingengine.domain.entities.PESpace;
import com.parkingengine.persistance.PEMeterDAO;
import com.parkingengine.persistance.PERuleDAO;
import com.parkingengine.persistance.PESpaceDAO;

@Service
public class PEMeterService {

  @Inject
  PEMeterDAO peMeterDAOImpl;

  @Inject
  PESpaceDAO peSpaceDAOImpl;

  @Inject
  PERuleDAO peRuleDAOImpl;

  public List<PEMeter> getAllPEMeter() {
    return peMeterDAOImpl.findAll();
  }

  private static final Logger LOG = LoggerFactory.getLogger(PEMeterService.class);


  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public long save(PEMeter peMeter) {
    long peRuleId = peMeterDAOImpl.saveOrUpdate(peMeter).getId();
    return peRuleId;
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public long mapPESpaceToMeter(long peSpaceId, long meterId) {
    PESpace peSpace = peSpaceDAOImpl.findById(peSpaceId);
    PEMeter peMeter = peMeterDAOImpl.findById(meterId);
    peMeter.addParkingSpaces(Arrays.asList(peSpace));
    peMeter = peMeterDAOImpl.saveOrUpdate(peMeter);
    LOG.info("Mapping PESpaceID: " + peSpace.getId() + " to PEMeterId: " + peMeter.getId());
    return peMeter.getId();
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public long mapPEMeterToPERules(long meterId, long ruleId) {
    PERule peRule = peRuleDAOImpl.findById(ruleId);
    PEMeter peMeter = peMeterDAOImpl.findById(meterId);
    peMeter.addParkingRules(Arrays.asList(peRule));
    peMeter = peMeterDAOImpl.saveOrUpdate(peMeter);
    return peMeter.getId();
  }



}
