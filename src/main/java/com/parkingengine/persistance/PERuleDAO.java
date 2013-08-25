package com.parkingengine.persistance;

import java.util.Collection;
import java.util.List;

import com.parkingengine.domain.entities.PERule;

public interface PERuleDAO extends GenericDAO<PERule> {

  final String JNDI_NAME = "PERuleDAO";

  List<PERule> findAll();

  void mergeCollection(Collection<PERule> peRules);

  void persistCollection(Collection<PERule> peRules);

}
