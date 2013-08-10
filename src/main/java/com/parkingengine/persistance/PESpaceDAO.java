package com.parkingengine.persistance;

import java.util.Collection;
import java.util.List;

import com.parkingengine.domain.entities.PESpace;

public interface PESpaceDAO extends GenericDAO<PESpace> {

	final String JNDI_NAME = "PESpaceDAO";

	List<PESpace> findAll();

	void mergeCollection(Collection<PESpace> peSpaces);

	void persistCollection(Collection<PESpace> peSpaces);

}
