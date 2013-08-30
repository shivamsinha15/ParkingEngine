package com.parkingengine.persistance.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.parkingengine.domain.entities.PEMeter;
import com.parkingengine.persistance.PEMeterDAO;

@Repository
public class PEMeterDAOImpl extends GenericEjb3DAO<PEMeter> implements PEMeterDAO {

  @SuppressWarnings("unchecked")
  public List<PEMeter> findAll() {
    return entityManager.createQuery("from " + getEntityBeanType().getName()).getResultList();
  }

  @Override
  @PersistenceContext(unitName = "EconDatesDB")
  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;

  }

}
