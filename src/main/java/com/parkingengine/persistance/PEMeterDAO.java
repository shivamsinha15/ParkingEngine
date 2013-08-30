package com.parkingengine.persistance;

import java.util.Collection;
import java.util.List;

import com.parkingengine.domain.entities.PEMeter;

public interface PEMeterDAO extends GenericDAO<PEMeter> {

  final String JNDI_NAME = "PEMeterDAO";

  List<PEMeter> findAll();

  void mergeCollection(Collection<PEMeter> peMeters);

  void persistCollection(Collection<PEMeter> peMeters);

}
