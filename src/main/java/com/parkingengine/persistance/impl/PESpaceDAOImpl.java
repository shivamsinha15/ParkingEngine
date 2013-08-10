package com.parkingengine.persistance.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.parkingengine.domain.entities.PESpace;
import com.parkingengine.persistance.PESpaceDAO;

@Repository
public class PESpaceDAOImpl extends GenericEjb3DAO<PESpace> implements
		PESpaceDAO {
	private static final Logger LOG = LoggerFactory
			.getLogger(PESpaceDAOImpl.class);

	@SuppressWarnings("unchecked")
	public List<PESpace> findAll() {
		return entityManager.createQuery(
				"from " + getEntityBeanType().getName()).getResultList();
	}

	@Override
	@PersistenceContext(unitName = "EconDatesDB")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;

	}

}
