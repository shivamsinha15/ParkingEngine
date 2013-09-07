package com.parkingengine.persistance.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.parkingengine.domain.entities.PERule;
import com.parkingengine.persistance.PERuleDAO;

@Repository
public class PERuleDAOImpl extends GenericEjb3DAO<PERule> implements PERuleDAO {

	@SuppressWarnings("unchecked")
	public List<PERule> findAll() {
		return entityManager.createQuery(
				"from " + getEntityBeanType().getName()).getResultList();
	}


	@Override
	@PersistenceContext(unitName = "ParkDB")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;

	}

}
